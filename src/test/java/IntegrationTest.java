import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

 public class IntegrationTest {

 	@Test
	public void testGoogle() throws InterruptedException {

 		System.setProperty("webdriver.chrome.driver", 
				"C:\\chromedriver.exe");

 		WebDriver driver = new ChromeDriver();

		driver.get("https://www.affamato.xyz");
		Thread.sleep(2000);
		
 		driver.findElement(By.linkText("Log In")).click();
 		System.out.print(driver.getTitle());
 		
		Thread.sleep(2000);

 		driver.findElement(By.id("identifierId")).sendKeys("affamatotesting@gmail.com");
 		driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
 	 	
 	 	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("betterthanfunyums");
 	 	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);

 		
 		Thread.sleep(5000);
 	}
 }