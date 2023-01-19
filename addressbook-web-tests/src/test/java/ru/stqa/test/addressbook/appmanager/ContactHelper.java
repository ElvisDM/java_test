package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type1(By.name("firstname"), contactData.firstname());
    type1(By.name("lastname"), contactData.lastname());
    type1(By.name("address"), contactData.address());
    attach(By.name("photo"), contactData.getPhoto());
    type1(By.name("home"), contactData.homephone());
    type1(By.name("mobile"),contactData.mobilephone());
    type1(By.name("work"),contactData.workphone());
    type1(By.name("email"), contactData.email());


    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void saveContact() {
    click(By.xpath("//input[21]"));
  }

  public void selectContactsById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public void deleteSelectedContacts() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }
  public void gotoAddNewContactPage() {
    wd.findElement(By.linkText("add new")).click();
    if (isElementPresent(By.name("new_group"))) {}
  }

  public void initContactsModificationById(int id) {
    /*wd.findElement(By.xpath("//img[@alt='Edit']")).click();*/
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  public void modify(ContactData contact) {
    selectContactsById(contact.getId());
    initContactsModificationById(contact.getId());
    fillContactForm(contact, false);
    updateContacts();
    returnToContactPage();
  }

  public void updateContacts() {
    click(By.name("update"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void delete(ContactData contact) {
    selectContactsById(contact.getId());
    deleteSelectedContacts();
    acceptAlert();
    returnToContactPage();
  }

  public void create(ContactData contact) {
    gotoAddNewContactPage();
    fillContactForm(new ContactData().withFirstname(contact.firstname()).withLastname(contact.lastname()).withAddress(contact.address())
            .withHomephone(contact.homephone()).withMobilephone("+7 (970) 354-91-12").withWorkphone("+7 (993) 479-82-86")
            .withEmail(contact.email()).withPhoto(contact.getPhoto()).withGroup("test1"),true);
    saveContact();
    returnToContactPage();
  }
  public boolean isThereASelectContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereACheckGroupName(String groupname) {
    return isElementPresent(By.xpath("//span[@class='group'][text()='" + groupname + "']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element: elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      String allphones = cells.get(5).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllphones(allphones).withAllEmails(allEmails).withAddress(address));
    }
    return contacts;
  }
  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactsModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomephone(home).withMobilephone(mobile).withWorkphone(work).withPhone2(phone2)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
