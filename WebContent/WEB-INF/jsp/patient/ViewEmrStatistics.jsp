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

<body onload="init(); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');show('summartyDiv');hide('saveSection');hide('caseTitleDiv');hide('orderContent');">
   
<%session.setAttribute("PhotoName","");%>
 <c:set var="CaseTitleJ" value="${command.fpcase.caseTitle}" scope="session"/>
 <c:set var="PatientIdJ" value="${command.patient.patientId}" scope="session"/>
 <c:set var="CaseIdJ" value="${command.fpcase.caseId}" scope="session"/>
<div id="divHeaderEMR"><%@ include file="/WEB-INF/jsp/common/EMRHeaderStatistics.jsp" %></div>

<div id="divPagebodyEMR">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td valign="top" width="200">

<div class="outer_container">
		<table width="100%" align="center" cellpadding="0" cellspacing="0" class="tableEMR">
         	<tr>
                <th><fmt:message key="patient.Details"/></th>
            </tr>
            <tr>
                <td align="center" valign="top"> <c:choose>
			<c:when test="${(command.patient.photo != null) && (command.patient.photo != '')}">
				<IMG src="DisplayPhoto.htm?Id=<c:out value="${command.patient.patientId}"/>&typePerson=patient" width=150 height=150 border=1>
			</c:when>
			<c:otherwise>
			    <img src="<fmt:message key="project.context"/>/images/nophoto.gif" width="150" height="150">
			</c:otherwise>
		</c:choose>	</td>
            </tr>
            <tr>
            <td class="patInfo"><fmt:message key="Patient_PatientId"/></td>
          </tr>
          <tr>
            <td><b>
              <c:out value="${command.patient.patientId}"/>
            </b></td>
          </tr>
          <tr>
            <td class="patInfo"><fmt:message key="patient_name"/></td>
          </tr>
          <tr>
            <td><b>
              <c:out value="${command.patient.firstName}"/>&nbsp;<c:out value="${command.patient.lastName}"/>
            </b></td>
          </tr>
          <tr>
            <td class="patInfo"><fmt:message key="patient_GAB"/></td>
          </tr>
          <tr>
            <td><b>
              <c:out value="${command.patient.gender}"/>/<c:out value="${command.patient.ageYears}"/>y
              <c:out value="${command.patient.ageMonths}"/>m /
              <c:out value="${command.patient.bloodGroup}"/>
            </b></td>
          </tr>
          <tr>
            <td class="patInfo"><fmt:message key="doctor_name"/></td>
          </tr>
          <tr>
            <td><b>
              <c:out value="${command.patient.creator.firstName}"/>
              <c:out value="${command.patient.creator.lastName}"/>
            </b></td>
            </tr>
        </table>
		</div>
<!-- end of patient details section -->
</td>
<td valign="top">
<div class="outer_container" id="summartyDiv">

<!--########################################-->
<!-- ########## Start Of Summary Page  -->
<!-- Start Of Case Title -->

<!-- End Of Case Title -->
<table width="100%" border="1" class="viewtable">
<tr><th colspan="2" align="left" ><fmt:message key="casetitle"/>  :  <c:out value="${command.fpcase.caseTitle}"/></th></tr>
</table>
<br>
<!-- Start Of Case Details -->
<table width="100%" border="1" class="viewtable">
    <tr><th colspan="2" align="left" ><fmt:message key="case_details"/> </th></tr>
    <tr><td width="25%" valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Status"/></strong></td><td class="textwrap"><c:out value="${command.fpcase.caseStatus}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Complaint_Symptoms"/></strong></td><td class="textwrap"><c:out value="${command.fpcase.complaint_symptoms}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_CaseDescription"/></strong></td><td class="textwrap"><c:out value="${command.fpcase.caseDescription}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="case"/> <fmt:message key="History_Attachments"/></strong></td><td>
        <c:forEach var="fpCase" items="${command.caseAttachment}" varStatus="status">
			<input type="hidden" value="<c:out value="${fpCase}"/>">
            <a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','case')" onclick="testFunction()"><c:out value="${fpCase}"/></a>&nbsp;&nbsp; 
        </c:forEach>
        </td>
	</tr>
</table>
<!-- End Of Case Details -->
<br>
<!-- Start Of History Details -->
<table width="100%" border="1" class="viewtable">
    <tr><th colspan="2" align="left" ><fmt:message key="historyDetails"/> </th></tr>
    <tr><td width="25%" valign="top">&nbsp;&nbsp;<strong><fmt:message key="History_Pastillness"/></strong></td><td class="textwrap"><c:out value="${command.history.pastIllness}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="History_FamilyHistory"/></strong></td><td class="textwrap"><c:out value="${command.history.familyHistory}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="History_CurrentMedication"/></strong></td><td class="textwrap"><c:out value="${command.history.currentMedication}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="History_Immunization"/></strong></td><td class="textwrap"><c:out value="${command.history.immunization}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="History_Allergies"/></strong></td><td class="textwrap"><c:out value="${command.history.allergies}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="history"/> <fmt:message key="History_Attachments"/></strong></td>
    <td><c:forEach var="fpCase" items="${command.historyAttachment}" varStatus="status">
     	<a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','history')" onclick="testFunction()"><c:out value="${fpCase}"/></a>&nbsp;&nbsp;
        </c:forEach></td></tr>
</table>
<!-- End Of History Details -->
<br>
<!-- Start Of Vital Sign Details -->
<table width="100%" border="1" class="viewtable">
	<tr><th colspan="7" align="left"><fmt:message key="vitalSignDet"/></th></tr>
    <tr align="center">
        <td><fmt:message key="Case_Weight"/><br><strong><c:out value="${command.fpcase.weight}"/></strong></td>
        <td><fmt:message key="Case_Height"/><br><strong><c:out value="${command.fpcase.height}"/></strong></td>
        <td><fmt:message key="Case_Temperature"/><br><strong><c:out value="${command.fpcase.temperature}"/></strong></td>
        <td><fmt:message key="Case_BloodPressure"/><br><strong><c:out value="${command.fpcase.bloodPressure}"/></strong></td>
        <td><fmt:message key="Case_PulseRate"/><br><strong><c:out value="${command.fpcase.pulseRate}"/></strong></td>
        <td><fmt:message key="Case_RespirationRate"/><br><strong><c:out value="${command.fpcase.respirationRate}"/></strong></td>
   <c:choose>
     <c:when test="${(command.patient.gender == 'F')}">
        <td><fmt:message key="Case_Lmp"/><br><strong><c:out value="${command.fpcase.lmp}"/></strong></td>
   </c:when>
   </c:choose>
    </tr>
</table>
<!-- End Of Vital Sign Details -->
<br>
<!-- Start Of Order Test -->
<table width="100%" border="1" class="viewtable">
    <tr><th colspan="3" align="left" ><fmt:message key="orderTestDet"/></th></tr>
    <tr>
    	<td width="33%"><strong><fmt:message key="service_name"/></strong></td>
        <td width="33%"><strong><fmt:message key="lab_name"/></strong></td>
        <td width="33%"><strong><fmt:message key="report"/></strong></td>
    </tr>
    <c:forEach var="labtest" items="${command.labServiceList}" varStatus="status">
    <tr>
        <td><c:out value="${labtest.serviceName}"/></td>
        <td><c:out value="${labtest.lab.labName}"/></td>
        <td> <c:if test="${labtest.labTest.testReport != null}">
        <c:forTokens items="${labtest.labTest.testReport}" delims="#" var="reportName" varStatus="status" >
         <c:out value="${status.count}" /> : <a href="javascript:testFunction(); windowOpen('<c:out value="${reportName}"/>','labReport')" onclick="testFunction()"><c:out value="${reportName}"/></a>
         <br/>
         </c:forTokens>    
        </c:if></td>
    </tr>
    </c:forEach>
</table>
<!-- End Of Order Test -->
<br>
<!-- Start Of Diagonisis Details -->
<table width="100%" border="1" class="viewtable">
    <tr><th colspan="2" align="left" ><fmt:message key="diagonisisDet"/></th></tr>
    <tr><td width="25%" valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Diagnosis"/></strong></td><td class="textwrap"><c:out value="${command.fpcase.diagnosis}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Prescription"/></strong></td><td class="textwrap"><c:out value="${command.fpcase.prescription}"/></td></tr>
    <tr><td valign="top">&nbsp;&nbsp;<strong><fmt:message key="Case_Impression"/></strong></td><td class="textwrap"><c:out value="${command.fpcase.impression}"/></td></tr>
</table>
<!-- End Of Diaonisis Deatils -->
<br>
<!--Start Of Second Opinion  -->

<c:if test="${command.docType=='fp'}"> 
<table width="100%" border="1" class="viewtable">
    <tr><th colspan="3" align="left"><fmt:message key="second_opinion_details"/></th></tr>
    <tr>
    	<td width="25%">&nbsp;&nbsp;<strong><fmt:message key="specalist_details"/></strong></td>
		<td><strong><fmt:message key="contact_details"/></strong></td>
		<td><strong><fmt:message key="opinion"/></strong></td>
	</tr>
	<c:forEach var="docSep" items="${command.specialityList}" varStatus="status"> 
	<tr>
		<td><fmt:message key="name"/>: <c:out value="${docSep.specalistId.firstName}"/> <c:out value="${docSep.specalistId.lastName}"/><br>
            <fmt:message key="Doctor_Designation"/>: <c:out value="${docSep.specalistId.designation}"/><br>
            <fmt:message key="Doctor_Specialty"/>: <c:out value="${docSep.specalistId.specialty}"/></td>
            <td><fmt:message key="Doctor_Address"/>: <c:out value="${docSep.specalistId.contactDetails.address}"/><br>
            <fmt:message key="Doctor_ContactNo"/>: <c:out value="${docSep.specalistId.contactDetails.contactNumber}"/><br>
            <fmt:message key="Doctor_Email"/>: <c:out value="${docSep.specalistId.contactDetails.email}"/></td>
            <td><c:out value="${docSep.opinions}"/></td>
    </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${command.docType=='specalist'}">
 <table width="100%" border="1" class="viewtable">
    <tr><th colspan="3" align="left"><fmt:message key="second_opinion_details"/></th></tr>
    <tr>
    	
		<td><strong><fmt:message key="opinion"/></strong></td>
	</tr>
	<c:forEach var="docSep" items="${command.specialityList}" varStatus="status"> 
	<tr>
		  <td><c:out value="${docSep.opinions}"/></td>
    </tr>
    </c:forEach>
</table>
</c:if>
<input type="button" value="Print Summary" onclick="printSummary('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>','<c:out value="${command.fpcase.caseId}"/>')">
<br>
<br>
<!--End Of Second Opinion  -->
<!-- End Of Summary Page -->
<!--########################################-->
</div>

<input id="labServiceIds" type="hidden" size="20" name="labServiceIds"/>
<input id="docIds" type="hidden" size="20" name="docIds"/>
<input id="caseId" type="hidden" size="20" name="caseIdN" value="<c:out value="${command.fpcase.caseId}"/>"/>
<input id="caseStatus" type="hidden" size="20" name="caseStatusN" value="<c:out value="${command.fpcase.caseStatus}"/>"/>
<input id="starttstamp" type="hidden" size="20" name="starttstampN" value="<c:out value="${command.fpcase.starttstamp}"/>"/>
<input id="endtstamp" type="hidden" size="20" name="endtstampN" value="<c:out value="${command.fpcase.endtstamp}"/>"/>
<input id="patientId" type="hidden" size="20" name="patientIdN" value="<c:out value="${command.patient.patientId}"/>"/>
<input id="historyId" type="hidden" size="20" name="historyIdN" value="<c:out value="${command.history.historyId}"/>"/>
<input id="serviceList" type="hidden" size="20" name="serviceListN" value="<c:out value="${command.labServiceList}"/>"/>
<input id="docType" type="hidden" size="20" name="docTypeN" value="<c:out value="${command.docType}"/>"/>

<br>
</td>
</tr>
</table>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>