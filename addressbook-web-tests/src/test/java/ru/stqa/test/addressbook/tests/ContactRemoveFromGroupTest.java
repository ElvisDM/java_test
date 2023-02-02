package ru.stqa.test.addressbook.tests;

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

    if (app.db().contacts().size() == 0) {
      app.goTo().Home();
      app.contact().create(new ContactData().withFirstname("Andrey").withLastname("Orlov")
              .withAddress("Volgograd").withHomephone("+7(906)258-14-39").withEmail("orlov_80@list.ru")
              .withPhoto(photo).inGroup(groups.iterator().next()));
    }
    groups = app.db().groups();
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

    Contacts contacts = app.db().contacts();
    ContactData contactToGroup = contacts.iterator().next();
    GroupData removedGroup = contactToGroup.getGroups().iterator().next();


    app.contact().gotoGroupWhithContact(removedGroup.getId());
    app.contact().selectContactsById(contactToGroup.getId());
    app.contact().removeContactFromGroup();

    ContactData after = contactToGroup.inGroup(removedGroup);

    assertThat(after, equalTo(contactToGroup));
    verifyContactListInUI();

  }

}
