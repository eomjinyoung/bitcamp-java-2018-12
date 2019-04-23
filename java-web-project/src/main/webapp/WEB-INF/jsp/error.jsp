<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="refererUrl">${(empty header.Referer) ? 
  pageContext.servletContext.contextPath : header.Referer}</c:set>
<!DOCTYPE html>
<html>
<head>
<title>실행 오류</title>
<%--  
<meta http-equiv="Refresh" content="2;url=${refererUrl}">
--%>
</head>
<body>
  <jsp:include page="header.jsp" />
  <h1>${requestScope["error.title"]}</h1>
  <p>${requestScope["error.content"]}</p>
  <p><a href="${refererUrl}">이전 페이지로 가기</a></p>
  <pre>
<% 
    Exception e = (Exception) request.getAttribute("error");
    e.printStackTrace(new PrintWriter(out));
%>    
  </pre>
</body>
</html>
