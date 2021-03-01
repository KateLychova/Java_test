package ru.stqa.pft.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends TestBase {





  @Test
  public void testContactCreation() throws Exception {

    Set<ContactData> before = app.contact().all();
    app.goTo().contactPage();
    ContactData contact = new ContactData().withFirstname("test1").withLastname("test2");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assertions.assertEquals(after.size(),before.size() +1);


    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assertions.assertEquals(before,after);
  }




}
