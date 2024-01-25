package com.televital.fptelemedicine.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.omg.CORBA.Request;

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class MainPageManagement {
	
	public static final Logger log4log = Logger.getLogger(MainPageManagement.class);
	private IFPTelemedicineFacade fpportalFacade;
	private ILicenseCodec licenseCodec;
	private List doctorList = null,labList = null;
	private String LastRecord="false";
	private String LastRecordLab="false";
	HttpSession sess;
	
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
	
	public List loadDoctors(int start,int end,String type,String name)
	{
		log4log.error("inside loadDoctorMain "+start+" "+end);
		int tempEnd =end;
		List tempList = new ArrayList();
		List enableDoc = null;
		if(start < 0)
		{
			start = 0;
		}
		String str = FPTelemedicineUtils.duplicateSpaceRemover(name);
		enableDoc =this.getFpportalFacade().loadFPSepDoctor(str.trim());
		log4log.error("Full Size: "+enableDoc.size());
		doctorList = new ArrayList();
		for(int e =0;e<enableDoc.size(); e++)
		{
		   Doctor doc1 = (Doctor)enableDoc.get(e);
		   Login login = this.getFpportalFacade().loadLogin(doc1.getDoctorId());
		 
		   if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
		   {
			   
			   doctorList.add(doc1);
		   }
		}
		log4log.error("Enabled Doc List Size: "+doctorList.size());
		if(end >= doctorList.size())
		{
			log4log.error("end : "+end+"  "+type);
			end = doctorList.size();
		    LastRecord ="true";
		}
		log4log.error("inside loadDoctorMain "+start+" "+end +"Size : "+doctorList.size());
		for(int i =start; i< end;i++ )
		{
			Doctor doc = (Doctor)doctorList.get(i);
			tempList.add(doc);
			
		}
		if(type.equals("next") || type.equals("load"))
		{
		if(LastRecord.equals("true"))
		{
			log4log.error("when true Doctor");
			tempList.add("true");
			LastRecord = "false";
		}
		else
		{
			tempList.add("false");
			LastRecord = "false";
		}
		}
		return tempList;
	}
	
	public List loadLabs(int start,int end,String type,String name)
	{
		log4log.error("inside loadLabMain "+start+" "+end);
		int tempEnd =end;
		List tempList = new ArrayList();
		List enableLab = null;
		if(start < 0)
		{
			start = 0;
		}
		
	    String str = FPTelemedicineUtils.duplicateSpaceRemover(name);
	    
	    enableLab =this.getFpportalFacade().loadLabByName(str.trim());
	    labList = new ArrayList();
		for(int e =0;e<enableLab.size(); e++)
		{
		   Lab lab1 = (Lab)enableLab.get(e);
		   Login login = this.getFpportalFacade().loadLogin(lab1.getLabId());
		 
		   if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
		   {
			   labList.add(lab1);
		   }
		}
		if(end >= labList.size())
		{
			log4log.error("end : "+end+"  "+type);
			end = labList.size();
		    LastRecordLab ="true";
		}
		log4log.error("inside loadLAbMain "+start+" "+end +"Size : "+labList.size());
		for(int i =start; i< end;i++ )
		{
			Lab lab = (Lab)labList.get(i);
			tempList.add(lab);
			
		}
		if(type.equals("next") || type.equals("load"))
		{
		if(LastRecordLab.equals("true"))
		{
			log4log.error("when true LAb");
			tempList.add("true");
			LastRecordLab = "false";
		}
		else
		{
			tempList.add("false");
			LastRecordLab = "false";
		}
		}
		return tempList;
	}
	
	
}
