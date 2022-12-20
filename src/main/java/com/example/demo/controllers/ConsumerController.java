package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Consumer;
import com.example.demo.entity.BillResponse;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.services.AdminService;
import com.example.demo.services.ConsumerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ConsumerController {
	
	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	AdminService adminService;
	
	// end point used for login of consumer
	@GetMapping("/login")
	public ResponseEntity<String> consumerLogin(@RequestParam String email, @RequestParam String password) {
		return consumerService.consumerLogin(email, password);
	}
	
	// end point for registering the consumer
	@GetMapping("/register")
	public ResponseEntity<String> register(@RequestParam String email, @RequestParam String name, @RequestParam String area_name, @RequestParam String consumer_type_name, @RequestParam String password) {
		return consumerService.registration(email, name, area_name, consumer_type_name, password);
	}
	
	// end point to view bills of the consumer based on consumer's email
	@GetMapping("/view-bills")
	public List<BillResponse> viewAllBills(@RequestParam String email){
		return adminService.viewAllBillsByCid(email);
	}
	
	// end point to remove the consumer from database
	@GetMapping("/remove-account")
	public ResponseEntity<String> deleteConsumer(@RequestParam String email) {
		return consumerService.removeAccount(email);
	}
	
	// end point to update the name of the consumer in the database
	@GetMapping("/update-name")
	public ResponseEntity<String> updateName(@RequestParam String email, @RequestParam String name) {
		return consumerService.updateName(email, name);
	}
	
	// end point to update the password of the consumer in the database
	@GetMapping("/update-password")
	public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String password) {
		return consumerService.updatePassword(email, password);
	}
	
}