
var peopleCache = { };
var selectSpecalist;
var selectLab;
var patReg;
var patRef;
var from;
var to;
var flag=false;
var flagLab = false;
var patOrdered;
var patordref1 ;
var patordref2 ;
var patis ;

function init(loginId,selectSpecalistG,selectLabG,patRegG,fromG,toG,patRefG,patOrderedG,patordref1G,patordref2G,patisG)
{
	//alert(loginId);
	//alert("GG");
	selectSpecalist =selectSpecalistG;
	selectLab = selectLabG;
	patReg = patRegG;
	patRef = patRefG
    from = fromG;
    to = toG;
    patOrdered = patOrderedG;
    patordref1 = patordref1G
    patordref2 = patordref2G
    patis =  patisG;
  	document.getElementById("fpMenuStatistics").style.background="#f7941d"; 
  	//alert(document.getElementById("fpStatisticsMenu"));
  	if(document.getElementById("fpStatisticsMenu") != null)
  	{
  		document.getElementById("fpStatisticsMenu").style.background="#f7941d"; 
  	}
	loadPatReferredMonth(loginId);
}


function clickOK(enteredDateInvalid,fromTOInvalid,loginId,selectSpecalistG,selectLabG,patRegG,fromG,toG,patRefG)
{
	var fromDate = document.getElementById('fromDate').value;
	var toDate= document.getElementById('toDate').value;
	document.getElementById("errorMessage").innerHTML=""
		
	StatisticsManagement.validateFromToDate(fromDate,toDate, function(data) {
		//document.getElementById("toDate").value = data;
		flag = data;
	
	if(fromDate == "" & toDate=="")
	{
		flag = true;
	}
	
		
	patRef =patRefG;
	selectSpecalist =selectSpecalistG;
	selectLab = selectLabG;
	patReg = patRegG;
    from = fromG;
    to = toG;
    var sepId ="";
    var docName ="";
    var tempVal = document.getElementById('spceialist').value;
    if(tempVal != null && tempVal != "")
    {
    	var tempVal1 = tempVal.split("###");
    	sepId = tempVal1[0];
    	docName = tempVal1[1]
    	//alert(sepId);
    }
   var labId ="";
   var labName ="";
   var tempValLab = document.getElementById('lab').value;
   if(tempValLab != null && tempValLab != "")
   {
   	var tempValLab1 = tempValLab.split("###");
   	labId = tempValLab1[0];
   	labName = tempValLab1[1]
   //	alert(labId);
	//alert(labName);
   }
	
	
   if(flag)
   {
	var flag1 =  false;
	   StatisticsManagement.validateEnteredDate(fromDate,toDate, function(data) {
		   flag1 = data;
		   if(fromDate =="" && toDate =="")
			 {
				flag1 = true; 
			 }
		 if(flag1)
		 {
	if(fromDate=="" && toDate=="" && sepId =="" && labId=="")
	{
	
		StatisticsManagement.getCurrentDateS(function(data) {
			//document.getElementById("toDate").value = data;
			toDate = data;
		});
		
		StatisticsManagement.getOneMonthBackDateS(function(data) {
			//document.getElementById("fromDate").value = data;
			fromDate = data;
			
			 });
		
	StatisticsManagement.loadPatRegisteredMonthWise(loginId, function(data) {
    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var patient,id,famphydetails,agegender;
	    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
	   var n=0;
	  
	    for (var i = (data.length)-1; i >= 0; i--) {
	      
	      patient = data[i];
	      id = patient.patientId;
	     
	      famphydetails=patient.firstName+" "+patient.lastName
	      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablePatId" + id, id);
	      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
	      dwr.util.setValue("tableagegenderdetails" + id, agegender);
	      
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = patient;
	      //alert(peopleCache);
	      n=n+1;
	      }
         /*var previousInnerText = new String(); 
         previousInnerText = document.getElementById('totalNoReg').innerText; 
         previousInnerText = previousInnerText.concat(n);*/
	       document.getElementById("insertReqText").innerHTML =patReg+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
	  });
	}
	else if(fromDate!="" && toDate!="" && sepId =="" && labId=="")
	{
		
		StatisticsManagement.loadRegisteredMonthWise(loginId,fromDate,toDate, function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var patient,id,famphydetails,agegender;
		    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
		   var n=0;
		  
		    for (var i = (data.length)-1; i >= 0; i--) {
		      
		      patient = data[i];
		      id = patient.patientId;
		     
		      famphydetails=patient.firstName+" "+patient.lastName
		      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tablePatId" + id, id);
		      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
		      dwr.util.setValue("tableagegenderdetails" + id, agegender);
		      
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = patient;
		      //alert(peopleCache);
		      n=n+1;
		      }
	         /*var previousInnerText = new String(); 
	         previousInnerText = document.getElementById('totalNoReg').innerText; 
	         previousInnerText = previousInnerText.concat(n);*/
		     document.getElementById("insertReqText").innerHTML =patReg+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
		  });
	}
	else if(sepId !="" && labId =="")
	{
		//alert("sepId: "+sepId);
		changeTypeSep(enteredDateInvalid,fromTOInvalid,loginId);
	}
	else if(sepId =="" && labId !="")
	{
		changeTypeLab(enteredDateInvalid,fromTOInvalid,loginId);
	}
	if(sepId !="" && labId !="")
	{
		bothSepLabSelected(enteredDateInvalid,fromTOInvalid,loginId,sepId,labId,fromDate,toDate,docName);
		
	}
	 }
		 else
		 {
			  failureMessage(enteredDateInvalid);
			   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
				      return (tr.id != "pattern");
				    }});
			   document.getElementById("insertReqText").innerHTML="";
		 }
	   });
   }
   else
   {
	   failureMessage(fromTOInvalid);
	   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
	   document.getElementById("insertReqText").innerHTML="";
   }
   
	});
}



function loadPatReferredMonth(loginId)
{
	
	var fromDate = document.getElementById('fromDate').value;
	var toDate= document.getElementById('toDate').value;
	
		
	CaseManagement.showSpecalistLogin(loginId,function(data) {
		
	data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
	var doctor,docId,name;
		for(var i=0; i<data.length;i++)
		{
		doctor = data[i];	
		docId = doctor.doctorId;
		name = doctor.firstName+" "+doctor.lastName
		var e = document.getElementById('spceialist');
		if(i==0)
		   {
			  //alert("Before: "+e.length);
			   for(var k =1;k<e.length;k++)
			   {
				   e.remove(k);
				   //alert(k);
			   }
			  e = document.getElementById('spceialist');
			  //alert("After "+e.length);
			   
		    e.options[i] = new Option(selectSpecalist,"");
		    e.options[i+1] = new Option(name,docId+"###"+name);
		  //  alert(e.options[i]);
		  // alert(e.options[i+1]);
		   }
		   else
		   {
			  e.options[i+1] = new Option(name,docId+"###"+name);
			  //alert("inside else");
			// alert(e.options[i+1]);
		   }
		
		}
	});
	
	LabManagement.loaddwrLabsLogin(loginId,function(data) {
		data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
		var lab,labId,labName;
		for(var i=0; i<data.length;i++)
		{
		lab = data[i];	
		labId = lab.labId;
		labName = lab.labName
		var el = document.getElementById('lab');
		if(i==0)
		   {
			  //alert("Before: "+e.length);
			   for(var k =1;k<el.length;k++)
			   {
				   el.remove(k);
				   //alert(k);
			   }
			  el = document.getElementById('lab');
			  //alert("After "+e.length);
			   
		    el.options[i] = new Option(selectLab,"");
		    el.options[i+1] = new Option(labName,labId+"###"+labName);
		  //  alert(e.options[i]);
		  // alert(e.options[i+1]);
		   }
		   else
		   {
			  el.options[i+1] = new Option(labName,labId+"###"+labName);
			  //alert("inside else");
			// alert(e.options[i+1]);
		   }
		
		}
	});
	
	var sepId ="";
    var tempVal = document.getElementById('spceialist').value;
    if(tempVal != null && tempVal != "")
    {
    	var tempVal1 = tempVal.split("###");
    	sepId = tempVal1[0];
    	//alert(sepId);
    }
    var labId ="";
    var labName ="";
    var tempValLab = document.getElementById('lab').value;
    if(tempValLab != null && tempValLab != "")
    {
    	var tempValLab1 = tempValLab.split("###");
    	labId = tempValLab1[0];
    	labName = tempValLab1[1]
    	//alert(labId);
 	//alert(labName);
    }
   
	if(fromDate=="" && toDate=="" && sepId =="" && labId=="")
	{
	
		StatisticsManagement.getCurrentDateS(function(data) {
			//document.getElementById("toDate").value = data;
			toDate = data;
		});
		
		StatisticsManagement.getOneMonthBackDateS(function(data) {
			//document.getElementById("fromDate").value = data;
			fromDate = data;
			
			 });
		
	StatisticsManagement.loadPatRegisteredMonthWise(loginId, function(data) {
    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var patient,id,famphydetails,agegender;
	    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
	   var n=0;
	  
	    for (var i = (data.length)-1; i >= 0; i--) {
	      
	      patient = data[i];
	      id = patient.patientId;
	     
	      famphydetails=patient.firstName+" "+patient.lastName
	      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablePatId" + id, id);
	      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
	      dwr.util.setValue("tableagegenderdetails" + id, agegender);
	      
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = patient;
	      //alert(peopleCache);
	      n=n+1;
	      }
         /*var previousInnerText = new String(); 
         previousInnerText = document.getElementById('totalNoReg').innerText; 
         previousInnerText = previousInnerText.concat(n);*/
	       document.getElementById("insertReqText").innerHTML =patReg+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
	  });
	}
	
}

function captureReturnKey(event)
{
	var	code = 0;
	code = event.keyCode;
	if(code	==	13);
		
}


function emrViewClicked(eleid,loginId) {
	   // alert(eleid);
		 testFunction()
		 var patient = peopleCache[eleid.substring(7)];
		// alert(patient.patientId);
		 var dType="fp";
		 document.location = "ViewEmrStatistics.htm?docType="+dType+"&patientId="+patient.patientId+"&docId="+loginId+"";
	 }

function changeTypeSep(enteredDateInvalid,fromTOInvalid,loginId)
{
			
	var sepId ="";
	var docName= "";	
    var tempVal = document.getElementById('spceialist').value;
    if(tempVal != null && tempVal != "")
    {
    	var tempVal1 = tempVal.split("###");
    	sepId = tempVal1[0];
    	docName = tempVal1[1];
    	//alert(sepId);
    	//alert(docName);
    }
    var labId ="";
    var labName ="";
    var tempValLab = document.getElementById('lab').value;
    if(tempValLab != null && tempValLab != "")
    {
    	var tempValLab1 = tempValLab.split("###");
    	labId = tempValLab1[0];
    	labName = tempValLab1[1]
    	//alert(labId);
 	//alert(labName);
    }
	var fromDate=document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var textData="";
	
	var labName= labId;	
	document.getElementById("errorMessage").innerHTML=""
	StatisticsManagement.validateFromToDate(fromDate,toDate, function(data) {
		
		flag = data;
		
		if(fromDate == "" & toDate=="")
		{
			flag = true;
		}
  if(flag)
  {
	var flag1 =  false;
	StatisticsManagement.validateEnteredDate(fromDate,toDate, function(data) {
		   flag1 = data;
		   if(fromDate =="" && toDate =="")
			 {
				flag1 = true; 
			 }
  if(flag1)
	 {
	if(sepId =="" && labId=="")
	{
		if(fromDate =="" && toDate =="")
		{
			loadPatReferredMonth(loginId);
		}
		else
		{
		StatisticsManagement.loadRegisteredMonthWise(loginId,fromDate,toDate, function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var patient,id,famphydetails,agegender;
		    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
		   var n=0;
		  
		    for (var i = (data.length)-1; i >= 0; i--) {
		      
		      patient = data[i];
		      id = patient.patientId;
		     
		      famphydetails=patient.firstName+" "+patient.lastName
		      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tablePatId" + id, id);
		      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
		      dwr.util.setValue("tableagegenderdetails" + id, agegender);
		      
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = patient;
		      //alert(peopleCache);
		      n=n+1;
		      }
		   
	         /*var previousInnerText = new String(); 
	         previousInnerText = document.getElementById('totalNoReg').innerText; 
	         previousInnerText = previousInnerText.concat(n);*/
		     document.getElementById("insertReqText").innerHTML =patReg+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
		  });
		}
	}
	else if(sepId !="" && labId=="")
	{
	  StatisticsManagement.loadPatReferredSep(loginId,sepId,fromDate,toDate, function(data) {
    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var patient,id,famphydetails,agegender;
	    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
	   var n=0;
	  
	    for (var i = (data.length)-1; i >= 0; i--) {
	      
	      patient = data[i];
	      id = patient.patientId;
	     
	      famphydetails=patient.firstName+" "+patient.lastName
	      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablePatId" + id, id);
	      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
	      dwr.util.setValue("tableagegenderdetails" + id, agegender);
	      
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = patient;
	      //alert(peopleCache);
	      n=n+1;
	      }
         /*var previousInnerText = new String(); 
         previousInnerText = document.getElementById('totalNoReg').innerText; 
         previousInnerText = previousInnerText.concat(n);*/
	    if(fromDate == "" && toDate !="")
		{
			textData=patRef+" "+n+" "+to+" "+docName+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate != "")
		{
			textData=patRef+" "+n+" "+to+" "+docName+" "+from+" "+fromDate;
		}
	    if(toDate != "" && fromDate != "")
		{
			textData=patRef+" "+n+" "+to+" "+docName+" "+from+" "+fromDate+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate == "")
		{
			textData=patRef+" "+n+" "+to+" "+docName;
		}
	    document.getElementById("insertReqText").innerHTML =textData;
	  });
	} else if(sepId == "" && labId !="")
	{
		//alert("hello");
		var orderedSize ="";
		 StatisticsManagement.loadTotalPatOrderedLab(loginId,labId,fromDate,toDate, function(data) {
				//document.getElementById("fromDate").value = data;
				orderedSize = data;
				 });
		StatisticsManagement.loadPatOrderedLab(loginId,labId,fromDate,toDate, function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var patient,id,famphydetails,agegender;
		    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
		   var n=0;
		  //alert("n value : "+n);
		    for (var i = (data.length)-1; i >= 0; i--) {
		      
		      patient = data[i];
		      id = patient.patientId;
		     //alert(id);
		      famphydetails=patient.firstName+" "+patient.lastName
		      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tablePatId" + id, id);
		      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
		      dwr.util.setValue("tableagegenderdetails" + id, agegender);
		      
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = patient;
		      //alert(peopleCache);
		      n=n+1;
		      }
		    //alert(n);
	         /*var previousInnerText = new String(); 
	         previousInnerText = document.getElementById('totalNoReg').innerText; 
	         previousInnerText = previousInnerText.concat(n);*/
		    if(fromDate == "" && toDate !="")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName+" "+to+" "+toDate;
			}
		    if(toDate == "" && fromDate != "")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName+" "+from+" "+fromDate;
			}
		    if(toDate != "" && fromDate != "")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName+" "+from+" "+fromDate+" "+to+" "+toDate;
			}
		    if(toDate == "" && fromDate == "")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName;
			}
		    document.getElementById("insertReqText").innerHTML =textData;
		  });
		
	}
	else 
	{
		bothSepLabSelected(enteredDateInvalid,fromTOInvalid,loginId,sepId,labId,fromDate,toDate,docName);
	}
	 }
  else
	 {
		  failureMessage(enteredDateInvalid);
		   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
			      return (tr.id != "pattern");
			    }});
		   document.getElementById("insertReqText").innerHTML="";
		   CaseManagement.showSpecalistLogin(loginId,function(data) {
				
				data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
				var doctor,docId,name;
					for(var i=0; i<data.length;i++)
					{
					doctor = data[i];	
					docId = doctor.doctorId;
					name = doctor.firstName+" "+doctor.lastName
					var e = document.getElementById('spceialist');
					if(i==0)
					   {
						  //alert("Before: "+e.length);
						   for(var k =1;k<e.length;k++)
						   {
							   e.remove(k);
							   //alert(k);
						   }
						  e = document.getElementById('spceialist');
						  //alert("After "+e.length);
						   
					    e.options[i] = new Option(selectSpecalist,"");
					    e.options[i+1] = new Option(name,docId+""+name);
					  //  alert(e.options[i]);
					  // alert(e.options[i+1]);
					   }
					   else
					   {
						  e.options[i+1] = new Option(name,docId+""+name);
						  //alert("inside else");
						// alert(e.options[i+1]);
					   }
					
					}
				});
	 }
	});
	
  }
  else
  {
	   failureMessage(fromTOInvalid);
	   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
	   document.getElementById("insertReqText").innerHTML="";
	   CaseManagement.showSpecalistLogin(loginId,function(data) {
			
			data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
			var doctor,docId,name;
				for(var i=0; i<data.length;i++)
				{
				doctor = data[i];	
				docId = doctor.doctorId;
				name = doctor.firstName+" "+doctor.lastName
				var e = document.getElementById('spceialist');
				if(i==0)
				   {
					  //alert("Before: "+e.length);
					   for(var k =1;k<e.length;k++)
					   {
						   e.remove(k);
						   //alert(k);
					   }
					  e = document.getElementById('spceialist');
					  //alert("After "+e.length);
					   
				    e.options[i] = new Option(selectSpecalist,"");
				    e.options[i+1] = new Option(name,docId+""+name);
				  //  alert(e.options[i]);
				  // alert(e.options[i+1]);
				   }
				   else
				   {
					  e.options[i+1] = new Option(name,docId+""+name);
					  //alert("inside else");
					// alert(e.options[i+1]);
				   }
				
				}
			});
  }
	});
}

function changeTypeLab(enteredDateInvalid,fromTOInvalid,loginId)
{
			
	var sepId ="";
	var docName= "";	
    var tempVal = document.getElementById('spceialist').value;
    if(tempVal != null && tempVal != "")
    {
    	var tempVal1 = tempVal.split("###");
    	sepId = tempVal1[0];
    	docName = tempVal1[1];
    	//alert(sepId);
    	//alert(docName);
    }
    var labId ="";
    var labName ="";
    var tempValLab = document.getElementById('lab').value;
    //alert(tempValLab);
    if(tempValLab != null && tempValLab != "")
    {
    	var tempValLab1 = tempValLab.split("###");
    	labId = tempValLab1[0];
    	labName = tempValLab1[1]
    	//alert(labId);
 	   // alert(labName);
    }
	var fromDate=document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var textData="";
	
	var labName = labId;
	document.getElementById("errorMessage").innerHTML=""
	StatisticsManagement.validateFromToDate(fromDate,toDate, function(data) {
		
		flagLab = data;
		
		if(fromDate == "" & toDate=="")
		{
			flagLab = true;
		}
  if(flagLab)
  {
	 //alert("one");
	var flag1 =  false;
	//alert(fromDate);
	//alert(toDate);
	
	
	StatisticsManagement.validateEnteredDate(fromDate,toDate, function(data) {
		   flag1 = data;
	 if(fromDate =="" && toDate =="")
	 {
		flag1 = true; 
	 }
  if(flag1)
	 {
	  //alert("two");
	if(sepId =="" && labId=="")
	{
		if(fromDate =="" && toDate =="")
		{
			loadPatReferredMonth(loginId);
		}
		else
		{
		StatisticsManagement.loadRegisteredMonthWise(loginId,fromDate,toDate, function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var patient,id,famphydetails,agegender;
		    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
		   var n=0;
		  
		    for (var i = (data.length)-1; i >= 0; i--) {
		      
		      patient = data[i];
		      id = patient.patientId;
		     
		      famphydetails=patient.firstName+" "+patient.lastName
		      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tablePatId" + id, id);
		      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
		      dwr.util.setValue("tableagegenderdetails" + id, agegender);
		      
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = patient;
		      //alert(peopleCache);
		      n=n+1;
		      }
		   
	         /*var previousInnerText = new String(); 
	         previousInnerText = document.getElementById('totalNoReg').innerText; 
	         previousInnerText = previousInnerText.concat(n);*/
		     document.getElementById("insertReqText").innerHTML =patReg+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
		  });
		}
	}
	else if(sepId !="" && labId=="")
	{
	  StatisticsManagement.loadPatReferredSep(loginId,sepId,fromDate,toDate, function(data) {
    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var patient,id,famphydetails,agegender;
	    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
	   var n=0;
	  
	    for (var i = (data.length)-1; i >= 0; i--) {
	      
	      patient = data[i];
	      id = patient.patientId;
	     
	      famphydetails=patient.firstName+" "+patient.lastName
	      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablePatId" + id, id);
	      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
	      dwr.util.setValue("tableagegenderdetails" + id, agegender);
	      
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = patient;
	      //alert(peopleCache);
	      n=n+1;
	      }
         /*var previousInnerText = new String(); 
         previousInnerText = document.getElementById('totalNoReg').innerText; 
         previousInnerText = previousInnerText.concat(n);*/
	    if(fromDate == "" && toDate !="")
		{
			textData=patRef+" "+n+" "+to+" "+docName+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate != "")
		{
			textData=patRef+" "+n+" "+to+" "+docName+" "+from+" "+fromDate;
		}
	    if(toDate != "" && fromDate != "")
		{
			textData=patRef+" "+n+" "+to+" "+docName+" "+from+" "+fromDate+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate == "")
		{
			textData=patRef+" "+n+" "+to+" "+docName;
		}
	    document.getElementById("insertReqText").innerHTML =textData;
	  });
	}
	else if(sepId == "" && labId !="")
	{
		//alert("hello");
		
		var orderedSize ="";
		 StatisticsManagement.loadTotalPatOrderedLab(loginId,labId,fromDate,toDate, function(data) {
				//document.getElementById("fromDate").value = data;
				orderedSize = data;
				 });
		
		
		StatisticsManagement.loadPatOrderedLab(loginId,labId,fromDate,toDate, function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var patient,id,famphydetails,agegender;
		    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
		   var n=0;
		  //alert("n value : "+n);
		    for (var i = (data.length)-1; i >= 0; i--) {
		      
		      patient = data[i];
		      id = patient.patientId;
		     //alert(id);
		      famphydetails=patient.firstName+" "+patient.lastName
		      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tablePatId" + id, id);
		      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
		      dwr.util.setValue("tableagegenderdetails" + id, agegender);
		      
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = patient;
		      //alert(peopleCache);
		      n=n+1;
		      }
		    //alert(n);
	         /*var previousInnerText = new String(); 
	         previousInnerText = document.getElementById('totalNoReg').innerText; 
	         previousInnerText = previousInnerText.concat(n);*/
		    if(fromDate == "" && toDate !="")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName+" "+to+" "+toDate;
			}
		    if(toDate == "" && fromDate != "")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName+" "+from+" "+fromDate;
			}
		    if(toDate != "" && fromDate != "")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName+" "+from+" "+fromDate+" "+to+" "+toDate;
			}
		    if(toDate == "" && fromDate == "")
			{
				textData=patOrdered+" "+orderedSize+" "+to+" "+labName;
			}
		    document.getElementById("insertReqText").innerHTML =textData;
		  });
		
	}
	else 
	{
		bothSepLabSelected(enteredDateInvalid,fromTOInvalid,loginId,sepId,labId,fromDate,toDate,docName);
	}
	 }
  else
	 {
		  failureMessage(enteredDateInvalid);
		   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
			      return (tr.id != "pattern");
			    }});
		   document.getElementById("insertReqText").innerHTML="";
		   CaseManagement.showSpecalistLogin(loginId,function(data) {
				
				data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
				var doctor,docId,name;
					for(var i=0; i<data.length;i++)
					{
					doctor = data[i];	
					docId = doctor.doctorId;
					name = doctor.firstName+" "+doctor.lastName
					var e = document.getElementById('spceialist');
					if(i==0)
					   {
						  //alert("Before: "+e.length);
						   for(var k =1;k<e.length;k++)
						   {
							   e.remove(k);
							   //alert(k);
						   }
						  e = document.getElementById('spceialist');
						  //alert("After "+e.length);
						   
					    e.options[i] = new Option(selectSpecalist,"");
					    e.options[i+1] = new Option(name,docId+""+name);
					  //  alert(e.options[i]);
					  // alert(e.options[i+1]);
					   }
					   else
					   {
						  e.options[i+1] = new Option(name,docId+""+name);
						  //alert("inside else");
						// alert(e.options[i+1]);
					   }
					
					}
				});
	 }
	});
	
  }
  else
  {
	   failureMessage(fromTOInvalid);
	   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
	   document.getElementById("insertReqText").innerHTML="";
	   CaseManagement.showSpecalistLogin(loginId,function(data) {
			
			data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
			var doctor,docId,name;
				for(var i=0; i<data.length;i++)
				{
				doctor = data[i];	
				docId = doctor.doctorId;
				name = doctor.firstName+" "+doctor.lastName
				var e = document.getElementById('spceialist');
				if(i==0)
				   {
					  //alert("Before: "+e.length);
					   for(var k =1;k<e.length;k++)
					   {
						   e.remove(k);
						   //alert(k);
					   }
					  e = document.getElementById('spceialist');
					  //alert("After "+e.length);
					   
				    e.options[i] = new Option(selectSpecalist,"");
				    e.options[i+1] = new Option(name,docId+""+name);
				  //  alert(e.options[i]);
				  // alert(e.options[i+1]);
				   }
				   else
				   {
					  e.options[i+1] = new Option(name,docId+""+name);
					  //alert("inside else");
					// alert(e.options[i+1]);
				   }
				
				}
			});
  }
	});
}

function bothSepLabSelected(enteredDateInvalid,fromTOInvalid,loginId,sepId,labId,fromDate,toDate,docNameG)
{
	docName = docNameG;
	labName = labId;
	StatisticsManagement.loadPatReferredOrdered(loginId,sepId,labId,fromDate,toDate, function(data) {
    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var patient,id,famphydetails,agegender;
	    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
	   var n=0;
	  
	    for (var i = (data.length)-1; i >= 0; i--) {
	      
	      patient = data[i];
	      id = patient.patientId;
	     
	      famphydetails=patient.firstName+" "+patient.lastName
	      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablePatId" + id, id);
	      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
	      dwr.util.setValue("tableagegenderdetails" + id, agegender);
	      
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = patient;
	      //alert(peopleCache);
	      n=n+1;
	      }
         /*var previousInnerText = new String(); 
         previousInnerText = document.getElementById('totalNoReg').innerText; 
         previousInnerText = previousInnerText.concat(n);*/
	    if(fromDate == "" && toDate !="")
		{
	    	patordref1 = patordref1G
	        patordref2 = patordref2G
	        patis =  patisG;
			textData=patordref1+" "+docName+" "+patordref2+" "+labName+" "+to+" "+toDate+" "+patis+" "+n;
		}
	    if(toDate == "" && fromDate != "")
		{
	    	textData=patordref1+" "+docName+" "+patordref2+" "+labName+" "+from+" "+fromDate+" "+patis+" "+n;
		}
	    if(toDate != "" && fromDate != "")
		{
	    	textData=patordref1+" "+docName+" "+patordref2+" "+labName+" "+from+" "+fromDate+" "+to+" "+toDate+" "+patis+" "+n;
			
		}
	    if(toDate == "" && fromDate == "")
		{
	    	textData=patordref1+" "+docName+" "+patordref2+" "+labName+" "+patis+" "+n;
		}
	    document.getElementById("insertReqText").innerHTML =textData;
	  });
}
