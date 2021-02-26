package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManeger;

public class TestBase {

  protected static final ApplicationManeger app = new ApplicationManeger(BrowserType.FIREFOX);

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

    @AfterSuite(alwaysRun = true)
    public void tearDown () throws Exception {
      app.stop();

    }

  }

