package com.billing.product;

public enum ItemType {
	ACCOMPANIMENTS ("Accompaniments"),
	ANYTHING_ELSE ("Anything Else"),
	BAKING_AND_DESSERT_PREPARATION ("Baking & Dessert Preparation"),
	BEVERAGES ("Beverages"),
	BREAD_OR_POULTRY_OR_CEREALS_OR_SNACKS ("Bread / Poultry / Cereals / Snacks"),
	CLEANIN_OR_BATHROOM_PRODUCTS ("Cleaning / Bathroom Products"),
	DAIRY_OR_FROZEN_FOOD ("Dairy / Frozen Food"),
	FLOURS_OR_ATTA_OR_SEMOLINA_OR_RAVA_OR_PROCESSED_GRAINS ("Flours / Atta / Semolina / Rava / Processed Grains"),
	GROUND_SPICES_OR_MASALAS ("Ground Spices / Masalas"),
	NUTS_AND_DRY_FRUITS_AND_OTHER_ITEMS ("Nuts, Dry Fruits & Other Items"),
	OIL_OR_GHEE ("Oil/ Ghee"),
	RICE_OR_GRAINS_OR_PULSES ("Rice / Grains / Pulses"),
	SALT_OR_SUGAR_OR_SPICES ("Salt / Sugar / Spices"),
	SWEETS_OR_DRY_FRUITS ("Sweets / Dry Fruits");

	private final String type;
	ItemType(String type){
		this.type = type;
	}
	
	public String getItemType() {
		return type;
	}
	
}
