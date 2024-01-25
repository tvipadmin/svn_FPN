import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.applet.Applet;

import java.awt.Color;
import java.security.AccessController;
import java.security.PrivilegedAction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.awt.Frame;

public class StartDigitalPen extends Applet {

	private String applicationName;
    private String strWebServer;
	private String projectContext ;
	private String firstName 	;
	private String lastName ;
	private String ageyears 	;
	private String agemonths 	;
	private String gender ;
	private String patientId 	;
	private String caseId 	;
	private String externalId ;
	private String localPath ;
	private String fullPath;
	private String deviceOutputFileName;
	private String datapath;
	private String casePath;
	Frame frame;

	public void init()
	{
            System.out.println("inside init of StartVitalTrack method");
            strWebServer 		= getParameter("WEBSERVER");
		    projectContext 	    = getParameter("PROJECTCONTEXT");
		    firstName 		= getParameter("FIRSTNAME");
			lastName 	    = getParameter("LASTNAME");
			ageyears		= getParameter("AGEYEARS");
			agemonths		= getParameter("AGEMONTHS");
			gender 	        = getParameter("GENDER");
			patientId 		= getParameter("PATIENTID");
			caseId			= getParameter("CASEID");
			localPath 	    = getParameter("LOCALPATH");
			//externalId      = getParameter("EXTERNALID");
			datapath		= getParameter("DATAPATH");
			casePath	= getParameter("CASEPATH");



	}

	public StartDigitalPen() {
         System.out.println("inside StartDigitalPen()");


	}


	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}

	public String getApplicationName()
	{
		return this.applicationName;
	}

	public void openApplication()
	{
		//this.setApplicationName(applicationName);

		AccessController.doPrivileged(
		new PrivilegedAction()
		{
			public Object run()
			{
				boolean appOpened = false,transfered = false;

				 appOpened = openDigitalPen();

				if(appOpened)
				{
				 transfered = fileAccess();
				}

				if(appOpened && transfered)
				{
					return true;
				}
				else
				{
					return false;
				}

			}
		});


	}




	private boolean openDigitalPen()
	{

		String appName = this.getApplicationName();
        ObjectOutputStream objectoutPut =null;
        ObjectInputStream outputFromServlet = null;

			try
			{
				//getting parameter

				//Runtime fpPrint=Runtime.getRuntime();

				//getting current time

				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
	            String timeV = sdf.format((Calendar.getInstance()).getTime());
	            deviceOutputFileName ="Note"+"-"+patientId+"-"+timeV;
				fullPath = localPath+patientId+"/"+caseId ;

				boolean dirCreated = (new File(fullPath)).mkdirs();

				if(dirCreated)
				{
					System.out.println("directory " + fullPath + " created");
				}

				Process child =null;

				System.out.println("Application Name: "+appName);
				//String execfile ="vt2App.exe -c,"+firstName+","+lastName+","+ageyears+","+externalId+","+gender+","+fullPath+","+deviceOutputFileName;

				String execfile ="java -cp .;c://vitalware//libs//digitalpen.jar;c://vitalware//libs//log4j-1.2.9.jar notepad"+" "+fullPath+" "+deviceOutputFileName;

			    //String execfile = "java -cp .;./log4j-1.2.9.jar notepad"+" "+fullPath+" "+deviceOutputFileName;

				System.out.println("Application with parameters : "+execfile);

				child	=	Runtime.getRuntime().exec(execfile);
                child.waitFor();




	                     System.out.println("after executing application command");
				        //child.destroy();
						}
								catch(Exception e)
								{
									MessageBox box = new MessageBox(frame,"VitalWare2.0",false,"Application ERROR: Unable to open VitalTrack Application");
									System.out.println("Unable to open application due to :"+e.toString());
									//update the status info

									return  false;
			                   }
			return true;
				}



	private boolean fileAccess()
	{
		                        String servletAddressdata = "http://"+strWebServer+projectContext+"/DigitalPenReceiver?pid="+patientId+"&cid="+caseId;
								HttpClient client = new HttpClient();
								System.out.println("httpclient created");
								PostMethod mPost = new PostMethod(servletAddressdata);
							//	System.out.println("post method created");

                                int recentFileSize=0,k=0;

                                File directory = new File(fullPath);
		                        File[] files = directory.listFiles();

                               	for (int index = 0; index < files.length; index++)
								{
								  String name = files[index].getName();
								  if(name.startsWith(deviceOutputFileName))
								  			{
								  				recentFileSize++;
			                                }

							    }

                               	int noatt = recentFileSize;
								File listfiles[]= new File[noatt];


								for (int index = 0; index < files.length; index++)
								{

								   String name = files[index].getName();

								   if(name.startsWith(deviceOutputFileName))
									 {
										listfiles[k] = files[index];
										k++;
									 }
		                        }


								Part[] parts= new Part[noatt*2];
								ArrayList pathfile[] = new ArrayList[noatt];

								boolean fileAvailable= false,transferSuccessful=false;

								String pathco="/";
										try
										{

						                 int f=0;
						             	for(int l=0;l<noatt;l++)
										{
										   	String onlypath=datapath+"/"+patientId+"/"+caseId+"/"+casePath;
									    	parts[f]=new StringPart(onlypath,onlypath) ;
											parts[f+1] =new FilePart(listfiles[l].getName(), listfiles[l]);
											f=f+2;
											System.out.println("path: "+onlypath);
										}


										fileAvailable = true;

										System.out.println("second for loop");
									    mPost.setRequestEntity( new MultipartRequestEntity(parts, mPost.getParams()) );
									    System.out.println("setRequestEntity");
										int statusCode1 = client.executeMethod(mPost);
										System.out.println("statusLine>>>" + mPost.getStatusLine()+"remove later"+ mPost.getResponseBodyAsString());
										mPost.releaseConnection();

										transferSuccessful = true;

						           }
						         catch(Exception e)
						         {
									 String ErrorMessage = null;
									 if(!fileAvailable)
									 {
										 ErrorMessage = "Error: Device output not available, Cannot continue";
									 }
									 else if(!transferSuccessful)
									 {
										 ErrorMessage = "Error: Data Transfer Failed";
									 }

									 MessageBox box = new MessageBox(frame,"VitalWare2.0",false,ErrorMessage);


							           System.out.println("Exception Due To: "+e.toString());
							           return false;
                                }

                                return true;
	                          }


			}





