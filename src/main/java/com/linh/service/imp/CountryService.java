package com.linh.service.imp;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.linh.model.Country;
import com.linh.respository.ICountryRepo;
import com.linh.service.ICountryService;

@Service
@AllArgsConstructor
public class CountryService implements ICountryService {

	private final ICountryRepo countryRepository;

	@Override
	public List<Country> findAll() {
		// TODO Auto-generated method stub
		return countryRepository.findAll();
	}

	@Override
	public Country findOneById(Integer id) {
		// TODO Auto-generated method stub
		return countryRepository.findById(id).get();
	}
	
	
}
