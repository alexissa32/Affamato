package affamato;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

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
	
	public boolean isUsingBoth() {
		return (useInventory && useExpiring);
	}

	static final int DATE = 1;
	static final int NAME = 0;
	public void calculateInventories(Recipe r, Cook c) {
		JSONArray ingredientArrays = new JSONObject(r.jsonString).getJSONArray("extendedIngredients");
		JSONArray pantryArray = c.getPantry();
		int pantryTotal = 0;
		int matching = 0;
		for(int i = 0; i < pantryArray.length(); i++) {
			pantryTotal++;
			JSONArray pantry = pantryArray.getJSONArray(i);
			String panName = pantry.getString(NAME);
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
		JSONArray pantryArray = c.getPantry();
		int pantryTotal = 0;
		int matching = 0;
		long today = Date.parse(new Date().toString());
		for(int i = 0; i < pantryArray.length(); i++) {
			pantryTotal++;
			JSONArray pantry = pantryArray.getJSONArray(i);
			String panName = pantry.getString(NAME);
			long exp = Date.parse(pantryArray.getString(DATE));
			if( ( (exp - today) / 86400000 ) <= 3) {
				for(int j = 0 ; j < ingredientArrays.length(); j++) {
					JSONObject recipeIngredient = ingredientArrays.getJSONObject(j);
					String ingName = recipeIngredient.getString("name");
					if(panName.equals(ingName)) {
						matching++;
						break;
					}
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
	
	public Map<Recipe, Float> sortAndReturn(){
		List<Recipe> returner = new ArrayList<Recipe>();
		Queue<Recipe> q = new PriorityQueue();
		Map<Recipe, Float> myMap = null;
		if(this.isUsingBoth()) {
			myMap = new HashMap<Recipe, Float>();
			for(Recipe r : inventoryMap.keySet()) {
				if(expiringMap.containsKey(r)) {
					myMap.put(r, inventoryMap.get(r) + expiringMap.get(r));
				}
				//could also put in all of the sums if i wanted
			}
		}
		else if(this.useExpiring) {
			myMap = expiringMap;
		}
		else if(this.useInventory){
			myMap = inventoryMap;
		}
		Map <Recipe, Float> sorted = myMap
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2 ));
		return sorted;
	}
}
