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
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/labCommonManagement.js"></script>
<title><fmt:message key="project.title"/></title>
</head>
<body onLoad="init('<fmt:message key="succReset"/>','<fmt:message key="notReset"/>','<fmt:message key="sureReset"/>','<fmt:message key="lab_registered"/>','<fmt:message key="person_enabled"/>','<fmt:message key="person_reject"/>','<fmt:message key="person_disable"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>'); show('ListTable'); hide('ListTablePED');hide('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/MainMenu.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="lab.management"/></td>
        <td align="right" valign="middle"><a id="listLAL" href="javascript:show('ListTable'); show('searchTable'); hide('ListTablePED'); hide('AddTable'); removeErrorMsg()" onclick="testFunction();loadLab('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','allType')"><fmt:message key="list_lab"/></a> |<a id="listLP" href="javascript:hide('ListTable'); show('searchTable'); show('ListTablePED'); hide('AddTable'); removeErrorMsg()" onclick="testFunction();showPendingListL('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','pending')"><fmt:message key="list_labPending"/></a> | <a id="listLD" href="javascript:hide('ListTable'); show('searchTable'); show('ListTablePED'); hide('AddTable'); removeErrorMsg()" onclick="testFunction();showDisabledListL('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','disabled')"><fmt:message key="list_labDisabled"/></a> | <a id="listLE" href="javascript:hide('ListTable'); show('searchTable'); show('ListTablePED'); hide('AddTable'); removeErrorMsg()" onclick="testFunction();showEnabledListL('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','enabled')"><fmt:message key="list_labEnabled"/></a> | <a id="listLA" href="javascript:hide('ListTable'); hide('searchTable'); hide('ListTablePED'); show('AddTable'); clearLab('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')" onclick="testFunction()"><fmt:message key="new_edit"/></a></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="labAdminMain">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="newReg" align="center" style="font-stretch: expanded; font-weight: bold;">
</div>
<input type="hidden" name="searchTypeLab" id="searchTypeLab" value="allType">
<div id="searchTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="lab.list"/></td>
        <td align="right" valign="middle"><fmt:message key="lab.enterName"/>: <input type="text" id="labnamen" name="labNameS" size="10"  onkeyup="javascript:searchOnKeyLab('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>'); testFunction()">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:searchOnKeyLab('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>'); testFunction()"></td>
</tr>
</table>
</div>
<div id="ListTable">


<table width="100%" border="0" align="center" class="viewtable">
  <thead>
    <tr>
      <th><fmt:message key="Lab_LabId"/></th>
      <th><fmt:message key="lab.details"/></th>
      <th><fmt:message key="contactDetails"/></th>
      <th><fmt:message key="Lab_Status"/></th>
      <th><fmt:message key="edit.delete"/></th></tr>
     </tr>
  </thead>
  <tbody id="labbody">
    <tr id="pattern" style="display:none;">
      <td id="tableDocId">id</td>
      <td><span id="tableDocDetails">docdetails</span><br/></td>
      <td><span id="tableDocContactDetails">contactdetails</span></td>
      <td id="tableDocStatus">status</td>
      <td width="15%" align="center" valign="middle">
      <img src="images/page-edit-16x16.png" width="16" height="16" alt="<fmt:message key="image.edit"/>" id="edit" class="editicon" title="Edit Lab" onClick="editClicked(this.id); hide('ListTable'); hide('searchTable'); hide('ListTablePED');show('AddTable')">&nbsp;
      <img src="images/delete-16x16.png" width="16" height="16" alt="<fmt:message key="image.delete"/>" id="delete" class="deleteicon" title="Delete Lab" onClick="deleteClicked(this.id,'<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>')">
      &nbsp;<img src="images/reset-16x16.png" width="35" height="16" alt=<fmt:message key="image.reset"/> id="reset" class="reseticon" title="Reset Password" onClick="resetClicked(this.id)"></td> 
      </td>
    </tr>
  </tbody>
</table>
<br>
<br>
</div>

<div id="ListTablePED">

<table width="100%" border="0" align="center" class="viewtable">
  <thead>
    <tr>
      <th><fmt:message key="Lab_LabId"/></th>
      <th><fmt:message key="lab.details"/></th>
      <th><fmt:message key="contactDetails"/></th>
      <th><fmt:message key="Lab_Status"/></th>
      <th><fmt:message key="edit.delete"/></th></tr>
     </tr>
  </thead>
  <tbody id="labbodyPED">
    <tr id="patternPED" style="display:none;">
      <td id="tableDocIdPED">id</td>
      <td><span id="tableDocDetailsPED">docdetails</span><br/></td>
      <td><span id="tableDocContactDetailsPED">contactdetails</span></td>
      <td id="tableDocStatusPED">status</td>
      <td width="15%">
      <img src="images/page-edit-16x16.png" width="16" height="16" alt="<fmt:message key="image.edit"/>" id="edit" class="editicon" onClick="editClicked(this.id); hide('ListTable'); hide('searchTable'); hide('ListTablePED');show('AddTable')">&nbsp;
      <img src="images/delete-16x16.png" width="16" height="16" alt="<fmt:message key="image.delete"/>" id="delete" class="editicon" onClick="deleteClicked(this.id,'<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>')">
      &nbsp;<img src="images/reset-16x16.png" width="35" height="16" alt=<fmt:message key="image.reset"/> id="reset" class="reseticon" title="Reset Password" onClick="resetClicked(this.id)"></td>
      </td>
    </tr>
  </tbody>
</table>
<br>
<br>
</div>



<div id="AddTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="lab.newEdit"/></td>
        <td align="right" valign="middle"></td>	
    </tr>
</table>

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
      <input type="button" value='<fmt:message key="Button_Save"/>' onClick="updateLab('<fmt:message key="data.Updated"/>','<fmt:message key="data.NotUpdated"/>','<fmt:message key="data.NewRecord"/>','<fmt:message key="data.NewRecordNot"/>','<fmt:message key="data.NotAdded"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>')"/>
      <input type="button" value='<fmt:message key="Button_Clear"/>' onClick="clearLab('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')"/>
      <input type="button" value="Cancel" onClick="clearLab('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>'); show('ListTable'); hide('AddTable'); removeErrorMsg();enableFullListLab()"/>
   </td>
  </tr>
</table>
<br>
</div>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>