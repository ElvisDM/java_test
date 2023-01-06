package ru.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.test.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void deleteSelectedContacts() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }
  public void gotoAddNewContactPage() {
    wd.findElement(By.linkText("add new")).click();
    if (isElementPresent(By.name("new_group"))) {}
  }

  public void initContactsModification(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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
  public boolean isThereASelectContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereACheckGroupName(String groupname) {
    return isElementPresent(By.xpath("//span[@class='group'][text()='" + groupname + "']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element: elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
