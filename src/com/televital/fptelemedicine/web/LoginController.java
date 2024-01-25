package com.televital.fptelemedicine.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.LoginForm;
import com.televital.fptelemedicine.license.EncryptionException;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.license.ServerLicense;
import com.televital.fptelemedicine.utils.AccessRights;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class LoginController extends  AbstractFPTelemedicineController{

	private ILicenseCodec licenseCodec;
	//setter method for licensecodec
	public void setLicenseCodec(ILicenseCodec licenseCodec)
	{
		this.licenseCodec = licenseCodec;
	}
	
private static final Logger log4log = Logger.getLogger(AbstractFPTelemedicineController.class);

public LoginController()
{
	setCommandClass(LoginForm.class);
	setSessionForm(true); 
}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command,BindException errors) throws ServletException {
		
		log4log.error("Inside LoginController");
			
		
		LoginForm loginForm = (LoginForm)command;
		try
		{
			
			HashMap activeUsersCheck1 = (HashMap)getServletContext().getAttribute("activeUsers"); // sessions values
			Boolean falg =true;
			log4log.error("activeUsersCheck1.size:  "+activeUsersCheck1.size());
		     for(Iterator i = activeUsersCheck1.keySet().iterator(); i.hasNext(); )
		     {
		    	 String sessionId = (String)i.next();
	        	 log4log.error("Session Id : "+sessionId);
	        	 HttpSession sess = (HttpSession)activeUsersCheck1.get(sessionId);
	             Login logincheck = (Login)sess.getAttribute(FPTelemedicineConstants.SESSION_VAR_LOGIN);
	             log4log.error("Login Objjjjjjjjjj : "+logincheck);
	             if(logincheck != null )
	             {
	            	 log4log.error("logincheck.getLoginId() : "+ logincheck.getLoginId());
	            	 falg = false;
	            	 //return new ModelAndView("OldUserExists");
	             }
	             else 
	             {
	            	falg = true;
	             }
	             log4log.error("Login Obj : "+logincheck);
		     }
		// End Of Check Already User Is Logged In.
		     
			Login login =null;
			if(getFpportalFacade() == null)
			{
				log4log.error("fptelemedicineFacade object is null or not set.");
				return new ModelAndView("GeneralError");
			}
			List loginList = getFpportalFacade().loadLoginForm(loginForm.getLoginId());
			
			log4log.error("loginlist");
			if(loginList.size() <= 0)
			{
				//redirecting user not found view.
				log4log.error("Login object for the user specified id is not found.");
				return new ModelAndView("UserNone");		
			}
			else if(loginList.size() >0)
			{
				log4log.error("Login object for the user id found.");
				login = (Login)loginList.get(0);
			}
			//encrypting user input password to verify with database password
			log4log.error("Encrypting user password for comparing.");
			String encryptedPassword = "";
			//encrypting user input password
			try
			{
				log4log.error(" licensecodec value : "+licenseCodec);
				//encrypting the user password for validating.
				encryptedPassword = licenseCodec.encrypt(loginForm.getPassword());
				log4log.error("encrypted password : "+encryptedPassword);
			}
			catch(EncryptionException e)
			{
				//logging exception
				log4log.error("Couldn't encrypt user password : "+e.toString());
				//redirecting wrong password view.
				return new ModelAndView("WrongPassword");
			}
			catch(Exception e)
			{
				//logging exception
				log4log.error("Couldn't encrypt user password : "+e.toString());
				//redirecting wrong password view.
				return new ModelAndView("WrongPassword");
			}
			
			//verifying user password with database password
			log4log.error("comparing user pasword with database stored password.");
			
			if(login.getPassword().equals(encryptedPassword))
			{
				//verify wheather the user account it a valid or not on the system.
				log4log.error(login.getAccountStatus());
				log4log.error(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED);
				log4log.error(login.getCurrentLoginStatus());
				log4log.error(FPTelemedicineConstants.LOGIN_STATUS_IN);
				if(login.getAccountStatus().equals(FPTelemedicineConstants.ACCOUNT_STATUS_ENABLED))
				{
					if(login.getCurrentLoginStatus().equals(FPTelemedicineConstants.LOGIN_STATUS_IN))
					{
						HashMap activeUsersCheck = (HashMap)getServletContext().getAttribute("activeUsers");
					     for(Iterator i = activeUsersCheck.keySet().iterator(); i.hasNext(); )
					     {
						     String sessionId = (String)i.next();
				        	 log4log.error("Session Id : "+sessionId);
				             HttpSession sess = (HttpSession)activeUsersCheck.get(sessionId);
				             Login logincheck = (Login)sess.getAttribute(FPTelemedicineConstants.SESSION_VAR_LOGIN);
				             log4log.error("Login Obj : "+logincheck);
				             if(null == logincheck)continue;
				             String enteredUserId=loginForm.getLoginId();
				             log4log.error("Current Entered UserId : "+enteredUserId);
				             String activeUserId =logincheck.getLoginId();
				             log4log.error("Active User : "+activeUserId);
				             				             
				             if(enteredUserId.equalsIgnoreCase(activeUserId))
				             {
				            	 log4log.error("User has been logged in,User has to wait");
					        	 return new ModelAndView("UserLoginWait");
				             }
				         }
					}
					
					/*
					 * updating  login object for the user
					 */
					HttpSession session = request.getSession(true);
					session.setAttribute(FPTelemedicineConstants.SESSION_VAR_LOGIN,login);
					//setting sessionbing object to session
					ResetLogin resetLogin = new ResetLogin(getServletContext());
					session.setAttribute(FPTelemedicineConstants.SESSION_VAR_RESET_LOGIN,resetLogin);
					if(login.getAccountType().equals(AccessRights.USER_ADMIN))
					{
						log4log.error("Found user as admin.");
						
						/*
						 * incrementing the concurrent count on the context
						 * once again checking the user count before redirecting to correcponding view for second verification.
						 */
					/*	switch(incrementConcurrentCount())
						{
							case 1:
								log4log.error("Concurrent connection reached the limit");
								return new ModelAndView("ReachedLimit");
							case 2:
								log4log.error("A general error occur while incrementing the concurrent user connection");
								return new ModelAndView("GeneralError");
						}*/
						/*
						 * updating the database for the logged in status and lastlogintime 
						 */
						if(!FPTelemedicineUtils.updateLoginStatus(login,FPTelemedicineConstants.LOGIN_STATUS_IN,this.getFpportalFacade()))
						{
							log4log.error("General error occur while updating login status.");
							return new ModelAndView("GeneralError");
						}

						 /**********************************/
						//redirecting to admin view.
						log4log.error("admin logged in");
						return new ModelAndView("AdminLoggedIn");
					}
					else
					{
						   log4log.error("not a admin");
						   switch(getAccountType(login))
							{
							case 0:
								//redirecting invalid account type view.
								return new ModelAndView("InvalidAccountType");
							case 1:
								   List docList = this.getFpportalFacade().loadDoctorById(login.getLoginId());
								   if(docList.size()>0)
								   {
									   Doctor doc = (Doctor)docList.get(0);
									   if(doc.getType().equalsIgnoreCase("FAMILYPHYSICIAN"))
									   {
										   if(!FPTelemedicineUtils.updateLoginStatus(login,FPTelemedicineConstants.LOGIN_STATUS_IN,this.getFpportalFacade()))
											{
												log4log.error("General error occur while updating login status.");
												return new ModelAndView("GeneralError");
											}
										   return new ModelAndView("FamilyPhysicianLoggedIn");
									   }
									   else if(doc.getType().equalsIgnoreCase("SPECALIST"))
									   {
										   if(!FPTelemedicineUtils.updateLoginStatus(login,FPTelemedicineConstants.LOGIN_STATUS_IN,this.getFpportalFacade()))
											{
												log4log.error("General error occur while updating login status.");
												return new ModelAndView("GeneralError");
											}
										   return new ModelAndView("SpecalistLoggedIn");
									   }
									   else if(doc.getType().equalsIgnoreCase("BOTH"))
									   {
										   if(!FPTelemedicineUtils.updateLoginStatus(login,FPTelemedicineConstants.LOGIN_STATUS_IN,this.getFpportalFacade()))
											{
												log4log.error("General error occur while updating login status.");
												return new ModelAndView("GeneralError");
											}
										   return new ModelAndView("BothLoggedIn");
									   }
									  
								   }
								  
								   return new ModelAndView("InvalidAccountType");
							case 2:
								if(!FPTelemedicineUtils.updateLoginStatus(login,FPTelemedicineConstants.LOGIN_STATUS_IN,this.getFpportalFacade()))
								{
									log4log.error("General error occur while updating login status.");
									return new ModelAndView("GeneralError");
								}
								return new ModelAndView("LabLoggedIn");
							
							}			
			 				return new ModelAndView("InvalidAccountType");
					
					}
				}
				else
				{
					//redirecting not active user view.
					log4log.error("notactive");
					return new ModelAndView("NotActive");					
				}
			}
			else
			{
				log4log.error("wrong password");
				//redirecting wrong password view.
				return new ModelAndView("WrongPassword");
			}
		}//catching not found exception for the user name
		catch(ObjectRetrievalFailureException o)
		{
			//logging the error message
			log4log.error(" User account not found on the system. :"+o.toString());
			//redirecting user not found view.
			return new ModelAndView("UserNone");		
		}
	}
	
	
	/*
	 * Function to check the user count reach the limit and increment the count on the context
	 * returns 1 : if the count reach the limit
	 * returns 2 : if any error while processing the license
	 * return 0 : on successful operation.
	 */
	private int incrementConcurrentCount()
	{
		log4log.error("incrementing operation started.");
		ServerLicense serverLicense;
		//reading the license from the context
		synchronized(FPTelemedicineUtils.getLock())
		{
			serverLicense = (ServerLicense)getServletContext().getAttribute(FPTelemedicineConstants.CONTEXT_VAR_SERVER_LICENSE);
		}
		log4log.error("in one");
		log4log.error("serverLicense.getMaxConcurrentUsers(): "+serverLicense.getMaxConcurrentUsers());
		try
		{
			int maxCount = new Integer(serverLicense.getMaxConcurrentUsers()).intValue();
			log4log.error("in two");
			int cCount = new Integer(serverLicense.getConcurrentUsers()).intValue();
			log4log.error("in three");
			if(cCount >= maxCount)
			{
				log4log.error("concurrent users reach limit.");
				return 1;
			}
			else
			{
				//incrementing the user count
				log4log.error("incrementing the user count.");
				cCount++;
				log4log.error("ccount : "+cCount);
				//updating the server license
				log4log.error("Updating the server license to context.");
				serverLicense.setConcurrentUsers(""+cCount);
				synchronized(FPTelemedicineUtils.getLock())
				{
					getServletContext().setAttribute(FPTelemedicineConstants.CONTEXT_VAR_SERVER_LICENSE,serverLicense);
				}	
				log4log.error("in four");
			}
		}
		catch(Exception e)
		{
			log4log.error("Error while incrementing the count due to : "+e.toString());
			return 2;
		}
		return 0;
	}
	
	
	/*
	 * function to compute the usertype in to integer for faster porcess
	 */
	private int getAccountType(Login login)
	{
		if(login.getAccountType().equals(AccessRights.USER_DOCTOR))
		{
			return 1;
		}
		else if(login.getAccountType().equals(AccessRights.USER_LAB))
		{
			return 2;
		}
		else
		{
			return 0;
		}
	}
		
	
}

