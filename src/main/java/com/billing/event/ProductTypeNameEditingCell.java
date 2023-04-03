package com.billing.event;

import com.billing.pojo.ProductType;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class ProductTypeNameEditingCell extends TableCell<ProductType, String> {
	private TextField textField;

	public ProductTypeNameEditingCell() {
	}

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			createTextField();
			setText(null);
			setGraphic(textField);
			textField.selectAll();
//			textField.requestFocus();
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();

		setText((String) getItem());
		setGraphic(null);
	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setText(null);
				setGraphic(textField);
			} else {
				setText(getString());
				setGraphic(null);
			}
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		
//		textField.setOnKeyPressed(keyEvent -> {
//            if (keyEvent.getCode() == KeyCode.ESCAPE) {
//                cancelEdit();
//                keyEvent.consume();
//            } else if (keyEvent.getCode() == KeyCode.ENTER) {
//                commitEdit(textField.getText()); // For now casting directly for testing
//                keyEvent.consume();
//            }
//        });
//		
		textField.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> obs, Boolean prevFocus, Boolean focused) -> {
//					if (!focused && isEditing()) {
//	                    cancelEdit();
//	                }
					 
					if (!focused) {
						commitEdit(textField.getText());
					}
				});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}
