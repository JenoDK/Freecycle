package be.vdab.services;

import be.vdab.entities.Artikel;
import be.vdab.entities.UploadFile;

public interface FileUploadService {
	void create(UploadFile uploadFile);
	
	UploadFile read(long upload_id);
	
	UploadFile findByArtikel(Artikel artikel);
}
