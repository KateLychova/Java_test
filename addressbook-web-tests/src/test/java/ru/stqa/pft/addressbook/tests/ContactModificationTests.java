package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if( app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstname("test1").withLastname("test2"));
    }

  }


  @Test
  public void testContactModification() throws Exception{



    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("test1").withMiddlename("test3").withLastname("test2").withAdress("test4").withHomenumber("test5").withMobilenumber("test6");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size() ));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }




}
