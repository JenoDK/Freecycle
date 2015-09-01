package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;
import be.vdab.entities.User;
import be.vdab.enums.Ouderdom;
import be.vdab.enums.Soort;
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
	public List<Artikel> zoeken(RegioSoortOuderdom regioSoortOuderdom) {
		if (regioSoortOuderdom.getRegio().isEmpty()) {
			if (regioSoortOuderdom.getSoort().equals(Soort.SOORTEN)) {
				if (regioSoortOuderdom.getOuderdom().equals(Ouderdom.OUDERDOM)){
					return artikelDAO.findAll();
				}
				return artikelDAO.findByOuderdomLike(regioSoortOuderdom
						.getOuderdom());
			} else if (regioSoortOuderdom.getOuderdom().equals(
					Ouderdom.OUDERDOM)) {
				return artikelDAO
						.findBySoortLike(regioSoortOuderdom.getSoort());
			} else {
				return artikelDAO.findBySoortLikeAndOuderdomLike(
						regioSoortOuderdom.getSoort(),
						regioSoortOuderdom.getOuderdom());
			}
		}
		if (regioSoortOuderdom.getSoort().equals(Soort.SOORTEN)) {
			if (regioSoortOuderdom.getOuderdom().equals(Ouderdom.OUDERDOM)) {
				return artikelDAO
						.findByRegioLike(regioSoortOuderdom.getRegio());
			} else {
				return artikelDAO.findByRegioLikeAndOuderdomLike(
						regioSoortOuderdom.getRegio(),
						regioSoortOuderdom.getOuderdom());
			}
		}
		if (regioSoortOuderdom.getOuderdom().equals(Ouderdom.OUDERDOM)) {
			return artikelDAO.findByRegioLikeAndSoortLike(
					regioSoortOuderdom.getRegio(),
					regioSoortOuderdom.getSoort());
		}
		return artikelDAO.findByRegioLikeAndSoortLikeAndOuderdomLike(
				regioSoortOuderdom.getRegio(), regioSoortOuderdom.getSoort(),
				regioSoortOuderdom.getOuderdom());
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
