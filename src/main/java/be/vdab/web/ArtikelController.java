package be.vdab.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Artikel;
import be.vdab.enums.Ouderdom;
import be.vdab.enums.Soort;
import be.vdab.services.ArtikelService;
import be.vdab.valueobjects.Regio;

@Controller
@RequestMapping(value = "/artikels", produces = MediaType.TEXT_HTML_VALUE)
public class ArtikelController {
	private final ArtikelService artikelService;
	private static final String ARTIKELS_VIEW = "artikels/artikels";
	private static final String TOEVOEGEN_VIEW = "artikels/toevoegen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/artikels";
	private static final String ARTIKEL_VIEW = "artikels/artikel";
	private static final String REDIRECT_URL_ARTIKEL_NIET_GEVONDEN = "redirect:/artikels";
	private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/artikels/{id}/verwijderd";
	private static final String VERWIJDERD_VIEW = "artikels/verwijderd";
	private static final String PER_REGIO_VIEW = "artikels/perregio";
	private static final String WIJZIGEN_VIEW = "artikels/wijzigen";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/artikels";

	@Autowired
	ArtikelController(ArtikelService artikelService) {
		this.artikelService = artikelService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Artikel artikel, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		}
		artikelService.create(artikel);
		return REDIRECT_URL_NA_TOEVOEGEN;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "artikel", new Artikel())
				.addObject("soorten", Arrays.asList(Soort.values())).addObject(
						"ouderdom", Arrays.asList(Ouderdom.values()));
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(ARTIKELS_VIEW, "artikels",
				artikelService.findAll()).addObject("aantalArtikels",
				artikelService.findAantalArtikels());
	}

	@RequestMapping(value = "{artikel}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Artikel artikel) {
		ModelAndView modelAndView = new ModelAndView(ARTIKEL_VIEW);
		if (artikel != null) {
			modelAndView.addObject(artikel);
		}
		return modelAndView;
	}

	@RequestMapping(value = "{artikel}/verwijderen", method = RequestMethod.POST)
	String delete(@PathVariable Artikel artikel,
			RedirectAttributes redirectAttributes) {
		if (artikel == null) {
			return REDIRECT_URL_ARTIKEL_NIET_GEVONDEN;
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

	@RequestMapping(value = "regio", method = RequestMethod.GET)
	ModelAndView findByRegio() {
		Regio regio = new Regio();
		return new ModelAndView(PER_REGIO_VIEW).addObject(regio);
	}
	
	@InitBinder("regio")
	void initBinderRegio(DataBinder dataBinder) {
		dataBinder.setRequiredFields("regio");
	}

	@RequestMapping(method = RequestMethod.GET, params = { "regio" })
	ModelAndView findByRegio(@ModelAttribute Regio regio, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(PER_REGIO_VIEW);
		if (!bindingResult.hasErrors()) {
			List<Artikel> artikels = artikelService.findByRegioLike(regio);
			if (artikels.isEmpty()) {
				bindingResult.reject("geenArtikels");
			} else {
				modelAndView.addObject("artikels", artikels);
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "{artikel}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Artikel artikel) {
		if (artikel == null) {
			return new ModelAndView(REDIRECT_URL_ARTIKEL_NIET_GEVONDEN);
		}
		return new ModelAndView(WIJZIGEN_VIEW).addObject(artikel);
	}

	@RequestMapping(value = "{id}/wijzigen", method = RequestMethod.POST)
	String update(@Valid Artikel artikel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		artikelService.update(artikel);
		return REDIRECT_URL_NA_WIJZIGEN;
	}

}