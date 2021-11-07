package com.linh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linh.entity.RoleEntity;
import com.linh.respository.InRoleRes;
import com.linh.service.InRoleService;

@Service
public class RoleService implements InRoleService {

	@Autowired
	private InRoleRes role;
	
	@Override
	public RoleEntity findOneByName(String name) {
		// TODO Auto-generated method stub
		return role.findOneByName(name);
	}

}
