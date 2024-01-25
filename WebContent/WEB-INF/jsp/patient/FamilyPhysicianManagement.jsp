<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/FamilyPhysicianManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/FamilyPhyManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/DoctorManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/FamilyPhysicianCommon.js"></script>

<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444;
                                       background: #9ACB34;}
</style>
<title><fmt:message key="project.title"/></title>
</head>
<body onload="init('<fmt:message key="labReport"/>','<fmt:message key="youHave"/>','<fmt:message key="andC"/>','<fmt:message key="opinionsp"/>','<fmt:message key="viewEmrMsg"/>','<fmt:message key="network.doctors"/>','<fmt:message key="newReq"/>','<c:out value="${login.loginId}"/>','no'); show('ListTable'); hide('CreateLink'); hide('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/FPMainMenu.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="patient.management"/></td>
        <td align="right" valign="middle"><a id="listPat" href="javascript:loadFamilyPhysician('<c:out value="${login.loginId}"/>','no');show('ListTable'); hide('AddTable'); removeErrorMsg()" onclick="testFunction()"><fmt:message key="list.patients"/></a> | <a id="listAP" href="javascript:hide('CreateLink'); hide('ListTable'); show('AddTable'); clearPatient('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>');" onclick="testFunction()"><fmt:message key="new_editP"/></a></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="familyPhyMain" >
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="newOpiLab" align="center" style="font-stretch: expanded; font-weight: bold;">
</div>
<div id="ListTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="all_patient"/></td>
        <td align="right" valign="middle"><fmt:message key="enter_patient"/>: <input type="text" id="patname" name="patientNameS" size="10"  onkeyup="javascript:testFunction(); searchOnKeyPatientNo('<c:out value="${login.loginId}"/>','yes')"> <fmt:message key="enter_patno"/>: <input type="text" id="patno" name="patientNoS" size="10"  onkeyup="javascript:testFunction(); searchOnKeyPatientNo('<c:out value="${login.loginId}"/>','yes')">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:testFunction(); searchOnKeyPatientNo('<c:out value="${login.loginId}"/>')"></td>
    </tr>
</table>

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="Patient_PatientId"/></th>
        <th><fmt:message key="patient_name"/></th>
      	<th><fmt:message key="age_gender"/></th>
      	<th><fmt:message key="contact_details"/></th>
        <th><fmt:message key="view_edit_delete"/></th>
      </tr>
    </thead>
  <tbody id="patientbody">
    <tr id="pattern" style="display:none;">
      <td id="tablePatId">id</td>
      <td id="tablefamphydetails">famphydetails</td>
      <td id="tableagegenderdetails" align="center">agegender</td>
      <td id="tableDocContactDetails">contactdetails</td>
      <td width="25%" align="center"><input id="newCase" type="button" class="viewemr" title="View EMR" value="" onClick="testFunction(); emrViewClicked(this.id,'<c:out value="${login.loginId}"/>');"/>&nbsp;<input id="view" type="button" class="newcasegrey" value="" title="New Case" disabled="disabled" onClick="testFunction(); emrClicked(this.id,'<c:out value="${login.loginId}"/>');"/>
      <input id="edit" type="button" class="editicon" title="Edit Patient" value=""  onClick="testFunction(); editClicked(this.id); hide('ListTable'); show('AddTable');"/>&nbsp;<img src="images/delete-16x16.png" width="16" height="16" title="Delete Patient"  alt=<fmt:message key="image.delete"/> id="delete" class="deleteicon" onClick="testFunction(); deleteClicked(this.id,'<c:out value="${login.loginId}"/>','<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>')"></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>
<div id="CreateLink">
<br><br><br><br>
<table width="99%" border="0" align="center" class="plain" >
    <tr>
        <td align="center"><b><font size="3"><a id="listAP" href="javascript:hide('ListTable'); show('AddTable'); hide('CreateLink'); clearPatient('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>');" onclick="testFunction()"><fmt:message key="RegPat"/></a></font></b></td>
    </tr>
</table>
</div>
<div id="AddTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="new_edit"/></td>
        <td align="right" valign="middle"></td>
    </tr>
</table>
<table width="99%" border="0" align="center" class="plain" >
  <tr>
    <td><fmt:message key="Patient_PatientId"/></td>
    <td><input id="patientId" type="hidden" size="20" name="patientIdN" onBlur="validatePatientIdInputField(this);"/>Auto Generated </td>
    <td><fmt:message key="Patient_FirstName"/><b class="compulsoryStar">*</b></td>
    <td><input id="firstName" type="text" size="20" name="firstNameN" onBlur="validatePatientIdInputField(this);"/></td>
  </tr>
  <tr>
    <td><fmt:message key="Patient_LastName"/></td>
    <td><input id="lastName" type="text" size="20" name="lastNameN" onBlur="validatePatientIdInputField(this);"/></td> 
    <td><fmt:message key="Patient_Gender"/><b class="compulsoryStar">*</b></td>
    <td><select name="genderN" id="gender" onChange="removeError()" onBlur="validatePatientIdInputField(this);">
	          <option value='' <c:if test="${gender == ''}">selected</c:if>>--<fmt:message key="select"/>--</option>
	          <option value='M'<c:if test="${gender == 'M'}">selected</c:if>><fmt:message key="Male"/></option>
	          <option value='F'<c:if test="${gender == 'F'}">selected</c:if>><fmt:message key="Female"/></option>
	          <option value='O'<c:if test="${gender == 'O'}">selected</c:if>><fmt:message key="Other"/></option>
	    </select>
    </td> 
  </tr>
  <tr>
    <td><fmt:message key="Patient_Age"/><b class="compulsoryStar">*</b></td>
    <td><input id="ageYears" type="text" size="5" name="ageYearsN" onBlur="validatePatientIdInputField(this);"/>&nbsp;<fmt:message key="Patient_Months"/><input id="ageMonths" type="text" size="5" name="ageMonthsN" onBlur="validatePatientIdYearsInputField(this,'<fmt:message key="patient.Age.Required"/>');"/></td> 
    <td><fmt:message key="Patient_MaritalStatus"/></td>
    <td>
        <select  id="maritalStatus" name="maritalStatusN">
	          <option value='' <c:if test="${maritalStatus == ''}">selected</c:if>>--<fmt:message key="select"/>--</option>
	          <option value='Single' <c:if test="${maritalStatus == 'Single'}">selected</c:if>><fmt:message key="Single"/></option>
	          <option value='Married' <c:if test="${maritalStatus == 'Married'}">selected</c:if>><fmt:message key="Married"/></option>
	          <option value='Widowed' <c:if test="${maritalStatus == 'Widowed'}">selected</c:if>><fmt:message key="Widowed"/></option>
	          <option value='Divorcee' <c:if test="${maritalStatus == 'Divorcee'}">selected</c:if>><fmt:message key="Divorcee"/></option>
	          <option value='Seperated' <c:if test="${maritalStatus == 'Seperated'}">selected</c:if>><fmt:message key="Seperated"/></option>
          </select>
     </td>
  </tr>
  <tr>
    <td><fmt:message key="Patient_BloodGroup"/></td>
    <td>
     <select  id="bloodGroup" name="bloodGroupN">
	            <option value='' <c:if test="${status.value == ''}">selected</c:if>
	>--<fmt:message key="select"/>--</option>
	            <option value='A +' <c:if test="${dob == 'A +'}">selected</c:if>>A +</option>
	            <option value='A -' <c:if test="${dob == 'A -'}">selected</c:if>>A -</option>
	            <option value='B +' <c:if test="${dob == 'B +'}">selected</c:if>>B +</option>
	            <option value='B -' <c:if test="${dob == 'B -'}">selected</c:if>>B -</option>
	            <option value='AB +' <c:if test="${dob == 'AB +'}">selected</c:if>>AB +</option>
	            <option value='AB -' <c:if test="${dob == 'AB -'}">selected</c:if>>AB -</option>
	            <option value='O +' <c:if test="${dob == 'O +'}">selected</c:if>>O +</option>
	            <option value='O -' <c:if test="${dob == 'O -'}">selected</c:if>>O -</option>
	        </select> 
    </td> 
    <td><fmt:message key="Patient_Occupation"/></td>
    <td><input id="occupation" type="text" size="20" name="occupationN" onBlur="validatePatientIdInputField(this);"/></td> 
  </tr>
  <tr>
    <td><fmt:message key="Patient_Address"/></td>
    <td> <textarea rows="2" id="contactDetails.address" name="addressN"  cols="20" ></textarea></td> 
    <td><fmt:message key="Patient_ContactNo"/></td>
    <td><input type="text" id="contactDetails.contactNumber" size="40" name="contactNumberN" onBlur="validatePatientIdInputField(this);"/></td>
  </tr>
  <tr>
    <td><fmt:message key="Patient_Photo"/></td>
    <td><input type="text" id="photo" name="chkPhotoName" disabled="disabled"><div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
  <div id="progressBarBoxContent"></div>
</div><iframe  scrolling="no" frameborder="0" id='target_upload' name='target_upload' style="display:none"></iframe>
    </td>
    <td><fmt:message key="Patient_Email"/></td>
    <td><input type="text" id="contactDetails.email" size="40" name="emailN" onBlur="validatePatientIdInputField(this);"/></td>
    </tr>
  </table>
</form>
<table width="99%" border="0" align="center">
  <tr>
    <td><form name="PhotoUpload" method="post" action="FileUpload.htm" enctype="multipart/form-data"  target="target_upload">
    <fmt:message key="select_file"/> : <input type="file" name="file1" id="photoUpload" onChange="callSubmit()" />&nbsp;<input type="button" name="cancelUpload" value="Remove" onClick="cancelPhotoo('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')">
    </form></td>
   </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td align="right">
      <!--<small>(ID=<span id="patientId">newfamilyPhy</span>)</small>-->
     <!--<input type="button" value='<fmt:message key="Button_Save"/>' onclick="updatePatient('<c:out value="${login.loginId}"/>','no')"/>-->
      <input type="button" value='<fmt:message key="Button_Save"/>' onClick="testFunction(); updatePatient('<c:out value="${login.loginId}"/>','yes','<fmt:message key="patient.Age.Required"/>','<fmt:message key="data.Updated"/>','<fmt:message key="data.NotUpdated"/>','<fmt:message key="data.NewRecord"/>','<fmt:message key="data.NewRecordNot"/>','<fmt:message key="data.NotAdded"/>')"/>
      <input type="button" value='<fmt:message key="Button_Clear"/>' onClick="testFunction(); clearPatient('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')"/>
        </td>
    </tr>
</table><br>
    
</div>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>
