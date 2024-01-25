<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/DoctorRegManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/RegDocValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/DoctorNetwork.js"></script>

<title><fmt:message key="project.title"/></title>
</head>
<body onLoad="init('<fmt:message key="TYPE_REQUIRED"/>');hide('successMessage'); hide('errorMessage');hide('succMsg');hide('checkNotSpam');">
<table width="100%" border="0" align="center">
            <tr>
                <td width="162" align="center">
                	<img class="logo" src="images/logo.jpg" width="250" height="67" alt="Vital Health Portal"/></td>
                <th align="right" valign="bottom">&nbsp;</th>
            </tr>
        </table>
<div id="divPagebody">
<form name="doctorNetwork">
<br>

<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="AddTable">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
        <tr>
            <td class="h4"><input type="radio" name="join" id="doctorJoin" value="doctor" onclick="javascript:joinDoctor();" checked="checked"><fmt:message key="doctor.register"/>&nbsp;&nbsp;<input type="radio" name="join" id="labJoin" value="lab" onclick="javascript:joinLab();"><fmt:message key="homeregisterlab"/></td>
            <td align="right" valign="middle"><a href="<c:url value="Logout.htm"/>"><fmt:message key="homepage"/></a></td>
        </tr>
    </table>   
<br>
    <table class="plain" width="100%">
       <tr>
            <fmt:message key="docSelect.network"/><br><br>
             <input type="radio" id="sep" name="typeDoc" value="SPECALIST" onclick="javascript:selectType();">  <fmt:message key="doc.sep"/><br>
             <input type="radio" id="fp" name="typeDoc" value="FAMILYPHYSICIAN" onclick="javascript:selectType();"> <fmt:message key="doc.fp"/> <br>
             <input type="radio" id="both" name="typeDoc" value="BOTH" onclick="javascript:selectType();"> <fmt:message key="doc.both"/> <br>
             <input type="hidden" id="type" name="docType">
       </tr>
      <tr>
        <td><fmt:message key="Doctor_DoctorId"/><span class="compulsoryStar">*</span></td>
        <td><input id="doctorId" type="text" size="20" name="doctorIdN" tabindex="1" onBlur="validateDoctorIdInputField(this);"/></td>
        <td><fmt:message key="Doctor_Email"/><span class="compulsoryStar">*</span></td>
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
        <td><textarea rows="2" id="contactDetails.address" name="addressN" tabindex="6" cols="25" ></textarea></td>
        <td><fmt:message key="Doctor_Specialty"/></td>
        <td><input type="text" id="specialty" size="40" name="specialtyN" tabindex="13" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>
       <tr>
        <td><fmt:message key="Doctor_ContactNo"/><span class="compulsoryStar">*</span></td>
        <td><input type="text" id="contactDetails.contactNumber" size="33" name="contactNumberN" tabindex="7" onBlur="validateDoctorIdInputField(this);"/></td>
       
      </tr>
     
    </table>
    </form>
   
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
      <tr><td align="right" valign="middle"><!-- <small>(ID=<span id="doctorId">newdoctor</span>)</small> -->
      <input type="button" value='<fmt:message key="Button_Continue"/>' tabindex="16" onClick="addRegDoctor()"/>
      <input type="button" value='<fmt:message key="Button_Clear"/>' tabindex="17"  onClick="clearDoctor('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')"/>
     
    </table>
   <br>

  </div>
</div>
 <div id="checkNotSpam">
   <table width="100%" border="0" align="center">
<tr><td align="center">
<iframe src="http://<fmt:message key="dataServer"/><fmt:message key="project.context"/>/RandomValue.htm" scrolling="no" frameborder="0" height="60"  width="500" id='target_random' name='target_random'></iframe>
<br>
<input id="chkVal" type="button" value="Submit" onclick="javascript:ValidFPN();"/>
</td>
</tr>
</table>    
 </div>

<input type="hidden" name="portNo" id="portNo" value=<fmt:message key="PORT"/>>
<input type="hidden" name="subject" id="subject" value=<fmt:message key="SELF_REG_SUBJECT"/>>
<input type="hidden" name="content" id="content" value=<fmt:message key="SELF_REG_CONTENT"/>>
<input type="hidden" name="hostname" id="hostname" value=<fmt:message key="SMTP_HOST_NAME"/>>
<input type="hidden" name="authUser" id="authUser" value=<fmt:message key="SMTP_AUTH_USER"/>>
<input type="hidden" name="authPassword" id="authPassword" value=<fmt:message key="SMTP_AUTH_PWD"/>>
<input type="hidden" name="emailFrom" id="emailFrom" value=<fmt:message key="EMAIL_FROM"/>>

<div id="succMsg">
<table width="100%" border="0" align="center">
<tr><td align="center">
<b><fmt:message key="confirmation"/>
<br><br>
<a href="<c:url value="Logout.htm"/>"><fmt:message key="homepage"/></a></b>
</td>
</tr>
</table>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>
