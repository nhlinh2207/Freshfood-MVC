package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.entity.RoleEntity;

public interface InRoleRes extends JpaRepository<RoleEntity, Integer> {
      
	RoleEntity findOneByName(String name);
}
