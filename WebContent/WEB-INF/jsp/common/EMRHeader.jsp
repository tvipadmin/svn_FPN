<script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function P7_getWD(tDim){ //v2.4 by PVII
 var sh=0,sw=0,rt=0;if(window.innerWidth){sh=window.innerHeight;sw=window.innerWidth;
 }else if(document.body){sh=document.body.clientHeight;sw=document.body.clientWidth;
 if(document.body.offsetHeight==sh&&document.documentElement&&document.documentElement.clientHeight){
  sh=document.documentElement.clientHeight;}
 if(document.body.offsetWidth==sw&&document.documentElement&&document.documentElement.clientWidth) {
 sw=document.documentElement.clientWidth;}}rt=(tDim=="width")?sw:sh;return rt;
}

function P7_getLDims(el) { //v2.4 by PVII
 var x,ll=0,tl=0,hh=0,ww=0,wx,g,gg;
 if((g=MM_findObj(el))!=null){gg=(document.layers)?g:g.style;ll=parseInt(gg.left);tt=parseInt(gg.top);
 if(isNaN(ll)){if(g.currentStyle){ll=parseInt(g.currentStyle.left);}else if(document.defaultView){
 ll=parseInt(document.defaultView.getComputedStyle(g,"").getPropertyValue("left"));}if(isNaN(ll)){ll=0;}}
 if(isNaN(tt)){if(g.currentStyle){tt=parseInt(g.currentStyle.top);}else if(document.defaultView){
 tt=parseInt(document.defaultView.getComputedStyle(g,"").getPropertyValue("top"));}if(isNaN(ll)){tt=0;}}
 if(document.all||document.getElementById){ww=parseInt(g.offsetWidth);hh=parseInt(g.offsetHeight);
 if(!ww){ww=parseInt(g.style.pixelWidth);if(!ww){ww=parseInt(g.style.width);}}if(!hh){
 hh=parseInt(g.style.pixelHeight);}if(g.hasChildNodes){for(x=0;x<g.childNodes.length;x++){
 wx=parseInt(g.childNodes[x].offsetWidth);if(wx>ww){ww=wx;}}}}else if(document.layers){
 ww=parseInt(g.clip.width);hh=parseInt(g.clip.height);}}var aR=[ll,tt,hh,ww];return aR;
}

function P7_alignRR() { //v2.4 by PVII
 var i;if(!document.p7alignRR) {return;}if(document.layers){
 if(innerWidth!=document.p7RRiw || innerHeight!=document.p7RRih){location.reload();}
 }else{document.p7RR=1;for(i=0;i<p7aRR.length;i++){eval(p7aRR[i]);}document.p7RR=0;}
}

function P7_alignWD2() { //v2.4 by PVII
 var g,gg,lp=0,tp=0,aL,lh,lw,ww,wh,pa='px',args=P7_alignWD2.arguments;if(document.layers||window.opera){pa='';}
 for(var i=0;i<args.length;i+=4){if((g=MM_findObj(args[i]))!=null){gg=(document.layers)?g:g.style;
 mm=parseInt(args[i+1]);mr=parseInt(args[i+2]);ml=parseInt(args[i+3]);ww=parseInt(P7_getWD('width'));
 wh=parseInt(P7_getWD('height'));aL=P7_getLDims(args[i]);lh=aL[2];lw=aL[3];tp=aL[1];lp=aL[0];
 if(mm==1){lp=mr;}if(mm==2){lp=parseInt((ww-lw-mr)/2);if(lp<0){lp=0;}}if(mm==3){lp=ww-lw-mr;if(lp<ml){lp=ml;}}
 if(mm==4){tp=parseInt((wh-lh-mr)/2);if(tp<0){tp=0;}}if(mm==5){tp=parseInt((wh-lh)/2);lp=parseInt((ww-lw)/2);
 if((lp-mr)<=0){lp=mr;}if((tp-ml)<=0){tp=ml;}}if(mm==6){tp=wh-lh-mr;}gg.top=tp+pa;gg.left=lp+pa;
 if(!document.p7alignRR){p7aRR=new Array();document.p7alignRR=true;document.p7RR=0;
 if(document.layers){document.p7RRiw=innerWidth;document.p7RRih=innerHeight;}onresize=P7_alignRR;}
 if(document.p7RR==0){p7aRR[p7aRR.length]="P7_alignWD2('"+args[i]+"',"+mm+","+mr+","+ml+")";}}}
}
//-->

var flag="";
function closeIt()
{
 //alert("hi!!! :)" +flag);
 var path="http://"+"<fmt:message key="dataServer"/>"+"<fmt:message key="project.context"/>"+"/";
 if(navigator.appName == "Netscape" && flag=="")
   {
	document.location =path+"Logout.htm";
   }
   else
   {
	  flag="";
   }
 if(navigator.appName == "Microsoft Internet Explorer")
 {	  
  if(flag=="" && window.event.clientY < 0  )
  {
	 // alert("here");
  document.location = 'Logout.htm';
  }
  else
  {
	  flag="";
  }
 }  
	  
}

window.onbeforeunload = closeIt;

function testFunction()
{
	//alert(flag);
	flag="NoWindowClose";
	//alert(flag);
}

function callViewCaseTitle(CaseTitleJ,patientId,doctorId,historydisplay,addednew,notaddednew,correcterrors,section,docType,fpDocId)
{
	testFunction();
	//alert("hi"+CaseTitleJ);
	//alert(document.getElementById("caseTitleType").value);
	var list = document.getElementById("caseTitleType").value;
	var listEach = list.split("#####");
	//alert(listEach[5]);
	if(document.getElementById('caseStatus').value == 'OPEN')
	{
	    updateCase(patientId,doctorId,historydisplay,addednew,notaddednew,correcterrors,section,docType,fpDocId)
		document.location = "ViewEmr.htm?caseId="+listEach[0]+"&patientId="+listEach[1]+"&docId="+listEach[2]+"&docType="+listEach[3]+"&patientName="+listEach[4]+"&emr="+listEach[5]+"";
	}
	else
	{
		document.location = "ViewEmr.htm?caseId="+listEach[0]+"&patientId="+listEach[1]+"&docId="+listEach[2]+"&docType="+listEach[3]+"&patientName="+listEach[4]+"&emr="+listEach[5]+"";
	}
}

</script>
<link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
<table width="100%" height="25" border="0" cellspacing="2" cellpadding="0" class="navtable">
  <tr>
    <td class="h3 whitefont"><fmt:message key="emr.display"/></td>
    <td align="right" class="EMRlogout"><fmt:message key="page.welcome"/> <strong> <c:out value="${login.person.firstName}"/> <c:out value="${login.person.lastName}"/></strong> | <a href="<c:url value="javascript:testFunction();openHelp();"/>" onclick="testFunction();"><fmt:message key="page.Help"/></a> | <a href="<c:url value="#"/>" onclick="checkCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','nothingLogout','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"><fmt:message key="user.Logout"/></a> | <c:if test="${command.docType=='specalist'}"> <a href="<c:url value="#"/>" onclick="testFunction(); checkCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','nothingSep','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>');"><fmt:message key="exit.EMR"/></a> </c:if> <c:if test="${command.docType=='fp'}"> <a  href="#" onclick="testFunction(); checkCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','nothingFp','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>');"><fmt:message key="exit.EMR"/> </a></c:if></td>
  </tr>
</table>

<div class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="31">
            <ul id="MenuBar1" class="MenuBarHorizontal" >
                <c:if test="${command.docType=='fp' && command.fpcase.caseStatus=='OPEN'}">
                 <c:choose>
                 
                <c:when test="${command.fpcase.caseTitle == null}">

                </c:when>
               <c:otherwise>
                <li><a id="caseDetM" href="javascript:highLightEmr('caseDet'); show('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');" onclick="testFunction();setEditAll('no')"><fmt:message key="case_details"/></a></li>
                <li><a id="historyM" href="javascript:highLightEmr('history'); hide('caseDetailsDiv'); show('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');" onclick="testFunction();setEditAll('no')"><fmt:message key="page.history"/></a></li>
                <li><a id="vitalSignM" href="javascript:highLightEmr('vitalSign'); hide('caseDetailsDiv'); hide('historyDetailsDiv');show('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');" onclick="testFunction();setEditAll('no')"><fmt:message key="page.vitalSigns"/></a></li>
               </c:otherwise>
               </c:choose>
                </c:if>

                <c:if test="${command.fpcase.caseStatus=='OPEN'}">
                 <c:choose>
                 
                 <c:when test="${command.fpcase.caseTitle == null}">

                </c:when>
                 <c:otherwise>
                <li><a id="labTestM" href="javascript:highLightEmr('labtest'); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');show('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');" onclick="testFunction();setEditAll('no')"><fmt:message key="page.labTest"/></a></li>
                 </c:otherwise>
                 </c:choose>
                </c:if>
                <c:if test="${command.docType=='fp' && command.fpcase.caseStatus=='OPEN'}">  
                 <c:choose>
                 
                 <c:when test="${command.fpcase.caseTitle == null}">

                </c:when>
               <c:otherwise>   
                <li><a id="caseDiagM" href="javascript:highLightEmr('caseDiag'); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');show('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');show('saveSection');show('caseTitleDiv');" onclick="testFunction();setEditAll('no')"><fmt:message key="page.caseDiagnosis"/></a></li>
                </c:otherwise>  
                </c:choose>
                </c:if>
                <c:if test="${command.fpcase.caseStatus=='OPEN'}">
                <c:choose>
                
                 <c:when test="${command.fpcase.caseTitle == null}">

                </c:when>
               <c:otherwise>
                <li><a id="secondOpiM" href="javascript:highLightEmr('secondOpinion'); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');show('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');" onclick="testFunction();setEditAll('no')"><fmt:message key="second_opinion"/></a></li>
                </c:otherwise>
                </c:choose>
                </c:if>
                <c:if test="${command.docType=='fp' && command.fpcase.caseStatus=='OPEN'}">
                 <c:choose>
                
                 <c:when test="${command.fpcase.caseTitle == null}">

                 </c:when>
                 <c:otherwise>
                <li><a id="edilAllM" href="javascript:highLightEmr('editAll'); show('caseDetailsDiv'); show('historyDetailsDiv');show('vitalSignsDetailsDiv');show('labTestDiv');show('caseDiagnosisDiv');show('secondOpinionDiv');hide('summartyDiv');show('saveSection');show('caseTitleDiv');" onclick="testFunction();setEditAll('yes')"><fmt:message key="page.editAll"/></a></li>
                 </c:otherwise>
                 </c:choose>
                </c:if>
                <!--  <li><a href="javascript:hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');show('summartyDiv');hide('saveSection');" onclick="testFunction()"><fmt:message key="page.summary"/></a></li>-->
                <c:choose>
                <c:when test="${command.fpcase.caseTitle == null}">

                 </c:when>  
                 <c:otherwise> 
               <li><a id="summaryM" href="javascript:highLightEmr('summaryM'); testFunction(); summaryData('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','Summary','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>');" onclick="testFunction()"><fmt:message key="page.summary"/></a></li>
                </c:otherwise>
              </c:choose>
            
            </ul>
           <div  id="caseDropDown"> <li> &nbsp;<select id="caseTitleType" onBlur=" " tabindex="14" onchange="callViewCaseTitle('<c:out value="${CaseTitleJ}"/>','<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','NoSection','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')" name="caseTitleType">
            <c:forEach var="fpCase" items="${command.fpCaseList}" varStatus="status">
                <option value ="<c:out value="${fpCase.caseId}"/>#####<c:out value="${command.patient.patientId}"/>#####<c:out value="${login.loginId}"/>#####<c:out value="${command.docType}"/>#####<c:out value="${command.patient.firstName}"/>#####<c:out value="${command.emrBO}"/>" <c:if test="${fpCase.caseTitle == CaseTitleJ}">selected</c:if>><c:out value="${fpCase.caseTitle}"/></option>
           </c:forEach>
          </select></li></div>
        </td>
</tr>

<tr>
        <td id="caseNumbers" align="right" valign="middle"><c:forEach var="fpCase" items="${command.fpCaseList}" varStatus="status">
        <a href="ViewEmr.htm?caseId=<c:out value="${fpCase.caseId}"/>&patientId=<c:out value="${command.patient.patientId}"/>&docId=<c:out value="${login.loginId}"/>&docType=<c:out value="${command.docType}"/>&patientName=<c:out value="${command.patient.firstName}"/>&emr=<c:out value="${command.emrBO}"/>" onclick="testFunction();checkCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','NoSection','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>');">
           <c:choose>
	         <c:when test="${fpCase.caseStatus=='OPEN'}">
              <span class="OpenedCase"><c:out value="${status.count}"/></span></a>
             </c:when>
             <c:otherwise>
               <span class="NotOpenedCase"><c:out value="${status.count}"/></span></a> 
             </c:otherwise>
            </c:choose>
        </c:forEach></td>
    </tr>

</table>
</div>
<script type="text/javascript">
<!--
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
//-->
</script>
