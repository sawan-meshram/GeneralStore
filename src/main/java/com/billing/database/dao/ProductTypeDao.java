package com.billing.database.dao;

import java.util.List;

import org.hibernate.Session;

import com.billing.pojo.ProductType;

public interface ProductTypeDao extends CRUDOperation{
//	boolean insert(ProductType p);
//	boolean update(ProductType p);
//	boolean delete(ProductType p);
	boolean exist(Session session, ProductType p);
	void init();
	List<ProductType> getProductTypes();
}
