<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 검색</title>
</head>
<body>
  <jsp:include page="/header.jsp" />
  <h1>사진 검색</h1>
  <p>
    <a href='add'>새 사진</a>
  </p>
  <p>
    <a href='../'>시스템 목록</a>
  </p>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>등록일</th>
      <th>조회수</th>
      <th>수업</th>
    </tr>
<jsp:useBean scope="request" id="list" type="java.util.List<PhotoBoard>"/> 
    <%for (PhotoBoard board : list) {%>
    <tr>
      <td><%=board.getNo() %></td>
      <td><a href='detail?no=<%=board.getNo()%>'><%=board.getTitle() %></a></td>
      <td><%=board.getCreatedDate() %></td>
      <td><%=board.getViewCount() %></td>
      <td><%=board.getLessonNo() %></td>
    </tr>
    <%} %>
  </table>
</body>
</html>








