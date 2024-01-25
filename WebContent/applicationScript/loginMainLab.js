var peopleCacheLab ={ };
var peopleCacheLab1 ={ };
var previousListLab =new Array();
var nextListLab=new Array();
var currentListLab = new Array();
var startLab,endLab,pstartLab,pendLab,totalLab=0,fstartLab,fendLab;
var diffLab=0;
var lastRecordLab="check";
var temppreviousListLab = new Array();
var tempcurrentListLab = new Array();
var tempnextListELab = new Array();
var tempcurrentListELab = new Array();
var tempPreLab=0;
var tempNextLab=0;
var tempLastPageLab = 0;
var tempFlagPreLab=0;
var tempPreFirstLab="true";
var tempOnlyTwoLab ="false";

// to change the page content we have to change diffLab,tempStart,fstartLab,fendLab,pstartLab,pendLab value

function initLab(valLab) {
	  startLab=0,endLab =parseInt(valLab),diffLab = parseInt(valLab),pstartLab=0,pendLab=parseInt(valLab),fstartLab=0,fendLab=parseInt(valLab);
	  loadLab(startLab,endLab);
	}


function loadLab(startLab,endLab)
{
	var tempStart = diffLab*2;
	MainPageManagement.loadLabs(startLab,tempStart,'load',"",function(data) {
	    	
		    // Delete all the rows except for the "pattern" row
		      dwr.util.removeAllRows("labbody", { filter:function(tr) {
		      return (tr.id != "pattern1");
		    }});
		   var  cc=0;nn=0,pp=0;
		    // Create a new set cloned from the pattern row
		   var lab,id,labName;
		   
		    var totalVal =data.length;
		    	
		    if(totalVal-1 >diffLab && startLab==0)
		    {
		     show('previousLab');	
		     show('nextLab');	
		    }
		    
		    if(startLab==0)
		    {
		    	hide('previousLab');	
		    }
		    
		    if(endLab > totalVal-1)
		    {
		    	endLab = totalVal-1
		    }
		    
		   	for (var j = startLab; j < endLab; j++) {
		   		previousListLab[pp]=data[j];
		   		pp++;
		   	}
		   //	alert(previousListLab.length);
		   	
			for (var p = startLab; p < endLab; p++) {
		   		currentListLab[cc]=data[p];
		   		cc++;
		   	}
			//alert(currentListLab.length);
		   	
		   	startLab = endLab;
		   	endLab = startLab+diffLab;
		   
		   	//alert(startLab);
		   	//alert(endLab);
		   	if(endLab > totalVal-1)
		    {
		    	endLab = totalVal-1
		    }
		   //	alert(startLab);
		   //	alert(endLab);
			for (var k = startLab; k < endLab; k++) {
				nextListLab[nn]=data[k];
				nn++;
		   	}
		//	alert("size");
			//alert(nextListLab.length);
		   	
		    for (var i = fstartLab; i < fendLab; i++) {
		    
		      lab = data[i];
		     // alert("doc: "+lab);
		      if(lab != null  && lab != 'true')
		      {
		      id = lab.labId;
		      labName = lab.labName;
		      
		      dwr.util.cloneNode("pattern1", { idSuffix:id });
		      dwr.util.setValue("tableLab" + id,"");
		      document.getElementById("tableLab" + id).innerHTML="<a href=\"javascript:testFunction(); openWindowL('"+id+"');\" onclick=\"testFunction()\" >"+labName+"</a>";
		      $("pattern1" + id).style.display = "";
		      peopleCacheLab[id] = lab;
		      }
		    }
		    //alert("in"+startLab);
		   // alert("in"+endLab);
		    setValLab(totalVal,startLab,endLab,data[totalVal-1]);
		  });
	    
	   // alert(startLab);
	    //alert(endLab);
	    
		}

function setValLab(totalVal,s,e,fValue)
{
	//alert(s);
	//alert(e);
	totalLab = totalVal;
	startLab = s;
	endLab =e;
	
	lastRecordLab = fValue;
	//alert(lastRecordLab);
	//alert(startLab);
   //	alert(endLab);
   	//alert(pstartLab);
   	//alert(pendLab);
}
  function previousLabData()
  {
	 //alert("pre");
	 show('nextLab');
	 lastRecordLab = "false"
	  dwr.util.removeAllRows("labbody", { filter:function(tr) {
	      return (tr.id != "pattern1");
	    }});
	
	  var lab,id,labName,type;
	//  alert(previousListLab.length);
	  for (i = 0; i < previousListLab.length; i++) {
		    
	      lab = previousListLab[i];
	    //alert("what"+lab);
	      id = lab.labId;
	      
	      labName = lab.labName;
	      dwr.util.cloneNode("pattern1", { idSuffix:id });
	      dwr.util.setValue("tableLab" + id,"");
	      document.getElementById("tableLab" + id).innerHTML="<a href=\"javascript:testFunction(); openWindowL('"+id+"');\" onclick=\"testFunction()\" >"+labName+"</a>";
	      $("pattern1" + id).style.display = "";
	      peopleCacheLab1[id] = lab;
	    }
	//alert(pstartLab);
	//alert(pendLab);
	//alert("tempPreLab"+tempPreLab);
	  //alert("tempNextLab"+tempNextLab);
	  if(pstartLab == 0 && pendLab == diffLab && tempPreLab == 1 )  // change the value here also, according to page content
	  {
		  hide('previousLab');
		  //alert("Start Of Record!!!");
		 // alert(tempPreLab);
		 // alert(tempNextLab);
		 // alert(tempLastPageLab);
		  tempPreLab=0;
		  
		  tempNextLab=0;
		  
		  tempLastPageLab = 0;
		  tempFlagPreLab=0;
		  
		// alert(startLab);
		// alert(endLab);
		// alert(pstartLab);
		// alert(pendLab);
		 // alert("pre"+previousListLab[0].firstName);
		 // alert("next"+nextListLab[0].firstName);
		 // alert("curr"+currentListLab[0].firstName);
		//  startLab =3;
		//  endLab =6;
		  for(var g22=0;g22<tempcurrentListLab.length;g22++)
		  {
			  lab = tempcurrentListLab[g22]
			  currentListLab[g22] = lab;
		  }
		
		  for(var g33=0;g33<temppreviousListLab.length;g33++)
		  {
			  lab = temppreviousListLab[g33]
			  previousListLab[g33] = lab;
		  }
		  for(var g4=0;g4<previousListLab.length;g4++)
		  {
			  lab = previousListLab[g4]
			  temppreviousListLab[g4] = lab;
		  }
		  
		  for(var g2=0;g2<currentListLab.length;g2++)
		  {
			  lab = currentListLab[g2]
			  tempcurrentListLab[g2] = lab;
		  }
		
		  for(var g3=0;g3<previousListLab.length;g3++)
		  {
			  lab = previousListLab[g3]
			  temppreviousListLab[g3] = lab;
		  }
		  for(var g1=0;g1<currentListLab.length;g1++)
		  {
			  lab = currentListLab[g1]
			  nextListLab[g1] = lab;
		  }
		  
		  for(var x1=0;x1<previousListLab.length;x1++)
		  {
			  lab = previousListLab[x1]
			  currentListLab[x1] =lab;
		  }
		  //alert("pre"+previousListLab[0].firstName);
		 // alert("next"+nextListLab[0].firstName);
		 // alert("curr"+currentListLab[0].firstName);
	
		  //change the value here according to page content
		
		  startLab =diffLab;
		  endLab=diffLab*2;
		  pstartLab=0;
		  pendLab = diffLab;
		  //alert(tempNextLab);
		 // alert(startLab);
		 // alert(endLab);
		  //alert(pstartLab);
		  //alert(pendLab);
		 
			
		  lastRecordLab = "false"
	      //alert(lastRecordLab);
	  }
	  else
	  {
		  
		  tempPreLab = tempPreLab-1;
		  tempNextLab=tempNextLab-1;
		 // alert("inside else tempPreLab"+tempPreLab);
		 // alert("pch"+pstartLab);
		//	 alert("pch"+pendLab);
		// alert("ch"+startLab);
			// alert("ch"+endLab);
		 // alert("pstartLab"+pstartLab);
			 //alert("pendLab"+pendLab);
				//alert("nstart"+startLab);
				// alert("nend"+endLab);
		//alert("tempPreFirstLab: "+tempPreFirstLab);
	 if(tempPreFirstLab == "true")
 	 {
  	     pendLab =pstartLab;
		 pstartLab = pendLab-diffLab;
         endLab = startLab;
		  startLab = endLab-diffLab;
	 }
		//alert("pstartLab"+pstartLab);
		// alert("pendLab"+pendLab);
		//	alert("nstart"+startLab);
		//	 alert("nend"+endLab);
		//alert(currentListLab[0].firstName);
		//alert(previousListLab[0].firstName);
		//alert(nextListLab[0].firstName);
		tempPreFirstLab = "true";
		  for(var g=0;g<currentListLab.length;g++)
		  {
			  lab = currentListLab[g]
			  nextListLab[g] = lab;
		  }
		  //previousListLab = currentListLab;
		  for(var x=0;x<previousListLab.length;x++)
		  {
			  lab = previousListLab[x]
			  currentListLab[x] =lab;
		  }
		 //nextListLab = currentListLab;
		// curentList = previousListLab;
		  lab =  previousListLab[0];
		 // alert(lab.firstName);
		  lab =  currentListLab[0];
		// alert(lab.firstName);
		
	  }
	  
	 // alert(p1);
	 // alert(p2);
	//  alert(c1);
	//  alert(c2);
	//  alert(n1);
	//  alert(n2);
	
	  prepareListLab("previous");
  }

  function nextLabData()
  {
	// alert("hi"); 
	  show('previousLab');
	 //alert(tempNextLab);
	  dwr.util.removeAllRows("labbody", { filter:function(tr) {
	      return (tr.id != "pattern1");
	    }});
	  //alert(nextListLab.length);
	 
	  var lab,id,labName;
	  for (var i = 0; i < nextListLab.length; i++) {
		    
	      lab = nextListLab[i];
	      //alert("what"+lab);
	      id = lab.labId;
	     // alert(id);
	      labName = lab.labName;
	     // alert(labName);
	      dwr.util.cloneNode("pattern1", { idSuffix:id });
	      dwr.util.setValue("tableLab" + id,labName);
	      $("pattern1" + id).style.display = "";
	      peopleCacheLab1[id] = lab;
	    }
	  //alert(startLab);
	 //alert(endLab);
	//  alert(p1);
	//  alert(p2);
	//  alert(c1);
	//  alert(c2);
	 // alert(n1);
	 // alert(n2);
	 // alert("next"+startLab);
	 // alert("next"+endLab);
	  //alert(" tempPreLab: "+tempPreLab);
	//	alert(" tempNextLab: "+tempNextLab);
	//	 alert("tempLastPageLab: "+tempLastPageLab);  
		// alert("lastRecordLab: "+lastRecordLab); 
	//alert("tempNextLab"+tempNextLab);
	  var checkF = tempNextLab+1
	//alert(checkF);
	//  alert("tempLastPageLab: "+tempLastPageLab)
	//alert(tempLastPageLab == checkF);
		
		// alert("lastRecordLab: "+lastRecordLab); 
	if(tempLastPageLab == checkF || tempOnlyTwoLab =="true")
	{
		lastRecordLab = "true"
			tempOnlyTwoLab ="false";	
		//startLab = startLab+diffLab;
		//endLab = endLab+diffLab; 
			
	}


	  if(lastRecordLab == "true")
	  {
		  hide('nextLab');
		  //alert("End Of The Record!!!");
		 // alert("pstartLab"+pstartLab);
		//	alert("pendLab"+pendLab);
		//	alert("nstart"+startLab);
		//	alert("nend"+endLab);
		  //alert(tempNextLab);
		//  alert("tempNextLab"+tempNextLab);
		  if(tempNextLab == 0)
		  {
			  tempOnlyTwoLab ="true"
		  }
		  else
		  {
			  tempOnlyTwoLab ="false"
		  }
		  tempPreLab = tempPreLab+1;
		  tempNextLab = tempNextLab+1;
		  //alert("tempNextLab"+tempNextLab);
		  //alert("tempPreLab "+tempPreLab);
		  tempLastPageLab = tempNextLab;
		  tempFlagPreLab = tempPreLab
		 // alert(startLab);
		//  alert(endLab);
		//alert(pstartLab);
		//alert(pendLab);
		//alert("pre"+previousListLab[0].firstName);
		 // alert("pre"+previousListLab[1].firstName);
		//  alert("pre"+previousListLab[2].firstName);
		//alert("next"+nextListLab[0].firstName);
		//alert("curr"+currentListLab[0].firstName);
		 lastRecordLab = "false";
		  
		   //alert("pstartLab"+pstartLab);
				//alert("pendLab"+pendLab);
				//alert("nstart"+startLab);
			//	alert("nend"+endLab);
			//alert(tempFlagPreLab);	
				
		   //pendLab =startLab;
		  // pstartLab =pendLab-diffLab;
		 // alert("pstartLab"+pstartLab);
		//	alert("pendLab"+pendLab);
			//alert("nstart"+startLab);
		//	alert("nend"+endLab);
		 
		// alert(startLab);
		 // alert(endLab);
		 // alert(pstartLab);
		 // alert(pendLab);
		
			tempPreFirstLab ="false";
		  
		  for(var gg2=0;gg2<tempcurrentListELab.length;gg2++)
		  {
			  lab = tempcurrentListELab[gg2]
			  currentListLab[gg2] = lab;
		  }
		
		  for(var gg3=0;gg3<tempnextListELab.length;gg3++)
		  {
			  lab = tempnextListELab[gg3]
			  nextListLab[gg3] = lab;
		  }
		  for(var gj2=0;gj2<currentListLab.length;gj2++)
		  {
			  lab = currentListLab[gj2]
			  tempcurrentListELab[gj2] = lab;
		  }
		
		  for(var gj3=0;gj3<nextListLab.length;gj3++)
		  {
			  lab = nextListLab[gj3]
			  tempnextListELab[gj3] = lab;
		  }
		  
		  
		  
		  for(var gc=0;gc<currentListLab.length;gc++)
		  {
			  lab = currentListLab[gc]
			  previousListLab[gc] = lab;
		  }
		  currentListLab = new Array();
		  for(var xc=0;xc<nextListLab.length;xc++)
		  {
			  lab = nextListLab[xc]
			  currentListLab[xc] =lab;
		  }
		 // alert("pre"+previousListLab[0].firstName);
		 // alert("pre"+previousListLab[1].firstName);
		//  alert("pre"+previousListLab[2].firstName);
		// alert("next"+nextListLab[0].firstName);
		// alert("curr"+currentListLab[0].firstName);
	  }
	  else
	  {
		//  alert("alert"+startLab);
		 
		  pendLab = startLab;
		  pstartLab = pendLab-diffLab;
		  startLab = endLab;
		  endLab = endLab+diffLab;
		  tempNextLab = tempNextLab+1;
		  tempPreLab = tempPreLab+1;
		  //alert("tempPreLab"+tempPreLab);
		  
		  for(var g=0;g<currentListLab.length;g++)
		  {
			  lab = currentListLab[g]
			  previousListLab[g] = lab;
		  }
		  //previousListLab = currentListLab;
		  currentListLab = new Array();
		  for(var x=0;x<nextListLab.length;x++)
		  {
			  lab = nextListLab[x]
			  currentListLab[x] =lab;
		  }
		 // currentListLab = nextListLab;
		lab =  previousListLab[0];
		//  alert(lab.firstName);
		  lab =  currentListLab[0];
		// alert(lab.firstName);
		 // alert(previousListLab.length);
		 // alert(currentListLab.length);
		   
	  }
	 // alert("next");
	 // alert(p1);
	//  alert(p2);
	 // alert(c1);
	//  alert(c2);
	//  alert(n1);
	//  alert(n2);
	  nextListLab = new Array();
	
	  prepareListLab('next');
	
	  
  }
  
  function prepareListLab(typeClick)
  { 
   
	
	var name = document.getElementById("searchnameLab").value;



	 if(typeClick == 'next')
	 { // alert("prepareListLab tempPreLab: "+tempPreLab);
		//alert("prepareListLab tempNextLab: "+tempNextLab);
		//alert("prepareListLab tempLastPageLab: "+tempLastPageLab);
		//alert("prepareListLab tempFlagPreLab: "+tempFlagPreLab);
		 if( tempLastPageLab == tempNextLab)
		 {
			 lastRecordLab = "true";
		 }
		
  	MainPageManagement.loadLabs(startLab,endLab,'next',name,function(data) {
  	  
  		
  		   var totalVal =data.length;
  		 totalLab = totalVal;
  		  // alert(data[totalVal-1]);
  	       lastRecordLab = data[totalVal-1]; 
  	       //alert(lastRecordLab);
  		   for (var j = 0; j < totalVal-1; j++) {
  		   		nextListLab[j]=data[j];
  		   }
  		
  		 updateNextList(nextListLab);
  		  });
	 }
	 else if(typeClick == 'previous')
	 {
		 //alert("tempNextLab: "+tempNextLab);
		// alert("tempLastPageLab: "+tempLastPageLab);
		 
		// alert("p"+pstartLab);
		// alert("p"+pendLab);
		
		
		 
		 	MainPageManagement.loadLabs(pstartLab,pendLab,'previous',name,function(data) {
		 	  	  
		  		
		  		   var totalVal =data.length;
		  	
		  		   for (var j = 0; j < data.length; j++) {
		  		   		previousListLab[j]=data[j];
		  		   }
		  		updatePreviousList(previousListLab);
		  			
		  		  });
	 }
  	}
  
  function searchOnHomeLab()
  {
	  //alert("here...");
	 
	
		
		var name = document.getElementById("searchnameLab").value;
		//alert(name);
		startLab=0,endLab =diffLab,pstartLab=0,pendLab=diffLab,fstartLab=0,fendLab=diffLab;
		previousListLab =new Array();
		nextListLab=new Array();
		currentListLab = new Array();
		lastRecordLab="check";
		temppreviousListLab = new Array();
		tempcurrentListLab = new Array();
	    tempnextListELab = new Array();
		tempcurrentListELab = new Array();
		tempPreLab=0;
		tempNextLab=0;
		tempLastPageLab = 0;
		tempFlagPreLab=0;
		tempPreFirstLab="true";
		tempOnlyTwoLab ="false";
		hide('previousLab');
		hide('nextLab');
		
	  var tempStart = diffLab*2;
		MainPageManagement.loadLabs(startLab,tempStart,'load',name,function(data) {
		    	
			    // Delete all the rows except for the "pattern" row
			      dwr.util.removeAllRows("labbody", { filter:function(tr) {
			      return (tr.id != "pattern1");
			    }});
			   var  cc=0;nn=0,pp=0;
			    // Create a new set cloned from the pattern row
			   var lab,id,labName;
			   
			    var totalVal =data.length;
			    	
			    if(totalVal-1 >diffLab && startLab==0)
			    {
			     show('previousLab');	
			     show('nextLab');	
			    }
			    
			    if(startLab==0)
			    {
			    	hide('previousLab');	
			    }
			    
			    if(endLab > totalVal-1)
			    {
			    	endLab = totalVal-1
			    }
			    
			   	for (var j = startLab; j < endLab; j++) {
			   		previousListLab[pp]=data[j];
			   		pp++;
			   	}
			   //	alert(previousListLab.length);
			   	
				for (var p = startLab; p < endLab; p++) {
			   		currentListLab[cc]=data[p];
			   		cc++;
			   	}
				//alert(currentListLab.length);
			   	
			   	startLab = endLab;
			   	endLab = startLab+diffLab;
			   
			   	//alert(startLab);
			   	//alert(endLab);
			   	if(endLab > totalVal-1)
			    {
			    	endLab = totalVal-1
			    }
			   //	alert(startLab);
			   //	alert(endLab);
				for (var k = startLab; k < endLab; k++) {
					nextListLab[nn]=data[k];
					nn++;
			   	}
			//	alert("size");
				//alert(nextListLab.length);
			   	
			    for (var i = fstartLab; i < fendLab; i++) {
			    
			      lab = data[i];
			     // alert("doc: "+lab);
			      if(lab != null  && lab != 'true')
			      {
			      id = lab.labId;
			      labName = lab.labName;
			      
			      dwr.util.cloneNode("pattern1", { idSuffix:id });
			      dwr.util.setValue("tableLab" + id,"");
			      document.getElementById("tableLab" + id).innerHTML="<a href=\"javascript:testFunction(); openWindowL('"+id+"');\" onclick=\"testFunction()\" >"+labName+"</a>";
			      $("pattern1" + id).style.display = "";
			      peopleCacheLab[id] = lab;
			      }
			    }
			    //alert("in"+startLab);
			   // alert("in"+endLab);
			    setValLab(totalVal,startLab,endLab,data[totalVal-1]);
			  });
		    
		   // alert(startLab);
		    //alert(endLab);
  }
function updatePreviousList(list)
{
	//previousListLab =list;
}

function updateNextList(list)
{
	//nextListLab =list;
}
function openWindowL(labId) 
{ 
newwin = window.open('ViewLabMainPage.htm?labId='+labId,"mywindow","menubar=0,resizable=0,width=450,height=210,left=20,top=10,scrolling=yes"); 
} 

function testFunction()
{
	flag="NoWindowClose";
}