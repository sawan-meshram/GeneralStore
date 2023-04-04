package com.billing.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductUtil {
	public static List<String> initProductTypes = new ArrayList<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add("Accompaniments");
			add("Anything Else");
			add("Baking & Dessert Preparation");
			add("Beverages");
			add("Bread / Poultry / Cereals / Snacks");
			add("Cleaning / Bathroom Products");
			add("Dairy / Frozen Food");
			add("Flours / Atta / Semolina / Rava / Processed Grains");
			add("Ground Spices / Masalas");
			add("Nuts, Dry Fruits & Other Items");
			add("Oil/ Ghee");
			add("Rice / Grains / Pulses");
			add("Salt / Sugar / Spices");
			add("Sweets / Dry Fruits");
		}
	};
	
	public static Map<String, String> initProductUnit = new HashMap<String, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("BOTTLE", "BTL");
			put("BOX","BOX");
			put("BUNCH", "BUNCH");
			put("BUNDLE", "BDL");
			put("GRAM", "GM");
			put("KG", "KG");
			put("LITRE", "LIT");
			put("MILLILITRE", "MIL");
			put("PACK", "PK");
			put("PACKET", "PKT");
			put("PIECE", "PC");
			put("ROLLS", "RL");
			put("UNIT", "UN");
		}
	};
}
