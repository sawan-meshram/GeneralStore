package com.billing.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.billing.database.BillingDatabase;
import com.billing.pojo.Product;
import com.billing.pojo.ProductType;
import com.billing.pojo.ProductUnit;

public class ProductTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Class> annotationClassList = new ArrayList<>();
		annotationClassList.add(Product.class);
		annotationClassList.add(ProductType.class);
		annotationClassList.add(ProductUnit.class);
		
//		BillingDatabase.createSessionFactory(annotationClassList);
//		BillingDatabase.createSession();
		
//		insertProductType(BillingDatabase.getSession());
		
//		BillingDatabase.closeSession();
//		BillingDatabase.closeSessionFactory();		
	}
	
	public static boolean insertProductType(Session session) {
		boolean isInserted = false;
		Transaction transaction = null;
		
		try	{
		
			transaction = session.beginTransaction();   

			ProductType type = new ProductType("Soap");
			
			Set<ProductUnit> productUnits = new HashSet<>();
			ProductUnit u1 = new ProductUnit("Packet", "PAK");
			ProductUnit u2 = new ProductUnit("Dozen", "DZN");
			productUnits.add(u1);
			productUnits.add(u2);
			
			Product p = new Product("Santoor", null, type, null);

			session.save(type);
			session.save(p);
//			session.save(u1);
//			session.save(u2);
			
			
			isInserted = true;
			
			
			transaction.commit();  
			System.out.println("successfully saved");    

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
                transaction.rollback();
            }
		}
		transaction = null;
		return isInserted;
	}

}
