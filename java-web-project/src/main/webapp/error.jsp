<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>실행 오류</title>
<meta http-equiv="Refresh" content="2;url=${(empty header.Referer) ? 
  pageContext.servletContext.contextPath : header.Referer}">
</head>
<body>
  <jsp:include page="/header.jsp" />
  <h1>${requestScope["error.title"]}</h1>
  <p>${requestScope["error.content"]}</p>
</body>
</html>
