package com.televital.fptelemedicine.domain;

import java.io.Serializable;

public class FavSpecalist  implements Serializable {

	private int favSpecalistId;
	private Doctor specalistId;
	private Doctor familyPhysicianId;
	private String status;
	private String flag;

	 public final int getFavSpecalistId() {   return favSpecalistId;	  }
     public void setFavSpecalistId(int favSpecalistId) {    this.favSpecalistId = favSpecalistId;  }
    
	 public final Doctor getSpecalistId() {  return specalistId;  }
	 public void setSpecalistId(Doctor value) { 	specalistId = value;  }
	  
	 public final Doctor getFamilyPhysicianId() {   return familyPhysicianId;  }
	 public void setFamilyPhysicianId(Doctor value) {  	familyPhysicianId = value;  }
	 
	 public final String getStatus() {   return status;	  }
     public void setStatus(String accountStatus) {    status = accountStatus;  }
	    
     public void setFlag(String flag) {   this.flag=flag;	}
     public String getFlag( ) {	  return this.flag;		  }
	
}
