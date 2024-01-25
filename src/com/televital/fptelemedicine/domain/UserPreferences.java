package com.televital.fptelemedicine.domain;

//import com.televital.vitalware.utils.VitalWareConstants;
import java.io.Serializable;

public class UserPreferences implements Serializable{
	private String css;
	private String language;
	private int maxPageSize;
	
	
	public UserPreferences() {
		//setting default values to the preferences
		//this.setCss(VitalWareConstants.PREFERENCE_DEFAULT_CSS);
		//this.setLanguage(VitalWareConstants.PREFERENCE_DEFAULT_LANGUAGE);
		//this.setMaxPageSize(VitalWareConstants.MAX_RESULTS_PER_PAGE);
	}
	public String getLanguage() {	return language;	}
	public void setLanguage(String language) {	this.language = language;	}
	
	public int getMaxPageSize() {		return maxPageSize;	}
	public void setMaxPageSize(int maxPageSize) {	this.maxPageSize = maxPageSize;	}
	
	public String getCss() {	return css;	}
	public void setCss(String css) {	this.css = css;	}

}
