package ru.stqa.test.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.test.mantis.model.MailMessage;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ResetUsersPasswordTest extends TestBase{
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetUserPassword() throws IOException, ParseException {
    String password = "zaq!23";

    app.changePass().loginUnderAdmin();
    app.changePass().goToUserPage();

    String user = app.getUserName();
    String email = String.format("%s@localhost.localdomain", user);

    app.changePass().resetPass();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);


    app.registration().finish(confirmationLink, password);
    Assert.assertTrue(app.newSession().login(user, password));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
