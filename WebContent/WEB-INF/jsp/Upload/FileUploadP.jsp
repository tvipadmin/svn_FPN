<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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



  function fileupload_query_uploadstatus() {
	  status="0";
	   UploadInfoBean.getPercentage( function(data) {  	
	   
        	setStatus(data);
	  
	    
	    }
	  );
    return true;
  }

  function setStatus(data)
  {
  
	   status=data;
	 
	  
	  if (status == "100")
	  {
	      document.getElementById("fileupload_progress").innerHTML
	                                       ="File successfully uploaded";
	      
	  }
	    else {
	      document.getElementById("progressBar").style.display = "block";
	      document.getElementById("fileupload_progress").innerHTML=
	                                       "Uploading file: " + status
	                                       + "% completed, please wait...";
	      document.getElementById("progressBarBoxContent").style.width =
	                                       parseInt(data * 3.5) + "px";
	      
	      setTimeout(fileupload_query_uploadstatus, 1000);
	    }
	
  
  }

  function callMethod()
  {

	  parent.setName(document.PhotoUpload.photoName.value);
	  
  }

  function filsel(fpath) {
	  myActiveXObject = new ActiveXObject("Scripting.FileSystemObject");
	  // The below commented code works fine //
	  //file = myActiveXObject.GetFile("c:\\inetpub\\wwwroot\\pic\\file1.gif");
	  file = myActiveXObject.GetFile(fpath);
	  file.Delete();
	  }

   function cancelPhotoo()
  {
	
	  var path="<fmt:message key="data.path"/>"+"/"+"<fmt:message key="photo.path"/>";
	  alert(path);
     
      var fileName=document.PhotoUpload.photoName.value;
      var fpath=path+"/"+fileName;
      alert(fpath);
      UploadInfoBean.cancelPhoto(fpath);
      document.PhotoUpload.photoName.value="";
      parent.setName(document.PhotoUpload.photoName.value);
  }
 
</script>

<style type="text/css">
  #progressBar {padding-top: 5px;}
  #progressBarBox {width: 350px; height: 20px; border: 1px insert; background: #eee;}
  #progressBarBoxContent {width: 0; height: 20px; border-right: 1px solid #444;
                                       background: #9ACB34;}
</style>


<title>Insert title here</title>
</head>
<body onload="callMethod()">
<table>
<tr>
<td>
<form name="PhotoUpload" method="post" action="FileUpload.htm" enctype="multipart/form-data" onsubmit="setTimeout('fileupload_query_uploadstatus()', 500)" target="target_upload">
<h3><fmt:message key="simpleupload"/></h3>
<p><fmt:message key="file.upload"/></p>
    <input type="file" name="file1" id="photoUpload" onchange="document.PhotoUpload.submit()"/><br/>
    <input type="submit" value="Upload" class="button" style="margin-top: 5px"  />
    <input type="button" name="cancelUpload" value="Cancel Upload" onclick="cancelPhotoo()">
    <input type="hidden" name="photoName" id="photoNameId" value="<%=session.getValue("PhotoName") %>">

</form>
</td>
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


