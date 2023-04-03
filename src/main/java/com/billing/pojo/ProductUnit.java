package com.billing.pojo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_unit")
public class ProductUnit {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true)
	private String name;
	
	@Column(unique=true)
	private String abbreviation;
	
//	@ManyToOne
//    @JoinColumn(name="product_id", nullable=false)
    private Product product;
	
	
	public ProductUnit() {
		super();
	}

	public ProductUnit(String name, String abbreviation) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public long getId() {
		return id;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ProductUnit other = (ProductUnit) obj;
		return Objects.equals(name, other.name) && Objects.equals(abbreviation, other.abbreviation);
	}
	
	@Override
	public String toString() {
		return "ProductUnit [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + "]";
	}
	
	
}
