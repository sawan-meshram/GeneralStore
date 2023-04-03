package com.billing.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProductDetailTableView{

	private TableView table = new TableView();
	private static final double WIDTH = 900;
	private static final double HEIGHT = 500;
	
	public void getProductDetailTableView(Stage stage) throws Exception {
//		table.setMinHeight(HEIGHT);
//		table.setMinWidth(WIDTH);

		stage.setTitle("Product Details");

		final Label label = new Label("Product Name");
		label.setFont(new Font("Arial", 20));
		
		table.setEditable(false);

		TableColumn itemNumberCol = new TableColumn("Item_Num");
		TableColumn itemNameCol = new TableColumn("Item");
		TableColumn itemTypeCol = new TableColumn("Type");
		TableColumn itemUnitCol = new TableColumn("Unit");
		TableColumn itemQtyCol = new TableColumn("Quantity");
		TableColumn purchasePriceCol = new TableColumn("Purchase_Price");
		TableColumn sellingPriceCol = new TableColumn("Selling_Price");
		TableColumn purchaseDateCol = new TableColumn("Purchase_Date");
		TableColumn gstCol = new TableColumn("GST(%)");
		TableColumn discountAmountCol = new TableColumn("Discount_Amt(%)");

		table.getColumns().addAll(itemNumberCol, itemNameCol, itemTypeCol, itemUnitCol, itemQtyCol, purchasePriceCol, sellingPriceCol, purchaseDateCol, gstCol, discountAmountCol);

		disableSortOnTableColumn(itemNumberCol);
		disableSortOnTableColumn(itemNameCol);
		disableSortOnTableColumn(itemTypeCol);
		disableSortOnTableColumn(itemUnitCol);
		disableSortOnTableColumn(itemQtyCol);
		disableSortOnTableColumn(purchasePriceCol);
		disableSortOnTableColumn(sellingPriceCol);
		disableSortOnTableColumn(purchaseDateCol);
		disableSortOnTableColumn(gstCol);
		disableSortOnTableColumn(discountAmountCol);
		
		itemNumberCol.setMinWidth(80);
		itemNameCol.setMinWidth(400);
		itemTypeCol.setMinWidth(100);
		itemUnitCol.setMinWidth(100);
		itemQtyCol.setMinWidth(150);
		purchasePriceCol.setMinWidth(150);
		sellingPriceCol.setMinWidth(150);
		purchaseDateCol.setMinWidth(150);
		gstCol.setMinWidth(100);
		discountAmountCol.setMinWidth(150);
		
		final HBox hb = new HBox();
//		final Label label = new Label("Address Book");
		final TextField totalQtyField = new TextField();
		setTextFieldPromptTextAndMaxWidth(totalQtyField, "Total Quantity", itemQtyCol.getPrefWidth());

		final TextField avgPurchasePrice = new TextField();
		setTextFieldPromptTextAndMaxWidth(avgPurchasePrice, "Avg. Purchase Price", purchasePriceCol.getPrefWidth());
		
		final TextField avgSellingPrice = new TextField();
		setTextFieldPromptTextAndMaxWidth(avgSellingPrice, "Avg. Selling Price", sellingPriceCol.getPrefWidth());
		
		final TextField emptyLabel = new TextField();
		emptyLabel.setMaxWidth(purchaseDateCol.getPrefWidth());
		
		hb.getChildren().addAll(totalQtyField, avgPurchasePrice, avgSellingPrice, emptyLabel);
        hb.setSpacing(3);
        hb.setAlignment(Pos.CENTER_RIGHT);
		
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(label, table, hb);
	    
		Scene scene = new Scene(vbox);

		stage.setScene(scene);
		stage.show();
	}
	
	private void setTextFieldPromptTextAndMaxWidth(TextField textField, String promptText, double maxWidth) {
		textField.setPromptText(promptText);
		textField.setMaxWidth(maxWidth);
	}
	
	private void disableSortOnTableColumn(TableColumn<?,?> tableColumn) {
		tableColumn.setSortable(false);
	}

}
