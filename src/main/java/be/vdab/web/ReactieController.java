package be.vdab.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Artikel;
import be.vdab.entities.Reactie;
import be.vdab.entities.User;
import be.vdab.services.ArtikelService;
import be.vdab.services.ReactieService;
import be.vdab.services.UserService;

@Controller
@RequestMapping(value = "/reactie", produces = MediaType.TEXT_HTML_VALUE)
public class ReactieController {
	private final UserService userService;
	private final ReactieService reactieService;
	private final ArtikelService artikelService;
	private static final String REDIRECT_NA_REACTIE = "redirect:/artikels/{id}";

	@Autowired
	ReactieController(ArtikelService artikelService, UserService userService, ReactieService reactieService) {
		this.userService = userService;
		this.reactieService = reactieService;
		this.artikelService = artikelService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid ReactieForm reactieform, BindingResult bindingResult,
			HttpServletRequest request, Principal principal, RedirectAttributes redirectAttributes) {
		Artikel artikel = artikelService.read(reactieform.getArtikel().getId());
		if (bindingResult.hasErrors()) {
			redirectAttributes.addAttribute("id", artikel.getId());
			return REDIRECT_NA_REACTIE;
		}
		String currentUser = principal.getName();
		User user = userService.findByNaamLike(currentUser);
		Reactie reactie = reactieform.getReactie();
		reactie.setUser(user);
		reactie.setArtikel(artikel);
		reactieService.create(reactie);
		redirectAttributes.addAttribute("id", artikel.getId());
		return REDIRECT_NA_REACTIE;
	}

}
