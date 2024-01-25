<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/ChangePasswordValidator.js"></script>
</head>
<script language="javascript">
function captureReturnKey(event)
{
	//alert("capture function msg : [" + event + "]"); return false;
	var	code = 0;
	code = event.keyCode;
	if(code	==	13)
		submitSearchPage();
}

function submitSearchPage()
{
	document.forms[0].action="ChangePassword.htm";
	document.forms[0].submit();
}

function init(message,updated,notupdated)
{
	//alert("init function msg : [" + message + "]"); return false;
	//alert("inside init");
	//alert(message);
	document.getElementById("fpMenu").style.background="#f7941d";
	document.getElementById("setAll").className="fontBold";
	if(message==[])
	{
		noMessage();
	}
	else
	{
	if(message=="updatedd")
	{
		//alert(message);
		successMessage(updated);
		//alert(document.getElementById('confirmPassword'));
		//alert(document.getElementById('currentPassword'));
		//alert(document.getElementById('newPassword'));
		document.getElementById('confirmPassword').value="";
		document.getElementById('currentPassword').value="";
		document.getElementById('newPassword').value="";
		
	}
	else
	{
		failureMessage(notupdated);
	}
	}
}


function successMessage(message) 
{
	//alert("success msg function : [" + message + "]"); return false;
	hide('errorMessage');
	
	document.getElementById ("successMessage").className = 'showDiv successMessage';
	
	document.getElementById("successMessage").innerHTML = message;

	setTimeout ("hide('successMessage')", 2000) 
	
	//alert("here");
	//document.getElementById("password").value="";
}

function hide(title) {
	document.getElementById (title).className = 'hideDiv';
	//alert("hide function message : [" + title + "]"); return false;
	
}

function failureMessage(message){
	//alert("failure msg function : [" + message + "]"); return false;
	hide('successMessage');
	document.getElementById ("errorMessage").className = 'showDiv errorMessage';
	document.getElementById("errorMessage").innerHTML = message;
	setTimeout ("hide('errorMessage')", 2000) 
}

function noMessage()
{
	
	hide('successMessage');
	hide('errorMessage');
}


</script>
<body onload="init('<c:out value="${command.errormessage}"/>','<fmt:message key="password.changed"/>','<fmt:message key="password.notchanged"/>');">

<div id="divHeader"><%@ include file="/WEB-INF/jsp/common/header.jsp" %></div>
<div id="divMainmenu"><%@ include file="/WEB-INF/jsp/common/ChangePasswordHeader.jsp" %></div>

<div id="divPagehead" class="pageHeadingL1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="h3"><fmt:message key="login.ChangePassword"/></td>
        <td align="right" valign="middle"></td>
    </tr>
</table>
</div>
<div id="divPagebody">
<form name="CPForm" method="Post" action="ChangePassword.htm?actiontype=update">
<div class="successMessage" id="successMessage">&nbsp;</div>
<div class="errorMessage" id="errorMessage">&nbsp;</div>

<spring:bind path="command.*">
	<c:if test="${status.error}">
		<TABLE border="0" align=center cellPadding=0 cellSpacing=0  class="table_bg">
	        <TR>
	          <TD class="topleft">&nbsp;</TD>
		        <td id="bulletred">
		        	<c:forEach var="errorMessage" items="${status.errorMessages}">
		        		<li class="compulsoryStar"><c:out value="${errorMessage}"/></li>
		        	</c:forEach>
		        </td>
		        <TD class="topright">&nbsp;</TD>
	        </TR>
	        <TR>
	          <TD class="bottomleft" colspan="2"></TD>
	          <TD class="bottomright"></TD>
	        </TR>
		</TABLE>     
	</c:if>
</spring:bind>
<div id="divPagebody">
<br>
<table width="99%" border="0" align="center" class="plain" >
  	<tr>
	    <td align="right"><b class="compulsoryStar">*</b> <fmt:message key="login.CurrentPassword"/></td>
		<td width=1%  align=center><strong>:</strong></td>
		<td><spring:bind path="command.currentPassword">
			<input id="currentPassword" type="password" class='testarea' name='<c:out value="${status.expression}"/>'  value="<c:out value="${status.value}"/>" size='25' onKeyPress="captureReturnKey(event)"></spring:bind>
		</td>
  	</tr>
	<tr>
        <td align="right"><b class="compulsoryStar">*</b> <fmt:message key="login.NewPassword"/></td>
		<td align=center><strong>:</strong></td>
		<td><spring:bind path="command.newPassword">
		<input id="newPassword" type="password" class='testarea' name='<c:out value="${status.expression}"/>' value="<c:out value="${status.value}"/>" size='25' OnKeyPress="captureReturnKey(event)"></spring:bind>
		</td>
	</tr>
	<tr>
		<td align="right"><b class="compulsoryStar">*</b> <fmt:message key="login.ConfirmPassword"/></td>
		<td align=center><strong>:</strong></td>
		<td><spring:bind path="command.confirmPassword">
			<input id="confirmPassword" type="password" class='testarea' name='<c:out value="${status.expression}"/>' size='25' value="<c:out value="${status.value}"/>" OnKeyPress="captureReturnKey(event)"></spring:bind>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center"><input type="hidden" value=<c:out value="${command.errormessage }"/>> <input type="submit" class="button" value='<fmt:message key="button.Submit"/>'onClick="Submit('<fmt:message key="data.Updated"/>','<fmt:message key="data.NotUpdated"/>')"/></td>
	</tr>
</table>
</div>
</form>

</div>
<div id="divFooter"><%@ include file="/WEB-INF/jsp/common/footer.jsp" %></div>
</body>
</html>