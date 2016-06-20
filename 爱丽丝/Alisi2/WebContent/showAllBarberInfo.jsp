<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%  
     List<Barber> barberList=(List<Barber>)   request.getAttribute("barberList");
    
    %>
    <table border=1 width="600px">
          <tr>
             <th>头像</th>
            <th>工号</th>
            <th>名字</th>
            <th>职位</th>
            <th>简介</th>
          </tr>
      <%
         for(int i=0;i<barberList.size();i++){
      %>
        <tr>
           <td> <img src="<%=barberList.get(i).getB_icon()%>" /> </td>
          <td><%=barberList.get(i).getB_id() %>
          <td><%=barberList.get(i).getB_name() %></td>
          <td><%=barberList.get(i).getB_position() %></td>
          <td><%=barberList.get(i).getB_desc() %></td>
          <td>
            <a href="/Alisi2/updateBarber.jsp?id=<%=barberList.get(i).getB_id()%>&b_name=<%=barberList.get(i).getB_name()%>">修改</a>
            <a href="/Alisi2/ShowAllBarberInfo?b_id=<%=barberList.get(i).getB_id()%>&s_id=${s_id}">删除</a>
          </td>
        
        
        </tr>
      
      <%
      
         }
      %>




</body>
</html>