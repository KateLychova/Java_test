package ru.stqa.pft.mantis.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;

  @Column(name="username")
  private String username;

  @Column(name="password")
  private String password;

  @Column(name="email")
  private String email;

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return Objects.equals(username, userData.username) && Objects.equals(email, userData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, email);
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }



}
