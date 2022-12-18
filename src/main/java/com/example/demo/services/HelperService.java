package com.example.demo.services;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Consumer;
import com.example.demo.entity.Helper;
import com.example.demo.repositories.BillRepository;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.repositories.HelperRepository;

@Service
public class HelperService{

	@Autowired(required = true)
	HelperRepository helperRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	BillRepository billRepository;
	
	public void addHelper(Helper helper) {
		helperRepository.save(helper);
	}

	public ResponseEntity<String> loginHelper(String email, String password) {
		if(helperRepository.findById(email).isPresent()) {
			if(helperRepository.findById(email).get().getPassword().equals(password)) {
				return new ResponseEntity("Successful Login", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity("Wrong Password", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<String> addBill(String email, Integer units){
		Double rate = consumerRepository.findById(email).get().getConsumer_type().getRate();
		Double totalAmount = rate * units;
		
		Date date = new Date();
		int month = date.getMonth();
		int year = date.getYear();
		
		Bill bills = null;
		for(Bill b: billRepository.findAll()) {
			if(b.getConsumer().getEmail().equalsIgnoreCase(email)) {
				bills = b;
				break;
			}
		}
		if(bills == null) {
			Bill bill = new Bill(new Consumer(email), new Date(), units, totalAmount);
			billRepository.save(bill);
		}
		else {
			if(bills.getBillDate().getMonth() == month && bills.getBillDate().getYear() == year)
				return new ResponseEntity<String>("Bill already calculated for the particular month of the year.", HttpStatus.BAD_REQUEST);
			else {
				Bill bill = new Bill(new Consumer(email), new Date(), units, totalAmount);
				billRepository.save(bill);
			}
		}
		
		return new ResponseEntity<String>("Bill added successfully", HttpStatus.ACCEPTED);
	}
}
