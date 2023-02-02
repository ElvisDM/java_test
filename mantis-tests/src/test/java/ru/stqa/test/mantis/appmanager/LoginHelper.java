package ru.stqa.test.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class LoginHelper {
  private final ApplicationManager app;
  private WebDriver wd;

  public LoginHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");

  }
}
