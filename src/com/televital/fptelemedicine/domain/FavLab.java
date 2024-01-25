package com.televital.fptelemedicine.domain;

import java.io.Serializable;

public class FavLab implements Serializable {

	private int favLabId;
	private Lab labId;
	private Doctor familyPhysicianId;
	private String status;
	private String flag;

	 public final int getFavLabId() {   return favLabId;	  }
     public void setFavLabId(int favLabId) {    this.favLabId = favLabId;  }
    
	 public final Lab getLabId() {  return labId;  }
	 public void setLabId(Lab labId) { 	this.labId = labId;  }
	  
	 public final Doctor getFamilyPhysicianId() {   return familyPhysicianId;  }
	 public void setFamilyPhysicianId(Doctor value) {  	familyPhysicianId = value;  }
	 
	 public final String getStatus() {   return status;	  }
     public void setStatus(String accountStatus) {    status = accountStatus;  }
	    
     public void setFlag(String flag) {   this.flag=flag;	}
     public String getFlag( ) {	  return this.flag;		  }
	
}
