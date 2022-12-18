package com.example.demo.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Area;
import com.example.demo.entity.Consumer;
import com.example.demo.entity.ConsumerType;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.repositories.BillRepository;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.repositories.ConsumerTypeRepository;

@Service
public class ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	ConsumerTypeRepository consumerTypeRepository;
	
	@Autowired
	BillRepository billRepository;
	
	public void registration(String email, String name, String area_name, String consumer_type_name, String password) {
		Area area = null; 
		for (Area a : areaRepository.findAll()) {
			if(a.getAreaName().equalsIgnoreCase(area_name))
				area = a;
		}
		ConsumerType ct = null;
		for(ConsumerType cty: consumerTypeRepository.findAll()) {
			if(cty.getTypeName().equalsIgnoreCase(consumer_type_name))
				ct = cty;
		}
		
		Consumer consumer = new Consumer(email, name, area, ct, password, null);
		
		consumerRepository.save(consumer);
	}
	
	public ResponseEntity<String> consumerLogin(Consumer consumer) {
		if(consumerRepository.findById(consumer.getEmail()).isPresent()) {
			if(consumerRepository.findById(consumer.getEmail()).get().getPassword().equals(Base64.getEncoder().encodeToString(consumer.getPassword().getBytes()))) {
				System.out.println("Successful Login");
				return new ResponseEntity<String>("Successful Login", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("Wrong Password", HttpStatus.UNAUTHORIZED);
			}
		}else {
			return new ResponseEntity<String>("Invalid Email/ Password", HttpStatus.UNAUTHORIZED);
		}
	}
	
}
