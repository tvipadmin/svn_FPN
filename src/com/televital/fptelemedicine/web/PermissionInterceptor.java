package com.televital.fptelemedicine.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.utils.FPTelemedicineConstants;

public class PermissionInterceptor extends HandlerInterceptorAdapter 
{
	private static final Logger log = Logger.getLogger(PermissionInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception 
	{
		Login login = (Login) WebUtils.getSessionAttribute(request,FPTelemedicineConstants.SESSION_VAR_LOGIN);
		if (login == null)
		{
			log.debug("Session is not active.");
			ModelAndView modelAndView = new ModelAndView("SessionExpired");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		else 
		{
			return true;
		}
	}
}
