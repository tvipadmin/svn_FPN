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
<script language="javascript">
function init()
{
	 document.getElementById("resetMainMenu").style.background="#f7941d";
}
function captureReturnKey(event)
{
	var	code = 0;
	code = event.keyCode;
	if(code	==	13)document.forms[0].submit();
}
</script>
<title><fmt:message key="project.title"/></title>
</head>
<body onload="init()">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>

<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/MainMenu.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="reset.Param"/></td>
        <td align="right" valign="middle"></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="ResetForm" method="post" action="ResetLogin.htm">

<TABLE width="98%" border="0" align=center cellPadding=0 cellSpacing=0  class="table_bg">
	    
	    <TR>
	        <TD colspan="2">
<br>
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_edit">
	            <tr>
	                
	                <td class="center">
	                	<fmt:message key="login.UserId"/> :
	                	<spring:bind path="command.searchUserId">
	              			<input type="text" class='testarea' name='Id' size='25' onKeyPress="captureReturnKey(event)">
	       		       </spring:bind>   
                         <input type="submit" class="button" value="<fmt:message key="button.Submit"/>">		                	
	                </td>
	              
	            </tr>
	            </table>
	        </TD>
	    </TR>
        <c:choose>
			<c:when test="${command.loginUpdate=='updated'}">
				<TR>
					<TD class="center bold"><fmt:message key="login.Reset"/></TD>
				</TR>
    		</c:when>
            <c:when test="${command.loginUpdate=='notupdated'}">
                <TR>
					<TD class="center bold"><fmt:message key="login.NotReset"/></TD>
				</TR>
            </c:when>
     </c:choose>
	</TABLE>
    </form>
   
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>
