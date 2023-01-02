package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

public class ContactsDeletionTests extends TestBase {


  @Test
  public void testContactsDeletion () {
    app.getNavigationHelper().gotoGroupPage();
    String group = "test1";
    if (! app.getContactHelper().isThereAGroupName(group)) {
      app.getGroupHelper().createGroup(new GroupData(group, "test2", "test3"));
    }
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereASelectContact()) {
      app.getContactHelper().createAndFillNewContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", group));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContacts();
    app.acceptNextAlert = true;
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptAlert();
    app.getNavigationHelper().gotoHome();


  }


}
