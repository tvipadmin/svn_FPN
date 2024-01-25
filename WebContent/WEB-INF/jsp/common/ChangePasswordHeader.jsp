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
	flag="NoWindowClose";
}

</script>
<link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navtable">
  <tr>
    <td height="32"><ul id="MenuBar1" class="MenuBarHorizontal">
      <li><a id="fpMenu" href="<c:url value="ChangePassword.htm"/>">&nbsp;<fmt:message key="Change.Password"/></a></li>
	  <li>
         <c:choose>
          <c:when test="${login.accountType== 'LAB' }">
          
          <c:if test="${login.accountType== 'LAB'}">
          <a id="labSer" href="ChangeLabProfile.htm?lId=<c:out value="${login.loginId}"/>">&nbsp;<fmt:message key="SubMenu.ChangeProfile"/>&nbsp;</a>
         </c:if>
         </c:when>
         <c:otherwise>
          <c:if test="${login.person.type=='FAMILYPHYSICIAN'|| login.person.type=='SPECALIST' || login.person.type== 'BOTH'}">
          <a id="sepReff" href="ChangeDoctorProfile.htm?hId=<c:out value="${login.loginId}"/>">&nbsp;<fmt:message key="SubMenu.ChangeProfile"/>&nbsp;</a>
          </c:if>
       </c:otherwise>
     </c:choose>
      </li>
       </ul></td>
  </tr>
</table>
<script type="text/javascript">
<!--
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
//-->
</script>
