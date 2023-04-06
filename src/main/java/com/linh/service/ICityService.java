package com.linh.service;

import java.util.List;

import com.linh.model.City;
import com.linh.model.Country;

public interface ICityService {
    
	List<City> findByCountry(Country country);

	City findById(Integer id);
}
