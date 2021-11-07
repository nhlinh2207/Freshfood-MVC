package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.entity.CountryEntity;

public interface InCountryRes extends JpaRepository<CountryEntity, Integer> {
    
	CountryEntity findOneById(Integer id);
}
