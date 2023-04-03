package com.billing.main;

import java.util.ArrayList;
import java.util.List;

import com.billing.database.BillingDatabase;
import com.billing.pojo.ProductType;
import com.billing.view.ShopGUI;

public class BillingSystemMain extends ShopGUI{

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Class> annotationClassList = new ArrayList<>();
		annotationClassList.add(ProductType.class);
		
		BillingDatabase.createSessionFactory(annotationClassList);

		launch(args);
		
		BillingDatabase.closeSessionFactory();		
	}


}
