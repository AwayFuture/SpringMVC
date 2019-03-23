<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
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
    <h3>Success Page</h3>
    Time : ${requestScope.time}
    
    <br><br>
    
    Names : ${requestScope.names}
    <br><br>
    
     request user : ${requestScope.user}
    <br><br>
    
     Session user : ${sessionScope.user}
    <br><br>
    
    Session school : ${sessionScope.school}
    <br><br>
    
    Session age : ${sessionScope.age}
    <br><br>
    
  </body>
</html>
