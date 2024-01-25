package com.televital.fptelemedicine.domain;

import java.io.Serializable;

public class Login implements Serializable{
  
  private String loginId;
  private String password;
  private String confirmPassword;
  private String activationKey;
  private String currentLoginStatus;
  private String lastLoginTime;
  private String accountType;
  private String accountStatus;
  private String tstamp;
  private Person person;
  private UserPreferences userPreferences;
  
  public void setLoginId(String loginId) {  this.loginId = loginId;  }
  public String getLoginId() {  return this.loginId;  }
  
  public void setPerson(Person person) {	this.person = person; }
  public Person getPerson() {	return person; }
  
  public void setPassword(String password) {  this.password = password;  }
  public String getPassword() {  return this.password;  }
  
  public void setActivationKey(String activationKey) {  this.activationKey = activationKey;  }
  public String getActivationKey() {  return this.activationKey;  }
  
  public void setCurrentLoginStatus(String currentLoginStatus) {  this.currentLoginStatus = currentLoginStatus;   }
  public String getCurrentLoginStatus() {  return this.currentLoginStatus;  }
  
  public void setLastLoginTime(String lastLoginTime) {  this.lastLoginTime = lastLoginTime;   }
  public String getLastLoginTime() {  return this.lastLoginTime;  }
  
  public void setAccountType(String accountType) {   this.accountType = accountType;   }
  public String getAccountType() {  return this.accountType;  }
  
  public void setAccountStatus(String accountStatus) {  this.accountStatus = accountStatus;  }
  public String getAccountStatus() {  return this.accountStatus;  }

  public void setTstamp(String value) {    tstamp = value;  }
  public final String getTstamp() {    return tstamp;  }
    
  public String getConfirmPassword() {	return confirmPassword; }
  public void setConfirmPassword(String confirmPassword) {	this.confirmPassword = confirmPassword; }
  
  public UserPreferences getUserPreferences() {	return userPreferences; }
  public void setUserPreferences(UserPreferences userPreferences) {	this.userPreferences = userPreferences; }
  
}
