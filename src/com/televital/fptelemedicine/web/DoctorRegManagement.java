package com.televital.fptelemedicine.web;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Person;
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.DoctorManagementValidator;

public class DoctorRegManagement {
	
public static final Logger log4log = Logger.getLogger(DoctorRegManagement.class);



	private IFPTelemedicineFacade fpportalFacade;
	private List doctorList = null;
	private ILicenseCodec licenseCodec;
	private UploadInfoBean uib;
	 private UploadInfoBean uploadInfoBeanInst; 
	 private static long bytesTransferred = 0;
	    private static long fileSize = -100;
	    private String filePath;
	    
	    private String hostName;
	    private String authUser;
	    private String authPassword;
	    private int portNo;
	    private String emailFrom;
	    private String regSub;
	    private String regContent;
	    private String contentStart;
	    
	    
	private Doctor doctor =null;
	private Errors error;
	
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
	
	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}
	public int getPortNo() {
		return portNo;
	}

	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}
	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getRegSub() {
		return regSub;
	}

	public void setRegSub(String regSub) {
		this.regSub = regSub;
	}
	public String getRegContent() {
		return regContent;
	}

	public void setRegContent(String regContent) {
		this.regContent = regContent;
	}
	public String getContentStart() {
		return contentStart;
	}

	public void setContentStart(String contentStart) {
		this.contentStart = contentStart;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public boolean saveRegDoctor(Doctor doctor)
	{
		log4log.error("new record: "+doctor.getDoctorId());
		
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		try
		{
		doctor.setTstamp(tstamp);
		doctor.getLogin().setLoginId(doctor.getDoctorId());
		
		doctor.getLogin().setPassword(this.getLicenseCodec().encrypt(doctor.getLogin().getPassword()));
		doctor.getLogin().setCurrentLoginStatus("OUT");
		doctor.getLogin().setAccountType("DOCTOR");
		doctor.getLogin().setAccountStatus("PENDING");
		doctor.getLogin().setTstamp(tstamp);
		doctor.getLogin().setPerson((Person)doctor);
		doctor.setFlag("NOTREAD");
		this.getFpportalFacade().storeDoctor(doctor,doctor.getLogin());
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
	
	public void sendConformation(Doctor doctor)
	{
		log4log.error("Mail Id: "+doctor.getContactDetails().getEmail());
		String[] recipients = {doctor.getContactDetails().getEmail()};
		MailHandler mailHandler = new MailHandler();
		String name = this.getContentStart()+" "+doctor.getFirstName()+" "+doctor.getLastName();
		int portNo = this.getPortNo();
		String subject = this.getRegSub();
		String message = this.getRegContent();
		String hostName = this.getHostName();
		String user = this.getAuthUser();
		String password =this.getAuthPassword();
		String from = this.getEmailFrom();
		message = name+""+message;
		mailHandler.postMail(recipients, subject, message, from, portNo, hostName, user, password);
	}
	
	
	public String loadPercentage()
	{
		return uib.getPercentage();
	}
	

}
