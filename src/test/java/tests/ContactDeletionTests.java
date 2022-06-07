package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    // Цикл на создание контакта если нет ни одного
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) /* Если список контактов ДО пустой */ {
            app.contact().create(new ContactData()
                        .withFirstname("Anton").withLastname("Arteev").withEmail("test@test.com").withHomepage("https://github.com/anton6263"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index =  before.size() -1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);

        // Старый список (before) содержит на один элемент больше, чем новый (after), соответственно нам нужно удалить элемент старого списка, чтобы их сравнить
        before.remove(index);
        // Сравниваем два списка (Среда разработки сама сравнивает без цикла)
        Assert.assertEquals(before, after);

    }

}
