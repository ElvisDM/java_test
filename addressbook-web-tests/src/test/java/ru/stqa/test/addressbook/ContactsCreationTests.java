package ru.stqa.test.addressbook;

import org.testng.annotations.Test;

public class ContactsCreationTests extends TestBase {


  @Test
  public void testContactsCreation() throws Exception {


    gotoAddNewContactPage();
    fillContactForm(new ContactData("Viktor", "Brovin", "+7(901)683-09-76", "brovin19@mail.ru"));
    saveContact();
    gotoHomePage();

  }


}
