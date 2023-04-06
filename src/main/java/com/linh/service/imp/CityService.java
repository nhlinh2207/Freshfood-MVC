package com.linh.service.imp;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.linh.model.City;
import com.linh.model.Country;
import com.linh.respository.ICitytRepo;
import com.linh.service.ICityService;

@Service
@AllArgsConstructor
public class CityService implements ICityService {

	private final ICitytRepo cityRepository;
	
	@Override
	public List<City> findByCountry(Country countryEntity) {
		// TODO Auto-generated method stub
		return cityRepository.findByCountry(countryEntity);
	}

	@Override
	public City findById(Integer id) {
		// TODO Auto-generated method stub
		return cityRepository.findById(id).get();
	}
	
	
   
}
