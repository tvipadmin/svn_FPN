package com.televital.fptelemedicine.domain;

public class LastRecord {
	private String code;
	private String lastId;
	
	public final String getCode() {
	    return code;
	}

  public void setCode(String code) {
	  this.code = code;
  }
  
  public final String getLastId() {
	 return lastId;
  }

  public void setLastId(String lastId) {
	this.lastId = lastId;
  }

}