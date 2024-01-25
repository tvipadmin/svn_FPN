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
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.FavLab;
import com.televital.fptelemedicine.domain.FavSpecalist;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.LabServices;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Person;
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.validation.LabManagementValidator;


public class LabManagement {
	
	public static final Logger log4log = Logger.getLogger(LabManagement.class);
	
	private IFPTelemedicineFacade fpportalFacade;
	private List labList = null;
	private UploadInfoBean uib;
	private ILicenseCodec licenseCodec;
	private static long bytesTransferred = 0;
	private static long fileSize = -100;
	private String filePath;
	private Lab lab =null;
	private UploadInfoBean uploadInfoBeanInst; 
	private List favList;
	private List fpList;
	private Doctor doctor;
	
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
	
	private Errors error;
	private LabManagementValidator dmv = new LabManagementValidator();
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
	
	
	
	public List loaddwrLabs()
	{
		log4log.error("inside load labs");
		labList =this.getFpportalFacade().loadLabs();
		List labListO =  new ArrayList();
		
		log4log.error("Size : "+labList.size());
		
		for(int i=0;i < labList.size() ; i++)
		{
			lab= (Lab)labList.get(i);
			Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
			lab.setLogin(login);
			labListO.add(lab);
		}
		log4log.error("Later Size : "+labListO.size());
		return labListO;
		
	}
	
	public List loaddwrLabsPending()
	{
		log4log.error("inside load labs");
		labList =this.getFpportalFacade().loadLabs();
		List labListP =  new ArrayList();
		
		log4log.error("Size : "+labList.size());
		
		for(int i=0;i < labList.size() ; i++)
		{
			lab= (Lab)labList.get(i);
			Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
			if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_PENDING))
			{
			lab.setLogin(login);
			labListP.add(lab);
			}
		}
		log4log.error("Later Size : "+labListP.size());
		return labListP;
		
	}
	
	public List loaddwrLabsDisabled()
	{
		log4log.error("inside load labs");
		labList =this.getFpportalFacade().loadLabs();
		List labListD =  new ArrayList();
		
		log4log.error("Size : "+labList.size());
		
		for(int i=0;i < labList.size() ; i++)
		{
			lab= (Lab)labList.get(i);
			Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
			if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_DISABLED))
			{
			lab.setLogin(login);
			labListD.add(lab);
			}
		}
		log4log.error("Later Size : "+labListD.size());
		return labListD;
		
	}
	
	public List loaddwrLabsEnabled()
	{
		log4log.error("inside load labs");
		labList =this.getFpportalFacade().loadLabs();
		List labListE =  new ArrayList();
		
		log4log.error("Size : "+labList.size());
		
		for(int i=0;i < labList.size() ; i++)
		{
			lab= (Lab)labList.get(i);
			Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
			if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
			{
			lab.setLogin(login);
			labListE.add(lab);
			}
		}
		log4log.error("Later Size : "+labListE.size());
		return labListE;
		
	}
	
	
	public List loaddwrLabsLogin(String loginId)
	{
		log4log.error("inside load labs");
		labList =this.getFpportalFacade().loadLabs();
		List labListO =  new ArrayList();
		List tempList = this.getFpportalFacade().loadFavLabByFPId(loginId);
		log4log.error("FavLab List : "+tempList.size());
		log4log.error("Size : "+labList.size());
		
		for(int i=0;i < labList.size() ; i++)
		{
			lab= (Lab)labList.get(i);
			List labServList = this.getFpportalFacade().loadLabServiceByLabId(lab.getLabId());
			if(labServList.size() > 0)
			{
			for(int j=0; j< tempList.size() ;j++)
			{
				FavLab labTemp = (FavLab)tempList.get(j);
				if(lab.getLabId().equals(labTemp.getLabId().getLabId()) && labTemp.getStatus().equals("CONFIRMED"))
				{
					  Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
					  if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
					  {
			       labListO.add(lab);
			       break;
					  }
				}
			}
			}
		}
		log4log.error("Later Size : "+labListO.size());
		return labListO;
		
	}
	
	public List loaddwrLabsLoginLab(String loginId, String labId)
	{
		log4log.error("inside load labs");
		labList =this.getFpportalFacade().loadLabById(labId);
		List labListO =  new ArrayList();
		List tempList = this.getFpportalFacade().loadFavLabByFPId(loginId);
		log4log.error("FavLab List : "+tempList.size());
		log4log.error("Size : "+labList.size());
		
		for(int i=0;i < labList.size() ; i++)
		{
			lab= (Lab)labList.get(i);
			List labServList = this.getFpportalFacade().loadLabServiceByLabId(lab.getLabId());
			if(labServList.size() > 0)
			{
			for(int j=0; j< tempList.size() ;j++)
			{
				FavLab labTemp = (FavLab)tempList.get(j);
				if(lab.getLabId().equals(labTemp.getLabId().getLabId()) && labTemp.getStatus().equals("CONFIRMED"))
				{
					  Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
					  if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
					  {
			       labListO.add(lab);
			       break;
					  }
				}
			}
			}
		}
		log4log.error("Later Size : "+labListO.size());
		return labListO;
		
	}
	
	
	public void  deleteSelfRegLab(String id)
	{
		Login login =this.getFpportalFacade().loadLogin(id);
		List labList = this.getFpportalFacade().loadLabById(id);
		if(labList.size() > 0)
		{
			Lab lab = (Lab)labList.get(0);
			List listLab =  this.getFpportalFacade().loadFavLab();
			for(int k1=0;k1<listLab.size();k1++)
			{
				FavLab favLab = (FavLab)listLab.get(k1);
				if(favLab.getLabId().getLabId().equals(lab.getLabId()))
				{
					this.getFpportalFacade().deleteFavLab(favLab);
				}
			}
			this.getFpportalFacade().deleteLab(lab, login);
		}
	}
	
	public boolean saveLab(Lab lab)
	{
		log4log.error("new record: "+lab.getLabId()+"   contactPerson: "+lab.getContactPerson());
		String tstamp = FPTelemedicineUtils.getCurrentTimeStamp();
		try
		{
		lab.setTstamp(tstamp);
		lab.getLogin().setLoginId(lab.getLabId());
		
		lab.getLogin().setPassword(this.getLicenseCodec().encrypt(lab.getLogin().getPassword()));
		lab.getLogin().setCurrentLoginStatus("OUT");
		lab.getLogin().setAccountType("LAB");
		lab.getLogin().setAccountStatus("ENABLED");
		lab.getLogin().setTstamp(tstamp);
		lab.getLogin().setPerson((Person)lab);
		lab.setFlag("READLAB");
		this.getFpportalFacade().storeLab(lab,lab.getLogin());
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}

	public boolean deleteLab(Lab lab)
	{
		try
		{
		log4log.error("delete lab"+lab.getFirstName());
		String oldPhoto="";
		String pathname="";
		List labList=this.getFpportalFacade().loadLabById(lab.getLabId());
		Lab doc = (Lab)labList.get(0);
		oldPhoto=doc.getPhoto();
		
		log4log.error("oldPhoto: "+oldPhoto);
		
		pathname = getFilePath()+"/"+oldPhoto;
		log4log.error("pathname: "+pathname);
		File photopath=new File(pathname);
		FPTelemedicineUtils.deletePhoto(photopath);
		Login login =this.getFpportalFacade().loadLogin(lab.getLabId());
		List listLab =  this.getFpportalFacade().loadFavLab();
		for(int k1=0;k1<listLab.size();k1++)
		{
			FavLab favLab = (FavLab)listLab.get(k1);
			if(favLab.getLabId().getLabId().equals(lab.getLabId()))
			{
				this.getFpportalFacade().deleteFavLab(favLab);
			}
		}
		this.getFpportalFacade().deleteLab(lab,login);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
		
	}
	
	public boolean editeachLab(Lab lab)
	{
		try
		{
		log4log.error("edit each lab"+lab.getTstamp());
		String oldPhoto="";
		String newPhoto="";
		String pathname="";
		List labList=this.getFpportalFacade().loadLabById(lab.getLabId());
		Lab doc = (Lab)labList.get(0);
		oldPhoto=doc.getPhoto();
		newPhoto=lab.getPhoto();
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
		lab.setTstamp(tstamp);
		lab.setFlag("READLAB");
		this.getFpportalFacade().editLab(lab);
		return true;
		}
		catch(Exception e)
		{
			log4log.error("Exception Raised is : "+e);
			return false;
		}
	}
	
	public List searchLabdwr(String labName)
	{
		log4log.error("inside search lab"+labName);
		labList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(labName);
		labList = this.getFpportalFacade().searchLabh(str.trim());
		log4log.error("labList Size : "+labList.size());
        List labSearch =  new ArrayList();
				
		for(int i=0;i < labList.size() ; i++)
		{
			lab= (Lab)labList.get(i);
			Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
			lab.setLogin(login);
			labSearch.add(lab);
		}
		log4log.error("Later Size : "+labSearch.size());
		return labSearch;
		
	}
	
	/*public List loadIndividualLab(String labId)
	{
		log4log.error("inside loadIndividualLab: "+labId);
		labList =this.getFpportalFacade().loadLabById(labId);
		log4log.error("labList: "+labList);
		return labList;
	}*/
	
/*	public void fileUploadStart()
	{
		 System.out.println("inside fileUploadStart");
		 
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
    		//System.out.println(k);
    	}
    	
    	uib.setPercentage(per.replace("%", ""));
    	if(per.replace("%", "").equals("100"))
    	{
    		uib.setPercentage("0");
    	}
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
	
	public List showLabSelected(String loginId)
	{
		log4log.error("showLabSelected "+loginId);
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavLabByFPId(loginId);
		
			for(int j=0; j< favList.size();j++)
			{
				FavLab lab = (FavLab)favList.get(j);
				log4log.error(lab.getLabId().getLabId());
				Login login = this.getFpportalFacade().loadLogin(lab.getLabId().getLabId());
				lab.getLabId().setLogin(login);
				tempList.add(lab);
			}
			
		log4log.error("showLabSelected Later Size : "+tempList.size());
		return tempList;		
	}
	
	public List showLabFav(String loginId)
	{
		log4log.error("inside showLabFav "+loginId);
		
		labList = null;
		labList = this.getFpportalFacade().loadLabs();
		log4log.error("showLabFav Size : "+labList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavLabByFPId(loginId);
		Boolean flag = true;		
		for(int i=0;i < labList.size() ; i++)
		{
			flag = true;
			lab= (Lab)labList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavLab labG = (FavLab)favList.get(j);
				log4log.error(lab.getLabId());
				//log4log.error(doc.getSpecalistId().getDoctorId());
				if(lab.getLabId().equals(labG.getLabId().getLabId()) &&  ( labG.getStatus().equals("PENDING") || labG.getStatus().equals("CONFIRMED") || labG.getStatus().equals("REREQUESTED")))
				{
					flag = false;
					break;
				}
			}
			
			if(flag)
			{
				log4log.error(lab.getLabId());
				Login login = this.getFpportalFacade().loadLogin(lab.getLabId());
				if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
				{
				tempList.add(lab);
				}
			}
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public boolean createFavLab(String loginId,String docdetIds)
	{
		try
		{
		 FavLab  favLab = null, favLabTemp = null;;
		 Doctor doc = (Doctor)this.getFpportalFacade().loadDoctorById(loginId).get(0);
		 String labIds[] = docdetIds.split("#####");
		 log4log.error("length : "+labIds.length);
		 for(int i=1;i<labIds.length;i++)
		 {
			 log4log.error("labIds : "+labIds[i]);
		 }
		 int val=1;
		 for(int i=1;i<labIds.length;i++)
		 {
			 Lab lab = (Lab)this.getFpportalFacade().loadLabById(labIds[i]).get(0);
			 log4log.error("lab : "+lab);
			 List favLabList =this.fpportalFacade.loadFavLab();
			 log4log.error("favLabList size:  "+favLabList.size());
			 if(favLabList.size() > 0)
			 {
			    int len = favLabList.size();
			    log4log.error("len : "+len);
			    log4log.error(len-1);
				val =((FavLab)favLabList.get(len-1)).getFavLabId()+1;
				log4log.error("val value : "+val);
			 }
			 else
			 {
				val=1; 
			 }
			 
			 String newRecord = "yes";
			 List tempList = this.getFpportalFacade().loadFavLabByFPIdLabId(doc.getDoctorId(), lab.getLabId());
			 if(tempList.size() > 0)
			 {
				 favLabTemp = (FavLab)tempList.get(0);
				 if(favLabTemp.getStatus().equals("REJECTED"))
				 {
					 newRecord = "no";
				 }
			 }
			 
			 if(newRecord.equals("yes"))
			 {
			 favLab = new FavLab();
			 favLab.setFavLabId(val);
			 favLab.setFamilyPhysicianId(doc);
			 favLab.setLabId(lab);
			 favLab.setStatus("PENDING");
			 favLab.setFlag("NOTREADLAB");
			 log4log.error("Check Status:  "+favLab.getStatus()+favLab.getLabId()+favLab.getFavLabId());
			 this.getFpportalFacade().storeFavLab(favLab);
			 }
			 else
			 {
				 favLabTemp.setStatus("REREQUESTED");
				 this.getFpportalFacade().updateFavLabdata(favLabTemp);
			 }
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("FavSep Exception: "+e);
			return false;
		}
	}
	
	public boolean removeFavLab(String loginId,String docdetIds)
	{
		try
		{
		 FavLab  favLab = null;
		
		 String labIds[] = docdetIds.split("#####");
		 log4log.error("length : "+labIds.length);
		 for(int i=1;i<labIds.length;i++)
		 {
			 log4log.error("labIds : "+labIds[i]);
		 }
		 
		 for(int i=1;i<labIds.length;i++)
		 {
			 List tempList = this.getFpportalFacade().loadFavLabByFPIdLabId(loginId,labIds[i]);
			 log4log.error("Remove Size : "+loginId+" "+labIds[i]+" "+tempList.size());
			 if(tempList.size()>0)
			 {
			 favLab = (FavLab)tempList.get(0);
			 this.getFpportalFacade().deleteFavLab(favLab);
			 }
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("favLab Exception: "+e);
			return false;
		}
	}
	
	public List searchLabFav(String labName,String loginId)
	{
		log4log.error("inside searchDocFav "+loginId);
		
		
        List tempList =  null;
        tempList = new ArrayList();
        String str = FPTelemedicineUtils.duplicateSpaceRemover(labName);
        favList = this.getFpportalFacade().loadFavLabByFPIdName(loginId,str.trim());
        log4log.error(favList.size());
			for(int j=0; j< favList.size();j++)
			{
				FavLab lab = (FavLab)favList.get(j);
				log4log.error(lab.getLabId().getLabId());
				tempList.add(lab);
			}
		
		log4log.error("searchLabFav Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public List searchAllLab(String name,String loginId)
	{
		log4log.error("inside searchAllDoc "+loginId);
		
		labList = null;
		String str = FPTelemedicineUtils.duplicateSpaceRemover(name);
		labList = this.getFpportalFacade().loadLabByName(str.trim());
		log4log.error("searchAllDoc Size : "+labList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavLabByFPId(loginId);
		Boolean flag = true;		
		for(int i=0;i < labList.size() ; i++)
		{
			flag = true;
			lab= (Lab)labList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavLab doc = (FavLab)favList.get(j);
				log4log.error(lab.getLabId());
				log4log.error(doc.getLabId().getLabId());
				if(lab.getLabId().equals(doc.getLabId().getLabId()))
				{
					flag = false;
					break;
					
				}
				
			}
			
			if(flag)
			{
				log4log.error(lab.getLabId());
				tempList.add(lab);
			}
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}
 
	public List showPendingFP(String loginId)
	{
		log4log.error("inside showPendingFP "+loginId);
	
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPByLabId(loginId);
        log4log.error(favList.size());
			for(int j=0; j< favList.size();j++)
			{
				FavLab doc = (FavLab)favList.get(j);
				
				log4log.error(doc.getLabId().getLabId());
				if(doc.getStatus().equals("PENDING") || doc.getStatus().equals("REREQUESTED"))
				{
					Login login = this.getFpportalFacade().loadLogin(doc.getFamilyPhysicianId().getDoctorId());
				    if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
					{
				     tempList.add(doc);
					}
				}
			}
			
		log4log.error("showPendingFP Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public List showConfirmedFav(String loginId)
	{
		log4log.error("inside showConfirmedFav "+loginId);
		
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPByLabId(loginId);
        log4log.error(favList.size());
			for(int j=0; j< favList.size();j++)
			{
				FavLab doc = (FavLab)favList.get(j);
				
				log4log.error(doc.getLabId().getLabId());
				if(doc.getStatus().equals("CONFIRMED"))
				{
				 Login login = this.getFpportalFacade().loadLogin(doc.getFamilyPhysicianId().getDoctorId());
				 doc.getFamilyPhysicianId().setLogin(login);
				  tempList.add(doc);
				}
			}
			
		log4log.error("showConfirmedFav Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public boolean updateFavLab(String loginId,String docdetIds,String type)
	{
		try
		{
		 FavLab  favLab = null;
		 Lab lab = (Lab)this.getFpportalFacade().loadLabById(loginId).get(0);
		 String docIds[] = docdetIds.split("#####");
		 log4log.error("length : "+docIds.length);
		 for(int i=1;i<docIds.length;i++)
		 {
			 log4log.error("docIds : "+docIds[i]);
		 }
		 int val=1;
		 for(int i=1;i<docIds.length;i++)
		 {
			 Doctor fpdoc = (Doctor)this.getFpportalFacade().loadDoctorById(docIds[i]).get(0);
			 List favSepList =this.fpportalFacade.loadFavLabByFPIdLabId(fpdoc.getDoctorId(), lab.getLabId());
			 log4log.error("favSepList size:  "+favSepList.size());
			 
			 favLab = (FavLab)favSepList.get(0);
			 if(type.equals("confirmed"))
			 {
			 favLab.setStatus("CONFIRMED");
			 favLab.setFlag("READLAB");
			 }
			 else
			 {
			  favLab.setStatus("REJECTED");
			  favLab.setFlag("READLAB");
			 }
			 log4log.error("Check Status:  "+favLab.getStatus()+favLab.getLabId()+favLab.getFavLabId());
			 
			 this.getFpportalFacade().updateFavLabdata(favLab);
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("FavLab Exception: "+e);
			return false;
		}
	}
	
	public boolean removeFPNetwork(String loginId,String docdetIds)
	{
		try
		{
		 FavLab  favLab = null;
		
		 String docIds[] = docdetIds.split("#####");
		 log4log.error("length : "+docIds.length);
		 for(int i=1;i<docIds.length;i++)
		 {
			 log4log.error("docIds : "+docIds[i]);
		 }
		 
		 for(int i=1;i<docIds.length;i++)
		 {
			 List tempList = this.getFpportalFacade().loadFavLabByFPIdLabId(docIds[i],loginId);
			 log4log.error("Remove Size : "+loginId+" "+docIds[i]+" "+tempList.size());
			 if(tempList.size()>0)
			 {
			 favLab = (FavLab)tempList.get(0);
			 this.getFpportalFacade().deleteFavLab(favLab);
			 }
		 }
		 
			return true;
		}
		catch(Exception e)
		{
			log4log.error("FavSep Exception: "+e);
			return false;
		}
	}
	
	public List searchDocPending(String docName,String loginId)
	{
		log4log.error("inside searchDocPending "+loginId);
		
		fpList = null;
		String str =FPTelemedicineUtils.duplicateSpaceRemover(docName);
		fpList = this.getFpportalFacade().loadFPSepDoctor(str.trim());
		log4log.error("fpList Size : "+fpList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPByLabId(loginId);
        log4log.error("favList Size : "+favList.size());
			
		for(int i=0;i < fpList.size() ; i++)
		{
			
			doctor= (Doctor)fpList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavLab lab = (FavLab)favList.get(j);
				log4log.error(doctor.getDoctorId());
				log4log.error(lab.getFamilyPhysicianId().getDoctorId());
				log4log.error(lab.getStatus());
				log4log.error("Pending: "+lab.getStatus().equals("PENDING"));
				log4log.error(doctor.getDoctorId().equals(lab.getFamilyPhysicianId().getDoctorId()) && lab.getStatus().equals("PENDING"));
				if(doctor.getDoctorId().equals(lab.getFamilyPhysicianId().getDoctorId()) && ( lab.getStatus().equals("PENDING") || lab.getStatus().equals("REREQUESTED")))
				{
					log4log.error("inside if : "+doctor.getDoctorId());
					tempList.add(lab);
					break;
					
				}
				
			}
	
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}

	public List searchDocConfirmed(String docName,String loginId)
	{
		log4log.error("inside searchDocConfirmed "+loginId);
		
		fpList = null;
		String str =FPTelemedicineUtils.duplicateSpaceRemover(docName);
		fpList = this.getFpportalFacade().loadFPSepDoctor(str.trim());
		log4log.error("fpList Size : "+fpList.size());
		
        List tempList =  null;
        tempList = new ArrayList();
        favList = this.getFpportalFacade().loadFavFPByLabId(loginId);
        log4log.error("favList Size : "+favList.size());
			
		for(int i=0;i < fpList.size() ; i++)
		{
			
			doctor= (Doctor)fpList.get(i);
			
			for(int j=0; j< favList.size();j++)
			{
				FavLab lab = (FavLab)favList.get(j);
				log4log.error(doctor.getDoctorId());
				log4log.error(lab.getFamilyPhysicianId().getDoctorId());
				log4log.error("Confirmed : "+lab.getStatus().equals("CONFIRMED"));
				if(doctor.getDoctorId().equals(lab.getFamilyPhysicianId().getDoctorId()) && lab.getStatus().equals("CONFIRMED"))
				{
					log4log.error("inside if : "+doctor.getDoctorId());
					tempList.add(lab);
					break;
					
				}
				
			}
	
			
		}
		log4log.error("Later Size : "+tempList.size());
		return tempList;
		
	}
	
	public boolean changeStatusLabD(String id, String status)
	{
		try
		{
		Login login =this.getFpportalFacade().loadLogin(id);
		login.setAccountStatus(status);
		this.getFpportalFacade().updateLogin(login);
        List lablist = this.getFpportalFacade().loadLabById(id);
		
		if(lablist.size() > 0)
		{
			log4log.error("Lab Id : "+id);	
			 lab = (Lab)lablist.get(0);
			 lab.setFlag("READLAB");
		}
		this.getFpportalFacade().editLab(lab);
		return true;
		}
		catch(Exception e)
		{
	     return false;
		}
	}
	
	
	
	public void sendConformationStatusLab(String id)
	{
		List labList = this.getFpportalFacade().loadLabById(id);
		Lab lab = (Lab)labList.get(0);
		Login login = this.getFpportalFacade().loadLogin(id);
		
		
		if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
		{
		log4log.error("Mail Id: "+lab.getContactDetails().getEmail());
		String[] recipients = {lab.getContactDetails().getEmail()};
		MailHandler mailHandler = new MailHandler();
		String name = this.getContentStart()+" "+lab.getContactPerson();
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
	
	public int newRequest(String loginId)
	{
		int newReq =0;
	
        List tempList = this.getFpportalFacade().loadFavFPByLabId(loginId);
      
			for(int j=0; j< tempList.size();j++)
			{
				FavLab labr = (FavLab)tempList.get(j);
				log4log.error("getFamilyPhysicianId : "+labr.getFamilyPhysicianId().getDoctorId());
				Login login = (Login)this.getFpportalFacade().loadLogin(labr.getFamilyPhysicianId().getDoctorId());
				log4log.error(" Status : "+login.getAccountStatus());
				if(labr.getFlag() != null)
				{
					if(labr.getFlag().equals("NOTREADLAB") && login.getAccountStatus().equalsIgnoreCase("ENABLED"))
					{
						newReq = newReq+1;
					}
				}
				
			}
		
		return newReq;
		
	}
	
	public String resetPasswordFunLab(String labId)
	{
		try
		{
		log4log.error("DocId: "+labId);
		Login login = this.getFpportalFacade().loadLogin(labId);
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
	
	public List loadIndividualLab(String labId)
	{
		log4log.error("inside loadIndividualLab: "+labId);
		labList =this.getFpportalFacade().loadLabById(labId);
		log4log.error("labList: "+labList.size());
		for(int k=0;k<labList.size();k++)
		{
			log4log.error("ddddddd");
			Lab list = (Lab)labList.get(k);
			String laboId = list.getLabId();
			log4log.error("the lab Id is : "+laboId);
		}
		return labList;
	}
}
