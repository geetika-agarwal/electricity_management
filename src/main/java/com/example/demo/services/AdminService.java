package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Area;
import com.example.demo.entity.City;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.repositories.CityRepository;


@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	AreaRepository areaRepository;
	
	public void addAdmin(Admin admin) {
		adminRepository.save(admin);
	}
	
	public void addCity(City city) {
		if(cityRepository.existsById(city.getId())) {
			System.out.println("City with the particular Id already Exists!!!");
		} else {
			System.out.println("City Added Successfully!!!");
			cityRepository.save(city);
		}
	}
	
	public ResponseEntity<HashMap<Integer, String>> viewAllCities() {
		List<HashMap<Integer, String>> cities = new ArrayList<HashMap<Integer, String>>();
		for (City city : cityRepository.findAll()) {
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			map.put(city.getId(), city.getName());
			cities.add(map);
		}
		return new ResponseEntity(cities, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<HashMap<Integer, String>> viewAllAreas() {
		List<HashMap<Integer, String>> areas = new ArrayList<HashMap<Integer, String>>();
		for (Area area : areaRepository.findAll()) {
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			map.put(area.getId(), area.getAreaName());
			areas.add(map);
		}
		return new ResponseEntity(areas, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<HashMap<Integer, String>> viewAreaByCityName(String cityName) {
		List<HashMap<Integer, String>> areas = new ArrayList<HashMap<Integer, String>>();
		List<City> cities = cityRepository.findAll();
		City city = null;
		for (City c : cities) {
			if(c.getName().equalsIgnoreCase(cityName)) {
				city = c;
				break;
			}
		}
		
		for (Area area : areaRepository.findAll()) {
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			if(area.getCity().getId() == city.getId()) {
				map.put(area.getId(), area.getAreaName());
				areas.add(map);
			}
		}
		
		System.out.println(city);
		return new ResponseEntity(areas, HttpStatus.ACCEPTED);
	}
	
	public City getCityById(int city_id) {
		return cityRepository.getOne(city_id);
	}
	
	public void addArea(int area_id, String area_name, int city_id) {
		if(areaRepository.existsById(area_id)) {
			System.out.println("Area with the same ID already exists!!!");
		}else if(!(cityRepository.findById(city_id).isPresent())) {
			System.out.println("City Doesn't Exist");
		}
		else {
			System.out.println("Area Added Successfully!!!");
			City city = getCityById(city_id);
			Area area = new Area(area_id, area_name, getCityById(city_id));
			areaRepository.save(area);
		}
	}
}
