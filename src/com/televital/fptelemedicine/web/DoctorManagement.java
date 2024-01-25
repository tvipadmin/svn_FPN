package com.televital.fptelemedicine.web;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.FavLab;
import com.televital.fptelemedicine.domain.FavSpecalist;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Person;
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.DoctorManagementValidator;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class DoctorManagement {
	
	public static final Logger log4log = Logger.getLogger(DoctorManagement.class);
	
	private IFPTelemedicineFacade fpportalFacade;
	private List doctorList = null;
	private ILicenseCodec licenseCodec;
	private UploadInfoBean uib;
	 private UploadInfoBean uploadInfoBeanInst; 
	 private static long bytesTransferred = 0;
	    private static long fileSize = -100;
	    private String filePath;
	private Doctor doctor =null;
	private Errors error;
	private String hostName;
	 private String authUser;
    private String authPassword;
    private int portNo;
    private String emailFrom;
    private String regSub;
    private String regContent1;
    private String regContent2;
    private String contentStart;
	private String resetPassword;
	
	private DoctorManagementValidator dmv = new DoctorManagementValidator();
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
	
	public String getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
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
	public String getRegContent1() {
		return regContent1;
	}

	public void setRegContent1(String regContent1) {
		this.regContent1= regContent1;
	}
	public String getRegContent2() {
		return regContent2;
	}

	public void setRegContent2(String regContent2) {
		this.regContent2 = regContent2;
	}
	public String getContentStart() {
		return contentStart;
	}

	public void setContentStart(String contentStart) {
		this.contentStart = contentStart;
	}
	
	
	
	public List loadDoctors()
	{
		doctorList =this.getFpportalFacade().loadDoctors();
		List doctorListO =  new ArrayList();
		
		log4log.error("Size : "+doctorList.size());
		
		for(int i=0;i < doctorList.size() ; i++)
		{
			doctor= (Doctor)doctorList.get(i);
			Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
			doctor.setLogin(login);
			doctorListO.add(doctor);
		}
		log4log.error("Later Size : "+doctorListO.size());
		return doctorListO;
		
	}
	
	public List loadDoctorsPending()
	{
		doctorList =this.getFpportalFacade().loadDoctors();
		List doctorListP =  new ArrayList();
		
		log4log.error("Size loadDoctorsPending: "+doctorList.size());
		
		for(int i=0;i < doctorList.size() ; i++)
		{
			doctor= (Doctor)doctorList.get(i);
			Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
			//log4log.error(login.getAccountStatus());
			//log4log.error(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_PENDING));
			if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_PENDING))
			{
			doctor.setLogin(login);
			doctorListP.add(doctor);
			}
		}
		log4log.error("Later Size loadDoctorsPending : "+doctorListP.size());
		return doctorListP;
		
	}
	
	
	public List loadDoctorsDisabled()
	{
		doctorList =this.getFpportalFacade().loadDoctors();
		List doctorListD =  new ArrayList();
		
		log4log.error("Size loadDoctorsDisabled  : "+doctorList.size());
		
		for(int i=0;i < doctorList.size() ; i++)
		{
			doctor= (Doctor)doctorList.get(i);
			Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
			//log4log.error(login.getLoginId());
			//log4log.error(login.getAccountStatus());
			//log4log.error(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_DISABLED));
			if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_DISABLED))
			{
			doctor.setLogin(login);
			doctorListD.add(doctor);
			}
		}
		log4log.error("Later Size loadDoctorsDisabled: "+doctorListD.size());
		return doctorListD;
		
	}
	
	public List loadDoctorsEnabled()
	{
		doctorList =this.getFpportalFacade().loadDoctors();
		List doctorListE=  new ArrayList();
		
		log4log.error("Size loadDoctorsEnabled: "+doctorList.size());
		
		for(int i=0;i < doctorList.size() ; i++)
		{
			doctor= (Doctor)doctorList.get(i);
			Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
			//log4log.error(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED));
			if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
			{
			doctor.setLogin(login);
			doctorListE.add(doctor);
			}
		}
		log4log.error("Later Size loadDoctorsEnabled: "+doctorListE.size());
		return doctorListE;
		
	}
	
	public boolean changeStatusDoctor(String id, String status)
	{
		try
		{
		log4log.error("Doc Id : "+id);	
	    Login login =this.getFpportalFacade().loadLogin(id);
		login.setAccountStatus(status);
		this.getFpportalFacade().updateLogin(login);
		
		List doclist = this.getFpportalFacade().loadDoctorById(id);
		
		if(doclist.size() > 0)
		{
			log4log.error("Doc Id : "+id);	
			 doctor = (Doctor)doclist.get(0);
			 doctor.setFlag("READ");
		}
		this.getFpportalFacade().editDoctor(doctor);
		return true;
		}
		catch(Exception e)
		{
			log4log.error(e);
			return false;
		}
	}
	public void  deleteSelfRegDoctor(String id)
	{
		Login login =this.getFpportalFacade().loadLogin(id);
		List doctorList = this.getFpportalFacade().loadDoctorById(id);
		if(doctorList.size() > 0)
		{
			Doctor doctor = (Doctor)doctorList.get(0);
			List favSeplist = this.getFpportalFacade().loadFavSep();
			for(int j=0;j<favSeplist.size();j++)
			{
				FavSpecalist favSep = (FavSpecalist)favSeplist.get(j);
				if(favSep.getFamilyPhysicianId().getDoctorId().equals(doctor.getDoctorId()) || favSep.getSpecalistId().getDoctorId().equals(doctor.getDoctorId()))
				{
					this.getFpportalFacade().deleteFavSpecalist(favSep);
				}
			}
			this.getFpportalFacade().deleteDoctor(doctor,login);
			
		}
	}
	
	
	public void sendConformationStatus(String id)
	{
		log4log.error("hi hellllllllllllllllllllllll");
		List docList = this.getFpportalFacade().loadDoctorById(id);
		Doctor doctor = (Doctor)docList.get(0);
		Login login = this.getFpportalFacade().loadLogin(id);
		
		
		if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
		{
		log4log.error("Mail Id: "+doctor.getContactDetails().getEmail());
		String[] recipients = {doctor.getContactDetails().getEmail()};
		MailHandler mailHandler = new MailHandler();
		String name = this.getContentStart()+" "+doctor.getFirstName()+" "+doctor.getLastName();
		int portNo = this.getPortNo();
		String subject = this.getRegSub();
		String message1 = this.getRegContent1();
		String message2 = this.getRegContent2();
		String message = "";
		String hostName = this.getHostName();
		String user = this.getAuthUser();
		String password =this.getAuthPassword();
		String from = this.getEmailFrom();
		message = name+" "+message1+" "+login.getLoginId()+" "+message2;
		mailHandler.postMail(recipients, subject, message, from, portNo, hostName, user, password);
		}
	}
	public boolean saveDoctor(Doctor doctor)
	{
		log4log.error("new record: "+doctor.getDoctorId());
		
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		String tempFirstName = "";
		String tempName[] = doctor.getFirstName().split("\\.");
		log4log.error("doctor.getFirstName() "+doctor.getFirstName()+tempName.length);
		
			  if(tempName.length > 0)
			  {
				
				if("Dr".equals(tempName[0]))
				{
					int len = doctor.getFirstName().length();
				    String  tempNameV = doctor.getFirstName().substring(4,len);
				    tempFirstName = "Dr. "+tempNameV.trim();
				    log4log.error(tempFirstName);
				}
				else
				{
					tempFirstName = "Dr. "+doctor.getFirstName().trim();
					log4log.error("firstElse "+tempFirstName);
				}
			  }
			  else
			  {
				  //alert(value);
				  tempFirstName = "Dr. "+doctor.getFirstName().trim();
				  log4log.error("secondElse "+tempFirstName);
			  }
		 
		try
		{
		doctor.setTstamp(tstamp);
		doctor.setFirstName(tempFirstName);
		doctor.getLogin().setLoginId(doctor.getDoctorId());
		doctor.getLogin().setPassword(this.getLicenseCodec().encrypt(doctor.getLogin().getPassword()));
		doctor.getLogin().setCurrentLoginStatus("OUT");
		doctor.getLogin().setAccountType("DOCTOR");
		doctor.getLogin().setAccountStatus("ENABLED");
		doctor.getLogin().setTstamp(tstamp);
		doctor.getLogin().setPerson((Person)doctor);
		doctor.setFlag("READ");
		this.getFpportalFacade().storeDoctor(doctor,doctor.getLogin());
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}

	public boolean deleteDoctor(Doctor doctor)
	{
		
		try
		{
			log4log.error("delete doctor"+doctor.getFirstName());
		
		
		String oldPhoto="";
		String pathname="";
		List docList=this.getFpportalFacade().loadDoctorById(doctor.getDoctorId());
		Doctor doc = (Doctor)docList.get(0);
		oldPhoto=doc.getPhoto();
		
		log4log.error("oldPhoto: "+oldPhoto);
		
		pathname = getFilePath()+"/"+oldPhoto;
		log4log.error("pathname: "+pathname);
		File photopath=new File(pathname);
		FPTelemedicineUtils.deletePhoto(photopath);
		Login login =this.getFpportalFacade().loadLogin(doctor.getDoctorId());
		
		List favSeplist = this.getFpportalFacade().loadFavSep();
		for(int j=0;j<favSeplist.size();j++)
		{
			FavSpecalist favSep = (FavSpecalist)favSeplist.get(j);
			if(favSep.getFamilyPhysicianId().getDoctorId().equals(doctor.getDoctorId()) || favSep.getSpecalistId().getDoctorId().equals(doctor.getDoctorId()))
			{
				this.getFpportalFacade().deleteFavSpecalist(favSep);
			}
		}
		
		List favLabList = this.getFpportalFacade().loadFavLab();
		for(int g=0;g<favLabList.size();g++)
		{
			FavLab favLab = (FavLab)favLabList.get(g);
			if(favLab.getFamilyPhysicianId().getDoctorId().equals(doctor.getDoctorId()))
			{
				this.getFpportalFacade().deleteFavLab(favLab);
			}
			
		}		
		this.getFpportalFacade().deleteDoctor(doctor,login);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public boolean editeachDoctor(Doctor doctor)
	{
		try
		{
		log4log.error("edit each doctor"+doctor.getTstamp()+doctor.getIdentification()+doctor.getClinicName());
		String oldPhoto="";
		String newPhoto="";
		String pathname="";
		
		String tempFirstName = "";
		String tempName[] = doctor.getFirstName().split("\\.");
		log4log.error("doctor.getFirstName() "+doctor.getFirstName()+tempName.length);
		
			  if(tempName.length > 0)
			  {
				
				if("Dr".equals(tempName[0]))
				{
					int len = doctor.getFirstName().length();
				    String  tempNameV = doctor.getFirstName().substring(4,len);
				    tempFirstName = "Dr. "+tempNameV.trim();
				    log4log.error(tempFirstName);
				}
				else
				{
					tempFirstName = "Dr. "+doctor.getFirstName().trim();
					log4log.error("firstElse "+tempFirstName);
				}
			  }
			  else
			  {
				  //alert(value);
				  tempFirstName = "Dr. "+doctor.getFirstName().trim();
				  log4log.error("secondElse "+tempFirstName);
			  }
		 
		
		
		List docList=this.getFpportalFacade().loadDoctorById(doctor.getDoctorId());
		Doctor doc = (Doctor)docList.get(0);
		oldPhoto=doc.getPhoto();
		newPhoto=doctor.getPhoto();
		log4log.error("oldPhoto: "+oldPhoto);
		log4log.error("newPhoto: "+newPhoto);
		pathname = getFilePath()+"/"+oldPhoto;
		log4log.error("pathname: "+pathname);
		File photopath=new File(pathname);
		if(oldPhoto !=null && newPhoto != null)
		{
		if(!oldPhoto.equals(newPhoto))
		{
			log4log.error("oldPhoto.equals(newPhoto) :"+oldPhoto.equals(newPhoto));
		  FPTelemedicineUtils.deletePhoto(photopath); 
		}
		}
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		doctor.setTstamp(tstamp);
		doctor.setFlag("READ");
		doctor.setFirstName(tempFirstName);
		this.getFpportalFacade().editDoctor(doctor);
		return true;
	  }
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public List searchDoctordwr(String doctorName)
	{
		log4log.error("inside search doctor"+doctorName);
		doctorList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(doctorName);
		doctorList = this.getFpportalFacade().searchDoctorh(str.trim());
		log4log.error("doctorList Size : "+doctorList.size());
        List doctorSearch =  new ArrayList();
				
		for(int i=0;i < doctorList.size() ; i++)
		{
			doctor= (Doctor)doctorList.get(i);
			Login login = this.getFpportalFacade().loadLogin(doctor.getDoctorId());
			doctor.setLogin(login);
			doctorSearch.add(doctor);
		}
		log4log.error("Later Size : "+doctorSearch.size());
		return doctorSearch;
		
	}
	
	/*public List loadIndividualDoctor(String doctorId)
	{
		log4log.error("inside loadIndividualDoctor: "+doctorId);
		doctorList =this.getFpportalFacade().loadDoctorById(doctorId);
		log4log.error("doctorList: "+doctorList);
		return doctorList;
	}
	
	public void fileUploadStart()
	{
		 System.out.println("inside fileUploadStart aaaaa");
		 
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
    		System.out.println(k);
    	}
    	
    	System.out.println("per aaaaaaaaaaaaaaaaaa: "+per);
    	uib.setPercentage(per.replace("%", ""));
    	
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
	*/
	
	public String loadPercentage()
	{
		return uib.getPercentage();
	}
	
	public List loadDoctorId(String doctorId)
	{
		doctorList =this.getFpportalFacade().loadDoctorById(doctorId);
		return doctorList;
	}
	
	public int newLabReg()
	{
		int newReq =0;
	
        List tempList = this.getFpportalFacade().loadLabs();
      
			for(int j=0; j< tempList.size();j++)
			{
				Lab lab = (Lab)tempList.get(j);
				if(lab.getFlag() != null)
				{
					if(lab.getFlag().equals("NOTREAD"))
					{
						newReq = newReq+1;
					}
				}
				
			}
			
		
		return newReq;
		
	}
	
	public String resetPasswordFun(String docId)
	{
		try
		{
		log4log.error("DocId: "+docId);
		Login login = this.getFpportalFacade().loadLogin(docId);
		login.setPassword(this.getLicenseCodec().encrypt(this.getResetPassword()));
		this.getFpportalFacade().updateLogin(login);
		return this.getResetPassword();
		}
		catch(Exception e)
		{
	      log4log.error("Exception due to : "+e);
		  return "NotUpdated" ;
		  
		}
	
	}
	
	public List loadDoctorByLogin(String doctorId)
	{
		doctor = new Doctor();
		//log4log.error("obj value : "+doctor.getDoctorId());
		log4log.error("inside load doctor by loginId : "+doctorId);
		List docList= this.getFpportalFacade().loadDoctorByLoginId(doctorId);
		for(int k=0;k<docList.size();k++)
		{
			log4log.error("cccccc");
			Doctor list = (Doctor)docList.get(k);
			String docId = list.getDoctorId();
			log4log.error("the doc Id is : "+docId);
		}
		return docList;
		
	}
	
}
