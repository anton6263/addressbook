import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnGroupPage();
    logout();
  }

}