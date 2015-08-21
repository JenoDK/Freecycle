package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Artikel;
import be.vdab.entities.UploadFile;

public interface FileUploadDAO extends JpaRepository<UploadFile, Long>{
	UploadFile findByArtikel(Artikel artikel);
}
