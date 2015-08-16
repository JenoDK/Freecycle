package be.vdab.services;

import java.util.List;

import be.vdab.entities.User;

public interface UserService {
	void create(User user);

	User read(long id);

	void update(User user);

	void delete(long id);

	List<User> findAll();
	
	long findAantalUsers();
	
	User findByEmailLike(String email);
}
