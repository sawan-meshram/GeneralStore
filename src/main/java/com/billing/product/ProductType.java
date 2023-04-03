package com.billing.product;

public class ProductType {
	private int id;
	private String typeName;
	
	public ProductType() {
		super();
	}

	public ProductType(String typeName) {
		super();
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ProductType [id=" + id + ", typeName=" + typeName + "]";
	}

	
	
}
