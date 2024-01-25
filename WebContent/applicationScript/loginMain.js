var peopleCache ={ };
var peopleCache1 ={ };
var previousList =new Array();
var nextList=new Array();
var currentList = new Array();
var start,end,pstart,pend,total=0,fstart,fend;
var diff =0
var lastRecord="check";
var temppreviousList = new Array();
var tempcurrentList = new Array();
var tempnextListE = new Array();
var tempcurrentListE = new Array();
var tempPre=0;
var tempNext=0;
var tempLastPage = 0;
var tempFlagPre=0;
var tempPreFirst="true";
var tempOnlyTwo ="false";

// to change the page content we have to change diff,tempStart,fstart,fend,pstart,pend value

function init(val) {
	  //alert(val);
	  start=0,end =parseInt(val),diff =parseInt(val),pstart=0,pend=parseInt(val),fstart=0,fend=parseInt(val);
	  loadDoctor(start,end);
	}

function joinKnow()
{
	document.location = "JoinNetwork.htm";
}

function checkMsg()
{
	alert("Message Sending");
	document.location = "http://instant.smsinfy.com/web2sms.php?username=demo&password=televital&to=9632339393&sender=BULKSMS&message=hi";
	
}


function loadDoctor(start,end)
{
	//alert("hello");
	var tempStart = diff*2;
	MainPageManagement.loadDoctors(start,tempStart,'load',"",function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		   var  cc=0;nn=0,pp=0;
		    // Create a new set cloned from the pattern row
		    var doctor,id,doctorName,type;
		   
		    var totalVal =data.length;
		   if(totalVal-1 > diff && start==0)
		    {
		     show('previousDoc');	
		     show('nextDoc');	
		    }
		    
		    if(start==0)
		    {
		    	hide('previousDoc');	
		    }
		    
		    if(end > totalVal-1)
		    {
		    	end = totalVal-1
		    }
		    
		   	for (var j = start; j < end; j++) {
		   		previousList[pp]=data[j];
		   		pp++;
		   	}
		   //	alert(previousList.length);
		   	
			for (var p = start; p < end; p++) {
		   		currentList[cc]=data[p];
		   		cc++;
		   	}
			//alert(currentList.length);
		   	
		   	start = end;
		   	end = start+diff;
		   
		   	//alert(start);
		   	//alert(end);
		   	if(end > totalVal-1)
		    {
		    	end = totalVal-1
		    }
		   //	alert(start);
		   //	alert(end);
			for (var k = start; k < end; k++) {
				nextList[nn]=data[k];
				nn++;
		   	}
		//	alert("size");
			//alert(nextList.length);
		   	
		    for (var i = fstart; i < fend; i++) {
		    
		      doctor = data[i];
		     // alert("doc: "+doctor);
		      if(doctor != null  && doctor != 'true')
		      {
		      id = doctor.doctorId;
		      type = doctor.type;
		     // alert(doctor.specialty=='');
		      if(type == 'BOTH' || type ==  'SPECALIST')
		      {
		    	  if(doctor.specialty=='' || doctor.specialty==null)
		    	  {
		    		  doctorName = doctor.firstName +" "+doctor.lastName;
		    	  
		    	  }
		    	  else
		    	  {
		    		  doctorName = doctor.firstName +" "+doctor.lastName+", "+doctor.specialty;
		    	  }
		      }
		      else
		      {
		    	  doctorName = doctor.firstName +" "+doctor.lastName;
		      }
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tableDoctor" + id,"");
		    
		      document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindowH('"+id+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = doctor;
		      }
		    }
		    //alert("in"+start);
		   // alert("in"+end);
		    setVal(totalVal,start,end,data[totalVal-1]);
		  });
	    
	   // alert(start);
	    //alert(end);
	    
		}

function setVal(totalVal,s,e,fValue)
{
	//alert(s);
	//alert(e);
	total = totalVal;
	start = s;
	end =e;
	
	lastRecord = fValue;
	//alert(lastRecord);
	//alert(start);
   //	alert(end);
   	//alert(pstart);
   	//alert(pend);
}
  function previousData()
  {
	 //alert("pre");
	 show('nextDoc');
	 lastRecord = "false"
	  dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	
	  var doctor,id,doctorName,type;
	//  alert(previousList.length);
	  for (i = 0; i < previousList.length; i++) {
		    
	      doctor = previousList[i];
	    //alert("what"+doctor);
	      id = doctor.doctorId;
	     
	      type = doctor.type;
	      if(type == 'BOTH' || type ==  'SPECALIST')
	      {
	    	  if(doctor.specialty=='' || doctor.specialty==null)
	    	  {
	    		  doctorName = doctor.firstName +" "+doctor.lastName;
	    	  
	    	  }
	    	  else
	    	  {
	    		  doctorName = doctor.firstName +" "+doctor.lastName+", "+doctor.specialty;
	    	  }
	      }
	      else
	      {
	    	  doctorName = doctor.firstName +" "+doctor.lastName;
	      }
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tableDoctor" + id,"");
	      
	      document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindowH('"+id+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
	      $("pattern" + id).style.display = "";
	      peopleCache1[id] = doctor;
	    }
	//alert(pstart);
	//alert(pend);
	//alert("tempPre"+tempPre);
	  //alert("tempNext"+tempNext);
	  if(pstart == 0 && pend == diff && tempPre == 1 )  // change the value here also, according to page content
	  {
		  hide('previousDoc');
		  //alert("Start Of Record!!!");
		 // alert(tempPre);
		 // alert(tempNext);
		 // alert(tempLastPage);
		  tempPre=0;
		  
		  tempNext=0;
		  
		  tempLastPage = 0;
		  tempFlagPre=0;
		  
		// alert(start);
		// alert(end);
		// alert(pstart);
		// alert(pend);
		 // alert("pre"+previousList[0].firstName);
		 // alert("next"+nextList[0].firstName);
		 // alert("curr"+currentList[0].firstName);
		//  start =3;
		//  end =6;
		  for(var g22=0;g22<tempcurrentList.length;g22++)
		  {
			  doctor = tempcurrentList[g22]
			  currentList[g22] = doctor;
		  }
		
		  for(var g33=0;g33<temppreviousList.length;g33++)
		  {
			  doctor = temppreviousList[g33]
			  previousList[g33] = doctor;
		  }
		  for(var g4=0;g4<previousList.length;g4++)
		  {
			  doctor = previousList[g4]
			  temppreviousList[g4] = doctor;
		  }
		  
		  for(var g2=0;g2<currentList.length;g2++)
		  {
			  doctor = currentList[g2]
			  tempcurrentList[g2] = doctor;
		  }
		
		  for(var g3=0;g3<previousList.length;g3++)
		  {
			  doctor = previousList[g3]
			  temppreviousList[g3] = doctor;
		  }
		  for(var g1=0;g1<currentList.length;g1++)
		  {
			  doctor = currentList[g1]
			  nextList[g1] = doctor;
		  }
		  
		  for(var x1=0;x1<previousList.length;x1++)
		  {
			  doctor = previousList[x1]
			  currentList[x1] =doctor;
		  }
		  //alert("pre"+previousList[0].firstName);
		 // alert("next"+nextList[0].firstName);
		 // alert("curr"+currentList[0].firstName);
	
		  //change the value here according to page content
		
		  start =diff;
		  end=diff*2;
		  pstart=0;
		  pend = diff;
		  //alert(tempNext);
		 // alert(start);
		 // alert(end);
		  //alert(pstart);
		  //alert(pend);
		 
			
		  lastRecord = "false"
	      //alert(lastRecord);
	  }
	  else
	  {
		  
		  tempPre = tempPre-1;
		  tempNext=tempNext-1;
		 // alert("inside else tempPre"+tempPre);
		 // alert("pch"+pstart);
		//	 alert("pch"+pend);
		// alert("ch"+start);
			// alert("ch"+end);
		 // alert("pstart"+pstart);
			 //alert("pend"+pend);
				//alert("nstart"+start);
				// alert("nend"+end);
		//alert("tempPreFirst: "+tempPreFirst);
	 if(tempPreFirst == "true")
 	 {
  	     pend =pstart;
		 pstart = pend-diff;
         end = start;
		  start = end-diff;
	 }
		//alert("pstart"+pstart);
		// alert("pend"+pend);
		//	alert("nstart"+start);
		//	 alert("nend"+end);
		//alert(currentList[0].firstName);
		//alert(previousList[0].firstName);
		//alert(nextList[0].firstName);
		tempPreFirst = "true";
		  for(var g=0;g<currentList.length;g++)
		  {
			  doctor = currentList[g]
			  nextList[g] = doctor;
		  }
		  //previousList = currentList;
		  for(var x=0;x<previousList.length;x++)
		  {
			  doctor = previousList[x]
			  currentList[x] =doctor;
		  }
		 //nextList = currentList;
		// curentList = previousList;
		  doctor =  previousList[0];
		 // alert(doctor.firstName);
		  doctor =  currentList[0];
		// alert(doctor.firstName);
		
	  }
	  
	 // alert(p1);
	 // alert(p2);
	//  alert(c1);
	//  alert(c2);
	//  alert(n1);
	//  alert(n2);
	
	  prepareListDoctor("previous");
  }

  function nextData()
  {
	// alert("hi"); 
	  show('previousDoc');
	 //alert(tempNext);
	  dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	  //alert(nextList.length);
	 
	  var doctor,id,doctorName,type;
	  for (i = 0; i < nextList.length; i++) {
		    
	      doctor = nextList[i];
	     // alert(doctor);
	      id = doctor.doctorId;
	     // alert(id);
	      type = doctor.type;
	      if(type == 'BOTH' || type ==  'SPECALIST')
	      {
	    	  if(doctor.specialty=='' || doctor.specialty==null)
	    	  {
	    		  doctorName = doctor.firstName +" "+doctor.lastName;
	    	  
	    	  }
	    	  else
	    	  {
	    		  doctorName = doctor.firstName +" "+doctor.lastName+", "+doctor.specialty;
	    	  }
	      }
	      else
	      {
	    	  doctorName = doctor.firstName +" "+doctor.lastName;
	      }
	      dwr.util.cloneNode("pattern", { idSuffix:id });
	      dwr.util.setValue("tableDoctor" + id,"");
	      
	      document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindowH('"+id+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
	      $("pattern" + id).style.display = "";
	      peopleCache1[id] = doctor;
	    }
	  //alert(start);
	 //alert(end);
	//  alert(p1);
	//  alert(p2);
	//  alert(c1);
	//  alert(c2);
	 // alert(n1);
	 // alert(n2);
	 // alert("next"+start);
	 // alert("next"+end);
	  //alert(" tempPre: "+tempPre);
	//	alert(" tempNext: "+tempNext);
	//	 alert("tempLastPage: "+tempLastPage);  
		// alert("lastRecord: "+lastRecord); 
	//alert("tempNext"+tempNext);
	  var checkF = tempNext+1
	//alert(checkF);
	 // alert("tempLastPage: "+tempLastPage)
	//alert(tempLastPage == checkF);
		
		// alert("lastRecord: "+lastRecord); 
	if(tempLastPage == checkF || tempOnlyTwo =="true")
	{
		lastRecord = "true"
			tempOnlyTwo ="false";	
		//start = start+diff;
		//end = end+diff; 
			
	}


	  if(lastRecord == "true")
	  {
		  hide('nextDoc');
		 // alert("End Of The Record!!!");
		 // alert("pstart"+pstart);
		//	alert("pend"+pend);
		//	alert("nstart"+start);
		//	alert("nend"+end);
		  //alert(tempNext);
		 // alert("tempNext"+tempNext);
		  if(tempNext == 0)
		  {
			  tempOnlyTwo ="true"
		  }
		  else
		  {
			  tempOnlyTwo ="false"
		  }
		  tempPre = tempPre+1;
		  tempNext = tempNext+1;
		//  alert("tempNext"+tempNext);
		  //alert("tempPre "+tempPre);
		  tempLastPage = tempNext;
		  tempFlagPre = tempPre
		 // alert(start);
		//  alert(end);
		//alert(pstart);
		//alert(pend);
		//alert("pre"+previousList[0].firstName);
		 // alert("pre"+previousList[1].firstName);
		//  alert("pre"+previousList[2].firstName);
		//alert("next"+nextList[0].firstName);
		//alert("curr"+currentList[0].firstName);
		 lastRecord = "false";
		  
		   //alert("pstart"+pstart);
				//alert("pend"+pend);
				//alert("nstart"+start);
			//	alert("nend"+end);
			//alert(tempFlagPre);	
				
		   //pend =start;
		  // pstart =pend-diff;
		 // alert("pstart"+pstart);
		//	alert("pend"+pend);
			//alert("nstart"+start);
		//	alert("nend"+end);
		 
		// alert(start);
		 // alert(end);
		 // alert(pstart);
		 // alert(pend);
		
			tempPreFirst ="false";
		  
		  for(var gg2=0;gg2<tempcurrentListE.length;gg2++)
		  {
			  doctor = tempcurrentListE[gg2]
			  currentList[gg2] = doctor;
		  }
		
		  for(var gg3=0;gg3<tempnextListE.length;gg3++)
		  {
			  doctor = tempnextListE[gg3]
			  nextList[gg3] = doctor;
		  }
		  for(var gj2=0;gj2<currentList.length;gj2++)
		  {
			  doctor = currentList[gj2]
			  tempcurrentListE[gj2] = doctor;
		  }
		
		  for(var gj3=0;gj3<nextList.length;gj3++)
		  {
			  doctor = nextList[gj3]
			  tempnextListE[gj3] = doctor;
		  }
		  
		  
		  
		  for(var gc=0;gc<currentList.length;gc++)
		  {
			  doctor = currentList[gc]
			  previousList[gc] = doctor;
		  }
		  currentList = new Array();
		  for(var xc=0;xc<nextList.length;xc++)
		  {
			  doctor = nextList[xc]
			  currentList[xc] =doctor;
		  }
		 // alert("pre"+previousList[0].firstName);
		 // alert("pre"+previousList[1].firstName);
		//  alert("pre"+previousList[2].firstName);
		// alert("next"+nextList[0].firstName);
		// alert("curr"+currentList[0].firstName);
	  }
	  else
	  {
		//  alert("alert"+start);
		 
		  pend = start;
		  pstart = pend-diff;
		  start = end;
		  end = end+diff;
		  tempNext = tempNext+1;
		  tempPre = tempPre+1;
		  //alert("tempPre"+tempPre);
		  
		  for(var g=0;g<currentList.length;g++)
		  {
			  doctor = currentList[g]
			  previousList[g] = doctor;
		  }
		  //previousList = currentList;
		  currentList = new Array();
		  for(var x=0;x<nextList.length;x++)
		  {
			  doctor = nextList[x]
			  currentList[x] =doctor;
		  }
		 // currentList = nextList;
		doctor =  previousList[0];
		//  alert(doctor.firstName);
		  doctor =  currentList[0];
		// alert(doctor.firstName);
		 // alert(previousList.length);
		 // alert(currentList.length);
		   
	  }
	 // alert("next");
	 // alert(p1);
	//  alert(p2);
	 // alert(c1);
	//  alert(c2);
	//  alert(n1);
	//  alert(n2);
	  nextList = new Array();
	
	  prepareListDoctor('next');
	
	  
  }
  
  function prepareListDoctor(typeClick)
  {
	
		
		
		var name = document.getElementById("searchnameDoc").value;
	 
	  
	 if(typeClick == 'next')
	 { // alert("prepareListDoctor tempPre: "+tempPre);
		//alert("prepareListDoctor tempNext: "+tempNext);
		//alert("prepareListDoctor tempLastPage: "+tempLastPage);
		//alert("prepareListDoctor tempFlagPre: "+tempFlagPre);
		 if( tempLastPage == tempNext)
		 {
			 lastRecord = "true";
		 }
		 
  	MainPageManagement.loadDoctors(start,end,'next',name,function(data) {
  	  
  		
  		   var totalVal =data.length;
  		 total = totalVal;
  		  // alert(data[totalVal-1]);
  	       lastRecord = data[totalVal-1]; 
  	       //alert(lastRecord);
  		   for (var j = 0; j < totalVal-1; j++) {
  		   		nextList[j]=data[j];
  		   }
  		
  		 updateNextList(nextList);
  		  });
	 }
	 else if(typeClick == 'previous')
	 {
		 //alert("tempNext: "+tempNext);
		// alert("tempLastPage: "+tempLastPage);
		 
		// alert("p"+pstart);
		// alert("p"+pend);
				 
		 	MainPageManagement.loadDoctors(pstart,pend,'previous',name,function(data) {
		 	  	  
		  		
		  		   var totalVal =data.length;
		  	
		  		   for (var j = 0; j < data.length; j++) {
		  		   		previousList[j]=data[j];
		  		   }
		  		updatePreviousList(previousList);
		  			
		  		  });
	 }
  	}
  
function searchOnHomeDoc()
{
	
	
	//alert(ty);
	
	var name = document.getElementById("searchnameDoc").value;
	//alert(name);
	
	var tempStart = diff*2;
	
		
	start=0,end =diff,pstart=0,pend=diff,fstart=0,fend=diff;
	previousList = new Array();
	currentList = new Array();
	nextList    = new Array();
	lastRecord="check";
	temppreviousList = new Array();
	tempcurrentList = new Array();
	tempnextListE = new Array();
	tempcurrentListE = new Array();
	tempPre=0;
	tempNext=0;
	tempLastPage = 0;
	tempFlagPre=0;
	tempPreFirst="true";
	tempOnlyTwo ="false";
	hide('previousDoc');
	hide('nextDoc');
	
	MainPageManagement.loadDoctors(start,tempStart,'load',name,function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("doctorbody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		   var  cc=0;nn=0,pp=0;
		    // Create a new set cloned from the pattern row
		    var doctor,id,doctorName,type;
		   
		    var totalVal =data.length;
		    	
		    if(totalVal-1 > diff && start==0)
		    {
		     show('previousDoc');	
		     show('nextDoc');	
		    }
		    
		    if(start==0)
		    {
		    	hide('previousDoc');	
		    }
		    
		    if(end > totalVal-1)
		    {
		    	end = totalVal-1
		    }
		    
		   	for (var j = start; j < end; j++) {
		   		previousList[pp]=data[j];
		   		pp++;
		   	}
		   //	alert(previousList.length);
		   	
			for (var p = start; p < end; p++) {
		   		currentList[cc]=data[p];
		   		cc++;
		   	}
			//alert(currentList.length);
		   	
		   	start = end;
		   	end = start+diff;
		   
		   	//alert(start);
		   	//alert(end);
		   	if(end > totalVal-1)
		    {
		    	end = totalVal-1
		    }
		   //	alert(start);
		   //	alert(end);
			for (var k = start; k < end; k++) {
				nextList[nn]=data[k];
				nn++;
		   	}
		//	alert("size");
			//alert(nextList.length);
		   	
		    for (var i = fstart; i < fend; i++) {
		    
		      doctor = data[i];
		     // alert("doc: "+doctor);
		      if(doctor != null  && doctor != 'true')
		      {
		      id = doctor.doctorId;
		      type = doctor.type;
		     // alert(doctor.specialty=='');
		      if(type == 'BOTH' || type ==  'SPECALIST')
		      {
		    	  if(doctor.specialty=='' || doctor.specialty==null)
		    	  {
		    		  doctorName = doctor.firstName +" "+doctor.lastName;
		    	  
		    	  }
		    	  else
		    	  {
		    		  doctorName = doctor.firstName +" "+doctor.lastName+", "+doctor.specialty;
		    	  }
		      }
		      else
		      {
		    	  doctorName = doctor.firstName +" "+doctor.lastName;
		      }
		      dwr.util.cloneNode("pattern", { idSuffix:id });
		      dwr.util.setValue("tableDoctor" + id,"");
		    
		      document.getElementById("tableDoctor" + id).innerHTML="<a href=\"javascript:testFunction(); openWindowH('"+id+"');\" onclick=\"testFunction()\" >"+doctorName+"</a>";
		      $("pattern" + id).style.display = "";
		      peopleCache[id] = doctor;
		      }
		    }
		    //alert("in"+start);
		   // alert("in"+end);
		    setVal(totalVal,start,end,data[totalVal-1]);
		  });
	    
	   // alert(start);
	    //alert(end);
	
	
}
function updatePreviousList(list)
{
	//previousList =list;
}

function updateNextList(list)
{
	//nextList =list;
}
function openWindowH(doctorId) 
{ 
	//alert("hey");
newwin = window.open('ViewDocMainPage.htm?doctorId='+doctorId,"mywindow","menubar=0,resizable=0,width=450,height=210,left=20,top=10,scrolling=yes"); 
} 

function openWindowHL(labId) 
{ 
	//alert("hey");
newwin = window.open('ViewLabMainPage.htm?doctorId='+labId,"mywindow","menubar=0,resizable=0,width=450,height=210,left=20,top=10,scrolling=yes"); 
} 

function testFunction()
{
	flag="NoWindowClose";
}