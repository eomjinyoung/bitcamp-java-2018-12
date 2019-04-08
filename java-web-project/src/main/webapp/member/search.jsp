<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 검색</title>
</head>
<body>
  <jsp:include page="/header.jsp" />

  <h1>회원 검색(JSP2)</h1>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>이름</th>
      <th>이메일</th>
      <th>전화</th>
      <th>가입일</th>
    </tr>
<jsp:useBean scope="request" id="list" type="java.util.List<Member>"/>
    <%
      for (Member member : list) {
    %>
    <tr>
      <td><%=member.getNo()%></td>
      <td><a href='detail?no=<%=member.getNo()%>'><%=member.getName()%></a></td>
      <td><%=member.getEmail()%></td>
      <td><%=member.getTel()%></td>
      <td><%=member.getRegisteredDate()%></td>
    </tr>
    <%
      }
    %>
  </table>
  <p>
    <a href='list'>목록</a>
  </p>
</body>
</html>
