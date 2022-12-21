package com.example.demo.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Admin;
import com.example.demo.entity.AdminResponse;
import com.example.demo.entity.Area;
import com.example.demo.entity.AreaResponse;
import com.example.demo.entity.Bill;
import com.example.demo.entity.BillResponse;
import com.example.demo.entity.City;
import com.example.demo.entity.CityResponse;
import com.example.demo.entity.Consumer;
import com.example.demo.entity.ConsumerResponse;
import com.example.demo.entity.ConsumerType;
import com.example.demo.entity.ConsumerTypeResponse;
import com.example.demo.entity.Helper;
import com.example.demo.entity.HelperResponse;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.repositories.BillRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.ConsumerRepository;
import com.example.demo.repositories.ConsumerTypeRepository;
import com.example.demo.repositories.HelperRepository;


@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired 
	ConsumerTypeRepository consumerTypeRepository;
	
	@Autowired 
	HelperRepository helperRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	BillRepository billRepository;
	
	public AdminService(ConsumerRepository consumerRepository2, AreaRepository areaRepository2,
			CityRepository cityRepository2, ConsumerTypeRepository consumerTypeRepository2,
			HelperRepository helperRepository2, BillRepository billRepository2, AdminRepository adminRepository2) {
		this.consumerRepository = consumerRepository2;
		this.areaRepository = areaRepository2;
		this.cityRepository = cityRepository2;
		this.consumerTypeRepository = consumerTypeRepository2;
		this.helperRepository = helperRepository2;
		this.billRepository = billRepository2;
		this.adminRepository = adminRepository2;
	}
	
	/********************************Add Functionalities******************************************/
	
	// To add admin in the database 
	public void addAdmin(Admin admin) {
		adminRepository.save(admin);
	}
	
	// To add consumer type in database
	public void addConsumerType(ConsumerType ct) {
		consumerTypeRepository.save(ct);
	}
	
	// To add City in the database
	public ResponseEntity<String> addCity(String city_name) {
		for(City c: cityRepository.findAll()) {
			if(c.getName().equalsIgnoreCase(city_name))
				return new ResponseEntity(new Exception("City with same name already exists!!!").getMessage(), HttpStatus.BAD_REQUEST);
		}
		City city = new City(city_name);
		cityRepository.save(city);
		return new ResponseEntity(new Exception("City Added Successfully!!!").getMessage(), HttpStatus.ACCEPTED);
	}
	
	// To add Area in the database
		public ResponseEntity<String> addArea(String area_name, String city_name) {
			City city = cityRepository.findByCity(city_name);
			
			for(Area area: areaRepository.findAll()) {
				if(area.getAreaName().equalsIgnoreCase(area_name))
					return new ResponseEntity(new Exception("Area with same name already present!!!").getMessage(), HttpStatus.BAD_REQUEST);
			}
			
			Area area = new Area(area_name, city);
			areaRepository.save(area);
			System.out.println("Area Added Successfully!!!");
			return new ResponseEntity(new Exception("Area Added Successfully!!!").getMessage(), HttpStatus.ACCEPTED);

		}

	
	/********************************View Functionalities******************************************/
	
	// To view all Cities
	public List<CityResponse> viewAllCities() {
		List<CityResponse> list = new ArrayList<CityResponse>();
		for(City c : cityRepository.findAll()) {
			list.add(new CityResponse(c.getId(), c.getName()));
		}
		return list;
	}
	
	// To view all Areas
	public List<AreaResponse> viewAllAreas() {
		List<AreaResponse> list = new ArrayList<AreaResponse>();
		for (Area area : areaRepository.findAll()) {
			list.add(new AreaResponse(area.getId(), area.getAreaName()));
		}
		return list;
	}
	
	// To view All Area by City Name
	public ResponseEntity<List<AreaResponse>> viewAreaByCityName(String cityName) {
		List<AreaResponse> areas = new ArrayList<AreaResponse>();
		List<City> cities = cityRepository.findAll();
		City city = null;
		for (City c : cities) {
			if(c.getName().equalsIgnoreCase(cityName)) {
				city = c;
				break;
			}
		}
		if(city == null) {
			return new ResponseEntity(areas, HttpStatus.BAD_REQUEST);
		}
		for (Area area : areaRepository.findAll()) {
			if(area.getCity().getId() == city.getId()) {
				areas.add(new AreaResponse(area.getId(), area.getAreaName()));
			}
		}
		return new ResponseEntity(areas, HttpStatus.ACCEPTED);
	}
	
	// To view all Helpers 
	public List<HelperResponse> viewAllHelpers(){
		List<HelperResponse> list = new ArrayList<>();
		for(Helper h: helperRepository.findAll()) {
			list.add(new HelperResponse(h.getEmail(), h.getName()));
		}
		return list;
	}
	
	// To view All Consumer Types
	public List<ConsumerTypeResponse> viewAllConsumerTypes(){
		List<ConsumerTypeResponse> list = new ArrayList<ConsumerTypeResponse>();
		for(ConsumerType ct: consumerTypeRepository.findAll()) {
			list.add(new ConsumerTypeResponse(ct.getId(), ct.getTypeName(), ct.getRate()));
		}
		System.out.println(list);
		return list;
	}
	
	// To view All Consumers
	public List<ConsumerResponse> viewAllConsumers(){
		List<ConsumerResponse> list = new ArrayList<>();
		for(Consumer c: consumerRepository.findAll()) {
			list.add(new ConsumerResponse(c.getEmail(), c.getName(), c.getArea().getAreaName(), c.getConsumer_type().getTypeName()));
		}
		return list;
	}
	
	// To view All Bills
	public List<BillResponse> viewAllBills() {
		List<BillResponse> list = new ArrayList<>();
		for(Bill bill: billRepository.findAll()) {
			list.add(new BillResponse(bill.getId(), bill.getConsumer().getEmail(), bill.getConsumer().getArea().getCity().getName(), bill.getConsumer().getArea().getAreaName(), bill.getBillDate(), bill.getUnitsConsumed(), bill.getTotalAmount()));
		}
		
		return list;
	}
	
	// To view all the bills based on consumer's email
	public List<BillResponse> viewAllBillsByCid(String email) {
		List<BillResponse> list = new ArrayList<>();
		for(Bill bill: billRepository.findAll()) {
			if(bill.getConsumer().getEmail().equals(email)) {
				list.add(new BillResponse(bill.getId(), bill.getConsumer().getEmail(), bill.getConsumer().getArea().getCity().getName(), bill.getConsumer().getArea().getAreaName(), bill.getBillDate(), bill.getUnitsConsumed(), bill.getTotalAmount()));
			}
		}
		System.out.println();
		return list;
	}
	
	// To view all the bills based on month and year
	public List<BillResponse> viewAllBillsByMnY(String month, int year) {
		List<BillResponse> list = new ArrayList<>();
		for(Bill bill: billRepository.findAll()) {
			if(bill.getBillDate().toLocaleString().contains(month) && bill.getBillDate().toString().contains(String.valueOf(year))) {
				list.add(new BillResponse(bill.getId(), bill.getConsumer().getEmail(), bill.getConsumer().getArea().getCity().getName(), bill.getConsumer().getArea().getAreaName(), bill.getBillDate(), bill.getUnitsConsumed(), bill.getTotalAmount()));
			}
		}
		return list;
	}
	
	// To view all bills based on city
	public List<BillResponse> viewAllBillsByCity(String city) {
		List<BillResponse> list = new ArrayList<>();
		for(Bill bill: billRepository.findAll()) {
			if(bill.getConsumer().getArea().getCity().getName().equalsIgnoreCase(city)) {
				list.add(new BillResponse(bill.getId(), bill.getConsumer().getEmail(), bill.getConsumer().getArea().getCity().getName(), bill.getConsumer().getArea().getAreaName(), bill.getBillDate(), bill.getUnitsConsumed(), bill.getTotalAmount()));
			}
		}
		return list;
	}
	
	// To view all the bills based on area
	public List<BillResponse> viewAllBillsByArea(String area) {
		List<BillResponse> list = new ArrayList<>();
		for(Bill bill: billRepository.findAll()) {
			if(bill.getConsumer().getArea().getAreaName().equalsIgnoreCase(area)) {
				list.add(new BillResponse(bill.getId(), bill.getConsumer().getEmail(), bill.getConsumer().getArea().getCity().getName(), bill.getConsumer().getArea().getAreaName(), bill.getBillDate(), bill.getUnitsConsumed(), bill.getTotalAmount()));
			}
		}
		System.out.println(list);
		return list;
	}
	/********************************Modify Functionalities******************************************/
	
	// to modify city name
	public ResponseEntity<String> modifyCity(String cityName, String newCityName) {
		City city = null;
		for(City c: cityRepository.findAll()) {
			if(c.getName().equalsIgnoreCase(cityName)) {
				city = c;
				break;
			}
		}
		
		if(city == null) {
			return new ResponseEntity("City with the name not found!!! Please enter Correct City Name", HttpStatus.EXPECTATION_FAILED);
		} else {
			city.setName(newCityName);
			cityRepository.save(city);
			return new ResponseEntity("City Updated Successfully!!!", HttpStatus.ACCEPTED);
		}
	}
	
	// to modify area name
	public ResponseEntity<String> modifyAreaName(String areaName, String newAreaName) {
		Area area = null;
		for(Area a: areaRepository.findAll()) {
			if(a.getAreaName().equalsIgnoreCase(areaName)) {
				area = a;
				break;
			}
		}
		
		if(area == null) {
			return new ResponseEntity("Area with the name not found!!! Please enter Correct Area Name", HttpStatus.EXPECTATION_FAILED);
		} else {
			area.setAreaName(newAreaName);
			areaRepository.save(area);
			return new ResponseEntity("Area Name Updated Successfully!!!", HttpStatus.ACCEPTED);
		}
	}
	
	// to modify area's city
	public ResponseEntity<String> modifyAreaByCityName(String areaName, String newCityName) {
		City city = null;
		Area area = null;
		
		for(Area a: areaRepository.findAll()) {
			if(a.getAreaName().equalsIgnoreCase(areaName)) {
				area = a;
				break;
			}
		}
		
		for(City c: cityRepository.findAll()) {
			if(c.getName().equalsIgnoreCase(newCityName)) {
				city = c;
				break;
			}
		}		
		area.setCity(city);
		areaRepository.save(area);
		return new ResponseEntity<String>("City Name Updated for that particular Area Name Successfully", HttpStatus.ACCEPTED);
	}
	
	// To modify rate for a particular consumer type
	public ResponseEntity<String> modifyConsumerTypeRate(String name, double rate){
		
		for (ConsumerType ct : consumerTypeRepository.findAll()) {
			if(ct.getTypeName().equalsIgnoreCase(name)) {
				ct.setRate(rate);
				consumerTypeRepository.save(ct);
				return new ResponseEntity<String>("Consumer Type : " + ct.getTypeName() + "'s rate was changed to " + rate, HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<String>("Consumer Type " + name + " was not found.", HttpStatus.EXPECTATION_FAILED);
	}
	
	/********************************Login Functionalities******************************************/
	
	// To Login Admin
	public ResponseEntity<String> loginAdmin(String email, String password) {
		if(adminRepository.findById(email).isPresent()) {
			if(adminRepository.findById(email).get().getPassword().equals(Base64.getEncoder().encodeToString(password.getBytes()))) {
				return new ResponseEntity("Successful Login", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity("Wrong Password", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}
	}
	
	public Optional<City> getCityById(int city_id) {
		return cityRepository.findById(city_id);
	}
	
	
}
