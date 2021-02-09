package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {







  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", "1111", "2222"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
    app.getContactHelper().logout();
  }




}
