
package com.televital.fptelemedicine.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class AccessRights
{
	private static final Logger log = Logger.getLogger(AccessRights.class);

	public static final String USER_ADMIN 		        = "ADMIN";
	public static final String USER_SPECALIST 	        = "SPECALIST";
	public static final String USER_FAMILYPHYSICIAN 	= "FAMILYPHYSICIAN";
	public static final String USER_LAB 	            = "LAB";
	public static final String USER_DOCTOR              = "DOCTOR";
	

	public static List<String> getAdminMainViewControllerUsers()
	{
		log.debug("Return getSingleFileDeleteControllerUsers");
		List<String> userList = new ArrayList<String>();
		userList.add(AccessRights.USER_ADMIN);
		return userList;
	}
	
	private static List<String> getEMRUsers()
	{
		log.debug("Return getEMRUsers");
		List<String> userList = new ArrayList<String>();
		userList.add(AccessRights.USER_ADMIN);
		userList.add(AccessRights.USER_FAMILYPHYSICIAN);
		userList.add(AccessRights.USER_SPECALIST);
		userList.add(AccessRights.USER_DOCTOR);
		userList.add(AccessRights.USER_LAB);
		return userList;
	}
	public static List<String> getChangePasswordControllerUsers()
	{
		return getEMRUsers();
	}
	
	
}
