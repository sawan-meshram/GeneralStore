package com.billing.database.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.billing.database.BillingDatabase;
import com.billing.database.dao.ProductTypeDao;
import com.billing.pojo.ProductType;
import com.billing.util.U;

public class ProductTypeDaoImpl implements ProductTypeDao{
	private static ProductTypeDaoImpl impl = null;
	
	private ProductTypeDaoImpl() { }
	
	public static ProductTypeDaoImpl getInstance() {
		if(impl == null) return new ProductTypeDaoImpl();
		return impl;
	}

	@Override
	public boolean insert(Object p) {
		boolean isInserted = false;
		Session session = BillingDatabase.createSession();
		
		try	{

			if(!exist(session, (ProductType)p)) {

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
//			ProductType p2 = (ProductType)session.get(ProductType.class, p.getId()); 
//			p2.setName(p.getName());;
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
	public void init() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public List<ProductType> getProductTypes() {
		Session s = BillingDatabase.createSession();
		Query<ProductType> q = s.createQuery("from ProductType");
		List<ProductType> productTypes = q.list();

		BillingDatabase.closeSession(s);
		return productTypes;
	}

//	@Override
//	public boolean exist(ProductType p) {
//		Session session = BillingDatabase.createSession();
//		
//		session.getTransaction().begin();
//		
//		boolean isExist = session.createQuery("from ProductType where name=:name")
//				.setParameter("name", p.getName()).uniqueResult() != null;
//		
//		session.getTransaction().commit();
//		BillingDatabase.closeSession(session);
//
//		
//		return isExist;
//	}
	
	@Override
	public boolean exist(Session session, ProductType p) {
		
		session.getTransaction().begin();
		
		boolean isExist = session.createQuery("from ProductType where name=:name")
				.setParameter("name", p.getName())
				.uniqueResult() != null;
		
		session.getTransaction().commit();
		return isExist;
	}


}
