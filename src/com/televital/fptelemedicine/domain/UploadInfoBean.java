package com.televital.fptelemedicine.domain;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.televital.fptelemedicine.utils.FPTelemedicineUtils;
import com.televital.fptelemedicine.web.FileUploadController;

public class UploadInfoBean {
	
	  private static Log log = LogFactory.getLog(UploadInfoBean.class);
	
	private long totalSize = 0;
	private long bytesRead = 0;
	private String percentage = "0";
	private String testData="";
	
	public String getTestData() {
		log.error("inside getTestData(): "+testData);
	return testData;
	}
	public void setTestData(String testData) {
		log.error("inside setTestData: "+testData);
	this.testData = testData;
	}

	
	public String getPercentage() {
		log.error("inside get Percentage aaaaa: "+percentage);
	return percentage;
	}
	public void setPercentage(String percentage) {
		log.error("inside set Percentage: "+percentage);
	this.percentage = percentage;
	}

	public long getTotalSize() {
	return totalSize;
	}
	public void setTotalSize(long totalSize) {
	this.totalSize = totalSize;
	}
	public long getBytesRead() {
	return bytesRead;
	}
	public void setBytesRead(long bytesRead) {
	this.bytesRead = bytesRead;
	}
	
	public void cancelPhoto(String pathName)
	{
		log.error("inside cancel photo"+pathName);
		File photopath=new File(pathName);
	    FPTelemedicineUtils.deletePhoto(photopath); 
	}
	}

