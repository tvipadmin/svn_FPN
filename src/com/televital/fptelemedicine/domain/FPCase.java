package com.televital.fptelemedicine.domain;

import java.util.List;
import java.util.Set;
import java.io.Serializable;
public class FPCase implements Serializable{
  
  private String caseId;
  private Doctor doctor;
  private Patient patient;
  private String caseStatus;
  private String complaint_symptoms;
  private String caseDescription;
  private String caseattachments;
  private String weight;
  private String height;
  private String temperature;
  private String bloodPressure;
  private String pulseRate;
  private String respirationRate;
  private String lmp;
  private String diagnosis; 
  private String prescription;
  private String impression;
  private String starttstamp;
  private String endtstamp;
  private String caseTitle;
  
  public final String getCaseId() {    return caseId;	  }
  public void setCaseId(String value) { 	caseId = value;	  }
  
  public final Doctor getDoctor() {    return doctor;  }
  public void setDoctor(Doctor value) {	doctor = value;  }
  
  public final String getCaseStatus() {    return caseStatus;  }
  public void setCaseStatus(String value) {	caseStatus = value;  }
  
  public final Patient getPatient() {    return patient;  }
  public void setPatient(Patient value) {	patient = value;  }
  
  public final String getComplaint_symptoms() {    return complaint_symptoms;  }
  public void setComplaint_symptoms(String value) {	complaint_symptoms = value;  }
  
  public final String getCaseDescription() {    return caseDescription;  }
  public void setCaseDescription(String value) {	caseDescription = value;  }
  
  public final String getCaseattachments() {    return caseattachments;  }
  public void setCaseattachments(String value) {	caseattachments = value;  }
  
  public final String getWeight() {    return weight;  }
  public void setWeight(String value) {	weight = value;  }
  
  public final String getHeight() {    return height;  }
  public void setHeight(String value) {	height = value;  }
  
  public final String getTemperature() {   return temperature;  }
  public void setTemperature(String value) {	temperature = value;  }
  
  public final String getBloodPressure() {    return bloodPressure;  }
  public void setBloodPressure(String value) {	bloodPressure = value;  }
  
  public final String getPulseRate() {    return pulseRate;  }
  public void setPulseRate(String value) {	pulseRate = value;  }
  
  public final String getRespirationRate() {    return respirationRate;  }
  public void setRespirationRate(String value) {	respirationRate = value;  }
  
  public final String getLmp() {    return lmp;  }
  public void setLmp(String value) {	lmp = value;  }
  
  public final String getDiagnosis() {    return diagnosis;  }
  public void setDiagnosis(String value) {	diagnosis = value;  }
  
  public final String getPrescription() {    return prescription;  }
  public void setPrescription(String value) {	prescription = value;  }
  
  public final String getImpression() {    return impression;  }
  public void setImpression(String value) {	impression = value;  }
  
  public final String getStarttstamp() {    return starttstamp;  }
  public void setStarttstamp(String value) {	starttstamp = value;  }
  
  public final String getEndtstamp() {    return endtstamp;  }
  public void setEndtstamp(String value) {	endtstamp = value;  }
  
  public final String getCaseTitle() {    return caseTitle;	  }
  public void setCaseTitle(String value) { 	caseTitle = value;	  }
}
