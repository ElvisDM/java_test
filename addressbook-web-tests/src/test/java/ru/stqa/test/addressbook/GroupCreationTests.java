package ru.stqa.test.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
    wd.findElement(By.linkText("Logout")).click();
  }

}
