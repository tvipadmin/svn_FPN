/**
 * This is a bean to load only username and password from the login form 
 * to get the login object from database 
 */
package com.televital.fptelemedicine.domain;


public class LoginForm {
	String loginId;
	String password;
   
	public void setLoginId(String id){	this.loginId = id;}
    public String getLoginId(){	return this.loginId;}
    
    public void setPassword(String password){	this.password = password;}
    public String getPassword(){	return this.password;}
}
