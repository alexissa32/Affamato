package affamato;

import org.json.JSONArray;

public class ingredientSearch implements SearchInterface{

	public String search;
	public ingredientSearch(String search) {
		this.search = search;
	}
	@Override
	public JSONArray search() {
		
		return Ingredient.searchIngredient(search);
	}

}
