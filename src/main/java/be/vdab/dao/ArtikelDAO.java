package be.vdab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Artikel;
import be.vdab.entities.User;


public interface ArtikelDAO extends JpaRepository<Artikel, Long> {
	List<Artikel> findByRegioLike(String regio);
	
	List<Artikel> findByUser(User user);
}
