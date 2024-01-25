 
package com.televital.fptelemedicine.domain;

import java.util.Set;

public class Doctor extends Person {
	
  private String doctorId;
  private String education;
  private String designation;
  private String identification;
  private String clinicName;
  private String specialty;
  private String type;
  private String flag;
  


  public void setDoctorId(String doctorId) {    this.doctorId=doctorId; }
  public String getDoctorId( ) {  return this.doctorId;	  }
  
  public void setEducation(String education) {    this.education=education; }
  public String getEducation( ) { 	  return this.education;  }
  
  public void setDesignation(String designation) {   this.designation=designation;  }
  public String getDesignation( ) {	  return this.designation;	  }
  
  public void setIdentification(String identification) {     this.identification=identification; }
  public String getIdentification( ) {	  return this.identification;	  }
  
  public void setClinicName(String clinicName) {    this.clinicName=clinicName; }
  public String getClinicName( ) { 	  return this.clinicName;  }
  
  public void setSpecialty(String specialty) {     this.specialty=specialty;  }
  public String getSpecialty() {  return this.specialty;  }
  
  public void setType(String type) {   this.type=type;	}
  public String getType( ) {	  return this.type;		  }
	
  public void setFlag(String flag) {   this.flag=flag;	}
  public String getFlag( ) {	  return this.flag;		  }
}
