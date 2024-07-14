import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class Chapter4_WindowManagement {
  WebDriver driver;

  @BeforeMethod
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().minimize();
    driver.get("http://automationpractice.com/index.php");
    System.out.println("Title: " + driver.getTitle());
  }

  @Test
  public void testNewWindowTab(){
    WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
    driver.manage().window().minimize();
    newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
    System.out.println("Title: " + driver.getTitle());
  }

  @Test
  public void testWorkingInBothWindowTabs(){
    // Automatically Open & Switch To The New Window Or Tab
    driver.manage().window().minimize();
    driver.switchTo().newWindow(WindowType.TAB)
            .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    System.out.println("Title: " + driver.getTitle());

    // Work In The New Window Or Tab
    driver.findElement(By.id("email_create")).sendKeys("Selenium4@TAU.com");
    driver.findElement(By.id("SubmitCreate")).click();

    // Get The Window ID Handles
    Set<String> allWindowTabss= driver.getWindowHandles();
    Iterator<String> iterate = allWindowTabss.iterator();
    String mainFirstWindow = iterate.next();

    // Switch & Work In The Main Window Or Tab
    driver.switchTo().window(mainFirstWindow);
    driver.manage().window().minimize();
    driver.findElement(By.id("search_query_top")).sendKeys("Shirt");
    driver.findElement(By.name("submit_search")).click();
    System.out.println("Title: " + driver.getTitle());
  }
}
