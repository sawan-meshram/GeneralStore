package com.billing.view;


import com.billing.event.ProductUnitInsertEvent;
import com.billing.util.ChangeCaseOnTextField;
import com.billing.util.U;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductUnitView {

	private static final double WIDTH = 500;
	private static final double HEIGHT = 600;
	private static final double BUTTON_WIDTH = 100;
	
	private ProductUnitInsertEvent productUnitInsertEvent = null;
	public void getProductUnitView(Stage stage) {
		stage.setTitle("Item Unit Form");

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(viewProductUnitEntryForm(), viewProductUnitTable(),viewBottomButton());
		
		Scene scene = new Scene(vbox, WIDTH, HEIGHT);

		stage.setScene(scene);
		
		stage.show();
	}
	
	private Pane viewProductUnitEntryForm() {
		GridPane grid = new GridPane();
		//Setting size for the pane 
		grid.setMaxWidth(WIDTH);
//		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(12);
		grid.setPadding(new Insets(25, 0, 25, 0));
		
		Text sceneTitle = new Text("Item Unit Form");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		
		final Separator separatorHorizontalTop = new Separator();
		separatorHorizontalTop.setValignment(VPos.CENTER);

		Label itemUnitName = new Label("Item Unit Name");
		Label itemUnitNameAbbr = new Label("Abbreviation");
		
		TextField itemUnitNameTextField = new TextField();
		TextField itemUnitNameAbbrTextField = new TextField();
		itemUnitNameTextField.setMinWidth(350);
		
		
		
		Label textCaseLabel = new Label("Choose Text Case");
		
		ToggleGroup groupTextCase = new ToggleGroup();
		RadioButton upperCaseButton = new RadioButton("Uppercase (default)");
		upperCaseButton.setToggleGroup(groupTextCase);
		upperCaseButton.setSelected(true);

		RadioButton lowerCaseButton = new RadioButton("Lowercase");
		lowerCaseButton.setToggleGroup(groupTextCase);
		
		RadioButton capitalizeCaseButton = new RadioButton("Capitalize");
		capitalizeCaseButton.setToggleGroup(groupTextCase);
		

		/**
		 * Setting GridPane
		 */
	    //set title
		grid.add(sceneTitle, 0, 0, 2, 1);
		
		//set Horizontal
		grid.add(separatorHorizontalTop, 0, 1, 2, 1);
		

		//set purchase date
		grid.add(itemUnitName, 0, 3);
		grid.add(itemUnitNameTextField, 1, 3);

		//set item type
		grid.add(itemUnitNameAbbr, 0, 4);
		grid.add(itemUnitNameAbbrTextField, 1, 4);
		
		grid.add(textCaseLabel, 0, 5, 1, 3);
		GridPane.setValignment(textCaseLabel, VPos.TOP);
		grid.add(upperCaseButton, 1, 5);
		grid.add(lowerCaseButton, 1, 6);
		grid.add(capitalizeCaseButton, 1, 7);
		
		
		Button btnAdd = new Button("Add");
		Button btnClear = new Button("Clear");
		Button btnReset = new Button("Reset");
		Label statusLabel = new Label();

		btnAdd.setMinWidth(BUTTON_WIDTH);
		btnReset.setMinWidth(BUTTON_WIDTH);
		btnClear.setMinWidth(BUTTON_WIDTH);
		
		//Set HBox for buttons
		HBox hboxBtn = new HBox(10);
		hboxBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hboxBtn.getChildren().addAll(btnReset, btnClear, btnAdd);
		
		//add buttons
		grid.add(hboxBtn, 1, 9);

		grid.add(statusLabel, 0, 11, 2, 1);
		
		GridPane.setHalignment(statusLabel, HPos.CENTER);
		 
		productUnitInsertEvent = new ProductUnitInsertEvent();
		productUnitInsertEvent.setItemUnitNameTextField(itemUnitNameTextField);
		productUnitInsertEvent.setItemUnitNameAbbrTextField(itemUnitNameAbbrTextField);
//		productUnitInsertEvent.setProductUnitView(this);
		productUnitInsertEvent.setStatusLabel(statusLabel);
		productUnitInsertEvent.setGroupTextCase(groupTextCase);
		productUnitInsertEvent.setDefaultTextCaseRadioButton(upperCaseButton);

		//changes text case 
		itemUnitNameTextField.textProperty().addListener((observableValues, oldValue, newValue) -> {
			ChangeCaseOnTextField.changeCaseOnTextField((RadioButton) groupTextCase.getSelectedToggle(), itemUnitNameTextField, newValue);
		});
		
		itemUnitNameAbbrTextField.textProperty().addListener((observableValues, oldValue, newValue) -> {
			ChangeCaseOnTextField.changeCaseOnTextField((RadioButton) groupTextCase.getSelectedToggle(), itemUnitNameAbbrTextField, newValue);
		});
		
		//Toggel event
		groupTextCase.selectedToggleProperty().addListener(productUnitInsertEvent);

		//Button event
		btnAdd.setOnAction(productUnitInsertEvent);
		btnClear.setOnAction(productUnitInsertEvent);
		btnReset.setOnAction(productUnitInsertEvent);
		
		return grid;
	}
	
	private TableView viewProductUnitTable() {
		TableView table = new TableView();
		table.setEditable(false);
		
		TableColumn unitIdCol = new TableColumn("Id");
		TableColumn unitNameCol = new TableColumn("Unit_Name");
		TableColumn unitNameAbbrCol = new TableColumn("Unit_Name_Abbreviation");
		
		table.getColumns().addAll(unitIdCol, unitNameCol, unitNameAbbrCol);
		
		U.disableSortOnTableColumn(unitIdCol);
		U.disableSortOnTableColumn(unitNameCol);
		U.disableSortOnTableColumn(unitNameAbbrCol);
		
		unitIdCol.setMinWidth(90);
		unitNameCol.setMinWidth(195);
		unitNameAbbrCol.setMinWidth(195);
		
		return table;
	}
	
	private Pane viewBottomButton() {
		final HBox hb = new HBox();
		Button btnUpdate = new Button("Update");
		Button btnDelete = new Button("Delete");
		Button btnView = new Button("View");
		
		
		
		hb.getChildren().addAll(btnView, btnDelete, btnUpdate);
        hb.setSpacing(3);
        hb.setAlignment(Pos.BASELINE_RIGHT);
        return hb;
	}
	
	/*public void unitNameAndAbbrMissingDialogBox(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Empty error");
		alert.setContentText(msg);
		alert.showAndWait();
	}*/
}
