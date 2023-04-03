package com.billing.event;

import com.billing.util.AlertDialog;
import com.billing.util.ChangeCaseOnTextField;
import com.billing.util.U;
import com.billing.view.ProductUnitView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class ProductUnitInsertEvent implements EventHandler<ActionEvent>, ChangeListener<Toggle> {
	private Label statusLabel;
	private TextField itemUnitNameTextField;
	private TextField itemUnitNameAbbrTextField;
	private ToggleGroup groupTextCase;
	private RadioButton defaultTextCaseRadioButton;
//	private ProductUnitView productUnitView;
	


	@Override
	public void handle(ActionEvent event) {
//		U.log(event.getSource());
		if(event.getSource() instanceof Button) {
			Button button = (Button) event.getSource();
//			U.log(button);
			if(button.getText().equals("Add")) {
				
				addButtonPress();
				
			}else if(button.getText().equals("Clear")) {
				
				clearButtonPress();
			}
			else if (button.getText().equals("Reset")) {
				resetButtonPress();
			}
		}
	}
	
	@Override
	public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		if (groupTextCase.getSelectedToggle() != null) {
			RadioButton button = (RadioButton) groupTextCase.getSelectedToggle();
//			System.out.println("select case: " + button.getText());
			
			ChangeCaseOnTextField.changeCaseOnTextField(button, itemUnitNameTextField, itemUnitNameTextField.getText().trim());
			ChangeCaseOnTextField.changeCaseOnTextField(button, itemUnitNameAbbrTextField, itemUnitNameAbbrTextField.getText().trim());
		}
	}
	
	
	

	private void addButtonPress() {
		String unitName = itemUnitNameTextField.getText().trim();
		String unitNameAbbr = itemUnitNameAbbrTextField.getText().trim();
		
		if(unitName.isEmpty() && unitNameAbbr.isEmpty()) {
//			productUnitView.unitNameAndAbbrMissingDialogBox("Enter unit name & its abbreviation");
			AlertDialog.showMissingTextAlert("Enter unit name & its abbreviation");
		}
		else if(unitName.isEmpty()) {
//			productUnitView.unitNameAndAbbrMissingDialogBox("Enter unit name");
			AlertDialog.showMissingTextAlert("Enter unit name");
		}
		else if(unitNameAbbr.isEmpty()) {
//			productUnitView.unitNameAndAbbrMissingDialogBox("Enter unit name abbreviation");
			AlertDialog.showMissingTextAlert("Enter unit name abbreviation");
		}else {
			U.log("Unit Name :"+unitName);
			U.log("Unit Name Abbr :"+unitNameAbbr);

			U.log("Values are present");
			setAddedSuccessfullyMessage();
		}
	}
	
	private void setAddedSuccessfullyMessage() {
		statusLabel.setTextFill(Color.GREEN);
		statusLabel.setStyle("-fx-font: normal bold 13px 'serif';");
		statusLabel.setText("Added Successfully.");
	}
	
	private void clearButtonPress() {
		itemUnitNameTextField.setText("");
		itemUnitNameAbbrTextField.setText("");
		statusLabel.setText(null);
	}
	
	private void resetButtonPress() {
		clearButtonPress();
//		if(!defaultTextCaseRadioButton.isSelected())
		defaultTextCaseRadioButton.setSelected(true);
	}

	public void setItemUnitNameTextField(TextField itemUnitNameTextField) {
		this.itemUnitNameTextField = itemUnitNameTextField;
	}

	public void setItemUnitNameAbbrTextField(TextField itemUnitNameAbbrTextField) {
		this.itemUnitNameAbbrTextField = itemUnitNameAbbrTextField;
	}

//	public void setProductUnitView(ProductUnitView productUnitView) {
//		this.productUnitView = productUnitView;
//	}
	public void setStatusLabel(Label statusLabel) {
		this.statusLabel = statusLabel;
	}
	
	public void setGroupTextCase(ToggleGroup groupTextCase) {
		this.groupTextCase = groupTextCase;
	}

	public void setBtnReset(Button btnReset) {
	}

	public void setDefaultTextCaseRadioButton(RadioButton defaultTextCaseRadioButton) {
		this.defaultTextCaseRadioButton = defaultTextCaseRadioButton;
	}
}
