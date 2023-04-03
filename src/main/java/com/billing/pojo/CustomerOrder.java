package com.billing.pojo;

public class CustomerOrder {
	private int id;
	private Customer customer;
	private Order order;
	
	
	public CustomerOrder() {
		super();
	}
	
	public CustomerOrder(Customer customer, Order order) {
		super();
		this.customer = customer;
		this.order = order;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", customer=" + customer + ", order=" + order + "]";
	}
	
	
}
