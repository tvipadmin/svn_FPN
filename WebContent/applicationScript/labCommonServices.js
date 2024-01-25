var viewed ="newlabservice";
var peopleCache = { };
var labname="noSearch";
var corrfield=0,err=0;
var tempLabId="";
var mynetwork;
var newReq;

function init(newReqc,mynetworkc,labId) {
	//alert(labId);
	  newReq =newReqc;
	  mynetwork =mynetworkc;
	  document.getElementById("labSer").style.background="#f7941d"; 
	  document.getElementById("listLS").className="fontBold";
	  loadLabServices(labId);
	 
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
  }  
}  

function validateLabServiceIdInputField(element,labId)
{
var id = element.id;
var value = element.value+"#####"+labId;
//alert(value);

LabServicesValidator.getInputFieldValidationMessage(id, value, {
        callback:function(dataFromServer) {
                setInputFieldStatus(element.id, dataFromServer);
             }
});

}


function validateLabServiceIdInputFieldFull(id,value,labServices,labId,updated,notupdated,addednew,notaddednew,correcterrors)
{
	  var idf = id;
	  var valuef = value+"#####"+labId;
	  LabServicesValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,labServices,labId,updated,notupdated,addednew,notaddednew,correcterrors);
	                  }
	  });
	 
}


function setInputFieldStatusfull(elementId, message,labServices,labId,updated,notupdated,addednew,notaddednew,correcterrors)
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
		   if(corrfield==1 && err==1)
				{
			      correctMessage(correcterrors,"correcterror");
				  dwr.engine.beginBatch();
				  LabServicesManagement.editeachLabService(labServices,labId, function(data) {
			          setEditStatus(data,updated,notupdated)
				      });
		  loadLabServices(labId);
		  dwr.engine.endBatch();
				}
			else if(err==1)
			  {
				failureMessage(notupdated);
			  }
		 
	  }
	  else
	  {
		  //alert("hiiiiiiiiiiiiiiiiiiiiiii");
		  if(corrfield==1 && err==1)
			{
		  dwr.engine.beginBatch();
		  LabServicesManagement.saveServiceLab(labServices,labId,function(data) {
         setSaveStatus(data,addednew,notaddednew)
		      });
	      loadLabServices(labId);
		  dwr.engine.endBatch();
			}
		  else if(err==1)
		  {
			  errorMessage(correcterrors,"correcterror");
		  }
		  
	  }
}

function loadLabServices(labId) {
	viewed ="newlabservice";
	document.getElementById("listLS").className="fontBold";
	document.getElementById("allLS").className="fontNormal";
	
	document.labServicesMain.labServiceNameS.value = "";
	 LabServicesManagement.newRequest(labId,function(data) {
			if(data > 0)
			{
			document.getElementById("myNetworkLabNew").innerHTML = mynetwork +" ("+data+""+newReq+" )"
			}
		});
	
	dwr.util.setValues({serviceName:null,labServiceId:null});
	      LabServicesManagement.loaddwrLabService(labId,function(data) {
	    	
	    // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("labservicebody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    // Create a new set cloned from the pattern row
	    var labServices,id,labservicesdetails;
	    data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });
	   
	   
	    for (var i = 0; i < data.length; i++) {
	    	
	      labServices = data[i];
	     
	      id = labServices.labServiceId;
	      
	      labservicesdetails=labServices.serviceName;
	      	     
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tableServiceId" + id, id);
	      dwr.util.setValue("tableServiceDetails" + id, labservicesdetails);
	      $("pattern" + id).style.display = "";
	      peopleCache[id] = labServices;
	    }
	  });
	}

function searchOnKeyLabService(labId) {
	var labServiceName=document.labServicesMain.labServiceNameS.value;
	
	LabServicesManagement.searchLabServicesdwr(labServiceName, function(data) {
  	
// Delete all the rows except for the "pattern" row
  dwr.util.removeAllRows("labservicebody", { filter:function(tr) {
  return (tr.id != "pattern");
}});
 
// Create a new set cloned from the pattern row
var labServices,id,labservicesdetails;

data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });


for (var i = 0; i < data.length; i++) {
	
	labServices = data[i];
	
  if(labServices != null)
  {
   if(labServices.lab.labId == labId)
   {
  id = labServices.labServiceId;
  labservicesdetails=labServices.serviceName
  dwr.util.cloneNode("pattern", { idSuffix:id });
  dwr.util.setValue("tableServiceId" + id, id);
  dwr.util.setValue("tableServiceDetails" + id, labservicesdetails);
  $("pattern" + id).style.display = "";
  peopleCache[id] = labServices;
   }
  }
}
});
}
function editClicked(eleid) {
	  // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
	  var labServices = peopleCache[eleid.substring(4)];
	  viewed ="viewed"
	  tempLabServiceId=labServices.labServiceId; 
	  dwr.util.setValues(labServices);
	}

function deleteClicked(eleid,labId,deleted,notDeleted,datadelete,question) {
	  // we were an id of the form "delete{id}", eg "delete42". We lookup the "42"
	  var labServices = peopleCache[eleid.substring(6)];
	  var name=labServices.serviceName;
	  if (confirm(datadelete +" "+ name +" "+ question)){
		dwr.engine.beginBatch();
		LabServicesManagement.deleteLabServices(labServices, function(data) {
	          setDeleteStatus(data,deleted,notDeleted)
		      });
	    loadLabServices(labId);
	    dwr.engine.endBatch();
	  }
	}



function updateLabService(labId,updated,notupdated,addednew,notaddednew,correcterrors) {
	 corrfield=0;
	 err=0;  
	 
    var labServices = {serviceName:null,labServiceId:null};
    
	  dwr.util.getValues(labServices);
	 	  
	    validateLabServiceIdInputFieldFull("serviceName",labServices.serviceName,labServices,labId,updated,notupdated,addednew,notaddednew,correcterrors);
	    
	   
	   //  alert("Action in progress");
	// alert(viewed);
	//  alert(x);
	  
	  
	}

function setSaveStatus(data,addednew,notaddednew)
{
	if(data)
	{
		successMessage(addednew);
		document.getElementById("listLS").className="fontNormal";
		document.getElementById("allLS").className="fontBold";
	}
	else
	{
		failureMessage(notaddednew);
	}
}

function setEditStatus(data,updated,notupdated)
{
	if(data)
	{
		successMessage(updated);
	}
	else
	{
		failureMessage(notupdated);
	}
}

function setDeleteStatus(data,deleted,notDeleted)
{
	//alert(data);
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

function clearLabService() {
	  document.getElementById("listLS").className="fontNormal";
	  document.getElementById("allLS").className="fontBold";
	  viewed = "newlabservice";
	  dwr.util.setValues({serviceName:null,labServiceId:null});
	  removeErrorMsg();
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
	document.getElementById("serviceName").className ="noerrorField"
}
