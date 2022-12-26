package ru.stqa.test.addressbook.model;

import java.util.Objects;

public final class ContactData {
  private final String firstname;
  private final String lastname;
  private String address;
  private final String homephone;
  private final String mail;
  private String group;

  public ContactData(String firstname, String lastname, String address, String homephone, String mail, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mail = mail;
    this.group = group;
  }

  public String firstname() {
    return firstname;
  }

  public String lastname() {
    return lastname;
  }
  public String address() {
    return address;
  }

  public String homephone() {
    return homephone;
  }

  public String mail() {
    return mail;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (ContactData) obj;
    return Objects.equals(this.firstname, that.firstname) &&
            Objects.equals(this.lastname, that.lastname) &&
            Objects.equals(this.homephone, that.homephone) &&
            Objects.equals(this.mail, that.mail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname, homephone, mail);
  }

  @Override
  public String toString() {
    return "ContactData[" +
            "firstname=" + firstname + ", " +
            "lastname=" + lastname + ", " +
            "homephone=" + homephone + ", " +
            "mail=" + mail + ']';
  }

  public String getGroup() {
    return group;
  }

}