package ru.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.List;

public class ContactsDeletionTests extends TestBase {


  @Test
  public void testContactsDeletion () {
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
    app.getContactHelper().selectContacts(before.size() - 1);
    app.acceptNextAlert = true;
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptAlert();
    app.getNavigationHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);



  }


}
