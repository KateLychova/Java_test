package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;

public class ApplicationManeger {
  private final Properties properties;
  private WebDriver wd;


  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private AdminHelper adminHelper;
  private DbHelper db;
  private SoapHelper soapHelper;



  public ApplicationManeger(String browser) {
    this.browser = browser;

    properties = new Properties();

  }



  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));





  }



  public void stop() {
    if(wd != null){
      wd.quit();
    }

  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }


  public RegistrationHelper registration() {
    if (registrationHelper == null){
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }
  public FtpHelper ftp() {
    if (ftp == null){
      ftp = new FtpHelper(this);
    }
    return ftp;
  }



  public WebDriver getDriver() {
    if (wd == null)
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }

    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));

    return wd;
  }
  public MailHelper mail(){
    if (mailHelper == null){
      mailHelper = new MailHelper(this);
    }return mailHelper;
  }
  public JamesHelper james(){
    if(jamesHelper == null){
      jamesHelper = new JamesHelper(this);
    }return jamesHelper;
  }
  public AdminHelper admin(){
    if(adminHelper == null){
      adminHelper = new AdminHelper(this);
    }return adminHelper;
  }
  public DbHelper db() {
    if (db == null){
      db = new DbHelper(this);
    }
    return db;
  }
  public SoapHelper soap(){
    if(soapHelper == null){
      soapHelper = new SoapHelper(this);
    }return soapHelper;
  }
}