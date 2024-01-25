<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/CaseManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/CaseManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/HistoryManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabServicesManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/ViewEmr.js"></script>
<title><fmt:message key="project.title"/></title>
</head>
<body onload="printPage('<c:out value="${command.patient.patientId}"/>','<c:out value="${command.fpcase.caseId}"/>','<c:out value="${command.docType}"/>','<c:out value="${login.loginId}"/>');">
<table width="100%" border="0" align="center" cellpadding="6" cellspacing="4">
  <tr>
    <td valign="top" class="h2"><fmt:message key="patient_MedRecord"/></td>
    <td><strong>Case Title  : <c:out value="${command.fpcase.caseTitle}"/></strong></td>
  </tr>
</table>
<strong><span class="h3"><fmt:message key="patient.Details"/></span></strong><br>
<table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" class="printtable">
  <tr>
    <td><fmt:message key="Patient_PatientId"/> :</td>
    <td><b> <c:out value="${command.patient.patientId}"/></b></td>
    <td><fmt:message key="patient_name"/> :</td>
    <td><b> <c:out value="${command.patient.firstName}"/>&nbsp;<c:out value="${command.patient.lastName}"/></b></td>
  </tr>
  <tr>
    <td><fmt:message key="patient_GAB"/> :</td>
    <td><b> <c:out value="${command.patient.gender}"/>/<c:out value="${command.patient.ageYears}"/>y <c:out value="${command.patient.ageMonths}"/>m / <c:out value="${command.patient.bloodGroup}"/> +</b></td>
    <td><fmt:message key="doctor_name"/> :</td>
    <td><b><c:out value="${command.patient.creator.firstName}"/>
    <c:out value="${command.patient.creator.lastName}"/></b></td>
  </tr>
</table>
<br>
<strong><span class="h3"><fmt:message key="case_details"/> </span></strong><br>
<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="printtable">
  <tr>
    <td width="30%" valign="top">&nbsp;&nbsp;<strong><strong><fmt:message key="Case_Status"/></strong></td>
    <td ><c:out value="${command.fpcase.caseStatus}"/></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Complaint_Symptoms"/></strong></td>
    <td ><c:out value="${command.fpcase.complaint_symptoms}"/></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_CaseDescription"/></strong></td>
    <td ><c:out value="${command.fpcase.caseDescription}"/></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;&nbsp;<strong><fmt:message key="case"/> <fmt:message key="History_Attachments"/></strong></td>
    <td><c:forEach var="fpCase" items="${command.caseAttachment}" varStatus="status">
			<input type="hidden" value="<c:out value="${fpCase}"/>">
            <a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','case')" onclick="testFunction()"><c:out value="${fpCase}"/></a>&nbsp;&nbsp;
        </c:forEach>&nbsp;&nbsp; </td>
  </tr>
</table>
<!-- End Of Case Details -->
<br>
<!-- Start Of History Details -->
<c:if test="${command.history != null}">
<strong><span class="h3"><fmt:message key="historyDetails"/></span></strong><br>
<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="printtable">
  <tr>
    <td width="30%" valign="top"><strong>&nbsp;&nbsp;<fmt:message key="History_Pastillness"/></strong></td>
    <td ><c:out value="${command.history.pastIllness}"/></td>
  </tr>
  <tr>
    <td valign="top"><strong>&nbsp;&nbsp;<fmt:message key="History_FamilyHistory"/></strong></td>
    <td ><c:out value="${command.history.familyHistory}"/></td>
  </tr>
  <tr>
    <td valign="top"><strong>&nbsp;&nbsp;<fmt:message key="History_CurrentMedication"/></strong></td>
    <td ><c:out value="${command.history.currentMedication}"/></td>
  </tr>
  <tr>
    <td valign="top"><strong>&nbsp;&nbsp;<fmt:message key="History_Immunization"/></strong></td>
    <td ><c:out value="${command.history.immunization}"/></td>
  </tr>
  <tr>
    <td valign="top"><strong>&nbsp;&nbsp;<fmt:message key="History_Allergies"/></strong></td>
    <td ><c:out value="${command.history.allergies}"/></td>
  </tr>
  <tr>
    <td valign="top"><strong>&nbsp;&nbsp;<fmt:message key="history"/> <fmt:message key="History_Attachments"/></strong></td>
    <td><c:forEach var="fpCase" items="${command.historyAttachment}" varStatus="status">
     	<a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','history')" onclick="testFunction()"><c:out value="${fpCase}"/></a>&nbsp;&nbsp;
        </c:forEach>&nbsp;&nbsp; </td>
  </tr>
</table>
</c:if>
<!-- End Of History Details -->
<br>
<!-- Start Of Vital Sign Details -->
<c:if test="${command.vitalSignsDisplay == 'yes'}">
<strong><span class="h3"><fmt:message key="vitalSignDet"/></span></strong><br>
<table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" class="printtable">
  <tr align="center">
    <td><fmt:message key="Case_Weight"/><br>
      <strong><c:out value="${command.fpcase.weight}"/></strong></td>
    <td><fmt:message key="Case_Height"/><br>
      <strong><strong><c:out value="${command.fpcase.height}"/></strong></td>
    <td><fmt:message key="Case_Temperature"/><br>
      <strong><c:out value="${command.fpcase.temperature}"/></strong></td>
    <td><fmt:message key="Case_BloodPressure"/><br>
      <strong><c:out value="${command.fpcase.bloodPressure}"/></strong></td>
    <td><fmt:message key="Case_PulseRate"/><br>
      <strong><c:out value="${command.fpcase.pulseRate}"/></strong></td>
    <td><fmt:message key="Case_RespirationRate"/><br>
      <strong><c:out value="${command.fpcase.respirationRate}"/></strong></td>
      <c:choose>
     <c:when test="${(command.patient.gender == 'F')}">
    <td><fmt:message key="Case_Lmp"/><br>
      <strong><c:out value="${command.fpcase.lmp}"/></strong></td>
      </c:when>
   </c:choose>
  </tr>
</table>
</c:if>
<!-- End Of Vital Sign Details -->
<br>
<!-- Start Of Order Test -->
<c:if test="${ !empty command.labServiceList}">
<strong><span class="h3"><fmt:message key="orderTestDet"/></span></strong><br>
<table width="95%" border="1" align="center" cellpadding="4" cellspacing="0" class="printtable">
  <tr>
    <td width="33%"><strong><fmt:message key="service_name"/></strong></td>
    <td width="33%"><strong><fmt:message key="lab_name"/></strong></td>
    <td width="33%"><strong><fmt:message key="report"/></strong></td>
  </tr>
  <c:forEach var="labtest" items="${command.labServiceList}" varStatus="status">
  <tr>
    <td><c:out value="${labtest.serviceName}"/> </td>
    <td><c:out value="${labtest.lab.labName}"/></td>
    <td align="center"><a href="javascript:testFunction(); windowOpen('<c:out value="${labtest.labTest.testReport}"/>','labReport')" onclick="testFunction()"><c:out value="${labtest.labTest.testReport}"/></a></td>
  </tr>
  </c:forEach>
</table>
</c:if>
<!-- End Of Order Test -->
<br>
<!-- Start Of Diagonisis Details -->
<c:if test="${command.diagonisisDisplay == 'yes'}">
<strong><span class="h3"><fmt:message key="diagonisisDet"/></span></strong><br>
<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="printtable">
  <tr>
    <td width="30%" valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Diagnosis"/></strong></td>
    <td ><c:out value="${command.fpcase.diagnosis}"/></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Prescription"/></strong></td>
    <td ><c:out value="${command.fpcase.prescription}"/></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Impression"/></strong></td>
    <td ><c:out value="${command.fpcase.impression}"/></td>
  </tr>
</table>
</c:if>
<!-- End Of Diaonisis Deatils -->
<br>
<!--Start Of Second Opinion  -->
<c:if test="${!empty command.specialityList}">
<c:if test="${command.docType=='fp'}">
<strong><span class="h3"><fmt:message key="second_opinion_details"/></span></strong><br>
<c:forEach var="docSep" items="${command.specialityList}" varStatus="status">
<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="printtable">
  <tr>
    <td width="25%"><strong><fmt:message key="specalist_details"/> : </strong> <c:out value="${docSep.specalistId.firstName}"/> <c:out value="${docSep.specalistId.lastName}"/>  <c:out value="${docSep.specalistId.designation}"/>  <c:out value="${docSep.specalistId.specialty}"/></td>
  </tr>
  <tr>
    <td><strong><fmt:message key="contact_details"/> : </strong><c:out value="${docSep.specalistId.contactDetails.address}"/>
      <c:out value="${docSep.specalistId.contactDetails.contactNumber}"/>
    <c:out value="${docSep.specalistId.contactDetails.email}"/></td>
  </tr>
  <tr>
    <td><strong><fmt:message key="opinion"/> : </strong><c:out value="${docSep.opinions}"/></td>
  </tr>
</table></c:forEach></c:if>
<c:if test="${command.docType=='specalist'}">
<c:forEach var="docSep" items="${command.specialityList}" varStatus="status">
<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="printtable">
<tr>
    <td><strong><fmt:message key="opinion"/> : </strong><c:out value="${docSep.opinions}"/></td>
  </tr>
</table>
</c:forEach>
</c:if>
</c:if>
<br>
<span class="h2"><fmt:message key="print_footer"/></span>
</body>
</html>