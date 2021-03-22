package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddAndRemoveFromGroup extends TestBase {

  @BeforeClass
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("test1").withMiddlename("test3").withLastname("test2").withAdress("test4").withHomenumber("test5").withMobilenumber("test6").withWorknumber("test7")
              .withEmail("test8").withEmail2("test9").withEmail3("test10"));
    }


  }

  @Test
  public void testContactAddAndRemoveFromGroup() {

    Groups groups = app.db().groups();
    Contacts contactNotInGroup = app.db().contactNotInGroup();
    ContactData addedContact = contactNotInGroup.iterator().next();
    GroupData selectedGroup = groups.iterator().next();
    if(app.db().contactNotInGroup().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("test1").withMiddlename("test3").withLastname("test2").withAdress("test4").withHomenumber("test5").withMobilenumber("test6").withWorknumber("test7")
              .withEmail("test8").withEmail2("test9").withEmail3("test10"));
    }




    app.goTo().homePage();
    app.contact().addToGroup(addedContact.getId(), selectedGroup.getId());
    app.goTo().homePage();
    app.contact().filterByGroup(selectedGroup.getId());
    assertThat(app.db().contactsInGroup(addedContact.getId()).getGroups().contains(selectedGroup), equalTo(true));






  }
  @Test
  public void testContactRemoveFromGroup(){
    Groups groups = app.db().groups();
    Contacts contactInGroup = app.db().contactInGroup();

    ContactData selectedContact = contactInGroup.iterator().next();
    GroupData selectedGroup = groups.iterator().next();

    if(app.db().contactInGroup().size() == 0){
      app.goTo().homePage();
      app.contact().addToGroup(selectedContact.getId(),selectedGroup.getId());
    }


    app.goTo().homePage();
    app.contact().filterByGroup(selectedGroup.getId());
    app.contact().deleteContactFromGroup(selectedContact);

    app.goTo().homePage();
    app.contact().filterByGroup(selectedGroup.getId());
    assertThat(app.db().contactsInGroup(selectedContact.getId()).getGroups().contains(selectedGroup), equalTo(false));
  }

}