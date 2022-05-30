package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        // Цикл на создание контакта если нет ни одного
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anton", "Arteev", "test@test.com", "https://github.com/anton6263"));
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Anton", "Arteev", "test@test.com", "https://github.com/anton6263");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        // Удаляем последний элемент
        before.remove(before.size() - 1);

        // Добавляем тот, который модифицорован
        before.add(contact);

        // Сравниваем два списка до и после по максимальному индентификатору
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        // Делаем множественные списки и сравниваем
        Assert.assertEquals(before, after);

    }

}
