<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
var firstNumber = Math.ceil(Math.random() * 10);
var secondNumber = Math.ceil(Math.random() * 10); 
var totalVal = firstNumber + secondNumber
var valQues;
var question;
function init(valQuesc,questionc)
{
	//alert(valQuesc);
	question = questionc;
	valQues = valQuesc;
	//alert(valQues);
	document.getElementById("totalVal").value = totalVal;
}
function DrawFPN(valQues)
{
	//alert(valQues);
    document.write(valQues+" "+ firstNumber + " + " + secondNumber +"? ");
    document.write("<input id='FPNInput' type='text' maxlength='2' size='2'/>");
}    
</script>
<title>Insert title here</title>
</head>
<body onload="init('<fmt:message key="whatIs"/>','<fmt:message key="data.question"/>');">
<table width="100%" border="0" align="center">
<tr><td align="center">
<b><fmt:message key="enter.value"/><br><br>
<script type="text/javascript">DrawFPN('<fmt:message key="whatIs"/>')</script></b>
</td>
</tr>
</table>
<input type="hidden" name="totalVal" id="totalVal">
</body>
</html>