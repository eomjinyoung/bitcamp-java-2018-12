<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <title>수업 목록</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>수업 목록(JSP2 + EL)</h1>
<p><a href='add'>새 수업</a></p>

<table border='1'>
  <tr><th>번호</th><th>수업</th><th>기간</th><th>총교육시간</th></tr>
<jsp:useBean scope="request" id="list" type="java.util.List<Lesson>"/>  
<%for (Lesson lesson : list) {
    pageContext.setAttribute("lesson", lesson);%>
  <tr>
    <td>${lesson.no}</td>
    <td><a href='detail?no=${lesson.no}'>${lesson.title}</a></td>
    <td>${lesson.startDate} ~ ${lesson.endDate}</td>
    <td>${lesson.totalHours}</td>
  </tr>
<%}%>
</table>
</body>
</html>







