package appmanager;

import model.ContactData;
import model.Contacts;
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

    public void goToHomePage() {
        click(By.linkText("home"));
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

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void editContactById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        goToCreateContactPage();
        fillContactForm(contact);
        submitContactCreation();
        goToHomePage();
    }

    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact);
        updateContact();
        goToHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
        acceptAlert();
        goToHomePage();
    }

    public void delete (ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        goToHomePage();
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

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(name).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(name).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }

}
