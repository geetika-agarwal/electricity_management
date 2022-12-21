package com.example.demo;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.City;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.repositories.BillRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.repositories.ConsumerTypeRepository;
import com.example.demo.repositories.HelperRepository;
import com.example.demo.services.AdminService;


@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	@Mock private ConsumerRepository consumerRepository;
	
	@Mock private AreaRepository areaRepository;
	
	@Mock private CityRepository cityRepository;
	
	@Mock private ConsumerTypeRepository consumerTypeRepository;
	
	@Mock private HelperRepository helperRepository;
	
	@Mock private BillRepository billRepository;
	
	@Mock private AdminRepository adminRepository;
	
	
	private AdminService adminService;
	
	@BeforeEach void setup() {
		this.adminService = new AdminService(this.consumerRepository, this.areaRepository, 
				this.cityRepository, this.consumerTypeRepository, 
				this.helperRepository, this.billRepository, this.adminRepository);
	}
	
	
	@Test void getAllConsumers() {
		adminService.viewAllConsumers();
		verify(consumerRepository).findAll();
	}
	
	@Test void getAllAreas() {
		adminService.viewAllAreas();
		verify(areaRepository).findAll();
	}
	
	@Test void getAllCities() {
		adminService.viewAllCities();
		verify(cityRepository).findAll();
	}
	
	@Test void getAllConsumerTypes() {
		adminService.viewAllConsumerTypes();
		verify(consumerTypeRepository).findAll();
	}
	
	@Test void getAllHelpers() {
		adminService.viewAllHelpers();
		verify(helperRepository).findAll();
	}
	
	@Test void getAllBills() {
		adminService.viewAllBills();
		verify(billRepository).findAll();
	}
}