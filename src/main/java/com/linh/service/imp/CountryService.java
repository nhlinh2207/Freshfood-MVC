package com.linh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linh.entity.CountryEntity;
import com.linh.respository.InCountryRes;
import com.linh.service.InCountryService;

@Service
public class CountryService implements InCountryService {
    
	@Autowired
	private InCountryRes country;

	@Override
	public List<CountryEntity> findAll() {
		// TODO Auto-generated method stub
		return country.findAll();
	}

	@Override
	public CountryEntity findOneById(Integer id) {
		// TODO Auto-generated method stub
		return country.findOneById(id);
	}
	
	
}
