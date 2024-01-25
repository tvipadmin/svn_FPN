

function init() {
	var labIdTemp =document.getElementById("labIdV").value;
	//alert("hi hello");
	//alert(doctorIdTemp);
	//alert(document.getElementById("doctorIdV").value);
	loadLab(labIdTemp);
}

function loadLab(labIdTemp)
{
	
	LabManagement.loadIndividualLab(labIdTemp, function(data) {
    	
		 
		  // Create a new set cloned from the pattern row
		  var lab,id,labdetails,contactdetails,login,type;
		  
		  for (var i = 0; i < data.length; i++) {
			
		  	lab = data[i];
		  	
		    if(lab != null)
		    {
		    id = lab.labId;
		    
		    labdetails=lab.labName
		    contactdetails=lab.contactDetails.address+"\n"+lab.contactDetails.contactNumber+"\n"+lab.contactDetails.email
		    //type = lab.type;
		    
		    dwr.util.cloneNode("pattern", { idSuffix:id });
		    dwr.util.setValue("tableDocId" + id, id);
		    dwr.util.cloneNode("pattern1", { idSuffix:id });
		    dwr.util.setValue("tableDocDetails" + id, labdetails);
		    dwr.util.cloneNode("pattern2", { idSuffix:id });
		    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
		    $("pattern" + id).style.display = "";
		    $("pattern1" + id).style.display = "";
		    $("pattern2" + id).style.display = "";
		   
		   
		    }
		  }
		});
}