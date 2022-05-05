import group.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    goToGroupPage();
    createNewGroup();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnGroupPage();
    logout();
  }

}
