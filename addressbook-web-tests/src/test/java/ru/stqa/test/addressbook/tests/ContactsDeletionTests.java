package ru.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactsDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    String group = "test1";
    if (! app.contact().isThereACheckGroupName(group)) {
      app.group().create(new GroupData().withName(group).withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoHome();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Viktor").withLastname("Brovin")
              .withAddress("Russia").withHomephone("+7(901)683-09-76").withMail("brovin19@mail.ru").withGroup(group));
    }
  }

  @Test
  public void testContactsDeletion () {
    app.goTo().gotoHome();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().gotoHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
