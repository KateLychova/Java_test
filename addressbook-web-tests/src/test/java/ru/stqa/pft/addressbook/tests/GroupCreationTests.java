package ru.stqa.pft.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);

    Set<GroupData> after = app.group().all();
    Assertions.assertEquals(after.size(),before.size() + 1);


    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assertions.assertEquals(before,after);
  }


}
