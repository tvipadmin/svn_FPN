package com.televital.fptelemedicine.domain;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable{
	
  private String historyId;
  private String patientId;
  private String pastIllness;
  private String familyHistory;
  private String currentMedication;
  private String immunization;
  private String allergies;
  private String attachments;
  private Patient patient;
  
  public final String getHistoryId() {	 return historyId;	  }
  public void setHistoryId(String value) {	  historyId = value;  }
  
  public void setPatientId(String value) {	  patientId = value;  }
  public final String getPatientId() {	    return patientId;	  }
  
  public void setPastIllness(String value) {	  pastIllness = value;	  }
  public final String getpastIllness() {    return pastIllness;		  }
  
  public void setFamilyHistory(String value) {     familyHistory = value;  }
  public final String getFamilyHistory() {    return familyHistory;	  }
  
  public final String getCurrentMedication() {   return currentMedication;  }
  public void setCurrentMedication(String value) {   currentMedication = value;  }
  
  public final String getImmunization() {    return immunization;	  }
  public void setImmunization(String value) {    immunization = value;  }
  
  public final String getAllergies() {    return allergies;	  }
  public void setAllergies(String value) {    allergies= value;	  }
  
  public final String getAttachments() {    return attachments;  }
  public void setAttachments(String value) {    attachments = value;  }
  
  public final Patient getPatient() {    return this.patient;	  }
  public void setPatient(Patient patient) {	  this.patient = patient;  }
 
}
