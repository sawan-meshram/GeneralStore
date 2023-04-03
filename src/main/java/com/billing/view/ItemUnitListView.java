package com.billing.view;

import com.billing.event.ItemUnitListViewEvent;
import com.billing.product.ItemUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemUnitListView { // extends Application

/*	public static void main(String[] args) {
        Application.launch(args);
    }
*/	
	private final static double MAX_BUTTON_WIDTH = 100;
	
/*	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ItemUnitListViewEvent event = new ItemUnitListViewEvent();
		
		
		
		getItemUnitListView(primaryStage, event);
		for(Object o : event.getItemUnitListView().getSelectionModel().getSelectedItems()){
            System.out.println("o = " + o + " (" + o.getClass() + ")");
        }
	}*/
	
	public void getItemUnitListView(Stage primaryStage, ItemUnitListViewEvent event) {
		primaryStage.setTitle("Choose Multiple Item Unit");

		ListView<ItemUnit> listView = new ListView();
        listView.getItems().addAll(ItemUnit.values()); 
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		if(event.getItemUnitChoiceBox() != null) {
			if(event.getItemUnitChoiceBox().getValue() != null)
				listView.getSelectionModel().select(event.getItemUnitChoiceBox().getValue());
		}
		
		ObservableList<ItemUnit> selectedItemUnitListView = event.getSelectedListViewItems();
		if(selectedItemUnitListView != null) {
			for(ItemUnit itemUnit : selectedItemUnitListView) {
				listView.getSelectionModel().select(itemUnit);
			}
		}
        
        Button cancelButton = new Button("Cancel");
        Button submitButton = new Button("Submit");
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setSpacing(10);
        hbox.getChildren().addAll(cancelButton, submitButton);
        
        cancelButton.setPrefWidth(MAX_BUTTON_WIDTH);
        submitButton.setPrefWidth(MAX_BUTTON_WIDTH);
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        
        vbox.getChildren().addAll(listView, hbox);

        event.setItemUnitListView(listView);
        event.setCurrentStage(primaryStage);
        
        cancelButton.setOnAction(event);
        submitButton.setOnAction(event); 
        
        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
	}


	
}
