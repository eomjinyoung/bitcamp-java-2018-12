<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% // _jspService() 메서드에 안에 넣을 코드를 작성하라!
int i;
%>
<html>
<head>
<meta charset="UTF-8">
<title>ex03</title>
</head>
<%
i = 5;
%>
<body>
<h1>스크립트릿(scriptlet)과 JSP 주석<%out.print(i);%></h1>
<%-- 
JSP 주석 
- 이 태그 안에 작성한 것은 자바 클래스를 생성할 때 제거된다.

스크립트릿
- JSP 파일 안에 실행할 자바 코드를 넣은 태그
- 예)
  <%  자바 코드 %>
- 스크립트릿 안에 작성한 코드는 자바 클래스를 생성할 때 
  _jspService() 메서드 안에 작성한 순서 그대로 복사된다. 
--%>
<%
if (i < 10) {
  for (int x = 0; x < i; x++) {
    out.println("<p>" + x + "</p>");
  }
}
%>
</body>
</html>

