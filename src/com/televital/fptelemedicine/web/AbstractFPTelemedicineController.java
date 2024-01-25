package com.televital.fptelemedicine.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;



public class AbstractFPTelemedicineController extends SimpleFormController {
	
	private static final Logger log4log = Logger.getLogger(AbstractFPTelemedicineController.class);
	private IFPTelemedicineFacade fpportalFacade;
	
	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade)
	{
		this.fpportalFacade = fpportalFacade;
	}
	
	public IFPTelemedicineFacade getFpportalFacade()
	{
		return this.fpportalFacade;
	}
	
	protected ModelAndView disallowDuplicateFormSubmission(HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		BindException errors = getErrorsForNewForm(request);
		errors.reject("duplicateFormSubmission", "Duplicate form submission");
		return showForm(request, response, errors);
	}
	
	public void afterPropertiesSet() {
		if (this.fpportalFacade == null) {
			log4log.error("fPtelemedicineFacade not set");
			throw new IllegalArgumentException("object 'fPtelemedicineFacade' is required");
		}
	}
		
	
	}


