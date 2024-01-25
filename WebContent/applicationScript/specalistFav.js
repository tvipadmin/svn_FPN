var peopleCache = {};
var peopleCache1 = {};
var notactive;
var removeDoc;
var reqnotsent;
var succRemoved;
var notremoved ;	

function init(removeDocc,succRemovedc,notremovedc,succsentc,reqnotsentc,notactivec,loginId) {
	//alert(loginId);
	removeDoc = removeDocc;
	succsent = succsentc;
	reqnotsent = reqnotsentc;
	succRemoved=succRemovedc;
	notremoved = notremovedc;	
	notactive = notactivec;
	if(document.getElementById("myNetworkNew") != null)
	{
	document.getElementById("myNetworkNew").style.background="#f7941d";
	}
	
	if(document.getElementById("networkMenu") != null)
	{
	document.getElementById("networkMenu").style.background="#f7941d";
	}
	if(document.getElementById("sepnetMenu") != null)
	{
	document.getElementById("sepnetMenu").style.background="#f7941d"; 
	}
	
	if(document.getElementById("listFav") != null)
	{
	document.getElementById("listFav").className="fontBold";
	}
	
		
	loadSepDoctor(loginId);
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
function highLight()
{
 document.getElementById("listFav").className="fontNormal";
 document.getElementById("addReq").className="fontBold";
}

function loadSepDoctor(loginId)
{
	       // alert(loginId);
	          
	        document.familyPhySub.docFavNameS.value="";
	        document.getElementById("docIds").value="";
	        document.getElementById("listFav").className="fontBold";
	        document.getElementById("addReq").className="fontNormal";
	        CaseManagement.showFavSep(loginId,function(data) {
		    
			    // Delete all the rows except for the "pattern" row
			      dwr.util.removeAllRows("specalityfavbody", { filter:function(tr) {
			      return (tr.id != "pattern1");
			    }});
			   
			    // Create a new set cloned from the pattern row
			    var doctor,id,specality,doctordetails,favDoc,status;
			
			 // data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
			 //alert(data.length);
			    for (var i = 0; i < data.length; i++) {
				  
			    	//alert("3");
			    	favDoc =data[i];
			    	//alert(favDoc.status);
			    	status = favDoc.status;
			    	doctor = data[i].specalistId;
			    	//alert("doctor"+doctor);
				      id = doctor.doctorId;
				    //  alert("4");
				      if(id != loginId)
				      {
				      //alert("id: "+id);
				    	//  alert("5");
				      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
				      //alert("docdetails: "+docdetails);
				      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
				      //alert("contactdetails: "+contactdetails);
				     // alert("6");
				    
					  //alert("7");
				      
				      dwr.util.cloneNode("pattern1", { idSuffix:id });
				      dwr.util.setValue("tableFavDocDetails" + id, docdetails);
				      dwr.util.setValue("tableFavDocContactDetails" + id, contactdetails);
				      dwr.util.setValue("tableStatus" + id, "");
				      if(doctor.login.accountStatus == "DISABLED")
				      {
				      document.getElementById("tableStatus" + id).innerHTML=status+"&nbsp;/&nbsp;"+notactive;
				      }
				      else
				      {
				    	  document.getElementById("tableStatus" + id).innerHTML=status
				      }
				      $("pattern1" + id).style.display = "";
				      peopleCache1[id] = doctor;
				      //alert("8");
				      
			    }
			    }
			    //alert("9");
			  });
	        
	        
	        
	        CaseManagement.showSpecalistFav(loginId,function(data) {
	    	//alert("1");
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalitybody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		      //alert("2");
		    // Create a new set cloned from the pattern row
		    var doctor,id,specality,doctordetails;
		 //alert(data);
		  data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
		   //alert("hello");
		   //alert("data.length"+data.length);
		    for (var i = 0; i < data.length; i++) {
			  
		    	//alert("3");
		    	doctor = data[i];
		    	//alert("doctor"+doctor);
			      id = doctor.doctorId;
			    //  alert("4");
			      if(id != loginId)
			      {
			      //alert("id: "+id);
			    	//  alert("5");
			      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
			      //alert("docdetails: "+docdetails);
			      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
			      //alert("contactdetails: "+contactdetails);
			     // alert("6");
			    
				  //alert("7");
			      
			      dwr.util.cloneNode("pattern", { idSuffix:id });
			      dwr.util.setValue("tableDocDetails" + id, docdetails);
			      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
			      $("pattern" + id).style.display = "";
			      peopleCache[id] = doctor;
			      //alert("8");
			      
		    }
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


function sendRequest(loginId)
{
	//alert(loginId);
	//alert(document.getElementById('docIds'));
	var docdetIds = document.getElementById('docIds').value; 
	//alert(docdetIds);
	if(docdetIds != "")
	{
	dwr.engine.beginBatch();
	//alert(docdetIds);
	  CaseManagement.createFavSep(loginId,docdetIds,function(data) {
		 // alert("hiGGG");
               setSaveStatus(data,loginId)
	      });
    dwr.engine.endBatch();
	}
	
}

function setSaveStatus(data,loginId)
{
	//alert(data);
	if(data)
	{
	//alert("hi");	
    document.getElementById("docIds").value="";
    document.getElementById("remDocId").value="";
   // alert("hello");
	loadSepDoctor(loginId);
	successMessage(succsent);
	}
	else
	{
	failureMessage(reqnotsent);
	}
}

function setSaveStatusR(data,loginId)
{
	//alert(data);
	if(data)
	{
    document.getElementById("docIds").value="";
    document.getElementById("remDocId").value="";
	loadSepDoctor(loginId);
	successMessage(succRemoved);
	}
	else
	{
	failureMessage(notremoved);
	}
}

function removeRequest(loginId)
{
	//alert(loginId);
	var docdetIds = document.getElementById('remDocId').value; 
	if(docdetIds != "")
	{
	if (confirm(removeDoc))
	{
	dwr.engine.beginBatch();
	//alert(docdetIds);
	  CaseManagement.removeFavSep(loginId,docdetIds,function(data) {
		  //alert("hi");
               setSaveStatusR(data,loginId)
	      });
    dwr.engine.endBatch();
	}
	}
}

function searchFavDoc(loginId) {
	var docName=document.familyPhySub.docFavNameS.value;
	
	 CaseManagement.searchDocFav(docName,loginId,function(data) {
		    
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalityfavbody", { filter:function(tr) {
		      return (tr.id != "pattern1");
		    }});
		   
		    // Create a new set cloned from the pattern row
		    var doctor,id,specality,doctordetails,favDoc,status;
		
		 // data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
		   // alert(data.length);
		    for (var i = 0; i < data.length; i++) {
			  
		    	//alert("3");
		    	favDoc =data[i];
		    	//alert(favDoc.status);
		    	status = favDoc.status;
		    	doctor = data[i].specalistId;
		    	//alert("doctor"+doctor);
			      id = doctor.doctorId;
			    //  alert("4");
			      if(id != loginId)
			      {
			      //alert("id: "+id);
			    	//  alert("5");
			      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
			      //alert("docdetails: "+docdetails);
			      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
			      //alert("contactdetails: "+contactdetails);
			     // alert("6");
			    
				  //alert("7");
			      
			      dwr.util.cloneNode("pattern1", { idSuffix:id });
			      dwr.util.setValue("tableFavDocDetails" + id, docdetails);
			      dwr.util.setValue("tableFavDocContactDetails" + id, contactdetails);
			      dwr.util.setValue("tableStatus" + id, status);
			      $("pattern1" + id).style.display = "";
			      peopleCache1[id] = doctor;
			      //alert("8");
			      
		    }
		    }
		    //alert("9");
		  });
     	
	
}

function searchDocAll(loginId) {
	//alert(loginId);
	var docName=document.familyPhySub.allDocS.value;
	
	 CaseManagement.searchAllDoc(docName,loginId,function(data) {
	    	//alert("1");
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalitybody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		      //alert("2");
		    // Create a new set cloned from the pattern row
		    var doctor,id,specality,doctordetails;
		 //alert(data);
		  data.sort(function(p1, p2) { return p1.firstName.localeCompare(p2.firstName); });
		   //alert("hello");
		   //alert("data.length"+data.length);
		    for (var i = 0; i < data.length; i++) {
			  
		    	//alert("3");
		    	doctor = data[i];
		    	//alert("doctor"+doctor);
			      id = doctor.doctorId;
			    //  alert("4");
			      if(id != loginId)
			      {
			      //alert("id: "+id);
			    	//  alert("5");
			      docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
			      //alert("docdetails: "+docdetails);
			      contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
			      //alert("contactdetails: "+contactdetails);
			     // alert("6");
			    
				  //alert("7");
			      
			      dwr.util.cloneNode("pattern", { idSuffix:id });
			      dwr.util.setValue("tableDocDetails" + id, docdetails);
			      dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
			      $("pattern" + id).style.display = "";
			      peopleCache[id] = doctor;
			      //alert("8");
			      
		    }
		    }
		    //alert("9");
		  });
}
