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
import com.televital.fptelemedicine.domain.LoginForm;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.domain.form.EmrBackingObject;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class ViewEMRController extends  AbstractFPTelemedicineController{
	
	private static final Logger log4log = Logger.getLogger(ViewEMRController.class);
	private  FPCase fpcaseemr;
	private FPCase fpnewcase ; 
	private LabTest labtest;
	private  History history;
	private Boolean flag=false;
	private Patient patient;
	private String caseId;

	public ViewEMRController()
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
	    
	    String sectionTemp = request.getParameter("section");
		String caseTitleTemp = request.getParameter("caseTitle");
		String emrTemp = request.getParameter("emr");
		String caseTitleValue = request.getParameter("caseTitleValue");
	
		log4log.error("emrTemp : "+request.getParameter("emr"));
		log4log.error("caseTitleTemp : "+caseTitleTemp);
		if(patList.size() > 0)
		{
	    patient  = (Patient)patList.get(0);
		patient=  FPTelemedicineUtils.getAge(patient);
		}
		EmrBackingObject emrbo = new EmrBackingObject();
		
		if(caseTitleTemp != null)
		{
			emrbo.setCaseTitleBO(caseTitleTemp);
		}
		else
		{
			emrbo.setCaseTitleBO("no");
		}
		
		if(emrTemp != null)
		{
			emrbo.setEmrbo(emrTemp);
		}
		else
		{
			emrbo.setEmrbo("noViewEMR");
			emrTemp ="";
		}
		
		
		if(sectionTemp != null)
		{
			emrbo.setSection(sectionTemp);
		}
		
		if(docType != null)
	    {
			emrbo.setDocType(docType);
	    }
		
		if(request.getParameter("caseId") != null)
		{
			if(historyList.size() > 0)
			{
			history = (History)historyList.get(0);
			emrbo.setHistory(history);
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
		else
		{
		log4log.error("when caseId is null"+patientId+" request.getParameter :" +request.getParameter("patientId"));
		String loginId = request.getParameter("docId");
        patList = this.getFpportalFacade().loadPatientById(request.getParameter("patientId"));
        log4log.error("patList"+patList.size());
        HttpSession sess = request.getSession();
		 
		if(patList.size() > 0)
		{
	    patient  = (Patient)patList.get(0);
		patient=  FPTelemedicineUtils.getAge(patient);
		}
	
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		log4log.error("request.getParameter(patientId) : "+request.getParameter("patientId"));
		fpCaseList = this.getFpportalFacade().loadCaseFPByPatientId(request.getParameter("patientId"));
		
		
		log4log.error("fpCaseList.size(): "+fpCaseList.size());
		for(int i=0;i<fpCaseList.size();i++)
		{
			fpcaseemr = (FPCase)fpCaseList.get(i);
			log4log.error("fpcase.getCaseStatus : "+fpcaseemr.getCaseStatus());
			if(fpcaseemr.getCaseStatus().equalsIgnoreCase("OPEN") || emrTemp.equals("viewEMR"))
			{
				log4log.error("if the case is open");
				emrbo.setFpcase(fpcaseemr);
				sess.setAttribute("CaseId",fpcaseemr.getCaseId());
				log4log.error("emrbo.getFPCase()  "+emrbo.getFpcase());
				
				if(fpcaseemr.getCaseattachments() != null)
				{
				String caseatt[]=fpcaseemr.getCaseattachments().split("#####");
				log4log.error("No of files + case: "+caseatt.length);
				List files = new ArrayList();
				int len =(caseatt.length);
				for(int k=0;k<len;k++)
				{
					
					files.add(caseatt[k]);
					log4log.error(caseatt[k]);
				
					
				}
				emrbo.setCaseAttachment(files);
				}
				
				log4log.error("caseId : "+fpcaseemr.getCaseId());		
				List labtestList = this.getFpportalFacade().loadLabTestByCaseId(fpcaseemr.getCaseId());
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
					
					log4log.error("labService: "+labService);
					labService.setLabTest(labtest);
				
					Boolean a =tempLabService1.add(labService);
					log4log.error("aaaaaaa: "+a);
					//Lab lab = this.getFpportalFacade().loadLabById(labService.getLab().getLabId());
					
					
				}
				
				emrbo.setLabServiceList(tempLabService1);
				emrbo.setCaseClosed("no");
				
				List docSepList = new ArrayList();
				if(docType.equals("fp"))
				{
					docSepList = this.getFpportalFacade().loadCaseReferralCaseId(fpcaseemr.getCaseId());
				}
				else if(docType.equals("specalist"))
				{
					String docId = request.getParameter("docId");
					docSepList = this.getFpportalFacade().loadCaseReferralCaseId(fpcaseemr.getCaseId(),docId);
				}
				
				//List docSepList = this.getFpportalFacade().loadCaseReferralCaseId(fpcaseemr.getCaseId());
				log4log.error("docSepList.size(): "+docSepList.size());
				List docList = new ArrayList();
				//List tempService = new ArrayList();
				for(int k=0;k<docSepList.size();k++)
				{
					CaseReferral caseRef = (CaseReferral)docSepList.get(k);
					docList.add(caseRef);
				}
				emrbo.setSpecialityList(docList);
				
			}
			else
			{
				
				
				emrbo.setCaseClosed("yes");
				
				List openCaseList =this.getFpportalFacade().loadOnlyOpenCase(patient.getPatientId());
				log4log.error("if the case is not open in else part");
				if( !(openCaseList.size() > 0))
				{
					log4log.error("for creating new case");	
				log4log.error("loginId: "+loginId);
				emrbo.setNewcase("yes");
				 fpnewcase = new FPCase();
				List docList = this.getFpportalFacade().loadDoctorById(loginId);
				if(docList.size() > 0)
				{
					Doctor doc = (Doctor)docList.get(0);
					patient.setCreator(doc);
					 fpnewcase.setDoctor(doc);
				}
				List caseList= this.getFpportalFacade().loadCaseFP();
				
				List lastExtIdC = this.getFpportalFacade().findLastExternalId("CS");
				String newId2 = FPTelemedicineUtils.generateNewExternalId("CS",lastExtIdC);
				caseId = newId2;
				
			/*	if(!(caseList.size()>0))
				{
					caseId="1";
				}
				else
				{
				for(int k=0; k<caseList.size();k++)
				{
					FPCase casef=(FPCase)caseList.get(k);
					caseId=casef.getCaseId();
					log4log.error("caseId  : "+caseId);
				}
				int tempval = Integer.parseInt(caseId);
				caseId=Integer.toString(tempval+1);
				
				}*/
				
				
				log4log.error("after: "+caseId);
				
				 fpnewcase.setCaseId(caseId);
				 emrbo.setCaseClosed("no");
				 fpnewcase.setPatient(patient);
				 fpnewcase.setCaseStatus("OPEN");
				 fpnewcase.setStarttstamp(tstamp);
				 fpnewcase.setEndtstamp(tstamp);
				 
				 this.getFpportalFacade().storeCase(fpnewcase);
				 
				 if(lastExtIdC.size()>0)
					{
						LastRecord lr = (LastRecord)lastExtIdC.get(0);
						lr.setLastId(fpnewcase.getCaseId());
						this.getFpportalFacade().updateLastRecord(lr);
					}
					else
					{
						LastRecord lr = new LastRecord();
						lr.setCode("CS");
						lr.setLastId(fpnewcase.getCaseId());
						this.getFpportalFacade().storeLastRecord(lr);
					}
				 
				 emrbo.setFpcase(fpnewcase);
				 
				 sess.setAttribute("CaseId",fpnewcase.getCaseId());
				
				}
				else
				{
					log4log.error("if the open case exists");
					emrbo.setCaseClosed("no");
						FPCase fpca = (FPCase)openCaseList.get(0);
						//emrbo.setFpcase(fpca);
						if(fpca.getCaseattachments() != null)
						{
							String caseatt[]=fpca.getCaseattachments().split("#####");
							log4log.error("No of files + case: "+caseatt.length);
							List files = new ArrayList();
							int len =(caseatt.length);
							for(int k=0;k<len;k++)
							{
								files.add(caseatt[k]);
								log4log.error(caseatt[k]);
							}
							emrbo.setCaseAttachment(files);
						}
						
						log4log.error("caseId : "+fpca.getCaseId());		
						List labtestList = this.getFpportalFacade().loadLabTestByCaseId(fpca.getCaseId());
						log4log.error("labtestList.size(): "+labtestList.size());
						List tempLabService1 = new ArrayList();
						List tempService = new ArrayList();
						for(int k=0;k<labtestList.size();k++)
						{
							LabTest labtest = (LabTest)labtestList.get(k);
							log4log.error("labtest.getLabServices().getLabServiceId(): "+labtest.getLabServices().getLabServiceId());
							tempService= this.getFpportalFacade().loadLabServiceByLabServiceId(labtest.getLabServices().getLabServiceId());
							log4log.error("	tempService.get(0) :"+tempService.get(0));
							
							LabServices labService = (LabServices)tempService.get(0);
							labService.setLabTest(labtest);
							log4log.error("labService: "+labService);
							Boolean a =tempLabService1.add(labService);
							log4log.error("aaaaaaa: "+a);
							//Lab lab = this.getFpportalFacade().loadLabById(labService.getLab().getLabId());
							
							
						}
						//emrbo.setCaseClosed("yes");
						emrbo.setLabServiceList(tempLabService1);
						
					log4log.error("dont know what has to be done");
				}
			}
			
		}
		//log4log.error("emrbo.getFPCase()  "+emrbo.getFpcase());
		//log4log.error("emrbo.getFPCase()  "+emrbo.getFpcase().getCaseId());
		log4log.error("historyList.size(): "+historyList.size());
		if(historyList.size()==0)
		{
			emrbo.setHistorytb("display");
			log4log.error("display");
		}
		else
		{
			emrbo.setHistorytb("notdisplay");
			history = (History)historyList.get(0);
			emrbo.setHistory(history);
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
			log4log.error("notdisplay");
		}
		
		fpCaseList = this.getFpportalFacade().loadCaseFPByPatientId(patientId);
		//log4log.error("emrbo.getLabServiceList() :"+emrbo.getLabServiceList()+patient);
		log4log.error("patient : "+patient);
		emrbo.setPatient(patient);
		emrbo.setFpCaseList(fpCaseList);
		if( patient!=null )
		{
		sess.setAttribute("PatientId",patient.getPatientId());
		}
		else
		{
			sess.setAttribute("PatientId",request.getParameter("patientId"));
		}
		
		//log4log.error("emrbo.getFPCase()  "+emrbo.getFpcase());
		return emrbo;
		}
	}
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException error) throws Exception 
	 {	
		log4log.error("Patient Name: "+request.getParameter("patientName"));
		return new ModelAndView("aaaa","command","");
	 }
}
