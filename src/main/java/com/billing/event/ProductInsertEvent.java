package com.billing.event;

import java.time.LocalDate;
import java.util.Optional;

import com.billing.product.ItemType;
import com.billing.product.ItemUnit;
import com.billing.util.AlertDialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;

public class ProductInsertEvent implements EventHandler<ActionEvent> {

	
	private Label statusItemAdder;
	
	private TextField itemNameTextField;
	private TextField alternateItemNameTextField;
	private TextField purchaseQtyTextField;
	private TextField purchasePriceTextField;
	private TextField wholesaleSellingPriceTextField;
	private TextField retailSellingPriceTextField;
	
	private TextField wholesaleIntraStateMovementGSTInPrecentTextField;
	private TextField wholesaleDiscountAmountInPrecentTextField;
	private TextField retailIntraStateMovementGSTInPrecentTextField;
	private TextField retailDiscountAmountInPrecentTextField;

	private ChoiceBox<ItemType> itemTypeChoiceBox;	
	private ChoiceBox<ItemUnit> itemUnitChoiceBox;
	
	private CheckBox askBeforeSubmitCheckBox;
	
	private DatePicker purchaseDatePicker;
	private ItemUnitListViewEvent itemUnitListViewEvent;
	
	public ProductInsertEvent(TextField itemNameTextField, TextField alternateItemNameTextField, 
			ChoiceBox<ItemType> itemTypeChoiceBox, ChoiceBox<ItemUnit> itemUnitChoiceBox) {
		this.itemNameTextField = itemNameTextField;
		this.alternateItemNameTextField = alternateItemNameTextField;
		this.itemTypeChoiceBox = itemTypeChoiceBox;
		this.itemUnitChoiceBox = itemUnitChoiceBox;
	}
	
		
	@Override
	public void handle(ActionEvent event) {
		Button button = (Button)event.getSource();
		System.out.println("Press button ::"+button.getText());
		
		if(button.getText().equals("Submit")) {
			if(validatedItemEntry()) {
				AlertDialog.showMissingTextAlert("Mandatory field must not be empty.");
				return;
			}
			
			if(askBeforeSubmitCheckBox.isSelected()) {
				boolean isOKPressed =  AlertDialog.showConfirmationDialogBox("Item Entry Confirmation", "Confirm", "Do you really want to store these records?");	//showConfirmationDialogBox();
				if(isOKPressed) {
					submitItemEntryRecord();
				}else {
					statusItemAdder.setTextFill(Color.RED);
					statusItemAdder.setStyle("-fx-font: normal bold 15px 'serif';");
					statusItemAdder.setText("Abort.");
				}
			}else {
				submitItemEntryRecord();
			}
		}
		else if(button.getText().equals("Clear")) {
			clearItemEntryRecord();
		}
	}

	private void clearItemEntryRecord() {
		itemNameTextField.clear();
		alternateItemNameTextField.clear();
		purchaseQtyTextField.clear();
		purchasePriceTextField.clear();
		
		wholesaleSellingPriceTextField.clear();
		wholesaleIntraStateMovementGSTInPrecentTextField.clear();
		wholesaleDiscountAmountInPrecentTextField.clear();
		
		retailSellingPriceTextField.clear();
		retailIntraStateMovementGSTInPrecentTextField.clear();
		retailDiscountAmountInPrecentTextField.clear();
		
		itemTypeChoiceBox.getSelectionModel().clearSelection();
		itemUnitChoiceBox.getSelectionModel().clearSelection();
		
//		purchaseDatePicker.getEditor().clear();
		purchaseDatePicker.setValue(LocalDate.now());
		
		statusItemAdder.setText("");
		
		itemUnitListViewEvent.getItemUnitListView().getSelectionModel().clearSelection(); //ListView clear
	}
	
	private boolean validatedItemEntry() {
		if(purchaseDatePicker.getValue().toString().isEmpty() ||
				itemNameTextField.getText().isEmpty() || itemTypeChoiceBox.getValue() == null
				|| itemUnitChoiceBox.getValue() == null)return true;
		return false;
	}
	
	private void submitItemEntryRecord() {
		
		System.out.println("Purchase date :"+purchaseDatePicker.getValue());

		System.out.println("Item Name :"+itemNameTextField.getText());
		System.out.println("Alternate Item Name :"+alternateItemNameTextField.getText());
		
		System.out.println("Item Type :"+itemTypeChoiceBox.getValue()); //.getItemType()
		System.out.println("Item Unit :"+itemUnitChoiceBox.getValue());	
		
		System.out.println("Purchase Qty :"+purchaseQtyTextField.getText());
		System.out.println("Purchase Price/Qty :"+purchasePriceTextField.getText());
		
		System.out.println("Wholesale Selling Price/Qty :"+wholesaleSellingPriceTextField.getText());
		
		if(wholesaleIntraStateMovementGSTInPrecentTextField.isEditable()) {
			System.out.println("Wholesale GST (%) :"+wholesaleIntraStateMovementGSTInPrecentTextField.getText());
		}
		if(wholesaleDiscountAmountInPrecentTextField.isEditable()) {
			System.out.println("Wholesale Discount (%) :"+wholesaleDiscountAmountInPrecentTextField.getText());
		}
		
		System.out.println("Retail Selling Price/Qty :"+retailSellingPriceTextField.getText());

		
		if(retailIntraStateMovementGSTInPrecentTextField.isEditable()) {
			System.out.println("Retail GST (%) :"+retailIntraStateMovementGSTInPrecentTextField.getText());
		}
		if(retailDiscountAmountInPrecentTextField.isEditable()) {
			System.out.println("Retail Discount (%) :"+retailDiscountAmountInPrecentTextField.getText());
		}
		
		statusItemAdder.setTextFill(Color.GREEN);
		statusItemAdder.setStyle("-fx-font: normal bold 15px 'serif';");
		statusItemAdder.setText("Record Successfully Saved.");

	}
	
	/*private boolean showConfirmationDialogBox() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Item Entry Confirmation");
		
		alert.setHeaderText("Confirm");
		
		alert.setContentText("Do you really want to store these records?");
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			return true;
		}
		return false;
	}*/

	
	public CheckBox getAskBeforeSubmitCheckBox() {
		return askBeforeSubmitCheckBox;
	}

	public void setAskBeforeSubmitCheckBox(CheckBox askBeforeSubmitCheckBox) {
		this.askBeforeSubmitCheckBox = askBeforeSubmitCheckBox;
	}

	public Label getStatusItemAdder() {
		return statusItemAdder;
	}

	public void setStatusItemAdder(Label statusItemAdder) {
		this.statusItemAdder = statusItemAdder;
	}


	public TextField getPurchaseQtyTextField() {
		return purchaseQtyTextField;
	}


	public void setPurchaseQtyTextField(TextField purchaseQtyTextField) {
		this.purchaseQtyTextField = purchaseQtyTextField;
	}


	public TextField getPurchasePriceTextField() {
		return purchasePriceTextField;
	}


	public void setPurchasePriceTextField(TextField purchasePriceTextField) {
		this.purchasePriceTextField = purchasePriceTextField;
	}


	public TextField getWholesaleSellingPriceTextField() {
		return wholesaleSellingPriceTextField;
	}


	public void setWholesaleSellingPriceTextField(TextField wholesaleSellingPriceTextField) {
		this.wholesaleSellingPriceTextField = wholesaleSellingPriceTextField;
	}


	public TextField getRetailSellingPriceTextField() {
		return retailSellingPriceTextField;
	}


	public void setRetailSellingPriceTextField(TextField retailSellingPriceTextField) {
		this.retailSellingPriceTextField = retailSellingPriceTextField;
	}


	public TextField getWholesaleIntraStateMovementGSTInPrecentTextField() {
		return wholesaleIntraStateMovementGSTInPrecentTextField;
	}


	public void setWholesaleIntraStateMovementGSTInPrecentTextField(
			TextField wholesaleIntraStateMovementGSTInPrecentTextField) {
		this.wholesaleIntraStateMovementGSTInPrecentTextField = wholesaleIntraStateMovementGSTInPrecentTextField;
	}


	public TextField getWholesaleDiscountAmountInPrecentTextField() {
		return wholesaleDiscountAmountInPrecentTextField;
	}


	public void setWholesaleDiscountAmountInPrecentTextField(TextField wholesaleDiscountAmountInPrecentTextField) {
		this.wholesaleDiscountAmountInPrecentTextField = wholesaleDiscountAmountInPrecentTextField;
	}


	public TextField getRetailIntraStateMovementGSTInPrecentTextField() {
		return retailIntraStateMovementGSTInPrecentTextField;
	}


	public void setRetailIntraStateMovementGSTInPrecentTextField(TextField retailIntraStateMovementGSTInPrecentTextField) {
		this.retailIntraStateMovementGSTInPrecentTextField = retailIntraStateMovementGSTInPrecentTextField;
	}


	public TextField getRetailDiscountAmountInPrecentTextField() {
		return retailDiscountAmountInPrecentTextField;
	}


	public void setRetailDiscountAmountInPrecentTextField(TextField retailDiscountAmountInPrecentTextField) {
		this.retailDiscountAmountInPrecentTextField = retailDiscountAmountInPrecentTextField;
	}


	public DatePicker getPurchaseDatePicker() {
		return purchaseDatePicker;
	}


	public void setPurchaseDatePicker(DatePicker purchaseDatePicker) {
		this.purchaseDatePicker = purchaseDatePicker;
	}


	public ItemUnitListViewEvent getItemUnitListViewEvent() {
		return itemUnitListViewEvent;
	}


	public void setItemUnitListViewEvent(ItemUnitListViewEvent itemUnitListViewEvent) {
		this.itemUnitListViewEvent = itemUnitListViewEvent;
	}

	
}
