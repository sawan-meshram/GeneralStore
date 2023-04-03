package com.billing.database.dao;

import java.util.List;

import org.hibernate.Session;

import com.billing.pojo.ProductType;

public interface CRUDOperation {
	boolean insert(Object o);
	boolean update(Object o);
	boolean delete(Object o);
}
