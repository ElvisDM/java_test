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
import java.util.Set;

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
      app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Brovin")
              .withAddress("Russia").withHomephone("+7(901)683-09-76").withEmail("brovin19@mail.ru").withPhoto(photo));
    }
    for (ContactData contact: contacts) {
      if (contact.getGroups().size() == 0 || contacts.size() != 0) {
        app.contact().selectContactsById(contact.getId());
        app.contact().contactAddToGroup(notGroupInContact().getName());
      }
    }
  }

  @Test
  public void testContactRemoveFromGroup() {
    app.goTo().Home();

    Contacts contacts = app.db().contacts();
    ContactData before = app.contact().contactInGroup(contacts);
    GroupData selectedGroup = selectGroup();

    app.contact().selectGroup(selectedGroup.getName());
    app.contact().removeContactFromGroup(app.contact().contactInGroup(contacts));

    ContactData after = before.inGroup(selectedGroup);
    MatcherAssert.assertThat(after, Matchers.equalTo(before));

  }

  private GroupData selectGroup() {
    Contacts contacts = app.db().contacts();
    GroupData groupWithContact = app.contact().contactInGroup(contacts).getGroups().iterator().next();
    return groupWithContact;
  }
  public ContactData contactAddToGroup(Contacts contacts) {
    for (ContactData contact : contacts) {
      Set<GroupData> ContactInGroup = contact.getGroups();
      int listGroups = app.db().groups().size();
      if (listGroups > ContactInGroup.size()) {
        return contact;
      }
    }
    return null;
  }

  public GroupData notGroupInContact() {
    Contacts contacts = app.db().contacts();
    Groups groupInContact = contactAddToGroup(contacts).getGroups();
    Groups listGroups = app.db().groups();
    listGroups.removeAll(groupInContact);
    GroupData group = listGroups.iterator().next();
    return group;
  }
}
