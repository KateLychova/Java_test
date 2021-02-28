package ru.stqa.pft.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {





  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.contact().list();
    app.goTo().contactPage();
    ContactData contact = new ContactData().withFirstname("test1").withLastname("test2");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assertions.assertEquals(after.size(),before.size() +1);


    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2) ->Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assertions.assertEquals(before,after);
  }




}
