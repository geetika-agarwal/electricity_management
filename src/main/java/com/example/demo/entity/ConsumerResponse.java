package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class ConsumerResponse {

	private String email;
	private String name;
	private String area_name;
	private String consumer_type_name;
	private String password;
	
	public ConsumerResponse(String email, String name, String area_name, String consumer_type_name) {
		super();
		this.email = email;
		this.name = name;
		this.area_name = area_name;
		this.consumer_type_name = consumer_type_name;
	}

	public ConsumerResponse(String email, String name, String area_name, String consumer_type_name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.area_name = area_name;
		this.consumer_type_name = consumer_type_name;
		this.password = password;
	}

	public ConsumerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getConsumer_type_name() {
		return consumer_type_name;
	}

	public void setConsumer_type_name(String consumer_type_name) {
		this.consumer_type_name = consumer_type_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
