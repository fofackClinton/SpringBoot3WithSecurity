package com.AppJoke.joke.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppJoke.joke.entities.Role;




public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
	
}
