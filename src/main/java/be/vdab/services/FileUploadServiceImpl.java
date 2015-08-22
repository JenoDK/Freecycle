package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;

import be.vdab.dao.FileUploadDAO;
import be.vdab.entities.Artikel;
import be.vdab.entities.UploadFile;

@ReadOnlyTransactionalService
public class FileUploadServiceImpl implements FileUploadService {
	private final FileUploadDAO fileUploadDAO;

	@Autowired
	FileUploadServiceImpl(FileUploadDAO fileUploadDAO) {
		this.fileUploadDAO = fileUploadDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(UploadFile uploadFile) {
		uploadFile.setId(fileUploadDAO.save(uploadFile).getId());

	}

	@Override
	public UploadFile read(long upload_id) {
		return fileUploadDAO.findOne(upload_id);
	}

	@Override
	public UploadFile findByArtikel(Artikel artikel) {
		return fileUploadDAO.findByArtikel(artikel);
	}
	
	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		UploadFile uploadFile = fileUploadDAO.findOne(id);
		if (uploadFile != null) {
			fileUploadDAO.delete(id);
		}
	}

}
