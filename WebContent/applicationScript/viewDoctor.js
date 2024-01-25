

function init(prjcon) {
	var doctorIdTemp =document.getElementById("doctorIdV").value;
	//alert("hi hello");
	//alert(doctorIdTemp);
	//alert(document.getElementById("doctorIdV").value);
	loadDoctor(doctorIdTemp,prjcon);
}

function loadDoctor(doctorIdTemp,prjcon)
{
	DoctorManagement.loadDoctorId(doctorIdTemp, function(data) {
    	
		 
		  // Create a new set cloned from the pattern row
		  var doctor,id,docdetails,contactdetails,login,type,photo;
		  
		  for (var i = 0; i < data.length; i++) {
			
		  	doctor = data[i];
		  	
		    if(doctor != null)
		    {
		    id = doctor.doctorId;
		    photo = doctor.photo;
		    //alert(photo);
		    if(photo == "" || photo == null)
		    {
		    	//alert(document.getElementById("imgPhoto").src);
		    	document.getElementById("imgPhoto").src=prjcon+"/images/nophoto.gif";
		    	//alert(document.getElementById("imgPhoto").src);
		    }
		    docdetails=doctor.firstName+" "+doctor.lastName+"\n"+doctor.designation+"\n"+doctor.specialty
		    contactdetails=doctor.contactDetails.address+"\n"+doctor.contactDetails.contactNumber+"\n"+doctor.contactDetails.email
		    type = doctor.type;
		    
		    dwr.util.cloneNode("pattern", { idSuffix:id });
		    dwr.util.setValue("tableDocId" + id, id);
		    dwr.util.cloneNode("pattern1", { idSuffix:id });
		    dwr.util.setValue("tableDocDetails" + id, docdetails);
		    dwr.util.cloneNode("pattern2", { idSuffix:id });
		    dwr.util.setValue("tableDocContactDetails" + id, contactdetails);
		    $("pattern" + id).style.display = "";
		    $("pattern1" + id).style.display = "";
		    $("pattern2" + id).style.display = "";
		   
		   
		    }
		  }
		});
}