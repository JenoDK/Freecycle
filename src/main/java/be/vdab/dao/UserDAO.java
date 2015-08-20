package be.vdab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
	User findByEmailLike(String email);

	User findByNaamLike(String naam);

	List<User> findByIdNotLike(long id);

}
