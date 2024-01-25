 
package com.televital.fptelemedicine.license;



import java.util.StringTokenizer;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class Parser
{
    private final static Logger log = Logger.getLogger(Parser.class);

	private static final String FIRST_DELIM 		= "\u0001";
	private static final String SECOND_DELIM 		= "\u0002";
	private static final String THIRD_DELIM 		= "\u0003";
	private static final String FOURTH_DELIM 		= "\u0004";

	private static final String DATE_FORMAT 		= "yyyy-MM-dd";
	private static final String TIMESTAMP_FORMAT 	= "yyyy-MM-dd HH:mm:ss";

	private static final String LEVEL1				= "LEVEL1";
	private static final String LEVEL2				= "LEVEL2";
	private static final String LEVEL3				= "LEVEL3";

	public static final String SITE_LICENSE		= "SITE";
	public static final String USER_LICENSE		= "USER";

	public static final String NO_EXPIRY			= "PERMANENT";

/*******************************************************************************************/
	private boolean isNumeric(String inputString)
	{
		for(int i=0; i<inputString.length(); i++)
		{
			char singleChar = inputString.charAt(i);

			if(singleChar < '0' || singleChar > '9')
			{
				return false;
			}
		}

		return true;
	}
/*******************************************************************************************/
	private String formatDate(String inputString, String format)
	{
		String outputString = null;

		try
		{
			SimpleDateFormat sdf= new SimpleDateFormat(format);
			Date dt = sdf.parse(inputString);
			outputString = sdf.format(dt);
		}
		catch(Exception e)
		{
			log.error("Error parsing Date : "+e.toString());
		}

		return outputString;
	}
/*******************************************************************************************/

	private boolean validateDate(String DateParam)
	{
		/**
			Input format : yyyy-MM-dd
			Check the length of the parameter
		**/
		if(DateParam.length() != 10)
		{
			log.error("Invalid date found : "+ DateParam );
			return false;
		}

		String tmpString = DateParam.replace("-","");

		/**
			Check if the parameter has invalid characters
		**/
		if(!isNumeric(tmpString))
		{
			log.error("Invalid Date : "+DateParam);
			return false;
		}

		int sMonth	= Integer.parseInt(tmpString.substring(4,6));
		int sDate	= Integer.parseInt(tmpString.substring(6,8));

		if(sMonth < 1 || sMonth > 12)
		{
			log.error("Invalid month '"+sMonth+"' found in Date :"+DateParam);
			return false;
		}

		if(sDate < 1 || sDate > 31)
		{
			log.error("Invalid date '"+sDate+"' found in :"+DateParam);
			return false;
		}

		return true;
	}
/*******************************************************************************************/
	private boolean validateTime(String TimeParam)
	{
		/**
			Input format : HH:mm:ss
			Check the length of the parameter
		**/
		if(TimeParam.length() != 8)
		{
			log.error("Invalid time found : "+ TimeParam );
			return false;
		}

		String tmpString = TimeParam.replace(":","");

		/**
			Check if the parameter has invalid characters
		**/
		if(!isNumeric(tmpString))
		{
			log.error("Invalid Time : "+TimeParam);
			return false;
		}

		/**
			Retrieve Hour, Minute, Second and validate individually
		**/
		int sHour	= Integer.parseInt(tmpString.substring(0,2));
		int sMinute	= Integer.parseInt(tmpString.substring(2,4));
		int sSecond	= Integer.parseInt(tmpString.substring(4,6));

		if(sHour < 0 || sHour > 23)
		{
			log.error("Invalid hour '"+sHour+"' found in Time :"+TimeParam);
			return false;
		}

		if(sMinute < 0 || sMinute > 59)
		{
			log.error("Invalid minute '"+sMinute+"' found in Time :"+TimeParam);
			return false;
		}

		if(sSecond < 0 || sSecond > 59)
		{
			log.error("Invalid second '"+sSecond+"' found in Time :"+TimeParam);
			return false;
		}

		return true;
	}


}