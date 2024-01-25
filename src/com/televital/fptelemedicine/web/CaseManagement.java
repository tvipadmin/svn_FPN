package com.televital.fptelemedicine.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.televital.fptelemedicine.domain.CaseReferral;
import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.FPCase;
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
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.CaseManagementValidator;
import com.televital.fptelemedicine.validation.LabManagementValidator;
import com.televital.fptelemedicine.domain.FavSpecalist;

public class CaseManagement {

public static final Logger log4log = Logger.getLogger(CaseManagement.class);
	
	private IFPTelemedicineFacade fpportalFacade;
	private List caseList = null;
	private UploadInfoBean uib;
	private ILicenseCodec licenseCodec;
	private static long bytesTransferred = 0;
	private static long fileSize = -100;
	private String filePath;
	private String dataPath;
	private String casePath;
	private String historyPath;
	private FPCase fpcase =null;
	private UploadInfoBean uploadInfoBeanInst; 
	private String historyId;
	private Patient patient;
	private Doctor doctor;
	private History history;
	private List specalistList;
	private List fpnList;
	private List fpList;
	private List favList;
	List lastExtId = null;
	
	private Errors error;
	private CaseManagementValidator cmv = new CaseManagementValidator();
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
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public String getCasePath() {
		return casePath;
	}

	public void setCasePath(String casePath) {
		this.casePath = casePath;
	}
	
	public String getHistoryPath() {
		return historyPath;
	}

	public void setHistoryPath(String historyPath) {
		this.historyPath = historyPath;
	}
	
	public UploadInfoBean getUploadInfoBeanInst() {
		return uploadInfoBeanInst;
	}
	public void setUploadInfoBeanInst(UploadInfoBean uploadInfoBeanInst) {
		this.uploadInfoBeanInst = uploadInfoBeanInst;
	}
	
	public boolean saveCaseHistory(FPCase fpCase, History history,String patientId,String doctorId,String labServiceIds)
	{
		log4log.error("inside FPCase"+fpCase.getCaseId()+"  "+fpCase.getStarttstamp()+fpCase.getEndtstamp());
		log4log.error("inside FPCase"+patientId+"history attachment :"+history.getAttachments());
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		String ststamp = fpCase.getStarttstamp();
		String etstamp = fpCase.getEndtstamp();
		List docList = this.getFpportalFacade().loadDoctorById(doctorId);
		
		List patList = this.getFpportalFacade().loadPatientById(patientId);
		String caseatt= fpCase.getCaseattachments();
		String historyatt = history.getAttachments();
		if(historyatt!=null)
		{
			if(historyatt.trim().equals(""))
			{
				history.setAttachments(null);
			}
		}
		if(caseatt!=null)
		{
			if(caseatt.trim().equals(""))
			{
				fpCase.setCaseattachments(null);
			}
		}
		log4log.error(docList.size());
		log4log.error(patList.size());
		try
		{
		  if(docList.size() > 0)
		  {
			  doctor = (Doctor)docList.get(0);
			  fpCase.setDoctor(doctor);
		  }
			if(patList.size() > 0)
			{
				 patient = (Patient)patList.get(0);
				if(patient.getTstamp().equals(fpCase.getStarttstamp()))
				{
					fpCase.setStarttstamp(tstamp);
					fpCase.setEndtstamp(etstamp);
					fpCase.setPatient(patient);
					fpCase.setCaseStatus("OPEN");
				}
				else
				{
					fpCase.setStarttstamp(ststamp);
					fpCase.setEndtstamp(etstamp);
					fpCase.setPatient(patient);
					fpCase.setCaseStatus("OPEN");
				}
			}
		
		//fpcase.setEndtstamp(tstamp);
			List historyList= this.getFpportalFacade().loadHistory();
			log4log.error("yyyyyyyyyyyyyyyyyyyyyy");
			lastExtId = this.getFpportalFacade().findLastExternalId("HS");
			String newIdh = FPTelemedicineUtils.generateNewExternalId("HS",lastExtId);
			historyId = newIdh;
			
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
		log4log.error("historyId  : "+historyId);	
		log4log.error("Docor : "+fpCase.getDoctor());
		log4log.error("DocorId  : "+fpCase.getDoctor().getDoctorId());
		history.setPatientId(patientId);
		history.setPatient(patient);
		history.setHistoryId(historyId);
		LabTest labtest = new LabTest();
		List labTestList= new ArrayList();
		String labTestId="";
		this.getFpportalFacade().storeCaseHistory(fpCase,history);
		
		if(lastExtId.size()>0)
		{
			LastRecord lr = (LastRecord)lastExtId.get(0);
			lr.setLastId(history.getHistoryId());
			this.getFpportalFacade().updateLastRecord(lr);
		}
		else
		{
			LastRecord lr = new LastRecord();
			lr.setCode("HS");
			lr.setLastId(history.getHistoryId());
			this.getFpportalFacade().storeLastRecord(lr);
		}
		if(labServiceIds != null)
		{
			String templabServiceIds[] = labServiceIds.split("#####");
			labtest.setFpcase(fpCase);
			
			for(int p=1;p<templabServiceIds.length;p++)
			{
				List labServiceList = this.getFpportalFacade().loadLabServiceByLabServiceId(templabServiceIds[p]);
				log4log.error("labServiceList.size(): "+labServiceList.size());
				LabServices labService = (LabServices)labServiceList.get(0);
				labtest.setLabServices(labService);
				labTestList= this.getFpportalFacade().loadLabTest();
				
				lastExtId = this.getFpportalFacade().findLastExternalId("LT");
				String newId = FPTelemedicineUtils.generateNewExternalId("LT",lastExtId);
				labTestId = newId;
				
				/*if(!(labTestList.size()>0))
				{
					labTestId="1";
				}
				else
				{
				for(int i=0; i<labTestList.size();i++)
				{
					LabTest labTestf=(LabTest)labTestList.get(i);
					labTestId=labTestf.getLabTestId();
					log4log.error("labTestId  : "+labTestId);
				}
				int tempval = Integer.parseInt(labTestId);
				labTestId=Integer.toString(tempval+1);
				
				}	
				*/
				labtest.setLabTestId(labTestId);
                labtest.setOrderedTime(tstamp);
				this.getFpportalFacade().storeLabTest(labtest);
				
				if(lastExtId.size()>0)
				{
					LastRecord lr = (LastRecord)lastExtId.get(0);
					lr.setLastId(labtest.getLabTestId());
					this.getFpportalFacade().updateLastRecord(lr);
				}
				else
				{
					LastRecord lr = new LastRecord();
					lr.setCode("LT");
					lr.setLastId(labtest.getLabTestId());
					this.getFpportalFacade().storeLastRecord(lr);
				}
			}
			
			
		}
		
	
				
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
	
	public boolean closeOpenCase(String caseId)
	{
		log4log.error("inside caseId "+caseId);
		List fpcaseList = this.getFpportalFacade().loadCaseFPByCaseId(caseId);
		FPCase fpCase= new FPCase();		
		log4log.error(fpcaseList.size());
	
		try
		{
		  if(fpcaseList.size() > 0)
		  {
			  fpCase = (FPCase)fpcaseList.get(0);
			  fpCase.setCaseStatus("CLOSED");
		  }
		
		this.getFpportalFacade().updateCaseOpen(fpCase);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}

	public boolean saveCase(FPCase fpCase,History historynew,String patientId,String doctorId,String labServiceIds,String docdetIds, CaseReferral caseReferralt,String docTypes,String caseReferralIdt)
	{
		log4log.error("inside FPCase"+fpCase.getCaseId()+"  "+fpCase.getStarttstamp()+fpCase.getEndtstamp()+"docType: "+docTypes+"caseReferralt: "+caseReferralt.getOpinions()+" doctorId : "+doctorId );
		
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		String ststamp =fpCase.getStarttstamp();
		String etstamp = fpCase.getEndtstamp();
		
		String caseAtt=fpCase.getCaseattachments();
		log4log.error("caseAtt : "+fpCase.getCaseattachments());
		FPCase fppen =(FPCase) this.getFpportalFacade().loadCaseFPByCaseId(fpCase.getCaseId()).get(0);
		String caseAttDB =fppen.getCaseattachments();
		log4log.error("caseAttDB : "+fppen.getCaseattachments());
		
		if(caseAtt!=null && caseAttDB != null)
		{
			if(!caseAtt.equals("") && !caseAttDB.equals(""))
			{
				fpCase.setCaseattachments(caseAtt+caseAttDB);
			}
			else if(!caseAtt.equals(""))
			{
				fpCase.setCaseattachments(caseAtt);
			}
			else if(!caseAttDB.equals(""))
			{
				fpCase.setCaseattachments(caseAttDB);
			}
			
		}
		else if(caseAtt!=null && caseAttDB==null )
		{
			if(!caseAtt.equals(""))
			{
			fpCase.setCaseattachments(caseAtt);
			}
		}
		else if(caseAtt==null && caseAttDB!=null)
		{
			if(!caseAttDB.equals(""))
			{
			fpCase.setCaseattachments(caseAttDB);
			}
		}
		
		
		
		List docList = this.getFpportalFacade().loadDoctorById(doctorId);
		
		List patList = this.getFpportalFacade().loadPatientById(patientId);
		
		List historyList = this.getFpportalFacade().loadHistoryByPatientId(patientId);
		
		
		
		log4log.error(docList.size());
		log4log.error(patList.size());
		log4log.error(historyList.size());
		//log4log.error("fpCase.getDoctor().getType(): "+fpCase.getDoctor().getType());
		try
		{
		  if(docList.size() > 0)
		  {
			  doctor = (Doctor)docList.get(0);
			  fpCase.setDoctor(doctor);
		  }
			if(patList.size() > 0)
			{
				 patient = (Patient)patList.get(0);
				if(patient.getTstamp().equals(fpCase.getStarttstamp()))
				{
					fpCase.setStarttstamp(tstamp);
					fpCase.setEndtstamp(etstamp);
					fpCase.setPatient(patient);
					fpCase.setCaseStatus("OPEN");
				}
				else
				{
					fpCase.setStarttstamp(ststamp);
					fpCase.setEndtstamp(etstamp);
					fpCase.setPatient(patient);
					fpCase.setCaseStatus("OPEN");
				}
			}
		
		//fpcase.setEndtstamp(tstamp);
			if(historyList.size() > 0)
			{
				history = (History)historyList.get(0);
				history.setAllergies(historynew.getAllergies());
				if(historynew.getAttachments() !=null)
				{
					if(historynew.getAttachments().trim().equals(""))
					{
					history.setAttachments(null);
					}
					else
					{
						history.setAttachments(historynew.getAttachments());
					}
				}
				else
				{
					history.setAttachments(null);
				}
				
				
				history.setCurrentMedication(historynew.getCurrentMedication());
				history.setFamilyHistory(historynew.getFamilyHistory());
				history.setImmunization(historynew.getImmunization());
				history.setPastIllness(historynew.getpastIllness());
				
			}
		
		log4log.error("Docor : "+fpCase.getDoctor());
		log4log.error("DocorId  : "+fpCase.getDoctor().getDoctorId());
		LabTest labtest = new LabTest();
		List labTestList= new ArrayList();
		String labTestId="";
		if(labServiceIds != null)
		{
			String templabServiceIds[] = labServiceIds.split("#####");
			
			
			labtest.setFpcase(fpCase);
			
			for(int p=1;p<templabServiceIds.length;p++)
			{
				List labServiceList = this.getFpportalFacade().loadLabServiceByLabServiceId(templabServiceIds[p]);
				log4log.error("labServiceList.size(): "+labServiceList.size());
				LabServices labService = (LabServices)labServiceList.get(0);
				labtest.setLabServices(labService);
				labTestList= this.getFpportalFacade().loadLabTest();
				
				lastExtId = this.getFpportalFacade().findLastExternalId("LT");
				String newId = FPTelemedicineUtils.generateNewExternalId("LT",lastExtId);
				labTestId = newId;
				
				/*if(!(labTestList.size()>0))
				{
					labTestId="1";
				}
				else
				{
				for(int i=0; i<labTestList.size();i++)
				{
					LabTest labTestf=(LabTest)labTestList.get(i);
					labTestId=labTestf.getLabTestId();
					log4log.error("labTestId  : "+labTestId);
				}
				int tempval = Integer.parseInt(labTestId);
				labTestId=Integer.toString(tempval+1);
				
				}	*/
				labtest.setLabTestId(labTestId);
				labtest.setFlag("NOTREADLAB");
				labtest.setOrderedTime(tstamp);
				this.getFpportalFacade().storeLabTest(labtest);
				
				if(lastExtId.size()>0)
				{
					LastRecord lr = (LastRecord)lastExtId.get(0);
					lr.setLastId(labtest.getLabTestId());
					this.getFpportalFacade().updateLastRecord(lr);
				}
				else
				{
					LastRecord lr = new LastRecord();
					lr.setCode("LT");
					lr.setLastId(labtest.getLabTestId());
					this.getFpportalFacade().storeLastRecord(lr);
				}
			}
			
			
		}
		
		if(docTypes.equals("fp"))
		{
		CaseReferral caseReferral = new CaseReferral();
		List caseReferralList= new ArrayList();
		String referralId="";
		
		if(docdetIds != null)
		{
			String sepdocIds[] = docdetIds.split("#####");
			
			caseReferral.setFpcase(fpCase);
			if(docList.size() > 0)
			  {
				  doctor = (Doctor)docList.get(0);
				  caseReferral.setFamilyPhysicianId(doctor);
			  }
			for(int p=1;p<sepdocIds.length;p++)
			{
				List docSepList = this.getFpportalFacade().loadDoctorById(sepdocIds[p]);
				log4log.error("docList.size(): "+docSepList.size());
				Doctor docSep = (Doctor)docSepList.get(0);
								
				caseReferral.setSpecalistId(docSep);
				caseReferral.setReferredTime(tstamp);
				caseReferral.setOpinions("No Opinion");			
				caseReferralList= this.getFpportalFacade().loadCaseReferral();
				

				 lastExtId = this.getFpportalFacade().findLastExternalId("RF");
				 String newId = FPTelemedicineUtils.generateNewExternalId("RF",lastExtId);
				 referralId = newId;
			/*	if(!(caseReferralList.size()>0))
				{
					referralId="1";
				}
				else
				{
				for(int i=0; i<caseReferralList.size();i++)
				{
					CaseReferral caseReferralf=(CaseReferral)caseReferralList.get(i);
					referralId=caseReferralf.getReferralId();
					log4log.error("referralId  : "+referralId);
				}
				int tempval = Integer.parseInt(referralId);
				referralId=Integer.toString(tempval+1);
				
				}	*/
				caseReferral.setReferralId(referralId);
				caseReferral.setFlag("NOTREADSEP");
				this.getFpportalFacade().storeCaseReferral(caseReferral);
				if(lastExtId.size()>0)
				{
					LastRecord lr = (LastRecord)lastExtId.get(0);
					lr.setLastId(caseReferral.getReferralId());
					this.getFpportalFacade().updateLastRecord(lr);
				}
				else
				{
					LastRecord lr = new LastRecord();
					lr.setCode("RF");
					lr.setLastId(caseReferral.getReferralId());
					this.getFpportalFacade().storeLastRecord(lr);
				}
			}
			
			
		}
		}
		else if(docTypes.equals("specalist"))
		{
			CaseReferral actualData = new CaseReferral();
			
			String caseReferralId = caseReferralt.getReferralId();
			log4log.error("caseReferralId: "+caseReferralIdt);
			
			List caseReferralList = this.getFpportalFacade().loadCaseReferralByReferralId(caseReferralIdt);
			log4log.error("caseReferralList size: "+caseReferralList.size());
			if(caseReferralList.size() > 0)
			{
			CaseReferral caseRefTemp = (CaseReferral)caseReferralList.get(0);
			actualData.setReferralId(caseRefTemp.getReferralId());
			actualData.setFpcase(fpCase);
			actualData.setOpinions(caseReferralt.getOpinions());
			actualData.setFlag("NOTREADOPI");
			actualData.setFamilyPhysicianId(caseRefTemp.getFamilyPhysicianId());
			actualData.setReferredTime(caseRefTemp.getReferredTime());
			actualData.setSpecalistId(caseRefTemp.getSpecalistId());
			this.getFpportalFacade().editCaseReferral(actualData);
			}
			
		}
		
		this.getFpportalFacade().updateCaseOpen(fpCase,history);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
	
	
	public boolean deleteHistoryAtt(String fileName, String caseId,String patientId)
	{
		try
		{
		log4log.error("delete HistoryAtt ");
		String oldPhoto="";
		String pathname="";
		List historyList=this.getFpportalFacade().loadHistoryByPatientId(patientId);
		if(historyList.size()>0)
		{
		History history = (History)historyList.get(0);
		String files = history.getAttachments();
		if(files != null)
		{
			String newfilelist="";
			String filelist[]=files.split("#####");
			for(int p=0;p<filelist.length;p++)
			{
			  String oldfile=filelist[p];
			  
			  if(!oldfile.equals(fileName))
			  {
				  if(newfilelist.equals(""))
				  {
					 newfilelist = oldfile; 
				  }
				  else
				  {
				  newfilelist=newfilelist+"#####"+oldfile;
				  }
			  }
			}
			history.setAttachments(newfilelist);
			this.getFpportalFacade().updateHistory(history);
		}
		}
		pathname = getDataPath()+"/"+patientId+"/"+getHistoryPath()+"/"+fileName;
		log4log.error("pathname: "+pathname);
		File filepath=new File(pathname);
		FPTelemedicineUtils.deletePhoto(filepath);
	//	Login login =this.getFpportalFacade().loadLogin(lab.getLabId());
		//this.getFpportalFacade().deleteCase(fpcase);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
	
	public boolean deleteCaseAtt(String fileName, String caseId,String patientId)
	{
		try
		{
		log4log.error("delete caseAtt ");
		String oldPhoto="";
		String pathname="";
		List caseList=this.getFpportalFacade().loadCaseFPByCaseId(caseId);
		FPCase dataCase = (FPCase)caseList.get(0);
		String files = dataCase.getCaseattachments();
		if(files != null)
		{
			String newfilelist="";
			String filelist[]=files.split("#####");
			for(int p=0;p<filelist.length;p++)
			{
			  String oldfile=filelist[p];
			  
			  if(!oldfile.equals(fileName))
			  {
				  if(newfilelist.equals(""))
				  {
					 newfilelist = oldfile; 
				  }
				  else
				  {
				  newfilelist=newfilelist+"#####"+oldfile;
				  }
			  }
			}
			dataCase.setCaseattachments(newfilelist);
			this.getFpportalFacade().updateCaseOpen(dataCase);
		}
		pathname = getDataPath()+"/"+patientId+"/"+caseId+"/"+getCasePath()+"/"+fileName;
		log4log.error("pathname: "+pathname);
		File filepath=new File(pathname);
		FPTelemedicineUtils.deletePhoto(filepath);
	//	Login login =this.getFpportalFacade().loadLogin(lab.getLabId());
		//this.getFpportalFacade().deleteCase(fpcase);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
/*	public boolean deleteCase(FPCase fpcase)
	{
		try
		{
		log4log.error("delete lab"+fpcase.getCaseStatus());
		String oldPhoto="";
		String pathname="";
		List caseList=this.getFpportalFacade().loadCaseById(fpcase.getCaseId());
		FPCase doc = (FPCase)caseList.get(0);
		
		
		//pathname = getFilePath()+"/"+oldPhoto;
		log4log.error("pathname: "+pathname);
		//File photopath=new File(pathname);
		//FPTelemedicineUtils.deletePhoto(photopath);
	//	Login login =this.getFpportalFacade().loadLogin(lab.getLabId());
		this.getFpportalFacade().deleteCase(fpcase);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public boolean editeachCase(FPCase fpcase)
	{
		try
		{
		log4log.error("edit each case"+fpcase.getTstamp());
		
		String pathname="";
		
		//pathname = getFilePath()+"/"+oldPhoto;
		log4log.error("pathname: "+pathname);
		//File photopath=new File(pathname);
		
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		fpcase.setTstamp(tstamp);
		this.getFpportalFacade().editCase(fpcase);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
	
	*/
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
	
	
	public List showSpecalist()
	{
		log4log.error("inside showSpecalist ");
		
		specalistList = null;
		specalistList = this.getFpportalFacade().loadSpecalityDoctor();
		log4log.error("specalistList Size : "+specalistList.size());
		
        List tempList =  new ArrayList();
				
		for(int i=0;i < specalistList.size() ; i++)
		{
			doctor= (Doctor)specalistList.get(i);
			tempList.add(doctor);
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public List showSpecalistLogin(String loginId)
	{
		log4log.error("inside showSpecalist ");
		
		specalistList = null;
		specalistList = this.getFpportalFacade().loadSpecalityDoctor();
		log4log.error("specalistList Size : "+specalistList.size());
		List favList = this.getFpportalFacade().loadFavSepByFPId(loginId);
        List tempList =  new ArrayList();
				
		for(int i=0;i < specalistList.size() ; i++)
		{
			doctor= (Doctor)specalistList.get(i);
			for(int j=0;j< favList.size();j++)
			{
			 FavSpecalist favSep = (FavSpecalist)favList.get(j);
			 log4log.error(loginId);
			 log4log.error(favSep.getFamilyPhysicianId().getDoctorId());
			 log4log.error(favSep.getStatus());
			 log4log.error(loginId.equals(favSep.getFamilyPhysicianId().getDoctorId()) &&(favSep.getStatus().equals("CONFIRMED")));
			
			  if(doctor.getDoctorId().equals(favSep.getSpecalistId().getDoctorId()) && loginId.equals(favSep.getFamilyPhysicianId().getDoctorId()) &&(favSep.getStatus().equals("CONFIRMED")))
			  {
				  Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
				  if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
				  {
			    tempList.add(doctor);
			    break;
				  }
			  }
			}
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}
	public String loadPercentage()
	{
		return uib.getPercentage();
	}
	
	public boolean createNewCaseTitle(FPCase fpCase)
	{
		try
		{
			log4log.error("New Title:  "+fpCase.getCaseId());
			List listFpCase = this.getFpportalFacade().loadCaseFPByCaseId(fpCase.getCaseId());
			FPCase fpCasetemp = (FPCase)listFpCase.get(0);
			fpCase.setDoctor(fpCasetemp.getDoctor());
			fpCase.setPatient(fpCasetemp.getPatient());
			fpCase.setCaseStatus(fpCasetemp.getCaseStatus());
			fpCase.setStarttstamp(fpCasetemp.getStarttstamp());
			fpCase.setEndtstamp(fpCasetemp.getEndtstamp());
			this.getFpportalFacade().updateCaseOpen(fpCase);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean createFavSep(String loginId,String docdetIds)
	{
		try
		{
		 FavSpecalist  favSep = null, favSepTemp = null;
		 
		 Doctor doc = (Doctor)this.getFpportalFacade().loadDoctorById(loginId).get(0);
		 String docIds[] = docdetIds.split("#####");
		 log4log.error("length : "+docIds.length);
		 for(int i=1;i<docIds.length;i++)
		 {
			 log4log.error("docIds : "+docIds[i]);
		 }
		 int val=1;
		 for(int i=1;i<docIds.length;i++)
		 {
			 Doctor sepdoc = (Doctor)this.getFpportalFacade().loadDoctorById(docIds[i]).get(0);
			 List favSepList =this.fpportalFacade.loadFavSep();
			 log4log.error("favSepList size:  "+favSepList.size());
			 if(favSepList.size() > 0)
			 {
			    int len = favSepList.size();
			    log4log.error("len : "+len);
			    log4log.error(len-1);
				val =((FavSpecalist)favSepList.get(len-1)).getFavSpecalistId()+1;
				log4log.error("val value : "+val);
			 }
			 else
			 {
				val=1; 
			 }
			 String newRecord = "yes";
			 List tempList = this.getFpportalFacade().loadFavSepByFPIdSepId(doc.getDoctorId(), sepdoc.getDoctorId());
			 if(tempList.size() > 0)
			 {
				 favSepTemp = (FavSpecalist)tempList.get(0);
				 if(favSepTemp.getStatus().equals("REJECTED"))
				 {
					 newRecord = "no";
				 }
			 }
			 if(newRecord.equals("yes"))
			 {
			 favSep = new FavSpecalist();
			 favSep.setFavSpecalistId(val);
			 favSep.setFamilyPhysicianId(doc);
			 favSep.setSpecalistId(sepdoc);
			 favSep.setStatus("PENDING");
			 favSep.setFlag("NOTREADSEP");
			 log4log.error("Check Status:  "+favSep.getStatus()+favSep.getSpecalistId()+favSep.getFavSpecalistId());
			 this.getFpportalFacade().storeFavSep(favSep);
			 }
			 else
			 {
				 favSepTemp.setStatus("REREQUESTED");
				 this.getFpportalFacade().updateFavSepdata(favSepTemp);
			 }
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("FavSep Exception: "+e);
			return false;
		}
	}
	
	public List showSpecalistFav(String loginId)
	{
		log4log.error("inside showSpecalistFav "+loginId);
		
		specalistList = null;
		specalistList = this.getFpportalFacade().loadSpecalityDoctor();
		log4log.error("specalistList Size : "+specalistList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavSepByFPId(loginId);
		Boolean flag = true;		
		for(int i=0;i < specalistList.size() ; i++)
		{
			flag = true;
			doctor= (Doctor)specalistList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				log4log.error(doctor.getDoctorId());
				log4log.error(doc.getSpecalistId().getDoctorId());
				if(doctor.getDoctorId().equals(doc.getSpecalistId().getDoctorId()) && ( doc.getStatus().equals("PENDING") || doc.getStatus().equals("CONFIRMED") || doc.getStatus().equals("REREQUESTED")))
				{
					flag = false;
					break;
					
				}
				
			}
			
			if(flag)
			{
				log4log.error(doctor.getDoctorId());
				Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
				if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
				{
				tempList.add(doctor);
				}
			}
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public List showFavSep(String loginId)
	{
		log4log.error("inside showFavSep "+loginId);
		
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavSepByFPId(loginId);
        log4log.error("showFavSep favList Size : "+favList.size());
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				log4log.error(doc.getSpecalistId().getDoctorId());
				Login login = this.getFpportalFacade().loadLogin(doc.getSpecalistId().getDoctorId());
				doc.getSpecalistId().setLogin(login);
				tempList.add(doc);
			}
			
			
				
			
		log4log.error("showFavSep Later Size : "+tempList.size());
		return tempList;
		
	}
	
	
	public boolean removeFavSep(String loginId,String docdetIds)
	{
		try
		{
		 FavSpecalist  favSep = null;
		
		 String docIds[] = docdetIds.split("#####");
		 log4log.error("length : "+docIds.length);
		 for(int i=1;i<docIds.length;i++)
		 {
			 log4log.error("docIds : "+docIds[i]);
		 }
		 
		 for(int i=1;i<docIds.length;i++)
		 {
			 List tempList = this.getFpportalFacade().loadFavSepByFPIdSepId(loginId,docIds[i]);
			 log4log.error("Remove Size : "+loginId+" "+docIds[i]+" "+tempList.size());
			 if(tempList.size()>0)
			 {
			 favSep = (FavSpecalist)tempList.get(0);
			 this.getFpportalFacade().deleteFavSpecalist(favSep);
			 }
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("FavSep Exception: "+e);
			return false;
		}
	}
	
	
	public List searchDocFav(String docName,String loginId)
	{
		log4log.error("inside searchDocFav "+loginId);
		
		
        List tempList =  null;
        tempList = new ArrayList();
        String str = FPTelemedicineUtils.duplicateSpaceRemover(docName);
        favList = this.getFpportalFacade().loadFavSepByFPIdName(loginId,str.trim());
        log4log.error("favList Size : "+favList.size());
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				log4log.error(doc.getSpecalistId().getDoctorId());
				tempList.add(doc);
			}
					
		log4log.error("searchDocFav Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public List searchAllDoc(String docName,String loginId)
	{
		log4log.error("inside searchAllDoc "+loginId);
		
		specalistList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(docName);
		specalistList = this.getFpportalFacade().loadSpecalityDoctor(str.trim());
		log4log.error("specalistList Size : "+specalistList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavSepByFPId(loginId);
		Boolean flag = true;		
		for(int i=0;i < specalistList.size() ; i++)
		{
			flag = true;
			doctor= (Doctor)specalistList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				log4log.error(doctor.getDoctorId());
				log4log.error(doc.getSpecalistId().getDoctorId());
				if(doctor.getDoctorId().equals(doc.getSpecalistId().getDoctorId()))
				{
					flag = false;
					break;
					
				}
				
			}
			
			if(flag)
			{
				log4log.error(doctor.getDoctorId());
				tempList.add(doctor);
			}
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}

	public List showPendingFP(String loginId)
	{
		log4log.error("inside showPendingFP "+loginId);
	
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPBySEPId(loginId);
        log4log.error(favList.size());
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				
				
				if(doc.getStatus().equals("PENDING") || doc.getStatus().equals("REREQUESTED"))
				{
				Login login = this.getFpportalFacade().loadLogin(doc.getFamilyPhysicianId().getDoctorId());
				
				if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
				  {
				  tempList.add(doc);
				  }
				}
			}
			
		log4log.error("showPendingFP Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public List showConfirmedFav(String loginId)
	{
		log4log.error("inside showConfirmedFav "+loginId);
		
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPBySEPId(loginId);
        log4log.error(favList.size());
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				
				log4log.error(doc.getFamilyPhysicianId().getDoctorId());
				if(doc.getStatus().equals("CONFIRMED"))
				{
				  Login login = this.getFpportalFacade().loadLogin(doc.getFamilyPhysicianId().getDoctorId());
				  doc.getFamilyPhysicianId().setLogin(login);
				  tempList.add(doc);
				  
				}
			}
			
		log4log.error("showConfirmedFav Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public boolean updateFavSep(String loginId,String docdetIds,String type)
	{
		try
		{
		 FavSpecalist  favSep = null;
		 Doctor doc = (Doctor)this.getFpportalFacade().loadDoctorById(loginId).get(0);
		 String docIds[] = docdetIds.split("#####");
		 log4log.error("length : "+docIds.length);
		 for(int i=1;i<docIds.length;i++)
		 {
			 log4log.error("docIds : "+docIds[i]);
		 }
		 int val=1;
		 for(int i=1;i<docIds.length;i++)
		 {
			 Doctor fpdoc = (Doctor)this.getFpportalFacade().loadDoctorById(docIds[i]).get(0);
			 List favSepList =this.fpportalFacade.loadFavSepByFPIdSepId(fpdoc.getDoctorId(), doc.getDoctorId());
			 log4log.error("favSepList size:  "+favSepList.size());
			 
			 favSep = (FavSpecalist)favSepList.get(0);
			 if(type.equals("confirmed"))
			 {
			 favSep.setStatus("CONFIRMED");
			 favSep.setFlag("READSEP");
			 }
			 else
			 {
			  favSep.setStatus("REJECTED");
			  favSep.setFlag("READSEP");
			 }
			
			 log4log.error("Check Status:  "+favSep.getStatus()+favSep.getSpecalistId()+favSep.getFavSpecalistId());
			 this.getFpportalFacade().updateFavSepdata(favSep);
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("FavSep Exception: "+e);
			return false;
		}
	}
	
	public boolean removeFPNetwork(String loginId,String docdetIds)
	{
		try
		{
		 FavSpecalist  favSep = null;
		
		 String docIds[] = docdetIds.split("#####");
		 log4log.error("length : "+docIds.length);
		 for(int i=1;i<docIds.length;i++)
		 {
			 log4log.error("docIds : "+docIds[i]);
		 }
		 
		 for(int i=1;i<docIds.length;i++)
		 {
			 List tempList = this.getFpportalFacade().loadFavSepByFPIdSepId(docIds[i],loginId);
			 log4log.error("Remove Size : "+loginId+" "+docIds[i]+" "+tempList.size());
			 if(tempList.size()>0)
			 {
			 favSep = (FavSpecalist)tempList.get(0);
			 this.getFpportalFacade().deleteFavSpecalist(favSep);
			 }
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("FavSep Exception: "+e);
			return false;
		}
	}
	
	public List searchDocPending(String docName,String loginId)
	{
		log4log.error("inside searchDocPending "+loginId);
		
		fpList = null;
		String str =FPTelemedicineUtils.duplicateSpaceRemover(docName);
		fpList = this.getFpportalFacade().loadFPDoctor(str.trim());
		log4log.error("fpList Size : "+fpList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPBySEPId(loginId);
        log4log.error("favList Size : "+favList.size());
			
		for(int i=0;i < fpList.size() ; i++)
		{
			
			doctor= (Doctor)fpList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				log4log.error(doctor.getDoctorId());
				log4log.error(doc.getFamilyPhysicianId().getDoctorId());
				if(doctor.getDoctorId().equals(doc.getFamilyPhysicianId().getDoctorId()) && (doc.getStatus().equals("PENDING") || doc.getStatus().equals("REREQUESTED")))
				{
					log4log.error("inside if : "+doctor.getDoctorId());
					tempList.add(doc);
					break;
					
				}
				
			}
	
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}

	public List searchDocConfirmed(String docName,String loginId)
	{
		log4log.error("inside searchDocConfirmed "+loginId);
		
		fpList = null;
		String str =FPTelemedicineUtils.duplicateSpaceRemover(docName);
		fpList = this.getFpportalFacade().loadFPDoctor(str.trim());
		log4log.error("fpList Size : "+fpList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPBySEPId(loginId);
        log4log.error("favList Size : "+favList.size());
			
		for(int i=0;i < fpList.size() ; i++)
		{
			
			doctor= (Doctor)fpList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavSpecalist doc = (FavSpecalist)favList.get(j);
				log4log.error(doctor.getDoctorId());
				log4log.error(doc.getFamilyPhysicianId().getDoctorId());
				if(doctor.getDoctorId().equals(doc.getFamilyPhysicianId().getDoctorId()) && doc.getStatus().equals("CONFIRMED"))
				{
					log4log.error("inside if : "+doctor.getDoctorId());
					tempList.add(doc);
					break;
					
				}
				
			}
	
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
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
	
	public List showFPLogin(String loginId)
	{
		log4log.error("inside showFP ");
		try
		{
		CaseReferral caseref = null;
		fpnList = null;
		fpnList = this.getFpportalFacade().loadFPReferralSep(loginId);
		
        List tempList =  new ArrayList();
				
		for(int i=0;i < fpnList.size() ; i++)
		{
			caseref= (CaseReferral)fpnList.get(i);
			doctor= caseref.getFamilyPhysicianId();
			Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
		    if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
			 {
			  tempList.add(doctor);
			 }
		  }
		log4log.error("Later Size : "+tempList.size());
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = tempList.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Doctor doc = (Doctor)element;
		   if (set.add(element) && set1.add(doc.getDoctorId()))
		      newList.add(element);
		    }
		 tempList.clear();
		 tempList.addAll(newList);
		 log4log.error("Later Size : "+tempList.size());
		return tempList;
		}
		catch (Exception e)
		{
			log4log.error("GGGGGGGG: "+e);
			return null;
		}
		
	}
	
	public List showLabLogin(String loginId)
	{
		log4log.error("inside showFP ");
		try
		{
		LabTest labTest = null;
		fpnList = null;
		fpnList = this.getFpportalFacade().loadLabTestByLabId(loginId);
		
        List tempList =  new ArrayList();
        				
		for(int i=0;i < fpnList.size() ; i++)
		{
			labTest= (LabTest)fpnList.get(i);
			doctor= labTest.getFpcase().getDoctor();
			Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
		    if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
			 {
			  tempList.add(doctor);
			 }
		  }
		log4log.error("Later Size : "+tempList.size());
		Set set = new HashSet();
		Set set1 = new HashSet();
		 List newList = new ArrayList();
		 for (Iterator iter = tempList.iterator();    iter.hasNext(); ) {
		 Object element = iter.next();
		 Doctor doc = (Doctor)element;
		   if (set.add(element) && set1.add(doc.getDoctorId()))
		      newList.add(element);
		    }
		 tempList.clear();
		 tempList.addAll(newList);
		 log4log.error("Later Size : "+tempList.size());
		return tempList;
		}
		catch (Exception e)
		{
			log4log.error("GGGGGGGG: "+e);
			return null;
		}
		
	}
	
}
