package ru.stqa.pft.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() throws Exception{
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("test1", null, null, null, null, null));
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomepage();
    int after = app.getContactHelper().getContactCount();
    Assertions.assertEquals(after,before -1);
  }


}
