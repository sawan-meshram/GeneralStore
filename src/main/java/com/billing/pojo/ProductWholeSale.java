package com.billing.pojo;

public class ProductWholeSale {
	private int id;
	private double sellingPricePerQty;
	private float gstInPercent;
	private float discountInPercent;
	
	public ProductWholeSale() {
		super();
	}

	public ProductWholeSale(double sellingPricePerQty, float gstInPercent, float discountInPercent) {
		super();
		this.sellingPricePerQty = sellingPricePerQty;
		this.gstInPercent = gstInPercent;
		this.discountInPercent = discountInPercent;
	}

	public double getSellingPricePerQty() {
		return sellingPricePerQty;
	}

	public void setSellingPricePerQty(double sellingPricePerQty) {
		this.sellingPricePerQty = sellingPricePerQty;
	}

	public float getGstInPercent() {
		return gstInPercent;
	}

	public void setGstInPercent(float gstInPercent) {
		this.gstInPercent = gstInPercent;
	}

	public float getDiscountInPercent() {
		return discountInPercent;
	}

	public void setDiscountInPercent(float discountInPercent) {
		this.discountInPercent = discountInPercent;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ProductWholeSale [id=" + id + ", sellingPricePerQty=" + sellingPricePerQty + ", gstInPercent="
				+ gstInPercent + ", discountInPercent=" + discountInPercent + "]";
	}
	
	
}
