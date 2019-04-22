import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.appengine.api.users.User;

import affamato.Cook;

public class CookTesting{
//to test you have to comment out the cookholder line in the cook constructor
	String ingredientString1="{\"id\":1001,\"original\":null,\"originalName\":null,\"name\":\"butter\",\"amount\":10.0,\"unit\":\"ounce\",\"unitShort\":\"oz\",\"unitLong\":\"ounces\"}";
	String ingredientString2="{\"id\":1011,\"original\":null,\"originalName\":null,\"name\":\"colbycheese\",\"amount\":10.0,\"unit\":\"ounce\",\"unitShort\":\"oz\",\"unitLong\":\"ounces\",\"estimatedCost\":{\"value\":377.36,\"unit\":\"USCents\"},\"consistency\":\"solid\",\"shoppingListUnits\":[\"ounces\",\"pounds\"],\"aisle\":\"Cheese\",\"image\":\"cheddar-cheese.png\",\"meta\":[],\"nutrition\":{\"nutrients\":[{\"title\":\"Calories\",\"amount\":1116.97,\"unit\":\"cal\",\"percentOfDailyNeeds\":55.85},{\"title\":\"Fat\",\"amount\":91.03,\"unit\":\"g\",\"percentOfDailyNeeds\":140.05},{\"title\":\"SaturatedFat\",\"amount\":57.32,\"unit\":\"g\",\"percentOfDailyNeeds\":358.23},{\"title\":\"Carbohydrates\",\"amount\":7.29,\"unit\":\"g\",\"percentOfDailyNeeds\":2.43},{\"title\":\"Sugar\",\"amount\":1.47,\"unit\":\"g\",\"percentOfDailyNeeds\":1.64},{\"title\":\"Cholesterol\",\"amount\":269.32,\"unit\":\"mg\",\"percentOfDailyNeeds\":89.77},{\"title\":\"Sodium\",\"amount\":1712.31,\"unit\":\"mg\",\"percentOfDailyNeeds\":74.45},{\"title\":\"Protein\",\"amount\":67.36,\"unit\":\"g\",\"percentOfDailyNeeds\":134.72},{\"title\":\"Calcium\",\"amount\":1941.94,\"unit\":\"mg\",\"percentOfDailyNeeds\":194.19},{\"title\":\"Phosphorus\",\"amount\":1295.57,\"unit\":\"mg\",\"percentOfDailyNeeds\":129.56},{\"title\":\"VitaminB2\",\"amount\":1.06,\"unit\":\"mg\",\"percentOfDailyNeeds\":62.54},{\"title\":\"Selenium\",\"amount\":41.11,\"unit\":\"\\u00b5g\",\"percentOfDailyNeeds\":58.72},{\"title\":\"Zinc\",\"amount\":8.7,\"unit\":\"mg\",\"percentOfDailyNeeds\":58.02},{\"title\":\"VitaminA\",\"amount\":2817.94,\"unit\":\"IU\",\"percentOfDailyNeeds\":56.36},{\"title\":\"VitaminB12\",\"amount\":2.35,\"unit\":\"\\u00b5g\",\"percentOfDailyNeeds\":39.22},{\"title\":\"Magnesium\",\"amount\":73.71,\"unit\":\"mg\",\"percentOfDailyNeeds\":18.43},{\"title\":\"Folate\",\"amount\":51.03,\"unit\":\"\\u00b5g\",\"percentOfDailyNeeds\":12.76},{\"title\":\"Iron\",\"amount\":2.15,\"unit\":\"mg\",\"percentOfDailyNeeds\":11.97},{\"title\":\"VitaminD\",\"amount\":1.7,\"unit\":\"\\u00b5g\",\"percentOfDailyNeeds\":11.34},{\"title\":\"VitaminB6\",\"amount\":0.22,\"unit\":\"mg\",\"percentOfDailyNeeds\":11.2},{\"title\":\"Potassium\",\"amount\":360.04,\"unit\":\"mg\",\"percentOfDailyNeeds\":10.29},{\"title\":\"VitaminK\",\"amount\":7.65,\"unit\":\"\\u00b5g\",\"percentOfDailyNeeds\":7.29},{\"title\":\"Copper\",\"amount\":0.12,\"unit\":\"mg\",\"percentOfDailyNeeds\":5.95},{\"title\":\"VitaminB5\",\"amount\":0.6,\"unit\":\"mg\",\"percentOfDailyNeeds\":5.95},{\"title\":\"VitaminE\",\"amount\":0.79,\"unit\":\"mg\",\"percentOfDailyNeeds\":5.29},{\"title\":\"VitaminB1\",\"amount\":0.04,\"unit\":\"mg\",\"percentOfDailyNeeds\":2.83},{\"title\":\"Manganese\",\"amount\":0.03,\"unit\":\"mg\",\"percentOfDailyNeeds\":1.7},{\"title\":\"VitaminB3\",\"amount\":0.26,\"unit\":\"mg\",\"percentOfDailyNeeds\":1.32}],\"caloricBreakdown\":{\"percentProtein\":24.1,\"percentFat\":73.29,\"percentCarbs\":2.61}}}";
	//colbycheese
	String recipeString1 = "{\"vegetarian\": false, \"vegan\": false, \"glutenFree\": true, \"dairyFree\": true, \"veryHealthy\": false, \"cheap\": false, \"veryPopular\": false, \"sustainable\": false, \"weightWatcherSmartPoints\": 0, \"gaps\": \"no\", \"lowFodmap\": false, \"ketogenic\": false, \"whole30\": true, \"preparationMinutes\": 15, \"cookingMinutes\": 15, \"sourceUrl\": \"http://www.realsimple.com/food-recipes/browse-all-recipes/halibut-spicy-squash-tomatoes-00000000006842/index.html\", \"spoonacularSourceUrl\": \"https://spoonacular.com/halibut-with-spicy-squash-and-tomatoes-3315\", \"aggregateLikes\": 70, \"spoonacularScore\": 57.0, \"healthScore\": 8.0, \"creditText\": \"Real Simple\", \"sourceName\": \"Real Simple\", \"pricePerServing\": 87.18, \"extendedIngredients\": [{\"id\": 10011693, \"aisle\": \"Canned and Jarred\", \"image\": \"tomatoes-canned.png\", \"consitency\": \"solid\", \"name\": \"canned tomatoes\", \"original\": \"1 28-oz can diced tomatoes\", \"originalString\": \"1 28-oz can diced tomatoes\", \"originalName\": \"diced tomatoes\", \"amount\": 28.0, \"unit\": \"oz\", \"meta\": [\"diced\", \"canned\"], \"metaInformation\": [\"diced\", \"canned\"], \"measures\": {\"us\": {\"amount\": 28.0, \"unitShort\": \"oz\", \"unitLong\": \"ounces\"}, \"metric\": {\"amount\": 793.787, \"unitShort\": \"g\", \"unitLong\": \"grams\"}}}, {\"id\": 11215, \"aisle\": \"Produce\", \"image\": \"garlic.jpg\", \"consitency\": \"solid\", \"name\": \"garlic\", \"original\": \"2 cloves garlic, chopped\", \"originalString\": \"2 cloves garlic, chopped\", \"originalName\": \"garlic, chopped\", \"amount\": 2.0, \"unit\": \"cloves\", \"meta\": [\"chopped\"], \"metaInformation\": [\"chopped\"], \"measures\": {\"us\": {\"amount\": 2.0, \"unitShort\": \"cloves\", \"unitLong\": \"cloves\"}, \"metric\": {\"amount\": 2.0, \"unitShort\": \"cloves\", \"unitLong\": \"cloves\"}}}, {\"id\": 15036, \"aisle\": \"Seafood\", \"image\": \"halibut-fillet.jpg\", \"consitency\": \"solid\", \"name\": \"halibut fillet\", \"original\": \"4 6-oz pieces skinless halibut fillet\", \"originalString\": \"4 6-oz pieces skinless halibut fillet\", \"originalName\": \"skinless halibut fillet\", \"amount\": 24.0, \"unit\": \"oz\", \"meta\": [\"skinless\"], \"metaInformation\": [\"skinless\"], \"measures\": {\"us\": {\"amount\": 24.0, \"unitShort\": \"oz\", \"unitLong\": \"ounces\"}, \"metric\": {\"amount\": 680.389, \"unitShort\": \"g\", \"unitLong\": \"grams\"}}}, {\"id\": 11979, \"aisle\": \"Canned and Jarred;Produce;Ethnic Foods\", \"image\": \"jalapeno-pepper.png\", \"consitency\": \"solid\", \"name\": \"jalapeno\", \"original\": \"1 jalape\\u00f1o, seeded and thinly sliced\", \"originalString\": \"1 jalape\\u00f1o, seeded and thinly sliced\", \"originalName\": \"jalape\\u00f1o, seeded and thinly sliced\", \"amount\": 1.0, \"unit\": \"\", \"meta\": [\"seeded\", \"thinly sliced\"], \"metaInformation\": [\"seeded\", \"thinly sliced\"], \"measures\": {\"us\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}, \"metric\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}}}, {\"id\": 1082047, \"aisle\": \"Spices and Seasonings\", \"image\": \"salt.jpg\", \"consitency\": \"solid\", \"name\": \"kosher salt\", \"original\": \"kosher salt and black pepper\", \"originalString\": \"kosher salt and black pepper\", \"originalName\": \"kosher salt and black pepper\", \"amount\": 30.0, \"unit\": \"servings\", \"meta\": [\"black\"], \"metaInformation\": [\"black\"], \"measures\": {\"us\": {\"amount\": 30.0, \"unitShort\": \"servings\", \"unitLong\": \"servings\"}, \"metric\": {\"amount\": 30.0, \"unitShort\": \"servings\", \"unitLong\": \"servings\"}}}, {\"id\": 4053, \"aisle\": \"Oil, Vinegar, Salad Dressing\", \"image\": \"olive-oil.jpg\", \"consitency\": \"liquid\", \"name\": \"olive oil\", \"original\": \"1 Tbsp olive oil\", \"originalString\": \"1 Tbsp olive oil\", \"originalName\": \"olive oil\", \"amount\": 1.0, \"unit\": \"Tbsp\", \"meta\": [], \"metaInformation\": [], \"measures\": {\"us\": {\"amount\": 1.0, \"unitShort\": \"Tbsp\", \"unitLong\": \"Tbsp\"}, \"metric\": {\"amount\": 1.0, \"unitShort\": \"Tbsp\", \"unitLong\": \"Tbsp\"}}}, {\"id\": 10011282, \"aisle\": \"Produce\", \"image\": \"red-onion.png\", \"consitency\": \"solid\", \"name\": \"red onion\", \"original\": \"1 red onion, chopped\", \"originalString\": \"1 red onion, chopped\", \"originalName\": \"red onion, chopped\", \"amount\": 1.0, \"unit\": \"\", \"meta\": [\"red\", \"chopped\"], \"metaInformation\": [\"red\", \"chopped\"], \"measures\": {\"us\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}, \"metric\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}}}, {\"id\": 11641, \"aisle\": \"Produce\", \"image\": \"yellow-squash.jpg\", \"consitency\": \"solid\", \"name\": \"yellow squash\", \"original\": \"2 small yellow squash, cut into 1/2-inch pieces\", \"originalString\": \"2 small yellow squash, cut into 1/2-inch pieces\", \"originalName\": \"yellow squash, cut into 1/2-inch pieces\", \"amount\": 2.0, \"unit\": \"small\", \"meta\": [\"yellow\", \"cut into 1/2-inch pieces\"], \"metaInformation\": [\"yellow\", \"cut into 1/2-inch pieces\"], \"measures\": {\"us\": {\"amount\": 2.0, \"unitShort\": \"small\", \"unitLong\": \"smalls\"}, \"metric\": {\"amount\": 2.0, \"unitShort\": \"small\", \"unitLong\": \"smalls\"}}}], \"id\": 3315, \"title\": \"Halibut With Spicy Squash And Tomatoes\", \"readyInMinutes\": 32, \"servings\": 30, \"image\": \"https://spoonacular.com/recipeImages/3315-556x370.jpg\", \"imageType\": \"jpg\", \"cuisines\": [], \"dishTypes\": [\"antipasti\", \"starter\", \"snack\", \"appetizer\", \"antipasto\", \"hor d'oeuvre\"], \"diets\": [\"gluten free\", \"dairy free\", \"paleolithic\", \"primal\", \"whole 30\", \"pescatarian\"], \"occasions\": [], \"winePairing\": {\"pairedWines\": [\"pinot grigio\", \"gruener veltliner\", \"pinot noir\"], \"pairingText\": \"Halibut on the menu? Try pairing with Pinot Grigio, Gruener Veltliner, and Pinot Noir. Fish is as diverse as wine, so it's hard to pick wines that go with every fish. A crisp white wine, such as a pinot grigio or Gr\\u00fcner Veltliner, will suit any delicately flavored white fish. Meaty, strongly flavored fish such as salmon and tuna can even handle a light red wine, such as a pinot noir. You could try Tramin Pinot Grigio. Reviewers quite like it with a 4.5 out of 5 star rating and a price of about 18 dollars per bottle.\", \"productMatches\": [{\"id\": 448227, \"title\": \"Tramin Pinot Grigio\", \"description\": \"Bright yellow in color with coppery reflections and clear fruit aromas of pear, citrus, honeysuckle, tropical fruits and light spices. Firm, velvety and rich on the palate, with well-integrated acidity. A very round and satisfying wine. Recommended with fish antipasti, risotto with asparagus, omelets and pasta\", \"price\": \"$18.29\", \"imageUrl\": \"https://spoonacular.com/productImages/448227-312x231.jpg\", \"averageRating\": 0.9, \"ratingCount\": 12.0, \"score\": 0.8729729729729729, \"link\": \"https://click.linksynergy.com/deeplink?id=*QCiIS6t4gA&mid=2025&murl=https%3A%2F%2Fwww.wine.com%2Fproduct%2Ftramin-pinot-grigio-2009%2F106454\"}]}, \"instructions\": \"Directions            Heat the oil in a large skillet over medium heat. Add the onion and cook, stirring occasionally, until soft, 6 to 8 minutes.Add the squash, garlic, jalapeo,  teaspoon salt, and  teaspoon pepper. Cook, stirring occasionally, until the squash begins to soften, 3 to 4 minutes.Stir in the tomatoes and their liquid. Season the halibut with  teaspoon salt and  teaspoon pepper and nestle it among the vegetables.Cover and simmer over medium-low heat until the halibut is opaque throughout and beginning to flake, 10 to 12 minutes.\", \"analyzedInstructions\": [{\"name\": \"\", \"steps\": [{\"number\": 1, \"step\": \"Heat the oil in a large skillet over medium heat.\", \"ingredients\": [], \"equipment\": [{\"id\": 404645, \"name\": \"frying pan\", \"image\": \"pan.png\"}]}, {\"number\": 2, \"step\": \"Add the onion and cook, stirring occasionally, until soft, 6 to 8 minutes.\", \"ingredients\": [{\"id\": 11282, \"name\": \"onion\", \"image\": \"brown-onion.png\"}], \"equipment\": [], \"length\": {\"number\": 6, \"unit\": \"minutes\"}}, {\"number\": 3, \"step\": \"Add the squash, garlic, jalapeo,  teaspoon salt, and  teaspoon pepper. Cook, stirring occasionally, until the squash begins to soften, 3 to 4 minutes.Stir in the tomatoes and their liquid. Season the halibut with  teaspoon salt and  teaspoon pepper and nestle it among the vegetables.Cover and simmer over medium-low heat until the halibut is opaque throughout and beginning to flake, 10 to 12 minutes.\", \"ingredients\": [{\"id\": 15036, \"name\": \"halibut\", \"image\": \"halibut-fillet.jpg\"}, {\"id\": 11215, \"name\": \"garlic\", \"image\": \"garlic.png\"}, {\"id\": 2047, \"name\": \"salt\", \"image\": \"salt.jpg\"}], \"equipment\": [], \"length\": {\"number\": 13, \"unit\": \"minutes\"}}]}], \"creditsText\": \"Real Simple\"}";

	
	@Test
	public void addGroceryListTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		c.newGroceryList();
		c.addToGroceryList(ingredientString1, 0);
		JSONArray list = c.getGroceryList(0);
		JSONObject o = (JSONObject) list.get(0);
		assertEquals(o.get("name"), "butter");
		}
	
	@Test
	public void removeGroceryListTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		c.newGroceryList();
		c.addToGroceryList(ingredientString1, 0);
		c.newGroceryList();
		c.addToGroceryList(ingredientString2, 1);
		c.removeGroceryList(0);
		JSONArray list = c.getGroceryList(0);
		JSONObject o = (JSONObject) list.get(0);
		assertEquals(o.get("name"), "colbycheese");
	}
	
	@Test
	public void removeFromGroceryListTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		c.newGroceryList();
		c.addToGroceryList(ingredientString1, 0);
		c.newGroceryList();
		c.addToGroceryList(ingredientString2, 1);
		c.addToGroceryList(ingredientString1, 1);
		c.removeFromGroceryList(0, 1);
		JSONArray list = c.getGroceryList(0);
		JSONObject o = list.getJSONObject(0);
		assertEquals(o.getString("name"), "butter");
	}
	
	@Test
	public void addToPantryTest(){
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		c.addToPantry(ingredientString1);
		JSONArray pantry = c.getPantry();
		JSONObject o = (JSONObject) pantry.get(0);
		assertEquals(o.get("name"), "butter");
	}
	
	@Test
	public void removeFromPantryTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		c.addToPantry(ingredientString1);
		c.addToPantry(ingredientString2);
		c.removeFromPantry(0);
		JSONArray pantry = c.getPantry();
		JSONObject o = (JSONObject) pantry.get(0);
		assertEquals(o.get("name"), "colbycheese");
	}
	
	@Test
	public void addToRecipeListTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		c.addToRecipeList(recipeString1);
		JSONArray list = c.getRecipeList();
		JSONObject o = list.getJSONObject(0);
		assertEquals(o.get("title"), "Halibut With Spicy Squash And Tomatoes");
	}
	
	@Test
	public void removeFromRecipeListTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		c.addToRecipeList(recipeString1);
		
	}
	
	@Test
	public void PantrySearchResultsTest() {
		
	}
	
	@Test
	public void GrocerySearchResultsTest() {
		
	}
	
	@Test
	public void RecipeSearchResultsTest() {
		
	}
	
	@Test
	public void getCookVoidTest() {
		
	}
	
	@Test
	public void getUserTest() {
		
	}
	
}

