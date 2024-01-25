package com.televital.fptelemedicine.web;

import java.io.Serializable;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import org.apache.log4j.Logger;

import com.televital.fptelemedicine.utils.FPTelemedicineUtils;

public class ResetLogin implements HttpSessionBindingListener, Serializable {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public static final long serialVersionUID = 1234; 
	public static final Logger log4log = Logger.getLogger(ResetLogin.class);
	ServletContext context = null;
	/**
	 * @param context
	 */
	public ResetLogin(ServletContext context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		log4log.error("Decrementing the concurrent count.");
		if(!FPTelemedicineUtils.decrementConcurrentCount(context))
		{
			log4log.error(" Cannot decrement the concurrent counter, cannot continue.");
		}
	}
}
