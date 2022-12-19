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
	
	// working
	@GetMapping("/admin/add-admin")
	public void addAdmin() {
		Admin admin1 = new Admin("admin1@gmail.com", "admin@1", "Admin 1");
		Admin admin2 = new Admin("admin2@gmail.com", "admin@2", "Admin 2");
		Admin admin3 = new Admin("admin3@gmail.com", "admin@3", "Admin 3");
		
		adminService.addAdmin(admin1);
		adminService.addAdmin(admin2);
		adminService.addAdmin(admin3);
	}
	
	@PutMapping("/admin/add-consumer-type")
	public void addConsumerType() {
		ConsumerType ct1 = new ConsumerType(1, "Commercial", 8.24);
		ConsumerType ct2 = new ConsumerType(2, "Industrial", 9.73);
		ConsumerType ct3 = new ConsumerType(3, "Domestic", 6.18);
		
		adminService.addConsumerType(ct1);
		adminService.addConsumerType(ct2);
		adminService.addConsumerType(ct3);
	}
	
	// working
	@PostMapping("/admin/register-consumer")
	public ResponseEntity<String> addConsumer(@RequestParam String email, @RequestParam String name, @RequestParam String area_name, @RequestParam String consumer_type_name, @RequestParam String password) {
		return adminService.addConsumer(email, name, area_name, consumer_type_name, password);
	}
	
	// working
	@PostMapping("/admin/add-city")
	public ResponseEntity<String> addCity(@RequestBody City city) {
		return adminService.addCity(city.getName());
	}
	
	// working 
	@PostMapping("/admin/add-area")
	public ResponseEntity<String> addArea(@RequestParam String area_name, @RequestParam String city_name) {
		return adminService.addArea(area_name, city_name);
	}
	
	// working
	@GetMapping("/admin/city")
	public List<CityResponse> viewCity() {
		return adminService.viewAllCities();
	}
	
	// working
	@GetMapping("/admin/area")
	public List<AreaResponse> viewArea() {
		return adminService.viewAllAreas();
	}
	
	// working
	@GetMapping("/admin/area-by-city")
	public ResponseEntity<List<AreaResponse>> viewAreaByCityName(@RequestParam String city) {
		return adminService.viewAreaByCityName(city);
	}
	
	// working
	@GetMapping("/admin/login") 
	public ResponseEntity<String> adminLogin(@RequestParam String email, @RequestParam String password){
		return adminService.loginAdmin(email, password);
	}
	
	// working	
	@PutMapping("/admin/modify-city")
	public ResponseEntity<String> modifyCity(@RequestParam String cityName, @RequestParam String newCityName) {
		return adminService.modifyCity(cityName, newCityName);
	}
	
	// working
	@PutMapping("/admin/modify-area")
	public ResponseEntity<String> modifyAreaName(@RequestParam String areaName, @RequestParam String newAreaName) {
		return adminService.modifyAreaName(areaName, newAreaName);
	}
	
	// working
	@PutMapping("/admin/modify-area-city")
	public ResponseEntity<String> modifyAreaByCityName(@RequestParam String areaName, @RequestParam String newCityName) {
		return adminService.modifyAreaByCityName(areaName, newCityName);
	}
	
	// working
	@PutMapping("/admin/modify-consumer-type-rate")
	public ResponseEntity<String> modifyConsumerTypeRate(@RequestParam int id, @RequestParam double rate){
		return adminService.modifyConsumerTypeRate(id, rate);
	}
	
	// working
	@PostMapping("/admin/add-helper")
	public ResponseEntity<String> addHelper(@RequestParam String email, @RequestParam String password, @RequestParam String name) {
		return adminService.addHelper(email, password, name);
	}
	
	// working
	@GetMapping("/admin/view-helpers")
	public List<HelperResponse> viewAllHelpers(){
		return adminService.viewAllHelpers();
	}
	
	// working
	@GetMapping("/admin/view-consumer-type")
	public List<ConsumerTypeResponse> viewAllConsumerTypes(){
		return adminService.viewAllConsumerTypes();
	}
	
	// working
	@GetMapping("/admin/consumers")
	public List<ConsumerResponse> viewAllConsumers(){
		return adminService.viewAllConsumers();
	}
	
	// working
	@PostMapping("/admin/remove-consumer")
	public ResponseEntity<String> removeConsumer(@RequestParam String email){
		return adminService.removeConsumer(email);
	}
	
	@GetMapping("/admin/view-bill-by-consumer-id")
	public List<Map<String, String>> viewBillsByConsumer(@RequestParam String email){
		return adminService.viewAllBillsByConsumerId(email);
	}
	
	@GetMapping("/admin/view-all-bills")
	public List<BillResponse> viewAllBills(){
		return adminService.viewAllBills();
	}
//	
//	//dummy - to be deleted later
//	@GetMapping("/admin/view-bills")
//	public List<Bill> viewBills(){
//		return adminService.viewBills();
//	}
//	
//	@GetMapping("/admin/view-bill-by-city2")
//	public List<String> viewBillsByCity2(@RequestParam String city){
//		return adminService.viewAllBillsByCity2(city);
//	}
//	
//	@GetMapping("/admin/view-bill-by-area")
//	public List<Map<String, String>> viewBillsByArea(@RequestParam String area){
//		return adminService.viewAllBillsByArea(area);
//	}
//	
//	@GetMapping("/admin/view-bill-by-city")
//	public List<Map<String, String>> viewBillsByCity(@RequestParam String city){
//		return adminService.viewAllBillsByCity(city);
//	}
//	
//	@GetMapping("/admin/view-bill-by-month-and-year")
//	public List<Map<String, String>> viewAllBillsByMonthAndYear(@RequestParam int month, @RequestParam int year){
//		return adminService.viewAllBillsByMonthAndYear(month, year);
//	}
	
}