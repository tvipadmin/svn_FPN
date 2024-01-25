var status="0";
var a;
var e=0;
var patientIdwr;
var peopleCache = { };
var patientname="noSearch";
var corrfield=0,err=0;
var tempPatientId="";
var mynetwork;
var newReq;
var youHave;
var patientRef;

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}



function init(patientRefc,youHavec,newReqc,mynetworkc,loginses) {
	  //alert(loginses);
	patientRef = patientRefc;
	youHave   = youHavec;
	mynetwork =mynetworkc;
	newReq = newReqc
	  document.getElementById("sepReff").style.background="#f7941d";
	  document.getElementById("docMain").className="fontBold";
	  loadSpecialistPatient(loginses);
	  document.getElementById("errorMessage").innerHTML=""
		  
	  //document.getElementById("ageYears1").innerHTML ="";
	  //document.getElementById("photo").Value="";
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
    	document.location = 'Logout.htm'
    	//alert(exception);
    }  
}  

function newRequest()
{
	//alert("hi");
}

function loadSpecialistPatient(loginses) {
	
	var loginId=loginses;
	var h=0;
	//alert(document.getElementById("myNetworkNew").innerHTML)
	
	//alert(loginId);
	//document.familyPhyMain.patientIdN.disabled = false;
	SpecialistManagement.newRequest(loginId,function(data) {
		if(data > 0)
		{
		document.getElementById("myNetworkNew").innerHTML = mynetwork +" ("+data+" "+newReq+" )";
		}
	});
	    SpecialistManagement.loadPatient(loginId,function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("patientbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var patient,id,famphydetails,contactdetails,agegender,doctorId;
	    //alert(data);
	    data.sort(function(p1, p2) { return p1.tstamp.localeCompare(p2.tstamp); });
	    for (var i = (data.length)-1; i >= 0; i--) {
	      patient = data[i];
	      id = patient.patientId;
	      doctorName =patient.creator.firstName+" "+patient.creator.lastName;
	      doctorId = patient.creator.doctorId;
	      //alert(id);
	      famphydetails=patient.firstName+" "+patient.lastName
	      agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
	      contactdetails=patient.contactDetails.address+"\n"+patient.contactDetails.contactNumber+"\n"+patient.contactDetails.email
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tablePatId" + id, id);
	      dwr.util.setValue("tablefamphydetails" + id, famphydetails);
	      dwr.util.setValue("tableagegenderdetails" + id, agegender);
	      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
	      dwr.util.setValue("tableDoctor" + id,"");
	      document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
	      if(patient.caseReferral.flag == "NOTREADSEP")
	      {
		    	 document.getElementById("pattern"+id).className="b";
		    	 h=h+1;
		  }
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = patient;
	      if(h > 0)
	      {
	      document.getElementById ("newReff").className = 'showDiv newReff';
	  	  document.getElementById("newReff").innerHTML = youHave+ h +" "+patientRef;
	  	  setTimeout ("hide('newReff')", 4000) 
	      }
	    }
	  });
	}

function searchOnKeyPatient(loginId) {
	var patientName=document.familyPhyMain.patientNameS.value;
	//alert(patientName);
	SpecialistManagement.searchPatientdwr(patientName,loginId, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("patientbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
  // Create a new set cloned from the pattern row
  var patient,id,famphydetails,contactdetails,login,doctorId;
  
 
  data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
  
 
  for (var i = 0; i < data.length; i++) {
	
  	patient = data[i];
  	
    if(patient != null)
    {
    id = patient.patientId;
    doctorName =patient.creator.firstName+" "+patient.creator.lastName;
    doctorId = patient.creator.doctorId;
    famphydetails=patient.firstName+" "+patient.lastName
    agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
    contactdetails=patient.contactDetails.address+"\n"+patient.contactDetails.contactNumber+"\n"+patient.contactDetails.email
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tablePatId" + id, id);
    dwr.util.setValue("tablefamphydetails" + id, famphydetails);
    dwr.util.setValue("tableagegenderdetails" + id, agegender);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    dwr.util.setValue("tableDoctor" + id, "");
    document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
    $("pattern" + id).style.display = "";
    peopleCache[id] = patient;
    }
  }
});
}

function searchOnKeyPatientNo(loginId) {
	var patientName=document.familyPhyMain.patientNameS.value;
	var phoneNo = document.familyPhyMain.patientNoS.value;
	
	//alert(patientName);
	SpecialistManagement.searchPatientdwrNo(patientName,phoneNo,loginId, function(data) {
    	
  // Delete all the rows except for the "pattern" row
    dwr.util.removeAllRows("patientbody", { filter:function(tr) {
    return (tr.id != "pattern");
  }});
   
  // Create a new set cloned from the pattern row
  var patient,id,famphydetails,contactdetails,login,doctorId;
  
 
  data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
  
 
  for (var i = 0; i < data.length; i++) {
	
  	patient = data[i];
  	
    if(patient != null)
    {
    id = patient.patientId;
    doctorName =patient.creator.firstName+" "+patient.creator.lastName;
    doctorId = patient.creator.doctorId;
    famphydetails=patient.firstName+" "+patient.lastName
    agegender= patient.ageYears+":"+patient.ageMonths+" / "+patient.gender
    contactdetails=patient.contactDetails.address+"\n"+patient.contactDetails.contactNumber+"\n"+patient.contactDetails.email
    dwr.util.cloneNode("pattern", { idSuffix:id });
    dwr.util.setValue("tablePatId" + id, id);
    dwr.util.setValue("tablefamphydetails" + id, famphydetails);
    dwr.util.setValue("tableagegenderdetails" + id, agegender);
    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
    dwr.util.setValue("tableDoctor" + id, "");
    document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
    $("pattern" + id).style.display = "";
    peopleCache[id] = patient;
    }
  }
});
}


 function emrClicked(eleid,loginId) {
	 //alert("hjiiiii");
    //alert(eleid);
	// alert(loginId);
	 testFunction();
	 var patient = peopleCache[eleid.substring(4)];
	 //alert(patient.caseReferral);
	 dwr.engine.beginBatch();
	 SpecialistManagement.readPatientReferral(patient.caseReferral,function(data) {
			
	      });
	 dwr.engine.endBatch();
	 var dType="specalist";
	 //alert(patient.creator.doctorId);
	 document.location = "ViewEmr.htm?docType="+dType+"&patientName="+patient.firstName+"&patientId="+patient.patientId+"&docId="+loginId+"";
	 
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
	
}

function openWindow(doctorId) 
{ 
newwin = window.open('ViewDoctor.htm?doctorId='+doctorId,"mywindow","menubar=0,resizable=0,width=450,height=210,left=20,top=10,scrolling=yes"); 
} 



