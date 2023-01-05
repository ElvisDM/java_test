package ru.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.List;

public class ContactsModificationTests extends TestBase{

  @Test
  public void testContactsModification() {
    app.getNavigationHelper().gotoGroupPage();
    String group = "test1";
    if (! app.getContactHelper().isThereACheckGroupName(group)) {
      app.getGroupHelper().createGroup(new GroupData(group, "test2", "test3"));
    }
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereASelectContact()) {
      app.getContactHelper().createAndFillNewContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", group));
    }
    app.getNavigationHelper().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactsModification(before.size() - 1);
    app.getContactHelper().fillContactForm(new ContactData("Pavel", "Ivanov", "China","+7(901)987-12-24", "ivanov99@yandex.ru", null), false);
    app.getContactHelper().updateContacts();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());


  }


}
