var viewed ="newcase";
var peopleCache = { };
var peopleCache1 = { };
var peopleCache2 = { };
var peopleCacheOne = { };
var corrfield=0,err=0;
var tempCase="";
var e=0;
var rowCount=0;
var remove;
var sureCloseCase;
var sureRemove;
var notDeleted;
var succRemoved;

function init(succRemovedc,notDeletedc,sureRemovec,sureCloseCasec,removec,caseClosed,val) {
	
	succRemoved = succRemovedc;
	notDeleted = notDeletedc;
	sureRemove = sureRemovec;
	sureCloseCase = sureCloseCasec;
	remove = removec;
if(document.getElementById("summaryM") != null  && val =='')
{
 document.getElementById("summaryM").style.background="#f7941d";
}
if(document.getElementById("secondOpiM") != null  && val =='secondOp')
{
 document.getElementById("secondOpiM").style.background="#f7941d";
}	

if(document.getElementById("labTestM") != null  && val =='labT')
{
 document.getElementById("labTestM").style.background="#f7941d";
}	

if(document.getElementById("caseDetM") != null  && val =='caseDet')
{
	document.getElementById("caseDetM").style.background="#f7941d";
}	

if(document.getElementById("edilAllM") != null  && val =='editA')
{
	document.getElementById("edilAllM").style.background="#f7941d";
}	


	//alert("hi");
	//alert("inside init  :"+caseClosed);
	//alert("hi one  "+target_upload.document.getElementById("photoNameId").value);
	  //target_upload.document.getElementById("photoNameId").value="";
	 hide('showOrder');
	 hide('showSpcealist');
	  if(caseClosed=='yes')
	  {
	  /*document.getElementById("pastIllness").disabled = true;
	  document.getElementById("familyHistory").disabled = true;
	  document.getElementById("currentMedication").disabled = true;
	  document.getElementById("immunization").disabled = true;
	  document.getElementById("allergies").disabled = true;
	  document.getElementById("complaint_symptoms").disabled = true;
	  document.getElementById("caseDescription").disabled = true;
	  document.getElementById("weight").disabled = true;
	  document.getElementById("height").disabled = true;
	  document.getElementById("temperature").disabled = true;
	  document.getElementById("bloodPressure").disabled = true;
	  document.getElementById("pulseRate").disabled = true;
	  document.getElementById("respirationRate").disabled = true;
	  document.getElementById("lmp").disabled = true;
	  document.getElementById("diagnosis").disabled = true;
	  document.getElementById("prescription").disabled = true;
	  document.getElementById("impression").disabled = true;*/
	  }
	  	  
	 // alert("hi  "+target_upload.document.getElementById("photoNameId").value);
	}

dwr.engine.setWarningHandler(null);

dwr.engine.setErrorHandler(errorHandler);  
function errorHandler(message, exception){  
	//alert("inside error Handler")
	//alert("errorhandler"+exception.javaClassName+"   "+message);
    //Session timedout/invalidated  
    if(exception != null){
        alert("exception: "+message+"    "+exception.toString());  
        //Reload or display an error etc.  
        //document.location.reload();  
    	document.location = 'Logout.htm'
    }  
}  

function highLightEmr(val)
{
	if(val == 'caseDet')
	{
		document.getElementById("caseDetM").style.background="#f7941d";
		document.getElementById("historyM").style.background="#27abb7";
		document.getElementById("vitalSignM").style.background="#27abb7";
		document.getElementById("labTestM").style.background="#27abb7";
		document.getElementById("caseDiagM").style.background="#27abb7";
		document.getElementById("secondOpiM").style.background="#27abb7";
		document.getElementById("edilAllM").style.background="#27abb7";
		document.getElementById("summaryM").style.background="#27abb7";
	}
	if(val == 'history')
	{
		document.getElementById("caseDetM").style.background="#27abb7";
		document.getElementById("historyM").style.background="#f7941d";
		document.getElementById("vitalSignM").style.background="#27abb7";
		document.getElementById("labTestM").style.background="#27abb7";
		document.getElementById("caseDiagM").style.background="#27abb7";
		document.getElementById("secondOpiM").style.background="#27abb7";
		document.getElementById("edilAllM").style.background="#27abb7";
		document.getElementById("summaryM").style.background="#27abb7";
	}
	if(val == 'vitalSign')
	{
		document.getElementById("caseDetM").style.background="#27abb7";
		document.getElementById("historyM").style.background="#27abb7";
		document.getElementById("vitalSignM").style.background="#f7941d";
		document.getElementById("labTestM").style.background="#27abb7";
		document.getElementById("caseDiagM").style.background="#27abb7";
		document.getElementById("secondOpiM").style.background="#27abb7";
		document.getElementById("edilAllM").style.background="#27abb7";
		document.getElementById("summaryM").style.background="#27abb7";
	}
	if(val == 'labtest')
	{
		if(document.getElementById("caseDetM") != null)
		{
		document.getElementById("caseDetM").style.background="#27abb7";
		}
		if(document.getElementById("historyM") != null)
		{
			document.getElementById("historyM").style.background="#27abb7";
		}
		if(document.getElementById("vitalSignM") != null)
		{
			document.getElementById("vitalSignM").style.background="#27abb7";
		}
		
		if(document.getElementById("caseDiagM") != null)
		{
			document.getElementById("caseDiagM").style.background="#27abb7";
		}
		
		if(document.getElementById("edilAllM") != null)
		{
			document.getElementById("edilAllM").style.background="#27abb7";
		}
		
		document.getElementById("labTestM").style.background="#f7941d";
		
		document.getElementById("secondOpiM").style.background="#27abb7";
		
		document.getElementById("summaryM").style.background="#27abb7";
	}
	if(val == 'caseDiag')
	{
		document.getElementById("caseDetM").style.background="#27abb7";
		document.getElementById("historyM").style.background="#27abb7";
		document.getElementById("vitalSignM").style.background="#27abb7";
		document.getElementById("labTestM").style.background="#27abb7";
		document.getElementById("caseDiagM").style.background="#f7941d";
		document.getElementById("secondOpiM").style.background="#27abb7";
		document.getElementById("edilAllM").style.background="#27abb7";
		document.getElementById("summaryM").style.background="#27abb7";
	}
	if(val == 'secondOpinion')
	{
		if(document.getElementById("caseDetM") != null)
		{
		document.getElementById("caseDetM").style.background="#27abb7";
		}
		if(document.getElementById("historyM") != null)
		{
			document.getElementById("historyM").style.background="#27abb7";
		}
		if(document.getElementById("vitalSignM") != null)
		{
			document.getElementById("vitalSignM").style.background="#27abb7";
		}
		
		if(document.getElementById("caseDiagM") != null)
		{
			document.getElementById("caseDiagM").style.background="#27abb7";
		}
		
		if(document.getElementById("edilAllM") != null)
		{
			document.getElementById("edilAllM").style.background="#27abb7";
		}
		
		document.getElementById("labTestM").style.background="#27abb7";
		
		document.getElementById("secondOpiM").style.background="#f7941d";
		
		document.getElementById("summaryM").style.background="#27abb7";
	}
	if(val == 'editAll')
	{
		document.getElementById("caseDetM").style.background="#27abb7";
		document.getElementById("historyM").style.background="#27abb7";
		document.getElementById("vitalSignM").style.background="#27abb7";
		document.getElementById("labTestM").style.background="#27abb7";
		document.getElementById("caseDiagM").style.background="#27abb7";
		document.getElementById("secondOpiM").style.background="#27abb7";
		document.getElementById("edilAllM").style.background="#f7941d";
		document.getElementById("summaryM").style.background="#27abb7";
	}
	if(val == 'summaryM')
	{
		if(document.getElementById("caseDetM") != null)
		{
		document.getElementById("caseDetM").style.background="#27abb7";
		}
		if(document.getElementById("historyM") != null)
		{
			document.getElementById("historyM").style.background="#27abb7";
		}
		if(document.getElementById("vitalSignM") != null)
		{
			document.getElementById("vitalSignM").style.background="#27abb7";
		}
		
		if(document.getElementById("caseDiagM") != null)
		{
			document.getElementById("caseDiagM").style.background="#27abb7";
		}
		
		if(document.getElementById("edilAllM") != null)
		{
			document.getElementById("edilAllM").style.background="#27abb7";
		}
		document.getElementById("labTestM").style.background="#27abb7";
		document.getElementById("secondOpiM").style.background="#27abb7";
		document.getElementById("summaryM").style.background="#f7941d";
	}
		
	
}
// remove it later
function insRow()
{
var x=document.getElementById('historyTable').insertRow(5);
var y=x.insertCell(0);
var z=x.insertCell(1);
y.innerHTML="NEW CELL1";
z.innerHTML="NEW CELL2";
}

function setName(string,type){
	
    var name=string
    //alert(string);
    //alert(type);
    //alert(string=='null');
    if(type=="history")
    {
    if(string != 'null' && string !="")
    {
    document.getElementById('attachments').value=name+"#####"+document.getElementById('attachmentCollection').value;
    document.getElementById('attachmentCollection').value=name+"#####"+document.getElementById('attachmentCollection').value;
    //var x=document.getElementById('historyTable').insertRow(1);
   // var y=x.insertCell(0);
   // var z=x.insertCell(1);
   // y.innerHTML= "<a href=\"javascript:connect('"+name+"');\">"+name+"</a>";
   // z.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a href=\"javascript:removeHistoryAtt('"+name+"');\">"+remove+"</a>";
    var table = document.getElementById('historyTable');          
    var rowCount = table.rows.length;     	              
    var row = table.insertRow(rowCount);    	           
    var cell1 = row.insertCell(0);    	               
    var element1 = document.createElement("input");    	            
    element1.type = "hidden"; 
    element1.value=name;     
    cell1.appendChild(element1); 
    var cell2 = row.insertCell(1); 
 	cell2.innerHTML = "<a href=\"javascript:testFunction(); windowOpen('"+name+"','history');\" onclick=\"javascript:testFunction()\">"+name+"</a>"; 
    var cell3 = row.insertCell(2); 
    cell3.innerHTML ="<a href=\"javascript:testFunction(); removeHistoryAtt('"+name+"');\" onclick=\"javascript:testFunction()\">"+remove+"</a>";
    }
    }
    else if(type=="case")
    {
    	  if(string != 'null' && string !="")
    	    {
    		  //alert("case: "+string);
    	    document.getElementById('caseattachments').value=name+"#####"+document.getElementById('caseattachmentCollection').value;
    	    document.getElementById('caseattachmentCollection').value=name+"#####"+document.getElementById('caseattachmentCollection').value;
    	    
    	    document.getElementById('newcaseattachment').value=name+"#####"+document.getElementById('newcaseattachment').value;
    	    
    	   
    	    
    	  //  var x1=document.getElementById('caseTable').insertRow(1);
         //   var y1=x1.insertCell(0);
    	   // var z1=x1.insertCell(1);
    	  //  y1.innerHTML= "<a href=\"javascript:connect('"+name+"');\">"+name+"</a>";
    	  //  var xr1=document.getElementById('caseTable')
    	  //  rowCount=rowCount+1;
    	  //  var val = rowCount;    	    
    	   // z1.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a href=\"javascript:removeCaseAtt('"+name+"','','','"+val+"');\">"+remove+"</a>";

    	    var table = document.getElementById('caseTable');          
    	    var rowCount = table.rows.length;     	              
    	    var row = table.insertRow(rowCount);    	           
    	    var cell1 = row.insertCell(0);    	               
    	    var element1 = document.createElement("input");    	            
    	    element1.type = "hidden"; 
    	    element1.value=name;     
    	    cell1.appendChild(element1); 
    	    var cell2 = row.insertCell(1); 
         	cell2.innerHTML = "<a href=\"javascript:testFunction(); windowOpen('"+name+"','case');\" onclick=\"javascript:testFunction()\">"+name+"</a>"; 
    	    var cell3 = row.insertCell(2); 
    	    cell3.innerHTML ="<a href=\"javascript:testFunction(); removeCaseAtt('"+name+"');\" onclick=\"javascript:testFunction()\">"+remove+"</a>";
    	    }
    }
  }

function validateCaseInputField(element)
{
  var id = element.id;
  var value = element.value;
  //alert(id);
 // alert(value);
  CaseManagementValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
                  setInputFieldStatus(element.id, dataFromServer);
               }
  });
}

function validateCaseInputFieldFull(id,value,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId)
{
	  var idf = id;
	  var valuef = value;
	  //alert("hello"+id +"  "+valuef);
	 
	  CaseManagementValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	                  }
	  });
}

function setInputFieldStatusfull(elementId, message,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId)
{

	 var tempvab = message
	 var flag = false;
	 var loginPer = doctorId;
	  err=err+1;
	  //alert("last method   " +message +"elementId : "+elementId);
	  //alert(caseReferral.opinions);
	  if(message == "noerror")
	   {
		   corrfield=corrfield+1;
		   correctMessage(message,elementId);
	       message="";
	       
	   }	
	   var id =elementId+"1";
	   //alert("one");
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
				else
				{
					flag = true;
				}
			}
					
		 }
		  if(flag)
		  {	
		    errorMessage(message,elementId);
	      }
	     
	   }
	   //alert("two");
   
   var labServiceIds = document.getElementById('labServiceIds').value;  
   //alert("three");
   var docdetIds = document.getElementById('docIds').value; 
   //alert("four");
   var docTypes = document.getElementById('docType').value; 
   //alert("five");
   var caseReferralIdt="";
   if(docType == 'specalist')
   {
	   //alert("six");
	   //alert(document.getElementById('caseReferralId').value);
	   caseReferralIdt = document.getElementById('caseReferralId').value;  
	   //alert("person : "+doctorId);
	  // alert(fpDocId);
	   doctorId=fpDocId;
	  // alert(caseReferralIdt);
   }
   else
   {
	  // alert("seven");
	   
	   caseReferralIdt="";
   }
   
   //alert("value of corrfield: "+corrfield);
  // alert("value of err: "+err);
   //alert(section);
	  if(corrfield==8 && err==8)
			{
			  dwr.engine.beginBatch();
			  if(document.getElementById('newcaseattachment') != null)
			  {
			  fpcase.caseattachments =  document.getElementById('newcaseattachment').value;
			  }
			  //alert(docdetIds);
			      CaseManagement.saveCase(fpcase,history,patientId,doctorId,labServiceIds,docdetIds,caseReferral,docTypes,caseReferralIdt,function(data) {
		                 setSaveStatus(data,patientId,loginPer,addednew,notaddednew,fpcase.caseId,section,docType,docdetIds)
					      });
		      dwr.engine.endBatch();
			}
		  else if(err==8)
		  {
			  //alert("Not Added Correct The Errors...");
			  errorMessage(correcterrors,"correcterror");
		  }
	 // alert("eight");
}


function checkCase(patientId,doctorId,historydisplay,addednew,notaddednew,correcterrors,section,docType,fpDocId) {
	
	//alert(document.getElementById('caseStatus').value);
	//alert(document.getElementById('caseStatus').value == 'OPEN');
	//alert(document.getElementById('caseId').value);
	if(document.getElementById('caseStatus').value == 'OPEN')
	{
	 
		updateCase(patientId,doctorId,historydisplay,addednew,notaddednew,correcterrors,section,docType,fpDocId) 
	
	}
	else
	{
		if(section == 'nothingFp')
		{
		     document.location='FamilyPhysicianManagement.htm';
		}
		else if(section == 'nothingSep')
		{
			 document.location='SpecalistManagement.htm';
		}
	}
}

function updateCase(patientId,doctorId,historydisplay,addednew,notaddednew,correcterrors,section,docType,fpDocId) {
	
	 corrfield=0,err=0;
     var fpcase ={caseId:null,caseStatus:null,starttstamp:null,endtstamp:null,complaint_symptoms:null, caseDescription:null, weight:null, height:null, temperature:null, bloodPressure:null, pulseRate:null, respirationRate:null, lmp:null, diagnosis:null, prescription:null, impression:null,caseattachments:null,caseTitle:null};
     var history = {pastIllness:null, familyHistory:null, currentMedication:null, immunization:null, allergies:null, attachments:null };    
	 var caseReferral ={opinions:null};
	 
     dwr.util.getValues(fpcase);
	 dwr.util.getValues(history);
	 dwr.util.getValues(caseReferral);
	 //alert("hi");	 
	
	 //alert(section); 
	 validateCaseInputFieldFull("weight",fpcase.weight,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 validateCaseInputFieldFull("height",fpcase.height,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 validateCaseInputFieldFull("temperature",fpcase.temperature,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 validateCaseInputFieldFull("bloodPressure",fpcase.bloodPressure,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 validateCaseInputFieldFull("pulseRate",fpcase.pulseRate,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 validateCaseInputFieldFull("respirationRate",fpcase.respirationRate,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 validateCaseInputFieldFull("lmp",fpcase.lmp,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 validateCaseInputFieldFull("caseTitle",fpcase.caseTitle,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	 //validateCaseInputFieldFull("caseTitle",fpcase.lmp,patientId,doctorId,historydisplay,fpcase,history,addednew,notaddednew,correcterrors,section,caseReferral,docType,fpDocId);
	   // alert("Action in progress");
	 
	}

function printSummary(patientId,doctorId,docType,fpDocId,caseId)
{
	testFunction();
	//alert("hi");
	var dType=docType;
	document.location = 'SummaryPrint.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+''
}

function printPage(patientId,caseId,docType,doctorId)
{
	//alert("hello");
	//alert(patientId);
	obj = document.getElementById('buttons');
	//obj.style.display="none";
	window.print();
	var dType=docType;
	document.location = 'ViewEmr.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+'';
	//obj.style.display="";
}

function setSaveStatus(data,patientId,doctorId,addednew,notaddednew,caseId,section,docType,docdetIds)
{
	if(data)
	{
		//alert("New Record Added"+section);
		
		var dType=docType;
		 successMessage(addednew);
		 if(section == "FPCase")
		 {
			 document.location = 'ViewEmr.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+'&section='+section+''
		 }
		 else if(section == "secondOpinion")
		 {
			 //alert("hi"+docdetIds);
			 callSendMsg(docdetIds);
			// alert("hello");
			 document.location = 'ViewEmr.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+'&section='+section+''
			 
		 }
		 else if(section == "labTest")
		 {
			 document.location = 'ViewEmr.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+'&section='+section+''
		 }
		 else if(section == "allSave")
		 {
		  document.location = 'ViewEmr.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+'&section='+section+''
		 }
		 else if(section == "Summary")
		 {
			 document.location = 'ViewEmr.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+''
		 }
		 else if(section == "NoSection")
		 {
			 
		 }	
		 else if(section == "FromOption")
		 {
			 
		 }
		 else if(section == "nothingFp")
		 {
			 document.location='FamilyPhysicianManagement.htm'
		 }
		 else if(section == "nothingSep")
		 {
			 document.location='SpecalistManagement.htm'
		 }
		 else if(section == "nothingLogout")
		 {
			 document.location='Logout.htm'
		 }
		 else if(section == "nothingHelp")
		 {
			 document.location='Logout.htm'
		 }
		 else if(section == "nothingSettings")
		 {
			 document.location='Logout.htm'
		 }
		 else if(section == "caseDiagnosis")
		 {
			 show('saveSection');
		 }
		 else 
		 {
			 hide('saveSection');
		 }
		 
	}
	else
	{
		 //alert("New Record Not Added");
		 failureMessage(notaddednew);
	}
}

function closeCase(caseId,caseClosed,caseNotClosed)
{
	if (confirm(sureCloseCase)){
		dwr.engine.beginBatch();
		  CaseManagement.closeOpenCase(caseId,function(data) {
	       setCloseStatus(data,caseClosed,caseNotClosed)
		      });
	    dwr.engine.endBatch();
	  }
}

function setCloseStatus(data,caseClosed,caseNotClosed)
{
	if(data)
	{
		 //alert("Case Successfully Closed.");
		 successMessage(caseClosed);
		 testFunction();
		 document.location = 'FamilyPhysicianManagement.htm';
	}
	else
	{
		failureMessage(caseNotClosed);
		 //alert("Case Not Closed");
	}
}

function summaryData(patientId,doctorId,historydisplay,addednew,notaddednew,correcterrors,section,docType,fpDocId)
{
	var caseId= document.getElementById('caseId').value;
	var dType=docType;
	
	updateCase(patientId,doctorId,historydisplay,addednew,notaddednew,correcterrors,section,docType,fpDocId);
	
		//document.location = 'ViewEmr.htm?docType='+dType+'&patientId='+patientId+'&docId='+doctorId+'&caseId='+caseId+'';
	
}


function removeCaseAtt(filename)
{
	//alert("hello");
	if (confirm(sureRemove)){
   var table = document.getElementById('caseTable'); 
   var rowCount = table.rows.length; 
   var tempCount ;
   var caseId= document.getElementById('caseId').value;
   var patientId=document.getElementById('patientId').value;
   var hisAtt = document.getElementById('caseattachments').value;
   var newListTemp = document.getElementById('newcaseattachment').value;
   var filelist = hisAtt.split("#####");
   var newTempFilelist =newListTemp.split("#####");
  //alert("orginal List: "+hisAtt);
  // alert("hi");
for(var i=1; i<rowCount; i++) { 
	//alert("start of for loop");    
var row = table.rows[i]; 
   //alert("row : "+row);           
var chkbox = row.cells[0].childNodes[0]; 
  //alert("chk value"+chkbox.value);   
 //alert("File Name: "+filename);     
if(null != chkbox &&  chkbox.value == filename ) { 
	var newfilelist="",newtemplist="";
	 table.deleteRow(i);
	 //alert("after table rowdelete");
	 for(var k=0;k<filelist.length;k++)
		{
		 var oldfile=filelist[k];
		  
		  if(!(oldfile == filename))
		  {
			  if(newfilelist=="")
			  {
				 newfilelist = oldfile; 
			  }
			  else
			  {
			  newfilelist=newfilelist+"#####"+oldfile;
			  }
		  }
		}
	 //alert(newTempFilelist.length);
	 for(var g=0;g<newTempFilelist.length;g++)
		{
		 var oldtempfile=newTempFilelist[g];
		  //alert("1: "+oldtempfile);
		  //alert("2: "+filename);
		  if(!(oldtempfile == filename))
		  {
			  if(newtemplist=="")
			  {
				  newtemplist = oldtempfile; 
			  }
			  else
			  {
				  newtemplist=newtemplist+"#####"+oldtempfile;
			  }
		  }
		}
	 
	 //alert("new File List: "+newfilelist);
	 //alert(newtemplist);
	 document.getElementById('caseattachments').value=newfilelist;
	 document.getElementById('newcaseattachment').value=newtemplist;
	 //alert("here....");
 	   dwr.engine.beginBatch();
	   CaseManagement.deleteCaseAtt(filename,caseId,patientId,function(data) {
         setDeleteStatus(data,table,i)
			      });
	   dwr.engine.endBatch();
	   //alert("row count"+ rowCount);
rowCount--; 
i--;
//alert("after decrement row count"+ rowCount);
}
//alert("end of If Loop");
} 
//alert("end of function");
	}
}

function removeHistoryAtt(filename)
{
	if (confirm(sureRemove)){
   var table = document.getElementById('historyTable'); 
   var rowCount = table.rows.length; 
   var caseId= document.getElementById('caseId').value;
   var patientId=document.getElementById('patientId').value;
   var hisAtt = document.getElementById('attachments').value;
   var filelist = hisAtt.split("#####");
      
for(var i=1; i<rowCount; i++) { 
            
var row = table.rows[i]; 
              
var chkbox = row.cells[0].childNodes[0]; 
 //alert("chk value"+chkbox.value);   
 // alert(filename);     
if(null != chkbox &&  chkbox.value == filename ) { 
	var newfilelist="";
	 table.deleteRow(i);
	 for(var k=0;k<filelist.length;k++)
		{
		 var oldfile=filelist[k];
		  
		  if(!(oldfile == filename))
		  {
			  if(newfilelist=="")
			  {
				 newfilelist = oldfile; 
			  }
			  else
			  {
			  newfilelist=newfilelist+"#####"+oldfile;
			  }
		  }
		}
	// alert(newfilelist);
	 document.getElementById('attachments').value=newfilelist;
 	   dwr.engine.beginBatch();
	   CaseManagement.deleteHistoryAtt(filename,caseId,patientId,function(data) {
         setDeleteStatus(data,table,i)
			      });
	   dwr.engine.endBatch();
 
rowCount--; 
i--; 
} 
}
}
}

function setDeleteStatus(data,table,i)
{
	if(data)
	{
		//alert(data);
		//alert("inside setDeleteStatus");
	}
	else
	{
		 alert(notDeleted);
	}
}

function windowOpen(fileName,attType)
{
	  var caseId= document.getElementById('caseId').value;
	   var patientId=document.getElementById('patientId').value;
	  // attwindow =window.open("OpenFile.htm?fileName="+fileName+"&patientId="+patientId+"&caseId="+caseId+"&attType="+attType+"");
	  //attwindow.close();
		window.location="OpenFile.htm?fileName="+fileName+"&patientId="+patientId+"&caseId="+caseId+"&attType="+attType+"";
}

function showLabs(loginId)
{
	       // dwr.util.setValues({labId:null, labName:null,contactPerson:null,photo:null, contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	      // alert("one: "+loginId);
	       //alert(loginId);
	       show('orderContent');
	        LabManagement.loaddwrLabsLogin(loginId,function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		     dwr.util.removeAllRows("labbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var lab,id,docdetails,contactdetails;
		    data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
		    //alert("two"+data.length);
		   if(data.length == 0)
		   {
			   show('showOrder');
		   }
		   else
		   {
		    for (var i = 0; i < data.length; i++) {
		    	
		      lab = data[i];
		     
		      id = lab.labId;
		      
		      docdetails=lab.labName
		     // alert("three:   "+id)
		      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		     
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tableDocDetails" + id, docdetails);
		      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
		      //alert("four:  "+id)
		      $("pattern" + id).style.display = "";
		      //alert("five")
		      peopleCache[id] = lab;
		     // alert("six");
		      if(i == 0)
		      {
		    	  LabManagement.loaddwrLabsLoginLab(loginId,lab.labId,function(data) {
		    			 dwr.util.removeAllRows("labbodyOne", { filter:function(tr) {
		    			      return (tr.id != "patternOne");
		    			    }});
		    			for (var i1 = 0; i1 < data.length; i1++) {
		    		    	
		    			      lab = data[i1];
		    			     
		    			      id = lab.labId;
		    			      docdetailsD=lab.labName
		    			     //alert("three:   "+id)
		    			     // alert("three:   "+idP)
		    			      contactdetailsD=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		    			    
		    			      dwr.util.cloneNode("patternOne", { idSuffix:id });
		    			      dwr.util.setValue("tableDocDetailsOne" + id, docdetailsD);
		    			      dwr.util.setValue("tableDocContactDetailsOne" + id, contactdetailsD);
		    			      //alert("four:  "+id)
		    			      $("patternOne" + id).style.display = "";
		    			      //alert("five")
		    			      peopleCacheOne[id] = lab;
		    			     
		    			     // alert("six");
		    			    }
		    		});
		    	  
		    	  LabServicesManagement.showLabServices(lab,function(data) {
		  	    	
		  		    // Delete all the rows except for the "pattern" row
		  		      dwr.util.removeAllRows("labservicebody", { filter:function(tr) {
		  		      return (tr.id != "pattern1");
		  		    }});
		  		    
		  		    // Create a new set cloned from the pattern row
		  		    var labServices,id,labservicesdetails,labServiceId,contactPerson;
		  		 
		  		  data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });
		  		   
		  		    for (var i2 = 0; i2 < data.length; i2++) {
		  		    	
		  			labServices = data[i2];
		  			contactPerson=labServices.lab.contactPerson;
		  			id = labServices.labServiceId;
		  			
		  			labservicesdetails=labServices.serviceName
		  			//alert(labservicesdetails);
		  			labServiceId =labServices.labServiceId
		  			dwr.util.cloneNode("pattern1", { idSuffix:id });
		  			dwr.util.setValue("tableServiceDetails" + id, labservicesdetails);
		  			//dwr.util.setValue("tableContactPerson"+id,contactPerson);      
		  			$("pattern1" + id).style.display = "";
		  			
		  			//peopleCache1[id] = labServices;
		  			
		  			document.getElementById("valId").checked=false;
		  		    }
		  		  });
		      }
		    }
		   }
		  });
}

function showLabDetails(loginId,eleid)
{
	//alert("hi");
	  var labP = peopleCache[eleid.substring(11)];
	LabManagement.loaddwrLabsLoginLab(loginId,labP.labId,function(data) {
		 dwr.util.removeAllRows("labbodyOne", { filter:function(tr) {
		      return (tr.id != "patternOne");
		    }});
		for (var i = 0; i < data.length; i++) {
	    	
		      lab = data[i];
		     
		      id = lab.labId;
		      docdetailsD=lab.labName
		     //alert("three:   "+id)
		     // alert("three:   "+idP)
		      contactdetailsD=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		    
		      dwr.util.cloneNode("patternOne", { idSuffix:id });
		      dwr.util.setValue("tableDocDetailsOne" + id, docdetailsD);
		      dwr.util.setValue("tableDocContactDetailsOne" + id, contactdetailsD);
		      //alert("four:  "+id)
		      $("patternOne" + id).style.display = "";
		      //alert("five")
		      peopleCacheOne[id] = lab;
		     
		     // alert("six");
		    }
	});
}

function showServices(eleid)
{
	       // dwr.util.setValues({labId:null, labName:null,contactPerson:null,photo:null, contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	        var lab = peopleCache[eleid.substring(11)];
	       //alert("showServices");
	        dwr.util.setValues({serviceName:null,labServiceId:null});
	        
	        
	       
	        LabServicesManagement.showLabServices(lab,function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("labservicebody", { filter:function(tr) {
		      return (tr.id != "pattern1");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var labServices,id,labservicesdetails,labServiceId,contactPerson;
		 
		  data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });
		   
		    for (var i = 0; i < data.length; i++) {
		    	
			labServices = data[i];
			contactPerson=labServices.lab.contactPerson;
			id = labServices.labServiceId;
			
			labservicesdetails=labServices.serviceName
			//alert(labservicesdetails);
			labServiceId =labServices.labServiceId
			dwr.util.cloneNode("pattern1", { idSuffix:id });
			dwr.util.setValue("tableServiceDetails" + id, labservicesdetails);
			//dwr.util.setValue("tableContactPerson"+id,contactPerson);      
			$("pattern1" + id).style.display = "";
			
			peopleCache1[id] = labServices;
			
			document.getElementById("valId").checked=false;
		    }
		  });
}

function showServicesCreated(eleid)
{
	       // dwr.util.setValues({labId:null, labName:null,contactPerson:null,photo:null, contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	        var lab = peopleCache[eleid.substring(11)];
	       
	        dwr.util.setValues({serviceName:null,labServiceId:null});
	       
	        LabServicesManagement.showLabServices(lab,function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("labservicebody", { filter:function(tr) {
		      return (tr.id != "pattern1");
		    }});
		    
			// Create a new set cloned from the pattern row
			var labServices,id,labservicesdetails,labServiceId;
			
			data.sort(function(p1, p2) { return p1.serviceName.localeCompare(p2.serviceName); });
			
			for (var i = 0; i < data.length; i++) {
			
			labServices = data[i];
			
			id = labServices.labServiceId;
			
			labservicesdetails=labServices.serviceName
			//alert(labservicesdetails);
			labServiceId =labServices.labServiceId
			var table = document.getElementById('labTestTable'); 
			var rowCount1 = table.rows.length; 
			//alert(rowCount1);
			
			
			
			dwr.util.cloneNode("pattern1", { idSuffix:id });
			dwr.util.setValue("tableServiceDetails" + id, labservicesdetails);
			// dwr.util.setValue("valId"+id,id); 
			 
			$("pattern1" + id).style.display = "";
			
			peopleCache1[id] = labServices;
			for(var j=1; j<rowCount1; j++) { 
			
				var row = table.rows[j]; 
							  
				var chkbox = row.cells[0].childNodes[0]; 
				//alert("val : "+chkbox.value);
				//alert(labServiceId);
				 // alert("chk value"+chkbox.value);   
				 // alert(filename);     
				if(null != chkbox &&  chkbox.value == labServiceId ) { 
					document.getElementById("valId"+id).disabled = true;
				rowCount1--; 
				j--; 
				} 
				} 
		    }
		  });
}

function  getTestExists(orderlistArray,labserviceId)
{
	var flag=false;
	for(j=1;j<=orderlistArray.length;j++)
	{
		if(orderlistArray[j] == labserviceId)
		{
			flag=true;
			break;
		}
	}

	return flag;
}

function getRemovedList(orderlistArray,labserviceId)
{
	var temp="";
	//alert("orderlistArray.length :  "+orderlistArray.length);
	//for(j=0;j<orderlistArray.length;j++)
	//{
	//	alert(j+"  "+orderlistArray[j]);
	//}
	for(j=1;j<orderlistArray.length;j++)
	{
		if(!(orderlistArray[j] == labserviceId))
		{
			//alert("tempVal"+temp);
			if(temp=="")
			{
				temp="#####"+orderlistArray[j];
				//alert("if loop"+temp);
			}
			else
			{
				//alert("tempVal"+temp);
				temp=temp+"#####"+orderlistArray[j];
				//alert("else loop"+temp+"    "+orderlistArray[j]);
			}
		}
	}
	return temp;
}

function appendValue(eleid)
{
	var labserviceId=eleid.replace("valId","");
	var orderlist =document.getElementById('labServiceIds').value;
	var tempList;
	var orderlistArray  =  orderlist.split("#####");
	var temporderlist = orderlist.split("#####");

	var flag =getTestExists(orderlistArray,labserviceId);

	if(flag)
	{
		tempList=getRemovedList(orderlistArray,labserviceId);
	}
	else
	{
		tempList=orderlist+"#####"+labserviceId
	}
	document.getElementById('labServiceIds').value=tempList;

}

function removeOrderedTest(labServiceId)
{
	var tableservices = document.getElementById('serviceTable');
	var tablelabdata = document.getElementById('labTable');
	
	var rowCountservices = tableservices.rows.length;  
//	var rowCountlab = tablelabdata.rows.length;
	if (confirm(sureRemove)){
	for(var k=1; k<rowCountservices; k++) {
		tableservices.deleteRow(0);
	}

	//for(var j=1; j<rowCountlab; j++) {
	//	tablelabdata.deleteRow(0);
//	}
	
	 dwr.util.removeAllRows("labbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
	//dwr.util.removeAllRows("labbody");
	//dwr.util.removeAllRows("labservicebody");
	
	   var table = document.getElementById('labTestTable'); 
	   var rowCount = table.rows.length; 
	   //alert(rowCount);
	      
	for(var i=1; i<rowCount; i++) { 
	            
	var row = table.rows[i]; 
	              
	var chkbox = row.cells[0].childNodes[0]; 
	//alert(chkbox.value);
	//alert(labServiceId);
	 // alert("chk value"+chkbox.value);   
	 // alert(filename);     
	if(null != chkbox &&  chkbox.value == labServiceId ) { 
		table.deleteRow(i);
		dwr.engine.beginBatch();
		LabServicesManagement.deleteLabTest(labServiceId,function(data) {
			//alert(data);
		//setDeleteStatus(data,table,i)
			  if(data)
			  {
				  //table.deleteRow(i);
				  successMessage(succRemoved);
			  }
			  
				  });
		dwr.engine.endBatch();
	 
	rowCount--; 
	i--; 
	} 
	} 
	}
}

function showSecondOpinion(loginId)
{
	//alert(loginId);
	        CaseManagement.showSpecalistLogin(loginId,function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalitybody", { filter:function(tr) {
		      return (tr.id != "pattern2");
		    }});
		    
		    // Create a new set cloned from the pattern row
		    var doctor,id,specality,doctordetails;
		 //alert(data);
		  data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
		   //alert("hello");
		   //alert("data.length"+data.length);
		   if(data.length == 0)
		   {
			   show('showSpcealist');
		   }
		   else
		   {
		    for (var i = 0; i < data.length; i++) {
			  
		    	
		    	doctor = data[i];
		    	//alert("doctor"+doctor);
			      id = doctor.doctorId;
			      if(id != loginId)
			      {
			      //alert("id: "+id);
			      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
			      //alert("docdetails: "+docdetails);
			      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
			      //alert("contactdetails: "+contactdetails);

			      var table = document.getElementById('specalityTable'); 
				  var rowCount1 = table.rows.length; 

			      
			      dwr.util.cloneNode("pattern2", { idSuffix:id });
			      dwr.util.setValue("tableDocDetails" + id, docdetails);
			      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
			      $("pattern2" + id).style.display = "";
			      peopleCache2[id] = doctor;

			      for(var j=1; j<rowCount1; j++) { 
			            
						var row = table.rows[j]; 
						              
						var chkbox = row.cells[0].childNodes[0]; 
						//alert("val : "+chkbox.value);
						//alert(labServiceId);
						 // alert("chk value"+chkbox.value);   
						 // alert(filename);     
						if(null != chkbox &&  chkbox.value == id ) { 
							document.getElementById("docId"+id).disabled = true;
						 
						rowCount1--; 
						                
						j--; 
						            
						} 
			      }
			      
                  document.getElementById("docId").checked=false;
		    }
		    }
		   }
		  });
			  
}

function appendDocValue(eleid)
{
	var docId=eleid.replace("docId","");
	var doclist =document.getElementById('docIds').value;
	var tempList;
	var doclistArray  =  doclist.split("#####");
	var temporderlist = doclist.split("#####");
	var flag =getdocTestExists(doclistArray,docId);
	
	//alert(flag);

	if(flag)
	{
		tempList=getdocRemovedList(doclistArray,docId);
	}
	else
	{
		tempList=doclist+"#####"+docId
	}
	document.getElementById('docIds').value=tempList;
}

function  getdocTestExists(doclistArray,docId)
{
	var flag=false;
	for(j=1;j<=doclistArray.length;j++)
	{
		if(doclistArray[j] == docId)
		{
			flag=true;
			break;
		}
	}
	return flag;
}

function getdocRemovedList(doclistArray,docId)
{
	var temp="";
	//alert("orderlistArray.length :  "+orderlistArray.length);
	//for(j=0;j<orderlistArray.length;j++)
	//{
	//	alert(j+"  "+orderlistArray[j]);
	//}
	for(j=1;j<doclistArray.length;j++)
	{
		if(!(doclistArray[j] == docId))
		{
			//alert("tempVal"+temp);
			if(temp=="")
			{
				temp="#####"+doclistArray[j];
				//alert("if loop"+temp);
			}
			else
			{
				//alert("tempVal"+temp);
				temp=temp+"#####"+doclistArray[j];
				//alert("else loop"+temp+"    "+orderlistArray[j]);
			}
		}
	}
	return temp;
}

function removeSpecalist(caseReferralId,doctorId)
{
	if (confirm(sureRemove)){
	var tableservices = document.getElementById('specalitybody');
	//var tablelabdata = document.getElementById('labTable');
	
	var rowCountservices = tableservices.rows.length;  
//	var rowCountlab = tablelabdata.rows.length;
	
	for(var k=1; k<rowCountservices; k++) {
	tableservices.deleteRow(0);
	}
//alert(caseReferralId);
//alert(doctorId);
	   var table = document.getElementById('specalityTable'); 
	   rowCount = table.rows.length; 
	   //alert(rowCount);
	      
	for(var i=1; i<rowCount; i++) { 
	            
	var row = table.rows[i]; 
	              
	var chkbox = row.cells[0].childNodes[0]; 
	//alert(chkbox.value);
	//alert(labServiceId);
	//alert("hi "+i);   
	
	 // alert(filename);     
	if(null != chkbox &&  chkbox.value == doctorId ) {
		//alert(i +"  i value"); 
		table.deleteRow(i);
		 dwr.engine.beginBatch();
		   LabServicesManagement.deleteCaseReferralDwr(caseReferralId,function(data) {
			   
			   removeRow(data);
			   
	 	       //setDeleteStatus(data,table,i)
				      });
		    
		   dwr.engine.endBatch();
		   i--;
	
	} 
	} 
	}
}

function removeRow(data)
{
	 if(data)
	 {
	 successMessage(succRemoved);
	 }
	 
}

function openDigitalPen()
{
	window.document.EDigitalPen.openApplication();
}

function emrClicked(eleid,loginId) {
	   // alert(eleid);
		 testFunction();
		 //alert(eleid);
		 //alert(loginId);
		 var valTemp=0;
		 
		 var fpcase ={caseId:null,caseStatus:null,starttstamp:null,endtstamp:null,complaint_symptoms:null, caseDescription:null, weight:null, height:null, temperature:null, bloodPressure:null, pulseRate:null, respirationRate:null, lmp:null, diagnosis:null, prescription:null, impression:null,caseattachments:null,caseTitle:null};
		 dwr.util.getValues(fpcase);
		 //alert(fpcase.caseTitle);
		 
		 CaseManagementValidator.getInputFieldValidationMessage("caseTitle", fpcase.caseTitle, {
	          callback:function(dataFromServer) {
			 //alert(dataFromServer);
	                  if(dataFromServer == "noerror")
	                  {
	                	  valTemp = 1;
	                	  //alert(valTemp);
	                  }
	                  if(valTemp == 1)
	                  {
	                	 dwr.engine.beginBatch();
	             		 //alert("outside: "+valTemp);
	             		 CaseManagement.createNewCaseTitle(fpcase,function(data) {
	             	                 setSaveTitleStatus(data,eleid,loginId)
	             				      });
	             	      dwr.engine.endBatch();
	             	    }
	                	                  
	               }
	      });
		 
		 
		
		 
	 }

function setSaveTitleStatus(data,eleid,loginId)
{
	var patient = eleid;
	// alert(patient);
	 //alert(loginId);
	// alert(patient.patientId);
	 var dType="fp";
	 var caseTitle="no";
	 var emr ="neweditEMR"
     var caseTitleValue = document.getElementById("caseTitle").value;	 
	 document.location = "ViewEmr.htm?docType="+dType+"&patientName="+patient.firstName+"&patientId="+patient+"&docId="+loginId+"&caseTitle="+caseTitle+"&emr="+emr+"&caseTitleValue="+caseTitleValue+"";
	 
}

function callSendMsg(docdetIds)
{
	//alert("heeeeeeeeeee"+docdetIds);
	SMSSender.sendMessage(docdetIds,function(data) {  	
		    messageResponse(data);
    }
  );
}

function messageResponse(data)
{
	//alert("inside method : "+data);
}

