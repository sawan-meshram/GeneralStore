package com.billing.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique=true)
	private String fullName;
	
	private String contactNumber;
	private String address;
	private LocalDateTime insertDate;
	private LocalDateTime updateDate;
	
	
	public Customer() {
		super();
	}

	public Customer(String fullName, String contactNumber, String address, LocalDateTime insertDate, LocalDateTime updateDate) {
		super();
		this.fullName = fullName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fullName=" + fullName + ", contactNumber=" + contactNumber + ", address="
				+ address + ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}
	
	
}
