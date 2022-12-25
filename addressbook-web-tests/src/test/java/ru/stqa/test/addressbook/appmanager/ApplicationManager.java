package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  public boolean acceptNextAlert = true;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }
  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setBinary("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
    } else if(browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver(new ChromeOptions().setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
    } else if (browser.equals(BrowserType.IEXPLORE)) {
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }


  public String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
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


  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public NavigationHelper gotoHomePage() {
    return navigationHelper;
  }
  public ContactHelper selectContacts(){
    return contactHelper;
  }
  public ContactHelper deleteSelectedContacts(){
    return contactHelper;
  }
  public ContactHelper gotoAddNewContactPage(){
    return contactHelper;
  }
}
