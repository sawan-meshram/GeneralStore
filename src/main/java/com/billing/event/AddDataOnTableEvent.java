package com.billing.event;

import com.billing.util.U;
import com.billing.product.Item;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddDataOnTableEvent implements EventHandler<ActionEvent> {
	
	private TableView<Item> tableView = null;
	private TextField fieldItemName = null;
	private TextField fieldItemType = null;
	private TextField fieldItemQty = null;
	private ObservableList<Item> itemTableList = null;
	
	public AddDataOnTableEvent(){}
	
	public AddDataOnTableEvent(TableView<Item> tableView, TextField fieldItemName, TextField fieldItemType, TextField fieldItemQty){
		this.setTableView(tableView);
		this.setFieldItemName(fieldItemName);
		this.setFieldItemType(fieldItemType);
		this.setFieldItemQty(fieldItemQty);
	}
	
	
	@Override
	public void handle(ActionEvent event) {

		String id = ((Node) event.getSource()).getId();
		
		switch (id) {
			case "Add_Item_At_Table":
	        // your code for "buttonDone"
				if(getTableView() != null){
					addItemOnTable();
					clearTextField();
				}else
					U.log("Table instance is null");
				break;
			default:
		}
		
		
		


	}

	private void addItemOnTable(){
		Item item = new Item();
		U.log("Name :"+getFieldItemName().getText());
		U.log("Qty :"+getFieldItemQty().getText());
		U.log("Type :"+getFieldItemType().getText());
		item.setItemName(getFieldItemName().getText());
		item.setQuantity(Float.parseFloat(getFieldItemQty().getText().trim()));
		item.setItemType(getFieldItemType().getText());

//		ObservableList<Item> itemList = getTableView().getItems();
		if(getItemTableList().isEmpty()){
			item.setSrNo(1);
		}else
			item.setSrNo(getItemTableList().size()+1);
		
		U.log("SrNo. :"+item.getSrNo());
		getTableView().getItems().add(item);
	}

	private void clearTextField(){
		getFieldItemName().clear();
		getFieldItemQty().clear();
		getFieldItemType().clear();
	}
	
	
	public TableView<Item> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<Item> tableView) {
		this.tableView = tableView;
	}


	public TextField getFieldItemName() {
		return fieldItemName;
	}


	public void setFieldItemName(TextField fieldItemName) {
		this.fieldItemName = fieldItemName;
	}


	public TextField getFieldItemType() {
		return fieldItemType;
	}


	public void setFieldItemType(TextField fieldItemType) {
		this.fieldItemType = fieldItemType;
	}


	public TextField getFieldItemQty() {
		return fieldItemQty;
	}


	public void setFieldItemQty(TextField fieldItemQty) {
		this.fieldItemQty = fieldItemQty;
	}

	public ObservableList<Item> getItemTableList() {
		return itemTableList;
	}

	public void setItemTableList(ObservableList<Item> itemTableList) {
		this.itemTableList = itemTableList;
	}

}
