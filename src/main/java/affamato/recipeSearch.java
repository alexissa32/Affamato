package affamato;

import org.json.JSONArray;

public class recipeSearch implements SearchInterface{

	String search;
	FilterParameters fp;
	Cook cook;
	
	public recipeSearch(String search, FilterParameters filterParameters, Cook cook) {
		this.search = search;
		this.fp = filterParameters;
		this.cook = cook;
		
	}
	
	@Override
	public JSONArray search() {

		return Recipe.searchRecipe(search, fp, cook);
	}

}
