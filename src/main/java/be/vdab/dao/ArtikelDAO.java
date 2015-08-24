package be.vdab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Artikel;
import be.vdab.entities.User;
import be.vdab.enums.Soort;


public interface ArtikelDAO extends JpaRepository<Artikel, Long> {
	List<Artikel> findByRegioLike(String regio);
	
	List<Artikel> findByUser(User user);
	
	List<Artikel> findBySoortLike(Soort soort);
	
	List<Artikel> findByRegioLikeAndSoortLike(String regio, Soort soort);
	
}
