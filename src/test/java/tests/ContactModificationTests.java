package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Arteev", "test@test.com", "https://github.com/anton6263"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Anton", "Arteev", "test@test.com", "https://github.com/anton6263"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
    }

}
