package ru.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Viktor").withLastname("Brovin")
            .withAddress("Russia").withHomephone("+7(901)683-09-76").withMail("brovin19@mail.ru").withGroup(group);
    app.contact().create(contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
