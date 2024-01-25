package com.televital.fptelemedicine.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.televital.fptelemedicine.domain.CaseReferral;
import com.televital.fptelemedicine.domain.FavLab;
import com.televital.fptelemedicine.domain.History;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.LabServices;
import com.televital.fptelemedicine.domain.LabTest;
import com.televital.fptelemedicine.domain.LastRecord;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.domain.Person;
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.domain.form.ViewPatientOrderTestBackingObject;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.LabManagementValidator;


public class LabServicesManagement {
	
	public static final Logger log4log = Logger.getLogger(LabServicesManagement.class);
	
	private IFPTelemedicineFacade fpportalFacade;
	private List labServiceList = null;
	private List labTestList = null;
	private ILicenseCodec licenseCodec;
	private static long bytesTransferred = 0;
	private static long fileSize = -100;
	private String labServiceId;
	private LabServices labService =null;
	private LabTest labTest = null;
	private Lab lab =null;
	private Errors error;
	List lastExtId = null;
	private String dataPath;
	private String labReportPath; 

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
	
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public String getLabReportPath() {
		return labReportPath;
	}

	public void setLabReportPath(String labReportPath) {
		this.labReportPath = labReportPath;
	}
	
		
	public List loaddwrLabService(String labId)
	{
		log4log.error("inside load labservices "+labId);
		labServiceList =this.getFpportalFacade().loadLabServices(labId);
		List labServiceListO =  new ArrayList();
		
		log4log.error("Size : "+labServiceList.size());
		
		for(int i=0;i < labServiceList.size() ; i++)
		{
			labService= (LabServices)labServiceList.get(i);
			labServiceListO.add(labService);
		}
		log4log.error("Later Size : "+labServiceListO.size());
		return labServiceList;
		
	}
	
	public boolean saveServiceLab(LabServices labService,String labId)
	{
		log4log.error("new record: "+labService.getServiceName());
		
		try
		{
			
          List labServicesList= this.getFpportalFacade().loadLabServices();
		  List labList = this.getFpportalFacade().loadLabById(labId);
		  lastExtId = this.getFpportalFacade().findLastExternalId("LS");
		  String newId = FPTelemedicineUtils.generateNewExternalId("LS",lastExtId);
		  labServiceId = newId;
		  if(labList.size()>0)
		  {
			  lab = (Lab)labList.get(0);
		  }
          
		/*	if(!(labServicesList.size()>0))
			{
				labServiceId="1";
			}
			else
			{
			for(int i=0; i<labServicesList.size();i++)
			{
				LabServices labServiceF=(LabServices)labServicesList.get(i);
				labServiceId=labServiceF.getLabServiceId();
				log4log.error("historyId  : "+labServiceId);
			}
			int tempval = Integer.parseInt(labServiceId);
			labServiceId=Integer.toString(tempval+1);
			
			}	*/
			log4log.error("one "+labServiceId);
		labService.setLab(lab);
		labService.setLabServiceId(labServiceId);
		log4log.error("one "+labServiceId);
		this.getFpportalFacade().storeLabService(labService);
		log4log.error("one "+labServiceId);
		if(lastExtId.size()>0)
		{
			LastRecord lr = (LastRecord)lastExtId.get(0);
			lr.setLastId(labService.getLabServiceId());
			this.getFpportalFacade().updateLastRecord(lr);
		}
		else
		{
			LastRecord lr = new LastRecord();
			lr.setCode("LS");
			lr.setLastId(labService.getLabServiceId());
			this.getFpportalFacade().storeLastRecord(lr);
		}
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}

	public boolean deleteLabServices(LabServices labServices)
	{
		try
		{
		log4log.error("delete lab Service"+labServices.getLabServiceId());
		this.getFpportalFacade().deleteLabService(labServices);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public boolean editeachLabService(LabServices labService,String labId)
	{
		try
		{
		log4log.error("edit each lab"+labService.getLabServiceId());
		List labList = this.getFpportalFacade().loadLabById(labId);
		if(labList.size()>0)
		{
			lab =(Lab)labList.get(0);
		}
		
		labService.setLab(lab);
		
		this.getFpportalFacade().editLabService(labService);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
	
	public List searchLabServicesdwr(String labServiceName)
	{
		log4log.error("inside search labService"+labServiceName);
		labServiceList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(labServiceName);
		labServiceList = this.getFpportalFacade().searchLabServiceh(str.trim());
		log4log.error("labList Size : "+labServiceList.size());
        List labSearch =  new ArrayList();
				
		for(int i=0;i < labServiceList.size() ; i++)
		{
			labService= (LabServices)labServiceList.get(i);
			labSearch.add(labService);
		}
		log4log.error("Later Size : "+labSearch.size());
		return labSearch;
		
	}
	
	public List showLabServices(Lab lab)
	{
		log4log.error("inside show "+lab);
		log4log.error("labId: "+lab.getLabId());
		
		labServiceList = null;
		labServiceList = this.getFpportalFacade().loadLabServiceByLabId(lab.getLabId());
		log4log.error("labList Size : "+labServiceList.size());
        List labSearch =  new ArrayList();
				
		for(int i=0;i < labServiceList.size() ; i++)
		{
			labService= (LabServices)labServiceList.get(i);
			labSearch.add(labService);
		}
		log4log.error("Later Size : "+labSearch.size());
		return labSearch;
		
	}
	
	public List showdwrServices(LabTest labTest)
	{
		log4log.error("inside showServices caseId =  "+labTest.getFpcase().getCaseId());
	
		
		labTestList = null;
		labTestList = this.getFpportalFacade().loadLabTestByCaseId(labTest.getFpcase().getCaseId());
		
		log4log.error("labTestList Size : "+labTestList.size());
        List labSearch =  new ArrayList();
				
		for(int i=0;i < labTestList.size() ; i++)
		{
			labTest= (LabTest)labTestList.get(i);
			labSearch.add(labTest);
		}
		log4log.error("Later Size : "+labSearch.size());
		return labSearch;
		
	}
	
	public List loadPatient(String labId)
	{
       List labServicesList = this.getFpportalFacade().loadLabServiceByLabId(labId);
		
		log4log.error("Lab Services list size: "+labServicesList.size());
		List labTestListtemp = new ArrayList();
		//Set labTestListtemp = new HashSet();
       List tempListPat = new ArrayList();
		
	for(int j=0;j<labServicesList.size();j++)
	{
		LabServices labServices = (LabServices)labServicesList.get(j);
		
		
		
		String labServiceId = labServices.getLabServiceId();
		List labTestList = this.getFpportalFacade().loadLabTestByServiceId(labServiceId);
		
		for(int k =0;k<labTestList.size();k++)
		{
			LabTest labTest = (LabTest)labTestList.get(k);
			log4log.error("case is open or closed: "+labTest.getFpcase().getCaseId()+" is "+ labTest.getFpcase().getCaseStatus());
			log4log.error("labTest.getLabServices().getLab().getLabId() : "+labTest.getLabServices().getLab().getLabId());
			log4log.error(labTest.getLabServices().getLab().getLabId().equals(labId));
			if(labTest.getLabServices().getLab().getLabId().equals(labId))
			{
			if(labTest.getFpcase().getCaseStatus().equals("OPEN"))
			{
			Patient patient = labTest.getFpcase().getPatient();
			patient=  FPTelemedicineUtils.getAge(patient);
			labTest.getFpcase().setPatient(patient);
			log4log.error("Age: "+labTest.getFpcase().getPatient().getAgeYears());
			log4log.error("patient name: "+patient.getFirstName());
			//labTestListtemp =  new ArrayList();
			patient.setLabTest(labTest);
			
            if(!tempListPat.contains(patient.getPatientId()))
            {
            labTestListtemp.add(labTest);
            }
            
            tempListPat.add(patient.getPatientId());
			}
			}
            //list.add(labService);
		}
		
	}
	log4log.error("labTestListtemp.size "+labTestListtemp.size());
	
	return labTestListtemp;
		
	}
	
	public List searchPatientListdwr(String patientName, String labId)
	{
		 List labServicesList = this.getFpportalFacade().loadLabServiceByLabId(labId);
			
			log4log.error("Lab Services list size: "+labServicesList.size());
			List labTestListtemp = new ArrayList();
			//Set labTestListtemp = new HashSet();
	       List tempListPat = new ArrayList();
			
		for(int j=0;j<labServicesList.size();j++)
		{
			LabServices labServices = (LabServices)labServicesList.get(j);
			String labServiceId = labServices.getLabServiceId();
			List labTestList = this.getFpportalFacade().loadLabTestByServiceId(labServiceId);
			
			for(int k =0;k<labTestList.size();k++)
			{
				LabTest labTest = (LabTest)labTestList.get(k);
				
				if(labTest.getLabServices().getLab().getLabId().equals(labId))
				{
				if(labTest.getFpcase().getCaseStatus().equals("OPEN"))
				{
				
				Patient patient = labTest.getFpcase().getPatient();
				String name = patient.getFirstName()+" "+patient.getLastName();
				if(patient.getFirstName().contains(patientName) || patient.getLastName().contains(patientName) || name.contains(patientName))
				{
				patient=  FPTelemedicineUtils.getAge(patient);
				labTest.getFpcase().setPatient(patient);
				log4log.error("Age: "+labTest.getFpcase().getPatient().getAgeYears());
				log4log.error("patient name: "+patient.getFirstName());
				//labTestListtemp =  new ArrayList();
				
				
	            if(!tempListPat.contains(patient.getPatientId()))
	            {
	            labTestListtemp.add(labTest);
	            }
	            tempListPat.add(patient.getPatientId());
	            //list.add(labService);
				}
				}
				}
			}
		}
		log4log.error("labTestListtemp.size "+labTestListtemp.size());
		
		return labTestListtemp;
			
	}
	
	
	public boolean updateLabTest(String caseId, String labServiceId,String reportname)
	{
		try
		{
			log4log.error("inside updateLabTesthhhhhhhhhhhhhhhhhhhhhhhhhh");
		 List labTestList = this.getFpportalFacade().loadLabTestByCaseIdServiceId(caseId,labServiceId);
		 if(labTestList.size()>0)
		 {
			 log4log.error("labTestList.size(): "+labTestList.size()+reportname);
			 LabTest labtest = (LabTest)labTestList.get(0);
			 labtest.setTestReport(reportname);
			 labtest.setFlag("NOTREADREP");
			 this.getFpportalFacade().editLabTest(labtest);
		 }
		
		 return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception due to : "+e);
			return false;
		}
	
		
		
			
	}
	
	public boolean deleteLabTest(String labServiceId)
	{
		try
		{
		log4log.error("delete lab test"+labServiceId);
		List tempList = this.getFpportalFacade().loadLabServiceByLabServiceId(labServiceId);
		if(tempList.size()>0)
		{
			LabServices labservice =(LabServices)tempList.get(0);
			List labList = this.getFpportalFacade().loadLabTestByServiceId(labservice.getLabServiceId());
			LabTest labtest = (LabTest)labList.get(0);
			if(labtest.getTestReport() == null)
			{
		      this.getFpportalFacade().deleteLabtest(labtest);
		      return true;
			}
			
		}
		return false;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}

	public boolean deleteCaseReferralDwr(String caseReferralId)
	{
		try
		{
		log4log.error("delete CaseReferral"+caseReferralId);
		List tempList = this.getFpportalFacade().loadCaseReferralByReferralId(caseReferralId);
		if(tempList.size()>0)
		{
			CaseReferral caseRef =(CaseReferral)tempList.get(0);
			if(caseRef.getOpinions().equalsIgnoreCase("No Opinion"))
			{
		         this.getFpportalFacade().deleteCaseReferral(caseRef);
		         return true;
			}
		
		}
		return false;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public boolean deleteServiceReport(String labTestId,String fileNamed)
	{
		try
		{
		
		log4log.error("delete servicereport "+labTestId);
		List tempList = this.getFpportalFacade().loadLabTestByLabTestId(labTestId);
		if(tempList.size()>0)
		{
			LabTest labTest = (LabTest)tempList.get(0);
			
			
			
			String caseId = labTest.getFpcase().getCaseId();
			String patientId = labTest.getFpcase().getPatient().getPatientId();
			String fileNames=labTest.getTestReport();
			String temp="";
			if(fileNames != null)
			{
				String fileNameList[]=fileNames.split("#####");
				for(int j=0;j<fileNameList.length;j++)
				{
					if(!(fileNameList[j].equals(fileNamed)))
					{
						
						if(temp=="")
						{
							temp=fileNameList[j];
						
						}
						else
						{
							
							temp=temp+"#####"+fileNameList[j];
							
						}
					}
				}
			}
			//String fileName = labTest.getTestReport();
			String pathname = getDataPath()+"/"+patientId+"/"+caseId+"/"+getLabReportPath()+"/"+fileNamed;
			log4log.error("pathname: "+pathname);
			File filepath=new File(pathname);
			labTest.setTestReport(temp);
			this.getFpportalFacade().editLabTest(labTest);
			FPTelemedicineUtils.deletePhoto(filepath);
		}
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
		
	}
	
	public int newRequest(String loginId)
	{
		int newReq =0;
	
        List tempList = this.getFpportalFacade().loadFavFPByLabId(loginId);
      
			for(int j=0; j< tempList.size();j++)
			{
				FavLab labr = (FavLab)tempList.get(j);
				log4log.error("getFamilyPhysicianId : "+labr.getFamilyPhysicianId().getDoctorId());
				Login login = (Login)this.getFpportalFacade().loadLogin(labr.getFamilyPhysicianId().getDoctorId());
				log4log.error(" Status : "+login.getAccountStatus());
				if(labr.getFlag() != null)
				{
					if(labr.getFlag().equals("NOTREADLAB") && login.getAccountStatus().equalsIgnoreCase("ENABLED"))
					{
						newReq = newReq+1;
					}
				}
				
			}
		
		return newReq;
		
	}
	
	public void readPatientReferral(LabTest labTest)
	{
		labTest.setFlag("READLAB");
		this.fpportalFacade.editLabTest(labTest);
	}
}
