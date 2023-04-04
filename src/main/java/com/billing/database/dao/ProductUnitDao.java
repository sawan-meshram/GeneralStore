package com.billing.database.dao;

import java.util.List;

import org.hibernate.Session;
import com.billing.pojo.ProductUnit;

public interface ProductUnitDao extends CRUDOperation{
	boolean exist(Session session, ProductUnit p);
	void init();
	List<ProductUnit> getProductUnits();
}
