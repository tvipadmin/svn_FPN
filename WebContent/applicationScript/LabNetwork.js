var labM;

function init()
{
	//document.PhotoUpload.cancelUpload.disabled = true;
}


function ValidLab(){
	   

    var enteredVal = parent.target_random.document.getElementById('FPNInput').value;
    var totalVal = parent.target_random.document.getElementById('totalVal').value;
   // alert(enteredVal);
    
    if (enteredVal == totalVal) 
    {
    	//alert("true");
    	dwr.engine.beginBatch();
	     LabRegManagement.saveRegLab(labM, function(data) {
         setSaveStatus(data)
	      });
     
	  dwr.engine.endBatch();
    	
    }
    else
    {
    	//alert("false");
    	//firstNumber = Math.ceil(Math.random() * 10);
    	//secondNumber = Math.ceil(Math.random() * 10); 
    	//totalVal = firstNumber + secondNumber
    	var f = parent.document.getElementById('target_random');
    	//alert(f);
    	f.contentWindow.location.reload(true);

    }
}

function addRegLab()
{
	corrfield=0;
	err=0;
	 var lab = {labId:null, labName:null,contactPerson:null,photo:null, contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}};
	 dwr.util.getValues(lab);
	 labM = lab;
	 validateLabIdInputFieldFull("labId",lab.labId,lab);
	 validateLabIdInputFieldFull("login.password",lab.login.password,lab);
	 validateLabIdInputFieldConfirmPassFull("login.confirmPassword",lab.login.confirmPassword,lab);
	 validateLabIdInputFieldFull("labName",lab.labName,lab);
	 validateLabIdInputFieldFull("contactDetails.contactNumber",lab.contactDetails.contactNumber,lab);
	 validateLabIdInputFieldFull("contactDetails.email",lab.contactDetails.email,lab);
	 validateLabIdInputFieldFull("contactPerson",lab.contactPerson,lab);
	   
}

function validateLabIdInputFieldFull(id,value,lab)
{
	  var idf = id;
	  var valuef = value;
	  RegLabValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,lab);
	                  }
	  });
	 
}

function validateLabIdInputField(element)
{
  var id = element.id;
  var value = element.value;
  
  RegLabValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
                  setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validateLabIdInputFieldConfirmPass(element)
{
  var id = element.id;
  var value = element.value;
  var tempval = document.labNetwork.passwordN.value+"yt%y"+value;
  RegLabValidator.getInputFieldValidationMessage(id, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validateLabIdInputFieldConfirmPassFull(id,value,lab)
{
  var idf = id;
  var valuef = value;
  var tempval = document.labNetwork.passwordN.value+"yt%y"+valuef;
  RegLabValidator.getInputFieldValidationMessage(idf, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatusfull(idf, dataFromServer,lab);
               }
  });
  
}


function setInputFieldStatusfull(elementId, message,lab)
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

	  
	  
		 // alert(err)
		  if(corrfield==7 && err==7)
			{
			  labM = lab;
			  show('checkNotSpam');
		      //correctMessage(correcterrors,"correcterror");
		     
			}
		  else if(err==7)
		  {		
			  //errorMessage(correcterrors,"correcterror");
		  }
	 
}

function setSaveStatus(data)
{
if(data)
{
	//alert("Successfully Registered!!!");
	hide('AddTable');
	hide('checkNotSpam');
	show('succMsg');
    dwr.engine.beginBatch();
    LabRegManagement.sendConformationLab(labM,function(data) {
	  });
  
  dwr.engine.endBatch();
	dwr.util.setValues({labId:null, labName:null,contactPerson:null,photo:null,contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
}
}

function joinDoctor()
{
	document.location = "JoinNetwork.htm";
}

function joinLab()
{
	document.location = "LabNetwork.htm";
}

function clearLab() {
	
	  removeErrorMsg();
	  dwr.util.setValues({labId:null, labName:null,contactPerson:null,photo:null,contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
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
	document.getElementById("labId").className ="noerrorField"
	document.getElementById("login.password").className ="noerrorField"
	document.getElementById("login.confirmPassword").className ="noerrorField"
	document.getElementById("labName").className ="noerrorField"
	document.getElementById("contactDetails.address").className ="noerrorField"
	document.getElementById("contactDetails.contactNumber").className ="noerrorField"
	document.getElementById("contactDetails.email").className ="noerrorField"
	document.getElementById("contactPerson").className ="noerrorField"
	//document.getElementById("photo").className ="noerrorField"
	//document.getElementById("specialty").className ="noerrorField"	
		
}
