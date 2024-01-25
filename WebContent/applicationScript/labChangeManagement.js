var viewed ="newLab";
var peopleCache = { };
var labname="noSearch";
var x=0;
var tempLabId="";
var  corrfield=0,err=0;

function init(id) {
	hide('successMessage');
	hide('errorMessage');
	document.getElementById("labSer").style.background="#f7941d";
	document.getElementById("setAll").className="fontBold";
	  loadLab(id);
	 // target_upload.document.getElementById("photoNameId").value="";
	}



dwr.engine.setWarningHandler(null);

dwr.engine.setErrorHandler(errorHandler);  

function errorHandler(message, exception){  
	
	//alert("errorhandler"+exception.javaClassName+"   "+message);
    //Session timedout/invalidated  
    if(exception != null){  
        //Reload or display an error etc.  
        //document.location.reload();  
    	//document.location = 'Logout.htm'
    }  
}  

function validateLabIdInputField(element)
{
	//alert("hi this is lab");
  var id = element.id;
  //alert(id);
  var value = element.value;
  //alert(value);
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

function validateLabIdInputFieldConfirmPassFull(id,value,lab,updated,notupdated,addednew,notaddednew,correcterrors)
{
  var idf = id;
  var valuef = value;
  var tempval = document.labAdminMain.passwordN.value+"yt%y"+valuef;
  LabManagementValidator.getInputFieldValidationMessage(idf, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatusfull(idf, dataFromServer,lab,updated,notupdated,addednew,notaddednew,correcterrors);
               }
  });
  
}

function validateLabIdInputFieldFull(id,value,lab,updated,notupdated,addednew,notaddednew,correcterrors)
{
	  var idf = id;
	  var valuef = value;
	  LabManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	                  }
	  });
	 
}




function setInputFieldStatusfull(elementId, message,lab,updated,notupdated,addednew,notaddednew,correcterrors)
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
          setEditStatus(data,updated,notupdated)
	      });
	  //loadLab();
	  dwr.engine.endBatch();
			}
		  }
		  else
		  {
			  if(err==4 && corrfield==4)
				{
				  dwr.engine.beginBatch();
				  LabManagement.editeachLab(lab, function(data) {
			          setEditStatus(data,updated,notupdated)
				      });
		  //loadLab();
		  dwr.engine.endBatch();
				}
			  else if(err==4)
			  {
				  alert(notupdated);
			  }
		  }
	  }
	 /* else
	  {
		 // alert(err)
		  if(corrfield==7 && err==7)
			{
		      correctMessage(correcterrors,"correcterror");
		     dwr.engine.beginBatch();
		     LabManagement.saveLab(lab, function(data) {
              setSaveStatus(data,addednew,notaddednew)
		      });
	      loadLab();
		  dwr.engine.endBatch();
			}
		  else if(err==7)
		  {		
			  errorMessage(correcterrors,"correcterror");
		  }
		  
	  }*/
}



function loadLab(id) {
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
	      LabManagement.loadIndividualLab(id,function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("labbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var lab,labid,docdetails,contactdetails;
	    data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
	   
	   
	    for (var i = 0; i < data.length; i++) {
	    	
	      lab = data[i];
	     
	      id = lab.labId;
	      
	      docdetails=lab.labName+"\n"+lab.contactPerson
	      
	      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
	     
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tableDocDetails" + id, docdetails);
	      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = lab;
	    }
	    viewed ="viewed"
	   	  tempLabId=lab.labId; 
	   	  //alert(tempLabId);
	   	 document.labAdminMain.labIdN.disabled = true;
	   	 document.labAdminMain.passwordN.disabled = true;
	   	 document.labAdminMain.confirmPasswordN.disabled = true;
	   	 document.PhotoUpload.cancelUpload.disabled =false;
	    	 dwr.util.setValues(lab);
	    
	  });
	}

function searchOnKeyLab() {
	var labName=document.labAdminMain.labNameS.value;
	
	LabManagement.searchLabdwr(labName, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("labbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
  // Create a new set cloned from the pattern row
  var lab,id,docdetails,contactdetails;
 
  data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
  
 
  for (var i = 0; i < data.length; i++) {
	
  	lab = data[i];
  	
    if(lab != null)
    {
    id = lab.labId;
    
    docdetails=lab.labName+"\n"+lab.contactPerson
    contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tableDocDetails" + id, docdetails);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    $("pattern" + id).style.display = "";
    peopleCache[id] = lab;
    }
  }
});
}
function editClicked(eleid) {
	alert("this is edit lab");
	alert(eleid);
 	  // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
	
 	  var lab = peopleCache[eleid.substring(4)];
 	  alert(lab);
 	  viewed ="viewed"
 	  tempLabId=lab.labId; 
 	  alert(tempLabId);
 	 document.labAdminMain.labIdN.disabled = true;
 	 document.labAdminMain.passwordN.disabled = true;
 	 document.labAdminMain.confirmPasswordN.disabled = true;
 	 document.PhotoUpload.cancelUpload.disabled =false;
  	 dwr.util.setValues(lab);
 	}

 function deleteClicked(eleid,deleted,notDeleted,datadelete,question) {
	 alert(eleid);
	  // we were an id of the form "delete{id}", eg "delete42". We lookup the "42"
	  var lab = peopleCache[eleid.substring(6)];
	  var name=lab.labName;
	  if (confirm(datadelete +" "+name+" "+question)){
		dwr.engine.beginBatch();
		LabManagement.deleteLab(lab, function(data) {
	          setDeleteStatus(data,deleted,notDeleted)
		      });
  	    loadLab();
	    dwr.engine.endBatch();
	  }
	}



function updateLab(id,updated,notupdated,addednew,notaddednew,correcterrors) {
	corrfield=0;
	err=0; 
	 
      var lab = {labId:null, labName:null,contactPerson:null,photo:null, contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}};
	  dwr.util.getValues(lab);
	 //alert(viewed);
	  if(viewed=="viewed")
	  {
		  
		  if(!(tempLabId==lab.labId))
		  {
			  
			  validateLabIdInputFieldFull("labId",lab.labId,lab,updated,notupdated,addednew,notaddednew,correcterrors);
		  }
		 
		  
	  }
	  else
	  {
		  validateLabIdInputFieldFull("labId",lab.labId,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	  }
	  if(!(viewed=="viewed"))
	  {
		  
	    validateLabIdInputFieldFull("login.password",lab.login.password,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateLabIdInputFieldConfirmPassFull("login.confirmPassword",lab.login.confirmPassword,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	  }
	    validateLabIdInputFieldFull("labName",lab.labName,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateLabIdInputFieldFull("contactDetails.contactNumber",lab.contactDetails.contactNumber,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateLabIdInputFieldFull("contactDetails.email",lab.contactDetails.email,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateLabIdInputFieldFull("contactPerson",lab.contactPerson,lab,updated,notupdated,addednew,notaddednew,correcterrors);
	   
	    // alert("Action in progress");
	  
	
	}

function setSaveStatus(data,addednew,notaddednew)
{
	if(data)
	{
		alert(addednew);
	}
	else
	{
		alert(notaddednew);
	}
}

function setEditStatus(data,updated,notupdated)
{
	//alert(data);
	if(data)
	{
		successMessage(updated);
		//show('ListTable'); 
		//hide('AddTable');
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
		alert(deleted);
	}
	else
	{
		alert(notDeleted);
	}
}


function clearLab() {
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
	  viewed = "newdoctor";
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
	  DoctorManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusPhoto(idf,dataFromServer);
	                  }
	  });
	 
}

 

   function cancelPhotoo(datapath,photopath)
  {
	
	  var path=datapath+"/"+photopath;
	  //alert(path);
     document.getElementById("fileupload_progress").innerHTML="";
    document.getElementById("progressBar").style.display = "none";
    document.getElementById("progressBarBoxContent").style.width =0;
      var fileName=document.labAdminMain.chkPhotoName.value;
      var fpath=path+"/"+fileName;
     //alert(fpath);
      UploadInfoBean.cancelPhoto(fpath);
      UploadInfoBean.setPercentage("0");
      document.labAdminMain.chkPhotoName.value="";
      parent.setName(document.PhotoUpload.photoName.value);
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
   	
   			//document.getElementById("photo").className ="noerrorField"
   			//document.getElementById("specialty").className ="noerrorField"	
   		
   }
   
   function cancel()
   {
   	document.location="ChangePassword.htm";
   }
