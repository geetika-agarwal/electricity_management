package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class AreaResponse {
	private int id;
	private String areaName;
	public AreaResponse(int id, String areaName) {
		super();
		this.id = id;
		this.areaName = areaName;
	}
	public AreaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
