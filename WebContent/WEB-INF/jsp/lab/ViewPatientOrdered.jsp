<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabServicesManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabServicesValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/viewCommonPatientOrdered.js"></script>
<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444; background: #9ACB34;}
</style>
<title><fmt:message key="project.title"/></title>
</head>
<body onload="init('<fmt:message key="sureremove_report"/>','<fmt:message key="remove"/>','<fmt:message key="pat_report"/>','<fmt:message key="youHave"/>','<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<c:out value="${login.loginId}"/>'); show('ListTable'); hide('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/LabMainMenu.jsp"%></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="lab.patientManagement"/></td>
        <td align="right" valign="middle"><a id="listPL" href="javascript:loadLabPatient('<c:out value="${login.loginId}"/>'); show('ListTable'); hide('AddTable')" onclick="testFunction()"><fmt:message key="list.patients"/></a></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="labPatientList">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="newRefLab" align="center" style="font-stretch: expanded; font-weight: bold;">
</div>
<div id="ListTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="lab.patientManagement"/></td>
        <td align="right" valign="middle"><fmt:message key="enter_patient"/> : <input type="text" id="patientName" name="patientNameS" size="10"  onkeyup="javascript:searchOnKeyPatientName('<c:out value="${login.loginId}"/>')">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:searchOnKeyPatientName('<c:out value="${login.loginId}"/>')"></td>
</tr>
</table>
<input type="hidden" id="caseIdVal">
<input type="hidden" id="patientIdVal">
<input type="hidden" id="labServiceIdVal">
<input type="hidden" id="labTestVal">
<input type="hidden" id="labTestElement">
<table width="100%" border="0" align="center" class="viewtable">
  <thead>
    <tr>
      <th><fmt:message key="Patient_PatientId"/></th>
      <th><fmt:message key="patient_name"/></th>
      <th><fmt:message key="Patient_Age"/></th>
      <th><fmt:message key="Patient_Gender"/></th>
      <th><fmt:message key="doctor_name"/></th>
      <th><fmt:message key="list.showServices"/></th>
     </tr>
  </thead>
  <tbody id="patientbody">
    <tr id="pattern" style="display:none;">
      <td><span id="tablepatientId">id</span>&nbsp;</td>
      <td><span id="tablepatientName">patientName</span>&nbsp;</td>
      <td><span id="tableage">age</span>&nbsp;</td>
      <td><span id="tablegender">gender</span>&nbsp;</td>
      <td><span id="tableDoctor"></span>&nbsp;</td>
      <td colspan="2" align="center"><input id="caseId" type="button" value="<fmt:message key="show_orders"/>" onClick="testFunction(); clickSeeServices(this.id); showServices(this.id,'<fmt:message key="noReport"/>','<fmt:message key="select.Service"/>','<c:out value="${login.loginId}"/>'); hide('ListTable'); show('AddTable')"/></td>
  </tr>
  </tbody>
</table>
<br>
<br>
</div>

<div id="AddTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="upload.Report"/></td>
        <td align="right" valign="middle"><strong><fmt:message key="patient_name"/> : <span id="eachPatientName"> </span></strong></td>
</tr>
</table>

<table width="100%" border="0" align="center" class="viewtable">
  <thead>
    <tr>
        <th><fmt:message key="labservice_ServiceId"/></th>
        <th><fmt:message key="Lab_ServiceName"/></th>
        <th align="center"><fmt:message key="report"/></th>
       <!--  <th><fmt:message key="remove"/></th> -->
    </tr>
  </thead>
  <tbody id="servicebody">
    <tr id="pattern1" style="display:none;">
        <td><span id="tableServiceId">labServiceId</span></td> 
        <td><span id="tableServices">labservicesdetails</span></td>
        <td align="center"><span id="tableReport"></span></td>
       <!--  <td><a id="id" href="javascript:testFunction()" onclick="testFunction(); removeLabReport(this.id,'<c:out value="${login.loginId}"/>','<fmt:message key="noReport"/>','<fmt:message key="select.Service"/>')">Remove</a></td> -->
  </tr>
  </tbody>
</table>
<br>
<table width="100%" border="0" align="center">
    <tr>
        <td align="center"> <fmt:message key="select_services"/> : <select name="serviceN" id="service" onChange="changeType()"></select></td>
    </tr>
    <tr>
       <td align="center"><fmt:message key="upload_report"/>&nbsp;<input type="text" id="photo" name="chkPhotoName" disabled="disabled" size="33">
        <%-- file upload progress bar --%>
            <div id="fileupload_progress"></div>
            <div id="progressBar" style="display: none;">
              <div id="progressBarBoxContent"></div>
            </div><iframe scrolling="no" frameborder="0" id='target_upload' name='target_upload' style="display:none"></iframe><span id="photo1"></span></td>
   </tr>
</table>
</form>
<form name="PhotoUpload" method="post" action="FileUpload.htm?uploadtype=labreport" enctype="multipart/form-data"  target="target_upload">
    <p align="center"><fmt:message key="file.upload"/>
        <input type="file" name="file1" id="photoUpload" onChange="callSubmitP()" />
        <input type="button" name="cancelUpload" value="Remove" onClick="cancelPhotoo('<fmt:message key="data.path"/>','<fmt:message key="labReport.path"/>')"></p>
<input type="hidden" id="caseIdVal" name="caseIdVal">
<input type="hidden" id="patientIdVal" name="patientIdVal">

</form>
    
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td align="right"><input type="button" value='<fmt:message key="Button_Save"/>'  onClick="updateLabServiceTest('<c:out value="${login.loginId}"/>','<fmt:message key="report.Added"/>','<fmt:message key="report.NotAdded"/>','<fmt:message key="select.Service"/>','<fmt:message key="noReport"/>')"/>  
   </td>
  </tr>
</table>
<br>
</div>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>