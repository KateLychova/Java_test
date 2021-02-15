package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() throws Exception{
    app.getNavigationHelper().gotoHomepage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("test1", null, null, null, null, null));
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
  }


}
