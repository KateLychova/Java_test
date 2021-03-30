package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {


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

  public void fillContactForm(ContactData contactData, boolean creation) {

    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAdress());
    type(By.name("home"), contactData.getHomenumber());
    type(By.name("mobile"), contactData.getMobilenumber());
    type(By.name("work"), contactData.getWorknumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

    if (creation) {
      if (contactData.getGroups().size() != 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }


  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withMiddlename(middlename).withLastname(lastname)
            .withAdress(address).withHomenumber(home).withMobilenumber(mobile).withWorknumber(work).withEmail(email).withEmail2(email2)
            .withEmail3(email3);


  }

  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  private void gotoContactPage() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    gotoContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());

    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    goToHomePage();


  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
  }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }

    click(By.linkText("home"));
  }

  public void findElement() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;


  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String adress = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allNumbers = cells.get(5).getText();

      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAdress(adress).withAllEmails(allEmails)
              .withAllNumbers(allNumbers));
    }
    return new Contacts(contactCache);
  }


  public void addContactToGroup() {
    click(By.name("add"));
    contactCache = null;
    goToHomePage();
  }

  public void removeContactFromGroup() {
    wd.findElement(By.name("selected[]")).click();
    click(By.name("remove"));
    contactCache = null;
    goToHomePage();
  }

  public void selectedGroup(Contacts contactData) {
    new Select(wd.findElement(By.name("group")))
            .selectByVisibleText(contactData.iterator().next().getGroups().iterator().next().getName());
  }

  public void selectGroup(GroupData group) {
    click(By.xpath(String.format("//select[@name='to_group']/option[@value='%s']", group.getId())));
  }

  public void filterByGroup(GroupData group) {
    click(By.xpath(String.format("//select[@name='group']/option[text() = '%s']", group.getName())));
  }

  public void selectContactWithoutGroup(ContactData contact) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[none]");
    click(By.xpath(String.format("//input[@type='checkbox']", contact.getId())));
  }

  public void selectContact(ContactData contact) {
    click(By.xpath(String.format("//input[@type='checkbox']", contact.getId())));
  }
}



