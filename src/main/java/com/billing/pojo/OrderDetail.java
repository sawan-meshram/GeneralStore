package com.billing.pojo;

public class OrderDetail {
	private int id;
	private int itemNumber;
	private Order order;
	private Product product;
	private double quantity;
	private double sellingPricePerQty;
	private float gstInPercent;
	private float discountInPercent;
	private double total;
	
	
	public OrderDetail() {
		super();
	}


	public OrderDetail(int id, int itemNumber, Order order, Product product, double quantity, double sellingPricePerQty,
			float gstInPercent, float discountInPercent, double total) {
		super();
		this.id = id;
		this.itemNumber = itemNumber;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.sellingPricePerQty = sellingPricePerQty;
		this.gstInPercent = gstInPercent;
		this.discountInPercent = discountInPercent;
		this.total = total;
	}
	
	
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", itemNumber=" + itemNumber + ", order=" + order + ", product=" + product
				+ ", quantity=" + quantity + ", sellingPricePerQty=" + sellingPricePerQty + ", gstInPercent="
				+ gstInPercent + ", discountInPercent=" + discountInPercent + ", total=" + total + "]";
	}
	
	
	
}
