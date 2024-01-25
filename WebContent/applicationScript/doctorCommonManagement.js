var status="0";
var a;
var viewed ="newdoctor";
var peopleCache = { };
var doctorname="noSearch";
var corrfield=0,err=0;
var tempDoctorId="";
var e=0;
var enable;
var reject;
var disable;
var labMan;
var newReg;
var openbr;
var closebr;
var resetyes;
var resetno;
var sureReset;
var docRegistered;

function init(docRegisteredc,enablec,rejectc,disablec,labManc,newRegc,datadelete,question,resetnoc,resetyesc,sureResetc) {
	  docRegistered = docRegisteredc;
	  enable = enablec;
	  disable = disablec;
	  reject = rejectc;
	  labMan = labManc;
	  newReg = newRegc;
	  resetyes = resetyesc;
	  resetno  = resetnoc;
	  sureReset = sureResetc;
	  document.getElementById("docManage").style.background="#f7941d";
	  document.getElementById("listAL").className="fontBold";
	  loadDoctor(datadelete,question,'allType');
	  document.getElementById("errorMessage").innerHTML=""
	
	}

dwr.engine.setWarningHandler(null);
dwr.engine.setErrorHandler(errorHandler);  

function errorHandler(message, exception){  
	//alert("errorhandler"+exception.javaClassName+"   "+message);
    //Session timedout/invalidated  
    if(exception != null){  
        //Reload or display an error etc.  
        document.location.reload();  
    	//document.location = 'Logout.htm';
    }  
}  
function validateDoctorIdInputField(element)
{
 //alert("hi");	
  var id = element.id;
  var value = element.value;
  //alert(id);
  if(id == "firstName")
  {
	  var tempName = value.split(".");
	  if(tempName.length > 0)
	  {
		//alert(tempName[0]);  
		if(tempName[0] == "Dr")
		{
			var len = value.length;
		    var tempFirstName = value.substr(4,len);
		    value = tempFirstName;
		}
		else
		{
			value = "Dr. "+value;
		}
	  }
	  else
	  {
		  //alert(value);
		  value = "Dr. "+value;
	  }
	 // alert(value);
	   
  }
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

function validateDoctorIdInputFieldConfirmPassFull(id,value,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question)
{
  var idf = id;
  var valuef = value;
  var tempval = document.doctorAdminMain.passwordN.value+"yt%y"+valuef;
  DoctorManagementValidator.getInputFieldValidationMessage(idf, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatusfull(idf, dataFromServer,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
               }
  });
  
}

function validateDoctorIdInputFieldFull(id,value,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question)
{
	  var idf = id;
	  var valuef = value;
	  DoctorManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	                  
	                  }
	  });
	 
}


function setInputFieldStatusfull(elementId, message,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question)
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
	 
	   if(!(tempDoctorId==doctor.doctorId))
		  {
		  if(corrfield==13 && err==13)
			{
			  dwr.engine.beginBatch();
	 
	  DoctorManagement.editeachDoctor(doctor, function(data) {
       setEditStatus(data,updated,notupdated,datadelete,question)
	      });
	 
	 
	  
	  dwr.engine.endBatch();
			}
	 }
		  else
		  {
			  if(corrfield==10 && err==10)
				{
				  dwr.engine.beginBatch();
				  DoctorManagement.editeachDoctor(doctor, function(data) {
			          setEditStatus(data,updated,notupdated,datadelete,question)
				      });
						 
				  
		  dwr.engine.endBatch();
				}
			  else if(err==10)
			  {
				  //alert("Values Not Updated");
				  failureMessage(notupdated);
			  }
		  }
	  }
	  else
	  {
		// alert(err)
	  if(corrfield==13 && err==13)
			{
		      correctMessage(correcterrors,"correcterror");
			  dwr.engine.beginBatch();
		      DoctorManagement.saveDoctor(doctor, function(data) {
               setSaveStatus(data,addednew,notaddednew)
			      });
		      
		      loadDoctor(datadelete,question,'allType');
		      dwr.engine.endBatch();
			}
		  else if(err == 13)
		  {
			  //alert("Not Added Correct The Errors...");
			  errorMessage(correcterrors,"correcterror");
		  }
		  
	  }
	 

   
}
function showPendingList(datadelete,question,searchType)
{
	document.getElementById("listP").className="fontBold";
	document.getElementById("listAL").className="fontNormal";
	document.getElementById("listD").className="fontNormal";
	document.getElementById("listE").className="fontNormal";
	document.getElementById("listA").className="fontNormal";
	
	
	document.doctorAdminMain.doctorNameS.value="";
	document.doctorAdminMain.searchType.value=searchType;
	 DoctorManagement.loadDoctorsPending(function(data) {
	    	
	  	    // Delete all the rows except for the "pattern" row
	  	      dwr.util.removeAllRows("doctorbodyP", { filter:function(tr) {
	  	      return (tr.id != "patternP");
	  	    }});
	  	    
	  	    // Create a new set cloned from the pattern row
	  	    var doctor,id,docdetails,contactdetails,login,type,status;
	  	    data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
	  	   
	  	    
	  	    for (var i = 0; i < data.length; i++) {
	  	    	
	  	      doctor = data[i];
	  	      id = doctor.doctorId;
	  	   
	  	      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
	  	      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
	  	      status=doctor.login.accountStatus;
	  	      type = doctor.type;
	  	      nameD = doctor.firstName+" "+doctor.lastName
	  	    if(status == "PENDING")
		      {
	  	      dwr.util.cloneNode("patternP", { idSuffix:id });
	  	      dwr.util.setValue("tableDocIdP" + id, id);
	  	      dwr.util.setValue("tableDocDetailsP" + id, docdetails);
	  	      dwr.util.setValue("tableDocTypeP" + id, type);
	  	      dwr.util.setValue("tableDocContactDetailsP" + id, contactdetails);
	  	      dwr.util.setValue("tableDocStatusP" + id, "");
	  	      document.getElementById("tableDocStatusP" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegDoc('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','"+status+"');\" onclick=\"testFunction()\" >"+reject+"</a>";
	  	     if(doctor.flag == "NOTREAD")
		      {
		    	 document.getElementById("patternP"+id).className="b";
		      }
	  	      $("patternP" + id).style.display = "";
	  	      peopleCache[id] = doctor;
		      }
	  	    }
	  	  });
	      
}

function showDisabledList(datadelete,question,searchType)
{
	document.getElementById("listP").className="fontNormal";
	document.getElementById("listAL").className="fontNormal";
	document.getElementById("listD").className="fontBold";
	document.getElementById("listE").className="fontNormal";
	document.getElementById("listA").className="fontNormal";
	document.doctorAdminMain.doctorNameS.value="";
	document.doctorAdminMain.searchType.value=searchType;
	 DoctorManagement.loadDoctorsDisabled(function(data) {
	    	
	  	    // Delete all the rows except for the "pattern" row
	  	      dwr.util.removeAllRows("doctorbodyD", { filter:function(tr) {
	  	      return (tr.id != "patternD");
	  	    }});
	  	    
	  	    // Create a new set cloned from the pattern row
	  	    var doctor,id,docdetails,contactdetails,login,type,status;
	  	    data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
	  	   
	  	    
	  	    for (var i = 0; i < data.length; i++) {
	  	    	
	  	      doctor = data[i];
	  	      id = doctor.doctorId;
	  	   
	  	      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
	  	      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
	  	      status=doctor.login.accountStatus;
	  	      type = doctor.type;
	  	    if(status == "DISABLED")
		      {
	  	      dwr.util.cloneNode("patternD", { idSuffix:id });
	  	      dwr.util.setValue("tableDocIdD" + id, id);
	  	      dwr.util.setValue("tableDocDetailsD" + id, docdetails);
	  	      dwr.util.setValue("tableDocTypeD" + id, type);
	  	      dwr.util.setValue("tableDocContactDetailsD" + id, contactdetails);
	  	      dwr.util.setValue("tableDocStatusD" + id, "");
	  	     
	  	      document.getElementById("tableDocStatusD" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
	  	      
	  	      $("patternD" + id).style.display = "";
	  	      peopleCache[id] = doctor;
		      }
	  	    }
	  	  });
}

function showEnabledList(datadelete,question,searchType)
{
	document.getElementById("listP").className="fontNormal";
	document.getElementById("listAL").className="fontNormal";
	document.getElementById("listD").className="fontNormal";
	document.getElementById("listE").className="fontBold";
	document.getElementById("listA").className="fontNormal";
	
	document.doctorAdminMain.doctorNameS.value="";
	document.doctorAdminMain.searchType.value=searchType;
    DoctorManagement.loadDoctorsEnabled(function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("doctorbodyE", { filter:function(tr) {
	      return (tr.id != "patternE");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var doctor,id,docdetails,contactdetails,login,type,status;
	    data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
	   
	    
	    for (var i = 0; i < data.length; i++) {
	    	
	      doctor = data[i];
	      id = doctor.doctorId;
	   
	      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
	      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
	      status=doctor.login.accountStatus;
	      type = doctor.type;
	    if(status == "ENABLED")
	      {
	      dwr.util.cloneNode("patternE", { idSuffix:id });
	      dwr.util.setValue("tableDocIdE" + id, id);
	      dwr.util.setValue("tableDocDetailsE" + id, docdetails);
	      dwr.util.setValue("tableDocTypeE" + id, type);
	      dwr.util.setValue("tableDocContactDetailsE" + id, contactdetails);
	      dwr.util.setValue("tableDocStatusE" + id, "");
	     
	      document.getElementById("tableDocStatusE" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','DISABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
	       
	      $("patternE" + id).style.display = "";
	      peopleCache[id] = doctor;
	      }
	    }
	  });
}

function enableFullList()
{
	document.getElementById("listP").className="fontNormal";
	document.getElementById("listAL").className="fontBold";
	document.getElementById("listD").className="fontNormal";
	document.getElementById("listE").className="fontNormal";
	document.getElementById("listA").className="fontNormal";
}

function loadDoctor(datadelete,question,searchType) {
    
	show('searchTable');
	document.getElementById("listAL").className="fontBold";
	document.getElementById("listP").className="fontNormal";
	document.getElementById("listD").className="fontNormal";
	document.getElementById("listE").className="fontNormal";
	document.getElementById("listA").className="fontNormal";
	var h =0;
	document.doctorAdminMain.doctorNameS.value="";

	document.doctorAdminMain.searchType.value=searchType;
	viewed ="newdoctor";
	document.getElementById("fileupload_progress").innerHTML="";
    document.getElementById("progressBar").style.display = "none";
    document.getElementById("progressBarBoxContent").style.width =0;
    document.getElementById("photo1").innerHTML="";
   
   	document.doctorAdminMain.doctorIdN.disabled = false;
	document.doctorAdminMain.passwordN.disabled = false;
 	document.doctorAdminMain.confirmPasswordN.disabled = false;
 	document.PhotoUpload.cancelUpload.disabled = true;
	//document.doctorAdminMain.typeN.value="";
	
 	
 	DoctorManagement.newLabReg( function(data) {
    	if(data > 0)
    	{
	document.getElementById("labManMenu").innerHTML = labMan +" ("+data+" "+newReg+")"
    	}
    	else
    	{
    		document.getElementById("labManMenu").innerHTML = labMan
    	}
    	
    });
 	
	dwr.util.setValues({doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null,photo:null,  contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	      DoctorManagement.loadDoctors(function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var doctor,id,docdetails,contactdetails,login,type,status,nameD;
	    data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
	   
	    
	    for (var i = 0; i < data.length; i++) {
	    	
	      doctor = data[i];
	      id = doctor.doctorId;
	   
	      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
	      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
	      status=doctor.login.accountStatus;
	      type = doctor.type;
	      nameD = doctor.firstName+" "+doctor.lastName
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tableDocId" + id, id);
	      dwr.util.setValue("tableDocDetails" + id, docdetails);
	      dwr.util.setValue("tableDocType" + id, type);
	      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
	      dwr.util.setValue("tableDocStatus" + id, "");
	     
	      
	      if(status == "ENABLED")
	      {
	      document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','DISABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
	      }
	      if(status == "DISABLED")
	      {
	      document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
	      }
	      if(status == "PENDING")
	      {
	      document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegDoc('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','LOAD');\" onclick=\"testFunction()\" >"+reject+"</a>";
	      }
	      
	      if(doctor.flag == "NOTREAD")
	      {
	    	 document.getElementById("pattern"+id).className="b";
	    	 h=h+1;
	      }
	      
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = doctor;
	      
	      if(h > 0)
	      {
	      document.getElementById("newReg").className = 'showDiv newReg';
	  	  document.getElementById("newReg").innerHTML = h +"  "+docRegistered;
	  	  setTimeout ("hide('newReg')", 4000) 
	  	
	      }
	    }
	  });
	}

function changeStatus(id,status,typeDoctor,datadelete,question)
{
	//alert(typeDoctor);
	//alert("id : "+id);
	//alert("status : "+status);
	DoctorManagement.changeStatusDoctor(id,status, function(data) {
		if(data)
		{
			dwr.engine.beginBatch();
			DoctorManagement.sendConformationStatus(id,function(data) {
				
		      });
		  
		  dwr.engine.endBatch();
		}
		
		if(typeDoctor == "LOAD")
		{
			loadDoctor(datadelete,question,'allType');
		}
	   if(typeDoctor == "ENABLED")
	   {
		   showEnabledList(datadelete,question,'enabled');
	   }
	  if(typeDoctor == "DISABLED")
	   {
		  showDisabledList(datadelete,question,'disabled')
	   }
	  if(typeDoctor == "PENDING")
	   {
		  showPendingList(datadelete,question,'pending');
	   }
	      
		      });
}
function deleteSelfRegDoc(id,datadelete,question,name,typeDoc)
{
	if (confirm(datadelete +" "+ name +" "+ question)){
		
	DoctorManagement.deleteSelfRegDoctor(id, function(data) {
		if(typeDoc == "LOAD")
		{
	      loadDoctor(datadelete,question,'allType');
		}
		if(typeDoc == "PENDING")
		{
			showPendingList(datadelete, question,'pending')
		}
		      });
	}
}
function searchOnKeyDoctor(datadelete,question) {
	//alert("hi");
	var doctorName=document.doctorAdminMain.doctorNameS.value;
	//alert(doctorName);
	
	DoctorManagement.searchDoctordwr(doctorName, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
    dwr.util.removeAllRows("doctorbodyP", { filter:function(tr) {
        return (tr.id != "patternP");
      }});
    dwr.util.removeAllRows("doctorbodyD", { filter:function(tr) {
        return (tr.id != "patternD");
      }});
    dwr.util.removeAllRows("doctorbodyE", { filter:function(tr) {
        return (tr.id != "patternE");
      }});
    //alert(doctorName);
  // Create a new set cloned from the pattern row
  var doctor,id,docdetails,contactdetails,login,type,status;
  
 
  data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
  
  //alert(doctorName);
  for (var i = 0; i < data.length; i++) {
	
  	doctor = data[i];
  	
    if(doctor != null)
    {
    id = doctor.doctorId;
    
    docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
    contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
    type = doctor.type;
    status = doctor.login.accountStatus;
    nameD = doctor.firstName+" "+doctor.lastName;
    
    var searchT = document.doctorAdminMain.searchType.value;
    
    //alert(searchT)
    if(searchT == 'allType')
    {
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tableDocId" + id, id);
    dwr.util.setValue("tableDocDetails" + id, docdetails);
    dwr.util.setValue("tableDocType" + id, type);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    dwr.util.setValue("tableDocStatus" + id, "");
    if(status == "ENABLED")
    {
    document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','DISABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
    }
    if(status == "DISABLED")
    {
    document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
    }
    if(status == "PENDING")
    {
    	document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','LOAD','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegDoc('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','LOAD');\" onclick=\"testFunction()\" >"+reject+"</a>";
    }
   // alert(doctorName);
    $("pattern" + id).style.display = "";
    peopleCache[id] = doctor;
    }
    
    if(searchT == 'pending')
    {
    	if(status == "PENDING")
    	{
    		  dwr.util.cloneNode("patternP", { idSuffix:id });
	  	      dwr.util.setValue("tableDocIdP" + id, id);
	  	      dwr.util.setValue("tableDocDetailsP" + id, docdetails);
	  	      dwr.util.setValue("tableDocTypeP" + id, type);
	  	      dwr.util.setValue("tableDocContactDetailsP" + id, contactdetails);
	  	      dwr.util.setValue("tableDocStatusP" + id, "");
	  	      document.getElementById("tableDocStatusP" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); deleteSelfRegDoc('"+id+"','"+datadelete+"','"+question+"','"+nameD+"','"+status+"');\" onclick=\"testFunction()\" >"+reject+"</a>";
	  	     
	  	      $("patternP" + id).style.display = "";
	  	      peopleCache[id] = doctor;
    	}
    }

    if(searchT == 'enabled')
    {
    	if(status == "ENABLED")
    	{
    	  dwr.util.cloneNode("patternE", { idSuffix:id });
  	      dwr.util.setValue("tableDocIdE" + id, id);
  	      dwr.util.setValue("tableDocDetailsE" + id, docdetails);
  	      dwr.util.setValue("tableDocTypeE" + id, type);
  	      dwr.util.setValue("tableDocContactDetailsE" + id, contactdetails);
  	      dwr.util.setValue("tableDocStatusE" + id, "");
  	     
  	      document.getElementById("tableDocStatusE" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','DISABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+disable+"</a>";
  	       
  	      $("patternE" + id).style.display = "";
  	      peopleCache[id] = doctor;
    	}
    }
    
    if(searchT == 'disabled')
    {
    	if(status == "DISABLED")
    	{
    		  dwr.util.cloneNode("patternD", { idSuffix:id });
	  	      dwr.util.setValue("tableDocIdD" + id, id);
	  	      dwr.util.setValue("tableDocDetailsD" + id, docdetails);
	  	      dwr.util.setValue("tableDocTypeD" + id, type);
	  	      dwr.util.setValue("tableDocContactDetailsD" + id, contactdetails);
	  	      dwr.util.setValue("tableDocStatusD" + id, "");
	  	     
	  	      document.getElementById("tableDocStatusD" + id).innerHTML=status+"&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:testFunction(); changeStatus('"+id+"','ENABLED','"+status+"','"+datadelete+"','"+question+"');\" onclick=\"testFunction()\" >"+enable+"</a>";
	  	      
	  	      $("patternD" + id).style.display = "";
	  	      peopleCache[id] = doctor;
    	}
    }
    
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
	  var sType =   document.doctorAdminMain.searchType.value;
	  if (confirm(datadelete +" "+ name +" "+ question)){
		dwr.engine.beginBatch();
	   
	    DoctorManagement.deleteDoctor(doctor, function(data) {
        setDeleteStatus(data,deleted,notDeleted)
	      });
	    if(sType == 'allType')
	    {
	    loadDoctor(datadelete,question,'allType');
	    }
	    if(sType == 'pending')
	    {
	     showPendingList(datadelete, question, 'pending')
	    }
	    if(sType == 'disabled')
	    {
	    	showDisabledList(datadelete, question, 'disabled')
	    }
	    if(sType == 'enabled')
	    {
	    	showEnabledList(datadelete, question, 'enabled')
	    }
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

function updateDoctor(updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question) {
	 corrfield=0;
	 err=0;  
     var doctor = {doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null, photo:null, contactDetails:{email:null,address:null,contactNumber:null}, login:{password:null,confirmPassword:null}};
	  dwr.util.getValues(doctor);
	 
	  if(viewed=="viewed")
	  {
		  
		  if(!(tempDoctorId==doctor.doctorId))
		  {
			  
			  validateDoctorIdInputFieldFull("doctorId",doctor.doctorId,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
		  }
		  
	  }
	  else
	  {
		  validateDoctorIdInputFieldFull("doctorId",doctor.doctorId,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	  }

	  if(!(viewed=="viewed"))
	  {
		  
	    validateDoctorIdInputFieldFull("login.password",doctor.login.password,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldConfirmPassFull("login.confirmPassword",doctor.login.confirmPassword,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	  }
	  
	    
	    var tempFirstName = "";
	    var tempName = doctor.firstName.split(".");
		  if(tempName.length > 0)
		  {
			//alert(tempName[0]);  
			if(tempName[0] == "Dr")
			{
				var len = doctor.firstName.length;
			    tempFirstName = doctor.firstName.substr(4,len);
			    
			}
			else
			{
				tempFirstName = "Dr. "+doctor.firstName;
			}
		  }
		  else
		  {
			  //alert(value);
			  tempFirstName = "Dr. "+doctor.firstName;
		  }
	 
	    validateDoctorIdInputFieldFull("firstName",tempFirstName,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("lastName",doctor.lastName,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("contactDetails.contactNumber",doctor.contactDetails.contactNumber,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("contactDetails.email",doctor.contactDetails.email,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("education",doctor.education,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("designation",doctor.designation,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("identification",doctor.identification,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("clinicName",doctor.clinicName,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("specialty",doctor.specialty,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
	    validateDoctorIdInputFieldFull("type",doctor.type,doctor,updated,notupdated,addednew,notaddednew,correcterrors,datadelete,question);
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

function setEditStatus(data,updated,notupdated,datadelete,question)
{
	if(data)
	{
		//alert("Values Updated");
		successMessage(updated);
		//show('ListTable'); 
		hide('AddTable');
		  var sType =   document.doctorAdminMain.searchType.value;
		 if(sType == 'allType')
		    {
		    loadDoctor(datadelete,question,'allType');
		    show('searchTable');
		    show('ListTable');
		    
		    }
		    if(sType == 'pending')
		    {
		     showPendingList(datadelete, question, 'pending')
		     show('searchTable');
		     show('ListTablePending');
		     
		    }
		    if(sType == 'disabled')
		    {
		    	showDisabledList(datadelete, question, 'disabled')
		    	show('searchTable');
		    	show('ListTableDisabled'); 
		    }
		    if(sType == 'enabled')
		    {
		    	showEnabledList(datadelete, question, 'enabled')
		    	show('searchTable');
		    	show('ListTableEnabled');
		    }

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
	  //alert(id);
	  //alert(value);
	  //alert("hi");
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
	document.getElementById("photo").className ="noerrorField"
			//document.getElementById("specialty").className ="noerrorField"	
		
}

function clearDoctor(datapath,photopath) {
	 
	  document.getElementById("listP").className="fontNormal";
	  document.getElementById("listAL").className="fontNormal";
	  document.getElementById("listD").className="fontNormal";
	  document.getElementById("listE").className="fontNormal";
	  document.getElementById("listA").className="fontBold";
	
	  document.doctorAdminMain.doctorIdN.disabled = false;
	  document.doctorAdminMain.passwordN.disabled = false;
	  document.doctorAdminMain.confirmPasswordN.disabled = false;
	  document.PhotoUpload.cancelUpload.disabled = true;
	  
	  
	  var path=datapath+"/"+photopath;
	// alert("path = "+path);
	  var fileName=document.doctorAdminMain.chkPhotoName.value;
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
	  dwr.util.setValues({doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null, photo:null,  contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	  document.getElementById("firstName").value="Dr. ";
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

function testFunction()
{
	flag="NoWindowClose";
}

function resetClicked(id)
{
	//alert(id);
	var docId = id.substring(5);
	if (confirm(sureReset))
	{
		
       // alert(docId);
        dwr.engine.beginBatch();
		DoctorManagement.resetPasswordFun(docId,function(data) {
			//alert(data);
			confirmResetPassword(data);
	      });
	  
	  dwr.engine.endBatch();
	}
}

function confirmResetPassword(data)
{
	//alert(resetyes);
	if(data != "NotUpdated")
	{
		alert(resetyes+" : "+data);
	}
	else
	{
		alert(resetno);
	}
}

