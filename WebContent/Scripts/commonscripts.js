// JavaScript Document

// show hide tab script

var y=0;
var tempArr = new Array(); 

var m=0,n=0;
var arr =0;
var arr1 =0;
var editAll ="no";

function show(title) {
	if(document.getElementById (title) != null)
	{
	document.getElementById (title).className = 'showDiv';
	}
}

function hide(title) {
	if(document.getElementById (title) != null)
	{
	document.getElementById (title).className = 'hideDiv';
	}
	
}


// error/success message 
function successMessage(message) {
	hide('errorMessage');
	document.getElementById ("successMessage").className = 'showDiv successMessage';
	document.getElementById("successMessage").innerHTML = message;
	setTimeout ("hide('successMessage')", 5000) 
}
function failureMessage(message){
	hide('successMessage');
	document.getElementById ("errorMessage").className = 'showDiv errorMessage';
	document.getElementById("errorMessage").innerHTML = message;
	setTimeout ("hide('errorMessage')", 5000) 
}
function errorMessage(message,elementId){
	 var tempvab = message
	// alert(tempvab);
	hide('successMessage');
	// alert(tempArr.length);
	document.getElementById ("errorMessage").className = 'showDiv errorMessage';
	var flag = true;
	tempArr[arr] = new Array(2);
	
	if(elementId != "correcterror")
	{
	document.getElementById(elementId).className ="errorField"
	}
		
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
	//alert(flag);
	if(flag)
	{	
	//alert("hi");	
	if(!(tempvab == "noerror" || tempvab ==""))
	{
		//alert(elementId);
		//alert(arr);
		//alert(arr1);
	tempArr[arr][arr1]=elementId
	//alert(tempArr[arr][arr1]);
	tempArr[arr][arr1+1]=message
	//alert(tempArr[arr][arr1+1]);
	arr++;
	//alert("hi");
	}
	}
	
	
	printList(tempArr);
		
	//document.getElementById("errorMessage").innerHTML = document.getElementById("errorMessage").innerHTML+" | "+message;
}

function correctMessage(message,elementId){
	 var tempvab = message
	 var secArray = new Array();
	 
	//alert(tempvab);
	//alert("elementId: "+elementId);
	hide('successMessage');
	// alert(tempArr.length);
	//document.getElementById ("errorMessage").className = 'showDiv errorMessage'; //commented this because on save a red mark was coming
	
	
	for(i=0 ; i<tempArr.length ; i++)
	{
		secArray[i] = new Array(2);
		
		if(tempArr[i][0] == elementId)
		{
			//alert(tempArr[i][1]);
			tempArr[i][0]=""
			//alert(tempArr[arr][arr1]);
				tempArr[i][1]=""
					
					//tempArr[i].splice(0,1);

		
		}
				
	}
	
	
	
	printList(tempArr);
	
	
	
	//document.getElementById("errorMessage").innerHTML = document.getElementById("errorMessage").innerHTML+" | "+message;
}

function printList(tempArr){
	//alert(tempArr.length);
	var y=0;
	var z=0;
	for(p=0;p<tempArr.length;p++)
	{
		//alert(tempArr[p][1]);
		//alert(tempArr[p][1] == "undefined");
		var a =tempArr[p][1];
		//alert(a);
		
	if(a == "")
		{
		   //alert(tempArr[p][1])
		   z++;
		}
	else
	{
		if(tempArr[p][1] != undefined)
		{
		if(y==0)
		{
			document.getElementById("errorMessage").innerHTML = tempArr[p][1];
			y=1;
		}
		else
		{
			document.getElementById("errorMessage").innerHTML = document.getElementById("errorMessage").innerHTML +" | "+ tempArr[p][1];
		}
	
		}
	}
	if(tempArr.length == z)
	{
		//alert("hi");
		document.getElementById("errorMessage").innerHTML ="";
		hide('errorMessage')
	}
	}
	
}


// For Each Field Validation

function setInputFieldStatus(elementId, message)
{
	var tempMessage=message;
	//alert(elementId);
	//alert(message);
	//alert(document.getElementById("errorMessage").innerHTML=="");
	var tempvar = elementId;
   var id =elementId+"1";
   var flag = true;
   // tempArr = new Array(); 
  // document.getElementById(elementId).style.backgroundColor="#FFF";
   e =0;
  //document.getElementById("errorMessage").innerHTML ="";
 //alert(message == "noerror" && document.getElementById("errorMessage").innerHTML =="");
  
   if(message == "noerror")
   {
	   //alert(message);
	    correctMessage(message,elementId)
	   message="";
	   //hide("errorMessage");
    
	  //errorMessage(message,elementId);
	    document.getElementById(elementId).className ="noerrorField"
	 //document.getElementById(elementId).style.backgroundColor="#FFF";
	 //document.getElementById(elementId).style.color="#000"; 
   }
   else
   {
	  
	   // alert(document.getElementById("tempvar").value);
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
	   //document.familyPhyMain.patientIdN.style.backgroundColor="#F00"
	   document.getElementById(elementId).className ="errorField"
	   //document.getElementById(elementId).style.backgroundColor="#F00";
	   //document.getElementById(elementId).style.color="#FFF";  
	  }
	 
	  
	  
   }
   //alert(document.getElementById("errorMessage").innerHTML =="");
  
   if((tempMessage == "noerror") && (document.getElementById("errorMessage").innerHTML ==""))
	  {
		  //alert("hi");
		  hide('errorMessage');
	  }

}

function setName(string){
	//alert("while setName");
    var name=string
    document.getElementById('photo').value=name;
  }

function setInputFieldStatusPhoto(elementId, message)
{
	 var id =elementId+"1";
	// alert("common script"+elementId);
	 //alert(message);
	 //alert("photo");
   if(message == "noerror")
   {
	   correctMessage(message,elementId)
	   message="";
	   setTimeout('fileupload_query_uploadstatus()', 500)
	   //alert("beforesubmit");
	   document.getElementById("photo").className ="noerrorField"
	   document.PhotoUpload.submit();
   }	
   else
   {
   //document.getElementById(id).innerHTML = message;
    errorMessage(message,elementId);
   }
}


function fileupload_query_uploadstatus() {
	  //status="0";
	 //alert("hi");
	   UploadInfoBean.getPercentage( function(data) {  	
		//   alert(data);
    	setStatus(data);
	    }
	  );
return true;
}

function setStatus(data)
{

	   status=data;
	 // alert(status);	  
	  if (status == "100" || status=="-1")
	  {
		      
		      document.getElementById("fileupload_progress").innerHTML
	                                       ="File successfully uploaded";
	          document.PhotoUpload.cancelUpload.disabled = false;
	          UploadInfoBean.setPercentage("0");
	      
	  }
	    else {
	      document.getElementById("progressBar").style.display = "block";
	      document.getElementById("fileupload_progress").innerHTML=
	                                       "Uploading file: " + status
	                                       + "% completed, please wait...";
	      document.getElementById("progressBarBoxContent").style.width =
	                                       parseInt(data * 3.5) + "px";
	      
	      setTimeout(fileupload_query_uploadstatus, 1000);
	    }
}

function callSubmit()
{
	//alert("hi");
	//alert(document.PhotoUpload.file1.value);
	validateDoctorIdInputFieldPhoto("photo",document.PhotoUpload.file1.value);
}

function callSubmitP()
{
	 setTimeout('fileupload_query_uploadstatus()', 500)
	   //alert("beforesubmit");
	 document.PhotoUpload.submit();
}

function setEditAll(valEdit)
{
	editAll=valEdit;
}

function openHelp() 
{
	//alert("hi");
newwin = window.open('HelpPage.htm',"mywindow","menubar=0,resizable=0,width=1024,height=750,left=0,top=0,scrollbars=yes"); 
} 

function openTerm() 
{
	//alert("hi");
 newwin = window.open('TermPage.htm',"mywindow","menubar=0,resizable=0,width=800,height=600,left=100,top=75,scrollbars=yes"); 
} 

function openPrivacy() 
{
	//alert("hi");
 newwin = window.open('PrivacyPage.htm',"mywindow","menubar=0,resizable=0,width=800,height=600,left=100,top=75,scrollbars=yes"); 
} 
