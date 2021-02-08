package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String adress;
  private final String homenumber;
  private final String mobilenumber;

  public ContactData(String firstname, String middlename, String lastname, String adress, String homenumber, String mobilenumber) {
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
}
