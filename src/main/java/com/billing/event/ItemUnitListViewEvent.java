package com.billing.event;

import com.billing.product.ItemUnit;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ItemUnitListViewEvent implements EventHandler<ActionEvent>{

	private ListView<ItemUnit> itemUnitListView;
	private ChoiceBox<ItemUnit> itemUnitChoiceBox;
	
	private ObservableList<ItemUnit> selectedListViewItems;
	private Stage currentStage;
	
	public ItemUnitListViewEvent() {
		
	}
	
	public ItemUnitListViewEvent(ChoiceBox<ItemUnit> itemUnitChoiceBox) {
		this.setItemUnitChoiceBox(itemUnitChoiceBox);
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() instanceof Button) {
			Button button = (Button) event.getSource();
			if(button.getText().equals("Cancel")) {
				itemUnitListView.getSelectionModel().clearSelection();
				
				if(itemUnitChoiceBox != null && itemUnitChoiceBox.getValue() != null) {
					itemUnitChoiceBox.setValue(null);
				}
			}else if(button.getText().equals("Submit")) {
				selectedListViewItems = itemUnitListView.getSelectionModel().getSelectedItems();
				
				for(Object o : selectedListViewItems){
		            System.out.println("o = " + o + " (" + o.getClass() + ")");
		        }
				
				if(itemUnitChoiceBox != null) {
					itemUnitChoiceBox.setValue(selectedListViewItems.get(0));
				}
				currentStage.close();
			}
		}
	}

	public ListView<ItemUnit> getItemUnitListView() {
		return itemUnitListView;
	}

	public void setItemUnitListView(ListView<ItemUnit> itemUnitListView) {
		this.itemUnitListView = itemUnitListView;
	}

	public ObservableList<ItemUnit> getSelectedListViewItems() {
		return selectedListViewItems;
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}

	public ChoiceBox<ItemUnit> getItemUnitChoiceBox() {
		return itemUnitChoiceBox;
	}

	public void setItemUnitChoiceBox(ChoiceBox<ItemUnit> itemUnitChoiceBox) {
		this.itemUnitChoiceBox = itemUnitChoiceBox;
	}
	
	
}
