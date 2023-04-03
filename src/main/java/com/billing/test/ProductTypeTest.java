package com.billing.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.sqlite.SQLiteException;

import com.billing.database.BillingDatabase;
import com.billing.database.ProductTypeDatabase;
import com.billing.pojo.ProductType;
import com.billing.util.U;


public class ProductTypeTest {

	public static void main(String[] args) {
/*		List<Object> annotationClassList = new ArrayList<>();
		annotationClassList.add(ProductType.class);
		
		BillingDatabase.createSessionFactory(annotationClassList);
		
*/		/*SessionFactory f = new Configuration()
				.configure()
				.addAnnotatedClass(ProductType.class)
				.buildSessionFactory();
*/
		BillingDatabase.createSessionFactory(Arrays.asList(ProductType.class));

		Session s = BillingDatabase.getSessionFactory().getCurrentSession();
//		Session s = f.getCurrentSession();
		U.log(s);
//		Session s1 = BillingDatabase.getSessionFactory().openSession();
//		U.log(s1);
		
		s.beginTransaction();
		Query<ProductType> q = s.createQuery("from ProductType");
		List<ProductType> result = q.list();
		for(ProductType p : result) {
			U.log(p);
		}
		U.log("----------------");
		Criteria cr = s.createCriteria(ProductType.class);
		List<ProductType> results = cr.list();
		for(ProductType p : results) {
			U.log(p);
		}
		U.log("----------------");

		
		ProductType p1 = s.get(ProductType.class, 1L);
		U.log(p1);
		s.getTransaction().commit();
		
		s = BillingDatabase.getSessionFactory().getCurrentSession();
		s.getTransaction().begin();
		
		boolean flag = s //BillingDatabase.getSessionFactory().getCurrentSession()
        .createQuery("from ProductType where name=:name")
        .setParameter("name", "Anything Else")
        .uniqueResult() != null;
		U.log("found :::"+flag);
		s.getTransaction().commit();
		
		
		ProductType p2 = new ProductType("Soap-3");

			try {
				s = BillingDatabase.getSessionFactory().getCurrentSession();

				s.getTransaction().begin();

		long id = (long) s.save(p2);
		U.log("new inserted id ::"+id);
		s.getTransaction().commit();

		}catch(Exception e) {
			s.getTransaction().rollback();
			U.log("Found duplicate -------------"+p2);
		}
		
		s = BillingDatabase.getSessionFactory().getCurrentSession();
		
		s.getTransaction().begin();
		
		List<ProductType> list = s.createQuery("from ProductType where name=:name")
		.setParameter("name", "Anything Else")
		.list();
		U.log(list);
		
		s.getTransaction().commit();
		
		s.close();

		BillingDatabase.closeSessionFactory();		
//		f.close();
		
	}
	public static void main2(String[] args) {
		// TODO Auto-generated method stub

//		List<Object> annotationClassList = new ArrayList<>();
		List<Class> annotationClassList = new ArrayList<>();

		annotationClassList.add(ProductType.class);
		
		BillingDatabase.createSessionFactory(annotationClassList);
		Session s = BillingDatabase.createSession();
		
		
		List<ProductType> productTypeList = ProductTypeDatabase.getAllProductType(ProductTypeDatabase.getProductTypeCriteria(s));
		U.log("Total Product ::"+productTypeList.size());
		
		
//		createInitProductType();
		
		
		Criteria criteria = ProductTypeDatabase.getProductTypeCriteria(s);  
		
/*		List<ProductType> productTypeList = getAllProductType(criteria);
		productTypeList.forEach(System.out::println);
		
		long totalRecords = getCountProductType(criteria);
		U.log("Total Records ::"+totalRecords);
		
		List<String> productTypeNames = getAllProductTypeName(criteria);
		productTypeNames.forEach(System.out::println);
*/		
		
//		ProductType p1 = new ProductType("Dairy / Frozen Food");
		ProductType p2 = new ProductType("Sweets / Dry Fruits"); //"Soap / Powder");
		U.log("p2 ::"+p2);
		U.log("Is exist :"+ProductTypeDatabase.existProductTypeName(criteria, p2));
		
		//for insert
//		U.log("New Inserted :: "+insertProductType(getSession(), p2));
		
		p2 = ProductTypeDatabase.getProductType(s, p2);
		U.log("Product Type from database --> "+p2);
		
	
		//for update
//		p2.setName("Soap / Powder");
//		U.log("New Update :: "+updateProductType(getSession(), p2));
		
		//for delete
//		U.log("For deleted :: "+deleteProductType(getSession(), p2));
		
		BillingDatabase.closeSession(s);
		
		BillingDatabase.closeSessionFactory();		
	}
	
	
}
