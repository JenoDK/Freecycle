package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Rol;

public interface RolDAO extends JpaRepository<Rol, Long>{
}
