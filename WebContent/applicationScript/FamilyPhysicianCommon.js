var status="0";
var a;
var e=0;
var patientIdwr;
var viewed ="newfamilyPhy";
var peopleCache = { };
var patientname="noSearch";
var corrfield=0,err=0;
var tempPatientId="";
var mynetwork;
var newreq;
var viewEmrMsg;
var youhave 
var opinions;
var andp;
var labReport;

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}



function init(labReportc,youhavec,andc,opinionsc,viewEmrMsgc,mynetworkc,newreqc,loginses,search) {
	
	labReport=labReportc;
	andp = andc;
	youhave = youhavec
	mynetwork = mynetworkc;
	newreq = newreqc;
	opinions = opinionsc
	viewEmrMsg = viewEmrMsgc;
	  document.getElementById("fpMenu").style.background="#f7941d"; 
	  document.getElementById("listPat").className="fontBold";
	  document.getElementById("docMain").className="fontBold";
	  loadFamilyPhysician(loginses,search);
	  document.getElementById("errorMessage").innerHTML=""
     
	  //document.getElementById("ageYears1").innerHTML ="";
	  document.getElementById("photo").Value="";
	 //target_upload.document.getElementById("photoNameId").value="";
	}


dwr.engine.setWarningHandler(null);

dwr.engine.setErrorHandler(errorHandler);  

function errorHandler(message, exception){  
	
	//alert("errorhandler"+exception.javaClassName+"   "+message);
    //Session timedout/invalidated  
    if(exception != null){  
        //Reload or display an error etc.  
        //document.location.reload();  
    	alert(exception);
    	document.location = 'Logout.htm'
    	//alert(exception);
    }  
}  


function validatePatientIdInputField(element)
{
  var id = element.id;
  var value = element.value;
  FamilyPhyManagementValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
	      setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validatePatientIdYearsInputField(element,ageRequired)
{
  var id = element.id;
  var value = element.value;
  var years=document.familyPhyMain.ageYearsN.value;
  var months=document.familyPhyMain.ageMonthsN.value;
  var flag=true;
  if(months.trim() == '' && years.trim()=='')
  {	  
	  for(i=0 ; i<tempArr.length ; i++)
		  {
			for( j=0;j<tempArr.length;j++)
			{
				if(tempArr[i][j] == ageRequired)
				{
					flag = false;
					break;
				}
			} 
           }
			if(flag)
			{
		  errorMessage(ageRequired,id);
		  document.getElementById(id).className ="errorField"
		  document.getElementById("ageYears").className ="errorField" 	  
			}
	 // document.familyPhyMain.ageYearsN.style.backgroundColor="#F00";
    //  document.familyPhyMain.ageYearsN.style.color="#FFF";
    //  document.familyPhyMain.ageMonthsN.style.backgroundColor="#F00";
    //  document.familyPhyMain.ageMonthsN.style.color="#FFF";
	  
		 
  }else
  {
	  //alert("hi");
	 // correctMessage("noerror",id);
	 // document.familyPhyMain.ageYearsN.style.backgroundColor="#FFF";
     // document.familyPhyMain.ageYearsN.style.color="#000";
    //  document.familyPhyMain.ageMonthsN.style.backgroundColor="#FFF";
    //  document.familyPhyMain.ageMonthsN.style.color="#000";	  
       FamilyPhyManagementValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
	      setInputFieldStatus(element.id, dataFromServer);
               }
  });
  }
}



function validatePatientIdInputFieldFull(id,value,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors)
{
	  var idf = id;
	  var valuef = value;
	 
	  
	  FamilyPhyManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
	                  
	                  }
	  });
	  
}



function validatePatientIdYearsInputFieldFull(id,value,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors)
{
	  var idf = id;
	  var valuef = value;
	  var years=document.familyPhyMain.ageYearsN.value;
	  var months=document.familyPhyMain.ageMonthsN.value;
	  var tempVal =document.getElementById("errorMessage").innerHTML;
	//  alert(tempVal);
	   if(months.trim() =='' || years.trim()=='')
	   {
		   correctMessage("noerror","ageMonths");
	   }
	  FamilyPhyManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
	         }
	  });
	  
	  
}

function validatePatientIdMonthsInputFieldFull(id,value,patient,loginId,viewEmr,ageRequired,updated,notupdated,addednew,notaddednew,correcterrors)
{
	  var idf = id;
	  var valuef = value;
	  var years=document.familyPhyMain.ageYearsN.value;
	  var months=document.familyPhyMain.ageMonthsN.value;
	  var tempVal =document.getElementById("errorMessage").innerHTML;
	  var flag = true;
	  //alert(tempVal);
	  if(months.trim() ==''&& years.trim()=='')
	  {
		  for(i=0 ; i<tempArr.length ; i++)
		  {
			for( j=0;j<tempArr.length;j++)
			{
				if(tempArr[i][j] == ageRequired)
				{
					flag = false;
					break;
				}
			} 
           }
			if(flag)
			{
		  errorMessage(ageRequired,id);
		  document.getElementById(id).className ="errorField"
	      document.getElementById("ageYears").className ="errorField"	  
			}
	  }
	  else
	  {
		  FamilyPhyManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
	         }
	  });
		  
	  }
}

function setInputFieldStatusfull(elementId, message,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors)
{
	//alert("err: "+err);
	//alert("corrfield: "+corrfield);
//alert(document.getElementById("errorMessage").value);
	 var tempvab = message
var flag = true;
  err=err+1;
   if(message == "noerror")
   {
	   corrfield=corrfield+1;
       correctMessage(message,elementId);
       message="";
      
   }	
   var id =elementId+"1";
  // document.getElementById(id).innerHTML = message;
//alert(message);
 if(tempvab != "noerror")
   {
    //show('errorMessage');
for(i=0 ; i<tempArr.length ; i++)
	  {
		for( j=0;j<tempArr.length;j++)
		{
			if(tempArr[i][j] == message)
			{
				flag = false;
				break;
			}
		}
				
	}
	  if(flag)
	  {	
	   errorMessage(message,elementId);
	   document.getElementById(elementId).className ="errorField";
   }
     //errorMessage(message,elementId);
   //document.getElementById("errorMessage").innerHTML =  document.getElementById("errorMessage").innerHTML +" | "+ message;
   }
 
   if(viewed=="viewed")
	  {
	   if(!(tempPatientId==patient.patientId))
		  {
		  if(corrfield==8 && err==8)
			{
			  dwr.engine.beginBatch();
	 
	  FamilyPhysicianManagement.editeachPatient(patient, function(data) {
       setEditStatus(data,patient,loginId,viewEmr,updated,notupdated)
	      });
	  loadFamilyPhysician(loginId,'no');
	  dwr.engine.endBatch();
			}
		  }
		  else
		  {
			 
			  if(corrfield==8 && err==8)
				{
				  dwr.engine.beginBatch();
				  FamilyPhysicianManagement.editeachPatient(patient, function(data) {
			          setEditStatus(data,patient,loginId,viewEmr,updated,notupdated)
				      });
		  loadFamilyPhysician(loginId,'no');
		  dwr.engine.endBatch();
				}
			  else if(err==8)
			  {
				 // alert(notupdated);;
				  failureMessage(notupdated);
			  }
		  }
	  }
	  else
	  {

	  if(corrfield==8 && err==8)
			{
		      correctMessage(correcterrors,"correcterror");
			  dwr.engine.beginBatch();
		      FamilyPhysicianManagement.savePatient(patient,loginId, function(data) {
		    	  
               setSaveStatus(data,patient,loginId,viewEmr,addednew,notaddednew)
			      });
		      loadFamilyPhysician(loginId,'no');
		      dwr.engine.endBatch();
			}
		  else if(err==8)
		  {
			  //alert("Not Added Correct The Errors...");
			  errorMessage(correcterrors,"correcterror");
		  }
		  
	  }

   
}



function loadFamilyPhysician(loginses,search) {
	
	viewed ="newfamilyPhy";
	var h=0,h1=0;
	document.getElementById("listPat").className="fontBold";
	document.getElementById("listAP").className="fontNormal";
	
	document.familyPhyMain.patientNameS.value="";
	document.getElementById("fileupload_progress").innerHTML="";
    document.getElementById("progressBar").style.display = "none";
    document.getElementById("progressBarBoxContent").style.width =0;
   
	document.PhotoUpload.cancelUpload.disabled = true;
	var loginId=loginses;
	//document.familyPhyMain.patientIdN.disabled = false;
	
	if(document.getElementById("myNetworkNew") != null)
	{
	FamilyPhysicianManagement.newRequest(loginId,function(data) {
		  	if(data > 0)
	    	{
		document.getElementById("myNetworkNew").innerHTML = mynetwork +" ("+data+" "+newreq +" )"
	    	}
	    	else
	    	{
	    		document.getElementById("myNetworkNew").innerHTML = mynetwork
	    	}
	    	
	    });
	}
	
    dwr.util.setValues({patientId:null, creator:null, gender:null, ageYears:null, ageMonths:null, maritalStatus:null, bloodGroup:null, occupation:null, firstName:null, lastName:null,photo:null,  contactDetails:{email:null,address:null,contactNumber:null}});
	      FamilyPhysicianManagement.loadFamilyPhysicians(loginId,search,function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var patient,id,famphydetails,contactdetails,agegender;
	    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
	    if(data.length == 0)
	    {
	    	hide('ListTable');
	    	show('CreateLink');
	    }
	    for (var i = (data.length)-1; i >= 0; i--) {
	      patient = data[i];
	      //alert(patient.caseRefList);
	      id = patient.patientId;
	      famphydetails=patient.firstName+" "+patient.lastName
	      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
	      contactdetails=patient.contactDetails.address+"\n"+patient.contactDetails.contactNumber+"\n"+patient.contactDetails.email
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablePatId" + id, id);
	      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
	      dwr.util.setValue("tableagegenderdetails" + id, agegender);
	      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
	      if(patient.caseRefList != null)
	      {
	      if(patient.caseRefList.length > 0)
	      {
	    	  for(var g = 0; g < patient.caseRefList.length; g=g+1)
	    	  {
	    		  caseRef= patient.caseRefList[g];
	    		  if(caseRef.flag == "NOTREADOPI")
	    		  {
	    			  h = h+1;
	    			  document.getElementById("pattern"+id).className="b";
	    		  }
	    	  }
		  }    
	      }
	      if(patient.labTestRep != null)
	      {
	    	  if(patient.labTestRep.length > 0)
		      {
	    		  for(var g1 = 0; g1 < patient.labTestRep.length; g1=g1+1)
		    	  {
		    		  labTest= patient.labTestRep[g1];
		    		  if(labTest.flag == "NOTREADREP")
		    		  {
		    			  h1 = h1+1;
		    			  document.getElementById("pattern"+id).className="b";
		    		  }
		    	  }
		      }
	      }
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = patient;
	      if(h > 0 || h1 > 0)
	      {
	      document.getElementById ("newOpiLab").className = 'showDiv newOpiLab';
	      if(h > 0 && h1 > 0)
	      {
	  	  document.getElementById("newOpiLab").innerHTML = youhave+" "+ h +" "+opinions+" " +andp+" "+h1+" "+labReport;
	      }
	      else if(h > 0 && h1 <= 0)
	      {
	    	  document.getElementById("newOpiLab").innerHTML = youhave+" "+ h +"  "+opinions+".";
	      }
	      else if(h <= 0 && h1 > 0)
	      {
	    	  document.getElementById("newOpiLab").innerHTML = youhave+" "+ h1 +" "+labReport;
	      }
	  	  setTimeout ("hide('newOpiLab')", 4000) 
	      }
	    }
	  });
	}

function searchOnKeyPatient(loginId) {
	var patientName=document.familyPhyMain.patientNameS.value;
	FamilyPhysicianManagement.searchPatientdwr(patientName,loginId, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("patientbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
  // Create a new set cloned from the pattern row
  var patient,id,famphydetails,contactdetails,login;
  
 
  //data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
  
 
  for (var i = 0; i < data.length; i=i+2) {
	
  	patient = data[i];
  	var flag = data[i+1]
  	                
    if(patient != null)
    {
    document.getElementById("view").disabled = flag;	
    if(flag == false)
    {
    	document.getElementById("view").className = "newcase";
    }
    else
    {
    	document.getElementById("view").className = "newcasegrey";
    }
    id = patient.patientId;
    famphydetails=patient.firstName+" "+patient.lastName
    agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
    contactdetails=patient.contactDetails.address+"\n"+patient.contactDetails.contactNumber+"\n"+patient.contactDetails.email
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tablePatId" + id, id);
    dwr.util.setValue("tablefamphydetails" + id, famphydetails);
    dwr.util.setValue("tableagegenderdetails" + id, agegender);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    $("pattern" + id).style.display = "";
    peopleCache[id] = patient;
    
    //alert(flag);
    
    }
  }
});
}

function searchOnKeyPatientNo(loginId) {
	var patientName=document.familyPhyMain.patientNameS.value;
	var phoneNo = document.familyPhyMain.patientNoS.value;

	FamilyPhysicianManagement.searchPatientdwrNo(patientName,phoneNo,loginId, function(data) {
   
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("patientbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
  // Create a new set cloned from the pattern row
  var patient,id,famphydetails,contactdetails,login;
  
 
  //data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });

  for (var i = 0; i < data.length; i=i+2) {
	
  	patient = data[i];
  	var flag = data[i+1]
  	                
    if(patient != null)
    {
    document.getElementById("view").disabled = flag;	
    if(flag == false)
    {
    	document.getElementById("view").className = "newcase";
    }
    else
    {
    	document.getElementById("view").className = "newcasegrey";
    }
    id = patient.patientId;
    famphydetails=patient.firstName+" "+patient.lastName
    agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
    contactdetails=patient.contactDetails.address+"\n"+patient.contactDetails.contactNumber+"\n"+patient.contactDetails.email
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tablePatId" + id, id);
    dwr.util.setValue("tablefamphydetails" + id, famphydetails);
    dwr.util.setValue("tableagegenderdetails" + id, agegender);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    $("pattern" + id).style.display = "";
    peopleCache[id] = patient;
    
    //alert(flag);
    
    }
  }
});
}

function editClicked(eleid) {
 	  // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
 	 // alert(eleid);
 	  var patient = peopleCache[eleid.substring(4)];
 	//  alert(patient);
 	  viewed ="viewed"
 	  tempPatientId=	patient.patientId; 
 	document.PhotoUpload.cancelUpload.disabled =false;
 	// document.familyPhyMain.patientIdN.disabled = true;
 	 dwr.util.setValues(patient);
 	}

 function deleteClicked(eleid,loginId,deleted,notDeleted,datadelete,question) {
	  // we were an id of the form "delete{id}", eg "delete42". We lookup the "42"
	  var patient = peopleCache[eleid.substring(6)];
	  var name=patient.firstName+" "+patient.lastName
	  if (confirm(datadelete + name+ question)){
		dwr.engine.beginBatch();
  	   
  	    FamilyPhysicianManagement.deletePatient(patient, function(data) {
          setDeleteStatus(data,deleted,notDeleted,datadelete,question)
	      });
  	    loadFamilyPhysician(loginId,'no');
	    dwr.engine.endBatch();
	  }
	}

 function emrViewClicked(eleid,loginId) {
	   // alert(eleid);
		 testFunction()
		 var patient = peopleCache[eleid.substring(7)];
		
		 if(patient.caseRefList != null)
		 {
		 for(var t=0;t<patient.caseRefList.length;t++)
		 {
			 caseRef = patient.caseRefList[t];	 
		 dwr.engine.beginBatch();
		 FamilyPhysicianManagement.readPatientOpinion(caseRef.referralId,function(data) {
				
		      });
		 dwr.engine.endBatch();
		 }
		 }
		
		 if(patient.labTestRep != null)
		 {
			 for(var t1=0;t1<patient.labTestRep.length;t1++)
			 {
				 labTestRep = patient.labTestRep[t1];	 
			 dwr.engine.beginBatch();
			 FamilyPhysicianManagement.readPatientReport(labTestRep.labTestId,function(data) {
					
			      });
			 dwr.engine.endBatch();
			 }
		 }
		 //alert(patient);
		 var dType="fp";
		 var emr ="viewEMR"
		 var caseTitle="no";
		 document.location = "ViewEmr.htm?docType="+dType+"&patientName="+patient.firstName+"&patientId="+patient.patientId+"&docId="+loginId+"&caseTitle="+caseTitle+"&emr="+emr+"";
		 
	 }
 
 function emrClicked(eleid,loginId) {
   // alert(eleid);
	 testFunction()
	 var patient = peopleCache[eleid.substring(4)];
	// alert(patient);
	 var dType="fp";
	 var caseTitle="yes";
	 var emr ="neweditEMR"
	 document.location = "ViewEmr.htm?docType="+dType+"&patientName="+patient.firstName+"&patientId="+patient.patientId+"&docId="+loginId+"&caseTitle="+caseTitle+"&emr="+emr+"";
	 
 }
 
 function updatePatient(loginId,viewEmr,ageRequired,updated,notupdated,addednew,notaddednew,correcterrors) {
     //alert("hi");
	 corrfield=0;
     err=0;
     //document.getElementById("errorMessage").innerHTML ="";
     
	 var patient = {patientId:null, creator:null, gender:null, ageYears:null, ageMonths:null,maritalStatus:null, bloodGroup:null, occupation:null, firstName:null, lastName:null,photo:null,  contactDetails:{email:null,address:null,contactNumber:null}};
	 dwr.util.getValues(patient);
	 
	 if(viewed=="viewed")
	  {
		  
		  if(!(tempPatientId==patient.patientId))
		  {
			  
			  //validatePatientIdInputFieldFull("patientId",patient.patientId,patient,loginId,viewEmr);
		  }
		 
		  
	  }
	  else
	  {
		 // validatePatientIdInputFieldFull("patientId",patient.patientId,patient,loginId,viewEmr);
	  }

	    validatePatientIdInputFieldFull("firstName",patient.firstName,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
	    validatePatientIdInputFieldFull("lastName",patient.lastName,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
        validatePatientIdInputFieldFull("gender",patient.gender,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
        validatePatientIdInputFieldFull("occupation",patient.occupation,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
	    validatePatientIdInputFieldFull("contactDetails.contactNumber",patient.contactDetails.contactNumber,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
	    validatePatientIdInputFieldFull("contactDetails.email",patient.contactDetails.email,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
	    validatePatientIdYearsInputFieldFull("ageYears",patient.ageYears,patient,loginId,viewEmr,updated,notupdated,addednew,notaddednew,correcterrors);
        validatePatientIdMonthsInputFieldFull("ageMonths",patient.ageMonths,patient,loginId,viewEmr,ageRequired,updated,notupdated,addednew,notaddednew,correcterrors);
	   // alert("Action in progress");
	   
	}

 function setPatientId(data1,loginId,patient)
 {
	// alert(data1);
	 patientIdwr = data1;
	// alert(patientIdwr);
	// alert("patientId :"+patientIdwr)
	 var answer = confirm(viewEmrMsg)
     //alert(answer); 
	 
	// if(viewEmr == 'no')
	if(!answer)
      {
      loadFamilyPhysician(loginId,'no');
      }
      else
      {
	      //alert("yes");
    	  var dType="fp";
    	  var emr ="neweditEMR"
          var caseTitle="yes";	  
	      document.location = "ViewEmr.htm?docType="+dType+"&patientName="+patient.firstName+"&patientId="+patientIdwr+"&docId="+loginId+"&emr="+emr+"&caseTitle="+caseTitle+"";
      }
 }
 
function setSaveStatus(data,patient,loginId,viewEmr,addednew,notaddednew)
{
	if(data)
	{
		 //alert("patient.firstName: "+patient.firstName + patient.patientId);
		 FamilyPhysicianManagement.getPatientIdd( function(data1) {  	
			   
			 setPatientId(data1,loginId,patient);
		  
		    }
		  );
		
		 
	      
	}
	else
	{
		 //alert("New Record Not Added");
		 failureMessage(notaddednew);
	}
}

function setEditStatus(data,patient,loginId,viewEmr,updated,notupdated)
{
	if(data)
	{
		
		//alert("Values Updated");
		show('ListTable'); 
		hide('AddTable');
		successMessage(updated);
	//	 var answer = confirm("Values Updated.\n\nClick Ok to View EMR.\nCancel To Add New Patient.")
	//	if(!answer)
	  //    {
	   //   loadFamilyPhysician(loginId);
	   //   }
	   //   else
	   //   {
		      //alert("yes");
		    //  document.location = "ViewEmr.htm?patientName="+patient.firstName+"&patientId="+patient.patientId+"&docId="+loginId+"";
	     // }
	      
	}
	else
	{
		failureMessage(notupdated);
	}
}

function setDeleteStatus(data,deleted,notDeleted,datadelete,question)
{
	if(data)
	{
		//alert("Deleted Successfully...");
		successMessage(deleted);
	}
	else
	{
		//alert("Not Deleted Case Is Open.");
		failureMessage(notDeleted);
	}
}


function clearPatient(datapath,photopath) {
	  viewed = "newfamilyPhy";
	  //document.familyPhyMain.patientIdN.disabled = false;
	  document.getElementById("listPat").className="fontNormal";
	  document.getElementById("listAP").className="fontBold";
	  document.familyPhyMain.patientNameS.value="";
	  document.PhotoUpload.cancelUpload.disabled = true; 
	  var path=datapath+"/"+photopath;
	  var fileName=document.familyPhyMain.chkPhotoName.value;
	  var fpath=path+"/"+fileName;
	  if(viewed !="viewed")
	  {		  
	  UploadInfoBean.cancelPhoto(fpath);
	  }
	  viewed = "newdoctor";
	  UploadInfoBean.setPercentage("0");
	 
	  document.getElementById("fileupload_progress").innerHTML="";
	  document.getElementById("progressBar").style.display = "none";
	  document.getElementById("progressBarBoxContent").style.width =0;
	  removeErrorMsg();
	  dwr.util.setValues({patientId:null, creator:null, gender:null, ageYears:null, ageMonths:null,maritalStatus:null, bloodGroup:null, occupation:null, firstName:null, lastName:null,photo:null,  contactDetails:{email:null,address:null,contactNumber:null}});
	}

function removeError()
{
	//alert(document.familyPhyMain.genderN.value);
   if(!document.familyPhyMain.genderN.value=="")
   {
   //document.getElementById("gender1").innerHTML="";
   }
}

function removeErrorMsg()
{
	//alert("length: "+tempArr.length);
	for(s=0;s<tempArr.length;s++)
	{
		for(t=0;t<tempArr.length;t++)
		{
			//alert("first : "+tempArr[s][t]);
			tempArr[s][t] ="";
			//alert("second :  "+tempArr[s][t]);
		}
	}
	
	document.getElementById("errorMessage").innerHTML=""
	hide('errorMessage');	
	document.getElementById("patientId").className ="noerrorField"
	document.getElementById("firstName").className ="noerrorField"
	document.getElementById("lastName").className ="noerrorField"
	document.getElementById("gender").className ="noerrorField"
	document.getElementById("ageYears").className ="noerrorField"
	document.getElementById("ageMonths").className ="noerrorField"
	document.getElementById("occupation").className ="noerrorField"
	document.getElementById("contactDetails.address").className ="noerrorField"
	document.getElementById("contactDetails.contactNumber").className ="noerrorField"
	document.getElementById("photo").className ="noerrorField"
	document.getElementById("contactDetails.address").className ="noerrorField"
	document.getElementById("contactDetails.email").className ="noerrorField"
	document.getElementById("photoUpload").className ="noerrorField"
	
}


function validateDoctorIdInputFieldPhoto(id,value)
{
	  var idf = id;
	  var valuef = value;
	 
	  DoctorManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusPhoto(idf, dataFromServer);
	                  
	                  }
	  });
	 
}

 
   function cancelPhotoo(datapath,photopath)
  {
	
	  var path=datapath+"/"+photopath;
	 // alert(path);
     document.getElementById("fileupload_progress").innerHTML="";
    document.getElementById("progressBar").style.display = "none";
    document.getElementById("progressBarBoxContent").style.width =0;
      var fileName=document.familyPhyMain.chkPhotoName.value;
      var fpath=path+"/"+fileName;
     //alert(fpath);
      UploadInfoBean.cancelPhoto(fpath);
      UploadInfoBean.setPercentage("0");
      document.familyPhyMain.chkPhotoName.value="";
     // parent.setName(document.PhotoUpload.photoName.value);
  }



