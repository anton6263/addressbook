package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goToCreateContactPage();
    app.fillContactForm(new ContactData("Anton", "Arteev", "test@test.com", "https://github.com/anton6263"));
    app.submitContactCreation();
    app.goToHomePage();
  }

}
