package com.televital.fptelemedicine.validation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.context.MessageSource; 
import org.springframework.context.support.StaticMessageSource;

import com.televital.fptelemedicine.domain.ContactDetails;

import com.televital.fptelemedicine.domain.Entity;
import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.LabServices;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;


public class  LabServiceManagementValidator  implements Validator{
	
	private static final Logger log4log = Logger.getLogger( LabServiceManagementValidator .class);
	MessageSource messageSource;
	private IFPTelemedicineFacade fpportalFacade;
	
	public void setMessageSource(MessageSource messageSource) {    this.messageSource=messageSource; }
	  public MessageSource getMessageSource( ) {  return this.messageSource;	  }
	  
	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return Lab.class.isAssignableFrom(arg0);
	}
	
	public IFPTelemedicineFacade getFpportalFacade() {
		return fpportalFacade;
	}	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade) {
		this.fpportalFacade = fpportalFacade;
	}
	
	public void validate(Object obj, Errors errors) {
		 log4log.error("inside validate method");
		   LabServices labServices = (LabServices) obj;
		   validateServiceName(labServices.getServiceName(), errors);
		  
		 }
	
	    public void validateServiceName(String serviceName, Errors errors) {
		  log4log.error("inside validate labId: "+serviceName);
		  String tempVal = serviceName;
		  String tempArr[] =serviceName.split("#####");
		  serviceName = tempArr[0];
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serviceName","LABSERVICE_NAME_REQUIRED");
		   if(serviceName.equals(""))
		   {
			  errors.rejectValue("serviceName","LABSERVICE_NAME_REQUIRED");
		   }
		  // if(!FPTelemedicineUtils.stringValidWithSpace(serviceName))
		//	{
		//		errors.rejectValue("serviceName","LABSERVICE_NAME_INVALID");
		//	}
		  if(! FPTelemedicineUtils.checkUniqueLabServiceName(tempVal, this.getFpportalFacade()))
		  {
			  errors.rejectValue("serviceName","SERVICENAME_EXISTS");
		  }
		 		
		  }
	   
	    public String getInputFieldValidationMessage(String formInputId, String formInputValue) {

			  String validationMessage = "";
			  String capitalizedFormInputId="";
			  String capitalizedFormInputIdSub="";
			  String labServiceMethodName="";
			 
			  
	          log4log.error("formInputId: "+formInputId+" formInputValue: "+formInputValue);
			  try
			  {
			    Object formBackingObject = new LabServices();
			    
			    Errors errors = new BindException(formBackingObject, "command");
			    log4log.error("one");
			    String formInputIdList[]=formInputId.split("\\.");
			    
			    if(formInputIdList.length > 1)
			    {
			    	log4log.error("inside if"+ formInputIdList.length);
			    	for(int i=0;i<formInputIdList.length;i++)
			    	{
			    		String tempval =formInputIdList[i];
			    		String contactDetField="";
			    				    		
			    		capitalizedFormInputId = StringUtils.capitalize(tempval);
			    		String tempval1="set"+capitalizedFormInputId;
			    		if(i==0)
			    		{
			    			labServiceMethodName=tempval1;
			    		}
			    		else
			    		{
			    			labServiceMethodName=labServiceMethodName+"()."+tempval1;
			    		}
			    		
			    	}
			    	
			    }
			    else
			    {
			    	capitalizedFormInputId = StringUtils.capitalize(formInputId);
			    	labServiceMethodName = "set" + capitalizedFormInputId;
			    	   
				    //formInputId = formInputId.split("\\.")[1]; // Ignore the preceding "command." portion of the id
				
				    log4log.error("else part two");
				    // Invoke the set[formInputId] method on the Account instance
				    
				    log4log.error("caseMethodNmae: "+labServiceMethodName);
				    
				    Class setterArgs[] = new Class[] { String.class };
				    Method accountMethod = formBackingObject.getClass().getMethod(labServiceMethodName,setterArgs);
				    accountMethod.invoke(formBackingObject, new Object[] { formInputValue });
				    log4log.error("three");
				    // Invoke the validate[formInputId] method of the AccountValidator instance
				    String validationMethodName = "validate" + capitalizedFormInputId;
				    Class validationArgs[] = new Class[] { String.class, Errors.class };
				    Method validationMethod = getClass().getMethod(validationMethodName, validationArgs);
				    validationMethod.invoke(this, new Object[] { formInputValue, errors });
				    log4log.error("formInputId : "+formInputId);
				    validationMessage = getValidationMessage(errors, formInputId);
			    }
			    
			 
			  }
			  catch (Exception e)
			  {
			    // Handle appropriately for your application
			    System.out.println("New code exception: " + e);
			  }

			  return validationMessage;
			 }
	  
	  protected String getValidationMessage(Errors errors, String fieldName)
	  {
	   String message = "noerror";
       log4log.error("errors: "+errors);
	   FieldError fieldError = errors.getFieldError(fieldName);

	   if (fieldError != null)
	   {
		log4log.error("fieldError.getCode(): "+fieldError.getCode());   
	    message =  messageSource.getMessage(fieldError.getCode(), null,"This field is invalid", Locale.ENGLISH);
	    
	   }

	   return message;
	  }




}