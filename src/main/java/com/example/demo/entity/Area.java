package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="area")
public class Area {
	@Id
	@Column(name="area_id")
	private int id;
	private String areaName;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City cityId;
	
	@OneToMany(targetEntity = Consumer.class, cascade = CascadeType.ALL)
	private Set<Consumer> consumer_areas = new HashSet<Consumer>();
	
	public Set<Consumer> getConsumer_areas() {
		return consumer_areas;
	}
	public void setConsumer_areas(Set<Consumer> consumer_areas) {
		this.consumer_areas = consumer_areas;
	}
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Area(int id, String areaName, City cityId) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.cityId = cityId;
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
	public City getCityId() {
		return cityId;
	}
	public void setCityId(City cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "Area [id=" + id + ", areaName=" + areaName + ", cityId=" + cityId + "]";
	}
	
		
}
