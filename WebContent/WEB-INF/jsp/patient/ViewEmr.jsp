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
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/SMSSender.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/ViewEmr.js"></script>
<title><fmt:message key="project.title"/></title>
</head>

<c:choose>
<c:when test="${command.section=='secondOpinion'}">
<body onload="init('<fmt:message key="succ_removed"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="sure_remove"/>','<fmt:message key="sureClose_Case"/>','<fmt:message key="remove"/>','<c:out value="${command.caseClosed}"/>','secondOp'); hide('successMessage'); hide('errorMessage'); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');show('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');hide('orderContent');">
</c:when>
<c:when test="${command.section=='labTest'}">
<body onload="init('<fmt:message key="succ_removed"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="sure_remove"/>','<fmt:message key="sureClose_Case"/>','<fmt:message key="remove"/>','<c:out value="${command.caseClosed}"/>','labT'); hide('successMessage'); hide('errorMessage'); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');show('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');hide('orderContent');">
</c:when>
<c:when test="${command.section=='FPCase'}">
<body onload="init('<fmt:message key="succ_removed"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="sure_remove"/>','<fmt:message key="sureClose_Case"/>','<fmt:message key="remove"/>','<c:out value="${command.caseClosed}"/>','caseDet'); hide('successMessage'); hide('errorMessage'); show('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');hide('saveSection');hide('caseTitleDiv');hide('orderContent');">
</c:when>
<c:when test="${command.section=='allSave'}">
<body onload="init('<fmt:message key="succ_removed"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="sure_remove"/>','<fmt:message key="sureClose_Case"/>','<fmt:message key="remove"/>','<c:out value="${command.caseClosed}"/>','editA'); hide('successMessage'); hide('errorMessage');  show('caseDetailsDiv'); show('historyDetailsDiv');show('vitalSignsDetailsDiv');show('labTestDiv');show('caseDiagnosisDiv');show('secondOpinionDiv');hide('summartyDiv');show('saveSection');show('caseTitleDiv');hide('orderContent');">
</c:when>
<c:when test="${command.fpcase.caseTitle == null}">
<body onload="init('<fmt:message key="succ_removed"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="sure_remove"/>','<fmt:message key="sureClose_Case"/>','<fmt:message key="remove"/>','<c:out value="${command.caseClosed}"/>',''); hide('successMessage'); hide('errorMessage'); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');hide('summartyDiv');hide('saveSection');show('caseTitleDiv');hide('caseDropDown');hide('caseNumbers');hide('orderContent');">
</c:when>
<c:otherwise>
<body onload="init('<fmt:message key="succ_removed"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="sure_remove"/>','<fmt:message key="sureClose_Case"/>','<fmt:message key="remove"/>','<c:out value="${command.caseClosed}"/>',''); hide('successMessage'); hide('errorMessage'); hide('caseDetailsDiv'); hide('historyDetailsDiv');hide('vitalSignsDetailsDiv');hide('labTestDiv');hide('caseDiagnosisDiv');hide('secondOpinionDiv');show('summartyDiv');hide('saveSection');hide('caseTitleDiv');hide('orderContent');">
</c:otherwise>
</c:choose>
   
<%session.setAttribute("PhotoName","");%>
 <c:set var="CaseTitleJ" value="${command.fpcase.caseTitle}" scope="session"/>
 <c:set var="PatientIdJ" value="${command.patient.patientId}" scope="session"/>
 <c:set var="CaseIdJ" value="${command.fpcase.caseId}" scope="session"/>

<div id="divHeaderEMR"><%@ include file="/WEB-INF/jsp/common/EMRHeader.jsp" %></div>

<div id="divPagebodyEMR">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td valign="top" width="200">

<!-- patient details section -->
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
<APPLET name="EDigitalPen" CODE="StartDigitalPen" MAYSCRIPT JAVA_CODEBASE="." ARCHIVE="vitalweb/StartDigitalPen.jar,vitalweb/commons-httpclient.jar,vitalweb/commons-logging.jar,vitalweb/commons-codec-1.2.jar" LANGUAGE=JAVASCRIPT width="10" height="10">
		<PARAM NAME="WEBSERVER" VALUE="<fmt:message key="webServer"/>">
		<PARAM NAME="PROJECTCONTEXT" VALUE="<fmt:message key="project.context"/>">
        <PARAM NAME="FIRSTNAME" VALUE="<c:out value="${command.patient.firstName}"/>">
        <PARAM NAME="LASTNAME" VALUE="<c:out value="${command.patient.lastName}"/>">
		<PARAM NAME="AGEYEARS" VALUE="<c:out value="${command.patient.ageYears}"/>">
		<PARAM NAME="AGEMONTHS" VALUE="<c:out value="${command.patient.ageMonths}"/>">
		<PARAM NAME="PATIENTID" VALUE="<c:out value="${command.patient.patientId}"/>">
        <PARAM NAME="GENDER" VALUE="<c:out value="${command.patient.gender}"/>">
		<PARAM NAME="CASEID" VALUE="<c:out value="${command.fpcase.caseId}"/>">
		<PARAM NAME="LOCALPATH" VALUE="<fmt:message key="localDataDir"/>">
		<PARAM NAME="DATAPATH" VALUE="<fmt:message key="data.path"/>">
		<PARAM NAME="CASEPATH" VALUE="<fmt:message key="case.path"/>">
		</APPLET>
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


<!-- case title section -->
<div class="outer_container" id="caseTitleDiv">
<table width="100%" cellpadding="0" cellspacing="0" class="tableEMR">
<tr>
 <td width="62%"><fmt:message key="Case_Title"/> : <input id="caseTitle" type="text" size="34" maxlength="32" name="caseTitleN" onBlur="validateCaseInputField(this);"  value="<c:out value="${command.fpcase.caseTitle}"/>"/>&nbsp;(Max 32 Chars)</td>
 <td> <c:if test="${command.fpcase.caseTitle == null && command.docType=='fp' && command.fpcase.caseStatus=='OPEN'}"><input type="button" name="Ok" value=" Ok " onclick="emrClicked('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>')"></c:if></td>
</tr>
</table>
</div>
<!-- end of case title section -->
<!-- case details section -->
<div class="outer_container" id="caseDetailsDiv">
<table width="100%" cellpadding="0" cellspacing="0" class="tableEMR">
    <tr>
        <th><fmt:message key="case_details"/></th>
        <th align="right">
        <c:choose>
             <c:when test="${command.caseClosed=='no'}">
             <input type="button"  value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','FPCase','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
             </c:when>
       </c:choose>  
&nbsp;
    </th>
    </tr>
    <tr>
        <td width="50%"><fmt:message key="Case_Status"/> : <c:out value="${command.fpcase.caseStatus}"/></td>
        <td><fmt:message key="History_Attachments"/></td>
    </tr>
    <tr>
        <td><fmt:message key="Case_Complaint_Symptoms"/><br>
 <textarea rows="3" id="complaint_symptoms" name="complaint_symptomsN"  cols="40" ><c:out value="${command.fpcase.complaint_symptoms}"/></textarea></td>
        <td rowspan="2" valign="top">
        <table width="100%" cellpadding="2" cellspacing="2">
<c:choose>
<c:when test="${command.caseClosed=='no'}">
<input type="hidden" name="newcaseattachment" id="newcaseattachment" value="">
<input type="hidden" name="caseattachmentCollection" id="caseattachmentCollection" value="<c:out value="${command.fpcase.caseattachments}"/>"><input id="caseattachments" type="hidden" size="20" name="caseattachmentsN" value="<c:out value="${command.fpcase.caseattachments}"/>"/>

<tr> 	
    <td colspan="2"></td>
</tr>
<tr>
    <td><table width="100%" id="caseTable" cellspacing="0" cellpadding="0">
            <tr><th width="1"></th>
                <th><fmt:message key="files"/></th>
                <th><fmt:message key="remove"/></th>
            </tr>
            <c:forEach var="fpCase" items="${command.caseAttachment}" varStatus="status">
            <c:if test="${fpCase != ''}">
            <tr>
                <td><input type="hidden" value="<c:out value="${fpCase}"/>">
                	<a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','case')" onclick="testFunction()"><c:out value="${fpCase}"/></a></td>
                <td ><a href="javascript:testFunction(); removeCaseAtt('<c:out value="${fpCase}"/>');" onclick="testFunction()">Remove</a></td>
            </tr>
           </c:if>
            </c:forEach>
        </table></td>
</tr>
<tr>
    <td><iframe src="http://<fmt:message key="dataServer"/><fmt:message key="project.context"/>/FileUploadCase.htm?uploadtype=case" scrolling="no" frameborder="0" height="60" id='target_upload' name='target_upload'></iframe></td>
</tr>
<tr>
<td><input type=button class="button" value="HandWriting Pad" onClick="javascript:openDigitalPen()"></td>
</tr>
</c:when>

<c:otherwise>
<tr>
  <td>
    <table width="100%" border="1" id="caseTable" class="viewtable">
        <c:forEach var="fpCase" items="${command.caseAttachment}" varStatus="status">
			<input type="hidden" value="<c:out value="${fpCase}"/>">
            <a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','case')" onclick="testFunction()"><c:out value="${fpCase}"/></a> | 
        </c:forEach>
    </table>
  </td>
</tr>
</c:otherwise>
</c:choose>
</table>
        
        </td>
    </tr>
    <tr>
        <td valign="top"><fmt:message key="Case_CaseDescription"/><br>
  <textarea rows="3" id="caseDescription" name="caseDescriptionN"  cols="40" ><c:out value="${command.fpcase.caseDescription}"/></textarea></td>
        </tr>
</table>
</div>
<!-- end of case details section -->

<!-- patient history section -->
<div class="outer_container" id="historyDetailsDiv">
<table width="100%" cellpadding="0" cellspacing="0" class="tableEMR">
   <tr>
    	<th><fmt:message key="History_Details"/></th>
   		<th align="right">
        <c:choose>
          <c:when test="${command.caseClosed=='no'}">
          <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','History','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
          </c:when>
         </c:choose>
         &nbsp;
         </th>
    </tr>
   <tr>
    <td width="50%"><fmt:message key="History_Pastillness"/><br>
<textarea rows="3" id="pastIllness" name="pastIllnessN"  cols="40" ><c:out value="${command.history.pastIllness}"/></textarea></td>
   <td><fmt:message key="History_FamilyHistory"/><br>
    <textarea rows="3" id="familyHistory" name="familyHistoryN"  cols="40" ><c:out value="${command.history.familyHistory}"/></textarea></td>
    </tr>
<tr>
    <td><fmt:message key="History_CurrentMedication"/><br>
    <textarea rows="3" id="currentMedication" name="currentMedicationN"  cols="40" ><c:out value="${command.history.currentMedication}"/></textarea></td>    
    <td><fmt:message key="History_Immunization"/><br>
    <textarea rows="3" id="immunization" name="immunizationN"  cols="40" ><c:out value="${command.history.immunization}"/></textarea></td>
   </tr>
<tr>
    <td valign="top"><fmt:message key="History_Allergies"/><br>
    <textarea rows="3" id="allergies" name="allergiesN"  cols="40" ><c:out value="${command.history.allergies}"/></textarea></td>
    <td valign="top">
    
<c:choose>
<c:when test="${command.caseClosed=='no'}">
<input type="hidden" name="attachmentCollection" id="attachmentCollection" value="<c:out value="${command.history.attachments}"/>"><input id="attachments" type="hidden" size="20" name="attachmentsN" value="<c:out value="${command.history.attachments}"/>"/>
<table width="100%">
  <tr>
    <td colspan="2"><fmt:message key="History_Attachments"/>
      <table width="100%" id="historyTable" cellpadding="2" cellspacing="2" class="viewtable">
			<tr>
            	<td width="1"></td>
                <td><fmt:message key="files"/></td>
                <td><fmt:message key="remove"/></td>
            </tr>	
        <c:forEach var="fpCase" items="${command.historyAttachment}" varStatus="status">
        <c:if test="${fpCase != ''}">
        <tr>
           <td><input type="hidden" value="<c:out value="${fpCase}"/>">&nbsp;<a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','history')" onclick="testFunction()"><c:out value="${fpCase}"/></a></td>
           <td><a href="javascript:testFunction(); removeHistoryAtt('<c:out value="${fpCase}"/>');" onclick="testFunction()">Remove</a></td>
        </tr>
        </c:if>
        </c:forEach>
        </table>
    </td>
    </tr>
    <tr>
    <td colspan="2"><iframe src="http://<fmt:message key="dataServer"/><fmt:message key="project.context"/>/FileUploadHistory.htm?uploadtype=history"  scrolling="no" height="60" frameborder="0" id='target_upload' name='target_upload'></iframe></td>
    </tr>
</table>
</c:when>
<c:otherwise>
<table width="100%">
<tr>
    <td colspan="2"><fmt:message key="history"/> <fmt:message key="History_Attachments"/>
      <table width="100%" border="1" id="historyTable" class="viewtable">
        <c:forEach var="fpCase" items="${command.historyAttachment}" varStatus="status">
        <input type="hidden" value="<c:out value="${fpCase}"/>">
        <a href="javascript:testFunction(); windowOpen('<c:out value="${fpCase}"/>','history')" onclick="testFunction()"><c:out value="${fpCase}"/></a> | 
        </c:forEach>
      </table>
	</td>
</tr>
</table>
</c:otherwise>
</c:choose>
    </td>
</tr>
</table>
</div>
<!-- end of patient history section -->

<!-- Vital Signs section -->
<div class="outer_container" id="vitalSignsDetailsDiv">
<table width="100%" cellpadding="0" cellspacing="0" class="tableEMR">
    <tr>
        <th><fmt:message key="Case_VitalSigns"/></th>
        <th align="right">
         <c:choose>
             <c:when test="${command.caseClosed=='no'}">
         <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','VitalSigns','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
            </c:when>
        </c:choose>
        &nbsp;
         </th>
    </tr>
<tr>
<td colspan="2"><table width="100%" class="viewtable"><tr>
    <td align="center"><fmt:message key="Case_Weight"/><br>
<input id="weight" type="text" size="4" name="weightN" onBlur="validateCaseInputField(this);" value="<c:out value="${command.fpcase.weight}"/>"/></td>
    <td align="center"><fmt:message key="Case_Height"/><br>
    <input id="height" type="text" size="4" name="heightN" onBlur="validateCaseInputField(this);" value="<c:out value="${command.fpcase.height}"/>"/></td>
    <td align="center"><fmt:message key="Case_Temperature"/><br>
    <input id="temperature" type="text" size="4" name="temperatureN" onBlur="validateCaseInputField(this);" value="<c:out value="${command.fpcase.temperature}"/>"/></td>
    <td align="center"><fmt:message key="Case_BloodPressure"/><br>
    <input id="bloodPressure" type="text" size="7" name="bloodPressureN" onBlur="validateCaseInputField(this);" value="<c:out value="${command.fpcase.bloodPressure}"/>"/></td>
    <td align="center"><fmt:message key="Case_PulseRate"/><br>
    <input id="pulseRate" type="text" size="4" name="pulseRateN" onBlur="validateCaseInputField(this);" value="<c:out value="${command.fpcase.pulseRate}"/>"/></td>
    <td align="center"><fmt:message key="Case_RespirationRate"/><br>
    <input id="respirationRate" type="text" size="6" name="respirationRateN" onBlur="validateCaseInputField(this);" value="<c:out value="${command.fpcase.respirationRate}"/>"/></td>
  <c:choose>
    <c:when test="${(command.patient.gender == 'F')}">
    <td align="center"><fmt:message key="Case_Lmp"/><br>
    <input id="lmp" type="text" size="6" name="lmpN" onBlur="validateCaseInputField(this);" value="<c:out value="${command.fpcase.lmp}"/>"/></td>
    </c:when>
  </c:choose>
  </tr>
</table>
</td>
</tr>
</table>
</div>
<!-- end of vital signs section -->

<!-- order lab tests section -->
<div class="outer_container" id="labTestDiv">
<table width="100%" cellpadding="0" border="1" cellspacing="0" class="tableEMR">
<c:choose>
<c:when test="${command.caseClosed=='no'}">
  <tr>
    <th><fmt:message key="order_tests"/></th>
    <th align="right"><c:choose>
         <c:when test="${command.caseClosed=='no'}">
         <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','labTest','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
         </c:when>
      </c:choose></th>
  </tr>
  <tr>
    <td colspan="2"><table width="100%" border="1" id="labTestTable" class="viewtable">
      <tr>
        <td><b><fmt:message key="service_name"/></b></td>
        <td><b><fmt:message key="lab_name"/></b></td>
        <td><b><fmt:message key="report"/></b></td>
        <td><b><fmt:message key="remove"/></b></td>
      </tr>
	<c:forEach var="labtest" items="${command.labServiceList}" varStatus="status">
     
	  <tr>
        <td><input type="hidden" value="<c:out value="${labtest.labServiceId}"/>"><c:out value="${labtest.serviceName}"/></td>
        <td><c:out value="${labtest.lab.labName}"/></a></td>
       <td>
         <c:if test="${labtest.labTest.testReport != null}">
        <c:forTokens items="${labtest.labTest.testReport}" delims="#" var="reportName" varStatus="status" >
         <c:out value="${status.count}" /> : <a href="javascript:testFunction(); windowOpen('<c:out value="${reportName}"/>','labReport')" onclick="testFunction()"><c:out value="${reportName}"/></a>
         <br/>
         </c:forTokens>    
        </c:if>    
       </td>
        <td><c:if test="${labtest.labTest.testReport == null}"><a href="javascript:testFunction(); removeOrderedTest('<c:out value="${labtest.labServiceId}"/>');" onclick="testFunction();">Remove</a></c:if>
        </td>
      </tr>
      
	</c:forEach>
    </table>
    </td>
  <tr>
   <c:if test="${command.docType=='fp'}"> 
	<td><a href="javascript:testFunction(); showLabs('<c:out value="${login.loginId}"/>')" onclick="testFunction()"><fmt:message key="order_test"/></a></td>
   </c:if>
   <c:if test="${command.docType=='specalist'}">
   <td><a href="javascript:testFunction(); showLabs('<c:out value="${command.patient.creator.doctorId}"/>')" onclick="testFunction()"><fmt:message key="order_test"/></a></td>
   </c:if>
</tr>
  <tr id="showOrder" align="center">
    <td colspan="2"><b><fmt:message key="noLabList"/></b></td>
  </tr>
  <tr id="orderContent">
    <td colspan="2"><table width="100%" border="1" class="viewtable">
     <tr>
        <td width="20%" valign="top">
        <div class="scrollFeature">
          <table width="100%" border="0" class="viewtable">
      
        
        <tbody id="labbody" >
            <tr id="pattern" style="display:none;">
                <td><a id="showservice" href="javascript:testFunction(); " onClick="testFunction(); showLabDetails('<c:out value="${login.loginId}"/>',this.id); showServicesCreated(this.id)"> <span id="tableDocDetails">docdetails</span></a></td>
               
            </tr>
        </tbody>
    </table></div></td>
        <td valign="top"><table width="100%" border="1" class="viewtable">
          <tr>
            <th><strong><fmt:message key="lab_details"/></strong></th>
            <th><strong><fmt:message key="contact_details"/></strong></th>
          </tr>
          <tbody id="labbodyOne">
            <tr id="patternOne" style="display:none;">
              <td><span id="tableDocDetailsOne">docdetailsD</span></a></td>
                <td><span id="tableDocContactDetailsOne">contactdetailsD</span></td>
            </tr>
          </tbody>
        </table>
          <br>
          <table width="100%" border="1" id="serviceTable" class="viewtable">
            <tr>
              <th colspan="2"><strong><fmt:message key="labservice_Details"/></strong></th>
            </tr>
            <tbody id="labservicebody">
            <tr id="pattern1" style="display:none;">
                <td><span id="tableServiceDetails">labservicesdetails</span></td>
                <td width="10%"><input type="checkbox" id="valId" onClick="appendValue(this.id)"></td>  
            </tr>
        </tbody>
          </table></td>
      </tr>
      </table></td>
  </tr>
</c:when>
 <c:otherwise>
<tr>
<th><fmt:message key="order_tests"/></th>
   	<th align="right">
      <c:choose>
         <c:when test="${command.caseClosed=='no'}">
         <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','labTest','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
         </c:when>
     </c:choose>     
  &nbsp;
   </th>
</tr>
<tr>
<td  colspan="2">
   <table width="100%" border="1" id="labTestTable" class="viewtable">
    <tr>
        <td><b><fmt:message key="service_name"/></b></td>
        <td><b><fmt:message key="lab_name"/></b></td>
        <td><b><fmt:message key="report"/></b></td>
    </tr>
    <c:forEach var="labtest" items="${command.labServiceList}" varStatus="status">
    <tr>
        <td><input type="hidden" value="<c:out value="${labtest.labServiceId}"/>"><c:out value="${labtest.serviceName}"/></td>
        <td><c:out value="${labtest.lab.labName}"/></td>
        <td> <a href="javascript:testFunction(); windowOpen('<c:out value="${labtest.labTest.testReport}"/>','labReport')" onclick="testFunction()"><c:out value="${labtest.labTest.testReport}"/></a></td>
    </tr>
    </c:forEach>
  </table>
</td>
</tr>
</c:otherwise>
</c:choose>
</table>
</div>
<!-- end of order lab tests section -->

<!-- Case Diagnosis section -->
<div class="outer_container" id="caseDiagnosisDiv">
<table width="100%" cellpadding="0" cellspacing="0" class="tableEMR">
    <tr>
        <th><fmt:message key="Case_DiagnosisDetails"/></th>
   		<th align="right">
        <c:choose>
         <c:when test="${command.caseClosed=='no'}">
           <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','caseDiagnosis','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
         </c:when>
        </c:choose>   
        &nbsp;
        </th>
    </tr>
    <tr>
        <td><fmt:message key="Case_Diagnosis"/><br>
<textarea rows="3" id="diagnosis" name="diagnosisN"  cols="40" ><c:out value="${command.fpcase.diagnosis}"/></textarea></td>
        <td><fmt:message key="Case_Prescription"/><br>
<textarea rows="3" id="prescription" name="prescriptionN"  cols="40" ><c:out value="${command.fpcase.prescription}"/></textarea></td>
    </tr>
    <tr>
        <td colspan="2"><fmt:message key="Case_Impression"/><br>
<textarea rows="3" id="impression" name="impressionN"  cols="40" ><c:out value="${command.fpcase.impression}"/></textarea></td>
    </tr>
</table>
</div>
<!-- end of case diagnosis section -->

<!-- Second Opinion section -->
<div class="outer_container" id="secondOpinionDiv">
<table width="100%" cellpadding="0" cellspacing="0" class="tableEMR">
<tr>
    <th><fmt:message key="second_opinion_details"/></th>
   	<th align="right">
     <c:choose>
       <c:when test="${command.caseClosed=='no'}">
         <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','secondOpinion','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
       </c:when>
     </c:choose>
     &nbsp;
    </th>
</tr>

<c:choose>
<c:when test="${command.caseClosed=='no'}">
<tr>
<td colspan="2">
  <c:choose>
  <c:when test="${command.docType=='specalist'}">
       <table id="specalityTable" border="1" width="100%" cellpadding="2" cellspacing="2" class="viewtable">   
            <tr>
                <td><b><fmt:message key="opinion"/></b></td>
            </tr>
               
           <c:choose>
            <c:when test="${command.caseClosed=='no'}">
            <c:forEach var="docSep" items="${command.specialityList}" varStatus="status"> 
            <tr>
              <td><input type="hidden" id="caseReferralId" name="caseReferralIdN" value="<c:out value="${docSep.referralId}"/>"><textarea rows="5" id="opinions" name="opinionsN"  cols="70" ><c:if test="${docSep.opinions != 'No Opinion'}"><c:out value="${docSep.opinions}"/></c:if></textarea></td>
            </tr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <c:forEach var="docSep" items="${command.specialityList}" varStatus="status"> 
            <tr>
              <td><input type="hidden" id="caseReferralId"  name="caseReferralIdN" value="<c:out value="${docSep.referralId}"/>"><textarea rows="5" id="opinions" name="opinionsN"  cols="70" disabled="disabled" ><c:if test="${docSep.opinions != 'No Opinion'}"><c:out value="${docSep.opinions}"/></c:if></textarea></td>
            </tr>
            </c:forEach>
          </c:otherwise>
          </c:choose>
          
       </table>
  </c:when>
  <c:otherwise>
        <table id="specalityTable" border="1" width="100%" cellpadding="2" cellspacing="2" class="viewtable">   
            <tr>
                <td><b><fmt:message key="specalist_details"/></b></td>
                <td><b><fmt:message key="contact_details"/></b></td>
                <td><b><fmt:message key="opinion"/></b></td>
                <td><b><fmt:message key="remove"/></b></td>
            </tr>
            <c:forEach var="docSep" items="${command.specialityList}" varStatus="status"> 
            <tr>
                <td><input type="hidden" value="<c:out value="${docSep.specalistId.doctorId}"/>">
                   <fmt:message key="name"/>: <c:out value="${docSep.specalistId.firstName}"/> <c:out value="${docSep.specalistId.lastName}"/><br>
                   <fmt:message key="Doctor_Designation"/>: <c:out value="${docSep.specalistId.designation}"/><br>
                   <fmt:message key="Doctor_Specialty"/>: <c:out value="${docSep.specalistId.specialty}"/></td>
                <td><fmt:message key="Patient_Address"/>: <c:out value="${docSep.specalistId.contactDetails.address}"/><br>
                    <fmt:message key="Doctor_ContactNo"/>: <c:out value="${docSep.specalistId.contactDetails.contactNumber}"/><br>
                    <fmt:message key="Doctor_Email"/>: <c:out value="${docSep.specalistId.contactDetails.email}"/></td>
                <td><input type="hidden" id="caseReferralId" name="caseReferralIdN" value="<c:out value="${docSep.referralId}"/>"><c:out value="${docSep.opinions}"/></td>
                <td><c:if test="${docSep.opinions == 'No Opinion'}">&nbsp;&nbsp;<a href="javascript:testFunction(); removeSpecalist('<c:out value="${docSep.referralId}"/>','<c:out value="${docSep.specalistId.doctorId}"/>');" onclick="testFunction();">Remove</a></c:if></td>
            </tr>
            </c:forEach>
    </table>
    </c:otherwise>
    </c:choose>
  </td>
</tr>
<c:choose>
  <c:when test="${command.docType=='fp'}">
<tr>
    <td><a href="javascript:testFunction(); showSecondOpinion('<c:out value="${login.loginId}"/>')" onclick="testFunction()"><strong><fmt:message key="second_opinion"/></strong></a></td>
</tr>
<tr id="showSpcealist">
<td align="center"><b><fmt:message key="noSpecialist"/></b></td>
</tr>
<tr>
    <td>
        <table width="100%" border="1" class="viewtable">
            <tr>
                <td><fmt:message key="specalist_details"/></td>
                <td><fmt:message key="contact_details"/></td>
                <td>&nbsp;</td>
            </tr>
            <tbody id="specalitybody">
            <tr id="pattern2" style="display:none;">
                <td><span id="tableDocDetails">docdetails</span><br/></td>
                <td><span id="tableDocContactDetails">contactdetails</span><br/></td>
                <td><input type="checkbox" id="docId" onClick="appendDocValue(this.id)"></td>  
            </tr>
            </tbody>
        </table>
    </td>
</tr>
</c:when>
</c:choose>
</c:when>
<c:otherwise>  
<!-- when case is closed --> 
<tr>
<td>
<c:choose>
  <c:when test="${command.docType=='specalist'}">
    <table width="100%" border="1" id="specalityTable" class="viewtable">   
        <tr>
            <td><b><fmt:message key="opinion"/></b></td>
        </tr>
        <c:forEach var="docSep" items="${command.specialityList}" varStatus="status"> 
        <tr>
            <td><input type="hidden" id="caseReferralId" name="caseReferralIdN" value="<c:out value="${docSep.referralId}"/>"><textarea rows="5" id="opinions" name="opinionsN"  cols="70"  disabled="disabled"><c:out value="${docSep.opinions}"/></textarea></td>
        </tr>
        </c:forEach>
    </table>
</c:when>
<c:otherwise>
 <table width="100%" border="1" id="specalityTable" class="viewtable">   
        <tr>
            <td><b><fmt:message key="specalist_details"/></b></td>
            <td><b><fmt:message key="contact_details"/></b></td>
            <td><b><fmt:message key="opinion"/></b></td>
        </tr>
        <c:forEach var="docSep" items="${command.specialityList}" varStatus="status"> 
        <tr>
            <td><input type="hidden" value="<c:out value="${docSep.specalistId.doctorId}"/>">
                <fmt:message key="name"/>: <c:out value="${docSep.specalistId.firstName}"/> <c:out value="${docSep.specalistId.lastName}"/><br>
                <fmt:message key="Doctor_Designation"/>: <c:out value="${docSep.specalistId.designation}"/><br>
                <fmt:message key="Doctor_Specialty"/>: <c:out value="${docSep.specalistId.specialty}"/></td>
            <td><fmt:message key="Doctor_Address"/>: <c:out value="${docSep.specalistId.contactDetails.address}"/><br>
                <fmt:message key="Doctor_ContactNo"/>: <c:out value="${docSep.specalistId.contactDetails.contactNumber}"/><br>
                <fmt:message key="Doctor_Email"/>: <c:out value="${docSep.specalistId.contactDetails.email}"/></td>
            <td><input type="hidden" id="caseReferralId" name="caseReferralIdN" value="<c:out value="${docSep.referralId}"/>"><c:out value="${docSep.opinions}"/></td>
        </tr>
        </c:forEach>
    </table>
</c:otherwise>
</c:choose>
</td>
</tr>
</c:otherwise>
</c:choose>
</table>
</div>
<br>
<!-- save and case close section -->
<div id="saveSection">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td align="right">
        <c:choose>
            <c:when test="${command.caseClosed=='no'}">
            <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updateCase('<c:out value="${command.patient.patientId}"/>','<c:out value="${login.loginId}"/>','<c:out value="${command.historytb}"/>','<fmt:message key="data.NewRecordU"/>','<fmt:message key="data.NewRecordNotU"/>','<fmt:message key="data.NotAdded"/>','allSave','<c:out value="${command.docType}"/>','<c:out value="${command.docType}"/>','<c:out value="${command.patient.creator.doctorId}"/>')"/>
            <input type="button" value='<fmt:message key="Button_CloseCase"/>' onClick="testFunction(); closeCase('<c:out value="${command.fpcase.caseId}"/>','<fmt:message key="data.CaseClosed"/>','<fmt:message key="data.CaseNotClosed"/>')"/>
            </c:when>
        </c:choose>
          

        </td>
  </tr>
</table>
<br>
<br>
</div>
<!--end of save & case close section -->

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
<br>
</td>
</tr>
</table>

<!-- end of Second Opinion section -->


</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>

</body>
</html>