import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.googlecode.objectify.annotation.Index;

import affamato.Ingredient;

@RunWith(Suite.class)
@SuiteClasses({})


public class IngredientTests {
	
	String str = "{\"id\": 1123, \"original\": null, \"originalName\": null, \"name\": \"eggs\", \"amount\": 10.0, \"unit\": \"ounce\", \"unitShort\": \"oz\", \"unitLong\": \"ounces\", \"estimatedCost\": {\"value\": 154.63, \"unit\": \"US Cents\"}, \"consistency\": \"solid\", \"aisle\": \"Milk, Eggs, Other Dairy\", \"image\": \"egg.png\", \"meta\": [], \"nutrition\": {\"nutrients\": [{\"title\": \"Calories\", \"amount\": 405.4, \"unit\": \"cal\", \"percentOfDailyNeeds\": 20.27}, {\"title\": \"Fat\", \"amount\": 26.96, \"unit\": \"g\", \"percentOfDailyNeeds\": 41.48}, {\"title\": \"Saturated Fat\", \"amount\": 8.86, \"unit\": \"g\", \"percentOfDailyNeeds\": 55.39}, {\"title\": \"Carbohydrates\", \"amount\": 2.04, \"unit\": \"g\", \"percentOfDailyNeeds\": 0.68}, {\"title\": \"Sugar\", \"amount\": 1.05, \"unit\": \"g\", \"percentOfDailyNeeds\": 1.17}, {\"title\": \"Cholesterol\", \"amount\": 1054.6, \"unit\": \"mg\", \"percentOfDailyNeeds\": 351.53}, {\"title\": \"Sodium\", \"amount\": 402.56, \"unit\": \"mg\", \"percentOfDailyNeeds\": 17.5}, {\"title\": \"Protein\", \"amount\": 35.61, \"unit\": \"g\", \"percentOfDailyNeeds\": 71.21}, {\"title\": \"Selenium\", \"amount\": 87.03, \"unit\": \"\u00b5g\", \"percentOfDailyNeeds\": 124.33}, {\"title\": \"Vitamin B2\", \"amount\": 1.3, \"unit\": \"mg\", \"percentOfDailyNeeds\": 76.21}, {\"title\": \"Phosphorus\", \"amount\": 561.32, \"unit\": \"mg\", \"percentOfDailyNeeds\": 56.13}, {\"title\": \"Vitamin B5\", \"amount\": 4.35, \"unit\": \"mg\", \"percentOfDailyNeeds\": 43.46}, {\"title\": \"Vitamin B12\", \"amount\": 2.52, \"unit\": \"\u00b5g\", \"percentOfDailyNeeds\": 42.05}, {\"title\": \"Vitamin D\", \"amount\": 5.67, \"unit\": \"\u00b5g\", \"percentOfDailyNeeds\": 37.8}, {\"title\": \"Folate\", \"amount\": 133.24, \"unit\": \"\u00b5g\", \"percentOfDailyNeeds\": 33.31}, {\"title\": \"Vitamin A\", \"amount\": 1530.87, \"unit\": \"IU\", \"percentOfDailyNeeds\": 30.62}, {\"title\": \"Iron\", \"amount\": 4.96, \"unit\": \"mg\", \"percentOfDailyNeeds\": 27.56}, {\"title\": \"Zinc\", \"amount\": 3.66, \"unit\": \"mg\", \"percentOfDailyNeeds\": 24.38}, {\"title\": \"Vitamin B6\", \"amount\": 0.48, \"unit\": \"mg\", \"percentOfDailyNeeds\": 24.1}, {\"title\": \"Vitamin E\", \"amount\": 2.98, \"unit\": \"mg\", \"percentOfDailyNeeds\": 19.84}, {\"title\": \"Calcium\", \"amount\": 158.76, \"unit\": \"mg\", \"percentOfDailyNeeds\": 15.88}, {\"title\": \"Potassium\", \"amount\": 391.22, \"unit\": \"mg\", \"percentOfDailyNeeds\": 11.18}, {\"title\": \"Copper\", \"amount\": 0.2, \"unit\": \"mg\", \"percentOfDailyNeeds\": 10.21}, {\"title\": \"Magnesium\", \"amount\": 34.02, \"unit\": \"mg\", \"percentOfDailyNeeds\": 8.5}, {\"title\": \"Vitamin B1\", \"amount\": 0.11, \"unit\": \"mg\", \"percentOfDailyNeeds\": 7.56}, {\"title\": \"Manganese\", \"amount\": 0.08, \"unit\": \"mg\", \"percentOfDailyNeeds\": 3.97}, {\"title\": \"Vitamin B3\", \"amount\": 0.21, \"unit\": \"mg\", \"percentOfDailyNeeds\": 1.06}], \"caloricBreakdown\": {\"percentProtein\": 36.22, \"percentFat\": 61.7, \"percentCarbs\": 2.08}}}";
	  @Test
	  public void nameCheck() {
	    Ingredient ing = new Ingredient(str);
	    assertEquals(ing.getName(), "eggs");
	  }
	  
	  
}
