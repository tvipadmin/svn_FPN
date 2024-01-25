
var peopleCache = { };
var selectFP;
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

function init(loginId,selectFpG,patRegG,fromG,toG,byG,patRefG,patOrderedG,patordref1G,patordref2G,patisG)
{
	//alert(loginId);
	//alert("GG");
	selectFP =selectFpG;
	
	patReg = patRegG;
	patRef = patRefG
    from = fromG;
    to = toG;
    patOrdered = patOrderedG;
    patordref1 = patordref1G
    patordref2 = patordref2G
    patis =  patisG;
    by = byG;
   
  	document.getElementById("sepMenuStatistics").style.background="#f7941d"; 
  	if(document.getElementById("sepStatisticsMenu") != null)
  	{
  		document.getElementById("sepStatisticsMenu").style.background="#f7941d"; 
  	}
        
	loadPatReferredMonth(loginId);
}


function clickOK(enteredDateInvalid,fromTOInvalid,loginId,selectFPG,patRegG,fromG,toG,patRefG)
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
	selectFP=selectFPG;
	patReg = patRegG;
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
  			loadPatReferredMonth(loginId);
  		}
  		else
  		{
  		StatisticsManagement.loadPatReferredByFP(loginId,fpId,fromDate,toDate, function(data) {
  	    	
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
  		     document.getElementById("insertReqText").innerHTML =patRef+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
  		  });
  		}
  	}
  	else 
  	{
  	  StatisticsManagement.loadPatReferredByFP(loginId,fpId,fromDate,toDate, function(data) {
      	
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
  			textData=patRef+" "+n+" "+by+" "+docName+" "+to+" "+toDate;
  		}
  	    if(toDate == "" && fromDate != "")
  		{
  			textData=patRef+" "+n+" "+by+" "+docName+" "+from+" "+fromDate;
  		}
  	    if(toDate != "" && fromDate != "")
  		{
  			textData=patRef+" "+n+" "+by+" "+docName+" "+from+" "+fromDate+" "+to+" "+toDate;
  		}
  	    if(toDate == "" && fromDate == "")
  		{
  			textData=patRef+" "+n+" "+by+" "+docName;
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
  		   CaseManagement.showFPLogin(loginId,function(data) {
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
  	   CaseManagement.showFPLogin(loginId,function(data) {
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



function loadPatReferredMonth(loginId)
{
	//alert("hi");
	var fromDate = document.getElementById('fromDate').value;
	var toDate= document.getElementById('toDate').value;
	//alert("hello");
		
	CaseManagement.showFPLogin(loginId,function(data) {
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
	
		StatisticsManagement.getCurrentDateS(function(data) {
			//document.getElementById("toDate").value = data;
			toDate = data;
		});
		
		StatisticsManagement.getOneMonthBackDateS(function(data) {
			//document.getElementById("fromDate").value = data;
			fromDate = data;
			
			 });
		
	StatisticsManagement.loadFPPatReferredMonthWise(loginId, function(data) {
    	
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
	       document.getElementById("insertReqText").innerHTML =patRef+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
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
			 //alert(patient.doctor.doctorId);
			 var dType="specalist";
			 document.location = "ViewEmrStatistics.htm?docType="+dType+"&patientId="+patient.patientId+"&docId="+loginId+"";
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
			loadPatReferredMonth(loginId);
		}
		else
		{
		StatisticsManagement.loadPatReferredByFP(loginId,fpId,fromDate,toDate, function(data) {
	    	
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
		     document.getElementById("insertReqText").innerHTML =patRef+" "+n+" "+from+" "+fromDate+" "+to+" "+toDate;
		  });
		}
	}
	else 
	{
	  StatisticsManagement.loadPatReferredByFP(loginId,fpId,fromDate,toDate, function(data) {
    	
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
			textData=patRef+" "+n+" "+by+" "+docName+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate != "")
		{
			textData=patRef+" "+n+" "+by+" "+docName+" "+from+" "+fromDate;
		}
	    if(toDate != "" && fromDate != "")
		{
			textData=patRef+" "+n+" "+by+" "+docName+" "+from+" "+fromDate+" "+to+" "+toDate;
		}
	    if(toDate == "" && fromDate == "")
		{
			textData=patRef+" "+n+" "+by+" "+docName;
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
		   CaseManagement.showFPLogin(loginId,function(data) {
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
	   CaseManagement.showFPLogin(loginId,function(data) {
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



