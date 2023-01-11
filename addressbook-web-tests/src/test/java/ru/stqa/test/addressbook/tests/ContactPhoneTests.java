package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
  String group = "test1";
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();

    if (! app.contact().isThereACheckGroupName(group)) {
      app.group().create(new GroupData().withName(group).withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoHome();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Brovin")
              .withAddress("Russia").withHomephone("+7(901)683-09-76").withEmail("brovin19@mail.ru").withGroup(group));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.address(), equalTo(contactInfoFromEditForm.getAddress()));
  //  assertThat(contact.getMobilephone(), equalTo(cleaned(contactInfoFromEditForm.getMobilephone())));
  //  assertThat(contact.getWorkphone(), equalTo(cleaned(contactInfoFromEditForm.getWorkphone())));

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getAllEmails())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
