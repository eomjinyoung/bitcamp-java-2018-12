<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextRootPath" value="${pageContext.servletContext.contextPath}"/>
<header>
  <img src="http://bitcamp.co.kr/img/logo.jpg" style="height:50px;">
<c:if test="${empty loginUser}">
  <a href='${contextRootPath}/app/auth/form'>로그인</a>
</c:if>
<c:if test="${not empty loginUser}">
  <img src='${contextRootPath}/upload/member/${loginUser.photo}' 
       style='height:20px;'>${loginUser.name}
  <a href='${contextRootPath}/app/auth/logout'>로그아웃</a> 
</c:if>
</header>



