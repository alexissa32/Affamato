package affamato;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class FilterParameters {

	boolean vegetarian;
	boolean glutenFree;
	boolean dairyFree;
	boolean ketogenic;
	boolean vegan;
	boolean quick;
	boolean useInventory;
	boolean useExpiring;
	Map<Recipe, Float> inventoryMap;
	Map<Recipe, Float> expiringMap;
	
	public FilterParameters() {
		vegetarian = false;
		glutenFree = false;
		dairyFree = false;
		ketogenic = false;
		vegan = false;
		quick = false;
		useInventory = false;
		useExpiring = false;
	}
	
	public FilterParameters(boolean veg, boolean glut, boolean dairy, boolean keto, boolean vegan, boolean quick, boolean inventory, boolean expiry) {
		this.vegetarian = veg;
		this.glutenFree = glut;
		this.dairyFree = dairy;
		this.ketogenic = keto;
		this.vegan = vegan;
		this.quick = quick;
		this.useInventory = inventory;
		if(this.useInventory) inventoryMap = new HashMap<Recipe, Float>();
		else inventoryMap = null;
		this.useExpiring = expiry;
		if(this.useExpiring) expiringMap = new HashMap<Recipe, Float>();
		else expiringMap = null;
	}
	
	public boolean valid(Recipe r) {
		if( (vegetarian == true) && (r.vegetarian == false)) return false;
		if( (glutenFree == true) && (r.glutenFree == false)) return false;
		if( (dairyFree == true) && (r.dairyFree == false)) return false;
		if( (ketogenic == true) && (r.ketogenic == false)) return false;
		if( (vegan == true) && (r.vegan == false)) return false;
		if( (quick == true) && (r.cookMinutes + r.prepMinutes > 30)) return false;
		//stuff for inventory
		//stuff for expiring
		return true;
	}

	public boolean isUsingStuff() {
		return (useInventory || useExpiring);
	}

	public void calculateInventories(Recipe r, Cook c) {
		JSONArray ingredientArrays = new JSONObject(r.jsonString).getJSONArray("extendedIngredients");
		JSONArray pantryArray = c.Pantry;
		int pantryTotal = 0;
		int matching = 0;
		for(int i = 0; i < pantryArray.length(); i++) {
			pantryTotal++;
			JSONArray pantry = pantryArray.getJSONArray(i);
			String panName = pantry.getString(0);
			for(int j = 0 ; j < ingredientArrays.length(); j++) {
				JSONObject recipeIngredient = ingredientArrays.getJSONObject(j);
				String ingName = recipeIngredient.getString("name");
				if(panName.equals(ingName)) {
					matching++;
					break;
				}
			}
		}
		
		if(pantryTotal > 0) {
			inventoryMap.put(r, new Float(matching/pantryTotal));
		}
		else {
			inventoryMap.put(r, new Float(0));
		}
		
	}

	public void calculateExpiring(Recipe r, Cook c) {
		JSONArray ingredientArrays = new JSONObject(r.jsonString).getJSONArray("extendedIngredients");
		JSONArray pantryArray = c.Pantry;
		int pantryTotal = 0;
		int matching = 0;
		for(int i = 0; i < pantryArray.length(); i++) {
			pantryTotal++;
			JSONArray pantry = pantryArray.getJSONArray(i);
			String panName = pantry.getString(0);
			for(int j = 0 ; j < ingredientArrays.length(); j++) {
				JSONObject recipeIngredient = ingredientArrays.getJSONObject(j);
				String ingName = recipeIngredient.getString("name");
				if(panName.equals(ingName)) {
					matching++;
					break;
				}
			}
		}
		
		if(pantryTotal > 0) {
			inventoryMap.put(r, new Float(matching/pantryTotal));
		}
		else {
			inventoryMap.put(r, new Float(0));
		}
		
	}
}
