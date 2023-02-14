package ru.stqa.test.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

    groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.goTo().Home();
      app.contact().create(new ContactData().withFirstname("Andrey").withLastname("Orlov")
              .withAddress("Volgograd").withHomephone("+7(906)258-14-39").withEmail("orlov_80@list.ru")
              .withPhoto(photo).inGroup(groups.iterator().next()));
    }
    app.goTo().Home();
    for (ContactData contact: contacts) {
      if (contact.getGroups().size() == 0) {
        GroupData addedGroup = groups.iterator().next();
        ContactData contactToGroup = contacts.iterator().next();
        app.contact().selectContactsById(contactToGroup.getId());
        app.contact().contactAddToGroup(addedGroup.getName());
      }
    }
  }

  @Test
  public void testContactRemoveFromGroup() {

    app.goTo().Home();

    Contacts contactsBefore = app.db().contacts();
    ContactData contactWithGroup = contactWithGroup();
    ContactData selectedContact = app.contact().contactInGroup(contactsBefore);
    GroupData removedGroup = contactWithGroup.getGroups().iterator().next();


    app.contact().gotoGroupWhithContact(removedGroup.getId());
    app.contact().selectContactsById(contactWithGroup.getId());
    app.contact().removeContactFromGroup();

    Contacts contactAfter = app.db().contacts();
    ContactData selectedContactAfter = app.db().contactById(selectedContact.getId());
    Contacts after = contactAfter.without(selectedContactAfter);

    assertThat(contactAfter.size(), equalTo(contactsBefore.size()));
    Contacts b1 = contactsBefore.withAdded(selectedContact);
    Contacts a1 = after.withAdded(selectedContactAfter.inGroup(removedGroup));
    MatcherAssert.assertThat(a1, equalTo(b1));
    verifyContactListInUI();

  }

  private ContactData contactWithGroup() {
    Contacts contacts = app.db().contacts();
    ContactData contactToGroup = null;
    for (ContactData contact: contacts) {
      if (contact.getGroups().size() > 0) {
        contactToGroup = contact;
        break;
      }
    }
    return contactToGroup;
  }
}
