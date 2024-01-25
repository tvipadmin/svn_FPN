package com.televital.fptelemedicine.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.WebUtils;

import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;



public class OpenAttachedFileController extends AbstractController
{
	private static final Logger log = Logger.getLogger(OpenAttachedFileController.class);
	private IFPTelemedicineFacade fpportalFacade;
	private String filePath;
	private String tempFilePath;
	private String dataPath;
	private String historyPath;
	private String casePath;
	private String labReportPath;
	
 public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException, DataAccessException,ObjectRetrievalFailureException
 {
	 log.error("Inside Open Attach File");
	  
	  String fileName = (String) request.getParameter("fileName");	  
	  String caseId =(String) request.getParameter("caseId");	  
      String patientId = (String) request.getParameter("patientId");	  
      String type =(String)request.getParameter("attType");	  
      log.error("fileName: "+fileName);
      log.error("caseId: "+caseId);
      log.error("patientId: "+patientId);
      log.error("type: "+type);
	  if(fileName == null || fileName.equals(""))
	  {
		  log.error("Input fileName is null or blank. Cannot continue.");
		  return null;
	  }
	  
	/*  // Check the mode.
	  if(this.getMode()== null)
	  {
		  log.error("Invalid mode specified. valid values are LOGO or PHOTO");
		  return null;
	  }*/
	
	
	  
	  if(fileName != null && !fileName.equals(""))
	  {
		  if(type.equals("case"))
		  {
			  tempFilePath =this.getDataPath()+"/"+patientId+"/"+caseId+"/"+this.getCasePath()+"/";
			  log.error("tempFilePath: "+tempFilePath);
		  }
		  else if(type.equals("history"))
		  {
			  tempFilePath =this.getDataPath()+"/"+patientId+"/"+this.getHistoryPath()+"/";
			  log.error("tempFilePath: "+tempFilePath);
		  }
		  else if(type.equals("labReport"))
		  {
			  tempFilePath =this.getDataPath()+"/"+patientId+"/"+caseId+"/"+this.getLabReportPath()+"/";
			  log.error("tempFilePath: "+tempFilePath);
		  }
		 
		  File attFile = new File(tempFilePath.concat(fileName));
		  log.error("attFile: "+attFile);
		  if(!attFile.exists())
		  {
			  log.error("The actual  file does not exist even if the database has reference.");
			  return null;
		  }
		  	  
		  byte[] buf = new byte[1024];
		  BufferedInputStream in 	= new BufferedInputStream(new FileInputStream(attFile));
		  ServletOutputStream out = response.getOutputStream();

		  response.setContentType("application/octet-stream");
		  //response.setContentLength((int)new File(file_name).length());
		  response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		  // copy data
		  int length;
		  while ((in != null) && ((length = in.read(buf)) != -1))
		  {
			  out.write(buf, 0, length);
		  }
	  }		
	  
	  return null;
 }

 public String getTempFilePath() {
		return tempFilePath;
	}

	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
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
	
	public IFPTelemedicineFacade getFpportalFacade() {
		return fpportalFacade;
	}

	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade) {
		this.fpportalFacade = fpportalFacade;
	}
	
	public String getFilePath() {
		
		if(filePath != null)
		{
			filePath = filePath.replace('\\','/');
			if(!filePath.endsWith("/")) filePath = filePath.concat("/");
		}
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getLabReportPath() {
		return labReportPath;
	}

	public void setLabReportPath(String labReportPath) {
		this.labReportPath = labReportPath;
	}

}