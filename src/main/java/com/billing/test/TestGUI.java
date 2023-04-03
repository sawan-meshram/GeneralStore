package com.billing.test;

import java.util.Arrays;
import java.util.List;

import com.billing.database.BillingDatabase;
import com.billing.pojo.ProductType;
import com.billing.view.ItemCalculatorView;
import com.billing.view.ProductDetailTableView;
import com.billing.view.ProductInsertView;
import com.billing.view.ProductTypeView;
import com.billing.view.ProductUnitView;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestGUI  extends Application{
	public static void main(String args[]){ 
		launch(args); 
	}
	
	@Override
	public void start(Stage stage) throws Exception {
//		ProductInsertView productInsertView = new ProductInsertView();
//		productInsertView.getProductInsertView(stage);
		
		
//		ProductDetailTableView productDetailTableView = new ProductDetailTableView();
//		productDetailTableView.getProductDetailTableView(stage);
		
//		ProductUnitView productUnitView = new ProductUnitView();
//		productUnitView.getProductUnitView(stage);
		
		BillingDatabase.createSessionFactory(Arrays.asList(ProductType.class));

		ProductTypeView productTypeView = new ProductTypeView();
		productTypeView.getProductTypeView(stage);
		
//		BillingDatabase.closeSessionFactory();		

//		ItemCalculatorView calc = new ItemCalculatorView();
//		calc.getItemCalculator(stage);
	}
}
