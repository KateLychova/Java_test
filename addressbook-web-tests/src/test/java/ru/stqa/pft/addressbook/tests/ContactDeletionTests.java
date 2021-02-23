package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() throws Exception{


    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("test1", "test2", null, null, null, null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteSelectedContact();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size()  );

  }


}
