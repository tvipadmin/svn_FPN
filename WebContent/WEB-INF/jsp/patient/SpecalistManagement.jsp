<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/SpecialistManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/SpecalistCommon.js"></script>

<title><fmt:message key="project.title"/></title>
</head>
<body onload="init('<fmt:message key="patient_Referral"/>','<fmt:message key="youHave"/>','<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<c:out value="${login.loginId}"/>'); show('ListTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/SepMainMenu.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="patient.management"/></td>
        <td align="right" valign="middle"></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="familyPhyMain" >
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="newReff" align="center" style="font-stretch: expanded; font-weight: bold;">
</div>
<div id="ListTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="all_patient"/></td>
        <td align="right" valign="middle"><fmt:message key="enter_patient"/>: <input type="text" id="patname" name="patientNameS" size="10"  onkeyup="javascript:testFunction(); searchOnKeyPatientNo('<c:out value="${login.loginId}"/>')"> <fmt:message key="enter_patno"/>: <input type="text" id="patno" name="patientNoS" size="10"  onkeyup="javascript:testFunction(); searchOnKeyPatientNo('<c:out value="${login.loginId}"/>','yes')">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:testFunction(); searchOnKeyPatientNo('<c:out value="${login.loginId}"/>')"></td>
    </tr>
</table>

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="Patient_PatientId"/></th>
        <th><fmt:message key="patient_name"/></th>
      	<th><fmt:message key="age_gender"/></th>
      	<th><fmt:message key="contact_details"/></th>
        <th><fmt:message key="doctor_name"/></th>
        <th><fmt:message key="view_emr"/></th>
      </tr>
    </thead>
  <tbody id="patientbody">
    <tr id="pattern" style="display:none;">
      <td id="tablePatId">id</td>
      <td id="tablefamphydetails">famphydetails</td>
      <td id="tableagegenderdetails">agegender</td>
      <td id="tableDocContactDetails">contactdetails</td>
      <td id="tableDoctor">doctorName</td>
      <td width="15%" align="center"><input id="view" type="button" value="View EMR" onClick="testFunction(); emrClicked(this.id,'<c:out value="${login.loginId}"/>');"/></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>

</form>

<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>
