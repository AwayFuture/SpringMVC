<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Index</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<!-- 
  		1. 模拟修改操作
  		2. 有一个属性的值不能被修改
  		3. 表单回显，模拟表单上已经显示出从数据库中获取到了数据，然后直接修改
  	 -->
  	<form action="springmvc/testModelAttribute">
  		<input type="hidden" name="id" value="1"/>
  		<br>
  		UserName : <input type="text" name="username" value="lhx"/>
  		<br>
  		Age : <input type="text" name="age" value="21" />
  		<br>
  		<input type="submit" value="Update">
  	</form>
  
  	<a href="springmvc/testSessionAttributes">Test SessionAttributes</a>
  	<br><br>
  
  	<a href="springmvc/testMap">Test Map</a>
  	<br><br>

	<a href="springmvc/testModelAndView">Test ModelAndView</a>
  	<br><br>

  	<a href="springmvc/testServletAPI">Test ServletAPI</a>
  	<br><br>
  
  	<form action="springmvc/testPojo" method="post">
  		username : <input type="text" name="username">
  		<br>
  		password : <input type="password" name="password">
  		<br>
  		age : <input type="text" name="age">
  		<br>
  		province : <input type="text" name="address.province">
  		<br>
  		city : <input type="text" name="address.city">
  		<br>
  		<input type="submit" value = "submit">
  	</form>
  
  	<a href="springmvc/testCookieValue">Test CookieValue</a>
  	<br><br>
  
  	<a href="springmvc/testRequestHeader">Test RequestHeader</a>
  	<br><br>
  
  	<a href="springmvc/testRequestParam?username=gdufe&age=11">Test RequestParam</a>
  	<br><br>
  	
  	<a href="springmvc/testRest/1">Test Rest Get</a>
  	<br><br>
  	
  	<form action="springmvc/testRest" method="post">
  		<input type="submit" value="Test Rest Post">
  	</form>
  	<br><br>
  	
  	<form action="springmvc/testRest/1" method="post">
  		<input type="hidden" name="_method" value="POST">
  		<input type="submit" value="Test Rest Delete">
  	</form>
  	<br><br>
    	
  	<form action="springmvc/testRest/1" method="post">
  		<input type="hidden" name="_method" value="POST">
  		<input type="submit" value="Test Rest Put">
  	</form>
  	<br><br>
  
    <a href="helloworld">helloworld</a>
    <br>
    
    <a href="springmvc/testSpringMapping">testSpringMapping</a>
    <br>
    
    <a href="springmvc/testMethod">testMethod</a>
    <form action="springmvc/testMethod" method="post">
    	<input type="submit" value="submit"/>
    </form>
    
    <a href="springmvc/testParamsAndHeaders?username&age=11">testParamsAndHeaders</a>
    <br>
    
    <a href="springmvc/testAntPath/antPath/mvc">testAntPath</a>
    <br>
    
    <a href="springmvc/testPathVariable/10">testPathVariable</a>
    <br>
  </body>
</html>
