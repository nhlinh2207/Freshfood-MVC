package com.linh.service;

import java.util.List;

import com.linh.entity.CityEntity;
import com.linh.entity.CountryEntity;

public interface InCityService {
    
	List<CityEntity> findByCountry(CountryEntity countryEntity);
	CityEntity findOneById(Integer id);
}
