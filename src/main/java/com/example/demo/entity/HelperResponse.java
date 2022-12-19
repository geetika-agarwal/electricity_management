package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class HelperResponse {
	private String email;
	private String name;
	public HelperResponse() {
		// TODO Auto-generated constructor stub
	}
	public HelperResponse(String email, String name) {
		super();
		this.email = email;
		this.name = name;
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
	
	
}
