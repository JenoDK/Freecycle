package be.vdab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Artikel;
import be.vdab.entities.User;
import be.vdab.enums.Ouderdom;
import be.vdab.enums.Soort;


public interface ArtikelDAO extends JpaRepository<Artikel, Long> {
	List<Artikel> findByUser(User user);
	
	List<Artikel> findByRegioLike(String regio);
	
	List<Artikel> findBySoortLike(Soort soort);
	
	List<Artikel> findByOuderdomLike(Ouderdom ouderdom);
	
	List<Artikel> findByRegioLikeAndSoortLike(String regio, Soort soort);
	
	List<Artikel> findByRegioLikeAndOuderdomLike(String regio, Ouderdom ouderdom);
	
	List<Artikel> findBySoortLikeAndOuderdomLike(Soort soort, Ouderdom ouderdom);
	
	List<Artikel> findByRegioLikeAndSoortLikeAndOuderdomLike(String regio, Soort soort, Ouderdom ouderdom);
	
}
