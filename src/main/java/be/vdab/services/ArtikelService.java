package be.vdab.services;

import java.util.List;

import be.vdab.entities.Artikel;
import be.vdab.entities.User;
import be.vdab.valueobjects.Regio;


public interface ArtikelService {
	void create(Artikel artikel);

	Artikel read(long id);

	void update(Artikel artikel);

	void delete(long id);

	List<Artikel> findAll();
	
	List<Artikel> findByRegioLike(Regio regio);
	
	List<Artikel> findByUser(User user);
	
	long findAantalArtikels();
}
