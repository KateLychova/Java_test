package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManeger;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {


  protected static final ApplicationManeger app
          = new ApplicationManeger(System.getProperty("browser",BrowserType.CHROME));



  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }
   public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {

     if (!app.soap().getIssueStatus(issueId).equals("resolved")) {
       return true;
     } else {
       return false;
     }
   }


  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

    @AfterSuite(alwaysRun = true)
    public void tearDown () throws Exception {
    app.ftp().restore("config_inc.php.bak","config_inc.php" );
      app.stop();

    }




  }



