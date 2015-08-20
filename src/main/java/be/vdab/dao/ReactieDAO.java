package be.vdab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Artikel;
import be.vdab.entities.Reactie;
import be.vdab.entities.User;


public interface ReactieDAO extends JpaRepository<Reactie, Long> {
	List<Reactie> findByUser(User user);
	
	List<Reactie> findByArtikel(Artikel artikel);
}


