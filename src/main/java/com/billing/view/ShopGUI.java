package com.billing.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.billing.util.U;
import com.billing.database.BillingDatabase;
import com.billing.event.AddDataOnTableEvent;
import com.billing.event.ExitMenuEvent;
import com.billing.event.ProductListFileChooserEvent;
import com.billing.pojo.ProductType;
import com.billing.pojo.TableFooter;
import com.billing.product.Item;
import com.billing.util.AutoCompleteTextField;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
public class ShopGUI extends Application{

	private AddDataOnTableEvent addDataOnTableEvent = null;
	private ObservableList<Item> itemTableList = null;
	private static final double BUTTON_WIDTH = 100;
	private static final String FOOTER_TEXT = "Copyright (c) XYZ, Inc. All rights reserved.";
	
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Pane rootNode = getRootPane(stage);
		Scene scene = new Scene( rootNode, 1366, 768);
		 
		stage.setTitle("Billing Software");
		stage.setScene(scene);
//		System.out.println(Util.getResource("images/bill_soft_icon.jpg"));
		
//		stage.getIcons().add(new Image("/resources/images/bill_soft_icon.jpg"));
		//stage.setMaximized(true);

		stage.show();
	      
	}
	
	private Pane getRootPane(Stage stage){
		//Create event for table and button's
		this.addDataOnTableEvent = new AddDataOnTableEvent();
		
		BorderPane root = new BorderPane();
		//root.setPadding(new Insets(0, 20, 10, 10));
		
		//Top
//		root.setTop(getMenuBar(stage));
		root.setTop(getTop(stage));
		
		//Left
		root.setLeft(getLeftNode());
		
		//Right
		root.setRight(getRightNode());
		
		//Center
		root.setCenter(getCenterNode());
		
		//Bottom
		root.setBottom(getFooterNode()); //(getBottomNode());
		return root;
	}

	private Pane getLeftNode(){
		Button newButton = new Button("New");
		
		Button openButton = new Button("Open");
		Button itemPriceCalcButton = new Button("Item Price Calculator");
		Button stockButton = new Button("Stock");
		Button settingButton = new Button("Setting");
		Button exitButton = new Button("Exit");
		
		newButton.setMaxWidth(Double.MAX_VALUE);
		openButton.setMaxWidth(Double.MAX_VALUE);
		itemPriceCalcButton.setMaxWidth(Double.MAX_VALUE);
		stockButton.setMaxWidth(Double.MAX_VALUE);
		settingButton.setMaxWidth(Double.MAX_VALUE);
		exitButton.setMaxWidth(Double.MAX_VALUE);
		
		exitButton.setOnAction(new ExitMenuEvent());
		itemPriceCalcButton.setOnAction(e -> new ItemCalculatorView().getItemCalculator(new Stage()));
		VBox vbox = new VBox();
		
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(15,20, 10,10));
        
		
		vbox.getChildren().addAll(newButton, openButton, itemPriceCalcButton, stockButton, settingButton, exitButton);
		return vbox;
	}
	
	private Pane getTop(Stage stage) {
		
		StackPane stack = new StackPane();
		Node clock = getNewClock();
		
		stack.getChildren().addAll(getMenuBar(stage), clock);
		
		StackPane.setAlignment(clock, Pos.CENTER_RIGHT);
		StackPane.setMargin(clock, new Insets(0, 20, 0, 0));
		VBox vb = new VBox(stack);
		
		return vb;
	}
	
	private Node getNewClock() {
//		HBox hb = new HBox();
//		hb.setAlignment(Pos.BASELINE_RIGHT);
		
//		final Label clockTitle = new Label("Clock : ");
		final Label clock = new Label();
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				clock.setText("Clock : "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			}
		};
		timer.start();
		clock.setStyle("-fx-font-weight: bold");
//		hb.getChildren().addAll(clockTitle, clock);
//		return hb;
		
		return clock;
	}
	
	private Pane getRightNode(){
		Button updateButton = new Button("Update");
		Button deleteButton = new Button("Delete");
		Button clearButton = new Button("Clear");
		
		updateButton.setMaxWidth(Double.MAX_VALUE);
		deleteButton.setMaxWidth(Double.MAX_VALUE);
		clearButton.setMaxWidth(Double.MAX_VALUE);
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(15,20, 10,10));
		
		vbox.getChildren().addAll(updateButton, deleteButton, clearButton);
		return vbox;
	}
	
	private MenuBar getMenuBar(Stage stage){
        MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu stockMenu = new Menu("Product");
        Menu sourceMenu = new Menu("Source");
        Menu customerMenu = new Menu("Customer");
        Menu helpMenu = new Menu("Help");
        
        // Create MenuItems
        MenuItem newItem = new MenuItem("New");
//        Image newImage = Util.getImage("/home/glady/workspaces/DemoGUI/resources/images/menu_new_file-512.png");
 //       newItem.setGraphic(new ImageView(newImage));
 
        
        MenuItem openFileItem = new MenuItem("Open File");
     // SeparatorMenuItem.
        SeparatorMenuItem separator= new SeparatorMenuItem();
        
        MenuItem exitItem = new MenuItem("Exit");
        // Set Accelerator for Exit MenuItem.
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
 
        // When user click on the Exit item.
        /*exitItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });*/

        
        exitItem.setOnAction(new ExitMenuEvent());
        
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        
        
        MenuItem addProductTypeMenuItem = new MenuItem("Add Product Type");
        MenuItem addProductUnitMenuItem = new MenuItem("Add Product Unit");
        MenuItem addStockMenuItem = new MenuItem("Add Product");
        MenuItem viewProductMenuItem = new MenuItem("View Product");
        
        addProductTypeMenuItem.setOnAction(e -> new ProductTypeView().getProductTypeView(new Stage()));
        addProductUnitMenuItem.setOnAction(e -> new ProductUnitView().getProductUnitView(new Stage()));
        addStockMenuItem.setOnAction(e -> new ProductInsertView().getProductInsertView(new Stage()));
        
        
        MenuItem selectProductListFileMenuItem = new MenuItem("Load Product List File");
        MenuItem saveProductListFileMenuItem = new MenuItem("Create Product List File");
        
        ProductListFileChooserEvent fileChooseEvent = new ProductListFileChooserEvent();
        fileChooseEvent.setPrimaryStage(stage);
        selectProductListFileMenuItem.setOnAction(fileChooseEvent);
        saveProductListFileMenuItem.setOnAction(fileChooseEvent);
        
        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, separator, exitItem);
        editMenu.getItems().addAll(copyItem, pasteItem);
        stockMenu.getItems().addAll(addProductTypeMenuItem, addProductUnitMenuItem, addStockMenuItem, viewProductMenuItem);
        sourceMenu.getItems().addAll(selectProductListFileMenuItem, saveProductListFileMenuItem);


        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu, stockMenu, sourceMenu, customerMenu, helpMenu);
        
        return menuBar;
	}
	
	public GridPane getTopDetailsNode(){
		GridPane gridPane = new GridPane();
		
		Label labelOrderId = new Label("Order ID :");
		TextField fieldOrderId = new TextField();
		Label labelDate = new Label("Date :");

		//date picker to choose date
		DatePicker orderDatePicker = new DatePicker();
		orderDatePicker.setValue(LocalDate.now());
		
		// 2nd Row
		Label labelCustomerName = new Label("Customer Name :");
		TextField fieldCustomerName = new TextField();

		Label labelCustomerAddress = new Label("Address :");
		TextField fieldCustomerAddress = new TextField();

		Label labelMobileNo = new Label("Mobile No. :");
		TextField fieldMobileNo = new TextField();

		final Separator separatorHorizontalTop = new Separator();
		separatorHorizontalTop.setValignment(VPos.CENTER);

		
		// 5th Row
		Label labelItemName = new Label("Item Name :");
				
		AutoCompleteTextField autoSuggestionField = new AutoCompleteTextField();
		Label labelItemQty = new Label("Qty. :");
		TextField fieldItemQty = new TextField();
		
		
		Button buttonAddItem = new Button("Add");
		Button buttonShowItem = new Button("Show");
		buttonAddItem.setMinWidth(BUTTON_WIDTH);
		buttonShowItem.setMinWidth(BUTTON_WIDTH);
		
		//Set HBox for buttons
		HBox hboxBtn = new HBox(10);
		hboxBtn.setAlignment(Pos.BOTTOM_LEFT);
		hboxBtn.getChildren().addAll(buttonAddItem, buttonShowItem);
				
		gridPane.add(labelOrderId, 0, 0);
		gridPane.add(fieldOrderId, 1, 0);
		gridPane.add(labelDate, 10, 0);
		gridPane.add(orderDatePicker, 11, 0);
		
		gridPane.add(labelCustomerName, 0, 1);
		gridPane.add(fieldCustomerName, 1, 1, 3, 1);
		gridPane.add(labelCustomerAddress, 5, 1);
		gridPane.add(fieldCustomerAddress, 6, 1, 3, 1);
		
		gridPane.add(labelMobileNo, 10, 1);
		gridPane.add(fieldMobileNo, 11, 1);

		gridPane.add(separatorHorizontalTop, 0, 3, 12, 1);
		
		
		gridPane.add(labelItemName, 0, 4);
		gridPane.add(autoSuggestionField, 1, 4, 3, 1);
		gridPane.add(labelItemQty, 5, 4);
		gridPane.add(fieldItemQty, 6, 4, 2, 1);
		gridPane.add(hboxBtn, 10, 4, 2, 1);
		
		autoSuggestionField.setMinWidth(300);
		autoSuggestionField.setMaxWidth(300);

		fieldCustomerAddress.setMinWidth(250);
		fieldCustomerAddress.setMaxWidth(250);
		
		this.addDataOnTableEvent.setFieldItemName(autoSuggestionField); //set event on textfield
		autoSuggestionField.getEntries().addAll(Arrays.asList("Flour", "Rice", "Soap", "Lux Soap", "Sop", "Powder"));

		this.addDataOnTableEvent.setFieldItemQty(fieldItemQty); //set event on textfield
		
		buttonAddItem.setId("Add_Item_At_Table"); //set ID for trace at event handler
		buttonAddItem.setOnAction(this.addDataOnTableEvent);
		buttonShowItem.setId("Show_Item_At_Table");
		
		
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
//		gridPane.setGridLinesVisible(true);

		
		return gridPane;
	}
	
	private Pane getClock() {
		HBox hb = new HBox();
		hb.setAlignment(Pos.BASELINE_RIGHT);
		final Label clockTitle = new Label("Clock : ");
		final Label clock = new Label();
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				clock.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			}
		};
		timer.start();
		hb.getChildren().addAll(clockTitle, clock);
		return hb;
	}
	
	private Pane getCenterNode(){
		VBox vbox = new VBox();
//		vbox.getChildren().addAll(getClock(), getTopDetailsNode(), setTableView(),  getBottomNode()); //getTableBottomNode()
		vbox.getChildren().addAll(getTopDetailsNode(), setTableView(),  getBottomNode()); //getTableBottomNode() 
		
		return vbox;
	}
	
	private Pane getFooterNode(){
		HBox hb = new HBox();
		hb.setPadding(new Insets(5, 0, 5, 0));
//		hb.setSpacing(10);
		    
		hb.setAlignment(Pos.BOTTOM_CENTER);
		Label footerLable = new Label(FOOTER_TEXT);
		hb.getChildren().add(footerLable);
		
		return hb;
	}
	
	
	private Pane getBottomNode(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 0, 60, 0));
		// 1st Row
		Label labelTotalItemPrice = new Label("Total Item Price : ");
		TextField textFieldTotalItemPrice = new TextField();
		
		Label labelTotalDiscount = new Label("Total Discount : ");
		TextField textFieldTotalDiscount = new TextField();
		
		
		Label labelTotalGST = new Label("Total GST : ");
		TextField textFieldTotalGST = new TextField();
		
		Label labelInvoiceTotal = new Label("Total Net : ");
		TextField textFieldInvoiceTotal = new TextField();
		
		
		gridPane.add(labelTotalItemPrice, 0, 0);
		gridPane.add(textFieldTotalItemPrice, 1, 0);
		gridPane.add(labelTotalDiscount, 0, 1);
		gridPane.add(textFieldTotalDiscount, 1, 1);
		gridPane.add(labelTotalGST, 0, 2);
		gridPane.add(textFieldTotalGST, 1, 2);
		gridPane.add(labelInvoiceTotal, 0, 3);
		gridPane.add(textFieldInvoiceTotal, 1, 3);
		
		
//		GridPane.setHalignment(labelTotalItemPrice, HPos.RIGHT);
//		GridPane.setHalignment(labelTotalDiscount, HPos.RIGHT);
//		GridPane.setHalignment(labelTotalGST, HPos.RIGHT);
//		GridPane.setHalignment(labelInvoiceTotal, HPos.RIGHT);
		
		gridPane.setAlignment(Pos.CENTER_RIGHT);
		gridPane.setVgap(5);
//		gridPane.setGridLinesVisible(true);
		gridPane.setStyle("-fx-background-color: #FFFFFF;");
		
		return gridPane;
	}
	
	
	public Pane setTableView(){
		
		TableView<Item> table = new TableView<>();
		
		TableColumn<Item, Integer> titleSrNo = new TableColumn<>("Sr.No.");
		TableColumn<Item, String> titleItem = new TableColumn<>("Item");
		TableColumn<Item, Float> titleQty = new TableColumn<>("Qty");
		TableColumn<Item, Float> titleItemUnit = new TableColumn<>("Unit");
		TableColumn<Item, Double> titleItemPrice = new TableColumn<>("Item_Price");
		TableColumn<Item, Float> titleDiscount = new TableColumn<>("Discount(%)");
		TableColumn<Item, Float> titleGST = new TableColumn<>("GST(%)");
		TableColumn<Item, Double> titleAmount = new TableColumn<>("Total(₹)");

		//Defines how to fill data for each cell.
	    // Get value from property of Item.
//		titleSrNo.setCellValueFactory(new PropertyValueFactory<>("srNo"));
//		titleItem.setCellValueFactory(new PropertyValueFactory<>("itemName"));
//		titleItemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
//		titleQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//		titleDiscount.setCellValueFactory(new PropertyValueFactory<>("itemDiscount"));
//		titleGST.setCellValueFactory(new PropertyValueFactory<>("cGstPrice"));
//		titleSGST.setCellValueFactory(new PropertyValueFactory<>("sGstPrice"));
//		titleAmount.setCellValueFactory(new PropertyValueFactory<>("itemTotal"));
		
		
		titleSrNo.setSortable(false);
		titleItem.setSortable(false);
		titleItemUnit.setSortable(false);
		titleItemPrice.setSortable(false);
		titleQty.setSortable(false);
		titleDiscount.setSortable(false);
		titleGST.setSortable(false);
		titleAmount.setSortable(false);
		
		titleSrNo.prefWidthProperty().bind(table.widthProperty().multiply(0.09));
		titleItem.prefWidthProperty().bind(table.widthProperty().multiply(0.28));
		titleItemUnit.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
		titleQty.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
		titleItemPrice.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		titleDiscount.prefWidthProperty().bind(table.widthProperty().multiply(0.09));
		titleGST.prefWidthProperty().bind(table.widthProperty().multiply(0.06));
		titleAmount.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		
		
		setItemPriceTextFieldPrefWidth(titleItemPrice.getMaxWidth()); //set total width of Item Price column size
		setDiscountTextFieldPrefWidth(titleDiscount.getMaxWidth()); //set total width of Discount column size
		setGSTTextFieldPrefWidth(titleGST.getMaxWidth()); //set total width of CGST column size
		setTotalAmountTextFieldPrefWidth(titleAmount.getMaxWidth()); //set total width of Total column size

		
		this.itemTableList = FXCollections.observableArrayList();
		
		this.addDataOnTableEvent.setItemTableList(itemTableList);
		table.setItems(itemTableList);
		
		table.getColumns().addAll(titleSrNo, titleItem, titleItemUnit, titleQty, titleItemPrice, titleDiscount, titleGST, titleAmount);
		
		this.addDataOnTableEvent.setTableView(table); //set reference at event class
		
		StackPane root = new StackPane();
		root.getChildren().add(table);
		
		setTablePrefWidth(table.getMinWidth()); //set total width of Table size
		
		return root;
	}
	
	public Pane getTableBottomNodeNew(){
		TableView table = new TableView();

		TableColumn colEmpty1 = new TableColumn(); //"Empty"
		TableColumn colEmpty2 = new TableColumn();
		TableColumn colEmpty3 = new TableColumn();
		TableColumn colEmpty4 = new TableColumn();
		TableColumn colEmpty5 = new TableColumn();
		TableColumn colEmpty6 = new TableColumn();
		
//		TableColumn colTotalInRupees = new TableColumn("Total In Rupees");
		TableColumn colItem = new TableColumn(); //"Item Price"
//		TableColumn colDiscount = new TableColumn("Discount");
//		TableColumn colGST = new TableColumn("GST");
		TableColumn colTotal = new TableColumn(); //"Total"
		
		colEmpty1.prefWidthProperty().bind(table.widthProperty().multiply(0.09));
		colEmpty2.prefWidthProperty().bind(table.widthProperty().multiply(0.28));
		colEmpty3.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
		colEmpty4.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
		colItem.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		colEmpty5.prefWidthProperty().bind(table.widthProperty().multiply(0.09));
		colEmpty6.prefWidthProperty().bind(table.widthProperty().multiply(0.06));
		colTotal.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		
		colEmpty1.setCellValueFactory(new PropertyValueFactory<TableFooter, Label>("label1"));
		colEmpty2.setCellValueFactory(new PropertyValueFactory<TableFooter, Label>("label2"));
		colEmpty3.setCellValueFactory(new PropertyValueFactory<TableFooter, Label>("label3"));
		colEmpty4.setCellValueFactory(new PropertyValueFactory<TableFooter, Label>("label4"));
		colEmpty5.setCellValueFactory(new PropertyValueFactory<TableFooter, Label>("label5"));
		colEmpty6.setCellValueFactory(new PropertyValueFactory<TableFooter, Label>("label6"));
		colItem.setCellValueFactory(new PropertyValueFactory<TableFooter, TextField>("itemPriceLabel"));
		colTotal.setCellValueFactory(new PropertyValueFactory<TableFooter, TextField>("totalAmountLabel"));
        
		

		table.getColumns().addAll(colEmpty1, colEmpty2, colEmpty3, colEmpty4, colItem, colEmpty5, colEmpty6, colTotal);

		colEmpty1.setSortable(false);
		colEmpty2.setSortable(false);
		colEmpty3.setSortable(false);
		colEmpty4.setSortable(false);
		colEmpty5.setSortable(false);
		colEmpty6.setSortable(false);
		colItem.setSortable(false);
		colTotal.setSortable(false);

		
//		TextField fieldItemPrice = new TextField();
//		fieldItemPrice.setPromptText("Total Item Price");
//		TextField fieldTotal = new TextField();
//		fieldTotal.setPromptText("Total Amount");
		
		table.getItems().add(new TableFooter());
		
		U.log(table.getItems().size());
		U.log(table.getFixedCellSize());
		
		table.setMinHeight(0);
		table.setMaxHeight(200);
		table.setPrefHeight(200);
		

		StackPane root = new StackPane();
		root.getChildren().add(table);
		return root;
	}
	
	public Pane getTableBottomNode(){
		final HBox hbox = new HBox();
		hbox.setSpacing(5);
		
		U.log(getTotalTextFieldPrefWidth());
		U.log(getGSTTextFieldPrefWidth());
		U.log(getDiscountTextFieldPrefWidth());
		U.log(getItemPriceTextFieldPrefWidth());
		
		final TextField fieldTotal = new TextField();
		fieldTotal.setPromptText("Total");
        fieldTotal.setMaxWidth(getTotalTextFieldPrefWidth());
        
        final TextField fieldGST = new TextField();
        fieldGST.setMaxWidth(getGSTTextFieldPrefWidth());
        fieldGST.setPromptText("GST");
        
        final TextField fieldDiscount = new TextField();
        fieldDiscount.setMaxWidth(getDiscountTextFieldPrefWidth());
        fieldDiscount.setPromptText("Discount");
        
        final TextField fieldItemPrice = new TextField();
        fieldItemPrice.setMaxWidth(getItemPriceTextFieldPrefWidth());
        fieldItemPrice.setPromptText("Item Price");
        
        hbox.getChildren().addAll(fieldItemPrice, fieldDiscount, fieldGST, fieldTotal);
        
        hbox.setPrefWidth(getTablePrefWidth());
        hbox.setAlignment(Pos.CENTER_RIGHT);
                
        return hbox;
	}
	
	private double tableTitleAmountWidth = 0;
	private double tableTitleGSTWidth = 0;
	private double tableTitleDiscountWidth = 0;
	private double tableTitleItemPriceWidth = 0;
	private double tableWidth = 0;
		
	private void setTotalAmountTextFieldPrefWidth(double tableTitleAmountWidth){
		this.tableTitleAmountWidth = tableTitleAmountWidth;
	}
	
	private double getTotalTextFieldPrefWidth(){
		return tableTitleAmountWidth;
	}
	
	private void setGSTTextFieldPrefWidth(double tableTitleGSTWidth){
		this.tableTitleGSTWidth = tableTitleGSTWidth;
	}
	
	private double getGSTTextFieldPrefWidth(){
		return tableTitleGSTWidth;
	}
	
	private void setDiscountTextFieldPrefWidth(double tableTitleDiscountWidth){
		this.tableTitleDiscountWidth = tableTitleDiscountWidth;
	}
	
	private double getDiscountTextFieldPrefWidth(){
		return tableTitleDiscountWidth;
	}
	
	private void setItemPriceTextFieldPrefWidth(double tableTitleItemPriceWidth){
		this.tableTitleItemPriceWidth = tableTitleItemPriceWidth;
	}
	
	private double getItemPriceTextFieldPrefWidth(){
		return tableTitleItemPriceWidth;
	}
	
	private void setTablePrefWidth(double tableWidth){
		this.tableWidth = tableWidth;
	}
	
	private double getTablePrefWidth(){
		return tableWidth;	
	}
}
