package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
      click(By.linkText("groups"));
      wd.get("http://localhost/addressbook/group.php");
    }

    public void homePage() {
      click(By.linkText("home"));
      wd.get("http://localhost/addressbook/");
    }
}
