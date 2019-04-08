<%@page import="com.eomcs.lms.domain.Board"%>
<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <title>게시물 목록</title>
</head>
<body>

<jsp:include page="/header.jsp"/>

<h1>게시물 목록(JSP2)</h1>
<p><a href='add'>새 글</a></p>
<table border='1'>
  <tr> <th>번호</th> <th>제목</th> <th>등록일</th> <th>조회수</th> </tr>
<jsp:useBean scope="request" id="list" type="java.util.List<Board>"/>
<%for (Board board : list) {%>
  <tr> 
    <td><%=board.getNo()%></td>
    <td><a href='detail?no=<%=board.getNo()%>'><%=board.getContents()%></a></td> 
    <td><%=board.getCreatedDate()%></td> 
    <td><%=board.getViewCount()%></td>
  </tr>
<%}%>
</table>
</body>
</html>







