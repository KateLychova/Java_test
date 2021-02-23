package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  public void setId(int id) {
    this.id = id;
  }

  private  int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String adress;
  private final String homenumber;
  private final String mobilenumber;


  public int getId() {
    return id;
  }




  public ContactData(String firstname, String middlename, String lastname, String adress, String homenumber, String mobilenumber) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.adress = adress;
    this.homenumber = homenumber;
    this.mobilenumber = mobilenumber;
  }
  public ContactData(int id, String firstname, String middlename, String lastname, String adress, String homenumber, String mobilenumber) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.adress = adress;
    this.homenumber = homenumber;
    this.mobilenumber = mobilenumber;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAdress() {
    return adress;
  }

  public String getHomenumber() {
    return homenumber;
  }

  public String getMobilenumber() {
    return mobilenumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ",lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }
}
