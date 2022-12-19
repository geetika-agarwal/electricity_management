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

import com.example.demo.entity.Admin;
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
	
	public void addAdmin(Admin admin) {
		adminRepository.save(admin);
	}
	
	public void addConsumerType(ConsumerType ct) {
		consumerTypeRepository.save(ct);
	}
	
	public ResponseEntity<String> addCity(String city_name) {
		City city = new City(city_name);
		cityRepository.save(city);
		return new ResponseEntity(new Exception("City Added Successfully!!!").getMessage(), HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<String> addArea(String area_name, String city_name) {
		City city = null;
		
		for(City c: cityRepository.findAll()) {
			if(c.getName().equalsIgnoreCase(city_name)) {
				city = c;
				break;
			}
		}
		
		if(city == null) {
			// Exception 
			return new ResponseEntity(new Exception("City Not Found").getMessage(), HttpStatus.BAD_REQUEST);
		}
		else {
			Area area = new Area(area_name, city);
			areaRepository.save(area);
			System.out.println("Area Added Successfully!!!");
			return new ResponseEntity(new Exception("Area Added Successfully!!!").getMessage(), HttpStatus.ACCEPTED);
		}
	}
	
	public List<CityResponse> viewAllCities() {
		List<CityResponse> list = new ArrayList<CityResponse>();
		for(City c : cityRepository.findAll()) {
			list.add(new CityResponse(c.getId(), c.getName()));
		}
		return list;
	}
	
	public List<AreaResponse> viewAllAreas() {
		List<AreaResponse> list = new ArrayList<AreaResponse>();
		for (Area area : areaRepository.findAll()) {
			list.add(new AreaResponse(area.getId(), area.getAreaName()));
		}
		return list;
	}
	
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
		
		if(area == null) {
			return new ResponseEntity<String>("Area with the name not found!!! Please enter Correct Area Name", HttpStatus.EXPECTATION_FAILED);
		}
		
		if(city == null) {
			return new ResponseEntity<String>("City with the name not found!!! Please enter Correct City Name", HttpStatus.EXPECTATION_FAILED);
		}
		
		area.setCity(city);
		areaRepository.save(area);
		return new ResponseEntity<String>("City Name Updated for that particular Area Name Successfully", HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<String> modifyConsumerTypeRate(int id, double rate){
		
		for (ConsumerType ct : consumerTypeRepository.findAll()) {
			if(ct.getId() == id) {
				ct.setRate(rate);
				consumerTypeRepository.save(ct);
				return new ResponseEntity<String>("Consumer Type : " + ct.getTypeName() + "'s rate was changed to " + rate, HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<String>("Consumer Type with " + id + " was not found.", HttpStatus.EXPECTATION_FAILED);
	}
	
	public ResponseEntity<String> addHelper(String email, String password, String name) {
		if(helperRepository.existsById(email)) {
			return new ResponseEntity<String>("Helper with given email already exists.", HttpStatus.EXPECTATION_FAILED);
		}
		else {
			Helper helper = new Helper(email, password, name);
			helperRepository.save(helper);
			return new ResponseEntity<String>("Helper added successfully.", HttpStatus.ACCEPTED);
		}
		
	}
	
	public List<HelperResponse> viewAllHelpers(){
		List<HelperResponse> list = new ArrayList<>();
		for(Helper h: helperRepository.findAll()) {
			list.add(new HelperResponse(h.getEmail(), h.getName()));
		}
		return list;
	}
	
	public List<ConsumerTypeResponse> viewAllConsumerTypes(){
		List<ConsumerTypeResponse> list = new ArrayList<ConsumerTypeResponse>();
		for(ConsumerType ct: consumerTypeRepository.findAll()) {
			list.add(new ConsumerTypeResponse(ct.getId(), ct.getTypeName(), ct.getRate()));
		}
		System.out.println(list);
		return list;
	}
	
	public List<ConsumerResponse> viewAllConsumers(){
		List<ConsumerResponse> list = new ArrayList<>();
		for(Consumer c: consumerRepository.findAll()) {
			list.add(new ConsumerResponse(c.getEmail(), c.getName(), c.getArea().getAreaName(), c.getConsumer_type().getTypeName()));
		}
		return list;
	}
	
	public ResponseEntity<String> removeConsumer(String email){
		if(consumerRepository.findById(email).isPresent()) {
			if((billRepository.findByEmail(new Consumer(email))).size() > 0){
				billRepository.deleteAllByEmail(new Consumer(email));
				consumerRepository.deleteById(email);
				return new ResponseEntity<String>("Consumer Bills & Account Deleted successfully.", HttpStatus.ACCEPTED);
			}else {
				consumerRepository.deleteById(email);
				return new ResponseEntity<String>("Consumer Deleted successfully.", HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<String>("Consumer Not Found.", HttpStatus.BAD_REQUEST);
	}	
	
	public ResponseEntity<String> addConsumer(String email, String name, String area_name, String consumer_type_name, String password) {
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
		return new ResponseEntity<String>("Consumer Registered Successfully!!!", HttpStatus.ACCEPTED);
	}
	
	//View all Bills by passing Consumer Email
		public List<Map<String,String>> viewAllBillsByConsumerId(String email){
			List<Bill> records = new ArrayList<Bill>();
			if(consumerRepository.findById(email).isPresent()) {
				records =  billRepository.findAll().stream()
						.filter(bill -> bill.getConsumer().getEmail().equalsIgnoreCase(email))
						.collect(Collectors.toList());
			}
			List<Map<String, String>> result = new ArrayList<>();
			for (Bill b : records) {
				
				Map<String, String> hm = new LinkedHashMap<>();
				
				hm.put("Bill Id", String.valueOf(b.getId()));
				hm.put("email", email);
				hm.put("billDate", String.valueOf(b.getBillDate()));
				hm.put("UnitsConsumed", String.valueOf(b.getUnitsConsumed()));
				hm.put("TotalAmount", String.valueOf(b.getTotalAmount()));	
				
				result.add(hm);
			}
			
			return result;
		}

	public List<BillResponse> viewAllBills() {
		List<BillResponse> list = new ArrayList<>();
		for(Bill bill: billRepository.findAll()) {
			list.add(new BillResponse(bill.getId(), bill.getConsumer().getEmail(), bill.getConsumer().getArea().getCity().getName(), bill.getConsumer().getArea().getAreaName(), bill.getBillDate(), bill.getUnitsConsumed(), bill.getTotalAmount()));
		}
		
		return list;
	}
}
