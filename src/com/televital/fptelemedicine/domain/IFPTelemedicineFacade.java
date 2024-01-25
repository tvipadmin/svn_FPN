package com.televital.fptelemedicine.domain;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;

public interface IFPTelemedicineFacade {

	public List loadLoginForm(String userId) throws DataAccessException,ObjectRetrievalFailureException;
	public Login loadLogin(String id) throws DataAccessException,ObjectRetrievalFailureException;
	public void updateLogin(Login login) throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadDoctors()throws DataAccessException,ObjectRetrievalFailureException;
	public void storeDoctor(Doctor doctor, Login login) throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteDoctor(Doctor doctor, Login login)throws DataAccessException,ObjectRetrievalFailureException;
	public void editDoctor(Doctor doctor)throws DataAccessException,ObjectRetrievalFailureException;
	public List searchDoctorh(String docName)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadDoctorById(String doctorId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadSpecalityDoctor()throws DataAccessException,ObjectRetrievalFailureException;
	
	
	public List loadLabs()throws DataAccessException,ObjectRetrievalFailureException;
	public void storeLab(Lab lab, Login login) throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteLab(Lab lab, Login login)throws DataAccessException,ObjectRetrievalFailureException;
	public void editLab(Lab lab)throws DataAccessException,ObjectRetrievalFailureException;
	public List searchLabh(String labName)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabById(String labId)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadPatients(String id)throws DataAccessException,ObjectRetrievalFailureException;
	public boolean storePatient(Patient patient,FPCase casefp,History history) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadPatientById(String patientId)throws DataAccessException,ObjectRetrievalFailureException;
	public void deletePatientdb(Patient patient)throws DataAccessException,ObjectRetrievalFailureException;
	public void editPatient(Patient patient)throws DataAccessException,ObjectRetrievalFailureException;
	public List searchPatienth(String patName, String loginId)throws DataAccessException,ObjectRetrievalFailureException;
	public List searchPatientSh(String patName, String loginId)throws DataAccessException,ObjectRetrievalFailureException;
	public List searchPatienthNo(String patientName,String contactNo,String loginId)throws DataAccessException,ObjectRetrievalFailureException;
	public List searchPatientShNo(String patientName,String contactNo,String loginId)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadCaseFP()throws DataAccessException,ObjectRetrievalFailureException;
	public List loadCaseFPByPatientId(String patientId)throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteFPCase(FPCase fpcase,String patientId)throws DataAccessException,ObjectRetrievalFailureException;
	public void storeCaseHistory(FPCase casefp,History history) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadCaseFPByCaseId(String caseId)throws DataAccessException,ObjectRetrievalFailureException;
	public void updateCaseOpen(FPCase fpcase,History history) throws DataAccessException,ObjectRetrievalFailureException;
	public void updateCaseOpen(FPCase fpcase) throws DataAccessException,ObjectRetrievalFailureException;
	public void storeCase(FPCase casefp) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadOnlyOpenCase(String patientId)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadHistoryByPatientId(String patientId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadHistory()throws DataAccessException,ObjectRetrievalFailureException;
	public boolean deleteHistory(String patientId)throws DataAccessException,ObjectRetrievalFailureException;
	public void updateHistory(History history) throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadLabServices()throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabServices(String labId)throws DataAccessException,ObjectRetrievalFailureException;
	public void storeLabService(LabServices labService) throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteLabService(LabServices labService)throws DataAccessException,ObjectRetrievalFailureException;
	public List searchLabServiceh(String labServiceName)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabServiceByServiceName(String serviceName)throws DataAccessException,ObjectRetrievalFailureException;
	public void editLabService(LabServices labService)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabServiceByLabId(String labId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabServiceByLabServiceId(String labId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabServiceByBoth(String serviceName,String labId)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadLabTest()throws DataAccessException,ObjectRetrievalFailureException;
	public void storeLabTest(LabTest labtest) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabTestByCaseId(String caseId) throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteLabtest(LabTest labtest) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabTestByServiceId(String labServiceId) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabTestByLabId(String labId) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabTestByCaseIdServiceId(String caseId,String serviceId) throws DataAccessException,ObjectRetrievalFailureException;
	public void editLabTest(LabTest labTest)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabTestByLabTestId(String labTestId) throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadCaseReferral()throws DataAccessException,ObjectRetrievalFailureException;
	public void storeCaseReferral(CaseReferral caseRef) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadCaseReferralCaseId(String caseId) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadCaseReferralCaseId(String caseId,String docId) throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteCaseReferral(CaseReferral caseRef) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadCaseReferralByReferralId(String caseReferralId) throws DataAccessException,ObjectRetrievalFailureException;
	public void editCaseReferral(CaseReferral caseReferral)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List findLastExternalId(String code) throws DataAccessException,ObjectRetrievalFailureException;
	public void storeLastRecord(LastRecord lastRecord) throws DataAccessException,ObjectRetrievalFailureException;
	public void updateLastRecord(LastRecord lastRecord) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadCaseReferralBySpecialistId(String specalistId) throws DataAccessException,ObjectRetrievalFailureException;
	
	public void storeFavSep(FavSpecalist favSep) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFavSep()throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFavSepByFPId(String fpId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFavSepByFPIdSepId(String fpId,String sepId)throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteFavSpecalist(FavSpecalist favSep) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFavSepByFPIdName(String fpId,String name)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadSpecalityDoctor(String name)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadFavLabByFPId(String id)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFavLab()throws DataAccessException,ObjectRetrievalFailureException;
	public void storeFavLab(FavLab favLab) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFavLabByFPIdLabId(String fpId,String labId)throws DataAccessException,ObjectRetrievalFailureException;
	public void deleteFavLab(FavLab favLab) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFavLabByFPIdName(String fpId,String name)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabByName(String name)throws DataAccessException,ObjectRetrievalFailureException;
	
	
	public List loadFavFPBySEPId(String fpId)throws DataAccessException,ObjectRetrievalFailureException;
	public void updateFavSepdata(FavSpecalist favSep) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFPDoctor(String name)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadFavFPByLabId(String labId)throws DataAccessException,ObjectRetrievalFailureException;
	public void updateFavLabdata(FavLab favLab) throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFPSepDoctor(String name)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadDoctorByLoginId(String doctorId)throws DataAccessException,ObjectRetrievalFailureException;
	
	public List loadReferralPatients(String id, String fromDate, String toDate, String sepId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadRegisteredPatient(String id, String fromDate, String toDate)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadOrderedLabPatients(String id, String fromDate, String toDate, String labId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadReferredAndOrdered(String id, String fromDate, String toDate,String sepId,String labId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadFPReferralSep(String loginId)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadReferredPatient(String id, String fromDate, String toDate)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadReferredPatientByFP(String sepId, String fromDate, String toDate,String id)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadLabTestOrdered(String id, String fromDate, String toDate)throws DataAccessException,ObjectRetrievalFailureException;
	public List loadtestOrderedPatientByFP(String labId, String fromDate, String toDate,String id)throws DataAccessException,ObjectRetrievalFailureException;
}
