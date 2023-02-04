package ru.stqa.test.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.test.mantis.model.MailMessage;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class RegistrationTests extends TestBase {
  //@BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test (enabled = true)
  public void testRegistration() throws IOException, ParseException {

    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost.localdomain", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);

    app.registration().finish(confirmationLink, password);
    Assert.assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  //@AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
