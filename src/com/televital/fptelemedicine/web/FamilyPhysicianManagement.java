
package com.televital.fptelemedicine.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


import com.televital.fptelemedicine.domain.CaseReferral;
import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.FavSpecalist;
import com.televital.fptelemedicine.domain.History;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.LabTest;
import com.televital.fptelemedicine.domain.LastRecord;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.domain.Person;
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.FamilyPhyManagementValidator;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class FamilyPhysicianManagement {
	
	public static final Logger log4log = Logger.getLogger(FamilyPhysicianManagement.class);
	
	private IFPTelemedicineFacade fpportalFacade;
	private String patientIdd;
	private List patientList = null;
	private ILicenseCodec licenseCodec;
	private UploadInfoBean uib;
	private UploadInfoBean uploadInfoBeanInst; 
	private static long bytesTransferred = 0;
	private static long fileSize = -100;
	private String filePath;
	private Patient patient =null;
	private FPCase fpcase;
	private History history;
	private Errors error;
	private String caseId;
	private String historyId;
	List lastExtIdH = null;
	List lastExtIdC = null;
	List lastExtIdP = null;

	
	private FamilyPhyManagementValidator dmv = new FamilyPhyManagementValidator();
	public IFPTelemedicineFacade getFpportalFacade() {
		return fpportalFacade;
	}
	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade) {
		this.fpportalFacade = fpportalFacade;
	}
	
	//setter method for licensecodec
	public ILicenseCodec getLicenseCodec() {
		return licenseCodec;
	}

	public void setLicenseCodec(ILicenseCodec licenseCodec) {
		this.licenseCodec = licenseCodec;
	}
	
	public UploadInfoBean getUploadInfoBeanInst() {
		return uploadInfoBeanInst;
	}
	public void setUploadInfoBeanInst(UploadInfoBean uploadInfoBeanInst) {
		this.uploadInfoBeanInst = uploadInfoBeanInst;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public List loadFamilyPhysicians(String loginId,String search)
	{
		
				
		patientList =this.getFpportalFacade().loadPatients(loginId);
		List patientListO =  new ArrayList();
		List tempListPat = new ArrayList();
		log4log.error("Size : "+patientList.size());
		if(search.equals("yes"))
		{
		for(int i=0;i < patientList.size() ; i++)
		{
			
			patient= (Patient)patientList.get(i);
			patient = FPTelemedicineUtils.getAge(patient);
			patientListO.add(patient);
			
		}
		}
		else
		{
			for(int i=0;i < patientList.size() ; i++)
			{
				
				patient= (Patient)patientList.get(i);
				patient = FPTelemedicineUtils.getAge(patient);
				List caseList = this.getFpportalFacade().loadCaseFPByPatientId(patient.getPatientId());
				
				for(int k=0;k<caseList.size();k++)
				{
				 FPCase fpCase =(FPCase) caseList.get(k);
					
				if(fpCase.getCaseStatus().equals("OPEN"))
				{
					if(!patientListO.contains(patient.getPatientId()))
		            {	
					 List listCaseRef = this.getFpportalFacade().loadCaseReferralCaseId(fpCase.getCaseId());
					 if(listCaseRef.size() > 0)
					 {
						 List tempList = new ArrayList();
						 for(int g=0;g< listCaseRef.size();g++)
						 {
						 CaseReferral caseRef = (CaseReferral)listCaseRef.get(g);
						 tempList.add(caseRef);
						 }
						 patient.setCaseRefList(tempList);
					 }
					 List listlistLabRep = this.getFpportalFacade().loadLabTestByCaseId(fpCase.getCaseId());
					 if(listlistLabRep.size() > 0)
					 {
						 List tempList1 = new ArrayList();
						 for(int g=0;g< listlistLabRep.size();g++)
						 {
						 LabTest labTest = (LabTest)listlistLabRep.get(g);
						 tempList1.add(labTest);
						 }
						 patient.setLabTestRep(tempList1);
					 }
				     patientListO.add(patient);
				    }
				 }
				}
				
			}
			
		}
		log4log.error("Later Size : "+patientListO.size());
		return patientListO;
		
	}
	
	public boolean savePatient(Patient patient,String loginId)
	{
		log4log.error("new record: "+patient.getPatientId());
		String patientId="";
		lastExtIdP = this.getFpportalFacade().findLastExternalId("PT");
		String newId = FPTelemedicineUtils.generateNewExternalId("PT",lastExtIdP);
		patientId = newId;

		
		fpcase = new FPCase();
		history = new History();
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		String age = getAgeModified(patient.getAgeYears(),patient.getAgeMonths());
		log4log.error("age : "+age);
		try
		{
		patient.setPatientId(patientId);	
		patient.setDob(age);	
		patient.setTstamp(tstamp);
		log4log.error("loginId: "+loginId);
		List docList = this.getFpportalFacade().loadDoctorById(loginId);
		if(docList.size() > 0)
		{
			Doctor doc = (Doctor)docList.get(0);
			patient.setCreator(doc);
			fpcase.setDoctor(doc);
		}
		
		List caseList= this.getFpportalFacade().loadCaseFP();
		
		lastExtIdC = this.getFpportalFacade().findLastExternalId("CS");
		String newId2 = FPTelemedicineUtils.generateNewExternalId("CS",lastExtIdC);
		caseId = newId2;
		/*if(!(caseList.size()>0))
		{
			caseId="1";
		}
		else
		{
		for(int i=0; i<caseList.size();i++)
		{
			FPCase casef=(FPCase)caseList.get(i);
			caseId=casef.getCaseId();
			log4log.error("caseId  : "+caseId);
		}
		int tempval = Integer.parseInt(caseId);
		caseId=Integer.toString(tempval+1);
		
		}*/
		log4log.error("after: "+caseId);
		
		fpcase.setCaseId(caseId);
		
		fpcase.setPatient(patient);
		fpcase.setCaseStatus("OPEN");
		fpcase.setStarttstamp(tstamp);
		fpcase.setEndtstamp(tstamp);
		log4log.error(fpcase.getCaseId()+"  "+fpcase.getCaseStatus());
		log4log.error("before Store");
		
		List historyList= this.getFpportalFacade().loadHistory();
		
		 lastExtIdH = this.getFpportalFacade().findLastExternalId("HS");
		 String newId1 = FPTelemedicineUtils.generateNewExternalId("HS",lastExtIdH);
		 historyId = newId1;

		
		/*if(!(historyList.size()>0))
		{
			historyId="1";
		}
		else
		{
		for(int i=0; i<historyList.size();i++)
		{
			History historyf=(History)historyList.get(i);
			historyId=historyf.getHistoryId();
			log4log.error("historyId  : "+historyId);
		}
		int tempval = Integer.parseInt(historyId);
		historyId=Integer.toString(tempval+1);
		
		}	*/
		 log4log.error("historyId: "+historyId);
		history.setHistoryId(historyId);
		history.setPatient(patient);
		history.setPatientId(patient.getPatientId());
		log4log.error("historyId: "+historyId);
		boolean val =this.getFpportalFacade().storePatient(patient,fpcase,history);
		log4log.error("historyId: "+history.getHistoryId());
		if(val)
		{
			if(lastExtIdH.size()>0)
			{
				LastRecord lr = (LastRecord)lastExtIdH.get(0);
				lr.setLastId(history.getHistoryId());
				this.getFpportalFacade().updateLastRecord(lr);
			}
			else
			{
				LastRecord lr = new LastRecord();
				lr.setCode("HS");
				lr.setLastId(history.getHistoryId());
				log4log.error("GGGGGGGGGGGGGGGGG");
				this.getFpportalFacade().storeLastRecord(lr);
			}
			
			if(lastExtIdC.size()>0)
			{
				LastRecord lr = (LastRecord)lastExtIdC.get(0);
				lr.setLastId(fpcase.getCaseId());
				this.getFpportalFacade().updateLastRecord(lr);
			}
			else
			{
				LastRecord lr = new LastRecord();
				lr.setCode("CS");
				lr.setLastId(fpcase.getCaseId());
				this.getFpportalFacade().storeLastRecord(lr);
			}
			
			if(lastExtIdP.size()>0)
			{
				LastRecord lr = (LastRecord)lastExtIdP.get(0);
				lr.setLastId(patient.getPatientId());
				this.getFpportalFacade().updateLastRecord(lr);
			}
			else
			{
				LastRecord lr = new LastRecord();
				lr.setCode("PT");
				lr.setLastId(patient.getPatientId());
				this.getFpportalFacade().storeLastRecord(lr);
			}
			
			this.setPatientIdd(patient.getPatientId());
			
		return true;
		
		}
		else
		{
			return false;
		}
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}

	public boolean deletePatient(Patient patient)
	{
		
		try
		{
			log4log.error("delete patient"+patient.getFirstName());
		
		
		String oldPhoto="";
		String pathname="";
		List patList=this.getFpportalFacade().loadPatientById(patient.getPatientId());
		Patient doc = (Patient)patList.get(0);
		oldPhoto=doc.getPhoto();
		
		log4log.error("oldPhoto: "+oldPhoto);
		
		pathname = getFilePath()+"/"+oldPhoto;
		log4log.error("pathname: "+pathname);
		File photopath=new File(pathname);
		FPTelemedicineUtils.deletePhoto(photopath);
		log4log.error("before delete");
		List opencaseList = this.getFpportalFacade().loadOnlyOpenCase(patient.getPatientId());
		List caseList= this.getFpportalFacade().loadCaseFPByPatientId(patient.getPatientId());
		log4log.error("caseList : "+caseList.size());
		if(!(opencaseList.size() > 0))
		{
		boolean val =this.getFpportalFacade().deleteHistory(patient.getPatientId());
		if(val)
		{
		if(caseList.size() > 0)
		{
		
			for(int i=0;i<caseList.size();i++)
			 {
				 FPCase fpcase = (FPCase)caseList.get(i);
				 List caseRefList = this.getFpportalFacade().loadCaseReferralCaseId(fpcase.getCaseId());
				 if(caseRefList.size() > 0)
				 {
					 for(int j=0;j<caseRefList.size();j++)
					 {
						 CaseReferral caseRef = (CaseReferral)caseRefList.get(j);
					 this.getFpportalFacade().deleteCaseReferral(caseRef);
					 
					 }
				 }
				 
			 }
			
		 for(int i=0;i<caseList.size();i++)
		 {
			 FPCase fpcase = (FPCase)caseList.get(i);
			 List labtestList = this.getFpportalFacade().loadLabTestByCaseId(fpcase.getCaseId());
			 if(labtestList.size() > 0)
			 {
				 for(int j=0;j<labtestList.size();j++)
				 {
				 LabTest labtest = (LabTest)labtestList.get(j);
				 this.getFpportalFacade().deleteLabtest(labtest);
				 
				 }
			 }
			 this.getFpportalFacade().deleteFPCase(fpcase,patient.getPatientId());
			 log4log.error("aaaa");
		 }
		 log4log.error("aaaabbbb");
		 
		}
		log4log.error("before delete patient");
		this.getFpportalFacade().deletePatientdb(patient);
		}
		return true;
		}
		else
		{
			return false;
		}
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public boolean editeachPatient(Patient patient)
	{
		try
		{
		log4log.error("edit each patient"+patient.getTstamp());
		String oldPhoto="";
		String newPhoto="";
		String pathname="";
		String age = getAgeModified(patient.getAgeYears(),patient.getAgeMonths());
		
		List patList=this.getFpportalFacade().loadPatientById(patient.getPatientId());
		Patient doc = (Patient)patList.get(0);
		oldPhoto=doc.getPhoto();
		newPhoto=patient.getPhoto();
		log4log.error("oldPhoto: "+oldPhoto);
		log4log.error("newPhoto: "+newPhoto);
		pathname = getFilePath()+"/"+oldPhoto;
		log4log.error("pathname: "+pathname);
		File photopath=new File(pathname);
		if(oldPhoto !=null && newPhoto != null)
		{
		if(!oldPhoto.equals(newPhoto))
		{
			log4log.error("oldPhoto.equals(newPhoto) :"+oldPhoto.equals(newPhoto));
		  FPTelemedicineUtils.deletePhoto(photopath); 
		}
		}
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		patient.setCreator(doc.getCreator());
		patient.setDob(age);
		patient.setTstamp(tstamp);
		this.getFpportalFacade().editPatient(patient);
		return true;
	  }
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public List searchPatientdwr(String patientName, String loginId)
	{
		log4log.error("inside search patient"+patientName+"  "+loginId);
		patientList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(patientName);
		patientList = this.getFpportalFacade().searchPatienth(str.trim(),loginId);
		log4log.error("patientList Size : "+patientList.size());
        List patientSearch =  new ArrayList();
        if(patientName == null)
        {
        	patientName="";
        }
		if(patientName.trim().equals(""))
		{
			for(int i=0;i < patientList.size() ; i++)
			{
				
				patient= (Patient)patientList.get(i);
				patient = FPTelemedicineUtils.getAge(patient);
				List caseList = this.getFpportalFacade().loadCaseFPByPatientId(patient.getPatientId());
				if(patient.getCreator().getDoctorId().equals(loginId))
				{
				for(int k=0;k<caseList.size();k++)
				{
				 FPCase fpCase =(FPCase) caseList.get(k);
					
				if(fpCase.getCaseStatus().equals("OPEN"))
				{
					 List listCaseRef = this.getFpportalFacade().loadCaseReferralCaseId(fpCase.getCaseId());
					 if(listCaseRef.size() > 0)
					 {
						 List tempList = new ArrayList();
						 for(int g=0;g< listCaseRef.size();g++)
						 {
						 CaseReferral caseRef = (CaseReferral)listCaseRef.get(g);
						 tempList.add(caseRef);
						 }
						 patient.setCaseRefList(tempList);
					 }
					 List listlistLabRep = this.getFpportalFacade().loadLabTestByCaseId(fpCase.getCaseId());
					 if(listlistLabRep.size() > 0)
					 {
						 List tempList1 = new ArrayList();
						 for(int g=0;g< listlistLabRep.size();g++)
						 {
						 LabTest labTest = (LabTest)listlistLabRep.get(g);
						 tempList1.add(labTest);
						 }
						 patient.setLabTestRep(tempList1);
					 }
					if(!patientSearch.contains(patient.getPatientId()))
		            {	
						patientSearch.add(patient);
						patientSearch.add(true);
				    }
				 }
				}
				}
			}
			
			
		  
		}
		else
		{
			
			for(int i=0;i < patientList.size() ; i++)
			{
				Boolean flag=false;
				patient= (Patient)patientList.get(i);
				List caseList = this.getFpportalFacade().loadCaseFPByPatientId(patient.getPatientId());
				if(patient.getCreator().getDoctorId().equals(loginId))
				{
				patient=  FPTelemedicineUtils.getAge(patient);
				
				for(int k=0;k<caseList.size();k++)
				{
				 FPCase fpCase =(FPCase) caseList.get(k);
				 List listCaseRef = this.getFpportalFacade().loadCaseReferralCaseId(fpCase.getCaseId());
				 if(listCaseRef.size() > 0)
				 {
					 List tempList = new ArrayList();
					 for(int g=0;g< listCaseRef.size();g++)
					 {
					 CaseReferral caseRef = (CaseReferral)listCaseRef.get(g);
					 tempList.add(caseRef);
					 }
					 patient.setCaseRefList(tempList);
				 }
				 List listlistLabRep = this.getFpportalFacade().loadLabTestByCaseId(fpCase.getCaseId());
				 if(listlistLabRep.size() > 0)
				 {
					 List tempList1 = new ArrayList();
					 for(int g=0;g< listlistLabRep.size();g++)
					 {
					 LabTest labTest = (LabTest)listlistLabRep.get(g);
					 tempList1.add(labTest);
					 }
					 patient.setLabTestRep(tempList1);
				 }
				if(fpCase.getCaseStatus().equals("OPEN"))
				{
		    			flag=true;
						break;
				    
				 }
				}
				patientSearch.add(patient);
				patientSearch.add(flag);
				}
			}
			
		}
		
		
		log4log.error("Later Size : "+patientSearch.size());
		return patientSearch;
		
	}
	
	public List searchPatientdwrNo(String patientName,String phoneNo, String loginId)
	{
		log4log.error("inside search patient"+patientName+"  "+loginId);
		patientList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(patientName);
		patientList = this.getFpportalFacade().searchPatienthNo(str.trim(),phoneNo,loginId);
		log4log.error("patientList Size : "+patientList.size());
        List patientSearch =  new ArrayList();
        if(patientName == null)
        {
        	patientName="";
        }
		if(patientName.trim().equals(""))
		{
			for(int i=0;i < patientList.size() ; i++)
			{
				
				patient= (Patient)patientList.get(i);
				patient = FPTelemedicineUtils.getAge(patient);
				List caseList = this.getFpportalFacade().loadCaseFPByPatientId(patient.getPatientId());
				if(patient.getCreator().getDoctorId().equals(loginId))
				{
				for(int k=0;k<caseList.size();k++)
				{
				 FPCase fpCase =(FPCase) caseList.get(k);
					
				if(fpCase.getCaseStatus().equals("OPEN"))
				{
					 List listCaseRef = this.getFpportalFacade().loadCaseReferralCaseId(fpCase.getCaseId());
					 if(listCaseRef.size() > 0)
					 {
						 List tempList = new ArrayList();
						 for(int g=0;g< listCaseRef.size();g++)
						 {
						 CaseReferral caseRef = (CaseReferral)listCaseRef.get(g);
						 tempList.add(caseRef);
						 }
						 patient.setCaseRefList(tempList);
					 }
					 List listlistLabRep = this.getFpportalFacade().loadLabTestByCaseId(fpCase.getCaseId());
					 if(listlistLabRep.size() > 0)
					 {
						 List tempList1 = new ArrayList();
						 for(int g=0;g< listlistLabRep.size();g++)
						 {
						 LabTest labTest = (LabTest)listlistLabRep.get(g);
						 tempList1.add(labTest);
						 }
						 patient.setLabTestRep(tempList1);
					 }
					if(!patientSearch.contains(patient.getPatientId()))
		            {	
						patientSearch.add(patient);
						patientSearch.add(true);
				    }
				 }
				}
				}
			}
			
			
		  
		}
		else
		{
			
			for(int i=0;i < patientList.size() ; i++)
			{
				Boolean flag=false;
				patient= (Patient)patientList.get(i);
				List caseList = this.getFpportalFacade().loadCaseFPByPatientId(patient.getPatientId());
				if(patient.getCreator().getDoctorId().equals(loginId))
				{
				patient=  FPTelemedicineUtils.getAge(patient);
				
				for(int k=0;k<caseList.size();k++)
				{
				 FPCase fpCase =(FPCase) caseList.get(k);
				 List listCaseRef = this.getFpportalFacade().loadCaseReferralCaseId(fpCase.getCaseId());
				 if(listCaseRef.size() > 0)
				 {
					 List tempList = new ArrayList();
					 for(int g=0;g< listCaseRef.size();g++)
					 {
					 CaseReferral caseRef = (CaseReferral)listCaseRef.get(g);
					 tempList.add(caseRef);
					 }
					 patient.setCaseRefList(tempList);
				 }
				 List listlistLabRep = this.getFpportalFacade().loadLabTestByCaseId(fpCase.getCaseId());
				 if(listlistLabRep.size() > 0)
				 {
					 List tempList1 = new ArrayList();
					 for(int g=0;g< listlistLabRep.size();g++)
					 {
					 LabTest labTest = (LabTest)listlistLabRep.get(g);
					 tempList1.add(labTest);
					 }
					 patient.setLabTestRep(tempList1);
				 }
				if(fpCase.getCaseStatus().equals("OPEN"))
				{
		    			flag=true;
						break;
				    
				 }
				}
				patientSearch.add(patient);
				patientSearch.add(flag);
				}
			}
			
		}
		
		
		log4log.error("Later Size : "+patientSearch.size());
		return patientSearch;
		
	}
	
	/*public List loadIndividualPatient(String patientId)
	{
		log4log.error("inside loadIndividualPatient: "+patientId);
		patientList =this.getFpportalFacade().loadPatientById(patientId);
		log4log.error("patientList: "+patientList);
		return patientList;
	}*/
	
	public void fileUploadStart()
	{
		 System.out.println("inside fileUploadStart");
		 
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		System.out.println("request: "+request);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		System.out.println("multipartRequest: "+multipartRequest);
		
    	MultipartFile filea = multipartRequest.getFile("file1");
    	System.out.println(" filea: "+ filea);
    		
try
{
    	InputStream inputStream = null;
    	OutputStream outputStream = null;
    	UploadInfoBean uib = this.getUploadInfoBeanInst();
    	bytesTransferred=0;
    	if (filea.getSize() > 0) {
  	    inputStream = filea.getInputStream();
  	    fileSize=filea.getSize();
  	    
  	     String fileName = FPTelemedicineUtils.getUniqueFileName(getFilePath(),filea.getOriginalFilename());
  	    
  	    System.out.println("fileSize : "+fileSize + "fileName: "+fileName);
  	    File realUpload = new File(getFilePath());
  	    //realUpload.mkdir();
    	outputStream = new FileOutputStream(fileName);
   
    	System.out.println("====22=========");
    	
    	System.out.println(filea.getOriginalFilename());
    	
    	System.out.println("=============");
    	
    	int readBytes = 0;
    	byte[] buffer = new byte[8192];
    	while ((readBytes = inputStream.read(buffer, 0 , 8192))!=-1){
    	System.out.println("inside while loop ");
    	bytesTransferred = bytesTransferred + readBytes;
    	System.out.println("bytesTransferred: "+bytesTransferred);
    	outputStream.write(buffer, 0, readBytes);
    	    	
    	String per = NumberFormat.getPercentInstance().format((double) bytesTransferred / (double) fileSize);
    	for(int k=0;k<1000;k++)
    	{
    		//System.out.println(k);
    	}
    	
    	uib.setPercentage(per.replace("%", ""));
    	if(per.replace("%", "").equals("100"))
    	{
    		uib.setPercentage("0");
    	}
    	System.out.println("per : "+per.replace("%", ""));
    	System.out.println("fileSize : "+fileSize + "fileName: "+fileName);
    	}
    	outputStream.close();
    	inputStream.close();
    	}
}
catch(Exception e)
{
	System.out.println("Exception : "+e);
}

    	
	}
	
	
	public String loadPercentage()
	{
		return uib.getPercentage();
	}
	
	private String getAgeModified(String yrs,String months)
	{
		//converting yrs and months to integer
		int i_months = 0;
		try
		{
			if(!yrs.equals(""))
			{
				i_months = (new Integer(yrs)).intValue()*12;
			}
			if(!months.equals(""))
			{
				i_months = i_months + (new Integer(months)).intValue();			
			}
			
		}
		catch(Exception e)
		{
			log4log.error("Error while processing age conversion due to : "+e.toString());
			return null;
		}
		//rolling back to the date previous to the as many number of months
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,-i_months);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log4log.debug("dob : "+sdf.format(calendar.getTime()));
		return sdf.format(calendar.getTime());
	}
	
	public String getPatientIdd()
	{
		log4log.error("eeeeeeeeeeeeeeeeeee:  "+this.patientIdd);
		return this.patientIdd;
	}
	private void setPatientIdd(String patientId)
	{
		log4log.error("fffffffffffffffffffff");
		this.patientIdd = patientId;
		
	}
	
	public void readPatientOpinion(String refId)
	{
		try
		{
			log4log.error("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh    "+refId);	
		
		List refList =	this.getFpportalFacade().loadCaseReferralByReferralId(refId);
		if(refList.size() > 0)
		{
		CaseReferral 	caseReff = (CaseReferral)refList.get(0);
		caseReff.setFlag("READOPI");
		this.fpportalFacade.editCaseReferral(caseReff);
		}
		}
		catch(Exception e)
		{
			log4log.error("Exception due to : "+e);
		}
	}
	
	public void readPatientReport(String labTestId)
	{
		try
		{
			log4log.error("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr    "+labTestId);	
		
		List labTestList =	this.getFpportalFacade().loadLabTestByLabTestId(labTestId);
		if(labTestList.size() > 0)
		{
		LabTest 	labTestRep = (LabTest)labTestList.get(0);
		labTestRep.setFlag("READREP");
		this.fpportalFacade.editLabTest(labTestRep);
		}
		}
		catch(Exception e)
		{
			log4log.error("Exception due to : "+e);
		}
	}
	
	public int newRequest(String loginId)
	{
		int newReq =0;
	
        List tempList = this.getFpportalFacade().loadFavFPBySEPId(loginId);
      
			for(int j=0; j< tempList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)tempList.get(j);
				log4log.error("getFamilyPhysicianId : "+doc.getFamilyPhysicianId().getDoctorId());
				Login login = (Login)this.getFpportalFacade().loadLogin(doc.getFamilyPhysicianId().getDoctorId());
				log4log.error(" Status : "+login.getAccountStatus());
				if(doc.getFlag() != null)
				{
					if(doc.getFlag().equals("NOTREADSEP") && login.getAccountStatus().equalsIgnoreCase("ENABLED"))
					{
						newReq = newReq+1;
					}
				}
				
			}
			
		
		return newReq;
		
	}
}

