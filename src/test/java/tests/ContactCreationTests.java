package tests;

import model.ContactData;
import model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Anton").withLastname("Arteev").withEmail("test@test.com").withHomepage("https://github.com/anton6263");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
