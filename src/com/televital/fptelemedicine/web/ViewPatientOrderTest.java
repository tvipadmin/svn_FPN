
package com.televital.fptelemedicine.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.History;
import com.televital.fptelemedicine.domain.LabServices;
import com.televital.fptelemedicine.domain.LabTest;
import com.televital.fptelemedicine.domain.LoginForm;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.domain.form.EmrBackingObject;
import com.televital.fptelemedicine.domain.form.ViewPatientOrderTestBackingObject;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class ViewPatientOrderTest extends  AbstractFPTelemedicineController{
	
	private static final Logger log4log = Logger.getLogger(ViewPatientOrderTest.class);
	private  FPCase fpcase;
	private LabTest labtest;
	
	private Boolean flag=false;
	private Patient patient;
	private String caseId;

	public ViewPatientOrderTest()
	{
	 setSessionForm(false); 
	}
	
	public static <T extends Object> List<T> getUniques(List<T> list) {
	    //Initiate the necessary variables.


	    final List<T> uniques = new ArrayList<T>();
	    //We declare the map to be final and it will be garbage 
	    //collected at the end of this method's execution, making     //the space used by the map temporary.


	    final HashMap<T,T> hm = new HashMap<T,T>();
	    //Loop through all of the elements.


	    for (T t : list) {
	        //If you don't find the object in the map, it is
	        //unique and we add it to the uniques list.


	        if (hm.get(t) == null) {
	            hm.put(t,t);
	            uniques.add(t);
	        }
	    }
	    return uniques;
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		log4log.error("LabId: "+request.getParameter("idL"));
		String labId=request.getParameter("idL");
		log4log.error("Lab Id: "+labId);
		
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
			Patient patient = labTest.getFpcase().getPatient();
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
	log4log.error("labTestListtemp.size "+labTestListtemp.size());
	ViewPatientOrderTestBackingObject vpobo = new ViewPatientOrderTestBackingObject();
	//labTestListtemp = this.getUniques(labTestListtemp);
	log4log.error("labTestListtemp.size "+labTestListtemp.size());
	vpobo.setLabTestList(labTestListtemp);
	return vpobo;
		
	}
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException error) throws Exception 
	 {	
		log4log.error("Patient Name: "+request.getParameter("patientName"));
		return new ModelAndView("aaaa","command","");
	 }
}
