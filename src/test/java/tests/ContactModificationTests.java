package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    // Цикл на создание контакта если нет ни одного
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) /* Если список контактов ДО пустой */ {
            app.contact().create(new ContactData()
                    .withFirstname("Anton").withLastname("Arteev").withEmail("test@test.com").withHomepage("https://github.com/anton6263"));
        }
    }

    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Anton").withLastname("Arteev").withEmail("test@test.com").withHomepage("https://github.com/anton6263");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        // Удаляем последний элемент
        before.remove(index);

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
