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

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.FamilyPhyManagementValidator;

public class SpecialistManagement {
	
public static final Logger log4log = Logger.getLogger(SpecialistManagement.class);
	
	private IFPTelemedicineFacade fpportalFacade;
	private String patientIdd;
	private List patientList = null;
	private ILicenseCodec licenseCodec;
	private UploadInfoBean uib;
	private UploadInfoBean uploadInfoBeanInst; 
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
	
	
	public List loadPatient(String specialistId)
	{
		log4log.error("loadPatient: "+specialistId);
		 List referralList = this.getFpportalFacade().loadCaseReferralBySpecialistId(specialistId);
		 log4log.error("ReferralList size: "+referralList.size());
		 List tempListPat = new ArrayList();
		 
		 for(int j=0;j<referralList.size();j++)
	       {
			CaseReferral caseReferral = (CaseReferral)referralList.get(j);
			String referralId = caseReferral.getReferralId();
			if(caseReferral.getFpcase().getCaseStatus().equals("OPEN"))
			{
			Patient patient = caseReferral.getFpcase().getPatient();
			patient=  FPTelemedicineUtils.getAge(patient);
			
			caseReferral.getFpcase().setPatient(patient);
			log4log.error("patient id: "+patient.getPatientId());
			log4log.error("Age: "+caseReferral.getFpcase().getPatient().getAgeYears());
			log4log.error("patient name: "+patient.getFirstName());
			log4log.error("patient gender:"+patient.getGender());
			log4log.error("patient contactdetails:" +patient.getContactDetails().getAddress() +patient.getContactDetails().getContactNumber() +patient.getContactDetails().getEmail());
			patient.setCaseReferral(caseReferral);	
			if(!tempListPat.contains(patient.getPatientId()))
	            {
				
				tempListPat.add(patient);
	            }
			}
	     }
		
    log4log.error("tempListPat.size "+tempListPat.size());
	return tempListPat;	
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
					if(doc.getFlag().equals("NOTREADSEP")&& login.getAccountStatus().equalsIgnoreCase("ENABLED"))
					{
						newReq = newReq+1;
					}
				}
				
			}
			
		
		return newReq;
		
	}
	
	public void readPatientReferral(CaseReferral caseReff)
	{
		caseReff.setFlag("READSEP");
		this.fpportalFacade.editCaseReferral(caseReff);
	}
	public List searchPatientdwr(String patientName, String loginId)
	{
		log4log.error("inside search patient"+patientName+"  "+loginId);
		patientList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(patientName);
		patientList = this.getFpportalFacade().searchPatientSh(str.trim(),loginId);
		log4log.error("patientList Size : "+patientList.size());
        List patientSearch =  new ArrayList();
				
        List referralList = this.getFpportalFacade().loadCaseReferralBySpecialistId(loginId);
        log4log.error("referralList Size : "+referralList.size());
        List tempListPat = new ArrayList();
        for(int j=0;j<referralList.size();j++)
	       {
        	 CaseReferral caseReferral = (CaseReferral)referralList.get(j);
			 String referralId = caseReferral.getReferralId();
			 log4log.error("referral Id :"+referralId);
			 if(caseReferral.getFpcase().getCaseStatus().equals("OPEN"))
				{
				 log4log.error("inside if");
			 Patient patientemp = caseReferral.getFpcase().getPatient();
			 
			 patientemp=  FPTelemedicineUtils.getAge(patientemp);
			 patientemp.setCaseReferral(caseReferral);	
			 for(int i=0;i < patientList.size() ; i++)
				{
				 patient= (Patient)patientList.get(i);
				 if(patientemp.getPatientId().equals(patient.getPatientId()))
				 {
					 log4log.error("inside second if");
					 patientSearch.add(patientemp);
				 }
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
		patientList = this.getFpportalFacade().searchPatientShNo(str.trim(),phoneNo,loginId);
		log4log.error("patientList Size : "+patientList.size());
        List patientSearch =  new ArrayList();
				
        List referralList = this.getFpportalFacade().loadCaseReferralBySpecialistId(loginId);
        log4log.error("referralList Size : "+referralList.size());
        List tempListPat = new ArrayList();
        for(int j=0;j<referralList.size();j++)
	       {
        	 CaseReferral caseReferral = (CaseReferral)referralList.get(j);
			 String referralId = caseReferral.getReferralId();
			 log4log.error("referral Id :"+referralId);
			 if(caseReferral.getFpcase().getCaseStatus().equals("OPEN"))
				{
				 log4log.error("inside if");
			 Patient patientemp = caseReferral.getFpcase().getPatient();
			 
			 patientemp=  FPTelemedicineUtils.getAge(patientemp);
			 patientemp.setCaseReferral(caseReferral);	
			 for(int i=0;i < patientList.size() ; i++)
				{
				 patient= (Patient)patientList.get(i);
				 if(patientemp.getPatientId().equals(patient.getPatientId()))
				 {
					 log4log.error("inside second if");
					 patientSearch.add(patientemp);
				 }
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

}
