package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.test.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type1(By.name("firstname"), contactData.firstname());
    type1(By.name("lastname"), contactData.lastname());
    type1(By.name("address"), contactData.address());
    type1(By.name("home"), contactData.homephone());
    type1(By.name("email"), contactData.mail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));

    }
  }
  public void saveContact() {
    click(By.xpath("//input[21]"));
  }
  public void selectContacts() {
    wd.findElement(By.name("selected[]")).click();
  }
  public void deleteSelectedContacts() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }
  public void gotoAddNewContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void initContactsModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateContacts() {
    click(By.name("update"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void createAndFillNewContactForm(ContactData contact) {
    gotoAddNewContactPage();
    fillContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", "test1"),true);
    saveContact();
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
