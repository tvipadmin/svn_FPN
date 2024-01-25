package com.televital.fptelemedicine.web;

//import java.net.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.form.ChangePasswordBackingObject;
import com.televital.fptelemedicine.license.ILicenseCodec;
import com.televital.fptelemedicine.license.ILicenseCodecImpl;
import com.televital.fptelemedicine.utils.AccessRights;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;


public class ChangePasswordController extends AbstractFPTelemedicineController implements InitializingBean
{
	private final Logger log4log = Logger.getLogger(ChangePasswordController.class);
	
	public ChangePasswordController()
	{
		this.setSessionForm(false);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception
	{
		ChangePasswordBackingObject cObj = new ChangePasswordBackingObject();
		try
		{
		log4log.error("this is backing obj");
		/* Check if the user is authorized to access this controller */
		if(!FPTelemedicineUtils.isAuthorizedUser(AccessRights.getChangePasswordControllerUsers(),((Login) WebUtils.getSessionAttribute(request,FPTelemedicineConstants.SESSION_VAR_LOGIN)).getAccountType()))
				{
					log4log.error("inside if");
					throw new ModelAndViewDefiningException(new ModelAndView("UnauthorizedAccess"));
					
				}
		log4log.error("this is next");
		//cObj.setErrormessage("notupdated");
		return cObj;
		}
		
		catch(Exception e)
		{
			log4log.error("the exception"+e);
			return false;
		}
		
		
	}
	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response,Object command, BindException errors) throws Exception
	{
		ChangePasswordBackingObject cObj = (ChangePasswordBackingObject)command;
		
		ILicenseCodec codec = new ILicenseCodecImpl();
		log4log.error("first message");
		
		Login login = (Login)WebUtils.getSessionAttribute(request,FPTelemedicineConstants.SESSION_VAR_LOGIN);
		log4log.error("second message");
		
		if(!codec.decrypt(login.getPassword()).equals(cObj.getCurrentPassword()))
		{
			log4log.debug("Invalid password ");
			errors.rejectValue("currentPassword","login.InvalidPassword");
			return showForm(request, response, errors);
		}
		
		String pw = codec.decrypt(login.getPassword());
		log4log.error("password pw : "+pw);
		login.setPassword(codec.encrypt(cObj.getNewPassword()));
		String cpw = cObj.getCurrentPassword();
		
		
		
		try
		{
		if(request.getParameter("actiontype").equals("update"))
		{
			String pw1 = codec.decrypt(login.getPassword());
			
			log4log.error("password here pw1 : "+pw1);
			log4log.error("current password cpw : "+cpw);
			
			log4log.error("cpw and pw are same then go to else otherwise go to the following if");
			
			if(!pw1.equals(pw))
			{
			
			this.getFpportalFacade().updateLogin(login);
			
			log4log.error("command value : "+command);
			cObj.setErrormessage("updatedd");
			log4log.error("errormessage value here : "+cObj.getErrormessage());
			return new ModelAndView("ChangePasswordView","command",cObj);
			}
			
			else
			{
				log4log.error("this is else of cpw");
				log4log.error("Invalid password ");
				errors.rejectValue("currentPassword","login.CurrentPassword.Required");
				cObj.setErrormessage("notupdated");
				log4log.error("errormessage value here : "+cObj.getErrormessage());
				cObj.getErrormessage();
				//return showForm(request, response, errors);
				return new ModelAndView("ChangePasswordView","command",cObj);
			}
			//}
		
			/*else
			{
				log4log.error("this is if");
				log4log.debug("Invalid password ");
				errors.rejectValue("currentPassword","login.InvalidPassword");
				cObj.setErrormessage("notupdated");
				log4log.error("errormessage value here : "+cObj.getErrormessage());
				cObj.getErrormessage();
				//return showForm(request, response, errors);
				return new ModelAndView("ChangePasswordView","command",cObj);
			}*/
		}
		}
		catch(Exception e)
		{
			log4log.error("Error while updating user password to the database due to : "+e.toString());
			return new ModelAndView("GeneralError");
		}
		String pw2 = codec.decrypt(login.getPassword());
		log4log.error("password here now pw2 : "+pw2);
		if(pw2.equals(cpw))
		{
			log4log.error("this is if");
			log4log.debug("Invalid password ");
			errors.rejectValue("currentPassword","login.InvalidPassword");
			cObj.setErrormessage("notupdated");
			log4log.error("errormessage value here : "+cObj.getErrormessage());
			cObj.getErrormessage();
			//return showForm(request, response, errors);
			return new ModelAndView("ChangePasswordView","command",cObj);
		}
		
		log4log.error("before model and view");
		//return new ModelAndView(this.getSuccessView());
		return null;
		
	}
	
}