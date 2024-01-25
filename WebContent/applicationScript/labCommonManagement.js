var viewed ="newLab";
var peopleCache = { };
var labname="noSearch";
var x=0;
var tempLabId="";
var  corrfield=0,err=0;
var status="0";
var enable;
var reject;
var disable;
var labReg;
var sureReset;
var succReset;
var notReset;

function init(succResetc,notResetc,sureResetc,labRegc,enablec,rejectc,disablec,datadelete,question) {
	  succReset = succResetc;
	  sureReset = sureResetc;
	  notReset  = notResetc;
	  labReg = labRegc;
	  enable = enablec;
	  disable = disablec;
	  reject = rejectc;
	  document.getElementById("labManMenu").style.background="#f7941d";
	  document.getElementById("listLAL").className="fontBold";
	  
	  loadLab(datadelete,question,'allType');
	  document.getElementById("errorMessage").innerHTML=""
	 // target_upload.document.getElementById("photoNameId").value="";
	}



dwr.engine.setWarningHandler(null);

dwr.engine.setErrorHandler(errorHandler);  

function errorHandler(message, exception){  
	
	alert("errorhandler"+exception.javaClassName+"   "+message);
    //Session timedout/invalidated  
    if(exception != null){  
        //Reload or display an error etc.  
        //document.location.reload();  
    	document.location = 'Logout.htm'
    }  
}  

function validateLabIdInputField(element)
{
  var id = element.id;
  var value = element.value;
  
  LabManagementValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
                  setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validateLabIdInputFieldConfirmPass(element)
{
  var id = element.id;
  var value = element.value;
  var tempval = document.labAdminMain.passwordN.value+"yt%y"+value;
  LabManagementValidator.getInputFieldValidationMessage(id, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validateLabIdInputFieldConfirmPassFull(id,value,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question)
{
  var idf = id;
  var valuef = value;
  var tempval = document.labAdminMain.passwordN.value+"yt%y"+valuef;
  LabManagementValidator.getInputFieldValidationMessage(idf, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatusfull(idf, dataFromServer,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
               }
  });
  
}

function validateLabIdInputFieldFull(id,value,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question)
{
	  var idf = id;
	  var valuef = value;
	  LabManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	                  }
	  });
	 
}




function setInputFieldStatusfull(elementId, message,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question)
{
  
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
	   if(tempvab != "noerror")
	   {
	   
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
	     }
	     
	   }

	  if(viewed=="viewed")
	  {
		 // alert("err: "+err);
		 // alert("corrfield "+corrfield);
		 		 
		  if(!(tempLabId==lab.labId))
		  {
		  if(err==5 && corrfield==5)
			{
			  dwr.engine.beginBatch();
	  
	  LabManagement.editeachLab(lab, function(data) {
          setEditStatus(data,updated,notupdated,datadelete,question)
	      });
	//  loadLab(datadelete,question,'allType');
	  dwr.engine.endBatch();
			}
		  }
		  else
		  {
			  if(err==4 && corrfield==4)
				{
				  dwr.engine.beginBatch();
				  LabManagement.editeachLab(lab, function(data) {
			          setEditStatus(data,updated,notupdated,datadelete,question)
				      });
		  //loadLab(datadelete,question,'allType');
		  dwr.engine.endBatch();
				}
			  else if(err==4)
			  {
				  alert(notupdated);
			  }
		  }
	  }
	  else
	  {
		 // alert(err)
		  if(corrfield==7 && err==7)
			{
		      correctMessage(correcterrors,"correcterror");
		     dwr.engine.beginBatch();
		     LabManagement.saveLab(lab, function(data) {
              setSaveStatus(data,addednew,notaddednew)
		      });
	      loadLab(datadelete,question,'allType');
		  dwr.engine.endBatch();
			}
		  else if(err==7)
		  {		
			  errorMessage(correcterrors,"correcterror");
		  }
		  
	  }
}

function showPendingListL(datadelete,question,searchType)
{
	
	  document.getElementById("listLP").className="fontBold";
	  document.getElementById("listLAL").className="fontNormal";
	  document.getElementById("listLD").className="fontNormal";
	  document.getElementById("listLE").className="fontNormal";
	  document.getElementById("listLA").className="fontNormal";
	
	
	document.labAdminMain.labNameS.value="";
	document.labAdminMain.searchTypeLab.value=searchType;
	
	 LabManagement.loaddwrLabsPending(function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("labbodyPED", { filter:function(tr) {
		      return (tr.id != "patternPED");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var lab,id,docdetails,contactdetails,status,nameD;
		     data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
		   
		   
		    for (var i = 0; i < data.length; i++) {
		    	
		      lab = data[i];
		     
		      id = lab.labId;
		      
		      docdetails=lab.labName+"\n"+lab.contactPerson
		      nameD = lab.labName
		      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		      status=lab.login.accountStatus;
		    
		      dwr.util.cloneNode("patternPED", { idSuffix:id });
		      dwr.util.setValue("tableDocIdPED" + id, id);
		      dwr.util.setValue("tableDocDetailsPED" + id, docdetails);
		      dwr.util.setValue("tableDocContactDetailsPED" + id, contactdetails);
		      dwr.util.setValue("tableDocStatusPED" + id, "");
		     		      
		      if(status == "PENDING")
		      {
		      document.getElementById("tableDocStatusPED" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegLab('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','PENDING');\" onclick=\"testFunction()\" >"+reject+"</a>";
		      }
		      if(lab.flag == "NOTREADLAB")
		      {
		    	   document.getElementById("patternPED"+id).className="b";
		      }
		      $("patternPED" + id).style.display = "";
		      peopleCache[id] = lab;
		    }
		  });
}

function showEnabledListL(datadelete,question,searchType)
{
	 document.getElementById("listLP").className="fontNormal";
	  document.getElementById("listLAL").className="fontNormal";
	  document.getElementById("listLD").className="fontNormal";
	  document.getElementById("listLE").className="fontBold";
	  document.getElementById("listLA").className="fontNormal";
	
	document.labAdminMain.labNameS.value="";
	document.labAdminMain.searchTypeLab.value=searchType;
	
	 LabManagement.loaddwrLabsEnabled(function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("labbodyPED", { filter:function(tr) {
		      return (tr.id != "patternPED");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var lab,id,docdetails,contactdetails,status;
		    data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
		   
		   
		    for (var i = 0; i < data.length; i++) {
		    	
		      lab = data[i];
		     
		      id = lab.labId;
		      
		      docdetails=lab.labName+"\n"+lab.contactPerson
		      
		      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		      status=lab.login.accountStatus;
		    
		      dwr.util.cloneNode("patternPED", { idSuffix:id });
		      dwr.util.setValue("tableDocIdPED" + id, id);
		      dwr.util.setValue("tableDocDetailsPED" + id, docdetails);
		      dwr.util.setValue("tableDocContactDetailsPED" + id, contactdetails);
		      dwr.util.setValue("tableDocStatusPED" + id, "");
		     
		      if(status == "ENABLED")
		      {
		      document.getElementById("tableDocStatusPED" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','DISABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
		      }
		      
		      $("patternPED" + id).style.display = "";
		      peopleCache[id] = lab;
		    }
		  });
}

function showDisabledListL(datadelete,question,searchType)
{
	 document.getElementById("listLP").className="fontNormal";
	  document.getElementById("listLAL").className="fontNormal";
	  document.getElementById("listLD").className="fontBold";
	  document.getElementById("listLE").className="fontNormal";
	  document.getElementById("listLA").className="fontNormal";
	
	document.labAdminMain.labNameS.value="";
	document.labAdminMain.searchTypeLab.value=searchType;
	
	 LabManagement.loaddwrLabsDisabled(function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("labbodyPED", { filter:function(tr) {
		      return (tr.id != "patternPED");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var lab,id,docdetails,contactdetails,status;
		    data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
		   
		   
		    for (var i = 0; i < data.length; i++) {
		    	
		      lab = data[i];
		     
		      id = lab.labId;
		      
		      docdetails=lab.labName+"\n"+lab.contactPerson
		      
		      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		      status=lab.login.accountStatus;
		    
		      dwr.util.cloneNode("patternPED", { idSuffix:id });
		      dwr.util.setValue("tableDocIdPED" + id, id);
		      dwr.util.setValue("tableDocDetailsPED" + id, docdetails);
		      dwr.util.setValue("tableDocContactDetailsPED" + id, contactdetails);
		      dwr.util.setValue("tableDocStatusPED" + id, "");
		     
		      
		      
		      if(status == "DISABLED")
		      {
		      document.getElementById("tableDocStatusPED" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
		      }
		      
		      $("patternPED" + id).style.display = "";
		      peopleCache[id] = lab;
		    }
		  });
}

function enableFullListLab()
{
	 document.getElementById("listLP").className="fontNormal";
	  document.getElementById("listLAL").className="fontBold";
	  document.getElementById("listLD").className="fontNormal";
	  document.getElementById("listLE").className="fontNormal";
	  document.getElementById("listLA").className="fontNormal";
}

function loadLab(datadelete,question,searchType) {
	
	show('searchTable');
	 document.getElementById("listLP").className="fontNormal";
	  document.getElementById("listLAL").className="fontBold";
	  document.getElementById("listLD").className="fontNormal";
	  document.getElementById("listLE").className="fontNormal";
	  document.getElementById("listLA").className="fontNormal";
	
	
	document.labAdminMain.labNameS.value="";
	document.labAdminMain.searchTypeLab.value=searchType;
	var hL =0;
	viewed ="newLab";
	document.getElementById("fileupload_progress").innerHTML="";
    document.getElementById("progressBar").style.display = "none";
    document.getElementById("progressBarBoxContent").style.width =0;
    document.getElementById("photo1").innerHTML="";
	document.PhotoUpload.cancelUpload.disabled = true;
	document.labAdminMain.labIdN.disabled = false;
	document.labAdminMain.passwordN.disabled = false;
 	document.labAdminMain.confirmPasswordN.disabled = false;
 	
	dwr.util.setValues({labId:null, labName:null,contactPerson:null,photo:null, contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	      LabManagement.loaddwrLabs(function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("labbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	     
	    // Create a new set cloned from the pattern row
	    var lab,id,docdetails,contactdetails,status,nameD;
	    data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
	   
	    
	    for (var i = 0; i < data.length; i++) {
	    	
	      lab = data[i];
	     
	      id = lab.labId;
	      
	      docdetails=lab.labName+"\n"+lab.contactPerson
	      
	      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
	      status=lab.login.accountStatus;
	      nameD = lab.labName
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tableDocId" + id, id);
	      dwr.util.setValue("tableDocDetails" + id, docdetails);
	      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
	      dwr.util.setValue("tableDocStatus" + id, "");
	     
	   
	      if(status == "ENABLED")
	      {
	      document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','DISABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
	      }
	      if(status == "DISABLED")
	      {
	      document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
	      }
	      if(status == "PENDING")
	      {
	      document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegLab('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','LOAD');\" onclick=\"testFunction()\" >"+reject+"</a>";
	      }
	    
	      if(lab.flag == "NOTREAD")
	      {
	    	 document.getElementById("pattern"+id).className="b";
	    	 hL=hL+1;
	      }
	      
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = lab;
	      if(hL > 0)
	      {
	      document.getElementById ("newReg").className = 'showDiv newReg';
	  	  document.getElementById("newReg").innerHTML = hL +" "+labReg;
	  	  setTimeout ("hide('newReg')", 4000) 
	      }
	    }
	  });
	}

function changeStatusLab(id,status,typeLab,datadelete,question)
{	
	LabManagement.changeStatusLabD(id,status, function(data) {
		
		if(data)
		{
			
			dwr.engine.beginBatch();
			LabManagement.sendConformationStatusLab(id,function(data) {
				
		      });
		  
		  dwr.engine.endBatch();
		}
		if(typeLab == "LOAD")
		{
	   loadLab(datadelete,question,'allType');
		}
	   if(typeLab == "ENABLED")
	   {
		   showEnabledListL(datadelete,question,'enabled');
	   }
	  if(typeLab == "DISABLED")
	   {
		  showDisabledListL(datadelete,question,'disabled')
	   }
	  if(typeLab == "PENDING")
	   {
		  showPendingListL(datadelete,question,'pending');
	   }
     });
}

function deleteSelfRegLab(id,datadelete,question,name,typeDoc)
{
	if (confirm(datadelete +" "+ name +" "+ question)){
	LabManagement.deleteSelfRegLab(id, function(data) {
		if(typeDoc == "LOAD")
		{
	      loadLab(datadelete,question,'allType');
		}
		if(typeDoc == "PENDING")
		{
		showPendingListL(datadelete,question,'pending');	
		}
		      });
	}
}

function searchOnKeyLab(datadelete,question) {
	var labName=document.labAdminMain.labNameS.value;
	
	LabManagement.searchLabdwr(labName, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("labbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
    dwr.util.removeAllRows("labbodyPED", { filter:function(tr) {
        return (tr.id != "patternPED");
      }});
    
    
  // Create a new set cloned from the pattern row
  var lab,id,docdetails,contactdetails,nameD;
 
  data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
  
 
  for (var i = 0; i < data.length; i++) {
	
  	lab = data[i];
  	
    if(lab != null)
    {
    id = lab.labId;
    
    docdetails=lab.labName+"\n"+lab.contactPerson
    contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
    status=lab.login.accountStatus;
    nameD = lab.labName
    var searchT = document.labAdminMain.searchTypeLab.value;
    if(searchT == 'allType')
    {
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tableDocId" + id, id);
    dwr.util.setValue("tableDocDetails" + id, docdetails);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    dwr.util.setValue("tableDocStatus" + id, "");
    
    if(status == "ENABLED")
    {
    document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','DISABLED','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
    }
    if(status == "DISABLED")
    {
    document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
    }
    if(status == "PENDING")
    {
    document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegLab('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','LOAD');\" onclick=\"testFunction()\" >"+reject+"</a>";
    }
   
    $("pattern" + id).style.display = "";
    peopleCache[id] = lab;
    }
    
    if(searchT == 'pending')
    {
    	if(status == "PENDING")
    	{
    		dwr.util.cloneNode("patternPED", { idSuffix:id });
		    dwr.util.setValue("tableDocIdPED" + id, id);
		    dwr.util.setValue("tableDocDetailsPED" + id, docdetails);
		    dwr.util.setValue("tableDocContactDetailsPED" + id, contactdetails);
		    dwr.util.setValue("tableDocStatusPED" + id, "");
		     		      
		      if(status == "PENDING")
		      {
		      document.getElementById("tableDocStatusPED" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegLab('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','PENDING');\" onclick=\"testFunction()\" >"+reject+"</a>";
		      }
		      $("patternPED" + id).style.display = "";
		      peopleCache[id] = lab;
    	}
    }
    
    if(searchT == 'enabled')
    {
    	if(status == "ENABLED")
    	{
    		dwr.util.cloneNode("patternPED", { idSuffix:id });
		    dwr.util.setValue("tableDocIdPED" + id, id);
		    dwr.util.setValue("tableDocDetailsPED" + id, docdetails);
		    dwr.util.setValue("tableDocContactDetailsPED" + id, contactdetails);
		    dwr.util.setValue("tableDocStatusPED" + id, "");
		     		      
		    if(status == "ENABLED")
		      {
		      document.getElementById("tableDocStatusPED" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','DISABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
		      }
		      $("patternPED" + id).style.display = "";
		      peopleCache[id] = lab;
    	}
    }
    
    if(searchT == 'disabled')
    {
    	if(status == "DISABLED")
    	{
    		dwr.util.cloneNode("patternPED", { idSuffix:id });
		    dwr.util.setValue("tableDocIdPED" + id, id);
		    dwr.util.setValue("tableDocDetailsPED" + id, docdetails);
		    dwr.util.setValue("tableDocContactDetailsPED" + id, contactdetails);
		    dwr.util.setValue("tableDocStatusPED" + id, "");
		     		      
		    if(status == "DISABLED")
		      {
		      document.getElementById("tableDocStatusPED" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatusLab('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
		      }
		      $("patternPED" + id).style.display = "";
		      peopleCache[id] = lab;
    	}
    }
    
    }
  }
});
}
function editClicked(eleid) {
 	  // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
 	  var lab = peopleCache[eleid.substring(4)];
 	  viewed ="viewed"
 	  tempLabId=lab.labId; 
 	 document.labAdminMain.labIdN.disabled = true;
 	 document.labAdminMain.passwordN.disabled = true;
 	 document.labAdminMain.confirmPasswordN.disabled = true;
 	 document.PhotoUpload.cancelUpload.disabled =false;
  	 dwr.util.setValues(lab);
 	}

 function deleteClicked(eleid,deleted,notDeleted,datadelete,question) {
	  // we were an id of the form "delete{id}", eg "delete42". We lookup the "42"
	  var lab = peopleCache[eleid.substring(6)];
	  var name=lab.labName;
	  var sType =   document.labAdminMain.searchTypeLab.value;
	  if (confirm(datadelete +" "+name+" "+question)){
		dwr.engine.beginBatch();
		LabManagement.deleteLab(lab, function(data) {
	          setDeleteStatus(data,deleted,notDeleted)
		      });
		if(sType == 'allType')
	    {
			loadLab(datadelete,question,'allType');
	    }
	    if(sType == 'pending')
	    {
	     showPendingListL(datadelete, question, 'pending')
	    }
	    if(sType == 'disabled')
	    {
	    	showDisabledListL(datadelete, question, 'disabled')
	    }
	    if(sType == 'enabled')
	    {
	    	showEnabledListL(datadelete, question, 'enabled')
	    }
  	    
	    dwr.engine.endBatch();
	  }
	}



function updateLab(updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question) {
	corrfield=0;
	err=0; 
	 
      var lab = {labId:null, labName:null,contactPerson:null,photo:null, contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}};
	  dwr.util.getValues(lab);
	 //alert(viewed);
	  if(viewed=="viewed")
	  {
		  
		  if(!(tempLabId==lab.labId))
		  {
			  
			  validateLabIdInputFieldFull("labId",lab.labId,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
		  }
		 
		  
	  }
	  else
	  {
		  validateLabIdInputFieldFull("labId",lab.labId,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	  }
	  if(!(viewed=="viewed"))
	  {
		  
	    validateLabIdInputFieldFull("login.password",lab.login.password,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateLabIdInputFieldConfirmPassFull("login.confirmPassword",lab.login.confirmPassword,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	  }
	    validateLabIdInputFieldFull("labName",lab.labName,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateLabIdInputFieldFull("contactDetails.contactNumber",lab.contactDetails.contactNumber,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateLabIdInputFieldFull("contactDetails.email",lab.contactDetails.email,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateLabIdInputFieldFull("contactPerson",lab.contactPerson,lab,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	   
	    // alert("Action in progress");
	  
	
	}

function setSaveStatus(data,addednew,notaddednew)
{
	if(data)
	{
		successMessage(addednew);
		show('ListTable');
		hide('AddTable');
	}
	else
	{
		failureMessage(notaddednew);
	}
}

function setEditStatus(data,updated,notupdated,datadelete,question)
{
	
	if(data)
	{
		successMessage(updated);
		hide('AddTable');
		 var sType =   document.labAdminMain.searchTypeLab.value;
		 
		 if(sType == 'allType')
		    {
		    loadLab(datadelete,question,'allType');
		    show('ListTable');
		    }
		    if(sType == 'pending')
		    {
		     showPendingListL(datadelete, question, 'pending')
		     show('ListTablePED');
		    }
		    if(sType == 'disabled')
		    {
		    	showDisabledListL(datadelete, question, 'disabled')
		    	show('ListTablePED'); 
		    }
		    if(sType == 'enabled')
		    {
		    	
		    	showEnabledListL(datadelete, question, 'enabled')
		    	show('ListTablePED');
		    }
	}
	else
	{
		failureMessage(notupdated);
	}
}

function setDeleteStatus(data,deleted,notDeleted)
{
	if(data)
	{
		successMessage(deleted);
	}
	else
	{
		failureMessage(notDeleted);
	}
}


function clearLab() {
	
	  document.getElementById("listLP").className="fontNormal";
	  document.getElementById("listLAL").className="fontNormal";
	  document.getElementById("listLD").className="fontNormal";
	  document.getElementById("listLE").className="fontNormal";
	  document.getElementById("listLA").className="fontBold";
	  
	  viewed = "newlab";
	  document.labAdminMain.labIdN.disabled = false;
	  document.labAdminMain.passwordN.disabled = false;
	  document.labAdminMain.confirmPasswordN.disabled = false;
	  document.PhotoUpload.cancelUpload.disabled = true;
	  var path="<fmt:message key=data.path/>"+"/"+"<fmt:message key=photo.path/>";
	  var fileName=document.labAdminMain.chkPhotoName.value;
	  var fpath=path+"/"+fileName;
	  if(viewed !="viewed")
	  {		  
	  UploadInfoBean.cancelPhoto(fpath);
	  }
	  viewed = "newLab";
	  UploadInfoBean.setPercentage("0");
	 
	  document.getElementById("fileupload_progress").innerHTML="";
	  document.getElementById("progressBar").style.display = "none";
	  document.getElementById("progressBarBoxContent").style.width =0;  
	  removeErrorMsg();
	  dwr.util.setValues({labId:null, labName:null,contactPerson:null,photo:null,contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	}



function validateDoctorIdInputFieldPhoto(id,value)
{
	  var idf = id;
	  var valuef = value;
	  //alert("hi");
	  //alert(id);
	  //alert(value);
	  LabManagementValidator.getInputFieldValidationMessage(idf, valuef, {
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
      var fileName=document.labAdminMain.chkPhotoName.value;
      var fpath=path+"/"+fileName;
     //alert(fpath);
      UploadInfoBean.cancelPhoto(fpath);
      UploadInfoBean.setPercentage("0");
      document.labAdminMain.chkPhotoName.value="";
     // parent.setName(document.PhotoUpload.photoName.value);
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
   	document.getElementById("labId").className ="noerrorField"
   	document.getElementById("login.password").className ="noerrorField"
   	document.getElementById("login.confirmPassword").className ="noerrorField"
   	document.getElementById("labName").className ="noerrorField"
   	document.getElementById("contactDetails.address").className ="noerrorField"
   	document.getElementById("contactDetails.contactNumber").className ="noerrorField"
   	document.getElementById("contactDetails.email").className ="noerrorField"
   	document.getElementById("contactPerson").className ="noerrorField"
   	document.getElementById("photo").className ="noerrorField"
   			//document.getElementById("specialty").className ="noerrorField"	
   		
   }
   function resetClicked(id)
   {
   	//alert(id);
   	var labId = id.substring(5);
   	if (confirm(sureReset))
   	{
   		
          // alert(docId);
           dwr.engine.beginBatch();
           LabManagement.resetPasswordFunLab(labId,function(data) {
   			//alert(data);
   			confirmResetPasswordLab(data);
   	      });
   	  
   	  dwr.engine.endBatch();
   	}
   }

   function confirmResetPasswordLab(data)
   {
   	if(data != "NotUpdated")
   	{
   		alert(succReset+" : "+data);
   	}
   	else
   	{
   		alert(notReset);
   	}
   }
