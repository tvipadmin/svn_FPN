<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabRegManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/RegLabValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/LabNetwork.js"></script>

<title><fmt:message key="project.title"/></title>
</head>
<body onLoad="hide('successMessage'); hide('errorMessage'); init(); hide('succMsg');hide('checkNotSpam');">
<table width="100%" border="0" align="center">
            <tr>
                <td width="162" align="center">
                	<img class="logo" src="images/logo.jpg" width="250" height="67" alt="Vital Health Portal"/></td>
                <th align="right" valign="bottom">&nbsp;</th>
            </tr>
        </table>
<div id="divPagebody">
<form name="labNetwork">
<br>

<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="AddTable">
 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
        <tr>
            <td class="h4"><input type="radio" name="join" id="doctorJoin" value="doctor" onclick="javascript:joinDoctor();"><fmt:message key="doctor.register"/>&nbsp;&nbsp;<input type="radio" name="join" id="labJoin" value="lab" onclick="javascript:joinLab();"  checked="checked"><fmt:message key="homeregisterlab"/></td>
            <td align="right" valign="middle"><a href="<c:url value="Logout.htm"/>"><fmt:message key="homepage"/></a></td>
        </tr>
    </table>   
<br>
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
    <td><fmt:message key="Lab_ContactNo"/><span class="compulsoryStar">*</span></td>
    <td><input type="text" id="contactDetails.contactNumber" size="40" name="contactNumberN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
  <tr>
    <td><fmt:message key="Lab_Emial"/><span class="compulsoryStar">*</span></td>
    <td><input type="text" id="contactDetails.email" size="40" name="emailN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
 <tr>
    <td><fmt:message key="Lab_ContactPerson"/></td>
    <td><input type="text" id="contactPerson" size="40" name="contactPersonN" onBlur="validateLabIdInputField(this);"/></td>
  </tr>
 
</form>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td align="right">
     <!--   <small>(ID=<span id="labId">newlab</span>)</small>-->
      <input type="button" value='<fmt:message key="Button_Continue"/>' onClick="addRegLab()"/>
      <input type="button" value='<fmt:message key="Button_Clear"/>' onClick="clearLab()"/>
   
   </td>
  </tr>
</table>
<br>

</div>
</div>
<div id="checkNotSpam">
   <table width="100%" border="0" align="center">
<tr><td align="center">
<iframe src="http://<fmt:message key="dataServer"/><fmt:message key="project.context"/>/RandomValue.htm" scrolling="no" frameborder="0" height="60"  width="500" id='target_random' name='target_random'></iframe>
<br>
<input id="chkVal" type="button" value="Submit" onclick="javascript:ValidLab();"/>
</td>
</tr>
</table>    
 </div>
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