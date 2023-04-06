package com.linh.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.model.City;
import com.linh.model.Country;

public interface ICitytRepo extends JpaRepository<City, Integer>{
	
	List<City> findByCountry(Country country);
}
