package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// POJO Class for the Bill table in the database

@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	//Bill id
	
	@ManyToOne
	@JoinColumn(name="consumer")
	private Consumer consumer;
	
	private Date billDate;
	private int unitsConsumed; //units consumed by the consumer
	private double totalAmount;
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(Consumer consumer, Date billDate, int unitsConsumed, double totalAmount) {
		super();
		this.consumer = consumer;
		this.billDate = billDate;
		this.unitsConsumed = unitsConsumed;
		this.totalAmount = totalAmount;
	}
	
	public int getId() {
		return id;
	}
	public Bill(int id, Consumer consumer, Date billDate, int unitsConsumed, double totalAmount) {
		super();
		this.id = id;
		this.consumer = consumer;
		this.billDate = billDate;
		this.unitsConsumed = unitsConsumed;
		this.totalAmount = totalAmount;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public int getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", consumer=" + consumer + ", billDate=" + billDate + ", unitsConsumed="
				+ unitsConsumed + ", totalAmount=" + totalAmount + "]";
	}
	
	
}
