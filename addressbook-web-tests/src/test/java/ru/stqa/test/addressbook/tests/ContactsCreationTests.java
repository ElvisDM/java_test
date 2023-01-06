package ru.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactsCreationTests extends TestBase {


  @Test(enabled = false)
  public void testContactsCreation() {
    app.goTo().groupPage();
    String group = "test1";
    if (! app.getContactHelper().isThereACheckGroupName(group)) {
      app.group().create(new GroupData(group, "test2", "test3"));
    }
    app.goTo().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", group);
    app.getContactHelper().createAndFillNewContactForm(contact);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
