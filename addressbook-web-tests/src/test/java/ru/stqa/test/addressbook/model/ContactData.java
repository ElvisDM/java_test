package ru.stqa.test.addressbook.model;

import java.util.Objects;

public final class ContactData {
  private  int id;
  private final String firstname;
  private final String lastname;
  private String address;
  private final String homephone;
  private final String mail;
  private String group;

  public int getId() {
    return id;
  }

  public ContactData(String firstname, String lastname, String address, String homephone, String mail, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mail = mail;
    this.group = group;
  }

  public ContactData(int id, String firstname, String lastname, String address, String homephone, String mail) {
    this.id = id;
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


  public String getGroup() {
    return group;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (!Objects.equals(firstname, that.firstname)) return false;
    return Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}