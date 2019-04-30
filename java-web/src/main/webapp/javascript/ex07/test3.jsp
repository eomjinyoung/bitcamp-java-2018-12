<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test3</title>
</head>
<body>
<h1>test3.jsp 실행!</h1>
</body>
</html>
<%
response.setHeader("Access-Control-Allow-Origin", "*");

// 일부러 응답을 지연시킨다.
Thread.currentThread().sleep(10000);
%>









