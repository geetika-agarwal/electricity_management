package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Consumer;
import com.example.demo.repositories.ConsumerRepository;

@RestController
public class ConsumerController {
	
	@Autowired(required = true)
	ConsumerRepository consumerRepository;
	
	@GetMapping("/login")
	public void helperLogin(@RequestBody Consumer consumer) {
		if(consumerRepository.findById(consumer.getEmail()) != null) {
			if(consumerRepository.findById(consumer.getEmail()).get().getPassword().equals(consumer.getPassword())) {
				System.out.println("Successful Login");
			} else {
				System.out.println("Wrong Password");
			}
		}else {
			System.out.println("Invalid Credentials For Consumer!!!");
		}
	}

	
}
