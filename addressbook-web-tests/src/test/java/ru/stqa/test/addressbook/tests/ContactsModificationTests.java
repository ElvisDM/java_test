package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase{

  @Test
  public void testContactsModification() {

    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createAndFillNewContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", "test1"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactsModification();
    app.getContactHelper().fillContactForm(new ContactData("Pavel", "Ivanov", "China","+7(901)987-12-24", "ivanov99@yandex.ru", null), false);
    app.getContactHelper().updateContacts();
    app.getNavigationHelper().gotoHomePage();


  }


}
