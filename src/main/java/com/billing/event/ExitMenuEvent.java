package com.billing.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExitMenuEvent implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
//		System.out.println("Exit");
		System.exit(0);
	}

}
