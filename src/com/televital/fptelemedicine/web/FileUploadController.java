package com.televital.fptelemedicine.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.televital.fptelemedicine.domain.FileUpload;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.UploadInfoBean;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class FileUploadController extends SimpleFormController {
    private static Log log = LogFactory.getLog(FileUploadController.class);
    private static long bytesTransferred = 0;
    private static long fileSize = -100;
    private String filePath;
    private String dataPath;
    private String historyPath;
    private String casePath;
    private String labReportPath;
	String fileName ="";
    
    public FileUploadController() {
      
        setCommandClass(FileUpload.class);
    }
     private UploadInfoBean uploadInfoBeanInst; 
     
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
	
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getHistoryPath() {
		return historyPath;
	}

	public void setHistoryPath(String historyPath) {
		this.historyPath = historyPath;
	}
	
	public String getCasePath() {
		return casePath;
	}

	public void setCasePath(String casePath) {
		this.casePath = casePath;
	}
	
	public String getLabReportPath() {
		return labReportPath;
	}

	public void setLabReportPath(String labReportPath) {
		this.labReportPath = labReportPath;
	}
	
    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, 
                                    Object command, BindException errors)
    throws ServletException, IOException {
    
  	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

    	MultipartFile filea = multipartRequest.getFile("file1");

    	InputStream inputStream = null;
    	OutputStream outputStream = null;
    	UploadInfoBean uib = this.getUploadInfoBeanInst();
    	bytesTransferred=0;
    	if (filea.getSize() > 0) {
  	    inputStream = filea.getInputStream();
  	    fileSize=filea.getSize();
  	  System.out.println("filea.getOriginalFilename() FileUpload Controller : "+filea.getOriginalFilename());
  	     if(request.getParameter("uploadtype")!=null)
  	     {
  	    	 System.out.println("uploadtype: "+request.getParameter("uploadtype"));
  	    	if(request.getParameter("uploadtype").equals("history"))
  	    	{
  	    		 HttpSession sess = request.getSession();
  	    		System.out.println("inside second if "+sess.getValue("PatientId")+"  "+sess.getAttribute("PatientId"));
  	    		
  	    		 String patientId = sess.getAttribute("PatientId").toString();
  	    		 String caseId = sess.getAttribute("CaseId").toString();
				 String filepathorg=getDataPath()+"/"+patientId+"/"+getHistoryPath();
  	    		fileName = FPTelemedicineUtils.getUniqueFileName(filepathorg,filea.getOriginalFilename());
  	    	}
  	    	else if(request.getParameter("uploadtype").equals("case"))
  	    	{
  	    		 HttpSession sess = request.getSession();
   	    		System.out.println("inside case second if "+sess.getValue("PatientId")+"  "+sess.getAttribute("PatientId"));
   	    		
   	    		 String patientId = sess.getAttribute("PatientId").toString();
   	    		 String caseId = sess.getAttribute("CaseId").toString();
 				 String filepathorg=getDataPath()+"/"+patientId+"/"+caseId+"/"+getCasePath();
   	    		fileName = FPTelemedicineUtils.getUniqueFileName(filepathorg,filea.getOriginalFilename());
  	    		
  	    	}
  	    	else if(request.getParameter("uploadtype").equals("labreport"))
  	    	{
  	    		 HttpSession sess = request.getSession();
    	    		System.out.println("inside labreport second if "+sess.getValue("PatientId")+"  "+sess.getAttribute("PatientId")+" "+request.getParameter("caseIdVal"));
    	    		
    	    		 String patientId = request.getParameter("patientIdVal");
    	    		 String caseId = request.getParameter("caseIdVal");
  				     String filepathorg=getDataPath()+"/"+patientId+"/"+caseId+"/"+getLabReportPath();
    	    		fileName = FPTelemedicineUtils.getUniqueFileName(filepathorg,filea.getOriginalFilename());
  	    	}
  	     }
  	     else
  	     {
  	    
  	      fileName = FPTelemedicineUtils.getUniqueFileName(getFilePath(),filea.getOriginalFilename());
  	     }
  	    System.out.println("fileSize : "+fileSize + "fileName: "+fileName+"  "+request.getParameter("uploadtype"));
  	    
  	    String docName = fileName.substring(fileName.lastIndexOf("/")+1);
  	  System.out.println("one");
		String docExt = docName.substring(docName.lastIndexOf(".")+1,docName.length());
		  System.out.println("one");
  	    
  	    
  	   // File realUpload = new File(getFilePath());
  	  System.out.println("one");
  	    //realUpload.mkdir();
    	outputStream = new FileOutputStream(fileName);
    	 System.out.println("one");
        HttpSession sess = request.getSession();
        System.out.println("one");
        sess.setAttribute("PhotoName", fileName.substring(fileName.lastIndexOf("/")+1));
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
    	for(int k=0;k<500;k++)
    	{
    	  //System.out.println(k);
    	}
    	
    	uib.setPercentage(per.replace("%", ""));
    	if(per.replace("%", "").equals("100"))
    	{
    		uib.setPercentage("-1");
    	}
    	
    	System.out.println("per : "+per.replace("%", ""));
    	System.out.println("fileSize : "+fileSize + "fileName: "+fileName);
    	}
    	outputStream.close();
    	inputStream.close();
    	}

    	 if(request.getParameter("uploadtype")!=null)
  	     {
  	    	 System.out.println("uploadtype: "+request.getParameter("uploadtype"));
  	    	if(request.getParameter("uploadtype").equals("history"))
  	    	{
    	     return new ModelAndView("fileUploadHistory");
  	    	}
  	    	else if(request.getParameter("uploadtype").equals("case"))
  	    	{
  	    		return new ModelAndView("fileUploadCase");
  	    	}
  	    	return new ModelAndView("fileUpload");
  	     }
    	 else
    	 {
    		 return new ModelAndView("fileUpload");
    	 }

    }
}