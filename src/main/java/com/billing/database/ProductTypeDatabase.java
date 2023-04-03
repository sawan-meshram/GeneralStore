package com.billing.database;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.billing.pojo.ProductType;
import com.billing.product.ItemType;

public class ProductTypeDatabase {
	
	/**
	 * This method is used to return Criteria for ProductType
	 * @return Criteria of ProductType for current session
	 */
	public static Criteria getProductTypeCriteria(Session session) {
		return session.createCriteria(ProductType.class);  
	}
	
	/**
	 * This method is used to return all ProductType from database
	 * @param criteria Criteria for ProductType
	 * @return list of ProductType
	 */
	public static List<ProductType> getAllProductType(Criteria criteria) {
		return criteria.list(); 
	}
	
	/**
	 * This method is used to return all ProductType Name from database
	 * @param criteria Criteria for ProductType
	 * @return list of product type name
	 */
	public static List<String> getAllProductTypeName(Criteria criteria) {
		return criteria.setProjection(Projections.property("name")).list();
	}
	
	/**
	 * This method is used to return all records count for ProductType from database.
	 * @param uniqueCriteria Criteria for ProductType
	 * @return
	 */
	public static long getCountProductType(Criteria uniqueCriteria) {
		return (long) uniqueCriteria.setProjection(Projections.rowCount()).list().get(0);
	}
	
	/**
	 * This method is used to check if the product type name is exist with our current database.
	 * @param uniqueCriteria Criteria for ProductType
	 * @param pType input ProductType to check its name
	 * @return {@code False} if name is not found<br>{@code True} if found
	 */
	public static boolean existProductTypeName(Criteria uniqueCriteria, ProductType pType) {
	    return uniqueCriteria
	            .add(Restrictions.eq("name", pType.getName()))
	            .uniqueResult() != null;
	}
	
	/**
	 * This method is used to check if the product type name is exist with our current database.
	 * @param session Hibernate Session for database
	 * @param pType input ProductType to check its name
	 * @return {@code False} if name is not found<br>{@code True} if found
	 */
	public static boolean existProductTypeName(Session session, ProductType pType) {
	    return session.createCriteria(ProductType.class)
	            .add(Restrictions.eq("name", pType.getName()))
	            .uniqueResult() != null;
	}
	
	/**
	 * This method is used to get ProductType from database. This method will check if the input ProductType name is match with database ProductType name.
	 * @param uniqueCriteria Criteria for ProductType
	 * @param pType input ProductType to check its name
	 * @return ProductType from database
	 */
	public static ProductType getProductType(Criteria uniqueCriteria, ProductType pType) {
	    return (ProductType) uniqueCriteria
	            .add(Restrictions.eq("name", pType.getName()))
	            .uniqueResult();
	}
	
	/**
	 * This method is used to get ProductType from database. This method will check if the input ProductType name is match with database ProductType name.
	 * @param session
	 * @param pType
	 * @return
	 */
	public static ProductType getProductType(Session session, ProductType pType) {
	    return (ProductType) session.createCriteria(ProductType.class)
	            .add(Restrictions.eq("name", pType.getName()))
	            .uniqueResult();
	}
	
	/**
	 * This method is used to initialised primary product type
	 */
	public static void createInitProductType1() {
		Transaction transaction = null;
		
		try(Session session = BillingDatabase.getSessionFactory().openSession();)
		{
			System.out.println("Session oject created : " + session);
		
			transaction = session.beginTransaction();   

			for(ItemType type : ItemType.values()) {
				
				ProductType p1 = new ProductType(type.getItemType());
				if(!existProductTypeName(session, p1))
					session.save(p1);
			}
			
			transaction.commit();  
			System.out.println("successfully saved");    
			System.out.println("Session is closed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
                transaction.rollback();
            }
		}
	}

	@Deprecated
	/**
	 * This method is used to insert new ProductType into database
	 * @param session
	 * @param p input ProductType
	 * @return {@code True} if new record inserted
	 */
	public static boolean insertProductType(Session session, ProductType p) {
		boolean isInserted = false;
		Transaction transaction = null;
		
		try	{
		
			transaction = session.beginTransaction();   

			if(!existProductTypeName(session, p)) {
				session.save(p);
				isInserted = true;
			}
			
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
	
	@Deprecated
	/**
	 * This method is used to update existing ProductType into database
	 * @param session
	 * @param p existing ProductType
	 * @return {@code True} if record updated
	 */
	public static boolean updateProductType(Session session, ProductType p) {
		boolean isUpdated = false;
		Transaction transaction = null;
		
		try	{
	
			transaction = session.beginTransaction();   

			ProductType p2 = (ProductType)session.get(ProductType.class, p.getId()); 
			p2.setName(p.getName());;
			session.update(p2); 
         
			isUpdated = true;
			
			transaction.commit();  
			System.out.println("successfully updated");    

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
                transaction.rollback();
            }
		}
		transaction = null;
		return isUpdated;
	}
	
	@Deprecated
	/**
	 * This method is used to delete ProductType from database
	 * @param session
	 * @param p existing ProductType
	 * @return {@code True} if record deleted
	 */
	public static boolean deleteProductType(Session session, ProductType p) {
		boolean isDeleted = false;
		Transaction transaction = null;
		
		try	{
			transaction = session.beginTransaction();   

			session.delete(p); 
         
			isDeleted = true;
			
			transaction.commit();  
			System.out.println("successfully deleted");    

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
                transaction.rollback();
            }
		}
		transaction = null;
		return isDeleted;
	}
	
	
}
