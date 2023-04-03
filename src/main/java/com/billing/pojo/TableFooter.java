package com.billing.pojo;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TableFooter {
	private Label label1;
	private Label label2;
	private Label label3;
	private Label label4;
	private Label itemPriceLabel;
//	private TextField itemPriceTextField = new TextField();
	private Label label5;
	private Label label6;
	private Label totalAmountLabel;
//	private TextField totalAmountTextField = new TextField();

	
	public TableFooter() {
		super();
	}


	
	public TableFooter(Label itemPriceLabel, Label totalAmountLabel) {
		super();
		this.itemPriceLabel = itemPriceLabel;
		this.totalAmountLabel = totalAmountLabel;
	}



	public Label getLabel1() {
		return label1;
	}


	public void setLabel1(Label label1) {
		this.label1 = label1;
	}


	public Label getLabel2() {
		return label2;
	}


	public void setLabel2(Label label2) {
		this.label2 = label2;
	}


	public Label getLabel3() {
		return label3;
	}


	public void setLabel3(Label label3) {
		this.label3 = label3;
	}


	public Label getLabel4() {
		return label4;
	}


	public void setLabel4(Label label4) {
		this.label4 = label4;
	}


	public Label getItemPriceLabel() {
		return itemPriceLabel;
	}


	public void setItemPriceLabel(Label itemPriceLabel) {
		this.itemPriceLabel = itemPriceLabel;
	}


	public Label getLabel5() {
		return label5;
	}


	public void setLabel5(Label label5) {
		this.label5 = label5;
	}


	public Label getLabel6() {
		return label6;
	}


	public void setLabel6(Label label6) {
		this.label6 = label6;
	}


	public Label getTotalAmountLabel() {
		return totalAmountLabel;
	}


	public void setTotalAmountLabel(Label totalAmountLabel) {
		this.totalAmountLabel = totalAmountLabel;
	}
	
	
	
	
	
}
