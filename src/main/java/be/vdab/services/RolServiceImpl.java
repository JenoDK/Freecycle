package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import be.vdab.dao.RolDAO;
import be.vdab.entities.Rol;

@ReadOnlyTransactionalService
public class RolServiceImpl implements RolService {
	private final RolDAO rolDAO;

	@Autowired
	RolServiceImpl(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Rol rol) {
		rolDAO.save(rol);
	}

	@Override
	public Rol read(long id) {
		return rolDAO.findOne(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Rol rol) {
		rolDAO.save(rol);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		Rol rol = rolDAO.findOne(id);
		if (rol != null) {
			rolDAO.delete(id);
		}
	}

	@Override
	public List<Rol> findAll() {
		return rolDAO.findAll(new Sort("naam"));
	}

	@Override
	public long findAantalUsers() {
		return rolDAO.count();
	}
}
