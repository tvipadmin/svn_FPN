package com.televital.fptelemedicine.domain;

public class ContactDetails {
	
	private String address;
	private String contactNumber;
	private String email;
	  
	public void setAddress(String address) {   this.address=address; }
	public String getAddress( ) {   return this.address; 	  }
	
	public void setContactNumber(String contactNumber) {  this.contactNumber=contactNumber; 	}
	public String getContactNumber( ) {	  return this.contactNumber;  }
	
    public void setEmail(String email) {  this.email=email; 	}
	public String getEmail( ) {	  return this.email;  }
}
