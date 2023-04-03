package com.billing.product;

public class Item {
	private String itemName = null;
	private String itemType = null;

	private int srNo = 0;
	private float quantity = 0;
	private float itemDiscount = 0;
	private float cGstPrice = 0;
	private float cGstPercent = 0;
	private float sGstPrice = 0;
	private float sGstPercent = 0;

	private double itemPrice = 0;
	private double totalItemPrice = 0;
	private double itemTotal = 0;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getItemDiscount() {
		return itemDiscount;
	}
	public void setItemDiscount(float itemDiscount) {
		this.itemDiscount = itemDiscount;
	}
	public float getcGstPrice() {
		return cGstPrice;
	}
	public void setcGstPrice(float cGstPrice) {
		this.cGstPrice = cGstPrice;
	}
	public float getcGstPercent() {
		return cGstPercent;
	}
	public void setcGstPercent(float cGstPercent) {
		this.cGstPercent = cGstPercent;
	}
	public float getsGstPrice() {
		return sGstPrice;
	}
	public void setsGstPrice(float sGstPrice) {
		this.sGstPrice = sGstPrice;
	}
	public float getsGstPercent() {
		return sGstPercent;
	}
	public void setsGstPercent(float sGstPercent) {
		this.sGstPercent = sGstPercent;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public double getTotalItemPrice() {
		return totalItemPrice;
	}
	public void setTotalItemPrice(double totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}
	public double getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}
}
