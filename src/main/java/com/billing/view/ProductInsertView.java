package com.billing.view;

import java.awt.Event;
import java.time.LocalDate;
import java.util.Optional;

import com.billing.event.ItemUnitListViewEvent;
import com.billing.event.ProductInsertEvent;
import com.billing.product.ItemType;
import com.billing.product.ItemUnit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ProductInsertView{
	
	
	public void getProductInsertView(Stage stage) {
		stage.setTitle("Item New Entry");
        
		Scene scene = new Scene(viewItemEntry(stage), 800, 750);

		stage.setScene(scene);
		
		stage.show();
	}
	
	private Pane viewItemEntry(Stage stage) {
		GridPane grid = new GridPane();
		//Setting size for the pane 
		grid.setMinSize(800, 500); 
	      
//		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		/**
		 * Item Entry Form
		 */
		Text sceneTitle = new Text("Item Entry Form");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

		
		Label _itemType = new Label("Item Type");
		Text _itemTypeCompulsory = new Text(" *");
		_itemTypeCompulsory.setFill(Color.RED);

		TextFlow itemType = new TextFlow();
		itemType.getChildren().addAll(_itemType, _itemTypeCompulsory);
		
		
		//Choice box for item type 
		ChoiceBox<ItemType> itemTypeChoiceBox = new ChoiceBox(); 
		itemTypeChoiceBox
			.getItems()
			.addAll(ItemType.values()); 

		Label _purchaseDate = new Label("Purchase Date");
		Text _purchaseDateCompulsory = new Text(" *");
		_purchaseDateCompulsory.setFill(Color.RED);

		TextFlow purchaseDate = new TextFlow();
		purchaseDate.getChildren().addAll(_purchaseDate, _purchaseDateCompulsory);
		
		
		//date picker to choose date
		DatePicker purchaseDatePicker = new DatePicker();
		purchaseDatePicker.setValue(LocalDate.now());

		
		Label _itemName = new Label("Item Name");
		Text _itemNameCompulsory = new Text(" *");
		_itemNameCompulsory.setFill(Color.RED);

		TextFlow itemName = new TextFlow();
		itemName.getChildren().addAll(_itemName, _itemNameCompulsory);
		
		TextField itemNameTextField = new TextField();

		Label _alternateItemName = new Label("Alternate Item Name");
		Text _alternateItemNameCompulsory = new Text(" (Optional)");
		_alternateItemNameCompulsory.setFill(Color.RED);
		_alternateItemNameCompulsory.setFont(Font.font("Verdana", FontPosture.ITALIC, 8));

		TextFlow alternateItemName = new TextFlow();
		alternateItemName.getChildren().addAll(_alternateItemName, _alternateItemNameCompulsory);
		
		
		TextField alternateItemNameTextField = new TextField();
		
		Label _itemUnit = new Label("Item Unit");
		Text _itemUnitCompulsory = new Text(" *");
		_itemUnitCompulsory.setFill(Color.RED);

		TextFlow itemUnit = new TextFlow();
		itemUnit.getChildren().addAll(_itemUnit, _itemUnitCompulsory);

		
		//Choice box for item type 
		ChoiceBox<ItemUnit> itemUnitChoiceBox = new ChoiceBox(); 
		itemUnitChoiceBox
			.getItems()
			.addAll(ItemUnit.values()); 
		
		
		Button allowMultipleItemUtilButton = new Button("Multiple Item Unit");
		
		
		
		Label purchaseQty = new Label("Purchase Quantity");
		TextField purchaseQtyTextField = new TextField();
		
		Label purchasePrice = new Label("Purchase Price / Qty");
		TextField purchasePriceTextField = new TextField();
		
		
		/*
		 * Wholesale
		 */
		Label wholesaleRatesLabel = new Label("Wholesale");
		wholesaleRatesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		
//		Label wholesalePurchasePrice = new Label("Purchase Price / Qty");
//		TextField wholesalePurchasePriceTextField = new TextField();
		
		Label wholesaleSellingPrice = new Label("Selling Price / Qty");
		TextField wholesaleSellingPriceTextField = new TextField();
		
		/*
		 * Retail
		 */
		Label retailRatesLabel = new Label("Retail");
		retailRatesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		
//		Label retailPurchasePrice = new Label("Purchase Price / Qty");
//		TextField retailPurchasePriceTextField = new TextField();
		
		Label retailSellingPrice = new Label("Selling Price / Qty");
		TextField retailSellingPriceTextField = new TextField();
		
		
		/*
		 * additional setting for wholesale
		 */
		Label wholesaleAdditionalSetting = new Label("Additional Setting");
		wholesaleAdditionalSetting.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		
		//check box for allow GST 
		Label wholesaleAllowGST = new Label("Allow GST");
		CheckBox wholesaleAllowGSTCheckBox = new CheckBox(); 
		wholesaleAllowGSTCheckBox.setSelected(true); 

		//check box for allow discount amount
		Label wholesaleAllowDiscountAmount = new Label("Allow Discount Amount");
		CheckBox wholesaleAllowDiscountAmountCheckBox = new CheckBox(); 
		wholesaleAllowDiscountAmountCheckBox.setSelected(true); 
		
		//GST
		Label wholesaleIntraStateMovementGSTInPrecent = new Label("GST(%)");
		TextField wholesaleIntraStateMovementGSTInPrecentTextField = new TextField();
		
		//Discount Amount
		Label wholesaleDiscountAmountInPrecent = new Label("Discount Amount(%)");
		TextField wholesaleDiscountAmountInPrecentTextField = new TextField();
		
		wholesaleAllowGSTCheckBox.setOnAction(event->{
			if(wholesaleAllowGSTCheckBox.isSelected()) {
				wholesaleIntraStateMovementGSTInPrecentTextField.setEditable(true);
				wholesaleIntraStateMovementGSTInPrecentTextField.setStyle("-fx-opacity:1");
			}else {
				wholesaleIntraStateMovementGSTInPrecentTextField.setEditable(false);
				wholesaleIntraStateMovementGSTInPrecentTextField.setStyle("-fx-opacity:0.5");
			}
		});
		
		wholesaleAllowDiscountAmountCheckBox.setOnAction(event->{
			if(wholesaleAllowDiscountAmountCheckBox.isSelected()) {
				wholesaleDiscountAmountInPrecentTextField.setEditable(true);
				wholesaleDiscountAmountInPrecentTextField.setStyle("-fx-opacity:1");
			}else {
				wholesaleDiscountAmountInPrecentTextField.setEditable(false);
				wholesaleDiscountAmountInPrecentTextField.setStyle("-fx-opacity:0.5");
			}
		});
		
		/*
		 * additional setting for retail
		 */
		Label retailAdditionalSetting = new Label("Additional Setting");
		retailAdditionalSetting.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		
		//check box for allow GST 
		Label retailAllowGST = new Label("Allow GST");
		CheckBox retailAllowGSTCheckBox = new CheckBox(); 
		retailAllowGSTCheckBox.setSelected(true); 

		//check box for allow discount amount
		Label retailAllowDiscountAmount = new Label("Allow Discount Amount");
		CheckBox retailAllowDiscountAmountCheckBox = new CheckBox(); 
		retailAllowDiscountAmountCheckBox.setSelected(true); 
		
		//GST
		Label retailIntraStateMovementGSTInPrecent = new Label("GST(%)");
		TextField retailIntraStateMovementGSTInPrecentTextField = new TextField();
		
		//Discount Amount
		Label retailDiscountAmountInPrecent = new Label("Discount Amount(%)");
		TextField retailDiscountAmountInPrecentTextField = new TextField();
		
		retailAllowGSTCheckBox.setOnAction(event->{
			if(retailAllowGSTCheckBox.isSelected()) {
				retailIntraStateMovementGSTInPrecentTextField.setEditable(true);
				retailIntraStateMovementGSTInPrecentTextField.setStyle("-fx-opacity:1");
			}else {
				retailIntraStateMovementGSTInPrecentTextField.setEditable(false);
				retailIntraStateMovementGSTInPrecentTextField.setStyle("-fx-opacity:0.5");
			}
		});
		
		retailAllowDiscountAmountCheckBox.setOnAction(event->{
			if(retailAllowDiscountAmountCheckBox.isSelected()) {
				retailDiscountAmountInPrecentTextField.setEditable(true);
				retailDiscountAmountInPrecentTextField.setStyle("-fx-opacity:1");
			}else {
				retailDiscountAmountInPrecentTextField.setEditable(false);
				retailDiscountAmountInPrecentTextField.setStyle("-fx-opacity:0.5");
			}
		});
		
		
		

		//check box for Ask Before Submit 
		CheckBox askBeforeSubmitCheckBox = new CheckBox("Ask Before Submit"); 
		askBeforeSubmitCheckBox.setSelected(true); 

		
		Button btnSubmit = new Button("Submit");
		Button btnClear = new Button("Clear");
		
		Label statusItemAdder = new Label();
		
		/**
		 * Setting GridPane
		 */
	    //set title
		grid.add(sceneTitle, 0, 0, 5, 1);

		//set Horizontal
		final Separator separatorHorizontalTop = new Separator();
		separatorHorizontalTop.setValignment(VPos.CENTER);
		grid.add(separatorHorizontalTop, 0, 1, 5, 1);
		

		//set purchase date
		grid.add(purchaseDate, 0, 3);
		grid.add(purchaseDatePicker, 1, 3);

		//set item type
		grid.add(itemType, 0, 4);
		itemTypeChoiceBox.setMaxWidth(Double.MAX_VALUE);
		grid.add(itemTypeChoiceBox, 1, 4, 4, 1);
		

		//set item name
		grid.add(itemName, 0, 5);
		grid.add(itemNameTextField, 1, 5, 4, 1);
		
		//set item alternate name
		grid.add(alternateItemName, 0, 6);
		grid.add(alternateItemNameTextField, 1, 6, 4, 1);
		
		//set item unit
		grid.add(itemUnit, 0, 7);
		itemUnitChoiceBox.setMaxWidth(Double.MAX_VALUE);
		grid.add(itemUnitChoiceBox, 1, 7);
		grid.add(allowMultipleItemUtilButton, 3, 7);
		
		
		//set purchase qty
		grid.add(purchaseQty, 0, 8);
		grid.add(purchaseQtyTextField, 1, 8);
		
		grid.add(purchasePrice, 0, 9);
		grid.add(purchasePriceTextField, 1, 9);
		
		/**
		 * Additional Setting
		 */
		final Separator separatorHorizontalMiddle = new Separator();
		separatorHorizontalMiddle.setValignment(VPos.CENTER);

		final Separator separatorVerticalMiddle = new Separator();
		separatorVerticalMiddle.setOrientation(Orientation.VERTICAL);
		
		//set Horizontal
		grid.add(separatorHorizontalMiddle, 0, 11, 5, 1);
		
		grid.add(separatorVerticalMiddle, 2, 12, 1, 12);
		
		
		grid.add(wholesaleRatesLabel, 0, 13);
		grid.add(retailRatesLabel, 3, 13);
		
//		grid.add(wholesalePurchasePrice, 0, 13);
//		grid.add(wholesalePurchasePriceTextField, 1, 13);
//		
//		grid.add(retailPurchasePrice, 3, 13);
//		grid.add(retailPurchasePriceTextField, 4, 13);

		
		grid.add(wholesaleSellingPrice, 0, 14);
		grid.add(wholesaleSellingPriceTextField, 1, 14);
		
		grid.add(retailSellingPrice, 3, 14);
		grid.add(retailSellingPriceTextField, 4, 14);
		
	
//		final Separator separatorHorizontalMiddle1 = new Separator();
//		separatorHorizontalMiddle1.setValignment(VPos.CENTER);
//		//set Horizontal
//		grid.add(separatorHorizontalMiddle1, 0, 13, 2, 1);
		
		grid.add(wholesaleAdditionalSetting, 0, 16);
		grid.add(retailAdditionalSetting, 3, 16);
		
		grid.add(wholesaleAllowGST, 0, 18);
		grid.add(wholesaleAllowGSTCheckBox, 1, 18);
		
		grid.add(retailAllowGST, 3, 18);
		grid.add(retailAllowGSTCheckBox, 4, 18);

//		GridPane.setHalignment(wholesaleAllowGSTCheckBox, HPos.CENTER);
//		GridPane.setHalignment(retailAllowGSTCheckBox, HPos.CENTER);
		
		
		grid.add(wholesaleAllowDiscountAmount, 0, 19);
		grid.add(wholesaleAllowDiscountAmountCheckBox, 1, 19);
		
		grid.add(retailAllowDiscountAmount, 3, 19);
		grid.add(retailAllowDiscountAmountCheckBox, 4, 19);
		
//		GridPane.setHalignment(wholesaleAllowDiscountAmountCheckBox, HPos.CENTER);
//		GridPane.setHalignment(retailAllowDiscountAmountCheckBox, HPos.CENTER);
		
		//set selling price/qty 
		grid.add(wholesaleIntraStateMovementGSTInPrecent, 0, 20);
		grid.add(wholesaleIntraStateMovementGSTInPrecentTextField, 1, 20);
		
		//set selling price/qty 
		grid.add(retailIntraStateMovementGSTInPrecent, 3, 20);
		grid.add(retailIntraStateMovementGSTInPrecentTextField, 4, 20);
				

		//set selling price/qty 
		grid.add(wholesaleDiscountAmountInPrecent, 0, 21);
		grid.add(wholesaleDiscountAmountInPrecentTextField, 1, 21);

		//set selling price/qty 
		grid.add(retailDiscountAmountInPrecent, 3, 21);
		grid.add(retailDiscountAmountInPrecentTextField, 4, 21);
		
		
		grid.add(askBeforeSubmitCheckBox, 0, 24);

		//Set HBox for buttons
		HBox hboxBtn = new HBox(10);
		hboxBtn.setAlignment(Pos.BOTTOM_CENTER);
		hboxBtn.getChildren().addAll(btnClear, btnSubmit);

		//add buttons
		grid.add(hboxBtn, 1, 26, 3, 1);

		grid.add(statusItemAdder, 0, 28, 2, 1);
		
		ItemUnitListViewEvent itemUnitListViewEvent = new ItemUnitListViewEvent(itemUnitChoiceBox);
		
		allowMultipleItemUtilButton.setOnAction(e->{
			ItemUnitListView itemUnitListView = new ItemUnitListView();
			itemUnitListView.getItemUnitListView(new Stage(), itemUnitListViewEvent);
		});
		
		//set events on submit button
		ProductInsertEvent itemAdderEvent = new ProductInsertEvent(itemNameTextField, alternateItemNameTextField, 
				itemTypeChoiceBox, itemUnitChoiceBox);
		
		itemAdderEvent.setItemUnitListViewEvent(itemUnitListViewEvent);
		
		itemAdderEvent.setPurchaseDatePicker(purchaseDatePicker);
		itemAdderEvent.setPurchasePriceTextField(purchasePriceTextField);
		itemAdderEvent.setPurchaseQtyTextField(purchaseQtyTextField);

		itemAdderEvent.setWholesaleSellingPriceTextField(wholesaleSellingPriceTextField);
		itemAdderEvent.setWholesaleDiscountAmountInPrecentTextField(wholesaleDiscountAmountInPrecentTextField);
		itemAdderEvent.setWholesaleIntraStateMovementGSTInPrecentTextField(wholesaleIntraStateMovementGSTInPrecentTextField);
		
		itemAdderEvent.setRetailSellingPriceTextField(retailSellingPriceTextField);
		itemAdderEvent.setRetailIntraStateMovementGSTInPrecentTextField(retailIntraStateMovementGSTInPrecentTextField);
		itemAdderEvent.setRetailDiscountAmountInPrecentTextField(retailDiscountAmountInPrecentTextField);
		
		itemAdderEvent.setAskBeforeSubmitCheckBox(askBeforeSubmitCheckBox); //set askBeforeSubmitCheckBox
		itemAdderEvent.setStatusItemAdder(statusItemAdder); //set lable for form status
		
		btnSubmit.setOnAction(itemAdderEvent);
		btnClear.setOnAction(itemAdderEvent);
		
		//Styling nodes
//		separatorHorizontal.setStyle("-fx-border-color:black; -fx-border-width: 0px;");
		btnSubmit.setStyle("-fx-background-color: skyblue; -fx-font: normal bold 15px 'serif';");
		btnClear.setStyle("-fx-background-color: skyblue; -fx-font: normal bold 15px 'serif';");
		
		//Setting the back ground color 
		grid.setStyle("-fx-background-color: BEIGE;");
//		grid.setGridLinesVisible(true);
		
		
		return grid;
	}
	

}
