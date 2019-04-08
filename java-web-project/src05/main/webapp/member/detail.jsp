<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%
  Member member = (Member) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<title>회원 조회</title>
</head>
<body>

  <jsp:include page="/header.jsp" />

  <h1>회원 조회</h1>
  <%
    if (member == null) {
  %>
  <p>해당하는 회원이 없습니다.</p>
  <%
    } else {
  %>
  <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input type='text' name='no' readonly value='<%=member.getNo()%>'></td>
      </tr>
      <tr>
        <th>이름</th>
        <td><input type='text' name='name' value='<%=member.getName()%>'></td>
      </tr>
      <tr>
        <th>이메일</th>
        <td><input type='email' name='email' value='<%=member.getEmail()%>'></td>
      </tr>
      <tr>
        <th>암호</th>
        <td><input type='password' name='password'></td>
      </tr>

      <tr>
        <th>사진</th>
        <%
          if (member.getPhoto() == null) {
        %>
        <td><img src='../images/default.jpg' style='height: 80px'> <%
   } else {
 %>
        <td><img src='../upload/member/<%=member.getPhoto()%>' style='height: 80px'> <%
   }
 %>
          <input type='file' name='photo'></td>
      </tr>


      <tr>
        <th>전화</th>
        <td><input type='text' name='tel' value='<%=member.getTel()%>'></td>
      </tr>
      <tr>
        <th>가입일</th>
        <td><%=member.getRegisteredDate()%></td>
      </tr>
    </table>
    <%
      }
    %>

    <p>
      <a href='list'>목록</a> <a href='delete?no=<%=member.getNo()%>'>삭제</a>
      <button type='submit'>변경</button>
    <p>
  </form>
</body>
</html>