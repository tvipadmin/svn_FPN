package com.televital.fptelemedicine.domain.form;

import java.util.List;

import com.televital.fptelemedicine.domain.Doctor;
import com.televital.fptelemedicine.domain.Lab;

public class ChangePasswordBackingObject 
{
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private String currentUserId;
	private String newUserId;
	private String confirmUserId;
	private String errormessage;
	
	
	
	public String getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	public String getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(String newUserId) {
		this.newUserId = newUserId;
	}
	public String getConfirmUserId() {
		return confirmUserId;
	}
	public void setConfirmUserId(String confirmUserId) {
		this.confirmUserId = confirmUserId;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	
}
