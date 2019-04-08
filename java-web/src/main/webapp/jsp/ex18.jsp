<%@page import="java.util.ArrayList"%>
<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ page import="bitcamp.vo.Board"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex16</title>
</head>
<body>
<h1>JSP 액션 태그 - jsp:useBean (계속)</h1>

<%-- for ( : ) 문을 제대로 사용하려면 목록에 보관된 객체의 타입이 무엇인지 
     제네릭으로 지정해야 한다.
     그러나 class에서는 제네릭을 지정할 수 없다.
     해결책?
     - type과 class를 함께써라!
     - type으로 제네릭을 표현하고 class로 객체를 생성할 클래스를 지정하라! 
      
     --%>
<jsp:useBean id="list"
    type="java.util.List<String>" 
    class="java.util.ArrayList" scope="page"/>
<%-- 자바코드로 표현해보면,
  java.util.List<String> list = 
     (java.util.List<String>) pageContext.getAttribute("list");
  if (list == null) {
    list = new java.util.ArrayList();
    pageContext.setAttribute("list", list);
  }
 --%>

<%
// type 속성에서 제레릭으로 String을 지정했기 때문에 항목의 타입을 String으로 선언할 수 있다.
for (String n : list) {
  out.println(n + "<br>");
}
%>

</body>
</html>
<%--
jsp:useBean (계속)

1) type 속성 
   - 보관소에서 꺼낸 객체의 타입을 지정할 때 사용한다.

 --%>






