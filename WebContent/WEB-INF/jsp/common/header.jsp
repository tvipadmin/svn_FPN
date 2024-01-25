<table width="100%" border="0" cellspacing="2" cellpadding="0">

  <tr>
   <c:choose>
	  <c:when test="${login.accountType=='LAB'}">
            <td width="162" rowspan="2" align="center"><img class="logo" src="images/logo.jpg" width="250" height="67" alt="Vital Health Portal"/></td>
            <td valign="top" align="right">&nbsp;</td>
            <td align="right" valign="top" class="logout">
  			<fmt:message key="page.welcome"/><strong> <c:out value="${login.person.labName}"/></strong> | 
            <a id="labMain" href="<c:url value="PatientListLabManagement.htm?idL=lab"/>"><fmt:message key="lab_mainpage"/></a> |
            <a id="setAll" href="<c:url value="ChangePassword.htm"/>"><fmt:message key="form_setting"/></a> | 
            <a id="labHelp" href="<c:url value="javascript:testFunction();openHelp();"/>"><fmt:message key="page.Help"/></a> | 
            <a href="<c:url value="Logout.htm"/>"><fmt:message key="user.Logout"/></a>
            </td>
      </c:when>
   
      <c:when test="${login.accountType=='DOCTOR'}"> 
          <td width="162" rowspan="2" align="center"><img class="logo" src="images/logo.jpg" width="250" height="67" alt="Vital Health Portal"/></td>
          <td valign="top" align="right">&nbsp;</td>
          <td align="right" valign="top" class="logout">
          <fmt:message key="page.welcome"/><strong> <c:out value="${login.person.firstName}"/> <c:out value="${login.person.lastName}"/></strong> |     
           <c:if test="${login.person.type== 'FAMILYPHYSICIAN' || login.person.type== 'BOTH' }">   		   
				<a id="docMain" href="<c:url value="FamilyPhysicianManagement.htm"/>"><fmt:message key="fp_mainpage"/></a>		  			
		  </c:if> 
		  <c:if test="${login.person.type=='SPECALIST' }">		
				<a id="docMain" href="<c:url value="SpecalistManagement.htm"/>"><fmt:message key="sp_mainpage"/></a>
		  </c:if> |
          <a id="setAll" href="<c:url value="ChangePassword.htm"/>"><fmt:message key="form_setting"/></a> | 
          <a id="docHelp" href="<c:url value="javascript:testFunction();openHelp();"/>"><fmt:message key="page.Help"/></a> | 
          <a href="<c:url value="Logout.htm"/>"><fmt:message key="user.Logout"/></a>
          </td>
     </c:when>

     <c:when test="${login.accountType=='ADMIN'}"> 
        <td width="162" rowspan="2" align="center"><img class="logo" src="images/logo.jpg" width="250" height="67" alt="Vital Health Portal"/></td>
        <td valign="top" align="right">&nbsp;</td>		
        <td align="right" valign="top" class="logout">
        <fmt:message key="page.welcome"/><strong> <c:out value="${login.person.firstName}"/> <c:out value="${login.person.lastName}"/></strong> |     
        <a href="<c:url value="Logout.htm"/>"><fmt:message key="form_setting"/></a> | 
        <a href="<c:url value="javascript:testFunction();openHelp();"/>"><fmt:message key="page.Help"/></a> | 
        <a href="<c:url value="Logout.htm"/>"><fmt:message key="user.Logout"/></a>
    </td>
</c:when>
</c:choose>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
</table>