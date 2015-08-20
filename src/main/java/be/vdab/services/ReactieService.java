package be.vdab.services;

import java.util.List;

import be.vdab.entities.Artikel;
import be.vdab.entities.Reactie;
import be.vdab.entities.User;

public interface ReactieService {
	void create(Reactie reactie);

	Reactie read(long id);

	void update(Reactie reactie);

	void delete(long id);

	List<Reactie> findAll();

	long findAantalReacties();

	List<Reactie> findByUser(User user);

	List<Reactie> findByArtikel(Artikel artikel);
}
