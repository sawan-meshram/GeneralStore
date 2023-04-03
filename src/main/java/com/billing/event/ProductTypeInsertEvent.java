package com.billing.event;

import org.hibernate.Session;

import com.billing.database.BillingDatabase;
import com.billing.database.ProductTypeDatabase;
import com.billing.database.dao.impl.ProductTypeDaoImpl;
import com.billing.pojo.ProductType;
import com.billing.util.AlertDialog;
import com.billing.util.ChangeCaseOnTextField;
import com.billing.util.U;
import com.billing.view.ProductTypeView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class ProductTypeInsertEvent implements EventHandler<ActionEvent>, ChangeListener<Toggle>{
	
	private Label statusLabel;
	private TextField itemTypeTextField;
	private ToggleGroup groupTextCase;
	private RadioButton defaultTextCaseRadioButton;
	private TableView<ProductType> table;
	
	@Override
	public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		if (groupTextCase.getSelectedToggle() != null) {
			RadioButton button = (RadioButton) groupTextCase.getSelectedToggle();
//			System.out.println("select case: " + button.getText());
			
			ChangeCaseOnTextField.changeCaseOnTextField(button, itemTypeTextField, itemTypeTextField.getText().trim());
		}

	}

	private long editedId = 0;
	private String editedProductTypeName;
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() instanceof Button) {
			Button button = (Button) event.getSource();
			U.log(button.getId());
			if(button.getId() != null && button.getId().equals("Table_Row_Edit")) {
				
				ProductType type = getTable().getSelectionModel().getSelectedItem();
				editedId = type.getId();
				editedProductTypeName = type.getName();
				
				getTable().setEditable(true);
				
	/*			Callback<TableColumn<ProductType, String>, TableCell<ProductType, String>> nameCellFactory = 
						(TableColumn<ProductType, String> p) -> new ProductTypeNameEditingCell();
				        
//				typeNameCol.setCellFactory(TextFieldTableCell.<ProductType>forTableColumn());
				typeNameCol.setCellFactory(nameCellFactory);
				
				typeNameCol.setOnEditCommit((e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue())));
*/
				//--OR--
//				typeNameCol.setOnEditCommit(
//						(CellEditEvent<ProductType, String> t) -> {
//			                ((ProductType) t.getTableView().getItems().get(t.getTablePosition().getRow()))
//			                .setName(t.getNewValue());
//						});

			}
			else if (button.getId() != null && button.getId().equals("Table_Row_Delete")) {
				deleteProductType(); 
				
			}else if (button.getId() != null && button.getId().equals("Table_Row_Update")) {
				updateProductType();
			}
			else if(button.getText().equals("Add")) {
				
				addButtonPress();
				
			}else if(button.getText().equals("Clear")) {
				
				clearButtonPress();
			}
			else if (button.getText().equals("Reset")) {
				resetButtonPress();
			}
		}
	}
	
	private void updateProductType() {
		getTable().setEditable(false);
		
		ProductType type = getTable().getSelectionModel().getSelectedItem();
		
		if(type == null || editedId == 0) {
			AlertDialog.showMissingTextAlert("Missing", null, "No edition found on table.");
			return;
		} 
		
		U.log("updated "+type);
		
		if(editedId == type.getId()) {
			if(!editedProductTypeName.equalsIgnoreCase(type.getName())) {
				
				if(ProductTypeDaoImpl.getInstance().update(type)) {
					showSuccessMessage("Updated Successfully.");
				}else {
					int index = getTable().getSelectionModel().getSelectedIndex();
					type.setName(editedProductTypeName);
					getTable().getItems().set(index, type); //revert changes if duplicate name found
					AlertDialog.showErrorTextAlert("Error while updating", "Cause may occur due to found duplicate 'Type_Name'.");
				}

			}else {
				AlertDialog.showSuccessTextAlert("Information", null, "No entry is found for updation.");
			}
			editedId = 0;
			editedProductTypeName = null;
		}
		else {
			AlertDialog.showSuccessTextAlert("Information", null, "Select edited row for updation.");
		}
		
		getTable().getSelectionModel().clearSelection();
	}
	
	private void deleteProductType() {
		
		ProductType type = getTable().getSelectionModel().getSelectedItem();
		if(AlertDialog.showConfirmationDialogBox("Confirmation", null, "Are you sure to delete this item?")) {
			if(ProductTypeDaoImpl.getInstance().delete(type)) {
				getTable().getItems().remove(type);
//				setDeletedSuccessfullyMessage();
				showSuccessMessage("Deleted Successfully.");

			}else {
				showFailedMessage("Item is not deleted.");
			}
		}else {
			showFailedMessage("Abort.");
		}

	}
	private void addButtonPress() {
		String itemType = itemTypeTextField.getText().trim();
		
		if(itemType.trim().isEmpty()) {
			AlertDialog.showMissingTextAlert("Missing", null, "Enter type name");
		}else {
			U.log("Item Type Name :"+itemType);

			ProductType type = new ProductType(itemType.trim());
//			U.log(type);
			
			if(ProductTypeDaoImpl.getInstance().insert(type)) { 
//				U.log(type);
				getTable().getItems().add(type);
				showSuccessMessage("Added Successfully.");
			}else {
				showSuccessMessage("Already Exist.");
			}
		}
	}
	
	private void showFailedMessage(String msg) {
		statusLabel.setTextFill(Color.RED);
		statusLabel.setStyle("-fx-font: normal bold 13px 'serif';");
		statusLabel.setText(msg);
	}

	private void showSuccessMessage(String msg) {
		statusLabel.setTextFill(Color.GREEN);
		statusLabel.setStyle("-fx-font: normal bold 13px 'serif';");
		statusLabel.setText(msg);
	}

	private void clearButtonPress() {
		itemTypeTextField.setText("");
		statusLabel.setText(null);
	}
	
	private void resetButtonPress() {
		clearButtonPress();
//		if(!defaultTextCaseRadioButton.isSelected())
		defaultTextCaseRadioButton.setSelected(true);
	}

	
	public void setStatusLabel(Label statusLabel) {
		this.statusLabel = statusLabel;
	}

	public void setItemTypeTextField(TextField itemTypeTextField) {
		this.itemTypeTextField = itemTypeTextField;
	}

	public void setGroupTextCase(ToggleGroup groupTextCase) {
		this.groupTextCase = groupTextCase;
	}

	public void setDefaultTextCaseRadioButton(RadioButton defaultTextCaseRadioButton) {
		this.defaultTextCaseRadioButton = defaultTextCaseRadioButton;
	}

	public TableView<ProductType> getTable() {
		return table;
	}

	public void setTable(TableView<ProductType> table) {
		this.table = table;
	}



}
