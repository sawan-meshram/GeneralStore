package com.billing.pojo;

import java.time.LocalDateTime;

public class ProductPurchaseDetail {
	private int id;
	private Product product;
	private LocalDateTime purchaseDate;
	private double purchasePricePerQty;
	private double quantity;
	private float gstInPercent; 
	private double totalPurchasePrice;
	
	
	public ProductPurchaseDetail() {
		super();
	}


	public ProductPurchaseDetail(int id, Product product, LocalDateTime purchaseDate, double purchasePricePerQty,
			double quantity, float gstInPercent, double totalPurchasePrice) {
		super();
		this.id = id;
		this.product = product;
		this.purchaseDate = purchaseDate;
		this.purchasePricePerQty = purchasePricePerQty;
		this.quantity = quantity;
		this.gstInPercent = gstInPercent;
		this.totalPurchasePrice = totalPurchasePrice;
	}
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public double getPurchasePricePerQty() {
		return purchasePricePerQty;
	}
	public void setPurchasePricePerQty(double purchasePricePerQty) {
		this.purchasePricePerQty = purchasePricePerQty;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public float getGstInPercent() {
		return gstInPercent;
	}
	public void setGstInPercent(float gstInPercent) {
		this.gstInPercent = gstInPercent;
	}
	public double getTotalPurchasePrice() {
		return totalPurchasePrice;
	}
	public void setTotalPurchasePrice(double totalPurchasePrice) {
		this.totalPurchasePrice = totalPurchasePrice;
	}
	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "ProductPurchaseDetail [id=" + id + ", product=" + product + ", purchaseDate=" + purchaseDate
				+ ", purchasePricePerQty=" + purchasePricePerQty + ", quantity=" + quantity + ", gstInPercent="
				+ gstInPercent + ", totalPurchasePrice=" + totalPurchasePrice + "]";
	}
	
	
	
}
