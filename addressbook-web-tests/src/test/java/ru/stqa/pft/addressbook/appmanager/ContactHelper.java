package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void logout() {
    click(By.linkText("Logout"));
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    click(By.name("theform"));
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAdress());
    type(By.name("home"),contactData.getHomenumber());
    type(By.name("mobile"),contactData.getMobilenumber());

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  private void gotoContactPage() {
    click(By.linkText("add new"));
  }
  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact) {
    gotoContactPage();
    fillContactForm(contact);
    submitContactCreation();
    returnToContactPage();
  }



  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements){

      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id =Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id,firstname,null,lastname,null,null,null);
      contacts.add(contact);
    }
    return contacts;
  }
}

