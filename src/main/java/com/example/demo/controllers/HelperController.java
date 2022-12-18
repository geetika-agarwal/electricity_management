package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Helper;
import com.example.demo.services.HelperService;

@RestController
public class HelperController {
	
	@Autowired(required = true)
	HelperService helperService;
	
	// working
	@GetMapping("/helper/login")
	public ResponseEntity<String> helperLogin(@RequestBody Helper helper) {
		return helperService.loginHelper(helper.getEmail(), helper.getPassword());
	}
	
	// working
	@PostMapping("/helper/home")
	public ResponseEntity<String> addBill(@RequestParam String email, @RequestParam Integer units){
		return helperService.addBill(email, units);
	}	
}