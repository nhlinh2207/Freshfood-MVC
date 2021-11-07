package com.linh.service;

import com.linh.entity.RoleEntity;

public interface InRoleService {
       
	RoleEntity findOneByName(String name);
}
