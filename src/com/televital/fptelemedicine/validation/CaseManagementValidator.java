package com.televital.fptelemedicine.validation;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.televital.fptelemedicine.domain.ContactDetails;
import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class CaseManagementValidator implements Validator{
	
	private static final Logger log4log = Logger.getLogger(CaseManagementValidator.class);
	MessageSource messageSource;
	private IFPTelemedicineFacade fpportalFacade;
	HttpSession sessiontemp;
	
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
		   FPCase fpCase = (FPCase) obj;
		   
		   validateWeight(fpCase.getWeight(), errors);
		   validateHeight(fpCase.getHeight(), errors);
		   validateTemperature(fpCase.getTemperature(), errors);
		   validateBloodPressure(fpCase.getBloodPressure(), errors);
		   validatePulseRate(fpCase.getPulseRate(),errors);
		   validateRespirationRate(fpCase.getRespirationRate(),errors);
		   validateLmp(fpCase.getLmp(),errors);
		   validateCaseTitle(fpCase.getCaseTitle(),errors);
		     
		 }
	
	    public void validateWeight(String weight, Errors errors) {
		  log4log.error("inside validate weight: ");
		  
		  if(!weight.trim().equals(""))
			{
			if(StringUtils.hasText(weight) && hasInvalidChars(weight))
			{
				errors.rejectValue("weight","Weight.Error");
			}
			else if(!(Integer.parseInt(weight)<=350 && Integer.parseInt(weight) >=1))
			{
				errors.rejectValue("weight","Weight.Error.Btw");
			}
			}
		 		
		  }
	    
	    public void validateHeight(String height, Errors errors) {
	    	  log4log.error("inside validate height");
	    	  	  
	    	
	    	  if(!height.trim().equals(""))
				{
				if(StringUtils.hasText(height) && hasInvalidChars(height))
				{
					errors.rejectValue("height","Height.Error");
				}else if(!(Integer.parseInt(height)<=240 && Integer.parseInt(height) >=20))
				{
					errors.rejectValue("height","Height.Error.Btw");
				}
				}			 
			  }
	    
	    public void validateTemperature(String temperature, Errors errors) {
	    	  log4log.error("inside validate Temperature");
	    	 
	    	   if(!temperature.trim().equals(""))
				{
				if(StringUtils.hasText(temperature) && hasInvalidChars(temperature))
				{
					errors.rejectValue("temperature","Temperature.Error");
				} else if(!(Integer.parseInt(temperature)<=105 && Integer.parseInt(temperature) >=90))
				{
					errors.rejectValue("temperature","Temperature.Error.Btw");
				}
				}	 
			  }
	    public void validateBloodPressure(String bloodPressure, Errors errors) {
	    	  log4log.error("inside validate BloodPressure");
	    	 
	    	  if(!bloodPressure.trim().equals(""))
				{
				if(StringUtils.hasText(bloodPressure))
				{
					if(bloodPressure.indexOf("/") == -1)
					{
						errors.rejectValue("bloodPressure","BP.Error");
					}
					else
					{
						StringTokenizer tokenizer = new StringTokenizer(bloodPressure,"/");
						if(tokenizer.countTokens() != 2)
						{
							errors.rejectValue("bloodPressure","BP.Error");
						}else			
						{
							String sys  =tokenizer.nextToken().trim();
							String dias =tokenizer.nextToken().trim();
						if(hasInvalidChars(sys) || hasInvalidChars(dias))
						{
							errors.rejectValue("bloodPressure","BP.Error.Num");
						}else if(!(Integer.parseInt(sys)<=250 && Integer.parseInt(sys)>=70 && Integer.parseInt(dias)<=180 && Integer.parseInt(dias)>=50))
						{
							errors.rejectValue("bloodPressure","BP.Error.Btw");
						}
					  }
					}			
				}
				}
			  }
	    
	    public void validatePulseRate(String pulseRate, Errors errors) {
	    	  log4log.error("inside validate PulseRate");
	    	 
	    	  if(!pulseRate.trim().equals(""))
				{
				if(StringUtils.hasText(pulseRate) && hasInvalidChars(pulseRate))
				{
					errors.rejectValue("pulseRate","PulseRate.Error");
				}else if(!(Integer.parseInt(pulseRate)<=140 && Integer.parseInt(pulseRate) >=30))
					{
						errors.rejectValue("pulseRate","PulseRate.Error.Btw");
					}
				}
			  }
	    
	    public void validateRespirationRate(String respirationRate, Errors errors) {
	    	  log4log.error("inside validate RespirationRate");
	    	
	    	  if(!respirationRate.trim().equals(""))
				{
				if(StringUtils.hasText(respirationRate) && hasInvalidChars(respirationRate))
				{
					errors.rejectValue("respirationRate","RR.Error");
				}else if(!(Integer.parseInt(respirationRate)<=70 && Integer.parseInt(respirationRate) >=10))
					{
						errors.rejectValue("respirationRate","RR.Error.Btw");
					}
				}
			  }
	    
	    public void validateLmp(String lmp, Errors errors) {
	    	  log4log.error("inside validate Lmp");
	    	 
	    	  if(StringUtils.hasText(lmp) && hasInvalidChars(lmp))
				{
					errors.rejectValue("lmp","LMP.Error");
				}	
			  }
	    
	    public void validateCaseTitle(String caseTitle, Errors errors) {
			  log4log.error("inside validate CaseTitle: ");
			  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caseTitle","CASETITLE_REQUIRED");	
			  
			  if(! FPTelemedicineUtils.checkUniqueCaseTitle(caseTitle, this.getFpportalFacade(),sessiontemp))
			  {
				  errors.rejectValue("caseTitle","CASETITLE_EXISTS");
			  }
			 		
			  }
	 
	  
	  public String getInputFieldValidationMessage(String formInputId, String formInputValue, HttpSession session) {

		  String validationMessage = "";
		  String capitalizedFormInputId="";
		  String capitalizedFormInputIdSub="";
		  String caseMethodNmae="";
		  String contactDetMethod="";
		  sessiontemp = session;
          log4log.error("formInputId: "+formInputId+" formInputValue: "+formInputValue +"session: "+session.getAttribute("PatientIdJ"));
		  try
		  {
		    Object formBackingObject = new FPCase();
		    
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
		    			caseMethodNmae=tempval1;
		    		}
		    		else
		    		{
		    		caseMethodNmae=caseMethodNmae+"()."+tempval1;
		    		}
		    		
		    	}
		    	
		    }
		    else
		    {
		    	capitalizedFormInputId = StringUtils.capitalize(formInputId);
		    	caseMethodNmae = "set" + capitalizedFormInputId;
		    	   
			    //formInputId = formInputId.split("\\.")[1]; // Ignore the preceding "command." portion of the id
			
			    log4log.error("else part two");
			    // Invoke the set[formInputId] method on the Account instance
			    
			    log4log.error("caseMethodNmae: "+caseMethodNmae);
			    
			    Class setterArgs[] = new Class[] { String.class };
			    Method accountMethod = formBackingObject.getClass().getMethod(caseMethodNmae,setterArgs);
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

	  public static boolean hasInvalidChars(String inputStr)
		{	
			for(int i=0;i<inputStr.length() ; i++)
			{
				char singleChar = inputStr.charAt(i);
				
				if((singleChar < '0' || singleChar > '9') && (singleChar != '.'))
				{
					return true;
				}
			}
			return false;
		}

}