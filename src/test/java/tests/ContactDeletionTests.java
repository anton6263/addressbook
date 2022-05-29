package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Arteev", "test@test.com", "https://github.com/anton6263"));
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        // Старый список (before) содержит на один элемент больше, чем новый (after), соответственно нам нужно удалить элемент старого списка, чтобы их сравнить
        before.remove(before.size() - 1);
        // Сравниваем два списка (Среда разработки сама сравнивает без цикла)
        Assert.assertEquals(before, after);

    }
}
