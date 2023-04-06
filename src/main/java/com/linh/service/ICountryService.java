package com.linh.service;

import java.util.List;

import com.linh.model.Country;

public interface ICountryService {
      
	List<Country> findAll();
	
	Country findOneById(Integer id);
}
