package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {


  private  int id = Integer.MAX_VALUE;
  private  String firstname;
  private  String middlename;
  private  String lastname;
  private  String adress;
  private  String homenumber;
  private  String mobilenumber;



  public ContactData withHomenumber(String homenumber) {
    this.homenumber = homenumber;
    return this;
  }

  public ContactData withAdress(String adress) {
    this.adress = adress;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }



  public ContactData withMobilenumber(String mobilenumber) {
    this.mobilenumber = mobilenumber;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }


  public int getId() {
    return id;
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
