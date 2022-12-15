package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void loginHelper(Helper helper) {
		if(helperRepository.findById(helper.getEmail()) != null) {
			if(helperRepository.findById(helper.getEmail()).get().getPassword().equals(helper.getPassword())) {
				System.out.println("Successful Login");
			} else {
				System.out.println("Wrong Password");
			}
		} else {
			System.out.println("Invalid Credentials");
		}
	}
	
}
