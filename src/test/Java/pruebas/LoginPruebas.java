package pruebas;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPruebas {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoginPruebas() throws Exception {
    driver.get("https://avengerslive1.herokuapp.com/login.htm");
    driver.findElement(By.name("inputEmail")).click();
    driver.findElement(By.name("inputEmail")).click();
    driver.findElement(By.name("inputEmail")).click();
    driver.findElement(By.name("inputEmail")).click();
    driver.findElement(By.name("inputPassword")).click();
    driver.findElement(By.name("inputPassword")).click();
    driver.findElement(By.name("inputPassword")).clear();
    driver.findElement(By.name("inputPassword")).sendKeys("Lydia12");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::input[2]")).click();
    driver.findElement(By.name("inputEmail")).clear();
    driver.findElement(By.name("inputEmail")).sendKeys("lydia.prado@alu.uclm.es");
    driver.findElement(By.name("inputPassword")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::input[2]")).click();
    driver.findElement(By.linkText("Salir")).click();
    driver.findElement(By.name("inputEmail")).clear();
    driver.findElement(By.name("inputEmail")).sendKeys("lydia.prado@alu.uclm.es");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

