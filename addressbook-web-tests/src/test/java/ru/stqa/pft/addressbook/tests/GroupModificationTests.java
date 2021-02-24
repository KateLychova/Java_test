package ru.stqa.pft.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{


  @Test
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1",null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() -1).getId(), "test1", "tesr2", "test3");
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "tesr2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assertions.assertEquals(after.size(),before.size());

    before.remove(before.size() -1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assertions.assertEquals(before,after);

  }
}