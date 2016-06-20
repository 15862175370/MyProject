<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
		width:90%;
		font:normal 100% Arial, Helvetica, sans-serif;
		margin:0 auto;
		}
	header{
		width:100%;
		height:100px;
		background-color:#0CC;
		
	}
	nav{
		width:100%;
		height:60px;
		background-color:#FF9966;
		border:none;
		
	}
	#navtable{
		width:100%;
		height:100%;
		border: solid 1px;
		color:#FFF;
		}
	#left{
		width:30%;
		height:400px;
		/*background-color:red;*/
		float:left;
		}
	#center{
		width:50%;
		height:400px;
		/*background-color:green;*/
		float:left;
		}
	#right{
		width:20%;
		height:400px;
		/*background-color:blue;*/
		float:right;
		/*clear:both;*/
		}
	#foot{
		width:100%;
		height:100px;
		background-color:yellow;
		float:right;
		/*clear:both;*/
		}
	#nav td{
		text-align:center;
		border-bottom:solid 1px #FFF;
		
	}
	#nav td:hover{
		background-color:#fff;
		font-weight:900;
		color:#009;
		border-bottom:solid 1px #FFF;
		cursor:pointer;
		}
	/*#left div{
		width:100%;
		background-color:#333;
		color:#FFF;
		font-size:2em;
		padding-left:30px;
		padding-bottom:40px;
		padding-top:10px;
		border-bottom:solid 3px #999999;
		}
	#left div:hover{
		width:100%;
		background-color:#fff;
		color:#333;
		font-size:2em;
		padding-left:30px;
		padding-bottom:40px;
		padding-top:10px;
		border-bottom:solid 3px #999999;
		}*/s
		
	[id^="test"]{
		width:100%;
		background-color:#333;
		color:#FFF;
		font-size:2em;
		padding-left:30px;
		padding-bottom:40px;
		padding-top:10px;
		border-bottom:solid 3px #999999;
		}
	[id^="test"]:hover{
		width:100%;
		background-color:#fff;
		color:#333;
		font-size:2em;
		padding-left:30px;
		padding-bottom:40px;
		padding-top:10px;
		border-bottom:solid 3px #999999;
		}
	iframe{
		width:100%;
		height:700px;
		border:none;
		}
</style>

<script language="javascript">
	function changeweb(){
		document.getElementById("myframe").setAttribute("src","shopInfo.jsp");
	}
	function changewebbarber(){
		document.getElementById("myframe").setAttribute("src","/Alisi2/ShowAllBarberInfo?s_id=${s_id}");
	}
	function changeorder(){
		document.getElementById("myframe").setAttribute("src","/Alisi2/ShowAllOrdersServlet?s_id=${s_id}");
	}
	function checkCode(){
		document.getElementById("myframe").setAttribute("src","confirmCode.jsp");
	}
</script>
</head>
<body>
<header>
<table align="right">
   <tr>
   <td>
   
     欢迎你：${username }
   </td>
   
   
   </tr>


</table>


</header>
    <nav>
   <table id="navtable" border="0">
  <tr id="nav">
    <td width="25%" onClick="changeweb()">店面管理</td>
    <td width="25%" onClick="changeorder()">查看订单</td>
    <td width="25%" onClick="checkCode()">预约验证</td>
    <td width="25%" onClick="changewebbarber()">员工管理</td>
    
    
    
  </tr>
</table>
    </nav>
    <div id="main">
    	 <div id="left">
         	<!--<div id="test1">注册页面</div>
            <div id="test2">注册页面</div>-->
         </div>
         <div id="center">
        
         	<iframe src="web-html5.html" id="myframe"/>
         </div>
         <div id="right"></div>
    </div>
     <div id="foot"></div>
</body>
</html>