package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {







  @Test
  public void testContactCreation() throws Exception {

    app.gotoContactPage();
    app.fillContactForm(new ContactData("test1", "test2", "test3", "test4", "1111", "2222"));
    app.submitContactCreation();
    app.returnToContactPage();
    app.logout();
  }




}
