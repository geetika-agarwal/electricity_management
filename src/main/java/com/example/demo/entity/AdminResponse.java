package com.example.demo.entity;

import org.springframework.stereotype.Component;


// POJO CLass for Admin Responses sent to Angular
@Component
public class AdminResponse {
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
	private String email;
	private String name;
	public AdminResponse() {
		// TODO Auto-generated constructor stub
	}
	public AdminResponse(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
}