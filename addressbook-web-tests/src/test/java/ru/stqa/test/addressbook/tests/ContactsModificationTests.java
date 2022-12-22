package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase{

  @Test
  public void testContactsModification() {

    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContacts();
    app.getContactHelper().initContactsModification();
    app.getContactHelper().fillContactForm(new ContactData("Pavel", "Ivanov", "+7(901)987-12-24", "ivanov99@yandex.ru"));
    app.getContactHelper().updateContacts();
    app.getNavigationHelper().gotoHomePage();


  }


}
