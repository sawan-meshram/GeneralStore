package com.billing.pojo;

public class ProductSelling {
	private Product product;
	private double quantity;
	private ProductWholeSale productWholeSale;
	private ProductRetail productRetail;
	
	
	public ProductSelling() {
		super();
	}


	public ProductSelling(Product product) {
		super();
		this.product = product;
	}

	public ProductSelling(Product product, double quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public ProductSelling(Product product, double quantity, ProductWholeSale productWholeSale,
			ProductRetail productRetail) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.productWholeSale = productWholeSale;
		this.productRetail = productRetail;
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


	public ProductWholeSale getProductWholeSale() {
		return productWholeSale;
	}


	public void setProductWholeSale(ProductWholeSale productWholeSale) {
		this.productWholeSale = productWholeSale;
	}


	public ProductRetail getProductRetail() {
		return productRetail;
	}


	public void setProductRetail(ProductRetail productRetail) {
		this.productRetail = productRetail;
	}


	@Override
	public String toString() {
		return "ProductSelling [product=" + product + ", quantity=" + quantity + ", productWholeSale="
				+ productWholeSale + ", productRetail=" + productRetail + "]";
	}

	
}
