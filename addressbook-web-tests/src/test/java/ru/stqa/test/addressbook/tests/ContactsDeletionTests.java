package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;

public class ContactsDeletionTests extends TestBase {


  @Test
  public void testContactsDeletion () {
    

    app.getContactHelper().selectContacts();
    app.acceptNextAlert = true;
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptAlert();
    app.getNavigationHelper().gotoHome();


  }


}
