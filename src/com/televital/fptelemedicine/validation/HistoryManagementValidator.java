package com.televital.fptelemedicine.validation;

//remove this class if not required 

import java.lang.reflect.Method;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.televital.fptelemedicine.domain.ContactDetails;
import com.televital.fptelemedicine.domain.History;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Lab;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class HistoryManagementValidator implements Validator{
	
	private static final Logger log4log = Logger.getLogger(HistoryManagementValidator.class);
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
		 History history = (History) obj;
		   validatePastIllness(history.getpastIllness(), errors);
		  /* validateFamilyHistory(history.getFamilyHistory(), errors);
		   validateCurrentMedication(history.getCurrentMedication(), errors);
		   validateImmunization(history.getImmunization(), errors);
		   validateAllergies(history.getAllergies(),errors);
		   validateAttachments(history.getAttachments(),errors);*/
		    
		 }
	
	    public void validatePastIllness(String pastIllness, Errors errors) {
		  log4log.error("inside validate pastIllness: ");
		 // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "labId","LABID_REQUIRED");	
		
		  }
	    
	    public void validatePassword(String password, Errors errors) {
	    	  log4log.error("inside validate password");
	    	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login.password","PASSWORD_REQUIRED");
	    	  
	    	  if(!errors.hasFieldErrors("login.password") && password.length()< 4 )
	  		  {
	  			errors.rejectValue("login.password","PASSWORD.INVALIDLENGTH");
	  		  }
	    	 			 
			  }
	    public void validateConfirmPassword(String confirmPassword, Errors errors) {
	    	  log4log.error("inside confirm password:  "+confirmPassword);
	    	
	  		  String temparr[]=confirmPassword.split("yt%y");
	  		  String temppassword="";
	  		  String tempconfpassword="";
	  		  log4log.error("temparr.length: "+temparr.length);
	  		  
	    	  if(temparr.length > 1)
	    	  {
	    		  temppassword=temparr[0];
	    		  tempconfpassword=temparr[1];
	    	  }
	    	  log4log.error("temppassword : "+temppassword);
	    	  log4log.error("tempconfpassword : "+tempconfpassword);
	    	  
	      if(confirmPassword.equalsIgnoreCase("yt%y"))
	  		  {
	  			errors.rejectValue("login.confirmPassword","CONFIRMPASSWORD_REQUIRED");
	  	      }
	      if(tempconfpassword.trim().equals(""))
		  {
			errors.rejectValue("login.confirmPassword","CONFIRMPASSWORD_REQUIRED");
	      }
	  		if(!errors.hasFieldErrors("login.confirmPassword") && tempconfpassword.length()< 4)
			{
				errors.rejectValue("login.confirmPassword","CONFORMPASSWORD.INVALIDLENGTH");
			}
	  		
	  		/* Check if Password and Confirm Passwords match */
			if(!errors.hasFieldErrors("login.confirmPassword") && !temppassword.equals(tempconfpassword))
			{
				errors.rejectValue("login.confirmPassword","CONFIRMPASSWORD.MISMATCH");
			}
	    	 
	    	 			 
			  }
	    
	   public void validateLabName(String labName, Errors errors) {
	 	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "labName","LAB_NAME_REQUIRED");
		  if(!FPTelemedicineUtils.stringValidWithSpaceDot(labName))
			{
				errors.rejectValue("labName","LAB_NAME_INVALID");
			}
		  }
	 	  
	   public void validateContactNumber(String contactnumber, Errors errors) {
			  log4log.error("inside validate ContactNumber");
			  if(!FPTelemedicineUtils.validPostalCode(contactnumber))
				{
					errors.rejectValue("contactDetails.contactNumber","VALID_CONTACTNUMBER");
				}
			  }
		  
	   public void validateEmail(String email, Errors errors) {
			  log4log.error("inside validate Email");
			  if(!FPTelemedicineUtils.validEMailId(email))
				{
					errors.rejectValue("contactDetails.email","VALID_EMAIL");
				}
			  }
	   
	   public void validateContactPerson(String contactPerson, Errors errors) {
		   if(!contactPerson.trim().equals(""))
			{
			if(!FPTelemedicineUtils.stringValidWithSpaceDot(contactPerson))
			{
				errors.rejectValue("contactPerson","INVALID_CHAR");
			}
			}
	   }
	 
	 
	  
	  public String getInputFieldValidationMessage(String formInputId, String formInputValue) {

		  String validationMessage = "";
		  String capitalizedFormInputId="";
		  String capitalizedFormInputIdSub="";
		  String doctorMethodName="";
		  String contactDetMethod="";
		  
          log4log.error("formInputId: "+formInputId+" formInputValue: "+formInputValue);
		  try
		  {
		    Object formBackingObject = new Lab();
		    
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
		    		if(tempval.equalsIgnoreCase("ContactDetails"))
		    		{
		    			 Object cd = new ContactDetails();
		    			 //Errors errorsc = new BindException(cd, "command");
		    			 contactDetField = formInputIdList[i+1];
		    			 capitalizedFormInputId = StringUtils.capitalize(contactDetField);
		    			 contactDetMethod = "set" + capitalizedFormInputId;
		    			 log4log.error("contactDetMethod  "+contactDetMethod);
		    			 Class setterArgs[] = new Class[] { String.class };
		 			     Method accountMethod = cd.getClass().getMethod(contactDetMethod,setterArgs);
		 			     accountMethod.invoke(cd, new Object[] { formInputValue });
		 			     
		 			    capitalizedFormInputIdSub = StringUtils.capitalize(tempval);
		 			    doctorMethodName="set"+capitalizedFormInputIdSub;
		 			    log4log.error("doctorMethodName  "+doctorMethodName);
		 			    
		 			    Class  setterArgstemp[] = new Class[] { ContactDetails.class };
		 			    log4log.error("setterArgstemp[] :"+setterArgstemp);
					    Method docMethod = formBackingObject.getClass().getMethod(doctorMethodName,setterArgstemp);
					    docMethod.invoke(formBackingObject, new Object[] { cd });
					    log4log.error("after invoke ");
		 			     
		 			    String validationMethodName = "validate" + capitalizedFormInputId;
		 			    log4log.error("validationMethodName: "+validationMethodName);
					    Class validationArgs[] = new Class[] { String.class, Errors.class };
					    Method validationMethod = getClass().getMethod(validationMethodName, validationArgs);
					    validationMethod.invoke(this, new Object[] { formInputValue, errors });
					    log4log.error("after invoke");
					    validationMessage = getValidationMessage(errors, formInputId);
					    log4log.error("formInputId : "+formInputId);
					    log4log.error("validationMessage : "+validationMessage);
		    		}
		    		else if(tempval.equalsIgnoreCase("Login"))
		    		{
		    			Object lo = new Login();
		    			
		    			 contactDetField = formInputIdList[i+1];
		    			 capitalizedFormInputId = StringUtils.capitalize(contactDetField);
		    			 contactDetMethod = "set" + capitalizedFormInputId;
		    			 log4log.error("loginDetMethod  "+contactDetMethod);
		    			 Class setterArgs[] = new Class[] { String.class };
		 			     Method accountMethod = lo.getClass().getMethod(contactDetMethod,setterArgs);
		 			     accountMethod.invoke(lo, new Object[] { formInputValue });
		 			     
		 			    capitalizedFormInputIdSub = StringUtils.capitalize(tempval);
		 			    doctorMethodName="set"+capitalizedFormInputIdSub;
		 			    log4log.error("doctorMethodName  "+doctorMethodName);
		 			    
		 			    Class  setterArgstemp[] = new Class[] { Login.class };
		 			    log4log.error("setterArgstemp[] :"+setterArgstemp);
					    Method docMethod = formBackingObject.getClass().getMethod(doctorMethodName,setterArgstemp);
					    docMethod.invoke(formBackingObject, new Object[] { lo });
					    log4log.error("after invoke ");
		 			     
		 			    String validationMethodName = "validate" + capitalizedFormInputId;
		 			    log4log.error("validationMethodName: "+validationMethodName);
					    Class validationArgs[] = new Class[] { String.class, Errors.class };
					    Method validationMethod = getClass().getMethod(validationMethodName, validationArgs);
					    validationMethod.invoke(this, new Object[] { formInputValue, errors });
					    log4log.error("after invoke");
					    validationMessage = getValidationMessage(errors, formInputId);
					    log4log.error("formInputId : "+formInputId);
					    log4log.error("validationMessage : "+validationMessage);
		    		}
		    		
		    		capitalizedFormInputId = StringUtils.capitalize(tempval);
		    		String tempval1="set"+capitalizedFormInputId;
		    		if(i==0)
		    		{
		    			doctorMethodName=tempval1;
		    		}
		    		else
		    		{
		    		doctorMethodName=doctorMethodName+"()."+tempval1;
		    		}
		    		
		    	}
		    	
		    }
		    else
		    {
		    	capitalizedFormInputId = StringUtils.capitalize(formInputId);
		    	doctorMethodName = "set" + capitalizedFormInputId;
		    	   
			    //formInputId = formInputId.split("\\.")[1]; // Ignore the preceding "command." portion of the id
			
			    log4log.error("else part two");
			    // Invoke the set[formInputId] method on the Account instance
			    
			    log4log.error("doctorMethodName: "+doctorMethodName);
			    
			    Class setterArgs[] = new Class[] { String.class };
			    Method accountMethod = formBackingObject.getClass().getMethod(doctorMethodName,setterArgs);
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
