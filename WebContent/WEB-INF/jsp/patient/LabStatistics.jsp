<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/StatisticsManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/LabStatistics.js"></script> 
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/CaseManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/scw.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagement.js"></script>

<title><fmt:message key="project.title"/></title>
</head>
<body onload="init('<c:out value="${login.loginId}"/>','<fmt:message key="select_fp"/>','<fmt:message key="patients_registered"/>','<fmt:message key="pat_from"/>','<fmt:message key="pat_to"/>','<fmt:message key="pat_by"/>','<fmt:message key="patients_referred"/>','<fmt:message key="patients_ordered"/>','<fmt:message key="patients_ordered_referred1"/>','<fmt:message key="patients_ordered_referred2"/>','<fmt:message key="pat_is"/>'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/LabMainMenu.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="patient.statistics"/></td>
        <td align="right" valign="middle"></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="sepMain" >
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="newOpiLab" align="center" style="font-stretch: expanded; font-weight: bold;">
</div>
<div id="ListTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td align="center">Date <fmt:message key="pat_from"/>&nbsp;<input name='fromDateS' id="fromDate"  size='11' maxlength="11" OnKeyPress="captureReturnKey(event)" onclick='scwShow(this,event);' readonly>&nbsp;<fmt:message key="pat_to"/>&nbsp;<input name='toDateS' id="toDate"  size='11' maxlength="11" OnKeyPress="captureReturnKey(event)"  onclick='scwShow(this,event);' readonly>
         <select name="fpN" id="fp" onChange="changeTypeFp('<fmt:message key="enteredDate_Invalid"/>','<fmt:message key="fromTodate_Invalid"/>','<c:out value="${login.loginId}"/>')"></select> <input type="button" value="Ok" name="okB" onclick="clickOK('<fmt:message key="enteredDate_Invalid"/>','<fmt:message key="fromTodate_Invalid"/>','<c:out value="${login.loginId}"/>','<fmt:message key="select_fp"/>','<fmt:message key="pat_from"/>','<fmt:message key="pat_to"/>','<fmt:message key="patients_ordered"/>');"> <input type="button" value="?" name="commHelp">
       </td>
   </tr>
</table>
<br>

<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="11" height="12"><img src="images/msgtbl_1.gif" width="11" height="12" /></td>
    <td background="images/msgtbl_1a.gif"></td>
    <td width="14" height="12"><img src="images/msgtbl_2.gif" width="14" height="12" /></td>
  </tr>
  <tr>
    <td background="images/msgtbl_4a.gif">&nbsp;</td>
    <td align="center"><b><div id="insertReqText"></div></b></td>
    <td background="images/msgtbl_2a.gif">&nbsp;</td>
  </tr>
  <tr>
    <td width="11" height="18"><img src="images/msgtbl_4.gif" width="11" height="18" /></td>
    <td background="images/msgtbl_3a.gif">&nbsp;</td>
    <td width="14"><img src="images/msgtbl_3.gif" width="14" height="18" /></td>
  </tr>
</table>


<br>

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="Patient_PatientId"/></th>
        <th><fmt:message key="patient_name"/></th>
      	<th><fmt:message key="age_gender"/></th>
      	<th>Check What to Put</th>
      </tr>
    </thead>
  <tbody id="patientbody">
    <tr id="pattern" style="display:none;">
      <td id="tablePatId">id</td>
      <td id="tablefamphydetails">famphydetails</td>
      <td id="tableagegenderdetails" align="center">agegender</td>
      <td width="25%" align="center">&nbsp;</td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>

</form>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>
