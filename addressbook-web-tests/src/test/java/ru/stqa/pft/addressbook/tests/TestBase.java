package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManeger;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);
  protected final ApplicationManeger app = new ApplicationManeger(BrowserType.CHROME);



  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

    @AfterSuite(alwaysRun = true)
    public void tearDown () throws Exception {
      app.stop();

    }

    @BeforeMethod
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test" +m.getName() + " with parameters " + Arrays.asList(p));

    }

    @AfterMethod
  public void logTestStop(Method m){
      logger.info("Stop test" +m.getName());
    }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }
  public void verifyContactListInUI()
  {if (Boolean.getBoolean("verifyUI")) {
    Contacts dbContacts = app.db().contacts();
    Contacts uiContacts = app.contact().all();
    assertThat(uiContacts, equalTo(dbContacts.stream()
            .map((c) -> new ContactData().withId(c.getId()).withFirstname(c.getFirstname()).withLastname(c.getLastname())
            .withAdress(c.getAdress()))
            .collect(Collectors.toSet())));
  }
  }

}

