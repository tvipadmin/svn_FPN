package com.televital.fptelemedicine.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class DigitalPenReceiver
 */
public class DigitalPenReceiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DigitalPenReceiver.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DigitalPenReceiver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String patientId = req.getParameter("pid");
			String caseId = req.getParameter("cid");
			String attachments="";
			log.info("patientId : "+patientId);
			log.info("caseId : "+caseId);
			String dataPath="";
			String deviceFileName = "";       
			FileUpload fup=new FileUpload();
						
        	DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
		

			List /* FileItem */ items = upload.parseRequest(req);
			
			//fileattachmentsize = items.size()/2;
			//perfileperct = 96/fileattachmentsize;
			
			/*outfile1 = new BufferedWriter(new FileWriter(strFilePath1));
			outfile1.write(perfileperct.toString());
			outfile1.close();*/
			
			log.info("Items :"+items);
			Iterator iter = items.iterator();
			String filepath="";
			
			while (iter.hasNext()) 
			{
				FileItem item = (FileItem) iter.next();
                if (item.isFormField())
                {
					filepath=item.getFieldName();
					//filepath=dataPath+filepath;
					//filepath ="D:/JulietVitalTrack";
				    log.info("its a field: "+filepath);
				  }
                else 
                {
                	log.info("its a file");
                	log.info(item.getName());
                	if(deviceFileName.equals(""))
                	{
                		deviceFileName = item.getName();
                	}
                	else
                	{
                		deviceFileName =deviceFileName+"#####"+item.getName();
                	}
                	
                	File cfile=new File(item.getName());
                	//File tosave=new File(getServletContext().getRealPath("/"),cfile.getName());
                	log.info("filepath: "+filepath);
                	boolean success = (new File(filepath)).mkdirs();
                	log.info("success: "+success);
                	File tosave=new File(filepath,cfile.getName());
                	log.info("tosave: "+tosave);
                	item.write(tosave);
                	//percentage = percentage+perfileperct;
                	//sess.setAttribute("transferupdate", percentage.toString());
                	/*outfile = new BufferedWriter(new FileWriter(strFilePath));
        			outfile.write(percentage.toString());
        			outfile.close();
           			outfile1 = new BufferedWriter(new FileWriter(strFilePath1));
        			outfile1.write(percentage.toString());
        			outfile1.close();*/
                	//log.info("session percentage: "+sess.getAttribute("transferupdate"));
        			//log.info("percentage: "+percentage);
                	log.info("tosave: "+tosave);
			    }
                            
			}
			//log.info("session percentage outside else: "+sess.getAttribute("transferupdate"));
			//percentage = 100;
			//sess.setAttribute("transferupdate", percentage.toString());
			//log.info("session percentage last: "+sess.getAttribute("transferupdate"));
			/*outfile = new BufferedWriter(new FileWriter(strFilePath));
			outfile.write(percentage.toString());
			outfile.close();*/
			//Login login =(Login)sess.getAttribute("VitalWareConstants.SESSION_VAR_LOGIN");
			log.info("End of saving files");
			
			log.info("Start of inserting to database");
			InputStream jdbcStream = this.getClass().getResourceAsStream("/jdbc.properties");   
			log.info("getting property file "+jdbcStream);
			
			Properties jdbcProperties = new Properties();   
			
			jdbcProperties.load(jdbcStream);  
			log.info("loaded properties");
			
			String Driver = jdbcProperties.getProperty("jdbc.driverClassName");
			String url = jdbcProperties.getProperty("jdbc.url");
			String user = jdbcProperties.getProperty("jdbc.username");
			String password = jdbcProperties.getProperty("jdbc.password");
			log.info("Extracted values");
			
			//log.info("database properties: Driver "+ Driver + " url " +url+ " user "+ user + " password "+ password);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String timeV = sdf.format((Calendar.getInstance()).getTime());
           
			Class.forName(Driver).newInstance();
            Connection con = DriverManager.getConnection(url,user,password);
                
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from TBL_Case where caseId='"+caseId+"'");
            while(rs.next())
            {
            	attachments = rs.getString("Attachments");
            }
            log.info("Form Database attachments : "+attachments);
            if(attachments != null)
            {
            	if(attachments.equals(""))
            	{
            		attachments = deviceFileName+"#####";
            	}
            	else
            	{
            		attachments = attachments+deviceFileName+"#####";
            	}
            }
            else
            {
            	attachments=deviceFileName+"#####";
            }
            stmt.executeUpdate("update TBL_Case set Attachments ='"+attachments+"' where caseId = '" +caseId+"'");
            con.close();
		}
		catch(Exception e)
		{
			//percentage=-1;
			//sess.setAttribute("transferupdate", percentage.toString());
			/*BufferedWriter outfile = new BufferedWriter(new FileWriter(strFilePath));
			outfile.write(percentage.toString());
			outfile.close();*/
			log.info("Exception Due To: "+e.toString());	
			log.info("Exception Due To e.getStackTrace(): "+e.getStackTrace());
			log.info("Exception Due To e.getCause(): "+e.getCause());
			log.info("Exception Due To e.getMessage(): "+e.getMessage());
		}

	}
}
