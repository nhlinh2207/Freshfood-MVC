package com.linh.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.linh.model.Role;
import com.linh.respository.IRoleRepository;
import com.linh.service.IRoleService;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {

	private final IRoleRepository roleRepository;
	
	@Override
	public Role findOneByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}

}
