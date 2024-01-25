package com.televital.fptelemedicine.domain.hibernate;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.log4j.Logger;

import com.televital.fptelemedicine.domain.CaseReferral;
import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.FavLab;
import com.televital.fptelemedicine.domain.FavSpecalist;
import com.televital.fptelemedicine.domain.History;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.LabServices;
import com.televital.fptelemedicine.domain.LabTest;
import com.televital.fptelemedicine.domain.LastRecord;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class HibernateFPTelemedicineFacade extends HibernateDaoSupport implements IFPTelemedicineFacade {
	private static final Logger log = Logger.getLogger(HibernateFPTelemedicineFacade.class);
	
	public void updateLogin(Login login) throws DataAccessException,ObjectRetrievalFailureException
	{
		getHibernateTemplate().update(login);
	}
	public List loadLoginForm(String loginId) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("userId: "+loginId);
		return getHibernateTemplate().find("from Login login where login.loginId=?",loginId);
	}
	public Login loadLogin(String id) throws DataAccessException,ObjectRetrievalFailureException  {
		
		return (Login)getHibernateTemplate().load(Login.class,id);
	}
	
	public List loadDoctors()throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load Doctors");
		return this.getHibernateTemplate().find("from Doctor doctor order by doctor.firstName");
	}
	
	public void storeDoctor(Doctor doctor,Login login) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(login);
		this.getHibernateTemplate().save(doctor);
	}
	
	public void deleteDoctor(Doctor doctor, Login login)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside delete Doctor");
		this.getHibernateTemplate().delete(login);
		this.getHibernateTemplate().delete(doctor);
	}
	
	public void editDoctor(Doctor doctor)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside edit Doctors");
		this.getHibernateTemplate().update(doctor);
	}
	
	public List searchDoctorh(String docName)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside search Doctors"+docName);
		String tempFirstName = "";
		String tempName[] = docName.split("\\.");
		String  tempNameV = docName;
		String lastNameTemp = docName;
		 if(tempName.length > 0)
		  {
			if(!"Dr".equals(tempName[0]))
			{
				log.error("hello");
			    tempNameV = "Dr. "+docName;
			}
		   }
		 docName = tempNameV;
		 log.error("inside search Doctors"+docName);
		return this.getHibernateTemplate().find("from Doctor doctor where  CONCAT(doctor.firstName,' ',doctor.lastName) like '"+docName+"%' or doctor.firstName like '"+docName+"%' or doctor.firstName like '"+lastNameTemp+"%' or doctor.lastName like '"+docName+"%' or doctor.lastName like '"+lastNameTemp+"%'");
	}
	
	public List loadDoctorById(String doctorIdt)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load individual Doctors");
		return this.getHibernateTemplate().find("from Doctor doctor where doctor.doctorId='"+doctorIdt+"'");
	}
	public List loadSpecalityDoctor()throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside Specality Doctors");
		return this.getHibernateTemplate().find("from Doctor doctor where doctor.type='SPECALIST' or doctor.type='BOTH'");
	}
	
	public List loadLabs()throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load Labs");
		return this.getHibernateTemplate().find("from Lab lab order by lab.labName");
	}
	
	public void storeLab(Lab lab, Login login) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(login);
		this.getHibernateTemplate().save(lab);
	}
	
	public void deleteLab(Lab lab, Login login)throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().delete(login);
		this.getHibernateTemplate().delete(lab);
	}
	
	public void editLab(Lab lab)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside edit Lab");
		this.getHibernateTemplate().update(lab);
	}
	
	public List searchLabh(String labName)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside search Doctors"+labName);
		return this.getHibernateTemplate().find("from Lab lab where lab.labName like '"+labName+"%'");
	}
	
	public List loadLabById(String labIdt)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load individual labs");
		return this.getHibernateTemplate().find("from Lab lab where lab.labId='"+labIdt+"'");
	}
	
	public List loadPatients(String id)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load Doctors");
		return this.getHibernateTemplate().find("from Patient patient where patient.creator.doctorId='"+id+"'");
	}
	
	public boolean storePatient(Patient patient,FPCase fpcase, History history) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside store patient hibernate"+fpcase.getCaseId() + history.getHistoryId());
		boolean val= this.storePatient(patient);
		if(val)
		{
			boolean valCase =this.storeCaseIn(fpcase);
			if(valCase)
			{
				boolean valHist=this.storeHistory(history);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
				
	}
	public boolean storeHistory(History history) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(history);
		return true;
	}
	public boolean storeCaseIn(FPCase fpcase) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(fpcase);
		return true;
	}
	public boolean storePatient(Patient patient) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(patient);
		return true;
	}
	public List loadPatientById(String patientId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load individual Patients"+patientId);
		return this.getHibernateTemplate().find("from Patient patient where patient.patientId='"+patientId+"'");
	}
	
	public void deletePatientdb(Patient patient)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside delete Patients");
		
		this.getHibernateTemplate().delete(patient);
	}
	
	public void editPatient(Patient patient)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside edit Patients");
		this.getHibernateTemplate().update(patient);
	}
	
	public List searchPatienth(String patientName,String loginId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside search Patient "+patientName+" loginId:  "+loginId);
		return this.getHibernateTemplate().find("from Patient patient where ((CONCAT(patient.firstName,' ',patient.lastName) like '"+patientName+"%' or patient.firstName like '"+patientName+"%' or patient.lastName like '"+patientName+"%')) and patient.creator.doctorId='"+loginId+"'");
	}
	
	public List searchPatienthNo(String patientName,String contactNo,String loginId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside search Patient "+patientName+" loginId:  "+loginId+" contactNo : "+contactNo);
		return this.getHibernateTemplate().find("from Patient patient where ((CONCAT(patient.firstName,' ',patient.lastName) like '"+patientName+"%' or patient.firstName like '"+patientName+"%' or patient.lastName like '"+patientName+"%')) and patient.creator.doctorId='"+loginId+"' and patient.contactDetails.contactNumber like '"+contactNo+"%'");
	}
	
	public List searchPatientSh(String patientName,String loginId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside search Patient "+patientName+" loginId:  "+loginId);
		return this.getHibernateTemplate().find("from Patient patient where ((CONCAT(patient.firstName,' ',patient.lastName) like '"+patientName+"%' or patient.firstName like '"+patientName+"%' or patient.lastName like '"+patientName+"%'))");
	}
	
	public List searchPatientShNo(String patientName,String contactNo,String loginId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside search Patient "+patientName+" loginId:  "+loginId+" contactNo : "+contactNo);
		return this.getHibernateTemplate().find("from Patient patient where ((CONCAT(patient.firstName,' ',patient.lastName) like '"+patientName+"%' or patient.firstName like '"+patientName+"%' or patient.lastName like '"+patientName+"%')) and patient.contactDetails.contactNumber like '"+contactNo+"%'");
	}
	
	public List loadCaseFP()throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load cases");
		return this.getHibernateTemplate().find("from FPCase fpcase order by fpcase.caseId");
	}
	
	public List loadCaseFPByPatientId(String patientId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load case by PatientID");
		return this.getHibernateTemplate().find("from FPCase fpcase where fpcase.patient.patientId='"+patientId+"' order by fpcase.starttstamp");
	}
	
	public boolean deleteHistory(String patientId)throws DataAccessException,ObjectRetrievalFailureException
	{
     List histList = this.loadHistoryByPatientId(patientId);
     if(histList.size()>0)
     {
    	 History history = (History)histList.get(0);
    	 this.getHibernateTemplate().delete(history);
     }
     return true;
	}
	public void deleteFPCase(FPCase fpcase,String patientId)throws DataAccessException,ObjectRetrievalFailureException
	{
		this.deleteHistory(patientId);
		this.getHibernateTemplate().delete(fpcase);
	}
	
	public List loadHistoryByPatientId(String patientId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load history by PatientID");
		return this.getHibernateTemplate().find("from History history where history.patientId='"+patientId+"'");
	}
	
	public void storeCaseHistory(FPCase fpcase, History history) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("fpcase:"+fpcase.getCaseId());
		log.error("history:"+history.getHistoryId());
		log.error("getDoctor: "+fpcase.getDoctor());
		log.error("getDoctorId: "+fpcase.getDoctor().getDoctorId());
		this.getHibernateTemplate().save(history);
		this.getHibernateTemplate().update(fpcase);
	}
	
	public List loadHistory()throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load history");
		return this.getHibernateTemplate().find("from History history order by history.historyId");
	}
	
	public List loadCaseFPByCaseId(String caseId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside load case by CaseID");
		return this.getHibernateTemplate().find("from FPCase fpcase where fpcase.caseId='"+caseId+"'");
	}
	
	public void updateCaseOpen(FPCase fpcase,History history) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside update history fpcase");
		getHibernateTemplate().update(history);
		getHibernateTemplate().update(fpcase);
	}
	
	public void updateCaseOpen(FPCase fpcase) throws DataAccessException,ObjectRetrievalFailureException
	{
		
		getHibernateTemplate().update(fpcase);
	}
	
	public void storeCase(FPCase casefp) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside save new case");
		this.getHibernateTemplate().save(casefp);
	}
	
	public List loadOnlyOpenCase(String patientId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside loadOnlyClosedCase");
		return this.getHibernateTemplate().find("from FPCase fpcase where fpcase.patient.patientId='"+patientId+"' and fpcase.caseStatus='OPEN'");
		
	}
	public void updateHistory(History history) throws DataAccessException,ObjectRetrievalFailureException
	{
		getHibernateTemplate().update(history);
	}
	
	public List loadLabServices()throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabServices labService order by labService.labServiceId");
	}
	
	public List loadLabServices(String labId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("labId: "+labId);
		return this.getHibernateTemplate().find("from LabServices labService where labService.lab.labId ='"+labId+"' order by labService.labServiceId");
	}
	
	public void storeLabService(LabServices labService) throws DataAccessException,ObjectRetrievalFailureException
	{
	   log.error(labService.getLab().getLabId()+labService.getLabServiceId()+labService.getServiceName());
		this.getHibernateTemplate().save(labService);
	}
	public void deleteLabService(LabServices labSevice)throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().delete(labSevice);
	}
	public List searchLabServiceh(String labServiceName)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside search Doctors"+labServiceName);
		return this.getHibernateTemplate().find("from LabServices labService where labService.serviceName like '"+labServiceName+"%'");
	}
	
	public List loadLabServiceByServiceName(String serviceName)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabServices labService where labService.serviceName='"+serviceName+"'");
	}
	public void editLabService(LabServices labService)throws DataAccessException,ObjectRetrievalFailureException
	{
		getHibernateTemplate().update(labService);
	}
	public List loadLabServiceByLabId(String labId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside  loadLabServiceByLabId "+labId);
		return this.getHibernateTemplate().find("from LabServices labService where labService.lab.labId='"+labId+"'");
	}
	
	public List loadLabServiceByLabServiceId(String labServiceId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside  loadLabServiceByLabId "+labServiceId);
		return this.getHibernateTemplate().find("from LabServices labService where labService.labServiceId='"+labServiceId+"'");
	}
	public List loadLabTest()throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabTest labtest order by labtest.labTestId");
	}
	
	public void storeLabTest(LabTest labtest) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("before storing labtest");
		this.getHibernateTemplate().save(labtest);
		log.error("after storing labtest");
	}
	
	public List loadLabTestByCaseId(String caseId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabTest labtest where labtest.fpcase.caseId='"+caseId+"'");
	}
	
	public void deleteLabtest(LabTest labtest) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().delete(labtest);
	}
	
	public List loadLabTestByServiceId(String labServiceId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.labServiceId='"+labServiceId+"'");
	}
	
	public List loadLabTestByLabTestId(String labTestId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabTest labtest where labtest.labTestId='"+labTestId+"'");
	}
	
	public List loadLabTestByLabId(String labId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"'");
	}
	
	public List loadLabTestByCaseIdServiceId(String caseId,String serviceId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.labServiceId='"+serviceId+"' and labtest.fpcase.caseId='"+caseId+"'");
	}
	
	public void editLabTest(LabTest labTest)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("aaaaaaaaaaaA: "+labTest);
		this.getHibernateTemplate().update(labTest);
	}
	
	public List loadCaseReferral()throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside loadCaseReferral");
		return this.getHibernateTemplate().find("from CaseReferral caseRef order by caseRef.referralId");
	}
	
	public void storeCaseReferral(CaseReferral caseRef) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside store CaseReferral");
		this.getHibernateTemplate().save(caseRef);
	}
	
	public List loadCaseReferralCaseId(String caseId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.fpcase.caseId='"+caseId+"'");
	}
	
	public List loadCaseReferralCaseId(String caseId,String docId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.fpcase.caseId='"+caseId+"' and caseRef.specalistId='"+docId+"'");
	}
	
	public void deleteCaseReferral(CaseReferral caseRef) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().delete(caseRef);
	}
	
	public List loadCaseReferralByReferralId(String caseReferralId) throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.referralId='"+caseReferralId+"'");
	}
	
	public void editCaseReferral(CaseReferral caseReferral)throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().update(caseReferral);
	}
	
	public List findLastExternalId(String code) throws DataAccessException,ObjectRetrievalFailureException
	{
		return getHibernateTemplate().find("from LastRecord as lastRecord where lastRecord.code=?",code);
	}

	public void storeLastRecord(LastRecord lastRecord) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(lastRecord);
	}
	public void updateLastRecord(LastRecord lastRecord) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().update(lastRecord);
	}

	public List loadLabServiceByBoth(String serviceName,String labId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from LabServices labService where labService.lab.labId='"+labId+"' and labService.serviceName='"+serviceName+"'");
	}
	
	public List loadCaseReferralBySpecialistId(String specalistId) throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside referral by speId"+specalistId);
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId='"+specalistId+"'");
	}
	
	public void storeFavSep(FavSpecalist favSep) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(favSep);
	}
	
	public List loadFavSep()throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavSpecalist favSep order by favSep.favSpecalistId");
	}
	
	public List loadFavSepByFPId(String fpId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavSpecalist favSep where favSep.familyPhysicianId ='"+fpId+"' order by favSep.favSpecalistId");
	}
	
	public List loadFavSepByFPIdSepId(String fpId,String sepId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavSpecalist favSep where favSep.familyPhysicianId ='"+fpId+"' and favSep.specalistId='"+sepId+"'");
	}
	
	public void deleteFavSpecalist(FavSpecalist favSep) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().delete(favSep);
	}
	
	public List loadFavSepByFPIdName(String fpId,String name)throws DataAccessException,ObjectRetrievalFailureException
	{
		String tempFirstName = "";
		String tempName[] = name.split("\\.");
		String  tempNameV = name;
		String lastNameTemp = name;
		 if(tempName.length > 0)
		  {
			if(!"Dr".equals(tempName[0]))
			{
				log.error("hello");
			    tempNameV = "Dr. "+name;
			}
		   }
		 name = tempNameV;
		return this.getHibernateTemplate().find("from FavSpecalist favSep where favSep.familyPhysicianId ='"+fpId+"'and (CONCAT(favSep.specalistId.firstName,' ',favSep.specalistId.lastName) like '"+name+"%' or favSep.specalistId.firstName like '"+name+"%' or favSep.specalistId.firstName like '"+lastNameTemp+"%' or favSep.specalistId.lastName like '"+name+"%' or favSep.specalistId.lastName like '"+lastNameTemp+"%')) order by favSep.favSpecalistId");
	}
	public List loadSpecalityDoctor(String name)throws DataAccessException,ObjectRetrievalFailureException
	{
		String tempFirstName = "";
		String tempName[] = name.split("\\.");
		String  tempNameV = name;
		String lastNameTemp = name;
		 if(tempName.length > 0)
		  {
			if(!"Dr".equals(tempName[0]))
			{
				log.error("hello");
			    tempNameV = "Dr. "+name;
			}
		   }
		 name = tempNameV;
		return this.getHibernateTemplate().find("from Doctor doctor where (CONCAT(doctor.firstName,' ',doctor.lastName) like '"+name+"%' or doctor.firstName like '"+name+"%'or doctor.firstName like '"+lastNameTemp+"%' or  doctor.lastName like '"+name+"%' or doctor.lastName like '"+lastNameTemp+"%') and (doctor.type='SPECALIST' or doctor.type='BOTH')");
	}
	
	public List loadFavLabByFPId(String fpId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavLab favLab where favLab.familyPhysicianId ='"+fpId+"' order by favLab.favLabId");
	}
	
	public List loadFavLab()throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavLab favLab order by favLab.favLabId");
	}
	
	public void storeFavLab(FavLab favLab) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().save(favLab);
	}

	public List loadFavLabByFPIdLabId(String fpId,String labId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavLab favLab where favLab.familyPhysicianId ='"+fpId+"' and favLab.labId='"+labId+"'");
	}
	
	public void deleteFavLab(FavLab favLab) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().delete(favLab);
	}

	public List loadFavLabByFPIdName(String fpId,String name)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavLab favLab where favLab.familyPhysicianId ='"+fpId+"'and favLab.labId.labName like '"+name+"%' order by favLab.favLabId");
	}
	
	public List loadLabByName(String name)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from Lab lab where lab.labName like '"+name+"%' order by lab.labName");
	}
	
	public List loadFavFPBySEPId(String sepId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavSpecalist favSep where favSep.specalistId ='"+sepId+"' order by favSep.favSpecalistId");
	}
	
	public void updateFavSepdata(FavSpecalist favSep) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().update(favSep);
	}
	
	public List loadFPDoctor(String name)throws DataAccessException,ObjectRetrievalFailureException
	{
		String tempFirstName = "";
		String tempName[] = name.split("\\.");
		String  tempNameV = name;
		String lastNameTemp = name;
		 if(tempName.length > 0)
		  {
			if(!"Dr".equals(tempName[0]))
			{
				log.error("hello");
			    tempNameV = "Dr. "+name;
			}
		   }
		 name = tempNameV;
		return this.getHibernateTemplate().find("from Doctor doctor where (CONCAT(doctor.firstName,' ',doctor.lastName)like '"+name+"%' or doctor.firstName like '"+name+"%' or doctor.firstName like '"+lastNameTemp+"%' or  doctor.lastName like '"+name+"%' or doctor.lastName like '"+lastNameTemp+"%') and (doctor.type='FAMILYPHYSICIAN' or doctor.type='BOTH')");
	}
	
	public List loadFavFPByLabId(String labId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from FavLab favLab where favLab.labId ='"+labId+"' order by favLab.favLabId");
	}
	
	public void updateFavLabdata(FavLab favLab) throws DataAccessException,ObjectRetrievalFailureException
	{
		this.getHibernateTemplate().update(favLab);
	}
	
	public List loadFPSepDoctor(String name)throws DataAccessException,ObjectRetrievalFailureException
	{
		String tempFirstName = "";
		String tempName[] = name.split("\\.");
		String  tempNameV = name;
		String lastNameTemp = name;
		 if(tempName.length > 0)
		  {
			if(!"Dr".equals(tempName[0]))
			{
				log.error("hello");
			    tempNameV = "Dr. "+name;
			}
		   }
		 name = tempNameV;
		return this.getHibernateTemplate().find("from Doctor doctor where (CONCAT(doctor.firstName,' ',doctor.lastName)like '"+name+"%' or doctor.firstName like '"+name+"%' or doctor.firstName like '"+lastNameTemp+"%' or  doctor.lastName like '"+name+"%' or doctor.lastName like '"+lastNameTemp+"%') and (doctor.type='FAMILYPHYSICIAN' or doctor.type='BOTH' or doctor.type='SPECALIST') order by doctor.firstName");
	}
	
	public List loadDoctorByLoginId(String doctorId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("inside loadDoctorByloginId"+doctorId);
		return this.getHibernateTemplate().find("from Doctor doctor where doctor.doctorId='"+doctorId+"'");
	}
	
	public List loadReferralPatients(String id, String fromDate, String toDate, String sepId)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("loginId: "+id);
		
		if(fromDate.equals("") && toDate.equals(""))
		{
			return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"'");
		}
		else if(fromDate.equals(""))
		{
			toDate = toDate+" 23:59:59";
			return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime <= '"+toDate+"'");
		}
		else if(toDate.equals(""))
		{
			fromDate = fromDate+" 00:00:00";
			return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime >= '"+fromDate+"'");
		}
		else
		{
			fromDate = fromDate+" 00:00:00";
			toDate = toDate+" 23:59:59";
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime  between '"+fromDate+"' and '"+toDate+"'");
		}
	}
	public List loadOrderedLabPatients(String id, String fromDate, String toDate, String labId)throws DataAccessException,ObjectRetrievalFailureException
	{
		try
		{
		log.error("loginId: "+id);
		
		if(fromDate.equals("") && toDate.equals(""))
		{
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"'");
		}
		else if(fromDate.equals(""))
		{
			toDate = toDate+" 23:59:59";
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime <= '"+toDate+"'");
		}
		else if(toDate.equals(""))
		{
			fromDate = fromDate+" 00:00:00";
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime >= '"+fromDate+"'");
		}
		else
		{
			fromDate = fromDate+" 00:00:00";
			toDate = toDate+" 23:59:59";
		    return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime  between '"+fromDate+"' and '"+toDate+"'");
		}
		}catch(Exception e)
		{
			System.out.println("OrderedLab Patients Exception: "+e);
			return null;
		}
	}
	
	public List loadReferredAndOrdered(String id, String fromDate, String toDate,String sepId,String labId)throws DataAccessException,ObjectRetrievalFailureException
	{
		try
		{
		log.error("loginId: "+id);
		
		if(fromDate.equals("") && toDate.equals(""))
		{
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.fpcase.caseId = some( select caseRef.fpcase.caseId from CaseReferral caseRef where caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.specalistId.doctorId='"+sepId+"')");
		}
		else if(fromDate.equals(""))
		{
			toDate = toDate+" 23:59:59";
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime <= '"+toDate+"' and labtest.fpcase.caseId = some( select caseRef.fpcase.caseId from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime <= '"+toDate+"')");
		}
		else if(toDate.equals(""))
		{
			fromDate = fromDate+" 00:00:00";
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime >= '"+fromDate+"' and labtest.fpcase.caseId = some( select caseRef.fpcase.caseId from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime >= '"+fromDate+"')");
		}
		else
		{
			fromDate = fromDate+" 00:00:00";
			toDate = toDate+" 23:59:59";
		    return this.getHibernateTemplate().find("from  LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and (labtest.orderedTime  between '"+fromDate+"' and '"+toDate+"') and labtest.fpcase.caseId = some( select caseRef.fpcase.caseId from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and (caseRef.referredTime between '"+fromDate+"' and '"+toDate+"'))");
		}
		}catch(Exception e)
		{
			System.out.println("OrderedReferred Patients Exception: "+e);
			return null;
		}
	}
	
	public List loadRegisteredPatient(String id, String fromDate, String toDate)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("loginId: "+id+" "+fromDate+" "+toDate);
		fromDate = fromDate+" 00:00:00";
		toDate = toDate+" 23:59:59";
		return this.getHibernateTemplate().find("from Patient patient where patient.creator.doctorId ='"+id+"' and patient.tstamp  between '"+fromDate+"' and '"+toDate+"'");
	}
	
	public List loadFPReferralSep(String loginId)throws DataAccessException,ObjectRetrievalFailureException
	{
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+loginId+"'");
	}
	
	public List loadReferredPatient(String id, String fromDate, String toDate)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("loginId: "+id+" "+fromDate+" "+toDate);
		fromDate = fromDate+" 00:00:00";
		toDate = toDate+" 23:59:59";
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId ='"+id+"' and caseRef.referredTime  between '"+fromDate+"' and '"+toDate+"'");
	}
	public List loadReferredPatientByFP(String sepId, String fromDate, String toDate,String id)throws DataAccessException,ObjectRetrievalFailureException
	{
      log.error("loginId: "+id);
		
		if(fromDate.equals("") && toDate.equals(""))
		{
			return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"'");
		}
		else if(fromDate.equals(""))
		{
			toDate = toDate+" 23:59:59";
			return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime <= '"+toDate+"'");
		}
		else if(toDate.equals(""))
		{
			fromDate = fromDate+" 00:00:00";
			return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime >= '"+fromDate+"'");
		}
		else
		{
			fromDate = fromDate+" 00:00:00";
			toDate = toDate+" 23:59:59";
		return this.getHibernateTemplate().find("from CaseReferral caseRef where caseRef.specalistId.doctorId='"+sepId+"' and caseRef.familyPhysicianId.doctorId='"+id+"' and caseRef.referredTime  between '"+fromDate+"' and '"+toDate+"'");
		}
	}
	
	public List loadLabTestOrdered(String id, String fromDate, String toDate)throws DataAccessException,ObjectRetrievalFailureException
	{
		log.error("loginId: "+id+" "+fromDate+" "+toDate);
		fromDate = fromDate+" 00:00:00";
		toDate = toDate+" 23:59:59";
		return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId ='"+id+"' and labtest.orderedTime  between '"+fromDate+"' and '"+toDate+"'");
	}
	
	public List loadtestOrderedPatientByFP(String labId, String fromDate, String toDate,String id)throws DataAccessException,ObjectRetrievalFailureException
	{
      log.error("loginId: "+id);
		
		if(fromDate.equals("") && toDate.equals(""))
		{
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"'");
		}
		else if(fromDate.equals(""))
		{
			toDate = toDate+" 23:59:59";
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime <= '"+toDate+"'");
		}
		else if(toDate.equals(""))
		{
			fromDate = fromDate+" 00:00:00";
			return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime >= '"+fromDate+"'");
		}
		else
		{
			fromDate = fromDate+" 00:00:00";
			toDate = toDate+" 23:59:59";
		return this.getHibernateTemplate().find("from LabTest labtest where labtest.labServices.lab.labId='"+labId+"' and labtest.fpcase.doctor.doctorId='"+id+"' and labtest.orderedTime between '"+fromDate+"' and '"+toDate+"'");
		}
	}
}
