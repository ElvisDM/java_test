package ru.stqa.test.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResetUsersPasswordTest extends TestBase{
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
    app.login();

  }

  @Test
  public void testResetUserPassword() {
    app.login().login("administrator", "root");

  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
