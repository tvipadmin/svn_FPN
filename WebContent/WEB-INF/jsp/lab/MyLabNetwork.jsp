<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/myLabNetwork.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/LabManagement.js"></script>


<title><fmt:message key="project.title"/></title>
</head>  
<body onload="init('<fmt:message key="sure_remove"/>','<fmt:message key="succ_confirmed"/>','<fmt:message key="not_confirmed"/>','<fmt:message key="not_removed"/>','<fmt:message key="succ_rejected"/>','<fmt:message key="not_rejected"/>','<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<fmt:message key="succ_removed"/>','<c:out value="${login.loginId}"/>','all'); show('ListTable'); show('ConfirmTable'); hide('successMessage'); hide('errorMessage');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/LabMainMenu.jsp" %></div>
<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="labrequest.management"/></td>
        <td align="right" valign="middle"><a id="allL" href="javascript:init('<fmt:message key="sure_remove"/>','<fmt:message key="succ_confirmed"/>','<fmt:message key="not_confirmed"/>','<fmt:message key="not_removed"/>','<fmt:message key="succ_rejected"/>','<fmt:message key="not_rejected"/>','<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<fmt:message key="succ_removed"/>','<c:out value="${login.loginId}"/>','all'); show('ListTable'); show('ConfirmTable'); removeErrorMsg()" onclick="testFunction()"><fmt:message key="list.All"/></a> | <a id="allPLL" href="javascript:init('<fmt:message key="sure_remove"/>','<fmt:message key="succ_confirmed"/>','<fmt:message key="not_confirmed"/>','<fmt:message key="not_removed"/>','<fmt:message key="succ_rejected"/>','<fmt:message key="not_rejected"/>','<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<fmt:message key="succ_removed"/>','<c:out value="${login.loginId}"/>','pending'); show('ListTable'); hide('ConfirmTable'); removeErrorMsg()" onclick="testFunction()"><fmt:message key="list.pending"/></a> | <a id="allLC" href="javascript:init('<fmt:message key="sure_remove"/>','<fmt:message key="succ_confirmed"/>','<fmt:message key="not_confirmed"/>','<fmt:message key="not_removed"/>','<fmt:message key="succ_rejected"/>','<fmt:message key="not_rejected"/>','<fmt:message key="newReq"/>','<fmt:message key="network.my"/>','<fmt:message key="succ_removed"/>','<c:out value="${login.loginId}"/>','confirmed'); hide('ListTable'); show('ConfirmTable')" onclick="testFunction()"><fmt:message key="list.confirmed"/></a></td>
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
       <td class="h4"><input type="button" onclick="javascript:confirmRequest('<c:out value="${login.loginId}"/>')" value="<fmt:message key="confirm_request"/>"><input type="button" onclick="javascript:rejectRequest('<c:out value="${login.loginId}"/>')" value="<fmt:message key="reject_doc"/>"></td>
        <td align="right" valign="middle"><fmt:message key="enter_specalist"/>: <input type="text" id="docFavName" name="docFavNameS" size="10"  onkeyup="javascript:testFunction(); searchPendingDoc('<c:out value="${login.loginId}"/>')">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:testFunction(); searchPendingDoc('<c:out value="${login.loginId}"/>')"></td>
    </tr>
</table>
<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="select"/></th> 
        <th><fmt:message key="doctor.name"/></th>
        <th><fmt:message key="contact_details"/></th>
        <th><fmt:message key="status"/></th>
      </tr>
    </thead>
  <tbody id="specalityfavbody">
    <tr id="pattern1" style="display:none;">
          <td><input type="checkbox" id="docIdConfirm" onClick="appendDocConfirmValue(this.id)"></td>   
          <td><span id="tableFavDocDetails">docdetails</span><br/></td>
          <td><span id="tableFavDocContactDetails">contactdetails</span><br/></td>
          <td><span id="tableStatus">status</span><br/></td>
    </tr>
   </tbody>
</table>
<br>
<br>
</div>
<div id="ConfirmTable">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pageHeadingL2">
    <tr>
        <td class="h4"><input type="button" onclick="javascript:removeDoctor('<c:out value="${login.loginId}"/>')" value="<fmt:message key="remove_doc"/>"></td>
        <td align="right" valign="middle"><fmt:message key="enter_specalist"/>: <input type="text" id="allDoc" name="allDocS" size="10"  onkeyup="javascript:testFunction(); searchConfirmedDoc('<c:out value="${login.loginId}"/>')">&nbsp;<input name="search" type="button"  value='Search' onClick="javascript:testFunction(); searchConfirmedDoc('<c:out value="${login.loginId}"/>')"></td>
    </tr>
</table>

<table width="100%" border="0" align="center" class="viewtable">
    <thead>
      <tr>
        <th><fmt:message key="select"/></th> 
        <th><fmt:message key="doctor.name"/></th>
        <th><fmt:message key="contact_details"/></th>
         <th><fmt:message key="status"/></th>
      </tr>
    </thead>
  <tbody id="specalitybody">
    <tr id="pattern" style="display:none;">
          <td><input type="checkbox" id="docId" onClick="appendDocValue(this.id)"></td>   
          <td><span id="tableDocDetails">docdetails</span><br/></td>
          <td><span id="tableDocContactDetails">contactdetails</span><br/></td>
          <td><span id="tableDocStatus">status</span><br/></td>
     </tr>
   </tbody>
</table>
</div>
</div>
<input id="remDocId" type="hidden" size="20" name="remDocId"/>
<input id="confirmdocIds" type="hidden" size="20" name="confirmdocIds"/>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</form>
</body>
</html>
