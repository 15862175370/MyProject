<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">
var f;
function use(f){
	location.href="/Alisi2/UseConfirmCode?f="+f;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String pa=(String)request.getAttribute("code"); %>

<table>
<tr>
<td>验证码未使用</td>
<td><input type="button" value="使用" onclick="use(<%=pa%>)"></td>
</tr>

</table>
</body>
</html>