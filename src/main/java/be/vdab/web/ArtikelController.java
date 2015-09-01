package be.vdab.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Artikel;
import be.vdab.entities.User;
import be.vdab.enums.Ouderdom;
import be.vdab.enums.Soort;
import be.vdab.mail.MailSender;
import be.vdab.services.ArtikelService;
import be.vdab.services.UserService;
import be.vdab.valueobjects.ContactBericht;
import be.vdab.valueobjects.RegioSoortOuderdom;

@Controller
@RequestMapping(value = "/artikels", produces = MediaType.TEXT_HTML_VALUE)
public class ArtikelController {
	private final ArtikelService artikelService;
	private final UserService userService;
	private final MailSender mailSender;
	private static final String ARTIKELS_VIEW = "artikels/artikels";
	private static final String TOEVOEGEN_VIEW = "artikels/toevoegen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/file/upload/{artikel}";
	private static final String ARTIKEL_VIEW = "artikels/artikel";
	private static final String REDIRECT_URL_ARTIKEL_NIET_GEVONDEN = "redirect:/artikels";
	private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/artikels/{id}/verwijderd";
	private static final String VERWIJDERD_VIEW = "artikels/verwijderd";
	private static final String ZOEKEN_VIEW = "artikels/zoeken";
	private static final String WIJZIGEN_VIEW = "artikels/wijzigen";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/user/mijnArtikels";
	private static final String CONTACTEER_VIEW = "artikels/contacteer";
	private static final String REDIRECT_NA_CONTACTEER = "artikels/contacteerSucces";
	private static final String FORBIDDEN = "forbidden";

	@Autowired
	ArtikelController(ArtikelService artikelService, UserService userService,
			MailSender mailSender) {
		this.artikelService = artikelService;
		this.userService = userService;
		this.mailSender = mailSender;
	}

	// if you register artikel it binds it to the logged in user.

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Artikel artikel, BindingResult bindingResult,
			HttpServletRequest request, Principal principal,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		}
		String currentUser = principal.getName();
		User user = userService.findByNaamLike(currentUser);
		artikel.setUser(user);
		artikelService.create(artikel);
		redirectAttributes.addAttribute("artikel", artikel.getId());
		return REDIRECT_URL_NA_TOEVOEGEN;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		List<Soort> soorten = new ArrayList<Soort>(
				Arrays.asList(Soort.values()));
		soorten.remove(0);
		List<Ouderdom> ouderdom = new ArrayList<Ouderdom>(
				Arrays.asList(Ouderdom.values()));
		ouderdom.remove(0);
		return new ModelAndView(TOEVOEGEN_VIEW, "artikel", new Artikel())
				.addObject("soorten", soorten).addObject("ouderdom", ouderdom);
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(ARTIKELS_VIEW, "artikels",
				artikelService.findAll()).addObject("aantalArtikels",
				artikelService.findAantalArtikels());
	}

	@RequestMapping(value = "{artikel}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Artikel artikel, Principal principal) {
		ModelAndView modelAndView = new ModelAndView(ARTIKEL_VIEW);
		String currentUser = principal.getName();
		if (artikel != null) {
			modelAndView.addObject(artikel).addObject(new ReactieForm())
					.addObject("currentUser", currentUser);
		}
		return modelAndView;
	}

	@RequestMapping(value = "{artikel}/verwijderen", method = RequestMethod.POST)
	String delete(@PathVariable Artikel artikel,
			RedirectAttributes redirectAttributes, Principal principal) {
		if (artikel == null) {
			return REDIRECT_URL_ARTIKEL_NIET_GEVONDEN;
		}
		String currentUser = principal.getName();
		if (!artikel.getUser().getNaam().equals(currentUser)) {
			return FORBIDDEN;
		}
		long id = artikel.getId();
		artikelService.delete(id);
		redirectAttributes.addAttribute("id", id).addAttribute("naam",
				artikel.getNaam());
		return REDIRECT_URL_NA_VERWIJDEREN;
	}

	@RequestMapping(value = "{id}/verwijderd", method = RequestMethod.GET)
	ModelAndView deleted(String naam) {
		return new ModelAndView(VERWIJDERD_VIEW, "naam", naam);
	}

	@RequestMapping(value = "zoeken", method = RequestMethod.GET)
	ModelAndView findByZoeken() {
		return new ModelAndView(ZOEKEN_VIEW, "regioSoortOuderdom",
				new RegioSoortOuderdom()).addObject("soorten",
				Arrays.asList(Soort.values())).addObject("ouderdom",
				Arrays.asList(Ouderdom.values()));
	}

	@InitBinder("regioSoortOuderdom")
	void initBinderRegioSoortOuderdom(WebDataBinder dataBinder) {
		dataBinder.setRequiredFields("regioSoortOuderdom");
		dataBinder.initDirectFieldAccess();
	}

	@RequestMapping(value = "artikelsZoeken", method = RequestMethod.GET)
	ModelAndView findByZoeken(@Valid RegioSoortOuderdom regioSoortOuderdom,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(ZOEKEN_VIEW);
		modelAndView.addObject("soorten", Arrays.asList(Soort.values()))
				.addObject("ouderdom", Arrays.asList(Ouderdom.values()));
		List<Artikel> artikels = artikelService.zoeken(regioSoortOuderdom);
		if (artikels.isEmpty()) {
			bindingResult.reject("geenArtikels");
		} else {
			modelAndView.addObject("artikels", artikels);
		}
		return modelAndView;
	}

	@RequestMapping(value = "{artikel}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Artikel artikel, Principal principal) {
		if (artikel == null) {
			return new ModelAndView(REDIRECT_URL_ARTIKEL_NIET_GEVONDEN);
		}
		String currentUser = principal.getName();
		if (!artikel.getUser().getNaam().equals(currentUser)) {
			return new ModelAndView(FORBIDDEN);
		}
		List<Soort> soorten = new ArrayList<Soort>(
				Arrays.asList(Soort.values()));
		soorten.remove(0);
		List<Ouderdom> ouderdom = new ArrayList<Ouderdom>(
				Arrays.asList(Ouderdom.values()));
		ouderdom.remove(0);
		return new ModelAndView(WIJZIGEN_VIEW).addObject(artikel)
				.addObject("soorten", soorten).addObject("ouderdom", ouderdom);
	}

	@RequestMapping(value = "{id}/wijzigen", method = RequestMethod.POST)
	String update(@Valid Artikel artikel, BindingResult bindingResult,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		String currentUser = principal.getName();
		User user = userService.findByNaamLike(currentUser);
		artikel.setUser(user);
		artikelService.update(artikel);
		return REDIRECT_URL_NA_WIJZIGEN;
	}

	@RequestMapping(value = "contacteer/{artikel}", method = RequestMethod.GET)
	ModelAndView contacteerForm(@PathVariable Artikel artikel) {
		ModelAndView modelAndView = new ModelAndView(CONTACTEER_VIEW);
		if (artikel != null) {
			ContactBericht contactBericht = new ContactBericht();
			contactBericht.setArtikel(artikel);
			modelAndView.addObject(contactBericht);
		}
		return modelAndView;
	}
	
	@InitBinder("contactBericht")
	void initBinderContactBericht(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

	@RequestMapping(value = "contacteer", method = RequestMethod.POST)
	public String create(@Valid ContactBericht contactBericht,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return CONTACTEER_VIEW;
		}
		String remoteAddr = request.getRemoteAddr();
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey("6Lf7XgsTAAAAADJQ940XN0Hwc6Qglw2pt2FUKhE1");

		String challenge = request.getParameter("recaptcha_challenge_field");
		String uresponse = request.getParameter("recaptcha_response_field");
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr,
				challenge, uresponse);

		if (!reCaptchaResponse.isValid()) {
			bindingResult.reject("recaptchaFout");
			return CONTACTEER_VIEW;
		}
		Artikel artikel = artikelService.read(contactBericht.getArtikel()
				.getId());
		contactBericht.setArtikel(artikel);
		try {
			mailSender.contacteerGebruikerEmail(contactBericht);
		} catch (MessagingException e) {
			return CONTACTEER_VIEW;
		}
		return REDIRECT_NA_CONTACTEER;
	}

}
