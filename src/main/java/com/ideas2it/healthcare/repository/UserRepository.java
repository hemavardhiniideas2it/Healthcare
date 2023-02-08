package com.ideas2it.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.healthcare.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	
}
