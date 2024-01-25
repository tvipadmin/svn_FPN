package com.televital.fptelemedicine.validation;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.televital.fptelemedicine.domain.form.ChangePasswordBackingObject;
import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class ChangePasswordValidator implements Validator

{
	private static final Logger log4log = Logger.getLogger(ChangePasswordValidator.class);
	
	public boolean supports(Class arg0) 
	{
		// TODO Auto-generated method stub
		return ChangePasswordBackingObject.class.isAssignableFrom(arg0);
	}
	
	public void validate(Object command,Errors errors)
	{
		ChangePasswordBackingObject cObj = (ChangePasswordBackingObject)command;
	
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "login.CurrentPassword.Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"newPassword","login.NewPassword.Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","login.ConfirmPassword.Required");
		
		if(!cObj.getCurrentPassword().equals("") && (cObj.getCurrentPassword().equals(cObj.getNewPassword())) && !cObj.getConfirmPassword().equals(""))
		{
			log4log.debug("Current and New password are same ");
			errors.rejectValue("newPassword","login.SameCurrentAndNewPassword");
		}
		
		if(!cObj.getNewPassword().equals("") && (cObj.getNewPassword().length() < 4))
		{
			log4log.debug("New Password length is lessthen 4 ");
			errors.rejectValue("newPassword","login.Password.InvalidLength");
		}
		
		if(!cObj.getConfirmPassword().equals("") && (cObj.getConfirmPassword().length() < 4))
		{
			log4log.debug("Confirm Password length is lessthen 4 ");
			errors.rejectValue("confirmPassword","login.ConfirmPassword.InvalidLength");
		}
		
		if((!cObj.getNewPassword().equals("") && !cObj.getConfirmPassword().equals(""))&&(!cObj.getConfirmPassword().equals(cObj.getNewPassword())))
		{
			log4log.debug("New password and confirm password doesn't match");
			errors.rejectValue("newPassword","login.ConfirmPassword.Mismatch");
		}
		
	}
	
}
