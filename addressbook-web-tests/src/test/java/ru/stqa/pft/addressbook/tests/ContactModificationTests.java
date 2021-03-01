package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if( app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstname("test1").withLastname("test2"));
    }

  }


  @Test
  public void testContactModification() throws Exception{



    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("test1").withMiddlename("test3").withLastname("test2").withAdress("test4").withHomenumber("test5").withMobilenumber("test6");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }




}
