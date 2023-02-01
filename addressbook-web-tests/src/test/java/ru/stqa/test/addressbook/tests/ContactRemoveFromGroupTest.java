package ru.stqa.test.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.io.File;

public class ContactRemoveFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String group = "test4";
    File photo = new File("src/test/resources/Screenshot_1.png");

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(group));
    }
    app.goTo().Home();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Andrey").withLastname("Orlov")
              .withAddress("Volgograd").withHomephone("+7(906)258-14-39").withEmail("orlov_80@list.ru").withPhoto(photo));
    }
    GroupData addedGroup = groups.iterator().next();
    ContactData contactToGroup = contacts.iterator().next();
    for (ContactData contact: contacts) {
      if (contact.getGroups().size() == 0) {
        app.contact().selectContactsById(contactToGroup.getId());
        app.contact().contactAddToGroup(addedGroup.getName());
      }
    }
  }

  @Test
  public void testContactRemoveFromGroup() {

    app.goTo().Home();

    Contacts contacts = app.db().contacts();
    ContactData contactToGroup = contacts.iterator().next();
    GroupData removedGroup = contactToGroup.getGroups().iterator().next();

    app.contact().gotoGroupWhithContact(removedGroup.getId());
    app.contact().selectContactsById(contactToGroup.getId());
    app.contact().removeContactFromGroup();

    ContactData after = contactToGroup.inGroup(removedGroup);

    MatcherAssert.assertThat(after, Matchers.equalTo(contacts));
    verifyContactListInUI();

  }

}
