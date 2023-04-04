package com.billing.database.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.billing.database.BillingDatabase;
import com.billing.database.dao.ProductUnitDao;
import com.billing.pojo.ProductUnit;
import com.billing.product.ProductUtil;

public class ProductUnitDaoImpl implements ProductUnitDao {
	private static ProductUnitDaoImpl impl = null;
	
	private ProductUnitDaoImpl() {
		
	}

	public static ProductUnitDaoImpl getInstance() {
		if(impl == null) return new ProductUnitDaoImpl();
		return impl;
	}
	
	@Override
	public boolean insert(Object p) {
		boolean isInserted = false;
		Session session = BillingDatabase.createSession();
		
		try	{

			if(!exist(session, (ProductUnit)p)) {

				session.getTransaction().begin();   
				session.save(p);
				isInserted = true;
				session.getTransaction().commit();
//				System.out.println("successfully saved");  
			}
//			else {
//				U.log("ProductType is already exist.");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			isInserted = false;
		}
		

		BillingDatabase.closeSession(session);
		return isInserted;
	}

	@Override
	public boolean update(Object p) {
		boolean isUpdated = false;
		Transaction transaction = null;
		Session session = BillingDatabase.createSession();

		try	{
	
			transaction = session.beginTransaction();   

			session.update(p); 
         
			isUpdated = true;
			
			transaction.commit();  
//			System.out.println("successfully updated");    

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
                transaction.rollback();
            }
			isUpdated = false;
		}
		transaction = null;
		session.close();

		return isUpdated;
	}

	@Override
	public boolean delete(Object p) {
		boolean isDeleted = false;
		Transaction transaction = null;
		
		Session session = BillingDatabase.createSession();
		try	{
			transaction = session.beginTransaction();   

			session.delete(p); 
         
			isDeleted = true;
			
			transaction.commit();  
//			System.out.println("successfully deleted");    

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
                transaction.rollback();
            }
			isDeleted = false;
		}
		transaction = null;
		session.close();
		return isDeleted;
	}

	@Override
	public boolean exist(Session session, ProductUnit p) {
		session.getTransaction().begin();
		
		boolean isExist = session.createQuery("from ProductUnit where name=:name and abbreviation=:abbreviation")
				.setParameter("name", p.getName())
				.setParameter("abbreviation", p.getAbbreviation())
				.uniqueResult() != null;
		
		session.getTransaction().commit();
		return isExist;

	}

	@Override
	public void init() {
		if(getProductUnits().size() > 0) return;
		
		Transaction transaction = null;
		
		Session session = BillingDatabase.createSession();
		
		try{
			transaction = session.beginTransaction();   

			for(Map.Entry<String, String> entry : ProductUtil.initProductUnit.entrySet()) {
				ProductUnit p1 = new ProductUnit(entry.getKey(), entry.getValue());
				if(!exist(session, p1))
					session.save(p1);
			}
			transaction.commit();  

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
                transaction.rollback();
            }
		}
		transaction = null;
		BillingDatabase.closeSession(session);
	}

	@Override
	public List<ProductUnit> getProductUnits() {
		Session s = BillingDatabase.createSession();
		Query<ProductUnit> q = s.createQuery("from ProductUnit");
		List<ProductUnit> productUnits = q.list();

		BillingDatabase.closeSession(s);
		return productUnits;
	}

}
