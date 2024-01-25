package com.televital.fptelemedicine.domain;

import java.io.Serializable;

public class CaseReferral implements Serializable {
  
	private String referralId;
	private FPCase fpcase;
	private Doctor specalistId;
	private Doctor familyPhysicianId;
	private String referredTime;
	private String opinions;
	private String flag;

  
    public final String getReferralId() {   return referralId;	  }
    public void setReferralId(String value) {    referralId = value;  }
    
    public final FPCase getFpcase() {    	return fpcase;    	}
    public void setFpcase(FPCase value) {    	fpcase = value;    }
    
    public final Doctor getSpecalistId() {  return specalistId;  }
    public void setSpecalistId(Doctor value) { 	specalistId = value;  }
    
    public final Doctor getFamilyPhysicianId() {   return familyPhysicianId;  }
    public void setFamilyPhysicianId(Doctor value) {  	familyPhysicianId = value;  }
    
    public final String getReferredTime() {   return this.referredTime;   }
    public void setReferredTime(String value) {    this.referredTime = value;   }
    
    public final String getOpinions() {      return this.opinions;    }
    public void setOpinions(String value) {   this.opinions = value;   }

    public void setFlag(String flag) {   this.flag=flag;	}
	public String getFlag( ) {	  return this.flag;		  }
}
