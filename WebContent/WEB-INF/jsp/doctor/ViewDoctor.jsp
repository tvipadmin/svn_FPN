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
<script type="text/javascript" src="<fmt:message key="project.context"/>/applicationScript/viewDoctor.js"></script>

<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444; background: #9ACB34;}
  
</style>
<title><fmt:message key="project.title"/></title>
</head>
<body onLoad="init('<fmt:message key="project.context"/>');">
<div id="divPagebody">
<form name="doctorAdminMain">

<input name="doctorIdV" id="doctorIdV" type="hidden" value=<%out.print(request.getParameter("doctorId"));%>>
<div class="outer_container">
		<table width="100%" align="center" cellpadding="0" cellspacing="0" class="tableEMR">
          <tbody id="doctorbody">
           	<tr>
                <th colspan="2"><fmt:message key="doctor.details"/></th>
            </tr>
            <tr>
                <td align="left" width="150" valign="top"> 
			<IMG id="imgPhoto" src="DisplayPhoto.htm?Id=<%out.print(request.getParameter("doctorId"));%>&typePerson=doctor" width=150 height=150 border=1>
			</td>
			<td>
		<TABLE width="100%" align="center" cellpadding="0" cellspacing="0" class="tableEMR">	
		
          <tr id="pattern" style="display:none;">
           <b> <td class="patInfo" align="left"><fmt:message key="Doctor_DoctorId"/>: </td> <td class="patInfo" id="tableDocId" align="left" width="75%">id</td></b>
          </tr>
          <tr><td  colspan="2"> </td></tr>
          <tr>
            <td class="patInfo" colspan="2"><fmt:message key="doctor.details"/></td>
          </tr>
          <tr id="pattern1" style="display:none;">
            <td id="tableDocDetails" colspan="2">docdetails</td>
          </tr>
           <tr id="pattern2" style="display:none;">
             <td id="tableDocContactDetails" colspan="2">contactdetails</td>
            </tr>
			</TABLE>
			</td>
            </tr>
            

 </tbody>
        </table>
		</div>
</div>

</body>
</html>
