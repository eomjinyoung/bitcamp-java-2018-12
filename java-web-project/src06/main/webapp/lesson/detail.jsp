<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>수업 조회</title>
</head>
<body>

  <jsp:include page="/header.jsp" />

  <h1>수업 조회(JSP2)</h1>

<jsp:useBean scope="request" id="lesson" type="com.eomcs.lms.domain.Lesson"/>
  <%
    if (lesson == null) {
  %>
  <p>해당 수업이 없습니다</p>
  <%
    } else {
  %>
  <form action='update' method='post'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input type='text' name='no' readonly value='<%=lesson.getNo()%>'></td>
      </tr>
      <tr>
        <th>수업</th>
        <td><input type='text' name='title' value='<%=lesson.getTitle()%>'></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><textarea name='contents' rows='5' cols='50'><%=lesson.getContents()%></textarea></td>
      </tr>
      <tr>
        <th>시작일</th>
        <td><input type='date' name='startDate' value='<%=lesson.getStartDate()%>'></td>
      </tr>
      <tr>
        <th>종료일</th>
        <td><input type='date' name='endDate' value='<%=lesson.getEndDate()%>'></td>
      </tr>
      <tr>
        <th>총 교육시간</th>
        <td><input type='number' name='totalHours' value='<%=lesson.getTotalHours()%>'></td>
      </tr>
      <tr>
        <th>일 교육시간</th>
        <td><input type='number' name='dayHours' value='<%=lesson.getDayHours()%>'></td>
      </tr>
    </table>
    <p>
      <a href='list'>목록</a> 
      <a href='delete?no=<%=lesson.getNo()%>'>삭제</a>
      <button type='submit'>변경</button>
    <p>
  </form>
  <%
    }
  %>
</body>
</html>
