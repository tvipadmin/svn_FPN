var doctorM;
var typeRequired;


function init(typeRequiredc)
{
	typeRequired = typeRequiredc
	
	//document.PhotoUpload.cancelUpload.disabled = true;
}



function ValidFPN(){
   

    var enteredVal = parent.target_random.document.getElementById('FPNInput').value;
    var totalVal = parent.target_random.document.getElementById('totalVal').value;
   // alert(enteredVal);
    
    if (enteredVal == totalVal) 
    {
    	//alert("true");
    	 dwr.engine.beginBatch();
		  DoctorRegManagement.saveRegDoctor(doctorM, function(data) {
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



function selectType()
{
	var val ="";
	if(document.getElementById("sep").checked)
	{
		val = document.getElementById("sep").value;
	}
	if(document.getElementById("fp").checked)
	{
		val = document.getElementById("fp").value;
	}
	if(document.getElementById("both").checked)
	{
		val = document.getElementById("both").value;
	}
	document.getElementById("type").value = val;
	correctMessage(typeRequired,"type");
	hide('errorMessage');
}

function clearDoctor(datapath,photopath) {

	document.getElementById("sep").checked = false
	document.getElementById("fp").checked = false
	document.getElementById("both").checked = false
	removeErrorMsg();
	  hide('checkNotSpam');
	  dwr.util.setValues({doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null, photo:null,  contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
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
	//document.getElementById("photo").className ="noerrorField"
			//document.getElementById("specialty").className ="noerrorField"	
		
}


function addRegDoctor()
{
	corrfield=0;
	err=0;  
    var doctor = {doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null, photo:null, contactDetails:{email:null,address:null,contactNumber:null}, login:{password:null,confirmPassword:null}};
	dwr.util.getValues(doctor);
	doctorM = doctor;
	validateDoctorIdInputFieldFull("doctorId",doctor.doctorId,doctor);
	validateDoctorIdInputFieldFull("login.password",doctor.login.password,doctor);
    validateDoctorIdInputFieldConfirmPassFull("login.confirmPassword",doctor.login.confirmPassword,doctor);
    validateDoctorIdInputFieldFull("firstName",doctor.firstName,doctor);
    validateDoctorIdInputFieldFull("lastName",doctor.lastName,doctor);
    validateDoctorIdInputFieldFull("contactDetails.contactNumber",doctor.contactDetails.contactNumber,doctor);
    validateDoctorIdInputFieldFull("contactDetails.email",doctor.contactDetails.email,doctor);
    validateDoctorIdInputFieldFull("education",doctor.education,doctor);
    validateDoctorIdInputFieldFull("designation",doctor.designation,doctor);
    validateDoctorIdInputFieldFull("identification",doctor.identification,doctor);
    validateDoctorIdInputFieldFull("clinicName",doctor.clinicName,doctor);
    validateDoctorIdInputFieldFull("specialty",doctor.specialty,doctor);
    validateDoctorIdInputFieldFull("type",doctor.type,doctor);
	  
}

function validateDoctorIdInputFieldConfirmPassFull(id,value,doctor)
{
  var idf = id;
  var valuef = value;
  var tempval = document.doctorNetwork.passwordN.value+"yt%y"+valuef;
  RegDocValidator.getInputFieldValidationMessage(idf, tempval, {
          callback:function(dataFromServer) {
                  setInputFieldStatusfull(idf, dataFromServer,doctor);
               }
  });
  
}
function validateDoctorIdInputFieldFull(id,value,doctor)
{
	  var idf = id;
	  var valuef = value;
	 
	  RegDocValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusfull(idf, dataFromServer,doctor);
	                  
	                  }
	  });
	 
}
function validateDoctorIdInputField(element)
{
 //alert("hi");	
  var id = element.id;
  var value = element.value;
  
  RegDocValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
	      setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function validateDoctorIdInputFieldConfirmPass(element)
{
  var id = element.id;
  var value = element.value;
  var tempval = document.doctorNetwork.passwordN.value+"yt%y"+value;
  RegDocValidator.getInputFieldValidationMessage(id, tempval, {
          callback:function(dataFromServer) {
	 
                  setInputFieldStatus(element.id, dataFromServer);
               }
  });
  
}

function setInputFieldStatusfull(elementId, message,doctor)
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

	  
	  if(corrfield==13 && err==13)
			{
		      //correctMessage("hello","correcterror");
		     doctorM = doctor;
		     hide('errorMessage');
			 show('checkNotSpam');
			 
			}
		  else if(err == 13)
		  {
			  //alert("Not Added Correct The Errors...");
			  //errorMessage("hello","correcterror");
		  }
		  

}

function setSaveStatus(data)
{
	
//alert(data);
if(data)
{
	//alert("Successfully Registered!!!");
	hide('AddTable');
	hide('checkNotSpam');
	show('succMsg');
	
	dwr.engine.beginBatch();
	
	DoctorRegManagement.sendConformation(doctorM,function(data) {
		
      });
  
  dwr.engine.endBatch();
  dwr.util.setValues({doctorId:null, education:null, designation:null, identification:null, clinicName:null, specialty:null, type:null, firstName:null, lastName:null, photo:null,  contactDetails:{email:null,address:null,contactNumber:null},login:{password:null,confirmPassword:null}});
	
	
}
}
function validateDoctorIdInputFieldPhoto(id,value)
{
	  var idf = id;
	  var valuef = value;
	  //alert(id);
	  //alert(value);
	  //alert("hi");
	  RegDocValidator.getInputFieldValidationMessage(idf, valuef, {
	          callback:function(dataFromServer) {
	                  setInputFieldStatusPhoto(idf, dataFromServer);
	                  }
	  });
}

function joinDoctor()
{
	document.location = "JoinNetwork.htm";
}

function joinLab()
{
	document.location = "LabNetwork.htm";
}