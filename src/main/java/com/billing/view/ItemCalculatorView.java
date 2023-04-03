package com.billing.view;

import java.text.DecimalFormat;

import com.billing.event.ProductUnitInsertEvent;
import com.billing.util.AlertDialog;
import com.billing.util.ChangeCaseOnTextField;
import com.billing.util.U;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ItemCalculatorView {

	private static final double WIDTH = 750;
	private static final double HEIGHT = 400;
	
//	@Override
	public void getItemCalculator(Stage stage) {
		stage.setTitle("Item Price Calculator");

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(viewProductUnitEntryForm());
		
		Scene scene = new Scene(vbox, WIDTH, HEIGHT);

		stage.setScene(scene);
		
		stage.show();

	}

	private Pane viewProductUnitEntryForm() {
		GridPane grid = new GridPane();
		//Setting size for the pane 
//		grid.setGridLinesVisible(true);
		grid.setMaxWidth(WIDTH);
//		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(12);
		grid.setPadding(new Insets(25, 0, 25, 0));
		
		Text sceneTitle = new Text("Item Price Calculator");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		
		
		ToggleGroup groupCalc = new ToggleGroup();
		RadioButton calcQtyButton = new RadioButton("Calculate Qty.(Gram/MilliLitre)");
		calcQtyButton.setToggleGroup(groupCalc);
		calcQtyButton.setSelected(true);

		RadioButton calcPriceButton = new RadioButton("Calculate Price");
		calcPriceButton.setToggleGroup(groupCalc);
		

		Label sellingPrice = new Label("Selling Price/(KG/Litre) :");
		TextField sellingPriceTextField = new TextField();

		Label askedPrice = new Label("Asking Price :");
		TextField askedPriceTexField = new TextField();
		Label resultCalcGramOrMilliLitre = new Label("");

		Label askedGramOrMilliLitre = new Label("Asking (Gram/MilliLitre) :");
		TextField askedGramOrMilliLitreTexField = new TextField();
		askedGramOrMilliLitreTexField.setEditable(false);
		askedGramOrMilliLitreTexField.setStyle("-fx-opacity:0.5");
		Label resultCalcPrice = new Label("");

		Button btnCalc = new Button("Calculate");
		Button btnClear = new Button("Clear");
		Button btnReset = new Button("Reset");

		/**
		 * Setting GridPane
		 */
	    //set title
		grid.add(sceneTitle, 0, 0, 6, 1);

		//set Horizontal
		final Separator separatorHorizontalTop = new Separator();
		separatorHorizontalTop.setValignment(VPos.CENTER);
		grid.add(separatorHorizontalTop, 0, 1, 6, 1);
		
		
		//set selling price
		grid.add(sellingPrice, 0, 3);
		grid.add(sellingPriceTextField, 1, 3);

		//set Horizontal
		final Separator separatorHorizontalMiddle = new Separator();
		separatorHorizontalMiddle.setValignment(VPos.CENTER);
		grid.add(separatorHorizontalMiddle, 0, 5, 6, 1);
				
		//set Center Vertical
		final Separator separatorVerticalMiddle = new Separator();
		separatorVerticalMiddle.setOrientation(Orientation.VERTICAL);
		grid.add(separatorVerticalMiddle, 3, 5, 1, 12);

		//Radio Buttons
		grid.add(calcQtyButton, 0, 6, 2, 1);
		grid.add(calcPriceButton, 4, 6, 2, 1);

		//Add asked prices		
		grid.add(askedPrice, 0, 7);
		grid.add(askedPriceTexField, 1, 7);
		
		//add asked qty
		grid.add(askedGramOrMilliLitre, 4, 7);
		grid.add(askedGramOrMilliLitreTexField, 5, 7);
		
		//Result labels
		grid.add(resultCalcGramOrMilliLitre, 0, 13, 2, 1);
		GridPane.setHalignment(resultCalcGramOrMilliLitre, HPos.CENTER);

		grid.add(resultCalcPrice, 4, 13, 2, 1);
		GridPane.setHalignment(resultCalcPrice, HPos.CENTER);


		//set Horizontal
		final Separator separatorHorizontalBotton = new Separator();
		separatorHorizontalBotton.setValignment(VPos.CENTER);
		grid.add(separatorHorizontalBotton, 0, 16, 6, 1);
				
		
		//Set HBox for buttons
		HBox hboxBtn = new HBox(10);
		hboxBtn.setAlignment(Pos.BOTTOM_CENTER);
		hboxBtn.getChildren().addAll(btnClear, btnReset, btnCalc);
		//add buttons
		grid.add(hboxBtn, 0, 17, 6, 1);
		GridPane.setHalignment(hboxBtn, HPos.RIGHT);
		
		
		EventHandler<ActionEvent> calcEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				if (groupCalc.getSelectedToggle() != null) {
					RadioButton button = (RadioButton) groupCalc.getSelectedToggle();
					U.log(button.getText());
					//Calculate Price
					if(button.getText().contains("Calculate Qty")) {
						if(!sellingPriceTextField.getText().trim().isEmpty() && !askedPriceTexField.getText().trim().isEmpty()) {
							try {
								double soldPrice = Double.parseDouble(sellingPriceTextField.getText().trim());
								double askedPrice = Double.parseDouble(askedPriceTexField.getText().trim());
								
								double result = calculateGramOrMilliLitre(askedPrice, soldPrice);
								setResultLabelFiled(resultCalcGramOrMilliLitre, result, "(gm/ml)");
								
							} catch (NumberFormatException ex) {
								AlertDialog.showErrorTextAlert("Incorrect numbers", "Only numbers allows");
							}
						}//eof if
					}else {
						if(!sellingPriceTextField.getText().trim().isEmpty() && !askedGramOrMilliLitreTexField.getText().trim().isEmpty()) {
							try {
								double soldPrice = Double.parseDouble(sellingPriceTextField.getText().trim());
								double askedUnit = Double.parseDouble(askedGramOrMilliLitreTexField.getText().trim());
								
								double result = calculatePrice(askedUnit, soldPrice);
								setResultLabelFiled(resultCalcPrice, result, "Rs./-");

							} catch (NumberFormatException ex) {
								AlertDialog.showErrorTextAlert("Incorrect numbers", "Only numbers allows");
							}
						}//eof if
					}//eof else
				}
					
			}
        };

        EventHandler<ActionEvent> clearEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				sellingPriceTextField.clear();
				askedPriceTexField.clear();;
				askedGramOrMilliLitreTexField.clear();
				resultCalcGramOrMilliLitre.setText(null);
				resultCalcPrice.setText(null);
//				calcQtyButton.setSelected(true);
//				calcQtyButton.requestFocus();
			}
        };
        
        EventHandler<ActionEvent> resetEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				sellingPriceTextField.clear();
				askedPriceTexField.clear();;
				askedGramOrMilliLitreTexField.clear();
				resultCalcGramOrMilliLitre.setText(null);
				resultCalcPrice.setText(null);
				calcQtyButton.setSelected(true);
				calcQtyButton.requestFocus();
			}
        };
        
        
        btnCalc.setOnAction(calcEvent);
		btnClear.setOnAction(clearEvent);
		btnReset.setOnAction(resetEvent);
		
		ChangeListener<Toggle> radioEvent = new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		        if (groupCalc.getSelectedToggle() != null) {
		        	
					RadioButton button = (RadioButton) groupCalc.getSelectedToggle();
//					U.log(button.getText());

					if(button.getText().contains("Calculate Price")) {
						//Calculate Price
						resultCalcGramOrMilliLitre.setText(null);
						askedPriceTexField.setEditable(false);
						askedPriceTexField.setStyle("-fx-opacity:0.5");

						askedPriceTexField.clear();
						askedGramOrMilliLitreTexField.setEditable(true);
						askedGramOrMilliLitreTexField.setStyle("-fx-opacity:1");


					}else {
						//Calculate Qty.(Gram/MilliLitre)
						resultCalcPrice.setText(null);
						askedPriceTexField.setEditable(true);
						askedPriceTexField.setStyle("-fx-opacity:1");
						
						askedGramOrMilliLitreTexField.setEditable(false);
						askedGramOrMilliLitreTexField.setStyle("-fx-opacity:0.5");

						askedGramOrMilliLitreTexField.clear();
						
					}
		        }
			}
		};
		groupCalc.selectedToggleProperty().addListener(radioEvent);
		return grid;
	}
	
	private static void setResultLabelFiled(Label resultLabel, double result, String resultUnit) {
		resultLabel.setText("Result :: "+decimalFormat.format(result)+" "+resultUnit); 
		resultLabel.setTextFill(Color.GREEN);
		resultLabel.setStyle("-fx-font: normal bold 18px 'serif';");
	}
	private static DecimalFormat decimalFormat = new DecimalFormat("#.###");
	
	private static double calculateGramOrMilliLitre(double askedPrice, double soldPrice) {
		return (askedPrice/soldPrice) * 1000;
	}
	
	private static double calculatePrice(double askedUnit, double soldPrice) {
		return (askedUnit/1000) * soldPrice;
	}
	
}
