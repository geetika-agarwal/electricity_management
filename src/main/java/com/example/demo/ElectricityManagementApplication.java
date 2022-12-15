package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.entity.Helper;
import com.example.demo.repositories.HelperRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class ElectricityManagementApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(ElectricityManagementApplication.class, args);
		System.out.println("Application Started!!!");
		
		
	}

}
