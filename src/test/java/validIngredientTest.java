

import static org.junit.Assert.*;

import org.junit.Test;

import affamato.validIngredient;

public class validIngredientTest {

	@Test
	public void testQuantity() {
		assertTrue(validIngredient.quantity("20"));
	}
	
	@Test
	public void testQuantity2() {
		assertFalse(validIngredient.quantity(""));
	}
	
	@Test 
	public void testQuantity3() {
		assertTrue(validIngredient.quantity("20,000"));
	}
	
	@Test
	public void testQuantity4() {
		assertTrue(validIngredient.quantity("1.5"));
	}
	
	@Test
	public void testQuantity5() {
		assertTrue(validIngredient.quantity("     32"));
	}
	
	@Test
	public void testQuantity6() {
		assertTrue(validIngredient.quantity("56     "));
	}
	
	@Test
	public void testQuantity7() {
		assertTrue(validIngredient.quantity("   35    "));
	}
	
	@Test
	public void testQuantity8() {
		assertFalse(validIngredient.quantity(" 3 4 "));
	}
	
	@Test
	public void testQuantity9() {
		assertFalse(validIngredient.quantity("i am dumb"));
	}
	
	@Test
	public void testQuantity10() {
		assertFalse(validIngredient.quantity("12345678901234567890"));
	}
	
	
	@Test
	public void testQuantity11() {
		assertFalse(validIngredient.quantity(" "));
	}
	
	@Test
	public void testQuantity12() {
		assertTrue(validIngredient.quantity(new Integer(Integer.MAX_VALUE).toString()));
		
	}
	
	@Test
	public void testUnits() {
		assertTrue(validIngredient.units("oz"));
	}
	
	@Test
	public void testUnits2() {
		assertFalse(validIngredient.units("oz."));
	}
	
	@Test
	public void testUnit3() {
		assertFalse(validIngredient.units(""));
	}
	
	@Test
	public void testUnit4() {
		assertFalse(validIngredient.units(" "));
	}
	
	@Test
	public void testUnits5() {
		assertTrue(validIngredient.units("     do     "));
	}
	
	@Test
	public void testUnits6() {
		assertTrue(validIngredient.units(" a y o "));
	}
	
	@Test
	public void testUnits7() {
		assertFalse(validIngredient.units(" 3 "));
	}
	
	@Test
	public void testIngredients() {
		assertFalse(validIngredient.ingredientName(""));
	}
	
	@Test
	public void testIngredients2() {
		assertFalse(validIngredient.ingredientName(" "));
	}
	
	@Test
	public void testIngredients3() {
		assertTrue(validIngredient.ingredientName(" b o b "));
	}
	
	@Test
	public void testIngredients4() {
		assertTrue(validIngredient.ingredientName("tomatoes and corn"));
	}
	
	@Test
	public void testIngredients5() {
		assertTrue(validIngredient.ingredientName("&%or^*!'}"));
	}
	
	@Test
	public void testIngredients6() {
		assertFalse(validIngredient.ingredientName("123456789012345678901"));
	}
	
	@Test
	public void testDate() {
		assertFalse(validIngredient.date("04/21/1997"));
	}
	
	@Test
	public void testDate2() {
		assertTrue(validIngredient.date("13/07/2001"));
	}
	
	@Test
	public void testDate3() {
		assertFalse(validIngredient.date("01/34/2014"));
	}
	
	@Test
	public void testDate4() {
		assertTrue(validIngredient.date("01/01/2021"));
	}
	
	@Test
	public void testDate5() {
		assertFalse(validIngredient.date(""));
	}

}
