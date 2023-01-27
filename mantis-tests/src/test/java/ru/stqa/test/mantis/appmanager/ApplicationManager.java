package ru.stqa.test.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  public WebDriver wd;
  private String browser;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }
  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setBinary("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
    } else if(browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver(new ChromeOptions().setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
    } else if (browser.equals(BrowserType.IEXPLORE)) {
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }

}