package com.billing.product;

import java.time.LocalDate;

public class Product {
	private int id;
	private String name;
	private String aliasName;
	
	private float quantity;
	private float purchasePricePerQty;
	private float sellingPricePerQty;
	
	private ProductType productType;
	private ProductUnit productUnit;
	
	private LocalDate purchaseDate;

	private boolean allowGst;
	private boolean allowDiscountAmount;

	private float gstInPercentage;
	private float discountAmountInPercentage;
	
	public Product() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getPurchasePricePerQty() {
		return purchasePricePerQty;
	}

	public void setPurchasePricePerQty(float purchasePricePerQty) {
		this.purchasePricePerQty = purchasePricePerQty;
	}

	public float getSellingPricePerQty() {
		return sellingPricePerQty;
	}

	public void setSellingPricePerQty(float sellingPricePerQty) {
		this.sellingPricePerQty = sellingPricePerQty;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public ProductUnit getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(ProductUnit productUnit) {
		this.productUnit = productUnit;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public boolean isAllowGst() {
		return allowGst;
	}

	public void setAllowGst(boolean allowGst) {
		this.allowGst = allowGst;
	}

	public boolean isAllowDiscountAmount() {
		return allowDiscountAmount;
	}

	public void setAllowDiscountAmount(boolean allowDiscountAmount) {
		this.allowDiscountAmount = allowDiscountAmount;
	}

	public float getGstInPercentage() {
		return gstInPercentage;
	}

	public void setGstInPercentage(float gstInPercentage) {
		this.gstInPercentage = gstInPercentage;
	}

	public float getDiscountAmountInPercentage() {
		return discountAmountInPercentage;
	}

	public void setDiscountAmountInPercentage(float discountAmountInPercentage) {
		this.discountAmountInPercentage = discountAmountInPercentage;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", aliasName=" + aliasName + ", quantity=" + quantity
				+ ", purchasePricePerQty=" + purchasePricePerQty + ", sellingPricePerQty=" + sellingPricePerQty
				+ ", productType=" + productType + ", productUnit=" + productUnit + ", purchaseDate=" + purchaseDate
				+ ", allowGst=" + allowGst + ", allowDiscountAmount=" + allowDiscountAmount + ", gstInPercentage="
				+ gstInPercentage + ", discountAmountInPercentage=" + discountAmountInPercentage + "]";
	}
	
	
	
}
