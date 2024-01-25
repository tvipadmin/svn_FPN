package com.televital.fptelemedicine.domain;

import java.io.Serializable;

public class Entity implements Serializable{
  
  private String photo;
  private String tstamp;
  private ContactDetails contactDetails;
 
 // private String address;
	//private String contactNumber;
	//private String email;
	  
/*	public void setAddress(String address) {   this.address=address; }
	public String getAddress( ) {   return this.address; 	  }
	
	public void setContactNumber(String contactNumber) {  this.contactNumber=contactNumber; 	}
	public String getContactNumber( ) {	  return this.contactNumber;  }
	
  public void setEmail(String email) {  this.email=email; 	}
	public String getEmail( ) {	  return this.email;  }*/
	
  public final String getPhoto() {    return this.photo;  }
  public void setPhoto(String value) {   this.photo = value;  }
  
  public final String getTstamp() {    return this.tstamp;  }
  public void setTstamp(String value) {   this.tstamp = value;  }
  
  public final ContactDetails getContactDetails() {   return this.contactDetails;  }
  public void setContactDetails(ContactDetails contactDetails) {  this.contactDetails = contactDetails;  }

}
