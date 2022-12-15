package com.example.demo.entity;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.codec.binary.Hex;

@Entity()
public class Consumer {
	@Id
	@Column(name = "consumer_id")
	private String email;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area_id;
	
	@ManyToOne
	@JoinColumn(name = "consumer_type_id")
	private ConsumerType consumer_type_id;
	private String password;
	
	@OneToMany(targetEntity = Bill.class, cascade = CascadeType.ALL)
	private Set<Bill> consumer_bills = new HashSet<Bill>();
	
	
	public Set<Bill> getConsumer_bills() {
		return consumer_bills;
	}
	public void setConsumer_bills(Set<Bill> consumer_bills) {
		this.consumer_bills = consumer_bills;
	}
	public Consumer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Consumer(String email, String name, Area area_id, ConsumerType consumer_type_id, String password) {
		super();
		this.email = email;
		this.name = name;
		this.area_id = area_id;
		this.consumer_type_id = consumer_type_id;
		setPassword(password);
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
	public Area getArea_id() {
		return area_id;
	}
	public void setArea_id(Area area_id) {
		this.area_id = area_id;
	}
	public ConsumerType getConsumer_type_id() {
		return consumer_type_id;
	}
	public void setConsumer_type_id(ConsumerType consumer_type_id) {
		this.consumer_type_id = consumer_type_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
        this.password = Base64.getEncoder().encodeToString(password.getBytes());
	}
	@Override
	public String toString() {
		return "Consumer [email=" + email + ", name=" + name + ", area_id=" + area_id + ", consumer_type_id="
				+ consumer_type_id + ", password=" + password + "]";
	}
}
