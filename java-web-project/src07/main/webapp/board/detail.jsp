<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>게시물 조회</title>
</head>
<body>

<jsp:include page="/header.jsp"/>

  <h1>게시물 조회(JSP2 + EL)</h1>
<jsp:useBean scope="request" id="board" type="com.eomcs.lms.domain.Board"/>  
<%if (board == null) {%>
  <p>해당 게시물이 없습니다</p>
<%} else {%>
  <form action='update' method='post'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input type='text' name='no' value='${board.no}' readonly></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><textarea name='contents' rows='3' cols='50'>${board.contents}</textarea></td>
      </tr>
      <tr>
        <th>작성일</th>
        <td>${board.createdDate}</td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>${board.viewCount}</td>
      </tr>
    </table>
    <p>
      <a href='list'>목록</a> <a href='delete?no=${board.no}'>삭제</a>
      <button type='submit'>변경</button>
    <p>
  </form>
<%}%>
</body>
</html>
