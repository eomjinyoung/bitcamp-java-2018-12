<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시물 조회</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container">
  <h1>게시물 조회</h1>
<c:choose>
<c:when test="${empty board}">
  <p>해당 게시물이 없습니다</p>
</c:when>
<c:otherwise>
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
      <a href='.'>목록</a> <a href='delete/${board.no}'>삭제</a>
      <button type='submit'>변경</button>
    <p>
  </form>
</c:otherwise>
</c:choose>

</div><!-- .container -->

<jsp:include page="../javascript.jsp"/>
</body>
</html>






