package com.example.demo.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BillResponse {
	private int bill_id;
	private String email;
	private String city;
	private String area;
	private Date date;
	private int units;
	private double amount;
	
	public BillResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public BillResponse(int bill_id, String email, String city, String area, Date date, int units, double amount) {
		super();
		this.bill_id = bill_id;
		this.email = email;
		this.city = city;
		this.area = area;
		this.date = date;
		this.units = units;
		this.amount = amount;
	}

	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}