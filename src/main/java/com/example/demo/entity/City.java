package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class City {
	@Id
	@Column(name="city_id")	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany(targetEntity = Area.class, cascade = CascadeType.ALL)
	private Set<Area> areas = new HashSet<Area>(); 
	
	public Set<Area> getAreas() {
		return areas;
	}
	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public City(int id, String name, Set<Area> areas) {
		super();
		this.id = id;
		this.name = name;
		this.areas = areas;
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
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", areas=" + areas + "]";
	}
	
	
}
