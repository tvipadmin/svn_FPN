package com.televital.fptelemedicine.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class LogoutController extends AbstractController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public static final Logger log4log = Logger.getLogger(LogoutController.class);
	private IFPTelemedicineFacade fpportalFacade;
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String logoutView = "LogoutView";
		
		log4log.info("Retreving login object for the user from session");
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			log4log.error("Session is null.");
			return new ModelAndView(logoutView);
		}
		Login login = (Login)session.getAttribute(FPTelemedicineConstants.SESSION_VAR_LOGIN);
		if(login == null)
		{
			log4log.error("Cannot retrive login object from the session.");
			return new ModelAndView(logoutView);
		}
		//invaldiing the current session
		log4log.info("Invalidating the session");
		if(session != null)
		{
			session.invalidate();
		}
		//updating login status
		log4log.info("Updating logout status to database");
		if(!FPTelemedicineUtils.updateLoginStatus(login,FPTelemedicineConstants.LOGIN_STATUS_OUT,fpportalFacade))
		{
			log4log.error(" Cannot update logout status to the database.");
			return new ModelAndView("GeneralError");
		}
		return new ModelAndView(logoutView);
	}
	public IFPTelemedicineFacade getFpportalFacade() {
		return fpportalFacade;
	}
	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade) {
		this.fpportalFacade = fpportalFacade;
	}
}
