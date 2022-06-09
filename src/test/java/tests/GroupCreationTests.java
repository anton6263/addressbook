package tests;

import model.GroupData;
import model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test4").withHeader("test43").withFooter("test1");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    // Список превращаем в поток -> пробегает функция сравниватель и находит максимальный индентификатор GroupData -> получаем группу с максимальным индентификатором -> получаем этот индентификатор
    // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

   /* g - параметр, который принимает группу, а в качестве результата выдает индентификатор (преобразует объект в число). Далее вычисляется максимальный индетификатор и отдается в каечстве числа */

    /*
    Сортировка необходима при использовании List (Упорядоченного списка)
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    */

  }

}
