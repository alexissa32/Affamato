package affamato;
public class FilterParameters {

	boolean vegetarian;
	boolean glutenFree;
	boolean dairyFree;
	boolean ketogenic;
	boolean vegan;
	boolean quick;
	boolean useInventory;
	boolean useExpiring;
	
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
		this.useExpiring = expiry;
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
}
