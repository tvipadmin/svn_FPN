package com.televital.fptelemedicine.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.televital.fptelemedicine.domain.CaseReferral;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.LabTest;

import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class StatisticsManagement {
	
public static final Logger log4log = Logger.getLogger(StatisticsManagement.class);
	
	private IFPTelemedicineFacade fpportalFacade;
	private List referredPatList = null;
	private List orderedPatList = null;
	private List patientList = null;
	private List caseReferralList = null;
	private List testOrderedList = null;
	private String fromDate;
	private String toDate;
	private String patientId;
	private Patient patient =null;
	private CaseReferral caseRef = null;
	private LabTest labTest = null;
	private ILicenseCodec licenseCodec;
	
	public IFPTelemedicineFacade getFpportalFacade() {
		return fpportalFacade;
	}
	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade) {
		this.fpportalFacade = fpportalFacade;
	}
	
	public ILicenseCodec getLicenseCodec() {
		return licenseCodec;
	}

	public void setLicenseCodec(ILicenseCodec licenseCodec) {
		this.licenseCodec = licenseCodec;
	}
	
	public List loadPatReferredSep(String loginId,String sepId,String fromDateF,String toDateF)
	{
		log4log.error("hi"); 
		try
		{
		toDate = toDateF;
		fromDate = fromDateF;
		if(!fromDate.equals(""))
		{
		log4log.error("fromdateRef: "+fromDate+" toDateRef: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		fromDate = corFromDate;
		}
		if(!toDate.equals(""))
		{
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		
		toDate = corToDate;
		}
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		referredPatList =this.getFpportalFacade().loadReferralPatients(loginId,fromDate,toDate,sepId);
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size referredPatList : "+referredPatList.size());
		
		for(int i=0;i < referredPatList.size() ; i++)
		{
			caseRef = (CaseReferral)referredPatList.get(i);
			log4log.error("patientId "+caseRef.getFpcase().getPatient().getPatientId());
			patientId = caseRef.getFpcase().getPatient().getPatientId();
			patientList = this.getFpportalFacade().loadPatientById(patientId);
			if(patientList.size() > 0)
			{
			patient= (Patient)patientList.get(0);
			patient = FPTelemedicineUtils.getAge(patient);
			patientListO.add(patient);
			}
			
		}
		log4log.error("Later Size : "+patientListO.size());
		for(int i=0;i < patientListO.size() ; i++)
		{
			log4log.error("patientListO :"+patientListO.get(i));
		}
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = patientListO.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Patient pat = (Patient)element;
		   if (set.add(element) && set1.add(pat.getPatientId()))
		      newList.add(element);
		    }
		 patientListO.clear();
		 patientListO.addAll(newList);

		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	
	public String getCurrentDateS()
	{
		String date = FPTelemedicineUtils.getCurrentDate();
		String corDate = FPTelemedicineUtils.getYearMonthDay(date);
	    return corDate;
	}
	
	public String getOneMonthBackDateS()
	{
		String date = FPTelemedicineUtils.getCurrentDate();
		String predate = FPTelemedicineUtils.getOneMonthBackDate(date);
		log4log.error("predate: "+predate);
		String corDate = FPTelemedicineUtils.getYearMonthDay(predate);
		return corDate;
		
	}
	
	public List loadPatRegisteredMonthWise(String loginId)
	{
		log4log.error("hi"); 
		try
		{
		toDate = FPTelemedicineUtils.getCurrentDate();
		fromDate = FPTelemedicineUtils.getOneMonthBackDate(toDate);
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		patientList =this.getFpportalFacade().loadRegisteredPatient(loginId,fromDate,toDate);
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size patientList : "+patientList.size());
		
		for(int i=0;i < patientList.size() ; i++)
		{
			patient= (Patient)patientList.get(i);
			patient = FPTelemedicineUtils.getAge(patient);
			patientListO.add(patient);
		}
		
		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
		
	public List loadRegisteredMonthWise(String loginId,String fromDateD,String toDateD)
	{
		log4log.error("hi"); 
		try
		{
		fromDate = fromDateD;
		toDate = toDateD;
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		fromDate = corFromDate;
		toDate = corToDate;
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		patientList =this.getFpportalFacade().loadRegisteredPatient(loginId,fromDate,toDate);
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size patientList : "+patientList.size());
		
		for(int i=0;i < patientList.size() ; i++)
		{
			patient= (Patient)patientList.get(i);
			patient = FPTelemedicineUtils.getAge(patient);
			patientListO.add(patient);
		}
		
		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	public Boolean validateFromToDate(String fromDate, String toDate)
	{
		Boolean flag = FPTelemedicineUtils.compareDates(fromDate, toDate);
		return flag;
	}
	
	public Boolean validateEnteredDate(String fromDate, String toDate)
	{
		Boolean flag = FPTelemedicineUtils.compareEnteredDates(fromDate, toDate);
		return flag;
	}
	
	
	public List loadPatOrderedLab(String loginId,String labId,String fromDateF,String toDateF)
	{
		log4log.error("hi"); 
		try
		{
		toDate = toDateF;
		fromDate = fromDateF;
		if(!fromDate.equals(""))
		{
		log4log.error("fromdateRef: "+fromDate+" toDateRef: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		fromDate = corFromDate;
		}
		if(!toDate.equals(""))
		{
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		
		toDate = corToDate;
		}
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		orderedPatList=this.getFpportalFacade().loadOrderedLabPatients(loginId,fromDate,toDate,labId);
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size orderedPatList: "+orderedPatList.size());
		
		for(int i=0;i < orderedPatList.size() ; i++)
		{
			labTest = (LabTest)orderedPatList.get(i);
			log4log.error("patientId "+labTest.getFpcase().getPatient().getPatientId());
			patientId = labTest.getFpcase().getPatient().getPatientId();
			patientList = this.getFpportalFacade().loadPatientById(patientId);
			if(patientList.size() > 0)
			{
			patient= (Patient)patientList.get(0);
			patient = FPTelemedicineUtils.getAge(patient);
			patientListO.add(patient);
			}
			
		}
		log4log.error("Later Size : "+patientListO.size());
		for(int i=0;i < patientListO.size() ; i++)
		{
			log4log.error("patientListO :"+patientListO.get(i));
		}
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = patientListO.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Patient pat = (Patient)element;
		   if (set.add(element) && set1.add(pat.getPatientId()))
		      newList.add(element);
		    }
		 patientListO.clear();
		 patientListO.addAll(newList);

		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	public String loadTotalPatOrderedLab(String loginId,String labId,String fromDateF,String toDateF)
	{
		log4log.error("hi"); 
		try
		{
		toDate = toDateF;
		fromDate = fromDateF;
		if(!fromDate.equals(""))
		{
		log4log.error("fromdateRef: "+fromDate+" toDateRef: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		fromDate = corFromDate;
		}
		if(!toDate.equals(""))
		{
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		
		toDate = corToDate;
		}
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		orderedPatList=this.getFpportalFacade().loadOrderedLabPatients(loginId,fromDate,toDate,labId);
		
		log4log.error("Size orderedPatList: "+orderedPatList.size());
		
		
		return Integer.toString(orderedPatList.size());
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	public List loadPatReferredOrdered(String loginId,String sepId,String labId,String fromDateF,String toDateF)
	{
		log4log.error("hi"); 
		try
		{
		toDate = toDateF;
		fromDate = fromDateF;
		if(!fromDate.equals(""))
		{
		log4log.error("fromdateRef: "+fromDate+" toDateRef: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		fromDate = corFromDate;
		}
		if(!toDate.equals(""))
		{
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		
		toDate = corToDate;
		}
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		orderedPatList=this.getFpportalFacade().loadReferredAndOrdered(loginId,fromDate,toDate,sepId,labId);
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size orderedPatList: "+orderedPatList.size());
		
		for(int i=0;i < orderedPatList.size() ; i++)
		{
			labTest = (LabTest)orderedPatList.get(i);
			log4log.error("patientId "+labTest.getFpcase().getPatient().getPatientId());
			patientId = labTest.getFpcase().getPatient().getPatientId();
			patientList = this.getFpportalFacade().loadPatientById(patientId);
			if(patientList.size() > 0)
			{
			patient= (Patient)patientList.get(0);
			patient = FPTelemedicineUtils.getAge(patient);
			patientListO.add(patient);
			}
			
		}
		log4log.error("Later Size : "+patientListO.size());
		for(int i=0;i < patientListO.size() ; i++)
		{
			log4log.error("patientListO :"+patientListO.get(i));
		}
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = patientListO.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Patient pat = (Patient)element;
		   if (set.add(element) && set1.add(pat.getPatientId()))
		      newList.add(element);
		    }
		 patientListO.clear();
		 patientListO.addAll(newList);

		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	
	public List loadFPPatReferredMonthWise(String loginId)
	{
		log4log.error("hi"); 
		try
		{
		toDate = FPTelemedicineUtils.getCurrentDate();
		fromDate = FPTelemedicineUtils.getOneMonthBackDate(toDate);
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		caseReferralList =this.getFpportalFacade().loadReferredPatient(loginId,fromDate,toDate);
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size caseReferralList : "+caseReferralList.size());
		
		for(int i=0;i < caseReferralList.size() ; i++)
		{
			caseRef= (CaseReferral)caseReferralList.get(i);
			log4log.error("patientObj: "+caseRef.getFpcase().getPatient());
			patient = FPTelemedicineUtils.getAge(caseRef.getFpcase().getPatient());
			patientListO.add(patient);
		}
		
		log4log.error("Later Size : "+patientListO.size());
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = patientListO.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Patient pat = (Patient)element;
		   if (set.add(element) && set1.add(pat.getPatientId()))
		      newList.add(element);
		    }
		 patientListO.clear();
		 patientListO.addAll(newList);
		 log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("loadFPPatReferredMonthWise Exception is: "+e);
			return null;
		}
		}
	public List loadPatReferredByFP(String loginId,String fpId,String fromDateF,String toDateF)
	{
		log4log.error("hi"); 
		try
		{
		toDate = toDateF;
		fromDate = fromDateF;
		if(!fromDate.equals(""))
		{
		log4log.error("fromdateRef: "+fromDate+" toDateRef: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		fromDate = corFromDate;
		}
		if(!toDate.equals(""))
		{
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		
		toDate = corToDate;
		}
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		if(fpId.equals(""))
		{
		 referredPatList = this.getFpportalFacade().loadReferredPatient(loginId,fromDate,toDate);
		}else
		{
		 referredPatList =this.getFpportalFacade().loadReferredPatientByFP(loginId,fromDate,toDate,fpId);
		}
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size referredPatList : "+referredPatList.size());
		
		for(int i=0;i < referredPatList.size() ; i++)
		{
			caseRef = (CaseReferral)referredPatList.get(i);
			log4log.error("patientId "+caseRef.getFpcase().getPatient().getPatientId());
			patientId = caseRef.getFpcase().getPatient().getPatientId();
			patientList = this.getFpportalFacade().loadPatientById(patientId);
			if(patientList.size() > 0)
			{
			patient= (Patient)patientList.get(0);
			patient = FPTelemedicineUtils.getAge(patient);
			patientListO.add(patient);
			}
			
		}
		log4log.error("Later Size : "+patientListO.size());
		for(int i=0;i < patientListO.size() ; i++)
		{
			log4log.error("patientListO :"+patientListO.get(i));
		}
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = patientListO.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Patient pat = (Patient)element;
		   if (set.add(element) && set1.add(pat.getPatientId()))
		      newList.add(element);
		    }
		 patientListO.clear();
		 patientListO.addAll(newList);

		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	
	public List loadFPPatTestOrderedMonthWise(String loginId)
	{
		log4log.error("hi"); 
		try
		{
		toDate = FPTelemedicineUtils.getCurrentDate();
		fromDate = FPTelemedicineUtils.getOneMonthBackDate(toDate);
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		testOrderedList = this.getFpportalFacade().loadLabTestOrdered(loginId,fromDate,toDate);
		
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size testOrderedList : "+testOrderedList.size());
		
		for(int i=0;i < testOrderedList.size() ; i++)
		{
			labTest= (LabTest)testOrderedList.get(i);
			log4log.error("patientObj: "+labTest.getFpcase().getPatient());
			patient = FPTelemedicineUtils.getAge(labTest.getFpcase().getPatient());
			patientListO.add(patient);
		}
		
		log4log.error("Later Size : "+patientListO.size());
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = patientListO.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Patient pat = (Patient)element;
		   if (set.add(element) && set1.add(pat.getPatientId()))
		      newList.add(element);
		    }
		 patientListO.clear();
		 patientListO.addAll(newList);
		 log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error(" Exception is: "+e);
			return null;
		}
		}
	
	public String loadTotalTestOrderedMonthWise(String loginId)
	{
		try
		{
		toDate = FPTelemedicineUtils.getCurrentDate();
		fromDate = FPTelemedicineUtils.getOneMonthBackDate(toDate);
		log4log.error("loadTotalTestOrderedMonthWise fromdate: "+fromDate+" toDate: "+toDate);
		testOrderedList = this.getFpportalFacade().loadLabTestOrdered(loginId,fromDate,toDate);
		log4log.error("Size loadTotalTestOrderedMonthWise GGGGGG  : "+testOrderedList.size());
		return Integer.toString(testOrderedList.size());
		}
		catch (Exception e)
		{
			log4log.error("loadTotalTestOrderedMonthWise Exception is: "+e);
			return null;
		}
	}
	
	public List loadPatTestOrderedByFP(String loginId,String fpId,String fromDateF,String toDateF)
	{
		log4log.error("hi"); 
		try
		{
		toDate = toDateF;
		fromDate = fromDateF;
		if(!fromDate.equals(""))
		{
		log4log.error("fromdateRef: "+fromDate+" toDateRef: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		fromDate = corFromDate;
		}
		if(!toDate.equals(""))
		{
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		
		toDate = corToDate;
		}
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		if(fpId.equals(""))
		{
		 testOrderedList = this.getFpportalFacade().loadLabTestOrdered(loginId,fromDate,toDate);
		}else
		{
		 testOrderedList =this.getFpportalFacade().loadtestOrderedPatientByFP(loginId,fromDate,toDate,fpId);
		}
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size testOrderedList : "+testOrderedList.size());
		
		for(int i=0;i < testOrderedList.size() ; i++)
		{
			
			labTest= (LabTest)testOrderedList.get(i);
			log4log.error("patientObj: "+labTest.getFpcase().getPatient());
			patient = FPTelemedicineUtils.getAge(labTest.getFpcase().getPatient());
			patientListO.add(patient);
			
		}
		log4log.error("Later Size : "+patientListO.size());
		for(int i=0;i < patientListO.size() ; i++)
		{
			log4log.error("patientListO :"+patientListO.get(i));
		}
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = patientListO.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Patient pat = (Patient)element;
		   if (set.add(element) && set1.add(pat.getPatientId()))
		      newList.add(element);
		    }
		 patientListO.clear();
		 patientListO.addAll(newList);

		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	public String loadTotalTestOrderedByFP(String loginId,String fpId,String fromDateF,String toDateF)
	{
		log4log.error("hi"); 
		try
		{
		toDate = toDateF;
		fromDate = fromDateF;
		if(!fromDate.equals(""))
		{
		log4log.error("fromdateRef: "+fromDate+" toDateRef: "+toDate);
		String corFromDate = FPTelemedicineUtils.getYearMonthDay(fromDate);
		fromDate = corFromDate;
		}
		if(!toDate.equals(""))
		{
		String corToDate = FPTelemedicineUtils.getYearMonthDay(toDate);
		
		toDate = corToDate;
		}
		log4log.error("fromdate: "+fromDate+" toDate: "+toDate);
		if(fpId.equals(""))
		{
		 testOrderedList = this.getFpportalFacade().loadLabTestOrdered(loginId,fromDate,toDate);
		}else
		{
		 testOrderedList =this.getFpportalFacade().loadtestOrderedPatientByFP(loginId,fromDate,toDate,fpId);
		}
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size testOrderedList : "+testOrderedList.size());
		log4log.error("Later Size : "+patientListO.size());
		return Integer.toString(testOrderedList.size());
		}
		catch (Exception e)
		{
			log4log.error("Exception is: "+e);
			return null;
		}
		}
	
}


