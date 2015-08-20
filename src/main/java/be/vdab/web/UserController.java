package be.vdab.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.User;
import be.vdab.exceptions.EmailExistsException;
import be.vdab.exceptions.NameExistsException;
import be.vdab.services.ArtikelService;
import be.vdab.services.RolService;
import be.vdab.services.UserService;

@Controller
@RequestMapping(value = "/user", produces = MediaType.TEXT_HTML_VALUE)
public class UserController {
	private final UserService userService;
	private final RolService rolService;
	private final ArtikelService artikelService;
	private static final String TOEVOEGEN_VIEW = "user/toevoegen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "user/succes";
	private static final String ARTIKELS_VIEW = "user/artikels";
	private static final String REDIRECT_URL_USER_NIET_GEVONDEN = "redirect:/";
	private static final String WIJZIGEN_VIEW = "user/wijzigen";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "user/wijzigenSucces";
	private static final String GEGEVENS_VIEW = "user/gegevens";

	@Autowired
	UserController(UserService userService, RolService rolService,
			ArtikelService artikelService) {
		this.userService = userService;
		this.rolService = rolService;
		this.artikelService = artikelService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
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
			return TOEVOEGEN_VIEW;
		}

		user.setActief(1);
		user.addRol(rolService.read(2));
		String geencrypteerd = new BCryptPasswordEncoder().encode(user
				.getPaswoord());
		user.setPaswoord(geencrypteerd);
		user.setMatchingPaswoord(geencrypteerd);
		if (!user.getPaswoord().equals(user.getMatchingPaswoord())){
			throw new IllegalArgumentException();
		}
		try {
			userService.create(user);
		} catch (RuntimeException ex) {
			if (ex instanceof EmailExistsException) {
				bindingResult.reject("emailBestaatAl");
			}
			if (ex instanceof NameExistsException) {
				bindingResult.reject("naamBestaatAl");
			}
			if (ex instanceof IllegalArgumentException) {
				bindingResult.reject("paswoordenNietHetzelfde");
			}
			return TOEVOEGEN_VIEW;
		}
		return REDIRECT_URL_NA_TOEVOEGEN;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "user", new User());
	}

	@RequestMapping(value = "mijnArtikels", method = RequestMethod.GET)
	ModelAndView findArtikels(Principal principal) {
		String currentUser = principal.getName();
		User user = userService.findByNaamLike(currentUser);
		return new ModelAndView(ARTIKELS_VIEW, "artikels",
				artikelService.findByUser(user));
	}

	@RequestMapping(value = "{user}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable User user) {
		if (user == null) {
			return new ModelAndView(REDIRECT_URL_USER_NIET_GEVONDEN);
		}
		user.setMatchingPaswoord(user.getPaswoord());
		return new ModelAndView(WIJZIGEN_VIEW).addObject(user);
	}

	@RequestMapping(value = "{id}/wijzigen", method = RequestMethod.POST)
	String update(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		user.setActief(1);
		user.addRol(rolService.read(2));
		try {
			userService.update(user);
		} catch (RuntimeException ex) {
			if (ex instanceof EmailExistsException) {
				bindingResult.reject("emailBestaatAl");
			}
			if (ex instanceof NameExistsException) {
				bindingResult.reject("naamBestaatAl");
			}
			if (ex instanceof IllegalArgumentException) {
				bindingResult.reject("paswoordenNietHetzelfde");
			}
			return WIJZIGEN_VIEW;
		}
		return REDIRECT_URL_NA_WIJZIGEN;
	}
	
	@RequestMapping(value = "gegevens", method = RequestMethod.GET)
	ModelAndView gegevens(Principal principal) {
		String currentUser = principal.getName();
		User user = userService.findByNaamLike(currentUser);
		return new ModelAndView(GEGEVENS_VIEW).addObject("user", user);
	}
}
