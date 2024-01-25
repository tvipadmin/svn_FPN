<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/labChangeManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/DoctorManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>

<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444; background: #9ACB34;}
</style>

<title><fmt:message key="project.title"/></title>
</head>

<body onLoad="init('<c:out value="${login.loginId}"/>');show('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>
<div id="divHeaderEMR"><%@ include file="/WEB-INF/jsp/common/ChangePasswordHeader.jsp" %></div>
<div id="divPagebody">
<form name="labAdminMain">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<!--<div id="AddTable">-->
<!--<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">-->
<tbody id="labbody">
    <tr id="pattern" style="display:none;"></tr>
 </tbody>
    <tr>
        <td class="h4"><fmt:message key="lab.newEdit"/></td>
        <td align="right" valign="middle"></td>	
    </tr>
</table>
</div>

<div id="divPagebody">
<table width="99%" border="0" align="center" class="plain" >
  <tr>
    <td><fmt:message key="Lab_LabId"/><span class="compulsoryStar">*</span></td>
    <td><input id="labId" type="text" size="20" name="labIdN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
  <tr>
    <td><fmt:message key="Doctor_Password"/><span class="compulsoryStar">*</span></td>
    <td><input id="login.password" type="password" size="20" name="passwordN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
  <tr>
    <td><fmt:message key="Doctor_ConfirmPassword"/><span class="compulsoryStar">*</span></td>
    <td><input id="login.confirmPassword" type="password" size="20" name="confirmPasswordN" onBlur="validateLabIdInputFieldConfirmPass(this);"/></td>
  </tr>
  <tr>
    <td><fmt:message key="Lab_LabName"/><span class="compulsoryStar">*</span></td>
    <td><input id="labName" type="text" size="20" name="labNameN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
   
  <tr>
    <td><fmt:message key="Lab_Address"/></td>
    <td><textarea rows="2" id="contactDetails.address" name="addressN"  cols="20" ></textarea></td>
  </tr>
   <tr>
    <td><fmt:message key="Lab_ContactNo"/></td>
    <td><input type="text" id="contactDetails.contactNumber" size="40" name="contactNumberN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
  <tr>
    <td><fmt:message key="Lab_Emial"/></td>
    <td><input type="text" id="contactDetails.email" size="40" name="emailN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
 <tr>
    <td><fmt:message key="Lab_ContactPerson"/></td>
    <td><input type="text" id="contactPerson" size="40" name="contactPersonN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
  
   <tr>
    <td><fmt:message key="Lab_Logo"/></td>
    <td> <input type="text" id="photo" name="chkPhotoName" disabled="disabled">
        <%-- file upload progress bar --%>
            <div id="fileupload_progress"></div>
            <div id="progressBar" style="display: none;">
              <div id="progressBarBoxContent"></div>
            </div><iframe scrolling="no" frameborder="0" id='target_upload' name='target_upload' style="display:none"></iframe><span id="photo1"></span></td>
  </tr>
</form>
<tr>
<td></td><td><form name="PhotoUpload" method="post" action="FileUpload.htm" enctype="multipart/form-data"  target="target_upload">
    <p><fmt:message key="file.upload"/>
        <input type="file" name="file1" id="photoUpload" tabindex="15" onChange="callSubmit()" />
        <input type="button" name="cancelUpload" value="Remove" onClick="cancelPhotoo('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')"></p>
    </form>
</td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td align="right">
     <!--   <small>(ID=<span id="labId">newlab</span>)</small>-->
      <input type="button" value='<fmt:message key="Button_Save"/>' onClick="updateLab('<c:out value="${login.loginId}"/>','<fmt:message key="data.Updated"/>','<fmt:message key="data.NotUpdated"/>','<fmt:message key="data.NewRecord"/>','<fmt:message key="data.NewRecordNot"/>','<fmt:message key="data.NotAdded"/>')"/>
      <input type="button" value='<fmt:message key="Button_Cancel"/>' tabindex="18"  onClick="cancel()"/></td></tr>
   </td>
  </tr>
</table>
<br>
</div>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>