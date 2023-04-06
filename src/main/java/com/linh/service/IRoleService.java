package com.linh.service;

import com.linh.model.Role;

public interface IRoleService {
       
	Role findOneByName(String name);
}
