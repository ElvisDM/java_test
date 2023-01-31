package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String group = "test4";
    File photo = new File("src/test/resources/Screenshot_1.png");
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(group));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().Home();
      app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Brovin")
              .withAddress("Russia").withHomephone("+7(901)683-09-76").withEmail("brovin19@mail.ru").withPhoto(photo));
    }
  }

  @Test
  public void testContactAddToGroup() {
    File photo = new File("src/test/resources/Screenshot_1.png");
    Contacts contacts = app.db().contacts();
    ContactData selectContact = contactAddToGroup(contacts);
    app.goTo().Home();

    if (selectContact == null || contactAddToGroup(contacts) == null) {
      app.contact().create(new ContactData().withFirstname("Andrey").withLastname("Orlov")
              .withAddress("Volgograd").withHomephone("+7(906)258-15-58").withEmail("orlov_80@list.ru").withPhoto(photo));
    }

    GroupData groupToContact = notGroupInContact();

    app.contact().selectContactsById(selectContact.getId());
    app.contact().contactAddToGroup(notGroupInContact().getName());

    ContactData after = selectContact.inGroup(notGroupInContact());
    assertThat(after, equalTo(selectContact));

  }

  public GroupData notGroupInContact() {
    Contacts contacts = app.db().contacts();
    Groups groupInContact = contactAddToGroup(contacts).getGroups();
    Groups listGroups = app.db().groups();
    listGroups.removeAll(groupInContact);
    GroupData group = listGroups.iterator().next();
    return group;
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

}
