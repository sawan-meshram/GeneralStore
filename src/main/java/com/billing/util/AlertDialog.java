package com.billing.util;

import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;

public class AlertDialog {

	public static void showMissingTextAlert(String msg) {
		showMissingTextAlert("Error", "Empty error", msg);
	}
	
	public static void showMissingTextAlert(String header, String msg) {
		showMissingTextAlert("Error", header, msg);
	}

	public static void showMissingTextAlert(String title, String header, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	public static void showErrorTextAlert(String header, String content) {
		showErrorTextAlert("Error", header, content);
	}
	
	public static void showErrorTextAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.showAndWait();
	}
	
	public static void showSuccessTextAlert(String header, String content) {
		showSuccessTextAlert("Success", header, content);
	}
	public static void showSuccessTextAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.showAndWait();
	}

	public static void showSuccessTextAlert(Node header, Node content) {
		showSuccessTextAlert("Success", header, content);
	}
	public static void showSuccessTextAlert(String title, Node header, Node content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.getDialogPane().setHeader(header);
		alert.getDialogPane().setContent(content);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.showAndWait();
	}
	
	
	public static boolean showConfirmationDialogBox(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		
		alert.setHeaderText(header);
		
		alert.setContentText(content);
		
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			return true;
		}
		return false;
	}
	
	
}
