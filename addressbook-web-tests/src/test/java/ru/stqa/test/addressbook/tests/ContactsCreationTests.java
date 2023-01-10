package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {
  String group = "test1";
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.contact().isThereACheckGroupName(group)) {
      app.group().create(new GroupData().withName(group).withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testContactsCreation() {

    app.goTo().gotoHome();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Viktor").withLastname("Brovin")
            .withAddress("Russia").withHomephone("+7(901)683-09-76").withMail("brovin19@mail.ru").withGroup(group);
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
