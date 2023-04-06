package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
      
	Role findByName(String name);
}
