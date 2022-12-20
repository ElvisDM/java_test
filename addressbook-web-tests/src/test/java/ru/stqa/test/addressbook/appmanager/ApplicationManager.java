package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final ContactHelper contactHelper = new ContactHelper();
  private GroupHelper groupHelper;
  public boolean acceptNextAlert = true;

  public void init() {
    contactHelper.wd = new FirefoxDriver(new FirefoxOptions().setBinary("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
    contactHelper.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    contactHelper.wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(contactHelper.wd);
    login("admin", "secret");
  }

  private void login(String username, String password) {

    contactHelper.wd.findElement(By.name("user")).clear();
    contactHelper.wd.findElement(By.name("user")).sendKeys(username);
    contactHelper.wd.findElement(By.name("pass")).click();
    contactHelper.wd.findElement(By.name("pass")).clear();
    contactHelper.wd.findElement(By.name("pass")).sendKeys(password);
    contactHelper.wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  public void gotoGroupPage() {
    contactHelper.wd.findElement(By.name("searchform")).click();
    contactHelper.wd.findElement(By.linkText("groups")).click();
  }

  public void stop() {
    contactHelper.wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      contactHelper.wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      contactHelper.wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void gotoAddNewContactPage() {
    contactHelper.wd.findElement(By.linkText("add new")).click();
  }

  public String closeAlertAndGetItsText() {
    try {
      Alert alert = contactHelper.wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public void deleteSelectedContacts() {
    contactHelper.wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  public void selectContacts() {
    contactHelper.wd.findElement(By.name("selected[]")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
