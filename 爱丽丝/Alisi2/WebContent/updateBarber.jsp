<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%        
       String b_id   = request.getParameter("id");
         out.print(b_id);
       String b_name=request.getParameter("b_name");
      /*  byte[] bs=b_name.getBytes("ISO8859-1");
       b_name=new String(bs,"utf-8"); */
        request.setAttribute("b_id", b_id);
        request.setAttribute("b_name", b_name);
       
   
   %>


  <form action="/Alisi2/UpdateBarberServlet"   method="post" enctype="multipart/form-data">
   头像： <input type="file" name="icon"> <br>
       名字：     <input type="text" name="b_name" value="${b_name }"><br>
      职位：        <input type="text" name="b_position"> <br>
     项目   ：     <input type="checkbox" name="types" value="洗剪吹" > 洗剪吹
   
       <input type="checkbox" name="types" value="烫">烫
       <input type="checkbox" name="types" value="染">染
       <input type="checkbox" name="types" value="造型">造型  <br>
   
   
   
   
   
     <table border="1">
       <tr>
         <td>
         价格
         </td>
       </tr> 
        <tr>
         <td>
              洗剪吹
         </td>
         <td>
             价格 ：    <input type="text" name="wash"><br>
         </td>
       </tr> 
        <tr>
         <td>
              烫
         </td>
         <td>
             价格 ：    <input type="text" name="tang"><br>
         </td>
       </tr> 
        <tr>
         <td>
             染
         </td>
         <td>
             价格 ：    <input type="text" name="ran"><br>
         </td>
       </tr> 
        <tr>
         <td>
             造型
         </td>
         <td>
             价格 ：    <input type="text" name="zaoxing"><br>
         </td>
       </tr> 
     </table>       
        个人简介：<br>  <textarea cols="30" rows="10" name="b_desc"></textarea></br> 
         
         <input type="submit" name="submit" value="修改">
         <input type="hidden" name="b_id" value="${b_id }">
         
         </form>







</body>
</html>