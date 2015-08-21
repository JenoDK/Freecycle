package be.vdab.web;

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

	@Autowired
	FileUploadController(FileUploadService fileUploadService,
			ArtikelService artikelService) {
		this.fileUploadService = fileUploadService;
		this.artikelService = artikelService;
	}

	@RequestMapping(value = "upload/{artikel}", method = RequestMethod.GET)
	ModelAndView createForm(@PathVariable Artikel artikel) {
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
				return UPLOAD_VIEW;
			}
			UploadFile fileUpload = new UploadFile();
			fileUpload.setFileName(uploadFile.getOriginalFilename());
			fileUpload.setData(uploadFile.getBytes());
			fileUpload.setArtikel(artikel);
			fileUploadService.create(fileUpload);
			redirectAttributes.addAttribute("id", artikel.getId());
			return SUCCES_VIEW;
		}
		return UPLOAD_VIEW;

	}

	@RequestMapping(value = "show/{artikelId}")
	@ResponseBody
	public byte[] helloWorld(@PathVariable long artikelId) {
		Artikel artikel = artikelService.read(artikelId);
		try {
			UploadFile image = fileUploadService.findByArtikel(artikel);
			return image.getData();
		} catch (NullPointerException ex) {
			return null;
		}

	}
}
