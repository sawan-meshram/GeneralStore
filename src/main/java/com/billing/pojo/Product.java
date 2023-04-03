package com.billing.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true)
	private String name;
	private String aliasName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_type_id", referencedColumnName = "id")
	private ProductType productType;
	
//	@OneToMany(mappedBy="product")
	private Set<ProductUnit> productUnits;
	
	
	public Product() {
		super();
	}

	public Product(String name, String aliasName, ProductType productType, Set<ProductUnit> productUnits) {
		super();
		this.name = name;
		this.aliasName = aliasName;
		this.productType = productType;
		this.productUnits = productUnits;
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
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Set<ProductUnit> getProductUnits() {
		return productUnits;
	}
	public void setProductUnits(Set<ProductUnit> productUnits) {
		this.productUnits = productUnits;
	}
	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", aliasName=" + aliasName + ", productType=" + productType
				+ ", productUnits=" + productUnits + "]";
	}
	
	
	
}
