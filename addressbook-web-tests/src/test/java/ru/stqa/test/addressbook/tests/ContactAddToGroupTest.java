package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.io.File;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    File photo = new File("src/test/resources/Screenshot_1.png");
    String group = "test4";

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(group));
    }
    Contacts contacts = app.db().contacts();
    if (app.db().contacts().size() == 0 || contactToGroup(contacts) == null) {
      app.goTo().Home();
      app.contact().create(new ContactData().withFirstname("Andrey").withLastname("Orlov")
              .withAddress("Volgograd").withHomephone("+7(906)258-14-39").withEmail("orlov_80@list.ru").withPhoto(photo));
    }
  }

  @Test
  public void testContactAddToGroup() {

    Contacts contacts = app.db().contacts();
    ContactData contactToGroup = contactToGroup(contacts);
    GroupData addedGroup = notGroupInContact();

    app.goTo().Home();
    app.contact().selectContactsById(contactToGroup.getId());
    app.contact().contactAddToGroup(addedGroup.getName());

    Contacts after = app.db().contacts();

    assertThat(after, equalTo(contacts.without(contactToGroup).withAdded(contactToGroup.inGroup(addedGroup))));
    verifyContactListInUI();

  }

  public GroupData notGroupInContact() {
    Contacts contacts = app.db().contacts();
    Groups groupInContact = contactToGroup(contacts).getGroups();
    Groups listGroups = app.db().groups();
    listGroups.removeAll(groupInContact);
    GroupData group = listGroups.iterator().next();
    return group;
  }

  public ContactData contactToGroup(Contacts contacts) {
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
