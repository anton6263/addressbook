package tests;

import model.ContactData;
import model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            if (app.db().contacts().size() == 0) {
                app.contact().create(new ContactData()
                        .withFirstname("Anton").withLastname("Arteev").withEmail("test@test.com").withAddress("address").withHomePhone("+79433333"));
            }
        }
    }

    @Test
    public void testAddContactInGroup() {
        Set<ContactData> contactsWithoutGroup = app.contact().all();
        ContactData contactNoneGroup = contactsWithoutGroup.iterator().next();
        int id = contactNoneGroup.getId();
        ContactData contact = app.db().contactsInGroup(id);
        GroupData group = app.db().groups().iterator().next();
        app.contact().addToGroup(contact, group);
        assertThat(app.db().contactsInGroup(contact.getId()).getGroups().contains(group), equalTo(false));
        verifyContactListInUi();
    }
}
