package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.User;

public interface UserDAO extends JpaRepository<User, Long>{
	User findByEmailLike(String email);
}
