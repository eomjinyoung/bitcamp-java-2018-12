<%@page import="com.eomcs.lms.domain.PhotoFile"%>
<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 조회</title>
</head>
<body>

  <jsp:include page="/header.jsp" />

  <h1>사진 조회(JSP2 + EL)</h1>
<jsp:useBean scope="request" id="board" type="com.eomcs.lms.domain.PhotoBoard"/>
  <%if (board == null) { %>
  <p>해당 사진을 찾을 수 없습니다.</p>
  <%} else { %>
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
<jsp:useBean scope="request" id="lessons" type="java.util.List<Lesson>"/>
            <%for (Lesson lesson : lessons) { 
                pageContext.setAttribute("lesson", lesson);%>
            <option value="${lesson.no}"
              ${board.lessonNo == lesson.no ? "selected" : ""}>${lesson.title}(${lesson.startDate}
              ~
              ${lesson.endDate})
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
<jsp:useBean scope="request" id="files" type="java.util.List<PhotoFile>"/>        
          <%for (PhotoFile file : files) {
              pageContext.setAttribute("file", file);%> 
            <img src='../upload/photoboard/${file.filePath}' style='height: 80px'> 
          <%} %>
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
  <%} %>
</body>
</html>