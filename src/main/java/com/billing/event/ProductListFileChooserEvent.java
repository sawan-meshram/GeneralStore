package com.billing.event;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.billing.database.ProductTypeDatabase;
import com.billing.pojo.Product;
import com.billing.pojo.ProductType;
import com.billing.util.AlertDialog;
import com.billing.util.FileUtil;
import com.billing.util.U;
import com.billing.view.ProductListFileChooserView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProductListFileChooserEvent implements EventHandler<ActionEvent>{

	private Stage primaryStage;
	private TableView<Product> table;
	
	@Override
	public void handle(ActionEvent event) {

		if(event.getSource() instanceof MenuItem) {
			MenuItem menu = (MenuItem) event.getSource();
			
			if(menu.getText().equals("Load Product List File")) {
				
				FileChooser fileChooser = new FileChooser();
		        fileChooser.setTitle("Open Product File");
		        fileChooser.getExtensionFilters().addAll(
		        	new FileChooser.ExtensionFilter("Csv Files (*.csv)", "*.csv"),
		        	new FileChooser.ExtensionFilter("Tsv Files (*.tsv)", "*.tsv")
		        );
		        
		        
				File file = fileChooser.showOpenDialog(getPrimaryStage());
	            U.log(file);
	            //reading the product list csv file and loaded into table
	            if (file != null) {
	            	List<Product> productList = readProductListFromCsv(file);
	            	
	            	ProductListFileChooserView view =  new ProductListFileChooserView();
	            	view.productListVChooserView(new Stage(), this);
	        		if(productList.size() > 0)
	        			getTable().getItems().addAll(productList);
	            }
			}
			else if(menu.getText().equals("Create Product List File")) {
				
				FileChooser fileChooser = new FileChooser();
		        fileChooser.setTitle("Save Product File");
				File file = fileChooser.showSaveDialog(getPrimaryStage());
	            U.log(file);
	            //creating a product list csv file
	            if (file != null) {
//	            	if(file.exists()) {
//	            		boolean flag = AlertDialog.showConfirmationDialogBox("Replace File", "File is already exists, do you want to replace it with new one?", file.getAbsolutePath());
//	            		if(!flag)return;
//	            	}
	            	U.log(file.getName());
	            	if(file.getName().endsWith(".csv") || file.getName().endsWith(".tsv")) {
	            		List<String[]> writeLines = new ArrayList<>();
		            	writeLines.add(new String[] {"Product_List", "Product_Alias_Name"});
		            	FileUtil.writeCsvFile(writeLines, file);
		            	
		            	writeLines.clear();
		            	writeLines = null;
		            	
		            	AlertDialog.showSuccessTextAlert("Sample Product List Csv File Created", file.getAbsolutePath());
	            	}else {
	            		AlertDialog.showErrorTextAlert("Missing '.csv' or '.tsv' file extension.", file.getAbsolutePath());
	            	}

	            }
			}
		}
		
		
	}
	
	

	private List<Product> readProductListFromCsv(File file){
		List<Product> productList = new ArrayList<>();
		
		List<String[]> readLines = FileUtil.readCsvFileWithoutHeader(file.getAbsolutePath());
		readLines.forEach(vals->{
			productList.add(new Product(vals[0], vals[1], null, null));
		});
		return productList;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}


	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}



	public TableView<Product> getTable() {
		return table;
	}



	public void setTable(TableView<Product> table) {
		this.table = table;
	}

	

}
