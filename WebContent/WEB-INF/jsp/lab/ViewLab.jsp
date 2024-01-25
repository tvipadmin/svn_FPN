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
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/viewLab.js"></script>
<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444; background: #9ACB34;}
  
</style>
<title><fmt:message key="project.title"/></title>
</head>
<body onLoad="init();">
<div id="divPagebody">
<form name="doctorAdminMain">

<input name="labIdV" id="labIdV" type="hidden" value=<%out.print(request.getParameter("labId"));%>>
<div class="outer_container">
		<table width="100%" align="center" cellpadding="0" cellspacing="0" class="tableEMR">
          <tbody id="doctorbody">
         
		<TABLE width="100%" align="center" cellpadding="0" cellspacing="0" class="tableEMR">	
		
          <tr id="pattern" style="display:none;">
           <b> <td class="patInfo" align="left"><fmt:message key="Lab_LabId"/>: </td> <td class="patInfo" id="tableDocId" align="left" width="75%">id</td></b>
          </tr>
          <tr><td  colspan="2"> </td></tr>
          <tr>
            <td class="patInfo" colspan="2"><fmt:message key="lab.details"/></td>
          </tr>
          <tr id="pattern1" style="display:none;">
            <td id="tableDocDetails" colspan="2">labdetails</td>
          </tr>
           <tr id="pattern2" style="display:none;">
             <td id="tableDocContactDetails" colspan="2">contactdetails</td>
            </tr>
			</TABLE>
			
            

 </tbody>
        </table>
		</div>
</div>

</body>
</html>
