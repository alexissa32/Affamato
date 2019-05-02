package affamato;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class validIngredient {

	public static boolean quantity(String quantity) {
		if(quantity.length() > 20 || quantity.length() == 0) return false;
		boolean b = true;
		quantity = quantity.trim();
		quantity = quantity.replaceAll(",", "");
		try {
			if(quantity.contains(".")) {
				Double.parseDouble(quantity);
			} 
			else {
				Integer.parseInt(quantity);
			}
		}
		catch(NumberFormatException e) {
			b = false;
		}
		return b;
	}
	
	public static boolean units(String units) {
		if(units.length() > 20 || units.length() == 0) return false;
		units = units.replaceAll("\\s+","");
		return units.matches("[a-zA-Z]+");
	} 
	
	public static boolean ingredientName(String name) {
		if(name.length() > 20) return false;
		name = name.replaceAll("\\s+","");
		if(name.length() == 0) return false;
		return true;
		
	}
	
	public static boolean date(String date) {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch(ParseException e) {
			return false;
		}
	}
	
	public static boolean validate(String quantity, String units, String ingredient, String date) {
		return ( quantity(quantity) && units(units) && ingredientName(ingredient) && date(date) );
	}
	
}
