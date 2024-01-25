package com.televital.fptelemedicine.license;


import java.util.List;

public class ServerLicense extends License {
  private String maxConcurrentUsers;

  
  private String concurrentUsers;

  public final String getMaxConcurrentUsers() {
    return this.maxConcurrentUsers;
  }

  public void setMaxConcurrentUsers(String value) {
    this.maxConcurrentUsers = value;
  }

  
  public final String getConcurrentUsers() {
    return this.concurrentUsers;
  }

  public void setConcurrentUsers(String value) {
    this.concurrentUsers = value;
  }

}
