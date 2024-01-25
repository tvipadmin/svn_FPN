package com.televital.fptelemedicine.domain.form;

import java.util.List;
import java.util.Set;

public class ViewPatientOrderTestBackingObject {
	private List labTestList;
	private List labServiceList;
	
	public void setLabTestList(List labTestList)	{	this.labTestList=labTestList;	}
	public List getLabTestList()	{	return this.labTestList;	}
	
	public void setLabServiceList(List labServiceList)	{	this.labServiceList=labServiceList;	}
	public List getLabServiceList()	{	return this.labServiceList;	}
}
