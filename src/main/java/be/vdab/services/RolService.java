package be.vdab.services;

import java.util.List;

import be.vdab.entities.Rol;

public interface RolService {
	void create(Rol rol);

	Rol read(long id);

	void update(Rol rol);

	void delete(long id);

	List<Rol> findAll();
	
	long findAantalUsers();
}
