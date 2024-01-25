package com.televital.fptelemedicine.domain;



import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;


public class SMSSenderClass  implements SMSSender{

public static final Logger log4log = Logger.getLogger(SMSSenderClass.class);
	
	private String smsUserId;
	private String smsPassword;
	private String smsSenderId;
	private String smsMessagePatRef;
	private IFPTelemedicineFacade fpportalFacade;
	
	public IFPTelemedicineFacade getFpportalFacade() {
		return fpportalFacade;
	}
	public void setFpportalFacade(IFPTelemedicineFacade fpportalFacade) {
		this.fpportalFacade = fpportalFacade;
	}
	
	public String getSmsUserId() {
		return smsUserId;
	}

	public void setSmsUserId(String smsUserId) {
		this.smsUserId = smsUserId;
	}
	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword =smsPassword ;
	}
	public String getSmsSenderId() {
		return smsSenderId;
	}

	public void setSmsSenderId(String smsSenderId ) {
		this.smsSenderId = smsSenderId;
	}
	public String getSmsMessagePatRef() {
		return smsMessagePatRef;
	}

	public void setSmsMessagePatRef(String smsMessagePatRef) {
		this.smsMessagePatRef = smsMessagePatRef;
	}
	
	 public String sendMessage(String doctorIds)
		{
			//String sendMsgdata = "http://"+strWebServer+projectContext+"/DigitalPenReceiver?pid="+patientId+"&cid="+caseId;
			try
			{
				log4log.error("SMSSMSSMSMSMSMSMSMSMSMSMSSMSM");
				String userId = this.getSmsUserId();
				log4log.error("userId: "+userId);
				String password = this.getSmsPassword();
				String senderId = this.getSmsSenderId();
				String message = this.getSmsMessagePatRef();
				
				log4log.error("pwd "+password);
				log4log.error("senderId : "+senderId);
				log4log.error("msg : "+message);
				String docIds[] = doctorIds.split("#####");
				String responseBody="";
				String tempRes="";
				if(docIds.length >0)
				{
				
				for(int i=0;i<docIds.length;i++)
				{
					
				List listDoc = this.getFpportalFacade().loadDoctorById(docIds[i]);
				if(listDoc.size()>0)
				{
					
				Doctor doc = (Doctor)listDoc.get(0);
				
				String to = doc.getContactDetails().getContactNumber();
				log4log.error("Mobile Number: "+to+" "+"DocName: "+doc.getFirstName());
				if(to != null && to != "")
				{
			    String sendMsgdata ="http://instant.smsinfy.com/web2sms.php?username="+userId+"&password="+password+"&to="+to+"&sender="+senderId+"&message="+message;
			    log4log.error("httpclient before creating");
			    HttpClient client = new HttpClient();
			    log4log.error("httpclient created");
	            GetMethod method = new GetMethod(sendMsgdata);
	            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
    		    new DefaultHttpMethodRetryHandler(3, false));
                int statusCode = client.executeMethod(method);

	                 if (statusCode != HttpStatus.SC_OK) {
	                	 log4log.error("Method failed: " + method.getStatusLine()); 
	                  
	                  }               
	   
	                 tempRes =tempRes+method.getResponseBodyAsString();

	               // Deal with the response.
	                // Use caution: ensure correct character encoding and is not binary data
	                 log4log.error("hi this is response: "+tempRes);
				}
				}
				}
				responseBody = tempRes;
				}
	            return responseBody;
			}
			catch(Exception e)
			{
				log4log.error("Exception GGGGGGGGGGGGGGGGGGGGGGG: "+e);
				return e.toString();
			}
		}
}
