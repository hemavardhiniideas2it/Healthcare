package com.ideas2it.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.healthcare.entity.Role;

public interface RoleRepository  extends JpaRepository<Role, Integer>{

	
}
