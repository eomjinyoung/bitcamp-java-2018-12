<%@page import="com.eomcs.lms.domain.PhotoFile"%>
<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@page import="java.util.List"%>
<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%
  PhotoBoard board = (PhotoBoard) request.getAttribute("board");
  List<Lesson> lessons = (List<Lesson>)request.getAttribute("lessons");
  List<PhotoFile> files = board.getFiles();
%>
<!DOCTYPE html>
<html>
<head>
<title>사진 조회</title>
</head>
<body>

  <jsp:include page="/header.jsp" />

  <h1>사진 조회</h1>

  <%if (board == null) { %>
  <p>해당 사진을 찾을 수 없습니다.</p>
  <%} else { %>
  <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input name='no' value='<%=board.getNo() %>' readonly></td>
      </tr>
      <tr>
        <th>제목</th>
        <td><input name='title' value='<%=board.getTitle() %>'></td>
      </tr>
      <tr>
        <th>등록일</th>
        <td><%=board.getCreatedDate() %></td>
      </tr>
      <tr>
        <th>조회수</th>
        <td><%=board.getViewCount() %></td>
      </tr>
      <tr>
        <th>수업</th>
        <td><select name='lessonNo'>

            <%for (Lesson lesson : lessons) { %>
            <option value='<%=lesson.getNo() %>'
              <%=board.getLessonNo() == lesson.getNo() ? "selected" : ""%>><%=lesson.getTitle() %>(<%=lesson.getStartDate() %>
              ~
              <%=lesson.getEndDate() %>)
            </option>
            <%} %>

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
          <%for (PhotoFile file : files) {%> 
            <img src='../upload/photoboard/<%=file.getFilePath()%>' style='height: 80px'> 
          <%} %>
        </td>
      </tr>
    </table>

    <p>
      <a href='list'>목록</a><a href='../'>전체 목록</a>
      <a href='delete?no=<%=board.getNo()%>'>삭제</a>
      <button type='submit'>변경</button>
    <p>
  </form>
  <%} %>
</body>
</html>