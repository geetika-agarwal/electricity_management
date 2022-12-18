package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Consumer;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.services.ConsumerService;

@RestController
public class ConsumerController {
	
	@Autowired
	ConsumerService consumerService;
	
	// working
	@GetMapping("/login")
	public ResponseEntity<String> consumerLogin(@RequestParam String email, @RequestParam String password) {
		return consumerService.consumerLogin(new Consumer(email, password));
	}
	
	// working
	@PostMapping("/register")
	public void register(@RequestParam String email, @RequestParam String name, @RequestParam String area_name, 
			@RequestParam String consumer_type_name, @RequestParam String password) {
		consumerService.registration(email, name, area_name, consumer_type_name, password);
	}
	
}