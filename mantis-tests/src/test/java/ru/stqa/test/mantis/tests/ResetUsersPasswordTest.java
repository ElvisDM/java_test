package ru.stqa.test.mantis.tests;

import org.testng.annotations.Test;

public class ResetUsersPasswordTest extends TestBase{

  @Test
  public void testResetUserPassword() {
    app.login().login("administrator", "root");
  }
}
