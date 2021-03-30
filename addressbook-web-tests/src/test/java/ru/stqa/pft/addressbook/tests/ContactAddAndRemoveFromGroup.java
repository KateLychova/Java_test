package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

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
    if (app.db().contactWithoutGroups().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("test1").withMiddlename("test3").withLastname("test2").withAdress("test4").withHomenumber("test5").withMobilenumber("test6").withWorknumber("test7")
              .withEmail("test8").withEmail2("test9").withEmail3("test10"));
    }
    if (app.db().contactWithGroups().size() == 0) {
      ContactData before = app.db().contactNotInGroup();
      Groups groups = app.db().groups();
      GroupData group = groups.iterator().next();
      app.goTo().homePage();
      app.contact().selectContactWithoutGroup(before);
      app.contact().selectGroup(group);
      app.contact().addContactToGroup();
    }

  }

  @Test
  public void testContactAddToFromGroup() {
    ContactData before = app.db().contactNotInGroup();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().homePage();
    app.contact().selectContactWithoutGroup(before);
    app.contact().selectGroup(group);
    app.contact().addContactToGroup();
    ContactData after = app.db().contactById(before.getId());
    assertTrue(after.getGroups().contains(group));




  }
  @Test
  public void testContactRemoveFromGroup(){
    ContactData before = app.db().contactInGroup();
    GroupData group = before.getGroups().iterator().next();
    app.goTo().homePage();
    app.contact().filterByGroup(group);
    app.contact().selectContact(before);
    app.contact().removeContactFromGroup();

    ContactData after = app.db().contactById(before.getId());
    assertTrue(after.getGroups().contains(group));







}}