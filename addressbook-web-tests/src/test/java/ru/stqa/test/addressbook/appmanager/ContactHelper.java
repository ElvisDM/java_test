package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.test.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    type1(By.name("firstname"), contactData.firstname());
    type1(By.name("lastname"), contactData.lastname());
    type1(By.name("home"), contactData.homephone());
    type1(By.name("email"), contactData.mail());
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
}
