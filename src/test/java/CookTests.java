import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.appengine.api.users.User;

import affamato.Cook;

@RunWith(Suite.class)
@SuiteClasses({})
public class CookTests {

	String ingredientString1 = "";
	String ingredientString2 = "";
	String recipeString = "";

	
	@Test
	public void addToGroceryListTest() {
		User me = new User("me@gmail.com", "pizza.com");
		Cook c = new Cook(me, "Cookholder"); 
		
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

