package com.linh.api.web;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.linh.model.City;
import com.linh.respository.ICitytRepo;
import com.linh.respository.ICountryRepo;

@RestController
@AllArgsConstructor
public class CityAPI {

	private final ICitytRepo city;
	private final ICountryRepo country;
    
	@GetMapping(value = "/freshfood/api/city/{id}")
	public List<City> getCity(@PathVariable Integer id) {
		List<City> c = new ArrayList<City>();
		for(City i : city.findByCountry(country.findById(id).get())) {
			  City e = City.builder().id(i.getId()).name(i.getName()).build();
			  c.add(e);
		}
		return c;
	}
}
