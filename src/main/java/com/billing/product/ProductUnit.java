package com.billing.product;

public class ProductUnit {
	private int id;
	private String unitName;
	private String unitNameAbbr;
	
	public ProductUnit() {
		super();
	}

	public ProductUnit(String unitName, String unitNameAbbr) {
		super();
		this.unitName = unitName;
		this.unitNameAbbr = unitNameAbbr;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitNameAbbr() {
		return unitNameAbbr;
	}

	public void setUnitNameAbbr(String unitNameAbbr) {
		this.unitNameAbbr = unitNameAbbr;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ProductUnit [id=" + id + ", unitName=" + unitName + ", unitNameAbbr=" + unitNameAbbr + "]";
	}

	
}
