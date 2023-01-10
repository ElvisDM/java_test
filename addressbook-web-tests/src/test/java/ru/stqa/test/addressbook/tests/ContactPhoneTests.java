package ru.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

public class ContactPhoneTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    String group = "test1";
    if (! app.contact().isThereACheckGroupName(group)) {
      app.group().create(new GroupData().withName(group).withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoHome();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Brovin").withAddress("Russia").
              withHomephone("+7(901)683-09-76").withMobilephone("+7 (914) 923-53-82").withWorkphone("+7 (922) 984-23-43")
              .withMail("brovin19@mail.ru").withGroup(group));
    }
  }
  @Test
  public void testContactPhones() {
    app.goTo().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

  }

}
