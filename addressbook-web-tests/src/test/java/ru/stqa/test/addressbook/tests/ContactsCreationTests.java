package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;

public class ContactsCreationTests extends TestBase {


  @Test
  public void testContactsCreation() throws Exception {


    app.gotoAddNewContactPage();
    app.fillContactForm(new ContactData("Viktor", "Brovin", "+7(901)683-09-76", "brovin19@mail.ru"));
    app.saveContact();
    app.gotoHomePage();

  }


}
