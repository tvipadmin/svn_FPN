package com.televital.fptelemedicine.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.Person;
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.DoctorManagementValidator;

public class LabRegManagement {
	
	public static final Logger log4log = Logger.getLogger(LabRegManagement.class);
		
		private IFPTelemedicineFacade fpportalFacade;
		private List labList = null;
		private ILicenseCodec licenseCodec;
		private UploadInfoBean uib;
		 private UploadInfoBean uploadInfoBeanInst; 
		 private static long bytesTransferred = 0;
		    private static long fileSize = -100;
		    private String filePath;
		private Lab lab =null;
		private Errors error;
		
		
		 private String hostName;
		 private String authUser;
		 private String authPassword;
		 private int portNo;
		 private String emailFrom;
		 private String regSub;
		 private String regContent;
		 private String contentStart;
		
		
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
		
		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
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
		
		
		public boolean saveRegLab(Lab lab)
		{
			log4log.error("new record: "+lab.getLabId());
			
			String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
			try
			{
			lab.setTstamp(tstamp);
			lab.getLogin().setLoginId(lab.getLabId());
			
			lab.getLogin().setPassword(this.getLicenseCodec().encrypt(lab.getLogin().getPassword()));
			lab.getLogin().setCurrentLoginStatus("OUT");
			lab.getLogin().setAccountType("LAB");
			lab.getLogin().setAccountStatus("PENDING");
			lab.getLogin().setTstamp(tstamp);
			lab.getLogin().setPerson((Person)lab);
			lab.setFlag("NOTREAD");
			this.getFpportalFacade().storeLab(lab,lab.getLogin());
			return true;
			}
			catch(Exception e)
			{
				log4log.error("Exception Raised is : "+e);
				return false;
			}
		}

		public String loadPercentage()
		{
			return uib.getPercentage();
		}
		
		public void sendConformationLab(Lab lab)
		{
			log4log.error("Mail Id: "+lab.getContactDetails().getEmail());
			String[] recipients = {lab.getContactDetails().getEmail()};
			MailHandler mailHandler = new MailHandler();
			int portNo = this.getPortNo();
			String name = this.getContentStart()+" "+lab.getContactPerson();
			String subject = this.getRegSub();
			String message = this.getRegContent();
			String hostName = this.getHostName();
			String user = this.getAuthUser();
			String password =this.getAuthPassword();
			String from = this.getEmailFrom();
			message = name+""+message;
			mailHandler.postMail(recipients, subject, message, from, portNo, hostName, user, password);
		}
		

	}
