package com.linh.service;

import java.util.List;

import com.linh.entity.CountryEntity;

public interface InCountryService {
      
	List<CountryEntity> findAll();
	
	CountryEntity findOneById(Integer id);
}
