package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Consumer;
import com.example.demo.entity.Helper;
import com.example.demo.repositories.BillRepository;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.repositories.HelperRepository;
import com.example.demo.services.HelperService;

@SpringBootTest
@Configuration
@ContextConfiguration(classes = ElectricityManagementApplication.class)
class HelperControllerTest {

	@Autowired
	HelperService helperService;
	
	@Autowired
	HelperRepository helperRepository;
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	//working perfect
	@Test
	public void addHelper() {
		boolean actualResult1 = false;
		boolean actualResult2 = true;
		
		//already present
		Helper helper2 = new Helper("helper4@gmail.com", "helper@6", "Helper 6");
		
		//adding new
		Helper helper1 = new Helper("helper10@gmail.com", "helper@7", "Helper 7");
		
		long preCount = helperRepository.count();
		
		Helper helper = helperRepository.save(helper2);
		if(helperRepository.count() == preCount)
			actualResult1 = true;
		
		
		Helper help = helperRepository.save(helper1);
		if(helperRepository.existsById("helper10@gmail.com"))
			actualResult2 = false;
		
		
		assertThat(actualResult1).isEqualTo(true);
		assertThat(actualResult2).isEqualTo(false);
		
	}
	
	@Test
	public void helperLogin() {
		boolean actualResult = false;
		boolean actualResult2 = false;
		Helper helper1 = new Helper("helper1@gmail.com", "helper@1", "Helper 1");
		Helper helper2 = new Helper("helper1@gmail.com", "helper@8", "Helper 1");
		
		if(helperRepository.findById(helper1.getEmail()).isPresent() 
				&& helperRepository.findById(helper1.getEmail()).get().getPassword().equals(helper1.getPassword())) {
			actualResult = true;
		}
		
		if(helperRepository.findById(helper2.getEmail()).isPresent() 
				&& helperRepository.findById(helper2.getEmail()).get().getPassword().equals(helper2.getPassword())) {
			actualResult2 = true;
		}
		
		assertThat(actualResult).isEqualTo(true);
		assertThat(actualResult2).isEqualTo(false);
	}
	
	@Test
	public void addBill() {
		boolean actualResult = false;
		String email = "geetika1@gmail.com";
		int units = 25;
		double rate = consumerRepository.findById(email).get().getConsumer_type().getRate();
		double totalAmount = units * rate;
		Consumer c = new Consumer(email);
		
		long preCount = billRepository.count();
		Bill b = new Bill(7, c, new Date(), units, totalAmount);
		
		billRepository.save(b);
		
		if(billRepository.count() != preCount) {
			actualResult = true;
		}
		
		assertEquals(true, actualResult);
		
	}
	
	
	
	
}