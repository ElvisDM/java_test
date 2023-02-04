package ru.stqa.test.mantis.appmanager;

import org.openqa.selenium.By;
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

  public void resetPassword(UserData userData) {
    wd.findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i")).click();
    wd.findElement(By.linkText("Manage Users"));
    wd.findElement(By.linkText("link=employee"));
    wd.findElement(By.xpath("//input[@value='Reset Password']"));


  }
}
