package com.AppJoke.joke.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppJoke.joke.entities.User;




public interface UserRepository extends JpaRepository<User, Long> {

		User findByUsername(String username);

}
