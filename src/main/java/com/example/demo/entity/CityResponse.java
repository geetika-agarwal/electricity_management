package com.example.demo.entity;

import org.springframework.stereotype.Component;

// City Class to send as a response to Angular

@Component
public class CityResponse {
	private int id;
	private String name;
	public CityResponse(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CityResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
