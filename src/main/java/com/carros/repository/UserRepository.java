package com.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carros.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsuLogin(String login);
		 
}
