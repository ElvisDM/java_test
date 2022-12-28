package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

public class ContactsModificationTests extends TestBase{

  @Test
  public void testContactsModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getContactHelper().isThereASelectGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereASelectContact()) {
      app.getContactHelper().createAndFillNewContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", "test1"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactsModification();
    app.getContactHelper().fillContactForm(new ContactData("Pavel", "Ivanov", "China","+7(901)987-12-24", "ivanov99@yandex.ru", null), false);
    app.getContactHelper().updateContacts();
    app.getNavigationHelper().gotoHomePage();


  }


}
