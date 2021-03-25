package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import javax.mail.MessagingException;
import java.io.IOException;

public class AdminHelper extends HelperBase{
  public AdminHelper(ApplicationManeger app) {
    super(app);
  }
  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");

    type(By.name("username"), username);
    click(By.xpath("//input[@value='Вход']"));
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Вход']"));
  }

  public void goToUsersManagePage() {
    click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
    click(By.xpath("//a[contains(text(),'Управление пользователями')]"));

  }
  public void selectUser(String username) {
    
    click(By.xpath(String.format("//a[text() = '%s']", username)));

  }


  public void resetPassword(String username) throws InterruptedException, MessagingException, IOException {

    click(By.xpath("//input[@value='Сбросить пароль']"));
  }

  public void changePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type = 'submit']"));
  }



}
