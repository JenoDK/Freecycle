package be.vdab.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.User;
import be.vdab.exceptions.EmailExistsException;
import be.vdab.services.RolService;
import be.vdab.services.UserService;

@Controller
@RequestMapping(value = "/user", produces = MediaType.TEXT_HTML_VALUE)
public class UserController {
	private final UserService userService;
	private final RolService rolService;
	private static final String TOEVOEGEN_VIEW = "user/toevoegen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "user/succes";
	
	@Autowired
	UserController(UserService userService, RolService rolService) {
		this.userService = userService;
		this.rolService = rolService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		}
		user.setActief(1);
		user.addRol(rolService.read(2));
		String geencrypteerd = new BCryptPasswordEncoder().encode(user.getPaswoord());
		user.setPaswoord(geencrypteerd);
		user.setMatchingPaswoord(geencrypteerd);
		try{
			userService.create(user);
		}catch (EmailExistsException ex){
			bindingResult.reject("emailBestaatAl");
			return TOEVOEGEN_VIEW;
		}
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
	

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "user", new User());
	}
}
