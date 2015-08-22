package be.vdab.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Artikel;
import be.vdab.entities.UploadFile;
import be.vdab.services.ArtikelService;
import be.vdab.services.FileUploadService;
import be.vdab.validators.ImageValidator;

@Controller
@RequestMapping(value = "/file")
public class FileUploadController {
	private final FileUploadService fileUploadService;
	private final ArtikelService artikelService;
	private static final String UPLOAD_VIEW = "file/upload";
	private static final String SUCCES_VIEW = "redirect:/artikels/{id}";
	private static final String REDIRECT_URL_ARTIKEL_NIET_GEVONDEN = "redirect:/artikels";
	private static final String FORBIDDEN = "forbidden";
	private static final String UPLOAD_VIEW_AFTER_FAIL = "redirect:/file/upload/{artikel}";

	@Autowired
	FileUploadController(FileUploadService fileUploadService,
			ArtikelService artikelService) {
		this.fileUploadService = fileUploadService;
		this.artikelService = artikelService;
	}

	@RequestMapping(value = "upload/{artikel}", method = RequestMethod.GET)
	ModelAndView createForm(@PathVariable Artikel artikel, Principal principal) {
		if (artikel == null) {
			return new ModelAndView(REDIRECT_URL_ARTIKEL_NIET_GEVONDEN);
		}
		String currentUser = principal.getName();
		if (!artikel.getUser().getNaam().equals(currentUser)) {
			return new ModelAndView(FORBIDDEN);
		}
		return new ModelAndView(UPLOAD_VIEW).addObject("artikel", artikel);
	}

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam long artikelid,
			@RequestParam CommonsMultipartFile uploadFile,
			RedirectAttributes redirectAttributes) throws Exception {
		Artikel artikel = artikelService.read(artikelid);
		if (uploadFile != null) {
			ImageValidator imageValidator = new ImageValidator();
			if (!imageValidator.validate(uploadFile.getOriginalFilename())) {
				redirectAttributes.addAttribute("artikel", artikel.getId());
				redirectAttributes.addAttribute("fout", "Verkeerde type bestand of spaties in bestandsnaam");
				return UPLOAD_VIEW_AFTER_FAIL;
			}
			UploadFile fileUpload = new UploadFile();
			fileUpload.setFileName(uploadFile.getOriginalFilename());
			fileUpload.setData(uploadFile.getBytes());
			fileUpload.setArtikel(artikel);
			fileUploadService.create(fileUpload);
			redirectAttributes.addAttribute("id", artikel.getId());
			return SUCCES_VIEW;
		}
		redirectAttributes.addAttribute("artikel", artikel.getId());
		redirectAttributes.addAttribute("fout", "Bestand te groot");
		return UPLOAD_VIEW_AFTER_FAIL;

	}

	@RequestMapping(value = "show/{id}")
	@ResponseBody
	public byte[] helloWorld(@PathVariable long id) {
		try {
			UploadFile uploadFile = fileUploadService.read(id);
			return uploadFile.getData();
		} catch (NullPointerException ex) {
			return null;
		}

	}
}
