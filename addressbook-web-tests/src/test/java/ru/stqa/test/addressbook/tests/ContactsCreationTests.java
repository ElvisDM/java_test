package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

public class ContactsCreationTests extends TestBase {


  @Test
  public void testContactsCreation() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getContactHelper().isThereASelectGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getContactHelper().createAndFillNewContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", "test1"));
    app.getNavigationHelper().gotoHomePage();
  }
}
