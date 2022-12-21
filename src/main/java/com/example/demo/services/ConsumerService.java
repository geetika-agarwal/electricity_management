package com.example.demo.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Area;
import com.example.demo.entity.Bill;
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
	
	public ConsumerService(ConsumerRepository consumerRepository2, AreaRepository areaRepository2,
			ConsumerTypeRepository consumerTypeRepository2) {
		this.consumerRepository = consumerRepository2;
		this.areaRepository = areaRepository2;
		this.consumerTypeRepository = consumerTypeRepository2;
	}
	
	// Register Consumer
	public ResponseEntity<String> registration(String email, String name, String area_name, String consumer_type_name, String password) {
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
		if(consumerRepository.existsById(email)) {
			return new ResponseEntity<String>("Consumer With same email Already present", HttpStatus.BAD_REQUEST);
		}
		Consumer consumer = new Consumer(email, name, area, ct, password, null);
		consumerRepository.save(consumer);
		return new ResponseEntity<String>("Successful Registration!!!", HttpStatus.ACCEPTED);
	}
	
	// Login Consumer
	public ResponseEntity<String> consumerLogin(String email, String password) {
		if(consumerRepository.findById(email).isPresent()) {
			if(consumerRepository.findById(email).get().getPassword().equals(Base64.getEncoder().encodeToString(password.getBytes()))) {
				System.out.println("Successful Login");
				return new ResponseEntity<String>("Successful Login", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("Wrong Password", HttpStatus.UNAUTHORIZED);
			}
		}else {
			return new ResponseEntity<String>("Invalid Email/ Password", HttpStatus.UNAUTHORIZED);
		}
	}
	
	// Delete Consumer
	public ResponseEntity<String> removeAccount(String email){
		if(consumerRepository.findById(email).isPresent()) {
			for(Bill bill: billRepository.findAll()) {
				if(bill.getConsumer().getEmail().equalsIgnoreCase(email)) {
					billRepository.deleteById(bill.getId());
				}
			}
			consumerRepository.deleteById(email);
			return new ResponseEntity<String>("Your Bills & Account Deleted successfully.", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("You are not logged in. Log In First", HttpStatus.BAD_REQUEST);
	}
	
	// Update Consumer Name
	public ResponseEntity<String> updateName(String email, String name){
		for (Consumer c : consumerRepository.findAll()) {
			if(c.getEmail().equals(email)) {
				c.setName(name);
				consumerRepository.save(c);
				return new ResponseEntity<String>("Consumer Name was changed." , HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<String>("You are not logged in. Please Log In.", HttpStatus.NOT_FOUND);
	}
	
	// Update Consumer Password Using Email
	public ResponseEntity<String> updatePassword(String email, String password){
		for (Consumer c : consumerRepository.findAll()) {
			if(c.getEmail().equals(email)) {
				c.setPassword(password);
				consumerRepository.save(c);
				return new ResponseEntity<String>("Consumer Password was changed." , HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<String>("You are not logged in. Please Log In.", HttpStatus.NOT_FOUND);
	}
	
}
