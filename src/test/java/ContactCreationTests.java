import group.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    login("admin", "secret");
  }

  private void login(String user, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(user);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() throws Exception {
    goToCreateContactPage();
    fillContactForm(new ContactData("Anton", "Arteev", "test@test.com", "https://github.com/anton6263"));
    submitContactCreation();
    goToHomePage();
    logout();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private void goToHomePage() {
    wd.findElement(By.linkText("home")).click();
    wd.get("http://localhost/addressbook/");
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.firstname());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.lastname());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactData.email());
    wd.findElement(By.name("homepage")).click();
    wd.findElement(By.name("homepage")).clear();
    wd.findElement(By.name("homepage")).sendKeys(contactData.homepage());
  }

  private void goToCreateContactPage() {
    wd.findElement(By.linkText("add new")).click();
    wd.get("http://localhost/addressbook/edit.php");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
