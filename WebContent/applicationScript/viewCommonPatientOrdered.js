var status="0";
var peopleCache = { };
var labname="noSearch";
var tempLabId="";
var mynetwork;
var newReq;
var youhave;
var patReport;
var remove;
var sureRemoveReport;

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}

function init(sureRemoveReportc,removec,patReportc,youhavec,newReqc,mynetworkc,labId) {
	
	sureRemoveReport = sureRemoveReportc;
	  remove = removec;
	  patReport = patReportc;
	  youhave = youhavec
	  newReq =newReqc;
	  mynetwork =mynetworkc
	  document.getElementById("labPL").style.background="#f7941d";
	  document.getElementById("listPL").className="fontBold";
	  document.getElementById("labMain").className="fontBold";
	  loadLabPatient(labId);
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
    	alert("message: "+message);
    	alert(exception);
    	document.location = 'Logout.htm'
    }  
}  



function loadLabPatient(labId) {
	
	// alert(labId);
	//dwr.util.setValues({serviceName:null,labServiceId:null});
	var h=0;
	document.getElementById('photo').value="";
	document.labPatientList.patientNameS.value="";
	document.getElementById("fileupload_progress").innerHTML="";
    document.getElementById("progressBar").style.display = "none";
    document.getElementById("progressBarBoxContent").style.width =0;
    document.getElementById("photo1").innerHTML="";
    document.PhotoUpload.cancelUpload.disabled = true;
    
    LabServicesManagement.newRequest(labId,function(data) {
		if(data > 0)
		{
		document.getElementById("myNetworkLabNew").innerHTML = mynetwork +" ("+data+""+newReq+" )"
		}
	});
    
    
	      LabServicesManagement.loadPatient(labId,function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var labTest,id,patientName,age,gender,doctorName;
	   // data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });
	   
	   
	    for (var i = 0; i < data.length; i++) {
	    	
	    	labTest = data[i];
	     
	      id = labTest.fpcase.patient.patientId;
	      doctorName = labTest.fpcase.doctor.firstName+" "+labTest.fpcase.doctor.lastName;
	      doctorId = labTest.fpcase.doctor.doctorId;
	      //alert(doctorName);
	      patientName =  labTest.fpcase.patient.firstName+" "+labTest.fpcase.patient.lastName;
	      age = labTest.fpcase.patient.ageYears+" : "+labTest.fpcase.patient.ageMonths;
	      gender = labTest.fpcase.patient.gender;	     
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablepatientId" + id, id);
	      dwr.util.setValue("tablepatientName" + id, patientName);
	      dwr.util.setValue("tableage" + id, age);
	      dwr.util.setValue("tablegender" + id, gender);
	      dwr.util.setValue("tableDoctor" + id, doctorName);
	      document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
	      if(labTest.flag == "NOTREADLAB")
	      {
		    	 document.getElementById("pattern"+id).className="b";
		    	 h=h+1;
		  }
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = labTest;
	      if(h > 0)
	      {
	      document.getElementById ("newRefLab").className = 'showDiv newReff';
	  	  document.getElementById("newRefLab").innerHTML = youhave+ h +" "+patReport;
	  	  setTimeout ("hide('newRefLab')", 4000) 
	      }
	    }
	  });

	  
	}

function loadLabPatientFull(labId,caseid) {
	
	// alert(labId);
	// alert(caseid);
	 document.location ="PatientListLabManagement.htm?idL="+labId+"";
	}

function searchOnKeyPatientName(labId) {
	var labPatientName=document.labPatientList.patientNameS.value;
	document.getElementById('photo').value="";
	LabServicesManagement.searchPatientListdwr(labPatientName,labId, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("patientbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
    // Create a new set cloned from the pattern row
    var labTest,id,patientName,age,gender,doctorName,doctorId;
   // data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });
   
   
    for (var i = 0; i < data.length; i++) {
    	
    	labTest = data[i];
     
      id = labTest.fpcase.patient.patientId;
      doctorName = labTest.fpcase.doctor.firstName+" "+labTest.fpcase.doctor.lastName;
      doctorId = labTest.fpcase.doctor.doctorId;
      patientName =  labTest.fpcase.patient.firstName+" "+labTest.fpcase.patient.lastName;
      age = labTest.fpcase.patient.ageYears+" : "+labTest.fpcase.patient.ageMonths;
      gender = labTest.fpcase.patient.gender;	     
      dwr.util.cloneNode("pattern", { idSuffix:id });
      dwr.util.setValue("tablepatientId" + id, id);
      dwr.util.setValue("tablepatientName" + id, patientName);
      dwr.util.setValue("tableage" + id, age);
      dwr.util.setValue("tablegender" + id, gender);
      dwr.util.setValue("tableDoctor" + id, "");
      document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
      
      
      $("pattern" + id).style.display = "";
      peopleCache[id] = labTest;
    }
  });
}

function clickSeeServices(eleid)
{
	  var labTest = peopleCache[eleid.substring(6)];
	 LabServicesManagement.readPatientReferral(labTest,function(data) {
			
      });
}
function showServices(eleid,noreport,selectService,labId) {
 	  // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
	//alert(eleid.substr(0,6));
	//if(eleid.substr(0,6) == "caseId")
	//{
	
	
	 
	document.getElementById("fileupload_progress").innerHTML="";
    document.getElementById("progressBar").style.display = "none";
    document.getElementById("progressBarBoxContent").style.width =0;
    document.getElementById("photo1").innerHTML="";
    //alert(eleid);
	document.getElementById('labTestElement').value = eleid;
 	  var labTest = peopleCache[eleid.substring(6)];
 	  //alert(labTest.fpcase.caseId);
 	  var caseId=labTest.fpcase.caseId;
	//}
	//else
	//{
	//	 labTest = eleid;
	//	 caseId=labTest.fpcase.caseId;
	//}
 	 document.getElementById('caseIdVal').value=caseId;
 	 document.getElementById('labTestVal').value=labTest;
 	
 	 document.PhotoUpload.caseIdVal.value=caseId;
 	 document.getElementById('photo').value="";
 	  var listservices = new Array('');
 	 
 	  LabServicesManagement.showdwrServices(labTest, function(data) {
 		//alert("hi");
 		 dwr.util.removeAllRows("servicebody", { filter:function(tr) {
 		    return (tr.id != "pattern1");
 		  }});
 		   
 		  // Create a new set cloned from the pattern row
 		  var labtests,id,labservicesdetails,labReport,labServiceId,fromSerLabId,txtid;
 		 var labIdL = labId;
 		 // data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });
 		 // alert( data.length);
 		 //alert("login Id: "+labIdL);
 		  for (var i = 0; i < data.length; i++) {
 			//alert(i);
 			 labtests = data[i];
 		  
 		    if(labtests != null)
 		    {
 		    id = labtests.labTestId;
 		    txtid = labtests.labServices.labServiceId
 		    //alert(id );
 			fromSerLabId = labtests.labServices.lab.labId
 			//alert("fromSerLabId: "+fromSerLabId);
 			//alert(labIdL==fromSerLabId);
 			var flag = labIdL==fromSerLabId ;
 			//alert(flag);
 		    labservicesdetails=labtests.labServices.serviceName
 		    labReport =  labtests.testReport;
 		    labServiceId = labtests.labServices.labServiceId
 		    if(flag)
 		    {
 		    	
 		   if(labReport =="" || labReport==null)
           {
           	labReport=noreport
           	dwr.util.cloneNode("pattern1", { idSuffix:id });
           	dwr.util.setValue("tableServiceId" + id, labServiceId);
 		    dwr.util.setValue("tableServices" + id, labservicesdetails);
 		    dwr.util.setValue("tableReport" + id,labReport);
           }
           else
           {
           	//labReport ="<a href=\"javascript:windowOpen('"+labReport+"','labReport');\">"+labReport+"</a>"; 
           	// var element1 = document.createElement("input");    	            
            //   element1.type = "button"; 
           	 //   element1.value="Open Report";
           	 //   element1.onclick=windowOpen(labReport,'labReport');    
           	  
           	dwr.util.cloneNode("pattern1", { idSuffix:id });
           	dwr.util.setValue("tableServiceId" + id, labServiceId);
  		    dwr.util.setValue("tableServices" + id, labservicesdetails);
  		    dwr.util.setValue("tableReport" + id,"");
  		    
  		  var element1 = document.createElement("input");    	            
  	      element1.type = "hidden"; 
  	      element1.id   = "txt"+txtid
  	      //alert("txt"+txtid);
  	      
  	      //alert(document.getElementById("tableReport" + id));
  	     // var cell1 =document.getElementById("tableReport" + id);
  	     // cell1.appendChild(element1); 
  	    //document.getElementById("tableReport" + id).innerHTML="f";
  		    
  		   var labReportListtemp = labReport.split("#####");
  		   for(k=0;k<labReportListtemp.length;k++)
  		   {
  			  // alert("AAA  "+labReportListtemp[k]);
  		    document.getElementById("tableReport" + id).innerHTML="<a href=\"javascript:testFunction(); windowOpen('"+labReportListtemp[k]+"','labReport');\" onclick=\"testFunction()\" >"+labReportListtemp[k]+"</a>"+"&nbsp;&nbsp;&nbsp;"+"<a id='"+id+"' href=\"javascript:testFunction();\" onclick=\"testFunction(); removeLabReport('"+id+"','"+labId+"','"+noreport+"','"+selectService+"','"+labReportListtemp[k]+"')\">"+remove+"</a><br>"+document.getElementById("tableReport" + id).innerHTML;
  		   }
  		    document.getElementById("tableReport" + id).appendChild(element1);
  		if(labReport != null)
	      {
	    	if(labReport != "")
	    	{
	    		//alert(labReport);
	    		var labReportList = labReport.split("#####");
	    		//alert(labReportList.length);
	    		for(j=0;j<labReportList.length;j++)
	    		{
	    			//alert(labReportList[j]);
	    			document.getElementById("txt" + txtid).value=labReportList[j]+"#####"+document.getElementById("txt" + txtid).value;
	    		}
	    	}
	      }
           }
 		    
           
 		    $("pattern1" + id).style.display = "";
 		    peopleCache[id] = labtests;
 		    listservices[i]=labtests;
 		   var e = document.getElementById('service');
 		   
 		   document.getElementById('eachPatientName').innerHTML=labtests.fpcase.patient.firstName+" "+labtests.fpcase.patient.lastName;
 		   document.getElementById('patientIdVal').value=labtests.fpcase.patient.patientId;
 		   document.PhotoUpload.patientIdVal.value=labtests.fpcase.patient.patientId;
 		  //alert(i);
 		   if(i==0)
 		   {
 			  //alert("Before: "+e.length);
 	 		   for(var k =1;k<e.length;k++)
 	 		   {
 	 			   e.remove(k);
 	 			   //alert(k);
 	 		   }
 	 		  e = document.getElementById('service');
 	 		  //alert("After "+e.length);
 			   
 		    e.options[i] = new Option(selectService,"");
 		    e.options[i+1] = new Option(labservicesdetails,labServiceId);
 		  //  alert(e.options[i]);
 		  // alert(e.options[i+1]);
 		   }
 		   else
 		   {
 			  e.options[i+1] = new Option(labservicesdetails,labServiceId);
 			  //alert("inside else");
 			// alert(e.options[i+1]);
 		   }
 		    }
 		    }
 		  } 
 		
 	  });
 	 
 	}

function changeType()
{
	document.getElementById('labServiceIdVal').value=document.getElementById('service').value;
   
}

function windowOpen(fileName,attType)
{
	//alert(fileName);
	//alert(peopleCache[fileId.substring(10)]);
	 // var labTest = peopleCache[fileId.substring(10)];
	  //alert(labTest.testReport);
	  var caseId= document.getElementById('caseIdVal').value;
	  var patientId=document.getElementById('patientIdVal').value;
	  //testFunction();
	  // attwindow =window.open("OpenFile.htm?fileName="+fileName+"&patientId="+patientId+"&caseId="+caseId+"&attType="+attType+"");
	  //attwindow.close();
	  if(fileName == "" || fileName==null)
	  {
		
	  }
	  else
	  {
		  window.location="OpenFile.htm?fileName="+fileName+"&patientId="+patientId+"&caseId="+caseId+"&attType="+attType+"";
	  }
}


function updateLabServiceTest(labId,reportAdded,reportNotAdded,selectService,noReport) {

	var caseid=document.getElementById('caseIdVal').value;
	var labServiceId=document.getElementById('labServiceIdVal').value;
	var name =document.getElementById('photo').value;
	
	var labTest = document.getElementById('labTestVal').value;
	var eleid = document.getElementById('labTestElement').value;
	//alert(labTest);
	//alert(labTest.fpcase.caseId);
	if(name != null)
	{
	if(name.trim() != '')
	{
	if(labServiceId != "")
	{
		//alert(labServiceId);
		//alert(document.getElementById("txt" + labServiceId));
		var orginalList="";
		if(document.getElementById("txt" + labServiceId) != null)
		{
			orginalList = document.getElementById("txt" + labServiceId).value;
		}
		else
		{
			orginalList=null
		}
		//var orginalList = document.getElementById("txt" + labServiceId).value;
		//alert(orginalList);
		var nameList="";
		if(orginalList != null)
		{
			nameList = orginalList+name; 
		}
		else
		{
			nameList= name;
		}
		 
		//alert(nameList);
		
		dwr.engine.beginBatch();
				  LabServicesManagement.updateLabTest(caseid,labServiceId,nameList, function(data) {
			          setEditStatus(data,labId,caseid,reportAdded,reportNotAdded)
				      });
		  //loadLabPatientFull(labId,caseid);
				  showServices(eleid,noReport,selectService,labId)
		  dwr.engine.endBatch();
	}
	else
	{
		failureMessage(selectService)
	}	
	}
    }
	}



function setEditStatus(data,labId,caseid,reportAdded,reportNotAdded)
{
	if(data)
	{
		successMessage(reportAdded);
		//document.location ="PatientListLabManagement.htm?idL="+labId+"";
	}
	else
	{
		failureMessage(reportNotAdded);
	}
}


function cancelPhotoo(dataPath,labReportPath)
{
	
	  var path=dataPath+"/"+document.getElementById("patientIdVal").value+"/"+document.getElementById("caseIdVal").value+"/"+labReportPath;
	 // alert(path);
   document.getElementById("fileupload_progress").innerHTML="";
  document.getElementById("progressBar").style.display = "none";
  document.getElementById("progressBarBoxContent").style.width =0;
    var fileName=document.labPatientList.chkPhotoName.value;
    var fpath=path+"/"+fileName;
  // alert(fpath);
    UploadInfoBean.cancelPhoto(fpath);
    UploadInfoBean.setPercentage("0");
    document.labPatientList.chkPhotoName.value="";
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
	document.getElementById("service").className ="noerrorField"
}

function removeLabReport(id,labId,noreport,selectService,fileName)
{
	//alert(id);
	//alert(labId);
	//alert(noreport)
	//alert(selectService);
	//alert(fileName);
	//alert(document.getElementById('labTestVal').value);
	//alert(labId);
	var labTestTemp = id;
	var deleted="Deleted";
	var notDeleted="Not Delleted";
	var eleid = document.getElementById('labTestElement').value;
	//alert(eleid);
	 if (confirm(sureRemoveReport)){
			dwr.engine.beginBatch();
			 LabServicesManagement.deleteServiceReport(labTestTemp,fileName, function(data) {
		          //setDeleteStatus(data,deleted,notDeleted)
			      });
			showServices(eleid, noreport, selectService, labId);
		    dwr.engine.endBatch();
		  }
	
}

function openWindow(doctorId) 
{ 
newwin = window.open('ViewDoctor.htm?doctorId='+doctorId,"mywindow","menubar=0,resizable=0,width=450,height=210,left=20,top=10,scrolling=yes"); 
} 
