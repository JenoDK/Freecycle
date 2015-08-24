package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;
import be.vdab.entities.User;
import be.vdab.valueobjects.RegioSoortOuderdom;

@ReadOnlyTransactionalService
public class ArtikelServiceImpl implements ArtikelService {
	private final ArtikelDAO artikelDAO;

	@Autowired
	ArtikelServiceImpl(ArtikelDAO artikelDAO) {
		this.artikelDAO = artikelDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Artikel artikel) {
		artikel.setId(artikelDAO.save(artikel).getId());
	}

	@Override
	public Artikel read(long id) {
		return artikelDAO.findOne(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Artikel artikel) {
		artikelDAO.save(artikel);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		Artikel artikel = artikelDAO.findOne(id);
		if (artikel != null) {
			artikelDAO.delete(id);
		}
	}

	@Override
	public List<Artikel> findAll() {
		return artikelDAO.findAll(new Sort("regio"));
	}

	@Override
	public List<Artikel> findByRegioLike(RegioSoortOuderdom regioSoortOuderdom) {
		return artikelDAO.findByRegioLike(regioSoortOuderdom.getRegio());
	}

	@Override
	public List<Artikel> findBySoortLike(RegioSoortOuderdom regioSoortOuderdom) {
		return artikelDAO.findBySoortLike(regioSoortOuderdom.getSoort());
	}

	@Override
	public List<Artikel> findByRegioLikeAndSoortLike(
			RegioSoortOuderdom regioSoortOuderdom) {
		return artikelDAO.findByRegioLikeAndSoortLike(regioSoortOuderdom.getRegio(), regioSoortOuderdom.getSoort());
	}

	@Override
	public long findAantalArtikels() {
		return artikelDAO.count();
	}

	@Override
	public List<Artikel> findByUser(User user) {
		return artikelDAO.findByUser(user);
	}

}
