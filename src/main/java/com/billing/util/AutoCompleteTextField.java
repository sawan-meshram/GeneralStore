package com.billing.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.billing.style.Styles;
public class AutoCompleteTextField extends TextField{
	
	//Local variables entries to autocomplete
	private final SortedSet<String> entries;
	
	/** The popup used to select an entry. */
	private ContextMenu entriesPopup;

	/** Construct a new AutoCompleteTextField. */
	public AutoCompleteTextField() {
		super();
		entries = new TreeSet<>();
		entriesPopup = new ContextMenu();
		
		setAutoSuggestionListner();
	}
	

	/**
	 * "Suggestion" specific listners
	 */
	private void setAutoSuggestionListner() { 
		//Add "suggestions" by changing text
		textProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
				String enteredText = getText();
				//always hide suggestion if nothing has been entered (only "spacebars" are dissalowed in TextFieldWithLengthLimit)
	            if (enteredText == null || enteredText.isEmpty()) {
	                entriesPopup.hide();
	            } else{
	            	//filter all possible suggestions depends on "Text", case insensitive
	                List<String> filteredEntries = entries.stream()
	                        .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
	                        .collect(Collectors.toList());
	                					
					//some suggestions are found
	                if (!filteredEntries.isEmpty()) {
	                    //build popup - list of "CustomMenuItem"
	                    populatePopup(filteredEntries, enteredText);
	                    if (!entriesPopup.isShowing()) { //optional
	                        entriesPopup.show(AutoCompleteTextField.this, Side.BOTTOM, 0, 0); //position of popup
	                    }
	                //no suggestions -> hide
	                } else {
	                    entriesPopup.hide();
	                }
	                
				}
			}
		});
	
		//Hide always by focus-in (optional) and out
		focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
				entriesPopup.hide();
			}
		});
	}
	
	/**
	 * Get the existing set of autocomplete entries.
	 * @return The existing autocomplete entries.
	 */
	public SortedSet<String> getEntries() { 
		return entries; 
	}
	
	/**
	 * Populate the entry set with the given search results.  Display is limited to 10 entries, for performance.
	 * @param searchResult The set of matching strings.
	 */
	private void populatePopup(List<String> searchResult, String searchReauest) {
		//List of "suggestions"
		List<CustomMenuItem> menuItems = new LinkedList<>();
		
		// If you'd like more entries, modify this line. // List size - 10 or founded suggestions count
		int maxEntries = 10;
		int count = Math.min(searchResult.size(), maxEntries);
		
		//Build list as set of labels
		for (int i = 0; i < count; i++)	{
			final String result = searchResult.get(i);
			//label with graphic (text flow) to highlight founded subtext in suggestions
			Label entryLabel = new Label();
			entryLabel.setGraphic(Styles.buildTextFlow(result, searchReauest));  
	        entryLabel.setPrefHeight(15);  //don't sure why it's changed with "graphic"
	          
			CustomMenuItem item = new CustomMenuItem(entryLabel, true);
			menuItems.add(item);

			//if any suggestion is select set it into text and close popup
			item.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent actionEvent) {
					setText(result);
					positionCaret(result.length());
					entriesPopup.hide();
				}
			});
		}
		//"Refresh" context menu
		entriesPopup.getItems().clear();
		entriesPopup.getItems().addAll(menuItems);
	}
}
