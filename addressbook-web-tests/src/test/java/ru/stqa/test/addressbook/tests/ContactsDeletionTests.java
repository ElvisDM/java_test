package ru.stqa.test.addressbook.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ContactsDeletionTests extends TestBase {


  @Test
  public void testContactsDeletion () throws Exception {

    app.selectContacts();
    app.acceptNextAlert = true;
    app.deleteSelectedContacts();
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));

  }


}
