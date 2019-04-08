<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>새 글</title>
</head>
<body>

<jsp:include page="/header.jsp"/>

  <h1>새 글(JSP)</h1>
  <form action='add' method='post'>
    <table border='1'>
      <tr>
        <th>내용</th>
        <td><textarea name='contents' rows='5' cols='50'></textarea></td>
      </tr>
    </table>
    <p>
      <button type='submit'>등록</button>
      <a href='list'>목록</a>
    </p>
  </form>
</body>
</html>
