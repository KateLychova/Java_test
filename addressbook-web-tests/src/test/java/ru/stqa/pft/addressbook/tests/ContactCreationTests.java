package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactCreationTests extends TestBase {







  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactData("test1", null, "test3", null, null, null));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() +1);
  }




}
