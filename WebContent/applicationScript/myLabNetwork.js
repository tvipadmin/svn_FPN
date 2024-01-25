var peopleCache = { };
var peopleCache1 = { };
var succRemoved;
var mynetwork;
var newReq;
var succReject;
var notreject;
var notremoved;
var succonfirmed;
var notconfirmed;
var sureRemove;

function init(sureRemovec,succonfirmedc,notconfirmedc,notremovedc,succRejectc,notrejectc,newReqc,mynetworkc,succRemovedc,loginId,val) {
	 //alert(loginId);
	sureRemove = sureRemovec;
	notconfirmed = notconfirmedc;
	succonfirmed = succonfirmedc;
	notremoved = notremovedc;
	succReject = succRejectc;
	notreject = notrejectc;
	newReq = newReqc;
	succRemoved =succRemovedc;
	mynetwork = mynetworkc
	document.getElementById("myNetworkLabNew").style.background="#f7941d";
	document.getElementById("allL").className="fontBold";
	if(val == 'all')
	{
		document.getElementById("allL").className="fontBold";
		document.getElementById("allPLL").className="fontNormal";
		document.getElementById("allLC").className="fontNormal";
	}
	if(val == 'pending')
	{
		document.getElementById("allL").className="fontNormal";
		document.getElementById("allPLL").className="fontBold";
		document.getElementById("allLC").className="fontNormal";
	}
	if(val == 'confirmed')
	{
		document.getElementById("allL").className="fontNormal";
		document.getElementById("allPLL").className="fontNormal";
		document.getElementById("allLC").className="fontBold";
	}
	
	 loadFPDoctor(loginId);
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

function loadFPDoctor(loginId)
{
	       // alert(loginId);
	
	
	document.familyPhySub.allDocS.value="";
	document.familyPhySub.docFavNameS.value="";	
	
	LabManagement.newRequest(loginId,function(data) {
    	if(data > 0)
    	{
	document.getElementById("myNetworkLabNew").innerHTML = mynetwork+" ("+data+" "+newReq +" )"
    	}
    	else
    	{
    		document.getElementById("myNetworkLabNew").innerHTML = mynetwork
    	}
    	
    });
	        document.getElementById("confirmdocIds").value="";
	       // alert(loginId);
	        LabManagement.showPendingFP(loginId,function(data) {
		    
			    // Delete all the rows except for the "pattern" row
			      dwr.util.removeAllRows("specalityfavbody", { filter:function(tr) {
			      return (tr.id != "pattern1");
			    }});
			   
			      
			   // alert(data.length);  
			    // Create a new set cloned from the pattern row
			    var doctor,id,specality,doctordetails,favDoc,status,doctorId;
			
			 // data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
			 
			    for (var i = 0; i < data.length; i++) {
				  
			    //alert("3");
			    	favDoc =data[i];
			    	//alert(favDoc.status);
			    	status = favDoc.status;
			    	doctor = data[i].familyPhysicianId;
			    	//alert("doctor"+doctor);
				      id = doctor.doctorId;
				      //alert(doctor.doctorId);
				      doctorId=id;
				      doctorName = doctor.firstName+" "+doctor.lastName
				    //  alert("4");
				    
				      //alert("id: "+id);
				     // alert("5");
				      //docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
				     
				      //alert("docdetails: "+docdetails);
				      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
				     
				      //alert("contactdetails: "+contactdetails);
				     // alert("6");
				    
					  //alert("7");
				      
				      dwr.util.cloneNode("pattern1", { idSuffix:id });
				      
				      dwr.util.setValue("tableFavDocContactDetails" + id, contactdetails);
				      dwr.util.setValue("tableStatus" + id, status);
				      dwr.util.setValue("tableFavDocDetails" + id, "");
				      document.getElementById("tableFavDocDetails" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";	      
				      $("pattern1" + id).style.display = "";
				      peopleCache1[id] = doctor;
				     // alert("8");
				      
			    
			    }
			    //alert("9");
			  });
	        
	        
	        
	        LabManagement.showConfirmedFav(loginId,function(data) {
	    	//alert("1");
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalitybody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		      //alert("2");
		    // Create a new set cloned from the pattern row
		    var doctor,id,specality,doctordetails,status,doctorId,doctorName;
		
		    for (var i = 0; i < data.length; i++) {
			  
		    	favDoc =data[i];
		    	//alert(favDoc.status);
		    	status = favDoc.status;
		    	doctor = data[i].familyPhysicianId;
		    	//alert("doctor"+doctor);
			      id = doctor.doctorId;
			      //alert("id: "+id);
			    	//  alert("5");
			      doctorId=id;
			      doctorName = doctor.firstName+" "+doctor.lastName

			      //docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
			      //alert("docdetails: "+docdetails);
			      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
			      //alert("contactdetails: "+contactdetails);
			     // alert("6");
			    
				 // alert(status);
			      
			      dwr.util.cloneNode("pattern", { idSuffix:id });
			      
			      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
			      dwr.util.setValue("tableDocStatus" + id, "");
			      dwr.util.setValue("tableDocDetails" + id, "");
			      if(doctor.login.accountStatus == "DISABLED")
			      {
			      document.getElementById("tableDocStatus" + id).innerHTML=status+"&nbsp;/&nbsp; Not Active";
			      }
			      else
			      {
			    	  document.getElementById("tableDocStatus" + id).innerHTML=status
			      }
			      document.getElementById("tableDocDetails" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
			      $("pattern" + id).style.display = "";
			      peopleCache[id] = doctor;
			      //alert("8");
			      
		  		    }
		    //alert("9");
		  });
			//alert("10");  
}

function appendDocValue(eleid)
{
	//alert("hi");
	//alert(eleid);
	var docId=eleid.replace("docId","");
	var doclist =document.getElementById('remDocId').value;
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
	document.getElementById('remDocId').value=tempList;
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


function appendDocRemValue(eleid)
{
	//alert("hi");
	//alert(eleid);
	var docId=eleid.replace("docIdRem","");
	//alert(docId);
	var doclist =document.getElementById('remDocId').value;
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
	//alert(tempList);
	document.getElementById('remDocId').value=tempList;
	
}


function confirmRequest(loginId)
{
	//alert(loginId);
	var docdetIds = document.getElementById('confirmdocIds').value; 
	if(docdetIds != "")
	{
	dwr.engine.beginBatch();
	//alert(docdetIds);
	LabManagement.updateFavLab(loginId,docdetIds,'confirmed',function(data) {
		  //alert("hi");
               setSaveStatus(data,loginId)
	      });
    dwr.engine.endBatch();
	}
	
}

function rejectRequest(loginId)
{
	//alert(loginId);
	var docdetIds = document.getElementById('confirmdocIds').value; 
	if(docdetIds != "")
	{
	dwr.engine.beginBatch();
	//alert(docdetIds);
	LabManagement.updateFavLab(loginId,docdetIds,'rejected',function(data) {
		  //alert("hi");
               setSaveStatusRe(data,loginId)
	      });
    dwr.engine.endBatch();
	}
}

function setSaveStatusRe(data,loginId)
{
	//alert(data);
	if(data)
	{
    document.getElementById("confirmdocIds").value="";
    document.getElementById("remDocId").value="";
	loadFPDoctor(loginId);
	successMessage(succReject);
	}
	else
	{
		errorMessage(notreject);
	}
}

function setSaveStatusR(data,loginId)
{
	//alert(data);
	if(data)
	{
    document.getElementById("confirmdocIds").value="";
    document.getElementById("remDocId").value="";
	loadFPDoctor(loginId);
	successMessage(succRemoved);
	}
	else
	{
		errorMessage(notremoved);
	}
}

function setSaveStatus(data,loginId)
{
	//alert(data);
	if(data)
	{
    document.getElementById("confirmdocIds").value="";
    document.getElementById("remDocId").value="";
	loadFPDoctor(loginId);
	successMessage(succonfirmed);
	}
	else
	{
		errorMessage(notconfirmed);
	}
}

function removeDoctor(loginId)
{
	//alert(loginId);
	if (confirm(sureRemove)){
	var docdetIds = document.getElementById('remDocId').value; 
	if(docdetIds != "")
	{
	dwr.engine.beginBatch();
	//alert(docdetIds);
	LabManagement.removeFPNetwork(loginId,docdetIds,function(data) {
		  //alert("hi");
               setSaveStatusR(data,loginId)
	      });
    dwr.engine.endBatch();
	}
	}
}



function searchPendingDoc(loginId) {
	//alert(loginId);
	var docName=document.familyPhySub.docFavNameS.value;
	
	LabManagement.searchDocPending(docName,loginId,function(data) {
	    	//alert("1");
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalityfavbody", { filter:function(tr) {
		      return (tr.id != "pattern1");
		    }});
		  
		      var doctor,id,specality,doctordetails,favDoc,status,doctorId,doctorName;
				
				 // data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
				 
				    for (var i = 0; i < data.length; i++) {
					  
				    //alert("3");
				    	favDoc =data[i];
				    
				    	status = favDoc.status;
				    	doctor = data[i].familyPhysicianId;
				    	
					      id = doctor.doctorId;
					      doctorId=id;
					      doctorName = doctor.firstName+" "+doctor.lastName

					    //  alert("4");
					    
					      //alert("id: "+id);
					     // alert("5");
					      //docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
					     
					      //alert("docdetails: "+docdetails);
					      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
					     
					      //alert("contactdetails: "+contactdetails);
					      //alert("6");
					       
					      dwr.util.cloneNode("pattern1", { idSuffix:id });
					      
					      dwr.util.setValue("tableFavDocContactDetails" + id, contactdetails);
					      dwr.util.setValue("tableStatus" + id, status);
					      dwr.util.setValue("tableFavDocDetails" + id, "");
					      document.getElementById("tableFavDocDetails" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
					      $("pattern1" + id).style.display = "";
					      peopleCache1[id] = doctor;
					    
					      
				    
				    }
		    //alert("9");
		  });
}

function searchConfirmedDoc(loginId) {
	//alert(loginId);
	var docName=document.familyPhySub.allDocS.value;
	
	LabManagement.searchDocConfirmed(docName,loginId,function(data) {
	    	//alert("1");
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalitybody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		  
		      var doctor,id,specality,doctordetails,favDoc,status,doctorId,doctorName;
				
				 // data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
				 
				    for (var i = 0; i < data.length; i++) {
					  
				    //alert("3");
				    	favDoc =data[i];
				    
				    	status = favDoc.status;
				    	doctor = data[i].familyPhysicianId;
				    	
					      id = doctor.doctorId;
					    //  alert("4");
					    
					      //alert("id: "+id);
					     // alert("5");
					      //docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
					      doctorId=id;
					      doctorName = doctor.firstName+" "+doctor.lastName
					      //alert("docdetails: "+docdetails);
					      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
					     
					      //alert("contactdetails: "+contactdetails);
					      //alert("6");
					       
					      dwr.util.cloneNode("pattern", { idSuffix:id });
					     
					      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
					      dwr.util.setValue("tableDocStatus" + id, status);
					      dwr.util.setValue("tableDocDetails" + id, "");
					      document.getElementById("tableDocDetails" + id).innerHTML="<a href=\"javascript:testFunction(); openWindow('"+doctorId+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
					      $("pattern" + id).style.display = "";
					      peopleCache[id] = doctor;
					    
				    }
		    //alert("9");
		  });
}

function appendDocConfirmValue(eleid)
{
	//alert("hi");
	//alert(eleid);
	var docId=eleid.replace("docIdConfirm","");
	//alert(docId);
	var doclist =document.getElementById('confirmdocIds').value;
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
	//alert(tempList);
	document.getElementById('confirmdocIds').value=tempList;
	
}

function openWindow(doctorId) 
{ 
newwin = window.open('ViewDoctor.htm?doctorId='+doctorId,"mywindow","menubar=0,resizable=0,width=450,height=210,left=20,top=10,scrolling=yes"); 
} 
