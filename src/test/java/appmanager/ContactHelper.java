package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstname());
        type(By.name("lastname"), contactData.lastname());
        type(By.name("email"), contactData.email());
        type(By.name("homepage"), contactData.homepage());
    }

    public void goToCreateContactPage() {
        click(By.linkText("add new"));
        wd.get("http://localhost/addressbook/edit.php");
    }
}
