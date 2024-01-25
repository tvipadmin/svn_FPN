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
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/DoctorManagementValidator.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/doctorCommonManagement.js"></script>

<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444; background: #9ACB34;}
  
</style>
<title><fmt:message key="project.title"/></title>
</head>
<body onLoad="init('<fmt:message key="doctor_registered"/>','<fmt:message key="person_enabled"/>','<fmt:message key="person_reject"/>','<fmt:message key="person_disable"/>','<fmt:message key="labManagement"/>','<fmt:message key="newReg"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','<fmt:message key="notReset"/>','<fmt:message key="succReset"/>','<fmt:message key="sureReset"/>'); show('ListTable'); hide('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); hide('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/MainMenu.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="doctor.management"/></td>
        <td align="right" valign="middle"><a id="listAL" href="javascript:show('ListTable');show('searchTable'); hide('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); hide('AddTable'); removeErrorMsg()" onclick="testFunction();loadDoctor('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','allType')"><fmt:message key="list_doctor"/></a> | <a  id="listP" href="javascript:hide('ListTable');show('searchTable'); show('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); hide('AddTable'); removeErrorMsg()" onclick="testFunction(); showPendingList('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','pending')"><fmt:message key="list_doctorPending"/></a>  | <a id="listD" href="javascript:hide('ListTable');show('searchTable'); hide('ListTablePending'); show('ListTableDisabled'); hide('ListTableEnabled'); hide('AddTable'); removeErrorMsg()" onclick="testFunction();showDisabledList('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','disabled')"><fmt:message key="list_doctorDisabled"/></a>  | <a id="listE" href="javascript:hide('ListTable'); show('searchTable'); hide('ListTablePending'); hide('ListTableDisabled'); show('ListTableEnabled'); hide('AddTable'); removeErrorMsg()" onclick="testFunction();showEnabledList('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>','enabled')"><fmt:message key="list_doctorEnabled"/></a>  | <a id="listA" href="javascript:clearDoctor('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>'); hide('ListTable'); hide('searchTable'); hide('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); show('AddTable')" onclick="testFunction()"><fmt:message key="add_doctor"/></a></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="doctorAdminMain">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="newReg" align="center" style="font-stretch: expanded; font-weight: bold;">
</div>

<input type="hidden" name="searchType" id="searchType" value="allType">
<div id="searchTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="doctor.list"/></td>
        <td align="right" valign="middle"><fmt:message key="doctor.enterName"/>: <input type="text" id="docname" name="doctorNameS" size="10"  onkeyup="javascript:searchOnKeyDoctor('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>'); testFunction()"><input name="search" type="button"  value='Search' onClick="javascript:searchOnKeyDoctor('<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>'); testFunction()"></td>
    </tr>
</table>
</div>
<div id="ListTable">

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="Doctor_DoctorId"/></th>
        <th><fmt:message key="doctor.details"/></th>
        <th><fmt:message key="Doctor_Type"/></th>
        <th><fmt:message key="contactDetails"/></th>
        <th><fmt:message key="Doctor_Status"/></th>
        <th><fmt:message key="edit.delete"/></th></tr>
    </thead>
  <tbody id="doctorbody">
    <tr id="pattern" style="display:none;">
      <td id="tableDocId">id</td>
      <td id="tableDocDetails">docdetails</td>
      <td id="tableDocType">type</td>
      <td id="tableDocContactDetails">contactdetails</td>
      <td id="tableDocStatus">status</td>
      <td width="10%" align="center"><img src="images/page-edit-16x16.png" width="16" height="16" alt="Edit" title="Edit Doctor" id=<fmt:message key="image.edit"/> class="editicon" onClick="editClicked(this.id); hide('ListTable'); hide('searchTable'); hide('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); show('AddTable'); return false;">&nbsp;<img src="images/delete-16x16.png" width="16" height="16" alt=<fmt:message key="image.delete"/> id="delete" class="deleteicon" title="Delete Doctor" onClick="deleteClicked(this.id,'<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>');">&nbsp;<img src="images/reset-16x16.png" width="35" height="16" alt=<fmt:message key="image.reset"/> id="reset" class="reseticon" title="Reset Password" onClick="resetClicked(this.id)"></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>

<div id="ListTablePending">

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="Doctor_DoctorId"/></th>
        <th><fmt:message key="doctor.details"/></th>
        <th><fmt:message key="Doctor_Type"/></th>
        <th><fmt:message key="contactDetails"/></th>
        <th><fmt:message key="Doctor_Status"/></th>
        <th><fmt:message key="edit.delete"/></th></tr>
    </thead>
  <tbody id="doctorbodyP">
    <tr id="patternP" style="display:none;">
      <td id="tableDocIdP">id</td>
      <td id="tableDocDetailsP">docdetails</td>
      <td id="tableDocTypeP">type</td>
      <td id="tableDocContactDetailsP">contactdetails</td>
      <td id="tableDocStatusP">status</td>
      <td width="10%" align="center"><img src="images/page-edit-16x16.png" width="16" height="16" alt="Edit" id=<fmt:message key="image.edit"/> class="editicon" onClick="editClicked(this.id); hide('ListTable');  hide('searchTable'); hide('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); show('AddTable'); return false;">&nbsp;<img src="images/delete-16x16.png" width="16" height="16" alt=<fmt:message key="image.delete"/> id="delete" class="editicon" onClick="deleteClicked(this.id,'<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>');">&nbsp;<img src="images/reset-16x16.png" width="35" height="16" alt=<fmt:message key="image.reset"/> id="reset" class="reseticon" title="Reset Password" onClick="resetClicked(this.id)"></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>


<div id="ListTableDisabled">

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="Doctor_DoctorId"/></th>
        <th><fmt:message key="doctor.details"/></th>
        <th><fmt:message key="Doctor_Type"/></th>
        <th><fmt:message key="contactDetails"/></th>
        <th><fmt:message key="Doctor_Status"/></th>
        <th><fmt:message key="edit.delete"/></th></tr>
    </thead>
  <tbody id="doctorbodyD">
    <tr id="patternD" style="display:none;">
      <td id="tableDocIdD">id</td>
      <td id="tableDocDetailsD">docdetails</td>
      <td id="tableDocTypeD">type</td>
      <td id="tableDocContactDetailsD">contactdetails</td>
      <td id="tableDocStatusD">status</td>
      <td width="10%" align="center"><img src="images/page-edit-16x16.png" width="16" height="16" alt="Edit" id=<fmt:message key="image.edit"/> class="editicon" onClick="editClicked(this.id); hide('ListTable');  hide('searchTable'); hide('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); show('AddTable'); return false;">&nbsp;<img src="images/delete-16x16.png" width="16" height="16" alt=<fmt:message key="image.delete"/> id="delete" class="editicon" onClick="deleteClicked(this.id,'<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>');">&nbsp;<img src="images/reset-16x16.png" width="35" height="16" alt=<fmt:message key="image.reset"/> id="reset" class="reseticon" title="Reset Password" onClick="resetClicked(this.id)"></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>

<div id="ListTableEnabled">
<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="Doctor_DoctorId"/></th>
        <th><fmt:message key="doctor.details"/></th>
        <th><fmt:message key="Doctor_Type"/></th>
        <th><fmt:message key="contactDetails"/></th>
        <th><fmt:message key="Doctor_Status"/></th>
        <th><fmt:message key="edit.delete"/></th></tr>
    </thead>
  <tbody id="doctorbodyE">
    <tr id="patternE" style="display:none;">
      <td id="tableDocIdE">id</td>
      <td id="tableDocDetailsE">docdetails</td>
      <td id="tableDocTypeE">type</td>
      <td id="tableDocContactDetailsE">contactdetails</td>
      <td id="tableDocStatusE">status</td>
      <td width="10%" align="center"><img src="images/page-edit-16x16.png" width="16" height="16" alt="Edit" id=<fmt:message key="image.edit"/> class="editicon" onClick="editClicked(this.id); hide('ListTable');  hide('searchTable'); hide('ListTablePending'); hide('ListTableDisabled'); hide('ListTableEnabled'); show('AddTable'); return false;">&nbsp;<img src="images/delete-16x16.png" width="16" height="16" alt=<fmt:message key="image.delete"/> id="delete" class="editicon" onClick="deleteClicked(this.id,'<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>');">&nbsp;<img src="images/reset-16x16.png" width="35" height="16" alt=<fmt:message key="image.reset"/> id="reset" class="reseticon" title="Reset Password" onClick="resetClicked(this.id)"></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>



<div id="AddTable">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
        <tr>
            <td class="h4"><fmt:message key="doctor.newEdit"/></td>
            <td align="right" valign="middle"></td>
        </tr>
    </table>   
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
        <td><textarea rows="2" id="contactDetails.address" name="addressN" tabindex="6" cols="25" ></textarea></td>
        <td><fmt:message key="Doctor_Specialty"/></td>
        <td><input type="text" id="specialty" size="40" name="specialtyN" tabindex="13" onBlur="validateDoctorIdInputField(this);"/></td>
      </tr>
       <tr>
        <td><fmt:message key="Doctor_ContactNo"/></td>
        <td><input type="text" id="contactDetails.contactNumber" size="33" name="contactNumberN" tabindex="7" onBlur="validateDoctorIdInputField(this);"/></td>
        <td><fmt:message key="Doctor_Type"/>
          <span class="compulsoryStar">*</span></td>
        <td>
          <select id="type" onBlur="validateDoctorIdInputField(this);" tabindex="14" onchange="validateDoctorIdInputField(this);" name="doctype">
            <option value=''>
              <fmt:message key="doctor.Select.Type"/>
            </option>
            <option value='SPECALIST'><fmt:message key="doctor.specalist"/></option>
            <option value='FAMILYPHYSICIAN'><fmt:message key="doctor.fp"/></option>
            <option value='BOTH'><fmt:message key="doctor.both"/></option>
          </select></td>
      </tr>
     <tr>
        <td><fmt:message key="Doctor_Photo"/></td>
        <td colspan="3"><input type="text" id="photo" name="chkPhotoName" disabled="disabled" size="33">
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
      <input type="button" value='<fmt:message key="Button_Save"/>' tabindex="16" onClick="updateDoctor('<fmt:message key="data.Updated"/>','<fmt:message key="data.NotUpdated"/>','<fmt:message key="data.NewRecord"/>','<fmt:message key="data.NewRecordNot"/>','<fmt:message key="data.NotAdded"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>')"/>
      <input type="button" value='<fmt:message key="Button_Clear"/>' tabindex="17"  onClick="clearDoctor('<fmt:message key="data.path"/>','<fmt:message key="photo.path"/>')"/>
      <input type="button" value='<fmt:message key="Button_Cancel"/>' tabindex="18"  onClick="javascript:show('ListTable'); hide('AddTable'); removeErrorMsg(); enableFullList()"/></td></tr>
    </table>
   <br>
  </div>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>
