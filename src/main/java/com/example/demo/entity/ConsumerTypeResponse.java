package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class ConsumerTypeResponse {
	private int id;
	private String typeName;
	private double rate;
	
	public ConsumerTypeResponse() {
		// TODO Auto-generated constructor stub
	}

	public ConsumerTypeResponse(int id, String typeName, double rate) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "ConsumerTypeResponse [id=" + id + ", typeName=" + typeName + ", rate=" + rate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}
