package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.AreaResponse;
import com.example.demo.entity.Bill;
import com.example.demo.entity.BillResponse;
import com.example.demo.entity.City;
import com.example.demo.entity.CityResponse;
import com.example.demo.entity.Consumer;
import com.example.demo.entity.ConsumerResponse;
import com.example.demo.entity.ConsumerType;
import com.example.demo.entity.ConsumerTypeResponse;
import com.example.demo.entity.Helper;
import com.example.demo.entity.HelperResponse;
import com.example.demo.services.AdminService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	// Adding Admin to the Database. Hard coded directly
	@GetMapping("/admin/add-admin")
	public void addAdmin() {
		Admin admin1 = new Admin("admin1@gmail.com", "admin@1", "Admin 1");
		Admin admin2 = new Admin("admin2@gmail.com", "admin@2", "Admin 2");
		Admin admin3 = new Admin("admin3@gmail.com", "admin@3", "Admin 3");
		
		adminService.addAdmin(admin1);
		adminService.addAdmin(admin2);
		adminService.addAdmin(admin3);
	}
	
	// Adding Consumer Type to the Database. Hard Coded directly
	@PutMapping("/admin/add-consumer-type")
	public void addConsumerType() {
		ConsumerType ct1 = new ConsumerType(1, "Commercial", 8.24);
		ConsumerType ct2 = new ConsumerType(2, "Industrial", 9.73);
		ConsumerType ct3 = new ConsumerType(3, "Domestic", 6.18);
		
		adminService.addConsumerType(ct1);
		adminService.addConsumerType(ct2);
		adminService.addConsumerType(ct3);
	}
	
	// End point to add a city into database
	@GetMapping("/admin/add-city")
	public ResponseEntity<String> addCity(@RequestParam String name) {
		return adminService.addCity(name);
	}
	
	// End point to add area to the database 
	@GetMapping("/admin/add-area")
	public ResponseEntity<String> addArea(@RequestParam String areaname, @RequestParam String cityname) {
		return adminService.addArea(areaname, cityname);
	}
	
	// End point to view all  cities present in the database
	@GetMapping("/admin/city")
	public List<CityResponse> viewCity() {
		return adminService.viewAllCities();
	}
	
	// End point to view all areas present in the database
	@GetMapping("/admin/area")
	public List<AreaResponse> viewArea() {
		return adminService.viewAllAreas();
	}
	
	// working
	@GetMapping("/admin/area-by-city")
	public ResponseEntity<List<AreaResponse>> viewAreaByCityName(@RequestParam String city) {
		return adminService.viewAreaByCityName(city);
	}
	
	// end point used for login of the admin
	@GetMapping("/admin/login") 
	public ResponseEntity<String> adminLogin(@RequestParam String email, @RequestParam String password){
		return adminService.loginAdmin(email, password);
	}
	
	// end point to modify the city name in the database to a new city name	
	@GetMapping("/admin/modify-city")
	public ResponseEntity<String> modifyCity(@RequestParam String cityName, @RequestParam String newCityName) {
		return adminService.modifyCity(cityName, newCityName);
	}
	
	// end point to modify the area name in the database to a new area name
	@PutMapping("/admin/modify-area")
	public ResponseEntity<String> modifyAreaName(@RequestParam String areaName, @RequestParam String newAreaName) {
		return adminService.modifyAreaName(areaName, newAreaName);
	}
	
	// end point to modify the city for a particular area using area's name
	@GetMapping("/admin/modify-area-city")
	public ResponseEntity<String> modifyAreaByCityName(@RequestParam String areaName, @RequestParam String newCityName) {
		return adminService.modifyAreaByCityName(areaName, newCityName);
	}
	
	// end point to modify the consumer type's rate
	@GetMapping("/admin/modify-consumer-type-rate")
	public ResponseEntity<String> modifyConsumerTypeRate(@RequestParam String typeName, @RequestParam double rate){
		return adminService.modifyConsumerTypeRate(typeName, rate);
	}
	
	// end point to view all helpers
	@GetMapping("/admin/view-helpers")
	public List<HelperResponse> viewAllHelpers(){
		return adminService.viewAllHelpers();
	}
	
	// end point to view all consumer types
	@GetMapping("/admin/view-consumer-type")
	public List<ConsumerTypeResponse> viewAllConsumerTypes(){
		return adminService.viewAllConsumerTypes();
	}
	
	// end point to view all consumers
	@GetMapping("/admin/consumers")
	public List<ConsumerResponse> viewAllConsumers(){
		return adminService.viewAllConsumers();
	}
	
	// end point to view all bills
	@GetMapping("/admin/view-all-bills")
	public List<BillResponse> viewAllBills(){
		return adminService.viewAllBills();
	}

	// end point to view all bills by consumer's email
	@GetMapping("/admin/view-bill-by-cid")
	public List<BillResponse> viewBillsByCid(@RequestParam String email){
		return adminService.viewAllBillsByCid(email);
	}
	
	// End point to view all bills by month and year of the bill date
	@GetMapping("/admin/view-bill-by-mny")
	public List<BillResponse> viewAllBillsByMonthAndYear(@RequestParam String month, @RequestParam int year){
		return adminService.viewAllBillsByMnY(month, year);
	}
	
	// end point to view bills by city of the consumer
	@GetMapping("/admin/view-bill-by-city")
	public List<BillResponse> viewAllBillsByCity(@RequestParam String city){
		return adminService.viewAllBillsByCity(city);
	}
	
	// end point to view bills by area of the consumer
	@GetMapping("/admin/view-bill-by-area")
	public List<BillResponse> viewAllBillsByArea(@RequestParam String area){
		return adminService.viewAllBillsByArea(area);
	}
}
