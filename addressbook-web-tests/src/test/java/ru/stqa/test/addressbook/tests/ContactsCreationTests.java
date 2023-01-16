package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {
  @DataProvider
   public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("Alex").withLastname("Maximov").withAddress("Moscow").withEmail("a.maximov@gmail.com").withHomephone("+7(495)602-26-08").withMobilephone("+7(916)147-09-15")});
    //list.add(new Object[]{new ContactData().withFirstname("Oleg").withLastname("Petrov").withAddress("Tula").withEmail("petrov82@ya.ru").withHomephone("+7(499)594-65-84").withMobilephone("+7(977)276-79-34")});
    //list.add(new Object[]{new ContactData().withFirstname("Maxim").withLastname("Karasev").withAddress("Kursk").withEmail("19karas@mail.ru").withHomephone("+7(498)942-32-91").withMobilephone("+7(906)466-73-55")});
    return list.iterator();
  }

  String group = "test1";
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.contact().isThereACheckGroupName(group)) {
      app.group().create(new GroupData().withName(group).withHeader("test2").withFooter("test3"));
    }
  }

  @Test(dataProvider = "validContacts")
  public void testContactsCreation(ContactData contact) {
    File photo = new File("src/test/resources/Screenshot_1.png");
    //ContactData contact = new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withPhoto(photo).withHomephone(homephone).withEmail(email).withPhoto(photo);
    app.goTo().gotoHome();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Screenshot_1.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
