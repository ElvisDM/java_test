package ru.stqa.test.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.util.Set;

public class ContactAddToGroupTest extends TestBase {

  @Test
  public void testContactAddToGroup() {
    app.goTo().Home();
    Contacts contacts = app.db().contacts();
    ContactData selectContact = contactAddToGroup(contacts);
    ContactData before = selectContact;

    app.contact().selectContactsById(selectContact.getId());
    app.contact().contactAddToGroup(notGroupInContact().getName());

    ContactData after = selectContact.inGroup(notGroupInContact());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));


  }

  private GroupData notGroupInContact() {
    Contacts contacts = app.db().contacts();
    Groups groupInContact = contactAddToGroup(contacts).getGroups();
    Groups listGroups = app.db().groups();
    listGroups.removeAll(groupInContact);
    GroupData group = listGroups.iterator().next();
    return group;
  }

  private ContactData contactAddToGroup(Contacts contacts) {
    for (ContactData contact : contacts) {
      Set<GroupData> ContactInGroup = contact.getGroups();
      int listGroups = app.db().groups().size();
      if (listGroups > ContactInGroup.size()) {
        return contact;
      }
    }
    return null;
  }

}
