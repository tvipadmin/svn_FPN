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
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/labCommonServices.js"></script>
<title><fmt:message key="project.title"/></title>
</head>
<body onload="init('<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<c:out value="${login.loginId}"/>'); show('ListTable'); hide('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/LabMainMenu.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="lab_Services"/></td>
        <td align="right" valign="middle"><a id="listLS" href="javascript:init('<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<c:out value="${login.loginId}"/>'); show('ListTable'); hide('AddTable')" onclick="testFunction()"><fmt:message key="list.labServices"/></a> | <a id="allLS" href="javascript:hide('ListTable'); show('AddTable'); clearLabService()" onclick="testFunction()"><fmt:message key="labservices.newEdit"/></a></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="labServicesMain">
<div class="successMessage" id="successMessage"></div>
<div class="errorMessage" id="errorMessage"></div>
<div id="ListTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><fmt:message key="all.list"/></td>
        <td align="right" valign="middle"><fmt:message key="enter_ServiceName"/>: <input type="text" id="labservicenamen" name="labServiceNameS" size="10"  onkeyup="javascript:searchOnKeyLabService('<c:out value="${login.loginId}"/>')"> <input name="search" type="button"  value='Search' onClick="javascript:searchOnKeyLabService('<c:out value="${login.loginId}"/>')"></td>
</tr>
</table>

<table width="100%" border="0" align="center" class="viewtable">
  <thead>
    <tr>
      <th><fmt:message key="labservice_ServiceId"/></th>
      <th><fmt:message key="labservice_Details"/></th>
       <th width="15%"><fmt:message key="labservice.newEdit"/></th>
     </tr>
  </thead>
  <tbody id="labservicebody">
    <tr id="pattern" style="display:none;">
      <td><span id="tableServiceId">id</span><br/></td>
      <td><span id="tableServiceDetails">labservicesdetails</span><br/></td>
      <td align="center">
      <img src="images/page-edit-16x16.png" width="16" height="16" alt=<fmt:message key="image.edit"/> id="edit" class="editicon" title="Edit Lab Service" onClick="editClicked(this.id); hide('ListTable'); show('AddTable')">&nbsp;
      <img src="images/delete-16x16.png" width="16" height="16" alt=<fmt:message key="image.delete"/> id="delete" class="deleteicon" title="Delete LabService" onClick="deleteClicked(this.id,'<c:out value="${login.loginId}"/>','<fmt:message key="data.Deleted"/>','<fmt:message key="data.NotDeleted"/>','<fmt:message key="data.Delete"/>','<fmt:message key="data.question"/>')">
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
        <td class="h4"><fmt:message key="labservice.newEdit"/></td>
        <td align="right" valign="middle">&nbsp;</td>
</tr>
</table>
<table border="0" align="center" class="plain" >
  <tr>
    <td><fmt:message key="Lab_ServiceName"/><span class="compulsoryStar">*</span></td>
    <td><input id="serviceName" type="text" size="20" name="serviceNameN" onBlur="validateLabServiceIdInputField(this,'<c:out value="${login.loginId}"/>');"/><input id="labServiceId" type="hidden"/></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
  <tr>
    <td colspan="2" align="right">
      <!--<small>(ID=<span id="labId">newlabservice</span>)</small>-->
      <input type="button" value='<fmt:message key="Button_Save"/>' onClick="updateLabService('<c:out value="${login.loginId}"/>','<fmt:message key="data.Updated"/>','<fmt:message key="data.NotUpdated"/>','<fmt:message key="data.NewRecord"/>','<fmt:message key="data.NewRecordNot"/>','<fmt:message key="data.NotAdded"/>')"/>
      <input type="button" value='<fmt:message key="Button_Clear"/>' onClick="clearLabService()"/>
   </td>
  </tr>
</table>
</form>
<br>
<br>
</div>
</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>