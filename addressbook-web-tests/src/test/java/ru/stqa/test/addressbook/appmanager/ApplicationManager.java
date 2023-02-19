package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  public WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  public boolean acceptNextAlert = true;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }
  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();
    if ("".equals(properties.getProperty("selenium.server"))) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver(new FirefoxOptions().setBinary("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
      } else if(browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver(new ChromeOptions().setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
      } else if (browser.equals(BrowserType.IEXPLORE)) {
        wd = new InternetExplorerDriver();
      }
    } else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }

    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
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

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public void gotoHomePage() {
  }
  public DbHelper db() {
    return dbHelper;
  }
  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

}
