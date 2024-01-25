package com.televital.fptelemedicine.utils;


import org.apache.log4j.Logger;
public abstract class FPTelemedicineConstants {
	
	public static final Logger log = Logger.getLogger(FPTelemedicineConstants.class);
	
	// Account Status
	public static final String ACCOUNT_STATUS_ENABLED			= "ENABLED";
	public static final String ACCOUNT_STATUS_DISABLED			= "DISABLED";
	public static final String ACCOUNT_STATUS_PENDING			= "PENDING";
	
	// Login Status
	public static final String LOGIN_STATUS_IN					= "IN";
	public static final String LOGIN_STATUS_OUT					= "OUT";
	
	// SESSION VARIABLES 
	public static final String SESSION_VAR_LOGIN				= "login";
	public static final String SESSION_VAR_RESET_LOGIN			= "resetLogin";
	
	//CONTEXT VARIABLES 
	public static final String CONTEXT_VAR_SERVER_LICENSE		= "serverLicense";
	
	
}
