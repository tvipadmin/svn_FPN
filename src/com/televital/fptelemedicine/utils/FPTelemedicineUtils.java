package com.televital.fptelemedicine.utils;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.televital.fptelemedicine.domain.FPCase;
import com.televital.fptelemedicine.domain.IFPTelemedicineFacade;
import com.televital.fptelemedicine.domain.LabServices;
import com.televital.fptelemedicine.domain.LastRecord;
import com.televital.fptelemedicine.domain.Login;
import com.televital.fptelemedicine.domain.Patient;
import com.televital.fptelemedicine.license.ServerLicense;

public abstract class  FPTelemedicineUtils {

	public static final Logger log4log = Logger.getLogger(FPTelemedicineUtils.class);
	private static List doctorListunq = null;
	private static List loginListunq = null;
	private static List labListunq = null;
	private static List patientListunq = null;
	// Static Object as lock for updating the Application variable
	private static Object lock_for_parallel_updates = null;
	
	public static Object getLock()
	{
		if(lock_for_parallel_updates == null) lock_for_parallel_updates = new Object();
		return lock_for_parallel_updates;
	}
	
	/*
	 * Function check the concurrent count on the server is zero or decrement the count on the context
	 * return true if counter is successfully decremented or false otherwise
	 */
	public static boolean decrementConcurrentCount(ServletContext context)
	{
		log4log.info("decrementing operation started.");
		ServerLicense serverLicense;
		//reading the license from the context
		synchronized(FPTelemedicineUtils.getLock())
		{
			serverLicense = (ServerLicense)context.getAttribute(FPTelemedicineConstants.CONTEXT_VAR_SERVER_LICENSE);
		}
		if(serverLicense == null)
		{
			log4log.error("cannot retrieve the license object from the context, cannot decrement.");
			return false;
		}
		try
		{
			int cCount = new Integer(serverLicense.getConcurrentUsers()).intValue();
			if(cCount > 0)
			{
				//decrementing the user count
				log4log.info("decrementing the user count.");
				cCount--;
				log4log.debug("ccount : "+cCount);
				
			}
			else if(cCount == 0)
			{
				log4log.error("Concurrent count is allready reached zero, cannot decrement further.");
			}
			//updating the server license
			log4log.info("Updating the server license to context.");
			serverLicense.setConcurrentUsers(""+cCount);
			synchronized(FPTelemedicineUtils.getLock())
			{
				context.setAttribute(FPTelemedicineConstants.CONTEXT_VAR_SERVER_LICENSE,serverLicense);
			}				
			return true;
		}
		catch(Exception e)
		{
			log4log.error("Error while incrementing the count due to : "+e.toString());
			return false;
		}

	}
	
	public static String getCurrentTimeStamp()
	{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return sdf.format((Calendar.getInstance()).getTime());
	}
	
	public static String getCurrentDate()
	{
		String date = null;
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
	  int year = c.get(Calendar.YEAR);
	  date = year+"-"+month+"-"+day;
	  return date;
	}
	
	//function which returns formated current date
	public static String getFormattedCurrentDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	public static boolean validContactNumber(String inputStr)
	{
	
		if(!StringUtils.hasLength(inputStr)) return true;
		if(isAllWhiteSpaces(inputStr)) return false;
		
		for(int i=0;i<inputStr.length() ; i++)
		{
			char singleChar = inputStr.charAt(i);
			if((singleChar < '0' || singleChar > '9') && singleChar != ' ')
			{
				log4log.info("Invalid Char :"+singleChar);
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAllWhiteSpaces(String inputStr)
	{
		if(!StringUtils.hasLength(inputStr)) return false;		
		if(!StringUtils.hasText(inputStr)) return true;	
		return false;
	}
	
	public static boolean validEMailId(String inputStr)
	{
		if(!StringUtils.hasLength(inputStr)) return true;
		if(isAllWhiteSpaces(inputStr)) return false;
		
		if (inputStr.indexOf('@') <= 0 || inputStr.indexOf('.') <= 0 || 
			inputStr.lastIndexOf('@') == inputStr.length() -1 || 
			inputStr.lastIndexOf('.') == inputStr.length()-1 || 
			inputStr.indexOf(' ') != -1 || 
			Math.abs(inputStr.indexOf('@')-inputStr.indexOf('.'))<=1 || 
			inputStr.lastIndexOf('.') < inputStr.indexOf('@'))
		{
			return false;
		}
		int count = 0;
		for(int j=0;j<inputStr.length();j++)
		{
			char singleChar = inputStr.charAt(j);
			if((singleChar  < '0' || '9'  < singleChar ) && 
			   (singleChar  < 'a' || 'z' < singleChar ) && 
			   (singleChar  < 'A' || 'Z' < singleChar) && 
			   singleChar != '.' && singleChar != '_' && 
			   singleChar != '-' && singleChar != '@')
			{
				return false;
			}
			
			if (singleChar == '@') count = count+1;
		}
		if (count!=1) return false;
		return true;
	}
	public static boolean validUserId(String inputStr)
	{
		if(!StringUtils.hasLength(inputStr)) return false;
		if(isAllWhiteSpaces(inputStr)) return false; 
	
		for(int i=0;i<inputStr.length() ; i++)
		{
			char singleChar = inputStr.charAt(i);
		
			if((singleChar < 'A' || singleChar > 'Z') &&
			   (singleChar < 'a' || singleChar > 'z') &&
			   (singleChar < '0' || singleChar > '9') &&
			   (singleChar != '_')&&(singleChar != '.')&&(singleChar != '-'))
			{
				return false;
			}
		}
	
		if(inputStr.indexOf("_") == 0 || inputStr.lastIndexOf("_") == inputStr.length()-1) return false;
		if(inputStr.indexOf(".") == 0 || inputStr.lastIndexOf(".") == inputStr.length()-1) return false;
		if(inputStr.indexOf("-") == 0 || inputStr.lastIndexOf("-") == inputStr.length()-1) return false;
		return true; 
	}
	public static boolean validPostalCode(String inputStr)
	{
		if(!StringUtils.hasLength(inputStr)) return true;
		if(isAllWhiteSpaces(inputStr)) return false;
		
		for(int i=0;i<inputStr.length() ; i++)
		{
			char singleChar = inputStr.charAt(i);
			if((singleChar < '0' || singleChar > '9') && singleChar != ' ')
			{
				log4log.info("Invalid Char :"+singleChar);
				return false;
			}
		}
		return true;
	}
	
	public static boolean isNumber(String s)
	{
		if(s == null) return false;
		
		for (int j = 0;j < s.length();j++)
		{
			if (!Character.isDigit(s.charAt(j)))
			{
				return false;
			}
		}
		return true;
	}
	
	//Function to check the access permission for different users
	
	public static boolean isAuthorizedUser(List<String> userList,String currentUser)
	{
		log4log.info("current user : "+currentUser);
		log4log.info("userList.size() : "+userList.size());
		if(userList == null) return false;
		for(int i=0; i< userList.size();i++)
		{
			log4log.info("(String)userList.get("+i+") : "+(String)userList.get(i));
			if(((String)userList.get(i)).equals(currentUser)) return true;
		}
		
		return false;
	}
	
	public static boolean updateLoginStatus(Login login,String loginStatus,IFPTelemedicineFacade fptelemedicineFacade)
	{
		log4log.info("Updating login status.");
		try
		{
			//setting login status
			login.setCurrentLoginStatus(loginStatus);
			//setting last login time
			login.setLastLoginTime(FPTelemedicineUtils.getCurrentTimeStamp());
			log4log.debug("loginsetlastlogintime : "+login.getLastLoginTime());
			fptelemedicineFacade.updateLogin(login);
			return true;
		}
		catch(Exception e)
		{
			log4log.error("Error while updating login status due to : "+e.toString());
			return false;
		}
	}
	
	public static boolean stringValidWithSpace(String str) 
	   {
		  return str.matches("[a-zA-Z ]*");  
	   }
	
	public static boolean stringValidWithSpaceDot(String str) 
	   {
		  return str.matches("[a-zA-Z .]*");  
	   }
	
	public static boolean stringValid(String str) 
	   {
		  return str.matches("[a-zA-Z]*");  
	   }
	
	public static boolean checkUniqueDoctor(String doctorId,IFPTelemedicineFacade fptelemedicineFacade)
	{
		log4log.error("inside loadIndividualDoctor: "+doctorId);
		doctorListunq =fptelemedicineFacade.loadDoctorById(doctorId);
		loginListunq = fptelemedicineFacade.loadLoginForm(doctorId);
		if(doctorListunq.size() > 0 || loginListunq.size() > 0)
		{
			return false;
		}
		return true;
	}
	
	public static boolean checkUniqueCaseTitle(String caseTitle,IFPTelemedicineFacade fptelemedicineFacade,HttpSession session)
	{
		log4log.error("inside checkUniqueCaseTitle: "+caseTitle);
		log4log.error("session pat: "+session.getAttribute("PatientIdJ"));
		log4log.error("session caseId: "+session.getAttribute("CaseIdJ"));
		Boolean flag=true;
		String patientId="";
		String caseId ="";
		
		if(session.getAttribute("PatientIdJ") != null)
		{
		 patientId=session.getAttribute("PatientIdJ").toString();
		}
		
		if(session.getAttribute("CaseIdJ") != null)
		{
			caseId=session.getAttribute("CaseIdJ").toString();
		}
		
		patientListunq =fptelemedicineFacade.loadCaseFPByPatientId(patientId);
		log4log.error("patientListunq size: "+patientListunq.size());
		for(int g=0;g<patientListunq.size();g++)
		{
			FPCase fpcase =(FPCase) patientListunq.get(g);
			String caseTitleTemp="";
			if(fpcase.getCaseTitle() != null)
			{
				caseTitleTemp = fpcase.getCaseTitle();
			}
			if(caseTitleTemp.equals(caseTitle.trim()) && !fpcase.getCaseId().equals(caseId))
			{
				flag = false;
				break;
			}
		}
		return flag;
		
	}
	
	public static boolean checkUniqueLab(String labId,IFPTelemedicineFacade fptelemedicineFacade)
	{
		log4log.error("inside loadIndividualDoctor: "+labId);
		labListunq =fptelemedicineFacade.loadLabById(labId);
		loginListunq = fptelemedicineFacade.loadLoginForm(labId);
		if(labListunq.size() > 0 || loginListunq.size() > 0)
		{
			return false;
		}
		return true;
	}
	
	public static String getUniqueFileName(String path,String fileName)
    {
 	   /* Check if the path exists */ 
 	   if(path==null || path.trim().equals(""))
 	   {
 		  log4log.error("Upload path is blank or null");
 		   return null;
 	   }
 	   /* check if the directory already exists and create it if does not exist */
 	   File tmpFile = new File(path);
 	   if(!tmpFile.exists())
 	   {
 		   if(!tmpFile.mkdirs())
 		   {
 			  log4log.error("Error creating directory "+path);
 			   return null;
 		   }
 	   }
 	   
 	   /* Check if the path is directory or a file */
 	   if(!tmpFile.isDirectory())
 	   {
 		  log4log.error("The path "+path+" is not a directory");
 		   return null;
 	   }
 	   /*remove spacess if exists*/
 	  fileName=fileName.replace(' ','_');
 	   
 	   /* Generate unique file name */
 	   path = path.replace('\\','/');
 	   if(!path.endsWith("/")) path = path.concat("/");
 	   tmpFile = new File(path.concat(fileName));
 	   int counter = 0;
 	   while(tmpFile.exists())
 	   {
 		   counter += 1;
 		   tmpFile = new File(path+counter+fileName);		   
 	   }
 	   
 	   return (tmpFile.toString()).replace('\\','/');   
    }
	
	
	static public void deletePhoto(File path) {  
	    if( path.exists() ) {
	    	log4log.error("path.exists: "+path.exists());
	        path.delete(); 
	    
	    }
	    
	  }
	
	public static boolean checkUniquePatient(String patientId,IFPTelemedicineFacade fptelemedicineFacade)
	{
		log4log.error("inside loadIndividualPatient: "+patientId);
		patientListunq =fptelemedicineFacade.loadPatientById(patientId);
		
		if(patientListunq.size() > 0)
		{
			return false;
		}
		return true;
	}
	
	/*
	 * calculating the age in years and months from the database date
	 */
	    public static Patient getAge( Patient patient)
	    {
	    	String s[] = {"",""};   	
	    	try
	    	{
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
		        Calendar lFrom = df.getCalendar();
		        lFrom.setTime(df.parse(patient.getDob()));
		        Calendar lTo = Calendar.getInstance();
		 
		        int lFromYear = lFrom.get( Calendar.YEAR );
		        int lFromMonth = lFrom.get( Calendar.MONTH );
		        int lFromDay = lFrom.get( Calendar.DAY_OF_MONTH );
		 
		        int lToYear = lTo.get( Calendar.YEAR );
		        int lToMonth = lTo.get( Calendar.MONTH );
		        int lToDay = lTo.get( Calendar.DAY_OF_MONTH );
		 
		        int lYearDiff = lToYear - lFromYear;
		        int lMonthDiff = lToMonth - lFromMonth;
		        int lDayDiff = lToDay - lFromDay;
		 
		        if ( lDayDiff < 0 )
		        {
		            lMonthDiff--;
		            Calendar lTemp = Calendar.getInstance();
		            lTemp.add( Calendar.MONTH, -1 );
		            lDayDiff = lTemp.getActualMaximum( Calendar.DAY_OF_MONTH ) + lDayDiff;
		        }
		 
		        if ( lMonthDiff < 0 )
		        {
		            lYearDiff--;
		            lMonthDiff = 12 + lMonthDiff;
		        }
		        s[0] = ""+lYearDiff;
		        s[1] = ""+lMonthDiff;
	    	}
	    	catch(Exception e)
	    	{
	    		log4log.error("Error while calculating age from date of birth due to :"+e.toString());
	    	}
	    	log4log.debug("Age : "+s[0] + "years " +s[1] +"months");
			//update age in years and months patient
			patient.setAgeYears(s[0]);
			patient.setAgeMonths(s[1]);

	    	return patient;
	    }

	    public static boolean checkUniqueLabServiceName(String serviceName,IFPTelemedicineFacade fptelemedicineFacade)
		{
			log4log.error("inside checkUniqueLabServiceName: "+serviceName);
			String temp[] = serviceName.split("#####");
			//labListunq =fptelemedicineFacade.loadLabServiceByServiceName(serviceName);
			labListunq =fptelemedicineFacade.loadLabServiceByBoth(temp[0],temp[1]);
			if(labListunq.size() > 0)
			{
				return false;
			}
			return true;
		}
	    
	    
	    
	    public static String generateNewExternalId(String code, List lastId)
		{
			int newId = 1;
			String finalId = "";
			SimpleDateFormat sdate = new SimpleDateFormat("y");
			Calendar cal = Calendar.getInstance();
			
			String currentYear = sdate.format(cal.getTime());
			
			
			if(lastId.size() > 0)
			{
				
				LastRecord lr = (LastRecord)lastId.get(0);
				
				String oldId = lr.getLastId();
				log4log.error("oldId "+oldId);
				int lastindex = oldId.lastIndexOf("-");
				String idNumber = oldId.substring(lastindex+1,oldId.length());
				int id = Integer.parseInt(idNumber);
				log4log.error("id number "+id);
			
				int firstindex = oldId.indexOf("-");
				String year = oldId.substring(firstindex+1,lastindex);
				
				log4log.error("year "+year);
				
				if(currentYear.equals(year))
				{
					newId = id + 1;
				}
				 finalId = code+"-"+currentYear+"-"+newId;
				
			}
			else
			{
				log4log.error("code "+code);
				finalId = code+"-"+currentYear+"-"+newId;
			}
			log4log.error("final id "+finalId);
			return finalId;
		}
	    
	    public static String duplicateSpaceRemover(String name)
	    {
	    	Pattern pattern = Pattern.compile("\\s+");
		    Matcher matcher = pattern.matcher(name);
		    String str = matcher.replaceAll(" ");
		    return str;
	    }
	    
	    public static String getOneMonthBackDate(String toDate)
		{
			StringTokenizer st = new StringTokenizer(toDate,"-/");

			String oneMonthBackDate = null;
			String strMonthBack = null;
			String strYearBack = null;
			int intYearBack = 0;
			int intMonthBack = 0;

			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();

			if(month.equals("01"))
			{
				intMonthBack = 12;
				strMonthBack = String.valueOf(intMonthBack);
				intYearBack = Integer.parseInt(year) - 1;
				strYearBack = String.valueOf(intYearBack);
				oneMonthBackDate = day+"-"+strMonthBack+"-"+strYearBack;
			}
			else
			{
				intMonthBack = Integer.parseInt(month) - 1;
				strMonthBack = String.valueOf(intMonthBack);
				oneMonthBackDate = day+"-"+strMonthBack+"-"+year;
			}
			return oneMonthBackDate;

		}

	    public static boolean compareDates(String fromDate,String toDate)
		{
			boolean flag = false;
			Calendar fromCal = Calendar.getInstance();
			Calendar toCal = Calendar.getInstance();
			
			try
			{
				StringTokenizer st1 = new StringTokenizer(fromDate, "-/");
				StringTokenizer st2 = new StringTokenizer(toDate, "-/");
				
				
				int day1 = Integer.parseInt(st1.nextToken());
				int month1 = Integer.parseInt(st1.nextToken());
				int year1 = Integer.parseInt(st1.nextToken());

				int day2 = Integer.parseInt(st2.nextToken());
				int month2 = Integer.parseInt(st2.nextToken());
				int year2 = Integer.parseInt(st2.nextToken());

								
				
				fromCal.set(year1,month1,day1);
				toCal.set(year2,month2,day2);
				
				String date1 = ""+year1+"/"+month1+"/"+day1;
				String date2 = ""+year2+"/"+month2+"/"+day2;

				if(toCal.equals(fromCal))
				{
					flag = true;
				}
				else if(toCal.after(fromCal))
				{
					flag = true;
				}
				else if(toCal.before(fromCal))
				{
					flag = false;
				}
								
								
				return flag;
			}catch(Exception e)
			{
				return false;
			}
		}
	    
	    public static String getYearMonthDay(String strDate)
		{
			StringTokenizer st = new StringTokenizer(strDate,"-/");
			String yearMonthDay = null;
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			yearMonthDay = year+"-"+month+"-"+day;
			return yearMonthDay;
		}
	    
	    public static boolean compareEnteredDates(String fromDate,String toDate)
		{
			boolean flag = false;
			Calendar fromCal = Calendar.getInstance();
			Calendar toCal = Calendar.getInstance();
			Calendar currentDate = Calendar.getInstance();
			String curDate = getCurrentDate();
			try
			{
				StringTokenizer st1 = new StringTokenizer(fromDate, "-/");
				StringTokenizer st2 = new StringTokenizer(toDate, "-/");
				StringTokenizer st3 = new StringTokenizer(curDate,"-/");
				
				int day1 = Integer.parseInt(st1.nextToken());
				int month1 = Integer.parseInt(st1.nextToken());
				int year1 = Integer.parseInt(st1.nextToken());

				int day2 = Integer.parseInt(st2.nextToken());
				int month2 = Integer.parseInt(st2.nextToken());
				int year2 = Integer.parseInt(st2.nextToken());

				int year3 = Integer.parseInt(st3.nextToken());
				int month3 = Integer.parseInt(st3.nextToken());
				int day3 = Integer.parseInt(st3.nextToken());
				
				
				fromCal.set(year1,month1,day1);
				toCal.set(year2,month2,day2);
				currentDate.set(year3,month3,day3);
				log4log.error("GGGGGGGGGGGG : "+fromCal+" "+toCal+" "+currentDate);
				log4log.info("GGGGGGGGGGGG : "+fromCal+" "+toCal+" "+currentDate);
				String date1 = ""+year1+"/"+month1+"/"+day1;
				String date2 = ""+year2+"/"+month2+"/"+day2;

				if(fromCal.after(currentDate))
				{
					flag = false;
				}
				else if(toCal.after(currentDate))
				{
					flag = false;
				}
				else
				{
					flag = true;
				}
				
				return flag;
			}catch(Exception e)
			{
				return false;
			}
		}
	    
	   
}
