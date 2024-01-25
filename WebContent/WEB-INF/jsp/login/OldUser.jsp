<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/MainPageManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/loginMain.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/loginMainLab.js"></script>

<title><fmt:message key="project.title"/></title>
<link href="images/fpstylesheet.css" rel="stylesheet" type="text/css" />
</head>
<body onload="init('<fmt:message key="homePageListSize"/>');initLab('<fmt:message key="homePageListSize"/>');hide('previousDoc'); hide('nextDoc'); hide('previousLab'); hide('nextLab');">

<form method="POST" >
<div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top">
        <table width="100%" border="0" align="center">
            <tr>
                <td width="162" align="center">
                	<img class="logo" src="images/logo.jpg" width="250" height="67" alt="Vital Health Portal"/></td>
                <th align="right" valign="bottom" class="h3"></th>
            </tr>
        </table>
<br />
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="aboutusTable">
     <tr>
        <td width="75%"><img src="images/collage.jpg" width="755" height="171" /></td>
        
        <td align="center" valign="middle" class="errorMessage"><c:out value="${login.loginId}"/> <fmt:message key="login.OldUser"/>
        <br><br>
        <input type='button' class='button' value="<fmt:message key="login.back"/>" OnClick="Javascript:document.location.href='Login.htm'">
        </td>
      </tr>
    </table>
</form>
<form name="mainHomePage">
<table width="99%" border="1" align="center" cellpadding="0" cellspacing="0" class="aboutusTable">

<tr><th align="left" class="h2Home homeUserListH" colspan="2"><fmt:message key="subscribers"/></th></tr>
<tr>
<td width="49%" class="homeUserListSH">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="h4" align="left"><fmt:message key="doctors"/>&nbsp;<input type="text" id="searchnameDoc" name="searchnameDoc" size="20" onKeyUp="javascript:searchOnHomeDoc()"></td>
		<td align="right" width="25px"><div id="previousDoc"><a href="javascript:previousData();"><img src="images/previous.gif" width="16" height="16" border="0"></a></div></td>
		<td width="25px"><div id="nextDoc"><a href="javascript:nextData();"><img src="images/next.gif" width="16" height="16" border="0"></a></div></td>
	</tr>
	</table></td>
	<td width="50%" class="homeUserListSH"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td class="h4" align="left"><fmt:message key="labs"/>&nbsp;<input type="text" id="searchnameLab" name="searchnameLab" size="20" onKeyUp="javascript:searchOnHomeLab()"></td>
		<td align="right" width="25px"><div id="previousLab"><a href="javascript:previousLabData();"><img src="images/previous.gif" width="16" height="16" border="0"></a></div></td>
		<td width="25px"><div id="nextLab"><a href="javascript:nextLabData();"><img src="images/next.gif" width="16" height="16" border="0"></a></div></td>
	</tr>
</table>
</td>
</tr>

<tr>
<td valign="top">
	<table width="100%" border="1" align="left">
		<tbody id="doctorbody">
			<tr id="pattern" style="display:none;">
				<td id="tableDoctor" class="homeUserList">doctorName</td>
			</tr>
		</tbody>
	</table>
</td>
<td valign="top">
	<table width="100%" border="1" align="left">
		<tbody id="labbody">
			<tr id="pattern1" style="display:none;">
				<td id="tableLab" class="homeUserList">name</td>
			</tr>
		</tbody>
	</table>
</td>
</tr>
</table>
      <br />
      <table width="99%" border="0" align="center" class="aboutusTable">
        <tr>
        <th align="left" class="h2Home">About Vital eHealth Network</th>
      </tr>
      <tr>
    <td valign="bottom" class="aboutusTableText">An eHealth network participated by Family Physicians,   General Physicians, Laboratories and Specialist Doctors. Using the network   physicians can send test orders to laboratories and get the report uploaded back   through the inbuilt patient's electronic record. Physicians can send the patient   record over internet to any registered Specialist Doctor and get second opinions.</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<br>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="42%" align="left" valign="top"><table width="99%" border="0" align="center" class="aboutusTable">
            <tr>
              <th align="left" class="h2Home">TeleVital News</th>
            </tr>
            <tr>
              <td valign="top" class="aboutusTableText">TeleVital - Recipient of the &quot;Indian Telemedicine Company of the Year - 2009&quot; award by Frost &amp; Sullivan <a href="http://www.televital.com/pressroom/fs2009award.asp" target="_blank">More</a><br />
                <br />
                TCIL awards telemedicine solution  contract to TeleVital,   implementing telemedicine based software across Pan African nations, this includes 53 countries in African continent. <a href="http://www.televital.com/pressroom/news2008.asp" target="_blank">More</a></td>
            </tr>
          </table></td>
          <td width="15%"  align="center" valign="top"><table width="99%" border="0" align="center" class="aboutusTable">
            <tr>
            <td align="center"><img src="images/tvlogo.jpg" width="119" height="108" longdesc="http://www.televital.com" /></td></tr></table></td>
          <td width="42%"  align="center" valign="top"><table width="99%" border="0" align="center" class="aboutusTable">
            <tr>
              <th align="left" class="h2Home">Telemedicine News</th>
            </tr>
            <tr>
              <td valign="top" class="aboutusTableText">www.telemedindia.org - All about Indian Telemedicine.... <a href="http://www.telemedindia.org" target="_blank">Go</a><br />
                <br />
                Telemedicine society of India... <a href="http://www.tsi.org.in" target="_blank">Go</a><br />
                <br />
                <br />
                <br /></td>
            </tr>
          </table></td>
        </tr>
      </table>
</div>
<br> <br>
<div id="divFooter">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navtablebottom">
      <tr>
        <td height="25" valign="middle" class="center links"><fmt:message key="footer.televital"/> | <a href="<c:url value="javascript:testFunction();openTerm();"/>"><fmt:message key="footer.terms"/></a> | <a href="<c:url value="javascript:testFunction();openPrivacy();"/>"><fmt:message key="footer.privacyPolicy"/></a> | <a href="http://www.televital.com" target="_blank"><fmt:message key="footer.televitalSite"/></a> | <a href="http://www.healthconsole.com" target="_blank"><fmt:message key="footer.healthConsole"/></a></td>
      </tr>
  </table>
</div>
</form>
</body>
</html>
