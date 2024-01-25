<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/DoctorManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/doctorChangeManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/DoctorManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>


<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444; background: #9ACB34;}
</style>

<title><fmt:message key="project.title"/></title>
</head>

<body onLoad="init('<c:out value="${login.loginId}"/>'); show('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>
<div id="divHeaderEMR"><%@ include file="/WEB-INF/jsp/common/ChangePasswordHeader.jsp" %></div>

<c:set var="subSelected" value="smChangeProfile" scope="session"/>

<div id="divPagebody">
<form name="doctorAdminMain">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody id="doctorbody">
 	 	<tr id="pattern" style="display:none;"></tr>
    </tbody>
    <tr>
        <td class="h3"><fmt:message key="doctor.Edit"/></td>
        <td align="right" valign="middle">&nbsp;</td>
    </tr>
</table>
</div>

<div id="divPagebody">
    <table class="plain" width="100%">
      <tr>
        <td><fmt:message key="Doctor_DoctorId"/><span class="compulsoryStar">*</span></td>
        <td><input id="doctorId" type="text" size="20" name="doctorIdN" tabindex="1" onBlur="validateDoctorIdInputField(this);"/></td>
        
		<td><fmt:message key="Doctor_Email"/></td>
        <td><input type="text" id="contactDetails.email" size="40" name="emailN" tabindex="8" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>

      <tr>
        <td><fmt:message key="Doctor_Password"/><span class="compulsoryStar">*</span></td>
        <td><input id="login.password" type="password" size="20" name="passwordN" tabindex="2" onBlur="validateDoctorIdInputField(this);"/></td>
        <td><fmt:message key="Doctor_Education"/></td>
        <td><input type="text" id="education" size="40" name="educationN" tabindex="9" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>
      <tr>
        <td><fmt:message key="Doctor_ConfirmPassword"/><span class="compulsoryStar">*</span></td>
        <td><input id="login.confirmPassword" type="password" size="20" tabindex="3" name="confirmPasswordN" onBlur="validateDoctorIdInputFieldConfirmPass(this);"/></td>
        <td><fmt:message key="Doctor_Designation"/></td>
        <td><input type="text" id="designation" size="40" name="designationN" tabindex="10" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>
      <tr>
        <td><fmt:message key="Doctor_FirstName"/><span class="compulsoryStar">*</span></td>
        <td><input id="firstName" type="text" size="20" name="firstNameN" tabindex="4" onBlur="validateDoctorIdInputField(this);"/></td>
        <td><fmt:message key="Doctor_Identification"/></td>
        <td><input type="text" id="identification" size="40" name="identificationN" tabindex="11" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>
      <tr>
        <td><fmt:message key="Doctor_LastName"/></td>
        <td><input id="lastName" type="text" size="20" name="lastNameN" tabindex="5" onBlur="validateDoctorIdInputField(this);"/></td>
        <td><fmt:message key="Doctor_ClinicName"/></td>
        <td><input type="text" id="clinicName" size="40" name="clinicNameN" tabindex="12" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>
      <tr>
        <td><fmt:message key="Doctor_Address"/></td>
        <td><textarea rows="2" id="contactDetails.address" name="addressN" tabindex="6" cols="20" ></textarea></td>
        <td><fmt:message key="Doctor_Specialty"/></td>
        <td><input type="text" id="specialty" size="40" name="specialtyN" tabindex="13" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>
       <tr>
        <td><fmt:message key="Doctor_ContactNo"/></td>
        <td><input type="text" id="contactDetails.contactNumber" size="40" name="contactNumberN" tabindex="7" onBlur="validateDoctorIdInputField(this);"/></td>
        <td><fmt:message key="Doctor_Type"/>
          <span class="compulsoryStar">*</span></td>
        <td>
          <select id="type" onBlur="validateDoctorIdInputField(this);" tabindex="14" onchange="validateDoctorIdInputField(this);" name="doctype">
            <option value=''>
              <fmt:message key="doctor.Select.Type"/>
            </option>
            <option value='SPECALIST'>SPECALIST</option>
            <option value='FAMILYPHYSICIAN'>FAMILYPHYSICIAN</option>
            <option value='BOTH'>BOTH</option>
          </select></td>
      </tr>
     <tr>
        <td><fmt:message key="Doctor_Photo"/></td>
        <td colspan="3"><input type="text" id="photo" name="chkPhotoName" disabled="disabled">
        <%-- file upload progress bar --%>
            <div id="fileupload_progress"></div>
            <div id="progressBar" style="display: none;">
              <div id="progressBarBoxContent"></div>
            </div><iframe scrolling="no" frameborder="0" id='target_upload' name='target_upload' style="display:none"></iframe><span id="photo1"></span></td>

      </tr>
    </table>
    </form>
    <form name="PhotoUpload" method="post" action="FileUpload.htm" enctype="multipart/form-data"  target="target_upload">
    <p><fmt:message key="file.upload"/>
        <input type="file" name="file1" id="photoUpload" tabindex="15" onChange="callSubmit()" />
        <input type="button" name="cancelUpload" value="Remove" onClick="cancelPhotoo('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')"></p>
    </form>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
      <tr><td align="right" valign="middle"><!-- <small>(ID=<span id="doctorId">newdoctor</span>)</small> -->
      <input type="button" value='<fmt:message key="Button_Save"/>' tabindex="16" onClick="updateDoctor('<c:out value = "${login.loginId}"/>','<fmt:message key="data.Updated"/>','<fmt:message key="data.NotUpdated"/>','<fmt:message key="data.NewRecord"/>','<fmt:message key="data.NewRecordNot"/>','<fmt:message key="data.NotAdded"/>')"/>
      <input type="button" value='<fmt:message key="Button_Cancel"/>' tabindex="18"  onClick="cancel()"/></td></tr>
    </table>
  </div>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>
