package com.televital.fptelemedicine.domain;

public class Lab extends Person{

	private String labId;
	private String labName;
	private String contactPerson;
	private String flag;
	 public void setLabId(String labId) {     this.labId=labId;	 }
	 public String getLabId( ) {	  return this.labId;	  }
	 
	 public void setLabName(String labName) {     this.labName=labName;	 }
	 public String getLabName( ) {	  return this.labName;	  }
	 
	 public void setContactPerson(String contactPerson) {     this.contactPerson=contactPerson;	 }
	 public String getContactPerson( ) {	  return this.contactPerson;	  }
	 
	 public void setFlag(String flag) {   this.flag=flag;	}
	  public String getFlag( ) {	  return this.flag;		  }
}
