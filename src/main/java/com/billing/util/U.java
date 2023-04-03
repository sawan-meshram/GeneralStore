package com.billing.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TableColumn;

public class U {
	public static void log(Object o){
		System.out.println(o);
	}
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static LocalDate getLocalDate(String inputDate) {
		return LocalDate.parse(inputDate, dateTimeFormatter);
	}
	
	public static void disableSortOnTableColumn(TableColumn<?,?> tableColumn) {
		tableColumn.setSortable(false);
	}
	
	public static String toCapitalizeWord(String input) {
		// stores each characters to a char array
		char[] charArray = input.toLowerCase().toCharArray();
		boolean foundSpace = true;

		for(int i = 0; i < charArray.length; i++) {
			// if the array element is a letter
			if(Character.isLetter(charArray[i])) {
				// check space is present before the letter
				if(foundSpace) {
					// change the letter into uppercase
					charArray[i] = Character.toUpperCase(charArray[i]);
					foundSpace = false;
				}
			}
			else {
				// if the new character is not character
				foundSpace = true;
			}
		}

		// convert the char array to the string
		return String.valueOf(charArray);
	}
}
