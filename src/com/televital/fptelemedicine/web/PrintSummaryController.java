package com.televital.fptelemedicine.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.televital.fptelemedicine.domain.CaseReferral;
import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.History;
import com.televital.fptelemedicine.domain.LabServices;
import com.televital.fptelemedicine.domain.LabTest;
import com.televital.fptelemedicine.domain.LastRecord;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.domain.form.EmrBackingObject;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class PrintSummaryController extends  AbstractFPTelemedicineController{
	
	private static final Logger log4log = Logger.getLogger(PrintSummaryController.class);
	private  FPCase fpcaseemr;
	private FPCase fpnewcase ; 
	private LabTest labtest;
	private  History history;
	private Boolean flag=false;
	private Patient patient;
	private String caseId;

	public PrintSummaryController()
	{
	 setSessionForm(false); 
	}
protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		log4log.error("Patient Name: "+request.getParameter("patientName"));
		String docType = request.getParameter("docType");
		log4log.error("request.getParameter docType: "+docType);
		
		String patientId=request.getParameter("patientId");
		log4log.error("Patient Id: "+patientId);
		List patList = this.getFpportalFacade().loadPatientById(patientId);
		log4log.error("Patient list size: "+patList.size());
		log4log.error("request.getParameter: "+request.getParameter("caseId"));
		List fpCaseList = this.getFpportalFacade().loadCaseFPByPatientId(patientId);
		List historyList = this.getFpportalFacade().loadHistoryByPatientId(patientId);
	    
	   // String sectionTemp = request.getParameter("section");
		//String caseTitleTemp = request.getParameter("caseTitle");
		//String emrTemp = request.getParameter("emr");
		//String caseTitleValue = request.getParameter("caseTitleValue");
	
		if(patList.size() > 0)
		{
	    patient  = (Patient)patList.get(0);
		patient=  FPTelemedicineUtils.getAge(patient);
		}
		EmrBackingObject emrbo = new EmrBackingObject();
		
		if(docType != null)
	    {
			emrbo.setDocType(docType);
	    }
		
		Boolean flagHistory = false;
			if(historyList.size() > 0)
			{
			history = (History)historyList.get(0);
			log4log.error(history+"  aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+history.getpastIllness()+ history.getFamilyHistory()+ history.getCurrentMedication()+ history.getImmunization()+ history.getAllergies() +  history.getAttachments());
			
			if(history.getpastIllness().equals("") && history.getFamilyHistory().equals("") && history.getCurrentMedication().equals("") && history.getImmunization().equals("") && history.getAllergies().equals(""))
			{
				if(history.getAttachments() == null)
				{
				  flagHistory = true;
				}
			}
			log4log.error(flagHistory+"  aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			if(flagHistory)
			{
				emrbo.setHistory(null);
			}
			else
			{
				emrbo.setHistory(history);
			
			}
			if(history.getAttachments()!=null)
			{
			String hisatt[]=history.getAttachments().split("#####");
			log4log.error("No of files + one: "+hisatt.length);
			List files = new ArrayList();
			int len =(hisatt.length);
			for(int k=0;k<len;k++)
			{
				files.add(hisatt[k]);
				log4log.error(hisatt[k]);
			}
			
			emrbo.setHistoryAttachment(files);
			}
			}
			log4log.error(emrbo.getHistoryAttachment());
			String caseId = request.getParameter("caseId");
			List caseList =this.getFpportalFacade().loadCaseFPByCaseId(caseId);
			if(caseList.size() > 0)
			{
				FPCase fpca = (FPCase)caseList.get(0);
				
				emrbo.setFpcase(fpca);
				if(fpca.getDiagnosis().equals("") && fpca.getPrescription().equals("") && fpca.getDiagnosis().equals(""))
				{
					emrbo.setDiagonisisDisplay("no");
				}
				else
				{
					emrbo.setDiagonisisDisplay("yes");
				}
				
				if(fpca.getWeight().equals("") && fpca.getHeight().equals("") && fpca.getTemperature().equals("") && fpca.getBloodPressure().equals("") && fpca.getPulseRate().equals("") && fpca.getRespirationRate().equals(""))
				{
					if(fpca.getLmp() == null)
					{
						emrbo.setVitalSignsDisplay("no");
					}
					else
					{
						emrbo.setVitalSignsDisplay("yes");
					}
				}
				else
				{
					emrbo.setVitalSignsDisplay("yes");
				}
				
				if(fpca.getCaseStatus().equalsIgnoreCase("CLOSED"))
				{
					emrbo.setCaseClosed("yes");
				}
				else
				{
					emrbo.setCaseClosed("no");
				}
				
				if(fpca.getCaseattachments() != null)
				{
				String caseatt[]=fpca.getCaseattachments().split("#####");
				log4log.error("No of files + one: "+caseatt.length);
				List files = new ArrayList();
				int len =(caseatt.length);
				for(int k=0;k<len;k++)
				{
					files.add(caseatt[k]);
					log4log.error(caseatt[k]);
				}
				emrbo.setCaseAttachment(files);
				}
			}
			fpCaseList = this.getFpportalFacade().loadCaseFPByPatientId(patientId);
			HttpSession sess = request.getSession();
			
			
			log4log.error("caseId : "+caseId);		
			List labtestList = this.getFpportalFacade().loadLabTestByCaseId(caseId);
			log4log.error("labtestList.size(): "+labtestList.size());
			List tempLabService1 = new ArrayList();
			List tempService = new ArrayList();
			for(int k=0;k<labtestList.size();k++)
			{
				LabTest labtest = (LabTest)labtestList.get(k);
				log4log.error("labtest.getLabServices().getLabServiceId(): "+labtest.getLabServices().getLabServiceId());
				log4log.error("labtest.getTestReport(): "+labtest.getTestReport());
				tempService= this.getFpportalFacade().loadLabServiceByLabServiceId(labtest.getLabServices().getLabServiceId());
				log4log.error("	tempService.get(0) :"+tempService.get(0));
				
				LabServices labService = (LabServices)tempService.get(0);
				labService.setLabTest(labtest);
				log4log.error("labService: "+labService);
				Boolean a =tempLabService1.add(labService);
				log4log.error("aaaaaaa: "+a);
				//Lab lab = this.getFpportalFacade().loadLabById(labService.getLab().getLabId());

			}
			
			emrbo.setLabServiceList(tempLabService1);
			List docSepList = new ArrayList();
			if(docType.equals("fp"))
			{
				docSepList = this.getFpportalFacade().loadCaseReferralCaseId(caseId);
			}
			else if(docType.equals("specalist"))
			{
				String docId = request.getParameter("docId");
				docSepList = this.getFpportalFacade().loadCaseReferralCaseId(caseId,docId);
			}
			
			
			log4log.error("docSepList.size(): "+docSepList.size());
			List docList = new ArrayList();
			//List tempService = new ArrayList();
			for(int k=0;k<docSepList.size();k++)
			{
				CaseReferral caseRef = (CaseReferral)docSepList.get(k);
				log4log.error("caseRef: "+caseRef.getOpinions());
				docList.add(caseRef);
			}
			
			emrbo.setSpecialityList(docList);
			
			emrbo.setPatient(patient);
			emrbo.setFpCaseList(fpCaseList);
			sess.setAttribute("PatientId",patient.getPatientId());
			sess.setAttribute("CaseId",caseId);
			return emrbo;
		
		
	}
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException error) throws Exception 
	 {	
		log4log.error("Patient Name: "+request.getParameter("patientName"));
		return new ModelAndView("aaaa","command","");
	 }
}
