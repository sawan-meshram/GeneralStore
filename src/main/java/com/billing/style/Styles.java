package com.billing.style;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Styles {
	/**
	 * Build TextFlow with selected text. Return "case" dependent.
	 * 
	 * @param text - string with text
	 * @param filter - string to select in text
	 * @return - TextFlow
	 */
	public static TextFlow buildTextFlow(String text, String filter) {        
	    int filterIndex = text.toLowerCase().indexOf(filter.toLowerCase());
	    //Part before occurence (might be empty)
	    Text textBefore = new Text(text.substring(0, filterIndex));
	    
	    //Part after occurence
	    Text textAfter = new Text(text.substring(filterIndex + filter.length()));
	    
	    //Part of (first) occurence
	    Text textFilter = new Text(text.substring(filterIndex,  filterIndex + filter.length())); //instead of "filter" to keep all "case sensitive"
	    
	    textFilter.setFill(Color.ORANGE);
	    textFilter.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));  
	    return new TextFlow(textBefore, textFilter, textAfter);
	}
}
