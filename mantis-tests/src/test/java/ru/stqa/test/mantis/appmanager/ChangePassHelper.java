package ru.stqa.test.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePassHelper extends HelperBase{

  public ChangePassHelper(ApplicationManager app) {
    super(app);
  }

  public void loginUnderAdmin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), "administrator");
    click(By.xpath("//input[@value='Login']"));
    type(By.name("password"), "root");
    click(By.xpath("//input[@value='Login']"));
  }

  public void goToUserPage() {
    goToManage();
    goToManageUsers();
    selectUser();
  }

  public void resetPass() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  private void selectUser() {
    click(By.xpath("//tr[2]/td/a"));
  }

  private void goToManageUsers() {
    click(By.linkText("Manage Users"));
  }

  private void goToManage() {
    click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
  }
}
