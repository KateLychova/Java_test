package ru.stqa.pft.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();

    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }

  }



  @Test
  public void testGroupDeletion() throws Exception {


    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assertions.assertEquals(after.size(),before.size() -1);

    before.remove(deletedGroup);
    Assertions.assertEquals(before,after);
  }



}
