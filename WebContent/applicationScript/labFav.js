var peopleCache = {};
var peopleCache1 = {};
var notactive;
var succsent;
var reqnotsent;
var succRemoved;
var notremoved;
var deletelab;

function init(deletelabc,succRemovedc,notremovedc,succsentc,reqnotsentc,notactivec,loginId,type) {
	//alert(loginId);
	deletelab  = deletelabc;
	succRemoved=succRemovedc;
	notremoved = notremovedc;
	succsent = succsentc;
	reqnotsent = reqnotsentc;
	notactive = notactivec
	if(document.getElementById("myNetworkNew") != null)
	{
	 document.getElementById("myNetworkNew").style.background="#f7941d";
	}	
	if(document.getElementById("networkMenu") != null)
	{
	 document.getElementById("networkMenu").style.background="#f7941d";
	}
	if(document.getElementById("labnetMenu") !=null)
	{
	 document.getElementById("labnetMenu").style.background="#f7941d";
	}
	if(document.getElementById("listFavLab") != null)
	{
	 document.getElementById("listFavLab").className="fontBold";
	}
	loadLab(loginId);
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

function highLightLab()
{
	  document.getElementById("listFavLab").className="fontNormal";
      document.getElementById("addReqLab").className="fontBold";
}

function loadLab(loginId)
{
	       //alert(loginId);
	        document.getElementById("labIds").value="";
	        document.familyPhySub.labFavNameS.value="";
	        document.getElementById("listFavLab").className="fontBold";
	        document.getElementById("addReqLab").className="fontNormal";
	        LabManagement.showLabSelected(loginId,function(data) {
			    
			    // Delete all the rows except for the "pattern" row
			      dwr.util.removeAllRows("specalityfavbody", { filter:function(tr) {
			      return (tr.id != "pattern1");
			    }});
			   
			      var lab,id,docdetails,contactdetails,favLab,status;
				    //data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
				   // alert(data);
				   
				    for (var i = 0; i < data.length; i++) {
				    	
				      favLab= data[i];
				      status = favLab.status;
				      lab = data[i].labId;
				      //alert(lab);
				      id = lab.labId;
				     // alert(id);
				      docdetails=lab.labName+"\n"+lab.contactPerson
				      
				      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
				     
				      dwr.util.cloneNode("pattern1", { idSuffix:id });
				      dwr.util.setValue("tableFavLabId" + id, id);
				      dwr.util.setValue("tableFavLabDetails" + id, docdetails);
				      dwr.util.setValue("tableFavLabContactDetails" + id, contactdetails);
				      dwr.util.setValue("tableStatus" + id, "");
				      if(lab.login.accountStatus == "DISABLED")
				      {
				      document.getElementById("tableStatus" + id).innerHTML=status+"&nbsp;/&nbsp;"+notactive;
				      }
				      else
				      {
				    	  document.getElementById("tableStatus" + id).innerHTML=status
				      }
				      $("pattern1" + id).style.display = "";
				      peopleCache1[id] = lab;
				      //alert("8");
				      
			    
			    }
			    //alert("9");
			  });
	       // alert("second");
	        LabManagement.showLabFav(loginId,function(data) {
	    	//alert("1");
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("specalitybody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		      //alert("2");
		    // Create a new set cloned from the pattern row
		      var lab,id,docdetails,contactdetails;
		      data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
			   
			   //alert(data);
			    for (var i = 0; i < data.length; i++) {
			    	
			      lab = data[i];
			     
			      id = lab.labId;
			      //alert("what :  "+id);
			      docdetails=lab.labName+"\n"+lab.contactPerson
			      
			      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
			     
			      dwr.util.cloneNode("pattern", { idSuffix:id });
			      dwr.util.setValue("tableLabId" + id, id);
			      dwr.util.setValue("tableLabDetails" + id, docdetails);
			      dwr.util.setValue("tableLabContactDetails" + id, contactdetails);
			      $("pattern" + id).style.display = "";
			      peopleCache[id] = lab;
			    
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
	var doclist =document.getElementById('labIds').value;
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
	document.getElementById('labIds').value=tempList;
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
	var doclist =document.getElementById('remLabId').value;
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
	document.getElementById('remLabId').value=tempList;
	
}


function sendRequest(loginId)
{
	//alert(loginId);
	var docdetIds = document.getElementById('labIds').value; 
	if(docdetIds != "")
	{
	dwr.engine.beginBatch();
	//alert(docdetIds);
	  LabManagement.createFavLab(loginId,docdetIds,function(data) {
		  //alert("hi");
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
    document.getElementById("labIds").value="";
    document.getElementById("remLabId").value="";
	loadLab(loginId);
	successMessage(succsent);
	}
	else
	{
	errorMessage(reqnotsent);
	}
}

function setSaveStatusR(data,loginId)
{
	//alert(data);
	if(data)
	{
    document.getElementById("labIds").value="";
    document.getElementById("remLabId").value="";
	loadLab(loginId);
	successMessage(succRemoved);
	}
	else
	{
	errorMessage(notremoved);
	}
}

function removeRequest(loginId)
{
	//alert(loginId);
	var docdetIds = document.getElementById('remLabId').value; 
	if(docdetIds != "")
	{
	if (confirm(deletelab))
	{
	dwr.engine.beginBatch();
	//alert(docdetIds);
	  LabManagement.removeFavLab(loginId,docdetIds,function(data) {
		  //alert("hi");
               setSaveStatusR(data,loginId)
	      });
    dwr.engine.endBatch();
	}
	}
}

function searchFavLab(loginId) {
	var docName=document.familyPhySub.labFavNameS.value;
	
	 LabManagement.searchLabFav(docName,loginId,function(data) {
		    
		 // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("specalityfavbody", { filter:function(tr) {
	      return (tr.id != "pattern1");
	    }});
	   
	      var lab,id,docdetails,contactdetails,favLab,status;
		    //data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
		   //alert(data);
		   
		    for (var i = 0; i < data.length; i++) {
		    	
		      favLab= data[i];
		      status = favLab.status;
		      lab = data[i].labId;
		      //alert(lab);
		      id = lab.labId;
		     // alert(id);
		      docdetails=lab.labName+"\n"+lab.contactPerson
		      
		      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		     
		      dwr.util.cloneNode("pattern1", { idSuffix:id });
		      dwr.util.setValue("tableFavLabId" + id, id);
		      dwr.util.setValue("tableFavLabDetails" + id, docdetails);
		      dwr.util.setValue("tableFavLabContactDetails" + id, contactdetails);
		      dwr.util.setValue("tableStatus" + id, status);
		      $("pattern1" + id).style.display = "";
		      peopleCache1[id] = lab;
		      //alert("8");
		     
	    }
	    //alert("9");
	  });
 }

function searchLabAll(loginId) {
	//alert(loginId);
	var docName=document.familyPhySub.allLabS.value;
	
	   LabManagement.searchAllLab(docName,loginId,function(data) {
	    	//alert("1");
		    // Delete all the rows except for the "pattern" row
		 // Delete all the rows except for the "pattern" row
	      dwr.util.removeAllRows("specalitybody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	      //alert("2");
	    // Create a new set cloned from the pattern row
	      var lab,id,docdetails,contactdetails;
	      data.sort(function(p1, p2) { return p1.labName.localeCompare(p2.labName); });
		   
		   //alert(data);
		    for (var i = 0; i < data.length; i++) {
		    	
		      lab = data[i];
		     
		      id = lab.labId;
		     // alert(id);
		      docdetails=lab.labName+"\n"+lab.contactPerson
		      
		      contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		     
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tableLabId" + id, id);
		      dwr.util.setValue("tableLabDetails" + id, docdetails);
		      dwr.util.setValue("tableLabContactDetails" + id, contactdetails);
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = lab;
	  
	    }
	   // alert("9");
	  });
}
