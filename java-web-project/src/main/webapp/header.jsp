<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%  
Member loginUser = (Member) session.getAttribute("loginUser");
String contextRootPath = application.getContextPath();
%>
<header>
  <img src='http://bitcamp.co.kr/img/logo.jpg' style='height:50px'>
<%if (loginUser == null) {%>
  <a href='<%=contextRootPath%>/auth/login'>로그인</a>
      
<%} else {%>
  <img src='<%=contextRootPath%>/upload/member/${loginUser.photo}' 
       style='height:20px;'>${loginUser.name}
  <a href='<%=contextRootPath%>/auth/logout'>로그아웃</a> 
<%}%>
</header>



