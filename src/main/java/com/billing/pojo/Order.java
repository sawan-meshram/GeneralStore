package com.billing.pojo;

import java.time.LocalDateTime;

public class Order {
	private int id;
	private Customer customer;
	private LocalDateTime orderDate;
	private LocalDateTime deliveredDate;
	private LocalDateTime updateDate;
	private PaymentMode paymentMode;
	private double grossAmount;
	private double discountAmount;
	private double gstAmount;
	private double netAmount;
	
	public Order() {
		super();
	}
	
	public Order(Customer customer, LocalDateTime orderDate, LocalDateTime deliveredDate, LocalDateTime updateDate,
			PaymentMode paymentMode, double grossAmount, double discountAmount, double gstAmount, double netAmount) {
		super();
		this.customer = customer;
		this.orderDate = orderDate;
		this.deliveredDate = deliveredDate;
		this.updateDate = updateDate;
		this.paymentMode = paymentMode;
		this.grossAmount = grossAmount;
		this.discountAmount = discountAmount;
		this.gstAmount = gstAmount;
		this.netAmount = netAmount;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDateTime getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(LocalDateTime deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	public PaymentMode getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(double gstAmount) {
		this.gstAmount = gstAmount;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", orderDate=" + orderDate + ", deliveredDate="
				+ deliveredDate + ", updateDate=" + updateDate + ", paymentMode=" + paymentMode + ", grossAmount="
				+ grossAmount + ", discountAmount=" + discountAmount + ", gstAmount=" + gstAmount + ", netAmount="
				+ netAmount + "]";
	}
	
	
	
}

