package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("homepage"), contactData.getHomepage());
    }

    public void goToCreateContactPage() {
        click(By.linkText("add new"));
        wd.get("http://localhost/addressbook/edit.php");
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {

        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void editContact(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact) {
        goToCreateContactPage();
        fillContactForm(contact);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
          return false;
        }

    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.findElement(By.xpath("//td[3]")).getText();
            String lastname = element.findElement(By.xpath("//td[2]")).getText();
            ContactData contact = new ContactData(id, name, lastname, null, null );
            contacts.add(contact);
        }
        return contacts;
    }
}
