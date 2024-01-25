package com.televital.fptelemedicine.domain;

public class LabTest {
	
	  private String labTestId;
	 // private Lab lab;
	  private LabServices labServices;
	  private FPCase fpcase;
	 // private Doctor doctor;
	  private String testReport;
	  private String flag;
	  private String orderedTime;
	  
	  public final String getLabTestId() {	    return labTestId;		  }
      public void setLabTestId(String value) { 	labTestId = value;		  }
      
	/*  public final Lab getLab() {	    return lab;	  }
      public void setLab(Lab value) {	   lab = value;	  }*/
      
      public final FPCase getFpcase() {    	return fpcase;    	}
      public void setFpcase(FPCase value) {    	fpcase = value;    }
      
    /*  public final Doctor getDoctor() {    	return doctor;    }
      public void setDoctor(Doctor value) {  	doctor = value;    }*/
      
      public final String getTestReport() {    	return testReport;    }
      public void setTestReport(String value) {    	testReport = value;    }
      
      public final LabServices getLabServices() {    	return labServices;    }
      public void setLabServices(LabServices value) {    	labServices = value;    }
      
      public void setFlag(String flag) {   this.flag=flag;	}
      public String getFlag( ) {	  return this.flag;		  }
      
      public final String getOrderedTime() {   return this.orderedTime;   }
      public void setOrderedTime(String value) {    this.orderedTime = value;   }
}
