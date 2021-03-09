package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstname("test1").withLastname("test2").withAdress("test3").withMobilenumber("111")
      .withHomenumber("222").withWorknumber("333").withEmail("test@4").withEmail2("test@5").withEmail3("test@6"));
    }

  }




  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllNumbers(), equalTo(mergeNumbers(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAdress(),equalTo(mergeAdress(contactInfoFromEditForm)));







  }

  private String mergeAdress(ContactData contact) {
    return Arrays.asList(contact.getAdress()).stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeNumbers(ContactData contact) {
    return Arrays.asList(contact.getHomenumber(),contact.getMobilenumber(),contact.getWorknumber())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleanedEmail)
            .collect(Collectors.joining("\n"));

  }



  public static String cleaned(String number){
    return number.replaceAll("\\s","").replaceAll("[-()]","");

  }
  public static String cleanedEmail(String email){
    return email.replaceAll("\\s","").replaceAll("[-()]","");

  }

}

