<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/engine.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/util.js"></script>
<script type="text/javascript" src="<fmt:message key="project.context"/>/dwr/interface/UploadInfoBean.js"></script>
<script language="javascript">
var status="0";
DWREngine.setErrorHandler(null);
DWREngine.setWarningHandler(null);
var a;


  function callMethod(str)
  {
	  parent.setName(document.PhotoUpload.photoName.value,str);
  }

</script>

<style type="text/css">
	#progressBar {padding-top: 5px;}
	#progressBarBox {width: 250px; height: 20px; border: 1px insert; background: #eee;}
	#progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444; background: #9ACB34;}
</style>

<title><fmt:message key="project.title"/></title>
</head>
<body onLoad="callMethod('case')" topmargin="0" leftmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td><form name="PhotoUpload" method="post" action="FileUploadCase.htm?uploadtype=case" enctype="multipart/form-data" onSubmit="setTimeout('fileupload_query_uploadstatus()', 500)" target="target_upload">
    <b><fmt:message key="case.upload"/></b><br>
    <fmt:message key="file.upload"/> :<br>
    <input type="file" name="file1" id="photoUpload" onChange="document.PhotoUpload.submit()"/>
    <input type="hidden" name="photoName" id="photoNameId" value="<%=session.getValue("PhotoName") %>">
</form></td>
        <td>
        	<%-- file upload progress bar --%>
            <div id="fileupload_progress"></div>
            <div id="progressBar" style="display: none;">
              <div id="progressBarBoxContent"></div>
            </div>
		</td>
    </tr>
</table>
</body>
</html>

