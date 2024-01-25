
var status="0";
var a;
var viewed ="newdoctor";
var peopleCache = { };
var doctorname="noSearch";
var corrfield=0,err=0;

var tempDoctorId="";
var e=0;


function init(id) {
		
	  //alert("inside init");
	 //alert(id);
	document.getElementById("sepReff").style.background="#f7941d";
	document.getElementById("setAll").className="fontBold";
	  loadDoctor(id);
	  document.getElementById("errorMessage").innerHTML=""
	}

dwr.engine.setWarningHandler(null);

dwr.engine.setErrorHandler(errorHandler);  


function errorHandler(message, exception){  
	
	//alert("errorhandler"+exception.javaClassName+"   "+message);
    //Session timedout/invalidated  
    if(exception != null){  
        //Reload or display an error etc.  
        //document.location.reload();  
    	//document.location = 'Logout.htm';
    }  
}  
function validateDoctorIdInputField(element)
{
 //alert("hi");	
  var id = element.id;
 // alert(id);
  var value = element.value;
  //alert(value);
  DoctorManagementValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
	      setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validateDoctorIdInputFieldConfirmPass(element)
{
  var id = element.id;
  var value = element.value;
  var tempval = document.doctorAdminMain.passwordN.value+"yt%y"+value;
  DoctorManagementValidator.getInputFieldValidationMessage(id, tempval, {
          callback:function(dataFromServer) {
	 
                  setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validateDoctorIdInputFieldConfirmPassFull(id,value,doctor,updated,notupdated,addednew,notaddednew,correcterrors)
{
  var idf = id;
  var valuef = value;
  var tempval = document.doctorAdminMain.passwordN.value+"yt%y"+valuef;
  DoctorManagementValidator.getInputFieldValidationMessage(idf, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatusfull(idf, dataFromServer,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
               }
  });
  
}

function validateDoctorIdInputFieldFull(id,value,doctor,updated,notupdated,addednew,notaddednew,correcterrors)
{
	  var idf = id;
	  var valuef = value;
	 // alert(idf);
	  //alert(valuef);
	  DoctorManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	                  
	                  }
	  });
	 
}


function setInputFieldStatusfull(elementId, message,doctor,updated,notupdated,addednew,notaddednew,correcterrors)
{

  var tempvab = message
 // alert(tempvab);
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
		  //alert("inside if flag");
	   errorMessage(message,elementId);
     }
     
   }

   if(viewed=="viewed")
	  {
	 	  
	   if(!(tempDoctorId==doctor.doctorId))
		  {
		  if(corrfield==13 && err==13)
			{
			  dwr.engine.beginBatch();
	 
	  DoctorManagement.editeachDoctor(doctor, function(data) {
       setEditStatus(data,updated,notupdated)
	      });
	  
	  //loadDoctor();
	  dwr.engine.endBatch();
			}
		  }
		  else
		  {
			 
			  //alert(corrfield);
			  if(corrfield==10 && err==10)
				{
				 
				  dwr.engine.beginBatch();
				  DoctorManagement.editeachDoctor(doctor, function(data) {
			          setEditStatus(data,updated,notupdated)
				      });
		 
		  //loadDoctor();
		  dwr.engine.endBatch();
				}
			  else if(err==10)
			  {
				
				  failureMessage(notupdated);
			  }
		  }
	  }
	 /* else
	  {
		// alert(err)
	  if(corrfield==13 && err==13)
			{
		      correctMessage(correcterrors,"correcterror");
			  dwr.engine.beginBatch();
		      DoctorManagement.saveDoctor(doctor, function(data) {
               setSaveStatus(data,addednew,notaddednew)
			      });
		      
		      loadDoctor();
		      dwr.engine.endBatch();
			}
		  else if(err == 13)
		  {
			  //alert("Not Added Correct The Errors...");
			  errorMessage(correcterrors,"correcterror");
		  }
		  
	  }*/
	    
}

function loadDoctor(id) {
	
	//alert("inside loaddoctor");
	//alert(id);
	viewed ="newdoctor";
	//document.getElementById("fileupload_progress").innerHTML="";
	   // document.getElementById("progressBar").style.display = "none";
       // document.getElementById("progressBarBoxContent").style.width =0;
    document.getElementById("photo1").innerHTML="";
   
   	document.doctorAdminMain.doctorIdN.disabled = false;
	document.doctorAdminMain.passwordN.disabled = false;
 	document.doctorAdminMain.confirmPasswordN.disabled = false;
 	document.PhotoUpload.cancelUpload.disabled = true;
	//document.doctorAdminMain.typeN.value="";

	
	dwr.util.setValues({doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null,photo:null,  contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	      DoctorManagement.loadDoctorByLogin(id,function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var doctor,doctorId,docdetails,contactdetails,login;
	    data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
	   //alert(data);
	    
	    for (var i = 0; i < data.length; i++) {
	    	
	      doctor = data[i];
	      //alert(doctor);
	      id = doctor.doctorId;
	     // alert(id);
	      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
	      //alert(docdetails);
	      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
	      //alert(contactdetails);
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tableDocDetails" + id, docdetails);
	      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = doctor;
	    }
	   // var doctor = peopleCache[eleid.substring(4)];
		  viewed ="viewed"
		  tempDoctorId=	doctor.doctorId; 
		//alert("here");
		 document.doctorAdminMain.doctorIdN.disabled = true;
		 document.doctorAdminMain.passwordN.disabled = true;
		 document.doctorAdminMain.confirmPasswordN.disabled = true;
		 document.PhotoUpload.cancelUpload.disabled =false;
		  dwr.util.setValues(doctor);
	  });
	}


function searchOnKeyDoctor() {
	var doctorName=document.doctorAdminMain.doctorNameS.value;
	DoctorManagement.searchDoctordwr(doctorName, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
  // Create a new set cloned from the pattern row
  var doctor,id,docdetails,contactdetails,login;
  
 
  data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
  
 
  for (var i = 0; i < data.length; i++) {
	
  	doctor = data[i];
  	
    if(doctor != null)
    {
    id = doctor.doctorId;
    
    docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
    contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tableDocDetails" + id, docdetails);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    $("pattern" + id).style.display = "";
    peopleCache[id] = doctor;
    }
  }
});
}

function editClicked(eleid) {
	  // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
	  var doctor = peopleCache[eleid.substring(4)];
	  viewed ="viewed"
	  tempDoctorId=	doctor.doctorId; 
	
	 document.doctorAdminMain.doctorIdN.disabled = true;
	 document.doctorAdminMain.passwordN.disabled = true;
	 document.doctorAdminMain.confirmPasswordN.disabled = true;
	 document.PhotoUpload.cancelUpload.disabled =false;
	  dwr.util.setValues(doctor);
	}

function deleteClicked(eleid,deleted,notDeleted,datadelete,question) {
	  // we were an id of the form "delete{id}", eg "delete42". We lookup the "42"
	  var doctor = peopleCache[eleid.substring(6)];
	  var name=doctor.firstName+" "+doctor.lastName
	  if (confirm(datadelete +" "+ name +" "+ question)){
		dwr.engine.beginBatch();
	   
	    DoctorManagement.deleteDoctor(doctor, function(data) {
        setDeleteStatus(data,deleted,notDeleted)
	      });
	    loadDoctor();
	    dwr.engine.endBatch();
	  }
	}

function changeType()
{
	//alert("hello");
   //document.doctorAdminMain.doctype.value=document.doctorAdminMain.typeN.value;
   //document.getElementById("type1").innerHTML="";
   document.getElementById("errorMessage").innerHTML="";
   
}

function updateDoctor(id,updated,notupdated,addednew,notaddednew,correcterrors) {
	//alert("id is : " +id);
	 corrfield=0;
	 err=0;  
     var doctor = {doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null, photo:null, contactDetails:{email:null,address:null,contactNumber:null}, login:{password:null,confirmPassword:null}};
	  dwr.util.getValues(doctor);
	  //alert(doctor);
	  //alert(doctor.doctorId);
	  if(viewed=="viewed")
	  {
		  //alert(tempDoctorId);
		  if(!(tempDoctorId==doctor.doctorId))
		  {
			  alert("dfdvxcvxcv");
			  validateDoctorIdInputFieldFull("doctorId",doctor.doctorId,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
			  alert("second");
		  }
		  
	  }
	  else
	  {
		  validateDoctorIdInputFieldFull("doctorId",doctor.doctorId,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	  }

	  if(!(viewed=="viewed"))
	  {
		
	    validateDoctorIdInputFieldFull("login.password",doctor.login.password,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldConfirmPassFull("login.confirmPassword",doctor.login.confirmPassword,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	  }
	  	
	    validateDoctorIdInputFieldFull("firstName",doctor.firstName,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("lastName",doctor.lastName,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("contactDetails.contactNumber",doctor.contactDetails.contactNumber,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("contactDetails.email",doctor.contactDetails.email,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("education",doctor.education,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("designation",doctor.designation,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("identification",doctor.identification,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("clinicName",doctor.clinicName,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("specialty",doctor.specialty,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    validateDoctorIdInputFieldFull("type",doctor.type,doctor,updated,notupdated,addednew,notaddednew,correcterrors);
	    //alert("Action in progress");
	    
	}

function setSaveStatus(data,addednew,notaddednew)
{
	if(data)
	{
		//alert("New Record Added");
		successMessage(addednew);
		show('ListTable');
		hide('AddTable');

	}
	else
	{
		 //alert("New Record Not Added");
		 failureMessage(notaddednew);
	}
}

function setEditStatus(data,updated,notupdated)
{
	//alert(data);
	if(data)
	{
		//alert(data);
		//alert("Values Updated");
		successMessage(updated);
		//show('ListTable'); 
		//hide('AddTable');

	}
	else
	{
		//alert("Values Not Updated");
		failureMessage(notupdated);
	}
}

function setDeleteStatus(data,deleted,notDeleted)
{
	if(data)
	{
		//alert("Deleted Successfully...");
		successMessage(deleted);
	}
	else
	{
		//alert("Not Deleted....");
		failureMessage(notDeleted);
	}
}



function validateDoctorIdInputFieldPhoto(id,value)
{
	  var idf = id;
	  var valuef = value;
	  //alert("hi in validate");
	  DoctorManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusPhoto(idf, dataFromServer);
	                  }
	  });
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
	document.getElementById("doctorId").className ="noerrorField"
	document.getElementById("contactDetails.email").className ="noerrorField"
	document.getElementById("login.password").className ="noerrorField"
	document.getElementById("education").className ="noerrorField"
	document.getElementById("login.confirmPassword").className ="noerrorField"
	document.getElementById("designation").className ="noerrorField"
	document.getElementById("firstName").className ="noerrorField"
	document.getElementById("identification").className ="noerrorField"
	document.getElementById("lastName").className ="noerrorField"
	document.getElementById("clinicName").className ="noerrorField"
	document.getElementById("contactDetails.address").className ="noerrorField"
	document.getElementById("specialty").className ="noerrorField"
	document.getElementById("contactDetails.contactNumber").className ="noerrorField"
	document.getElementById("type").className ="noerrorField"
			//document.getElementById("photo").className ="noerrorField"
			//document.getElementById("specialty").className ="noerrorField"	
		
}

function clearDoctor(datapath,photopath) {
	 
	  document.doctorAdminMain.doctorIdN.disabled = false;
	  document.doctorAdminMain.passwordN.disabled = false;
	  document.doctorAdminMain.confirmPasswordN.disabled = false;
	  document.PhotoUpload.cancelUpload.disabled = true;
	  
	  
	  var path=datapath+"/"+photopath;
	 //alert("path = "+path);
	  var fileName=document.doctorAdminMain.chkPhotoName.value;
	  var fpath=path+"/"+fileName;
	  //alert(fpath);
	  if(viewed !="viewed")
	  {
		  //alert("inside if");
	  UploadInfoBean.cancelPhoto(fpath);
	  }
	  //alert("outside if");
	  viewed = "newdoctor";
	  UploadInfoBean.setPercentage("0");
	  
	  document.getElementById("fileupload_progress").innerHTML="";
	  document.getElementById("progressBar").style.display = "none";
	  document.getElementById("progressBarBoxContent").style.width =0;
	  removeErrorMsg();
	  dwr.util.setValues({doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null, photo:null,  contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	}

function cancelPhotoo(datapath,photopath)
{
	
	  var path=datapath+"/"+photopath;
	 // alert(path);
   document.getElementById("fileupload_progress").innerHTML="";
  document.getElementById("progressBar").style.display = "none";
  document.getElementById("progressBarBoxContent").style.width =0;
    var fileName=document.doctorAdminMain.chkPhotoName.value;
    var fpath=path+"/"+fileName;
   //alert(fpath);
    UploadInfoBean.cancelPhoto(fpath);
    UploadInfoBean.setPercentage("0");
    document.doctorAdminMain.chkPhotoName.value="";
   // parent.setName(document.PhotoUpload.photoName.value);
}

function cancel()
{
	document.location="ChangePassword.htm";
}

