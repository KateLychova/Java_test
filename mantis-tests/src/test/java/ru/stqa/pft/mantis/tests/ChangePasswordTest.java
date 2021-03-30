package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

public class ChangePasswordTest extends TestBase{

@Test
  public void testChangePassword() throws InterruptedException, MessagingException, IOException, ServiceException {
  Users users = app.db().users();
  UserData user = users.iterator().next();
  String username = user.getUsername();
  String email = user.getEmail();
  app.admin().login("administrator", "root");
  app.mail().start();
  app.admin().goToUsersManagePage();
  app.admin().selectUser(username);
  app.admin().resetPassword(username);
  List<MailMessage> mailMessages = app.mail().waitForMail(1,10000);
  String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
  app.mail().stop();
  String newPassword = "1111";
  app.admin().changePassword(confirmationLink, newPassword);

  HttpSession session = app.newSession();

  Assert.assertTrue(session.login(username, newPassword));
  Assert.assertTrue(session.isLoggedInAs(username));

}


}
