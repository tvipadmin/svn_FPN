package com.televital.fptelemedicine.domain;

import java.io.Serializable;

public class LabServices implements Serializable{
	
	private String labServiceId;
	private String serviceName;
	private Lab lab;
	private LabTest labTest;
	private LabServices labServices;
	
	  
    public final String getLabServiceId() {    return labServiceId;	  }
    public void setLabServiceId(String value) {   	labServiceId = value;	  }
    
    public final String getServiceName() {    return serviceName;	  }
    public void setServiceName(String value) {  	serviceName = value;	  }
    
    public final Lab getLab() {    return lab;	  }
    public void setLab(Lab value) {   	lab= value;	  }
    
    public final LabTest getLabTest() {    return labTest;	  }
    public void setLabTest(LabTest value) {   	labTest= value;	  }
    
    //public final LabServices getServiceName() {    return labServices;	  }
    public void setServiceName(LabServices value) {  	labServices = value;	  }
}
