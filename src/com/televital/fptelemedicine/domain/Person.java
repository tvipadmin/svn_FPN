package com.televital.fptelemedicine.domain;

public class Person extends Entity {

  private String firstName;
  private String lastName;
  private Login login;

  public final String getFirstName() {    return this.firstName;  }
  public void setFirstName(String value) {    this.firstName = value;  }
  public final String getLastName() {    return this.lastName;  }
  public void setLastName(String value) {    this.lastName = value;  }
  public final Login getLogin() {    return this.login;  }
  public void setLogin(Login value) {    this.login = value;  }

}
