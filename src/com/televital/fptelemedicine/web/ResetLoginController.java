package com.televital.fptelemedicine.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.form.AdminResetLogin;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;


public class ResetLoginController extends  AbstractFPTelemedicineController {
	
	private static final Logger log = Logger.getLogger(ResetLoginController.class);
	public ResetLoginController()
	{		
	}
	protected Object formBackingObject(HttpServletRequest request) throws ServletException 
	{
		
		AdminResetLogin  command = new AdminResetLogin();
		return command;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response,Object command,BindException errors)throws Exception 
	{
		
		String hospitalId = null;
		
		try
		{
			AdminResetLogin BO = (AdminResetLogin) command;
			BO.setLoginUpdate("notupdated");
			
			
				Login adminlogin = (Login) WebUtils.getSessionAttribute(request, FPTelemedicineConstants.SESSION_VAR_LOGIN);
				// Get data from database
				
				String userId = request.getParameter("Id");	
				String flag="n";
				if(userId != null && !userId.equals(""))
				{
					try
					{
						List loginlist = this.getFpportalFacade().loadLoginForm(userId);
						HashMap activeUsersCheck = (HashMap)getServletContext().getAttribute("activeUsers");
						log.error("active users size: "+activeUsersCheck.size());
						if(loginlist.size()>0)
						{
							 for(Iterator i = activeUsersCheck.keySet().iterator(); i.hasNext(); )
						     {
								 String sessionId = (String)i.next();
								 log.error("Session Id : "+sessionId);
								 HttpSession sess = (HttpSession)activeUsersCheck.get(sessionId);
					             Login logincheck = (Login)sess.getAttribute(FPTelemedicineConstants.SESSION_VAR_LOGIN);
					             if(null == logincheck)continue;
					             String activeUserId =logincheck.getLoginId();
					             log.error("activeUserId: "+activeUserId);
					             if(userId.equalsIgnoreCase(activeUserId))
					             {
					            	    flag ="y";
					            	    Login loginob = (Login)loginlist.get(0);	
										Login login = this.getFpportalFacade().loadLogin(loginob.getLoginId());
										login.setCurrentLoginStatus("OUT");
										this.getFpportalFacade().updateLogin(login);
										BO.setLoginUpdate("updated");
					            	    sess.invalidate();
					            	    log.error("session has been invalidated");
					            	    break;
					             }
						     }
						if(flag.equals("n"))
						{
						Login loginob = (Login)loginlist.get(0);	
						Login login = this.getFpportalFacade().loadLogin(loginob.getLoginId());
						login.setCurrentLoginStatus("OUT");
						this.getFpportalFacade().updateLogin(login);
						BO.setLoginUpdate("updated");
						}
						}
						return new ModelAndView(getFormView(),"command",BO);
					}
					catch(Exception e)
					{
						log.error("Error while resetting the password. "+e.toString());
						return new ModelAndView("DataAccessException","exception",e);
					}
				}				
			
			
			return new ModelAndView(getFormView(),"command",BO);
		}
		catch(Exception e)
		{
			log.error("Error:"+e.toString());
			return new ModelAndView("DataAccessException","exception",e);
		}
	}
	
	protected ModelAndView handleInvalidSubmit( HttpServletRequest request, 
												HttpServletResponse response)
												throws Exception 
	{
		return disallowDuplicateFormSubmission(request, response);
	}

	


}
