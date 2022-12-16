package com.example.demo.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Helper;
import com.example.demo.repositories.HelperRepository;

@Service
public class HelperService{

	@Autowired(required = true)
	HelperRepository helperRepository;
	
	public void addHelper(Helper helper) {
		helperRepository.save(helper);
	}

	public ResponseEntity<String> loginHelper(String email, String password) {
		if(helperRepository.findById(email).isPresent()) {
			if(helperRepository.findById(email).get().getPassword().equals(Base64.getEncoder().encodeToString(password.getBytes()))) {
				return new ResponseEntity("Successful Login", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity("Wrong Password", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}
	}
	
}
