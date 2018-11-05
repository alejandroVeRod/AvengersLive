package webDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestCase;

public class SeleniumStartTest extends TestCase {
	protected WebDriver driver;
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		if (driver != null) {
            driver.quit();
        }
		
	}

	@Test
	public void test() {
		driver.get("https://avengerslive.herokuapp.com/login.htm");
	}

}