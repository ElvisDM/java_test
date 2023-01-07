package ru.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.test.addressbook.model.ContactData;
import ru.stqa.test.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactsDeletionTests extends TestBase {


  @Test
  public void testContactsDeletion () {
    app.goTo().groupPage();
    String group = "test1";
    if (! app.getContactHelper().isThereACheckGroupName(group)) {
      app.group().create(new GroupData().withName(group).withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoHome();
    if (! app.getContactHelper().isThereASelectContact()) {
      app.getContactHelper().createAndFillNewContactForm(new ContactData("Viktor", "Brovin", "Russia","+7(901)683-09-76", "brovin19@mail.ru", group));
    }
    app.goTo().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContacts(before.size() - 1);
    app.acceptNextAlert = true;
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptAlert();
    app.goTo().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
