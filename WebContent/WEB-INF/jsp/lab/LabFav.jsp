<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/labFav.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagement.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagementValidator.js"></script>


<title><fmt:message key="project.title"/></title>
</head>
<body onload="init('<fmt:message key="delete_lab"/>','<fmt:message key="succ_removed"/>','<fmt:message key="not_removed"/>','<fmt:message key="succ_sent"/>','<fmt:message key="req_notsent"/>','<fmt:message key="notactive"/>','<c:out value="${login.loginId}"/>'); show('ListTable'); hide('AddTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/FPMainMenu.jsp" %></div>
<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="labrequest.management"/></td>
        <td align="right" valign="middle"><a id="listFavLab" href="javascript:loadLab('<c:out value="${login.loginId}"/>'); show('ListTable'); hide('AddTable'); removeErrorMsg()" onclick="testFunction()"><fmt:message key="list.specalist"/></a> | <a id="addReqLab" href="javascript:hide('ListTable'); show('AddTable'); highLightLab()" onclick="testFunction()"><fmt:message key="add_request"/></a></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="familyPhySub">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>
<div id="ListTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
       <td class="h4"><input type="button" onclick="javascript:removeRequest('<c:out value="${login.loginId}"/>')" value="<fmt:message key="remove_fav"/>"></td>
        <td align="right" valign="middle"><fmt:message key="enter_specalist"/>: <input type="text" id="labFavName" name="labFavNameS" size="10"  onkeyup="javascript:testFunction(); searchFavLab('<c:out value="${login.loginId}"/>')">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:testFunction(); searchFavLab('<c:out value="${login.loginId}"/>')"></td>
    </tr>
</table>
<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="select"/></th> 
         <th><fmt:message key="Lab_LabId"/></th>
         <th><fmt:message key="lab.details"/></th>
         <th><fmt:message key="contactDetails"/></th>
        <th><fmt:message key="status"/></th>
      </tr>
    </thead>
  <tbody id="specalityfavbody">
    <tr id="pattern1" style="display:none;">
          <td><input type="checkbox" id="docIdRem" onClick="appendDocRemValue(this.id)"></td>   
          <td><span id="tableFavLabId">id</span><br/></td>
          <td><span id="tableFavLabDetails">docdetails</span><br/></td>
          <td><span id="tableFavLabContactDetails">contactdetails</span><br/></td>
          <td><span id="tableStatus">status</span><br/></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>
<div id="AddTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><input type="button" onclick="javascript:sendRequest('<c:out value="${login.loginId}"/>')" value="<fmt:message key="send_request"/>"></td>
        <td align="right" valign="middle"><fmt:message key="enter_lab"/>: <input type="text" id="allLab" name="allLabS" size="10"  onkeyup="javascript:testFunction(); searchLabAll('<c:out value="${login.loginId}"/>')">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:testFunction(); searchLabAll('<c:out value="${login.loginId}"/>')"></td>
    </tr>
</table>

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="select"/></th> 
          <th><fmt:message key="Lab_LabId"/></th>
         <th><fmt:message key="lab.details"/></th>
         <th><fmt:message key="contactDetails"/></th>
      </tr>
    </thead>
  <tbody id="specalitybody">
    <tr id="pattern" style="display:none;">
          <td><input type="checkbox" id="docId" onClick="appendDocValue(this.id)"></td>   
           <td><span id="tableLabId">id</span><br/></td>
          <td><span id="tableLabDetails">docdetails</span><br/></td>
          <td><span id="tableLabContactDetails">contactdetails</span><br/></td>
          
     </tr>
   </tbody>
</table>
</div>
</div>
<input id="remLabId" type="hidden" size="20" name="remLabId"/>
<input id="labIds" type="hidden" size="20" name="labIds"/>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</form>
</body>
</html>
