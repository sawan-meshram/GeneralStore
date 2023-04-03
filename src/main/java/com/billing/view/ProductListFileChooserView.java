package com.billing.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.billing.event.ProductListFileChooserEvent;
import com.billing.event.ProductTypeInsertEvent;
import com.billing.pojo.Product;
import com.billing.util.U;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProductListFileChooserView{

//	private Desktop desktop = Desktop.getDesktop();
	
/*	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JavaFX App");

		
		
        Button button = new Button("Select Product List File");
        Button button2 = new Button("Save Product List File");
        
        
        ProductListFileChooserEvent event = new ProductListFileChooserEvent();
        event.setPrimaryStage(primaryStage);
//        event.setSelectProductListFileButton(button);
        
//        ProductListFileChooserView view = new ProductListFileChooserView();
//        view.getOpenProductListFile(primaryStage, event);
        
        button.setOnAction(event);
        button2.setOnAction(event);
        
        HBox hb = new HBox();
        hb.getChildren().addAll(button, button2);
        
        VBox vBox = new VBox();
        vBox.getChildren().add(hb);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
        
	}*/


	public void productListVChooserView(Stage primaryStage, ProductListFileChooserEvent event) {
		VBox vBox = new VBox();
		vBox.setSpacing(5);
		vBox.setPadding(new Insets(10, 10, 10, 10));
        
		TableView<Product> table = readingCsvFileOnTable(event);
        vBox.getChildren().addAll(table, viewBottomButton(event));
        
        Scene scene = new Scene(vBox, 960, 600);//

		table.prefHeightProperty().bind(primaryStage.heightProperty());
        table.prefWidthProperty().bind(primaryStage.widthProperty());

        primaryStage.setTitle("Product List From CSV File");
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        return vBox;
	}
	
	private TableView<Product> readingCsvFileOnTable(ProductListFileChooserEvent event) {
    	
    	TableView<Product> table = new TableView<>();
    	TableColumn<Product, String> productNameCol = new TableColumn("Product_Name");
    	productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    	TableColumn<Product, String> productAliasNameCol = new TableColumn("Product_Alias_Name");
    	productAliasNameCol.setCellValueFactory(new PropertyValueFactory<>("aliasName"));

    	table.getColumns().addAll( productNameCol, productAliasNameCol);

    	U.disableSortOnTableColumn(productNameCol);
    	U.disableSortOnTableColumn(productAliasNameCol);

    	table.setEditable(false);
    	
    	productNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.50));
    	productAliasNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.50));

    	event.setTable(table);
    	
    	return table;
 	}

	
	private Pane viewBottomButton (ProductListFileChooserEvent event) {
		final HBox hb = new HBox();
		Button btnSave = new Button("Save");
		Button btnUpdate = new Button("Update");
		Button btnDelete = new Button("Delete");
		
		btnSave.setId("Table_Row_Save");
		btnUpdate.setId("Table_Row_Update");
		btnDelete.setId("Table_Row_Delete");
		
		
		btnUpdate.setOnAction(event);
		btnDelete.setOnAction(event);
		btnSave.setOnAction(event);
		
		
		hb.getChildren().addAll(btnDelete, btnUpdate, btnSave);
        hb.setSpacing(3);
        hb.setAlignment(Pos.BASELINE_RIGHT);
        return hb;
	}
}
