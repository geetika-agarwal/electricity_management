package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Helper;
import com.example.demo.services.HelperService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelperController {
	
	@Autowired(required = true)
	HelperService helperService;
	
	// End point used for login of the helper
	@GetMapping("/helper/login")
	public ResponseEntity<String> helperLogin(@RequestParam String email, @RequestParam String password) {
		return helperService.loginHelper(email, password);
	}
	
	// End point to add bills to the database by the helper using consumer's email
	@GetMapping("/helper/home")
	public ResponseEntity<String> addBill(@RequestParam String email, @RequestParam Integer units){
		return helperService.addBill(email, units);
	}	
}
