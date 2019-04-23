import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.appengine.api.users.User;

import affamato.Cook;

@RunWith(Suite.class)
@SuiteClasses({})
public class CookTests {

	String ingredientString1 = "{\"id\": 1001, \"original\": null, \"originalName\": null, \"name\": \"butter\", \"amount\": 10.0, \"unit\": \"ounce\", \"unitShort\": \"oz\", \"unitLong\": \"ounces\", \"estimatedCost\": {\"value\": 243.0, \"unit\": \"US Cents\"}, \"consistency\": \"solid\", \"shoppingListUnits\": [\"ounces\", \"pounds\"], \"aisle\": \"Milk, Eggs, Other Dairy\", \"image\": \"butter-sliced.jpg\", \"meta\": [], \"nutrition\": {\"nutrients\": [{\"title\": \"Calories\", \"amount\": 2032.66, \"unit\": \"cal\", \"percentOfDailyNeeds\": 101.63}, {\"title\": \"Fat\", \"amount\": 229.94, \"unit\": \"g\", \"percentOfDailyNeeds\": 353.76}, {\"title\": \"Saturated Fat\", \"amount\": 145.63, \"unit\": \"g\", \"percentOfDailyNeeds\": 910.16}, {\"title\": \"Carbohydrates\", \"amount\": 0.17, \"unit\": \"g\", \"percentOfDailyNeeds\": 0.06}, {\"title\": \"Sugar\", \"amount\": 0.17, \"unit\": \"g\", \"percentOfDailyNeeds\": 0.19}, {\"title\": \"Cholesterol\", \"amount\": 609.51, \"unit\": \"mg\", \"percentOfDailyNeeds\": 203.17}, {\"title\": \"Sodium\", \"amount\": 2024.15, \"unit\": \"mg\", \"percentOfDailyNeeds\": 88.01}, {\"title\": \"Protein\", \"amount\": 2.41, \"unit\": \"g\", \"percentOfDailyNeeds\": 4.82}, {\"title\": \"Vitamin A\", \"amount\": 7084.54, \"unit\": \"IU\", \"percentOfDailyNeeds\": 141.69}, {\"title\": \"Vitamin E\", \"amount\": 6.58, \"unit\": \"mg\", \"percentOfDailyNeeds\": 43.85}, {\"title\": \"Vitamin D\", \"amount\": 4.25, \"unit\": \"\\u00b5g\", \"percentOfDailyNeeds\": 28.35}, {\"title\": \"Vitamin K\", \"amount\": 19.84, \"unit\": \"\\u00b5g\", \"percentOfDailyNeeds\": 18.9}, {\"title\": \"Vitamin B12\", \"amount\": 0.48, \"unit\": \"\\u00b5g\", \"percentOfDailyNeeds\": 8.03}, {\"title\": \"Calcium\", \"amount\": 68.04, \"unit\": \"mg\", \"percentOfDailyNeeds\": 6.8}, {\"title\": \"Phosphorus\", \"amount\": 68.04, \"unit\": \"mg\", \"percentOfDailyNeeds\": 6.8}, {\"title\": \"Vitamin B2\", \"amount\": 0.1, \"unit\": \"mg\", \"percentOfDailyNeeds\": 5.67}, {\"title\": \"Selenium\", \"amount\": 2.84, \"unit\": \"\\u00b5g\", \"percentOfDailyNeeds\": 4.05}, {\"title\": \"Vitamin B5\", \"amount\": 0.31, \"unit\": \"mg\", \"percentOfDailyNeeds\": 3.12}, {\"title\": \"Folate\", \"amount\": 8.5, \"unit\": \"\\u00b5g\", \"percentOfDailyNeeds\": 2.13}, {\"title\": \"Potassium\", \"amount\": 68.04, \"unit\": \"mg\", \"percentOfDailyNeeds\": 1.94}, {\"title\": \"Zinc\", \"amount\": 0.26, \"unit\": \"mg\", \"percentOfDailyNeeds\": 1.7}, {\"title\": \"Magnesium\", \"amount\": 5.67, \"unit\": \"mg\", \"percentOfDailyNeeds\": 1.42}], \"caloricBreakdown\": {\"percentProtein\": 0.46, \"percentFat\": 99.5, \"percentCarbs\": 0.04}}}";
	String ingredientString2 = "";
	String recipeString = "{\"vegetarian\": false, \"vegan\": false, \"glutenFree\": true, \"dairyFree\": true, \"veryHealthy\": false, \"cheap\": false, \"veryPopular\": false, \"sustainable\": false, \"weightWatcherSmartPoints\": 0, \"gaps\": \"no\", \"lowFodmap\": false, \"ketogenic\": false, \"whole30\": true, \"preparationMinutes\": 15, \"cookingMinutes\": 15, \"sourceUrl\": \"http://www.realsimple.com/food-recipes/browse-all-recipes/halibut-spicy-squash-tomatoes-00000000006842/index.html\", \"spoonacularSourceUrl\": \"https://spoonacular.com/halibut-with-spicy-squash-and-tomatoes-3315\", \"aggregateLikes\": 70, \"spoonacularScore\": 57.0, \"healthScore\": 8.0, \"creditText\": \"Real Simple\", \"sourceName\": \"Real Simple\", \"pricePerServing\": 87.18, \"extendedIngredients\": [{\"id\": 10011693, \"aisle\": \"Canned and Jarred\", \"image\": \"tomatoes-canned.png\", \"consitency\": \"solid\", \"name\": \"canned tomatoes\", \"original\": \"1 28-oz can diced tomatoes\", \"originalString\": \"1 28-oz can diced tomatoes\", \"originalName\": \"diced tomatoes\", \"amount\": 28.0, \"unit\": \"oz\", \"meta\": [\"diced\", \"canned\"], \"metaInformation\": [\"diced\", \"canned\"], \"measures\": {\"us\": {\"amount\": 28.0, \"unitShort\": \"oz\", \"unitLong\": \"ounces\"}, \"metric\": {\"amount\": 793.787, \"unitShort\": \"g\", \"unitLong\": \"grams\"}}}, {\"id\": 11215, \"aisle\": \"Produce\", \"image\": \"garlic.jpg\", \"consitency\": \"solid\", \"name\": \"garlic\", \"original\": \"2 cloves garlic, chopped\", \"originalString\": \"2 cloves garlic, chopped\", \"originalName\": \"garlic, chopped\", \"amount\": 2.0, \"unit\": \"cloves\", \"meta\": [\"chopped\"], \"metaInformation\": [\"chopped\"], \"measures\": {\"us\": {\"amount\": 2.0, \"unitShort\": \"cloves\", \"unitLong\": \"cloves\"}, \"metric\": {\"amount\": 2.0, \"unitShort\": \"cloves\", \"unitLong\": \"cloves\"}}}, {\"id\": 15036, \"aisle\": \"Seafood\", \"image\": \"halibut-fillet.jpg\", \"consitency\": \"solid\", \"name\": \"halibut fillet\", \"original\": \"4 6-oz pieces skinless halibut fillet\", \"originalString\": \"4 6-oz pieces skinless halibut fillet\", \"originalName\": \"skinless halibut fillet\", \"amount\": 24.0, \"unit\": \"oz\", \"meta\": [\"skinless\"], \"metaInformation\": [\"skinless\"], \"measures\": {\"us\": {\"amount\": 24.0, \"unitShort\": \"oz\", \"unitLong\": \"ounces\"}, \"metric\": {\"amount\": 680.389, \"unitShort\": \"g\", \"unitLong\": \"grams\"}}}, {\"id\": 11979, \"aisle\": \"Canned and Jarred;Produce;Ethnic Foods\", \"image\": \"jalapeno-pepper.png\", \"consitency\": \"solid\", \"name\": \"jalapeno\", \"original\": \"1 jalape\\u00f1o, seeded and thinly sliced\", \"originalString\": \"1 jalape\\u00f1o, seeded and thinly sliced\", \"originalName\": \"jalape\\u00f1o, seeded and thinly sliced\", \"amount\": 1.0, \"unit\": \"\", \"meta\": [\"seeded\", \"thinly sliced\"], \"metaInformation\": [\"seeded\", \"thinly sliced\"], \"measures\": {\"us\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}, \"metric\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}}}, {\"id\": 1082047, \"aisle\": \"Spices and Seasonings\", \"image\": \"salt.jpg\", \"consitency\": \"solid\", \"name\": \"kosher salt\", \"original\": \"kosher salt and black pepper\", \"originalString\": \"kosher salt and black pepper\", \"originalName\": \"kosher salt and black pepper\", \"amount\": 30.0, \"unit\": \"servings\", \"meta\": [\"black\"], \"metaInformation\": [\"black\"], \"measures\": {\"us\": {\"amount\": 30.0, \"unitShort\": \"servings\", \"unitLong\": \"servings\"}, \"metric\": {\"amount\": 30.0, \"unitShort\": \"servings\", \"unitLong\": \"servings\"}}}, {\"id\": 4053, \"aisle\": \"Oil, Vinegar, Salad Dressing\", \"image\": \"olive-oil.jpg\", \"consitency\": \"liquid\", \"name\": \"olive oil\", \"original\": \"1 Tbsp olive oil\", \"originalString\": \"1 Tbsp olive oil\", \"originalName\": \"olive oil\", \"amount\": 1.0, \"unit\": \"Tbsp\", \"meta\": [], \"metaInformation\": [], \"measures\": {\"us\": {\"amount\": 1.0, \"unitShort\": \"Tbsp\", \"unitLong\": \"Tbsp\"}, \"metric\": {\"amount\": 1.0, \"unitShort\": \"Tbsp\", \"unitLong\": \"Tbsp\"}}}, {\"id\": 10011282, \"aisle\": \"Produce\", \"image\": \"red-onion.png\", \"consitency\": \"solid\", \"name\": \"red onion\", \"original\": \"1 red onion, chopped\", \"originalString\": \"1 red onion, chopped\", \"originalName\": \"red onion, chopped\", \"amount\": 1.0, \"unit\": \"\", \"meta\": [\"red\", \"chopped\"], \"metaInformation\": [\"red\", \"chopped\"], \"measures\": {\"us\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}, \"metric\": {\"amount\": 1.0, \"unitShort\": \"\", \"unitLong\": \"\"}}}, {\"id\": 11641, \"aisle\": \"Produce\", \"image\": \"yellow-squash.jpg\", \"consitency\": \"solid\", \"name\": \"yellow squash\", \"original\": \"2 small yellow squash, cut into 1/2-inch pieces\", \"originalString\": \"2 small yellow squash, cut into 1/2-inch pieces\", \"originalName\": \"yellow squash, cut into 1/2-inch pieces\", \"amount\": 2.0, \"unit\": \"small\", \"meta\": [\"yellow\", \"cut into 1/2-inch pieces\"], \"metaInformation\": [\"yellow\", \"cut into 1/2-inch pieces\"], \"measures\": {\"us\": {\"amount\": 2.0, \"unitShort\": \"small\", \"unitLong\": \"smalls\"}, \"metric\": {\"amount\": 2.0, \"unitShort\": \"small\", \"unitLong\": \"smalls\"}}}], \"id\": 3315, \"title\": \"Halibut With Spicy Squash And Tomatoes\", \"readyInMinutes\": 32, \"servings\": 30, \"image\": \"https://spoonacular.com/recipeImages/3315-556x370.jpg\", \"imageType\": \"jpg\", \"cuisines\": [], \"dishTypes\": [\"antipasti\", \"starter\", \"snack\", \"appetizer\", \"antipasto\", \"hor d'oeuvre\"], \"diets\": [\"gluten free\", \"dairy free\", \"paleolithic\", \"primal\", \"whole 30\", \"pescatarian\"], \"occasions\": [], \"winePairing\": {\"pairedWines\": [\"pinot grigio\", \"gruener veltliner\", \"pinot noir\"], \"pairingText\": \"Halibut on the menu? Try pairing with Pinot Grigio, Gruener Veltliner, and Pinot Noir. Fish is as diverse as wine, so it's hard to pick wines that go with every fish. A crisp white wine, such as a pinot grigio or Gr\\u00fcner Veltliner, will suit any delicately flavored white fish. Meaty, strongly flavored fish such as salmon and tuna can even handle a light red wine, such as a pinot noir. You could try Tramin Pinot Grigio. Reviewers quite like it with a 4.5 out of 5 star rating and a price of about 18 dollars per bottle.\", \"productMatches\": [{\"id\": 448227, \"title\": \"Tramin Pinot Grigio\", \"description\": \"Bright yellow in color with coppery reflections and clear fruit aromas of pear, citrus, honeysuckle, tropical fruits and light spices. Firm, velvety and rich on the palate, with well-integrated acidity. A very round and satisfying wine. Recommended with fish antipasti, risotto with asparagus, omelets and pasta\", \"price\": \"$18.29\", \"imageUrl\": \"https://spoonacular.com/productImages/448227-312x231.jpg\", \"averageRating\": 0.9, \"ratingCount\": 12.0, \"score\": 0.8729729729729729, \"link\": \"https://click.linksynergy.com/deeplink?id=*QCiIS6t4gA&mid=2025&murl=https%3A%2F%2Fwww.wine.com%2Fproduct%2Ftramin-pinot-grigio-2009%2F106454\"}]}, \"instructions\": \"Directions            Heat the oil in a large skillet over medium heat. Add the onion and cook, stirring occasionally, until soft, 6 to 8 minutes.Add the squash, garlic, jalapeo,  teaspoon salt, and  teaspoon pepper. Cook, stirring occasionally, until the squash begins to soften, 3 to 4 minutes.Stir in the tomatoes and their liquid. Season the halibut with  teaspoon salt and  teaspoon pepper and nestle it among the vegetables.Cover and simmer over medium-low heat until the halibut is opaque throughout and beginning to flake, 10 to 12 minutes.\", \"analyzedInstructions\": [{\"name\": \"\", \"steps\": [{\"number\": 1, \"step\": \"Heat the oil in a large skillet over medium heat.\", \"ingredients\": [], \"equipment\": [{\"id\": 404645, \"name\": \"frying pan\", \"image\": \"pan.png\"}]}, {\"number\": 2, \"step\": \"Add the onion and cook, stirring occasionally, until soft, 6 to 8 minutes.\", \"ingredients\": [{\"id\": 11282, \"name\": \"onion\", \"image\": \"brown-onion.png\"}], \"equipment\": [], \"length\": {\"number\": 6, \"unit\": \"minutes\"}}, {\"number\": 3, \"step\": \"Add the squash, garlic, jalapeo,  teaspoon salt, and  teaspoon pepper. Cook, stirring occasionally, until the squash begins to soften, 3 to 4 minutes.Stir in the tomatoes and their liquid. Season the halibut with  teaspoon salt and  teaspoon pepper and nestle it among the vegetables.Cover and simmer over medium-low heat until the halibut is opaque throughout and beginning to flake, 10 to 12 minutes.\", \"ingredients\": [{\"id\": 15036, \"name\": \"halibut\", \"image\": \"halibut-fillet.jpg\"}, {\"id\": 11215, \"name\": \"garlic\", \"image\": \"garlic.png\"}, {\"id\": 2047, \"name\": \"salt\", \"image\": \"salt.jpg\"}], \"equipment\": [], \"length\": {\"number\": 13, \"unit\": \"minutes\"}}]}], \"creditsText\": \"Real Simple\"}";

	
	@Test
	public void addToGroceryListTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder");
		//c.addToGroceryList(ID, index);
		//c.addToGroce(ID);
		
	}
	
	@Test
	public void removeGroceryListTest() {
		
	}
	
	@Test
	public void removeFromGroceryListTest() {
		
	}
	
	@Test
	public void addToPantryTest(){
		
	}
	
	@Test
	public void removeFromPantryTest() {
		
	}
	
	@Test
	public void addToRecipeListTest() {
		
	}
	
	@Test
	public void removeFromRecipeListTest() {
		
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

