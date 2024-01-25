
var peopleCache = { };
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
var by;
var selectFP;

function init(loginId,selectFPG,patRegG,fromG,toG,byG,patRefG,patOrderedG,patordref1G,patordref2G,patisG)
{
	//alert(loginId);
	//alert("GG");
	selectFP =selectFPG;
	
	patReg = patRegG;
	patRef = patRefG
    from = fromG;
    to = toG;
    patOrdered = patOrderedG;
    patordref1 = patordref1G
    patordref2 = patordref2G
    patis =  patisG;
    by = byG;
  	document.getElementById("labMenuStatistics").style.background="#f7941d"; 
	loadPatTestOrderedMonth(loginId);
}


function clickOK(enteredDateInvalid,fromTOInvalid,loginId,selectFPG,fromG,toG,patOrderedG)
{
	var fromDate = document.getElementById('fromDate').value;
	var toDate= document.getElementById('toDate').value;
	var orderedSize = "0";
	document.getElementById("errorMessage").innerHTML=""
		
	StatisticsManagement.validateFromToDate(fromDate,toDate, function(data) {
		//document.getElementById("toDate").value = data;
		flag = data;
	
	if(fromDate == "" & toDate=="")
	{
		flag = true;
	}
	
		
	 patOrdered = patOrderedG;
	
	selectFP=selectFPG;
	
    from = fromG;
    to = toG;
    var fpId="";
    var docName="";
    var tempVal = document.getElementById('fp').value;
    if(tempVal != null && tempVal != "")
    {
    	var tempVal1 = tempVal.split("###");
    	fpId = tempVal1[0];
    	docName = tempVal1[1];
    	//alert(sepId);
    	//alert(docName);
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
  	if(fpId =="")
  	{
  		if(fromDate =="" && toDate =="")
  		{
  			loadPatTestOrderedMonth(loginId);
  		}
  		else
  		{
  			StatisticsManagement.loadTotalTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
  				//document.getElementById("fromDate").value = data;
  				orderedSize = data;
  				 });
  			
  		StatisticsManagement.loadPatTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
  			
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
  		     document.getElementById("insertReqText").innerHTML =patOrdered+" "+orderedSize+" "+from+" "+fromDate+" "+to+" "+toDate;
  		  });
  		}
  	}
  	else 
  	{

	  StatisticsManagement.loadTotalTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
				//document.getElementById("fromDate").value = data;
				orderedSize = data;
				 });
	  
  	  StatisticsManagement.loadPatTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
      	
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
  			textData=patOrdered+" "+orderedSize+" "+by+" "+docName+" "+to+" "+toDate;
  		}
  	    if(toDate == "" && fromDate != "")
  		{
  			textData=patOrdered+" "+orderedSize+" "+by+" "+docName+" "+from+" "+fromDate;
  		}
  	    if(toDate != "" && fromDate != "")
  		{
  			textData=patOrdered+" "+orderedSize+" "+by+" "+docName+" "+from+" "+fromDate+" "+to+" "+toDate;
  		}
  	    if(toDate == "" && fromDate == "")
  		{
  			textData=patOrdered+" "+orderedSize+" "+by+" "+docName;
  		}
  	    document.getElementById("insertReqText").innerHTML =textData;
  	  });
  	} 
  	
  	 }
    else
  	 {
  		  failureMessage(enteredDateInvalid);
  		   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
  			      return (tr.id != "pattern");
  			    }});
  		   document.getElementById("insertReqText").innerHTML="";
  		   CaseManagement.showLabLogin(loginId,function(data) {
  				//alert(data.length);
  			data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
  			var doctor,docId,name;
  				for(var i=0; i<data.length;i++)
  				{
  				doctor = data[i];	
  				docId = doctor.doctorId;
  				name = doctor.firstName+" "+doctor.lastName
  				var e = document.getElementById('fp');
  				if(i==0)
  				   {
  					  //alert("Before: "+e.length);
  					   for(var k =1;k<e.length;k++)
  					   {
  						   e.remove(k);
  						   //alert(k);
  					   }
  					  e = document.getElementById('fp');
  					  //alert("After "+e.length);
  					   
  				    e.options[i] = new Option(selectFP,"");
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
  	   CaseManagement.showLabLogin(loginId,function(data) {
  			//alert(data.length);
  		data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
  		var doctor,docId,name;
  			for(var i=0; i<data.length;i++)
  			{
  			doctor = data[i];	
  			docId = doctor.doctorId;
  			name = doctor.firstName+" "+doctor.lastName
  			var e = document.getElementById('fp');
  			if(i==0)
  			   {
  				  //alert("Before: "+e.length);
  				   for(var k =1;k<e.length;k++)
  				   {
  					   e.remove(k);
  					   //alert(k);
  				   }
  				  e = document.getElementById('fp');
  				  //alert("After "+e.length);
  				   
  			    e.options[i] = new Option(selectFP,"");
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
  		
    }
  	});
}



function loadPatTestOrderedMonth(loginId)
{
	//alert("hi");
	var fromDate = document.getElementById('fromDate').value;
	var toDate= document.getElementById('toDate').value; 
	var orderedSize = "0";
	//alert("hello");
		
	CaseManagement.showLabLogin(loginId,function(data) {
		//alert(data.length);
	data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
	var doctor,docId,name;
		for(var i=0; i<data.length;i++)
		{
		doctor = data[i];	
		docId = doctor.doctorId;
		name = doctor.firstName+" "+doctor.lastName
		var e = document.getElementById('fp');
		if(i==0)
		   {
			  //alert("Before: "+e.length);
			   for(var k =1;k<e.length;k++)
			   {
				   e.remove(k);
				   //alert(k);
			   }
			  e = document.getElementById('fp');
			  //alert("After "+e.length);
			   
		    e.options[i] = new Option(selectFP,"");
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
	
	
	var fpId="";
    var docName="";
    var tempVal = document.getElementById('fp').value;
    if(tempVal != null && tempVal != "")
    {
    	var tempVal1 = tempVal.split("###");
    	fpId = tempVal1[0];
    	docName = tempVal1[1];
    	//alert(sepId);
    	//alert(docName);
    }
	   
	if(fromDate=="" && toDate=="" && fpId =="")
	{
		StatisticsManagement.loadTotalTestOrderedMonthWise(loginId,function(data) {
			//document.getElementById("fromDate").value = data;
			orderedSize = data;
			 });
	
		StatisticsManagement.getCurrentDateS(function(data) {
			//document.getElementById("toDate").value = data;
			toDate = data;
		});
		
		StatisticsManagement.getOneMonthBackDateS(function(data) {
			//document.getElementById("fromDate").value = data;
			fromDate = data;
			
			 });
		
		
	StatisticsManagement.loadFPPatTestOrderedMonthWise(loginId, function(data) {
    	
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
	       document.getElementById("insertReqText").innerHTML =patOrdered+" "+orderedSize+" "+from+" "+fromDate+" "+to+" "+toDate;
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
		 //alert(patient);
		 var dType="fp";
		 var emr ="viewEMR"
		 var caseTitle="no";
		 document.location = "ViewEmr.htm?docType="+dType+"&patientName="+patient.firstName+"&patientId="+patient.patientId+"&docId="+loginId+"&caseTitle="+caseTitle+"&emr="+emr+"";
		 
	 }

function changeTypeFp(enteredDateInvalid,fromTOInvalid,loginId)
{
			
	var fpId="";
    var docName="";
    var tempVal = document.getElementById('fp').value;
    if(tempVal != null && tempVal != "")
    {
    	var tempVal1 = tempVal.split("###");
    	fpId = tempVal1[0];
    	docName = tempVal1[1];
    	//alert(sepId);
    	//alert(docName);
    }
	
	var fromDate=document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var textData="";
	
	var orderedSize = "0";
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
	if(fpId =="")
	{
		if(fromDate =="" && toDate =="")
		{
			loadPatTestOrderedMonth(loginId);
		}
		else
		{
			StatisticsManagement.loadTotalTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
				//document.getElementById("fromDate").value = data;
				orderedSize = data;
				 });
			
		StatisticsManagement.loadPatTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
	    	
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
		     document.getElementById("insertReqText").innerHTML =patOrdered+" "+orderedSize+" "+from+" "+fromDate+" "+to+" "+toDate;
		  });
		}
	}
	else 
	{
		StatisticsManagement.loadTotalTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
			//document.getElementById("fromDate").value = data;
			orderedSize = data;
			 });
		
	  StatisticsManagement.loadPatTestOrderedByFP(loginId,fpId,fromDate,toDate, function(data) {
    	
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
			textData=patOrdered+" "+orderedSize+" "+by+" "+docName+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate != "")
		{
			textData=patOrdered+" "+orderedSize+" "+by+" "+docName+" "+from+" "+fromDate;
		}
	    if(toDate != "" && fromDate != "")
		{
			textData=patOrdered+" "+orderedSize+" "+by+" "+docName+" "+from+" "+fromDate+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate == "")
		{
			textData=patOrdered+" "+orderedSize+" "+by+" "+docName;
		}
	    document.getElementById("insertReqText").innerHTML =textData;
	  });
	} 
	
	 }
  else
	 {
		  failureMessage(enteredDateInvalid);
		   dwr.util.removeAllRows("patientbody", { filter:function(tr) {
			      return (tr.id != "pattern");
			    }});
		   document.getElementById("insertReqText").innerHTML="";
		   CaseManagement.showLabLogin(loginId,function(data) {
				//alert(data.length);
			data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
			var doctor,docId,name;
				for(var i=0; i<data.length;i++)
				{
				doctor = data[i];	
				docId = doctor.doctorId;
				name = doctor.firstName+" "+doctor.lastName
				var e = document.getElementById('fp');
				if(i==0)
				   {
					  //alert("Before: "+e.length);
					   for(var k =1;k<e.length;k++)
					   {
						   e.remove(k);
						   //alert(k);
					   }
					  e = document.getElementById('fp');
					  //alert("After "+e.length);
					   
				    e.options[i] = new Option(selectFP,"");
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
	   CaseManagement.showLabLogin(loginId,function(data) {
			alert(data.length);
		data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
		var doctor,docId,name;
			for(var i=0; i<data.length;i++)
			{
			doctor = data[i];	
			docId = doctor.doctorId;
			name = doctor.firstName+" "+doctor.lastName
			var e = document.getElementById('fp');
			if(i==0)
			   {
				  //alert("Before: "+e.length);
				   for(var k =1;k<e.length;k++)
				   {
					   e.remove(k);
					   //alert(k);
				   }
				  e = document.getElementById('fp');
				  //alert("After "+e.length);
				   
			    e.options[i] = new Option(selectFP,"");
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
		
  }
	});
}



