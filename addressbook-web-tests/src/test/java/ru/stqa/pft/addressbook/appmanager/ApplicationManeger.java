package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManeger {
  private final Properties properties;
  protected WebDriver wd;

  private  ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private  NavigationHelper navigationHelper;
  private  GroupHelper groupHelper ;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManeger(String browser) {
    this.browser = browser;

    properties = new Properties();

  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();

    if("".equals(properties.getProperty("selenium.server"))) {

      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }else {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setPlatform(Platform.fromString(properties.getProperty("platform", "win10")));
        wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")),capabilities);
      }
    }
    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));


  }



  public void stop() {
    wd.quit();
  }



  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }
  public DbHelper db(){return dbHelper;}
}