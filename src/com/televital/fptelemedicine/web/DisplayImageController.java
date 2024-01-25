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

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Patient;



public class DisplayImageController extends AbstractController
{
	private static final Logger log = Logger.getLogger(DisplayImageController.class);
	
	private String filePath;
	private String mode;
	private IFPTelemedicineFacade fpportalFacade;
	
	private final String SIGNATURE 	= "SIGNATURE";
	private final String PHOTO 		= "PHOTO";
	private final String LOGO 		= "LOGO";
	
  public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, DataAccessException,ObjectRetrievalFailureException
  {
	  
		
	  
	  // Get the input Id
	  log.error("GGGGGGGGGGGGGGGGGGG");
	  String id = (String) request.getParameter("Id");	 
	  log.error("id: "+id);
	  String typePerson = (String)request.getParameter("typePerson");	
	  log.error("typePerson: "+typePerson);
	  if(id == null || id.equals(""))
	  {
		  log.error("Input Id is null or blank. Cannot continue.");
		  return null;
	  }
	  
	  // Check the mode.
	  if(this.getMode()== null)
	  {
		  log.error("Invalid mode specified. valid values are LOGO or PHOTO");
		  return null;
	  }
	
	  // Load the user Object
	  String imageName = null;
	  try
	  {
		  log.error("one");
		  if(mode.equals(PHOTO))
		  {
			  log.error("two : "+typePerson);
			 if(typePerson.equals("doctor"))
			 {
				 log.error("three : "+id);
				 Doctor doctor =(Doctor)this.getFpportalFacade().loadDoctorById(id).get(0);
			     imageName = doctor.getPhoto();
			     log.error("four : "+imageName);
			 }
			 else if(typePerson.equals("patient"))
			 {
			 Patient patient =(Patient)this.getFpportalFacade().loadPatientById(id).get(0);
		     imageName = patient.getPhoto();
			 }
			 
		  }
		  else if(mode.equals(LOGO))
		  {
			 
		  }
	  }
	  catch(Exception e)
	  {
		  log.error("Error loading Object");
		  return null;
	  }
	  
	  if(imageName != null && !imageName.equals(""))
	  {
		  File imageFile = new File(this.getFilePath().concat(imageName));
		  log.error("imageFile  : "+imageFile);
		  if(!imageFile.exists())
		  {
			  log.error("The actual image file does not exist even if the database has reference.");
			  return null;
		  }
		  	  
		  byte[] buf = new byte[1024];
		  BufferedInputStream in 	= new BufferedInputStream(new FileInputStream(imageFile));
		  ServletOutputStream out = response.getOutputStream();

		  response.setContentType("application/octet-stream");
		  //response.setContentLength((int)new File(file_name).length());
		  response.addHeader("Content-Disposition", "inline; filename=\"" + imageName + "\"");

		  // copy data
		  int length;
		  while ((in != null) && ((length = in.read(buf)) != -1))
		  {
			  out.write(buf, 0, length);
		  }
	  }		
	  
	  return null;
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
	
	public String getMode() {
		if(mode == null || (!mode.equals(LOGO) && !mode.equals(PHOTO) && !mode.equals(SIGNATURE))) mode = null;
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
}