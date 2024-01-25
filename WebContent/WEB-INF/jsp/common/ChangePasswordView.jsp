<%--  
	* $Id: ChangePasswordView.jsp,v 1.1 2007/01/10 04:43:46 hcvs Exp $ 
    * Harish N S
--%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<link>
<title><fmt:message key="project.title"/></title>
<BODY>
<c:set var="mainSelected" value="mmSettings" scope="session"/>
<c:set var="subSelected" value="smChangePassword" scope="session"/>
<!--<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>-->
<jsp:include page="/WEB-INF/jsp/common/ChangePasswordData.jsp"/>
</BODY>
</HTML>
