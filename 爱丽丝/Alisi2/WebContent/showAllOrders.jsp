<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.*"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
     <%
        List<Entity> entityList=(List<Entity>)request.getAttribute("entityList");
     
     %>
      <%
         EntityPage entityPage=(EntityPage)request.getAttribute("userPage");
         String username=(String)session.getAttribute("username");
     
     %>
   
  
   
   
   <hr>
   
       <table border=1 width="600px">
             
             <% for(int i=0;i<entityPage.getPageUers().size();i++) {
             
                 Entity entity=entityPage.getPageUers().get(i);
             
             %>
            <tr>
          <td>订单编号<%=entity.getO_id() %> </td>
          <td>预约人手机号<%=entity.getPhone() %></td>
          <td>预约时间<%=entity.getTime2() %></td>
          <td>预约状态<%=entity.getState()%></td>
        <%--   <td>
            <a href="/day_03_homework/showAllUserServlet?id=<%=user.getId()%>">修改</a>
            <a href="/day_03_homework/showAllUserServlet?id=<%=user.getId()%>">删除</a>
          </td> --%>
        
        
        </tr>
      
      <%
      
         }
      %>
      <tr align="center">
 		<td colspan="6">
 		    当前<%=entityPage.getCurrentPage()%>页 共<%=entityPage.getTotalPage() %>页
 			<% if(entityPage.getCurrentPage() == 1){ %>
 				上一页
 			<%}else{%>
	 			<a href="<%=request.getContextPath()%>/ShowAllOrdersServlet?currentPage=<%=entityPage.getCurrentPage() - 1%>&s_id=${s_id}">上一页</a> 
	 		<%} %>
	 		
	 		<% if(entityPage.getTotalPage() <= entityPage.getCurrentPage()){ %>
 				下一页
 			<%}else{%>
	 			<a href="<%=request.getContextPath()%>/ShowAllOrdersServlet?currentPage=<%=entityPage.getCurrentPage() + 1%>&s_id=${s_id}">下一页</a>
	 		<%} %>
 		</td>
 	</tr>
       </table>
   
   
     

</body>
</html>