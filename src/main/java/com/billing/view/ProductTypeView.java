package com.billing.view;

import java.util.List;

import org.hibernate.Session;

import com.billing.database.BillingDatabase;
import com.billing.database.ProductTypeDatabase;
import com.billing.database.dao.impl.ProductTypeDaoImpl;
import com.billing.event.ProductTypeInsertEvent;
import com.billing.event.ProductTypeNameEditingCell;
import com.billing.pojo.ProductType;
import com.billing.util.ChangeCaseOnTextField;
import com.billing.util.U;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProductTypeView {
	private static final double WIDTH = 600;
	private static final double HEIGHT = 700;
	private static final double BUTTON_WIDTH = 100;

	public void getProductTypeView(Stage stage) {
		
		ProductTypeInsertEvent event = new ProductTypeInsertEvent();

		stage.setTitle("Item Type Form");

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(viewProductTypeEntryForm(event), viewProductTypeTable(event), viewBottomButton(event));
		
		Scene scene = new Scene(vbox, WIDTH, HEIGHT);

		stage.setScene(scene);
		stage.show();
	}
	
	private Pane viewProductTypeEntryForm (ProductTypeInsertEvent event) {
		GridPane grid = new GridPane();
		//Setting size for the pane 
		grid.setMaxWidth(WIDTH);
//		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(12);
		grid.setPadding(new Insets(25, 0, 25, 0));
		
		Text sceneTitle = new Text("Item Type Form");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		
		final Separator separatorHorizontalTop = new Separator();
		separatorHorizontalTop.setValignment(VPos.CENTER);

		Label itemTypeName = new Label("Item Type Name");
		TextField itemTypeTextField = new TextField();
		itemTypeTextField.setMinWidth(450);
		
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
		grid.add(itemTypeName, 0, 3);
		grid.add(itemTypeTextField, 1, 3);

		
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
		
		event.setDefaultTextCaseRadioButton(upperCaseButton);
		event.setStatusLabel(statusLabel);
		event.setGroupTextCase(groupTextCase);
		event.setItemTypeTextField(itemTypeTextField);
//		event.setProductTypeView(this);
		
		//changes text case 
		itemTypeTextField.textProperty().addListener((observableValues, oldValue, newValue) -> {
			ChangeCaseOnTextField.changeCaseOnTextField((RadioButton) groupTextCase.getSelectedToggle(), itemTypeTextField, newValue);
		});
		
		groupTextCase.selectedToggleProperty().addListener(event);
				
		//Button event
		btnAdd.setOnAction(event);
		btnClear.setOnAction(event);
		btnReset.setOnAction(event);
				
		return grid;
	}
	
	private TableView viewProductTypeTable (ProductTypeInsertEvent event) {
		TableView<ProductType> table = new TableView<>();
		table.setEditable(false);
		
		TableColumn<ProductType, Long> typeIdCol = new TableColumn<>("Id");
		typeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<ProductType, String> typeNameCol = new TableColumn<>("Type_Name");
		typeNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		table.getColumns().addAll(typeIdCol, typeNameCol);
		
		U.disableSortOnTableColumn(typeIdCol);
		U.disableSortOnTableColumn(typeNameCol);
		
//		typeIdCol.setMinWidth(90);
//		typeNameCol.setMinWidth(390);
		
		typeIdCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
		typeNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.80));
		
		typeIdCol.setStyle("-fx-alignment: CENTER;");

		Callback<TableColumn<ProductType, String>, TableCell<ProductType, String>> nameCellFactory = 
				(TableColumn<ProductType, String> p) -> new ProductTypeNameEditingCell();
		        
//		typeNameCol.setCellFactory(TextFieldTableCell.<ProductType>forTableColumn());
		typeNameCol.setCellFactory(nameCellFactory);
		typeNameCol.setOnEditCommit((e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue())));

		event.setTable(table);
		
		//add records from database
		
		List<ProductType> productTypeList = ProductTypeDaoImpl.getInstance().getProductTypes();
		U.log("Total Product ::"+productTypeList.size());
		if(productTypeList.size() > 0)
			table.getItems().addAll(productTypeList);
		
		
		return table;
	}
	
	private Pane viewBottomButton (ProductTypeInsertEvent event) {
		final HBox hb = new HBox();
		Button btnUpdate = new Button("Update");
		Button btnDelete = new Button("Delete");
		Button btnEdit = new Button("Edit");
		
		btnUpdate.setId("Table_Row_Update");
		btnDelete.setId("Table_Row_Delete");
		btnEdit.setId("Table_Row_Edit");
		
		btnUpdate.setOnAction(event);
		btnDelete.setOnAction(event);
		btnEdit.setOnAction(event);
		
		
		hb.getChildren().addAll(btnEdit, btnDelete, btnUpdate);
        hb.setSpacing(3);
        hb.setAlignment(Pos.BASELINE_RIGHT);
        return hb;
	}
}
