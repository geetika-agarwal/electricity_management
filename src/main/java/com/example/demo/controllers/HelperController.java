package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Helper;
import com.example.demo.services.HelperService;

@RestController
public class HelperController {
	
	@Autowired(required = true)
	HelperService helperService;
	
	@GetMapping("/helper/add-helper")
	public void addHelper() {
		Helper helper1 = new Helper("helper1@gmail.com", "helper@1", "Helper 1");
		Helper helper2 = new Helper("helper2@gmail.com", "helper@2", "Helper 2");
		Helper helper3 = new Helper("helper3@gmail.com", "helper@3", "Helper 3");
		
		helperService.addHelper(helper1);
		helperService.addHelper(helper2);
		helperService.addHelper(helper3);
	}
	
	@GetMapping("/helper/login")
	public ResponseEntity<String> helperLogin(@RequestParam("email") String email, @RequestParam("password")String password) {
		return helperService.loginHelper(email, password);
	}

}
