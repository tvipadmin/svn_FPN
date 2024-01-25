package com.televital.fptelemedicine.domain.form;

import java.util.List;

import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.History;
import com.televital.fptelemedicine.domain.Patient;

public class EmrBackingObject {
	
	private Patient patient;
	private FPCase  fpcase;
	private String historytb;
	private List fpCaseList;
	private History history;
	private String newcase;
	private List historyAttachment;
	private List caseAttachment;
	private List labServiceList;
	private String caseClosed;
	private List specialityList;
	private String docType;
	private String section;
	private String caseTitleBO;
	private String emrBO;
	private String diagonisisDisplay;
	private String vitalSignsDisplay;
	
	public void setDiagonisisDisplay(String diagonisisDisplay)	{	this.diagonisisDisplay=diagonisisDisplay;	}
	public String getDiagonisisDisplay()	{	return this.diagonisisDisplay;	}
	
	public void setVitalSignsDisplay(String vitalSignsDisplay)	{	this.vitalSignsDisplay=vitalSignsDisplay;	}
	public String getVitalSignsDisplay()	{	return this.vitalSignsDisplay;	}
	
	
	public void setPatient(Patient patient)	{	this.patient=patient;	}
	public Patient getPatient()	{	return this.patient;	}

	public void setHistorytb(String historytb)	{	this.historytb=historytb;	}
	public String getHistorytb()	{	return this.historytb;	}
	
	public void setFpcase(FPCase fpcase)	{	this.fpcase=fpcase;	}
	public FPCase getFpcase()	{	return this.fpcase;	}
	
	public void setFpCaseList(List fpCaseList)	{	this.fpCaseList=fpCaseList;	}
	public List getFpCaseList()	{	return this.fpCaseList;	}
	
	public void setHistory(History history)	{	this.history=history;	}
	public History getHistory()	{	return this.history;	}
	
	public void setNewcase(String newcase)	{	this.newcase=newcase;	}
	public String getNewcase()	{	return this.newcase;	}
	
	public void setHistoryAttachment(List historyAttachment)	{	this.historyAttachment=historyAttachment;	}
	public List getHistoryAttachment()	{	return this.historyAttachment;	}
	
	public void setCaseAttachment(List caseAttachment)	{	this.caseAttachment=caseAttachment;	}
	public List getCaseAttachment()	{	return this.caseAttachment;	}
	
	public void setLabServiceList(List labServiceList)	{	this.labServiceList=labServiceList;	}
	public List getLabServiceList()	{	return this.labServiceList;	}
	
	public void setCaseClosed(String caseClosed)	{	this.caseClosed=caseClosed;	}
	public String getCaseClosed()	{	return this.caseClosed;	}
	
	public void setSpecialityList(List specialityList)	{	this.specialityList=specialityList;	}
	public List getSpecialityList()	{	return this.specialityList;	}
		
	public void setDocType(String docType)	{	this.docType=docType;	}
	public String getDocType()	{	return this.docType;	}
	
	public void setSection(String section)	{	this.section=section;	}
	public String getSection()	{	return this.section;	}
	
	public void setCaseTitleBO(String caseTitleBO)	{	this.caseTitleBO=caseTitleBO;	}
	public String getCaseTitleBO()	{	return this.caseTitleBO;	}
	
	public void setEmrbo(String emrBO)	{	this.emrBO=emrBO;	}
	public String getEmrBO()	{	return this.emrBO;	}
}
