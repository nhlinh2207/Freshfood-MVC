package com.linh.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linh.model.Country;

public interface ICountryRepo extends JpaRepository<Country, Integer> {
}
