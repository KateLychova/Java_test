package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if( app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("test1").withLastname("test2"));
    }

  }


  @Test
  public void testContactModification() throws Exception{



    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/screen.png");
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("test1").withMiddlename("test3").withLastname("test2").withAdress("test4").withHomenumber("test5").withMobilenumber("test6").withWorknumber("test7").withPhoto(photo)
            .withEmail("test8").withEmail2("test9").withEmail3("test10");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size() ));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }



}
