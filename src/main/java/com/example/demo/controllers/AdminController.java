package com.example.demo.controllers;

import java.util.HashMap;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Area;
import com.example.demo.entity.City;
import com.example.demo.entity.Consumer;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.services.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/admin/add-admin")
	public void addAdmin() {
		Admin admin1 = new Admin("admin1@gmail.com", "admin@1", "Admin 1");
		Admin admin2 = new Admin("admin2@gmail.com", "admin@2", "Admin 2");
		Admin admin3 = new Admin("admin3@gmail.com", "admin@3", "Admin 3");
		
		adminService.addAdmin(admin1);
		adminService.addAdmin(admin2);
		adminService.addAdmin(admin3);
	}
	
	@GetMapping("/admin/register-consumer")
	public void addConsumer(@RequestBody Consumer consumer) {
	}
	
	@PutMapping("/admin/add-city")
	public void addCity(@RequestBody City city) {
		adminService.addCity(city);
	}
	
	@PutMapping("/admin/add-area")
	public void addArea(@RequestParam("area_id") Integer area_id, @RequestParam("area_name") String area_name, @RequestParam("city_id") Integer city_id) {
		adminService.addArea(area_id, area_name, city_id);
	}
	
	@GetMapping("/admin/city")
	public ResponseEntity<HashMap<Integer, String>> viewCity() {
		return adminService.viewAllCities();
	}
	
	@GetMapping("/admin/area")
	public ResponseEntity<HashMap<Integer, String>> viewArea() {
		return adminService.viewAllAreas();
	}
	
	@GetMapping("/admin/area/{city_name}")
	public ResponseEntity<HashMap<Integer, String>> viewAreaByCityName(@PathParam("city") String cityName) {
		return adminService.viewAreaByCityName(cityName);
	}
}
