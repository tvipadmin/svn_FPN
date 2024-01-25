package com.televital.fptelemedicine.domain;

import java.util.List;
import java.util.Set;

public class Patient extends Person {
  private String patientId;
  private Doctor creator;
  private CaseReferral caseReferral;
  private LabTest labTest;
  private String gender;
  private String dob;
  private String maritalStatus;
  private String bloodGroup;
  private String occupation;
  private Doctor doctor;
  
  private String ageYears;
  private String ageMonths;
   
  private List caseRefList;
  private List labTestRep;
  
  public final List getLabTestRep() {	    return this.labTestRep;		  }
  public void setLabTestRep(List labTestRep) {	  this.labTestRep = labTestRep;	  }  
  
  public final LabTest getLabTest() {	    return this.labTest;		  }
  public void setLabTest(LabTest labTest) {	  this.labTest = labTest;	  }  
  
  public final List getCaseRefList() {    return this.caseRefList;	  }
  public void setCaseRefList(List caseRefList) {  this.caseRefList = caseRefList;	  }
  
  public final String getPatientId() {    return this.patientId;	  }
  public void setPatientId(String patientId) {  this.patientId = patientId;	  }
  
  public final Doctor getCreator() {	    return this.creator;		  }
  public void setCreator(Doctor creator) {	  this.creator = creator;	  }
  
  public final CaseReferral getCaseReferral() {	    return this.caseReferral;		  }
  public void setCaseReferral(CaseReferral caseReferral) {	  this.caseReferral = caseReferral;	  }
  
  public String getGender() {    return this.gender;  }
  public void setGender(String value) {    this.gender = value;  }
  
  public final String getDob() {    return dob;	  }
  public void setDob(String value) {    dob = value;	  }
  
  public String getMaritalStatus() {   return this.maritalStatus;  }
  public void setMaritalStatus(String value) {   this.maritalStatus = value;  }
  
  public String getBloodGroup() {   return this.bloodGroup;  }
  public void setBloodGroup(String value) {    this.bloodGroup = value;  }
  
  public String getOccupation() {    return this.occupation;  }
  public void setOccupation(String value) {    this.occupation = value;  }
  
  public Doctor getDoctor() {    return this.doctor;	  }
  public void setDoctor(Doctor doctor) {   this.doctor = doctor;	  }
 
  public final String getAgeYears() {    return this.ageYears;	  }
  public void setAgeYears(String value) {	    this.ageYears = value;	  }

  public final String getAgeMonths() {    return this.ageMonths;	  }
  public void setAgeMonths(String value) {	    this.ageMonths = value;	  }

}
