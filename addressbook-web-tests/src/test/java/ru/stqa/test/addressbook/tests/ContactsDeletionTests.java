package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;

public class ContactsDeletionTests extends TestBase {


  @Test
  public void testContactsDeletion () {
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createAndFillNewContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", "test1"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContacts();
    app.acceptNextAlert = true;
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptAlert();
    app.getNavigationHelper().gotoHome();


  }


}
