<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<header></header>
    <nav></nav>
    <aside></aside>
    <section>
    
<!--表格案例：表格用于元素布局-->
  <!--  <table width="400" border="1" align="center">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="5">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
  
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
   
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
   
    <td colspan="5">&nbsp;</td>
    </tr>
</table>-->
		<form action="/day_03_homework/UpdateShopInfo" method="post" id="myform">
		<table width="500px" height="400px" border="1px" id="taba" >
        	<caption style="font-size:30px;color:#F00">店面管理</caption>
            <tr>
            	<td>头像</td> 
                <td> <img src="http://localhost:8080/Alisi2/images/first.png" /> </td>
            </tr>
        	<tr>
            	<td>店名</td>
                <td><input name="shopname" id="txtname" type="text" placeholder="输入你的店名" required></td>
            </tr>
            <tr>
            
            
            
            	<td>密码</td>
                <td><input type="password" maxlength="6" id="txtpass" name="shoppassword" pattern="[0-9]*" required/></td>
            </tr>
           <tr>
            	<td>地址</td>
                <td><input name="address" id="address" type="text" placeholder="输入你的地址" required></td>
           </tr>
            <tr>
            	<td>电话</td>
                <td><input name="phone" id="phone" type="text" placeholder="输入你的电话" required></td>
           </tr>
           <tr>
               <td>
               <input type="hidden" name="s_id" value="${s_id }">
               </td>
           
           
           
           </tr>
           
           
     
           
             <tr>
            	<td>ADDRESS</td>
                <td>
                	<select>
                    	<optgroup label="北南">
                            <option value="shandong">山东</option>
                            <option>江苏</option>
                            <option>河北</option>
                        </optgroup>
                        <optgroup label="华南">
                        <option>广西</option>
                        <option>浙江</option>
                        <option>广东</option>
                        </optgroup>
                    </select>省
                </td>
            </tr>
            
           
            <tr>
            	<td><input type="button" value="begin"></td>
                <td><input type="submit" class="btncss" value="修改" id="regist">
            </tr>
           
        </table>
        
        </form>
   	  <article></article>
	</section>
    <footer>${s_id}</footer>



</body>
</html>