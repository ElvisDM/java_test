package ru.stqa.test.addressbook;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ContactsDeletionTests extends TestBase {


  @Test
  public void testContactsDeletion () throws Exception {

    selectContacts();
    acceptNextAlert = true;
    deleteSelectedContacts();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));

  }


}
