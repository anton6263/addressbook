package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void goToGroupPage() {
      wd.findElement(By.linkText("groups")).click();
      wd.get("http://localhost/addressbook/group.php");
    }

    public void goToHomePage() {
      wd.findElement(By.linkText("home")).click();
      wd.get("http://localhost/addressbook/");
    }
}
