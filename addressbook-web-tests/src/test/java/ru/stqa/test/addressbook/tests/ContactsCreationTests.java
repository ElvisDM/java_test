package ru.stqa.test.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.Contacts;
import ru.stqa.test.addressbook.model.GroupData;
import ru.stqa.test.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {
  @DataProvider
   public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      xStream.allowTypes(new Class[]{ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    //list.add(new Object[]{new ContactData().withFirstname("Alex").withLastname("Maximov").withAddress("Moscow").withEmail("a.maximov@gmail.com").withHomephone("+7(495)602-26-08").withMobilephone("+7(916)147-09-15")});
    //list.add(new Object[]{new ContactData().withFirstname("Oleg").withLastname("Petrov").withAddress("Tula").withEmail("petrov82@ya.ru").withHomephone("+7(499)594-65-84").withMobilephone("+7(977)276-79-34")});
    //list.add(new Object[]{new ContactData().withFirstname("Maxim").withLastname("Karasev").withAddress("Kursk").withEmail("19karas@mail.ru").withHomephone("+7(498)942-32-91").withMobilephone("+7(906)466-73-55")});
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  String group = "test1";
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(group));
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactsCreation(ContactData contact) {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/Screenshot_1.png");
    //ContactData contact = new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withPhoto(photo).withHomephone(homephone).withEmail(email).withPhoto(photo);
    app.goTo().Home();
    Contacts before = app.db().contacts();
    app.contact().create(contact.inGroup(groups.iterator().next()).withPhoto(photo));
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();
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
