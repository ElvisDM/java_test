package ru.stqa.test.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.test.mantis.appmanager.HttpSession;

import java.io.IOException;
import java.text.ParseException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException, ParseException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));


  }
}
