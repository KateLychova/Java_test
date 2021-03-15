package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class ContactData {

@XStreamOmitField
@Id
@Column(name = "id")
  private  int id = Integer.MAX_VALUE;

@Expose
@Column(name = "firstname")
  private  String firstname;

@Expose
@Column(name = "middlename")
  private  String middlename;

  @Expose
  @Column(name = "lastname")
  private  String lastname;



  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private  String adress;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private  String homenumber;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private  String mobilenumber;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String worknumber;

  @Transient
  private String allNumbers;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;


  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;



  @Transient
  private String allEmails;

  @Column(name ="photo")
  @Type(type = "text")
  private String photo;



  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
  joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getAllNumbers() {
    return allNumbers;
  }

  public ContactData withAllNumbers(String allNumbers) {
    this.allNumbers = allNumbers;
    return this;
  }

  public ContactData withWorknumber(String worknumber){
    this.worknumber = worknumber;
    return this;
  }

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

  public String getWorknumber() { return worknumber;}


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(middlename, that.middlename) && Objects.equals(lastname, that.lastname) && Objects.equals(adress, that.adress) && Objects.equals(homenumber, that.homenumber) && Objects.equals(mobilenumber, that.mobilenumber) && Objects.equals(worknumber, that.worknumber) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, adress, homenumber, mobilenumber, worknumber, email, email2, email3);
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ",lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }


  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
