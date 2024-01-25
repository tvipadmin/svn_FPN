package com.televital.fptelemedicine.license;

public class License {
  private String tstamp;

  private String expDate;

  private String withEffectiveFrom;

  private String clientName;

  /**This variable indicate the level of license.
   * Level avaiable are
   * Level 1 (For a server in the Televital context).
   * Level 2 (For a Hospital in the TeleVital context).
   * Level 3 (For a Doctor and Hospital Assitants).
   */
  private String licenseLevel;

  public final String getTstamp() {
    return this.tstamp;
  }

  public void setTstamp(String value) {
    this.tstamp = value;
  }

  public final String getExpDate() {
    return this.expDate;
  }

  public void setExpDate(String value) {
    this.expDate = value;
  }

  public final String getWithEffectiveFrom() {
    return this.withEffectiveFrom;
  }

  public void setWithEffectiveFrom(String value) {
    this.withEffectiveFrom = value;
  }

  public final String getClientName() {
    return this.clientName;
  }

  public void setClientName(String value) {
    this.clientName = value;
  }

  public final String getLicenseLevel() {
    return this.licenseLevel;
  }

  public void setLicenseLevel(String value) {
    this.licenseLevel = value;
  }

}
