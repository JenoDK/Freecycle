package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import be.vdab.dao.ReactieDAO;
import be.vdab.entities.Artikel;
import be.vdab.entities.Reactie;
import be.vdab.entities.User;

@ReadOnlyTransactionalService
public class ReactieServiceImpl implements ReactieService {

	private final ReactieDAO reactieDAO;

	@Autowired
	ReactieServiceImpl(ReactieDAO reactieDAO) {
		this.reactieDAO = reactieDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Reactie reactie) {
		reactie.setId(reactieDAO.save(reactie).getId());
	}

	@Override
	public Reactie read(long id) {
		return reactieDAO.findOne(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Reactie reactie) {
		reactieDAO.save(reactie);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		Reactie reactie = reactieDAO.findOne(id);
		if (reactie != null) {
			reactieDAO.delete(id);
		}
	}

	@Override
	public List<Reactie> findAll() {
		return reactieDAO.findAll(new Sort("id"));
	}

	@Override
	public long findAantalReacties() {
		return reactieDAO.count();
	}

	@Override
	public List<Reactie> findByUser(User user) {
		return reactieDAO.findByUser(user);
	}

	@Override
	public List<Reactie> findByArtikel(Artikel artikel) {
		return reactieDAO.findByArtikel(artikel);
	}

}
