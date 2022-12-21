package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.City;

@Repository
@Component
public interface CityRepository extends JpaRepository<City, Integer> {
	@Query("SELECT c FROM City c WHERE c.name = :city")
	City findByCity(String city);
}
