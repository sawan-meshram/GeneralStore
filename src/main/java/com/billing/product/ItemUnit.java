package com.billing.product;

public enum ItemUnit {
	BOTTLE ("BTL"),
	BOX ("BOX"),
	BUNCH ("BUNCH"),
	BUNDLE ("BDL"),
	GRAM ("GM"),
	KG ("KG"),
	LITRE ("LIT"),
	MILLILITRE ("MIL"),
	PACK ("PK"),
	PACKET ("PKT"),
	PIECE ("PC"),
	ROLLS ("RL"),
	UNIT ("UN");

	private final String unit;
	
	ItemUnit(String unit){
		this.unit = unit;
	}
	
	public String getItemUnit() {
		return unit;
	}
}
