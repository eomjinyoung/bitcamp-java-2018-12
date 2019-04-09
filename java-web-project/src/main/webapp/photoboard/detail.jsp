<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>사진 조회</title>
</head>
<body>

  <jsp:include page="/header.jsp" />

  <h1>사진 조회(JSP2 + EL + JSTL)</h1>
<c:choose>
<c:when test="${empty board}">
  <p>해당 사진을 찾을 수 없습니다.</p>
</c:when>
<c:otherwise>
  <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input name='no' value='${board.no}' readonly></td>
      </tr>
      <tr>
        <th>제목</th>
        <td><input name='title' value='${board.title}'></td>
      </tr>
      <tr>
        <th>등록일</th>
        <td>${board.createdDate}</td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>${board.viewCount}</td>
      </tr>
      <tr>
        <th>수업</th>
        <td><select name='lessonNo'>
          <c:forEach items="${lessons}" var="lesson">
            <option value="${lesson.no}"
              ${board.lessonNo == lesson.no ? "selected" : ""}>${lesson.title}(${lesson.startDate}
              ~
              ${lesson.endDate})
            </option>
          </c:forEach>
        </select></td>
      </tr>
      <tr>
        <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td>
      </tr>
      <tr>
        <th>사진1</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진2</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진3</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진4</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진5</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진</th>
        <td>
          <c:set var="contextRootPath" value="${pageContext.servletContext.contextPath}"></c:set>
          <c:forEach items="${board.files}" var="file"> 
            <img src='${contextRootPath}/upload/photoboard/${file.filePath}' style='height: 80px'> 
          </c:forEach>
        </td>
      </tr>
    </table>

    <p>
      <a href='list'>목록</a>
      <a href='delete?no=${board.no}'>삭제</a>
      <button type='submit'>변경</button>
      <a href='../'>메인화면</a>
    <p>
  </form>
</c:otherwise>
</c:choose>
</body>
</html>