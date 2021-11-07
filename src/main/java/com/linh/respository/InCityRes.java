package com.linh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.entity.CityEntity;
import com.linh.entity.CountryEntity;

public interface InCityRes extends JpaRepository<CityEntity, Integer>{
	
	List<CityEntity> findByCountry(CountryEntity countryEntity);
	
	CityEntity findOneById(Integer id);
}
