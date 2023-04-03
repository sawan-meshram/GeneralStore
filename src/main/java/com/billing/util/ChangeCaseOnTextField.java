package com.billing.util;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ChangeCaseOnTextField {

	public static void changeCaseOnTextField(RadioButton button, TextField textField, String newValue) {
		if(button.getText().contains("Lowercase"))
			textField.setText(newValue.toLowerCase());
		else if(button.getText().contains("Uppercase"))
			textField.setText(newValue.toUpperCase());
		else if(button.getText().contains("Capitalize"))
			textField.setText(U.toCapitalizeWord(newValue));
	}
}
