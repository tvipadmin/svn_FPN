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
import com.televital.fptelemedicine.domain.ContactDetails;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;


public class FamilyPhyManagementValidator implements Validator{
	
	private static final Logger log4log = Logger.getLogger(FamilyPhyManagementValidator.class);
	MessageSource messageSource;
	private IFPTelemedicineFacade fpportalFacade;
	
	public void setMessageSource(MessageSource messageSource) {    this.messageSource=messageSource; }
	  public MessageSource getMessageSource( ) {  return this.messageSource;	  }
	  
	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return Patient.class.isAssignableFrom(arg0);
	}
	
	public IFPTelemedicineFacade getFpportalFacade() {
		return fpportalFacade;
	}	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade) {
		this.fpportalFacade = fpportalFacade;
	}
	
	public void validate(Object obj, Errors errors) {
		 log4log.error("inside validate method");
		   Patient patient = (Patient) obj;
		   validatePatientId(patient.getPatientId(), errors);
		   validateFirstName(patient.getFirstName(), errors);
		   validateLastName(patient.getLastName(), errors);
		   validateGender(patient.getGender(), errors);
		   validateAgeYears(patient.getAgeYears(), errors);
		   validateAgeMonths(patient.getAgeMonths(), errors);
		   validateOccupation(patient.getOccupation(), errors);
		   validateContactNumber(patient.getContactDetails().getContactNumber(),errors);
		   validateEmail(patient.getContactDetails().getEmail(),errors);
		 
		 }
	
	    public void validatePatientId(String patientId, Errors errors) {
		  log4log.error("inside validate patientId: ");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patientId","PATIENTID_REQUIRED");	
		  if(!errors.hasFieldErrors("patientId") && ! FPTelemedicineUtils.validUserId(patientId))
			{
				errors.rejectValue("patientId","INVALID_ID");
			}
		  if(! FPTelemedicineUtils.checkUniquePatient(patientId, this.getFpportalFacade()))
		  {
			  errors.rejectValue("patientId","ID_EXISTS");
		  }
		 		
		  }
	    
	 /*   public void validatePassword(String password, Errors errors) {
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
	  		
	  	
			if(!errors.hasFieldErrors("login.confirmPassword") && !temppassword.equals(tempconfpassword))
			{
				errors.rejectValue("login.confirmPassword","CONFIRMPASSWORD.MISMATCH");
			}
	    	 
	    	 			 
			  }*/
			  
	   public void validateFirstName(String firstName, Errors errors) {
	 	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","FIRST_NAME_REQUIRED");
		  if(!FPTelemedicineUtils.stringValidWithSpaceDot(firstName))
			{
				errors.rejectValue("firstName","FIRST_NAME_INVALID");
			}
		  }
	 
	   public void validateLastName(String lastName, Errors errors) {
		   
		   if(!lastName.trim().equals(""))
			{
			if(!FPTelemedicineUtils.stringValidWithSpaceDot(lastName))
			{
				errors.rejectValue("lastName","LAST_NAME_INVALID");
			}
			}
		   
	   }
	   public void validateGender(String gender, Errors errors) {
		 	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender","GENDER_REQUIRED");
	       }
	   public void validateContactNumber(String contactnumber, Errors errors) {
			  log4log.error("inside validate Email");
			  if(!FPTelemedicineUtils.validPostalCode(contactnumber))
				{
					errors.rejectValue("contactDetails.contactNumber","VALID_CONTACTNUMBER");
				}
			  }
		  
	   public void validateAgeYears(String ageYears, Errors errors) {
			  log4log.error("inside validate ageYears: ");
			  if(!errors.hasFieldErrors("ageYears") && !ageYears.trim().equals(""))
				{log4log.debug("age years : "+ageYears);
					if(!FPTelemedicineUtils.isNumber(ageYears))
					{
						log4log.error("Age in year field is not a number : ");
						errors.rejectValue("ageYears","patient.Age.Year.InValid");
					}
					if(FPTelemedicineUtils.isNumber(ageYears))
					{
						log4log.error("Is Number");
						log4log.error((ageYears));
						log4log.error(Integer.parseInt(ageYears) <= 120 && Integer.parseInt(ageYears) >= 0);
						
					if(!(Integer.parseInt(ageYears) <= 120 && Integer.parseInt(ageYears) >= 0))
					{
						errors.rejectValue("ageYears","person.ValidAge");
					}
					}
				}	
			 
			  }
	   public void validateAgeMonths(String ageMonths, Errors errors) {
			  log4log.error("inside validate ageMonths: "+ageMonths);
			
			  if(!errors.hasFieldErrors("ageMonths") && !ageMonths.trim().equals(""))
				{
					try
					{
						if((Integer.parseInt(ageMonths)) >= 12)
						{
							errors.rejectValue("ageMonths","patient.Age.MorethanLimit");
						}
					}
					catch(Exception e)
					{
						log4log.error("Process error for age in months due to : "+e.toString());
						errors.rejectValue("ageMonths","patient.Age.Months.InValid");
					}
				}
				
			  }
	   
	   public void validateOccupation(String occupation, Errors errors) {
		  /* log4log.error("inside validate occupation");
		   if(!occupation.trim().equals(""))
			{
			if(!FPTelemedicineUtils.stringValidWithSpace(occupation))
			{
				errors.rejectValue("occupation","INVALID_CHAR");
			}
			}*/
	   }
	   
	   public void validateEmail(String email, Errors errors) {
			  log4log.error("inside validate Email"+ errors.getObjectName());
			  if(!FPTelemedicineUtils.validEMailId(email))
				{
				  log4log.error("inside email if");
					errors.rejectValue("contactDetails.email","VALID_EMAIL");
					
				}
			  }
	  
	 
	  
	  public String getInputFieldValidationMessage(String formInputId, String formInputValue) {

		  String validationMessage = "";
		  String capitalizedFormInputId="";
		  String capitalizedFormInputIdSub="";
		  String patientMethodName="";
		  String contactDetMethod="";
          log4log.error("formInputId: "+formInputId+" formInputValue: "+formInputValue);
		  try
		  {
		    Object formBackingObject = new Patient();
		    
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
		 			    patientMethodName="set"+capitalizedFormInputIdSub;
		 			    log4log.error("patientMethodName  "+patientMethodName);
		 			    
		 			    Class  setterArgstemp[] = new Class[] { ContactDetails.class };
		 			    log4log.error("setterArgstemp[] :"+setterArgstemp);
					    Method docMethod = formBackingObject.getClass().getMethod(patientMethodName,setterArgstemp);
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
		 			    patientMethodName="set"+capitalizedFormInputIdSub;
		 			    log4log.error("patientMethodName  "+patientMethodName);
		 			    
		 			    Class  setterArgstemp[] = new Class[] { Login.class };
		 			    log4log.error("setterArgstemp[] :"+setterArgstemp);
					    Method docMethod = formBackingObject.getClass().getMethod(patientMethodName,setterArgstemp);
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
		    			patientMethodName=tempval1;
		    		}
		    		else
		    		{
		    		patientMethodName=patientMethodName+"()."+tempval1;
		    		}
		    		
		    	}
		    	
		    }
		    else
		    {
		    	capitalizedFormInputId = StringUtils.capitalize(formInputId);
		    	patientMethodName = "set" + capitalizedFormInputId;
		    	   
			    //formInputId = formInputId.split("\\.")[1]; // Ignore the preceding "command." portion of the id
			
			    log4log.error("else part two");
			    // Invoke the set[formInputId] method on the Account instance
			    
			    log4log.error("patientMethodName: "+patientMethodName);
			    
			    Class setterArgs[] = new Class[] { String.class };
			    Method accountMethod = formBackingObject.getClass().getMethod(patientMethodName,setterArgs);
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