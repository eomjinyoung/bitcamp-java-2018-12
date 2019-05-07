<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원 목록</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>
  <jsp:include page="../header.jsp" />
  
  <div class="container">
  
    <h1>회원 목록</h1>
    
    <p>
      <a href='form' class="btn btn-primary btn-sm">회원가입</a>
    </p>
    
    <div class="bit-list">
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">이름</th>
          <th scope="col">이메일</th>
          <th scope="col">전화번호</th>
          <th scope="col">생성일</th>
        </tr>
      </thead>
      <c:forEach items="${list}" var="member">
      <tr>
        <th scope="row">${member.no}</th>
        <td><a href='${member.no}'>${member.name}</a></td>
        <td>${member.email}</td>
        <td>${member.tel}</td>
        <td>${member.registeredDate}</td>
      </tr>
      </c:forEach>
    </table>
    </div><!-- .bit-list -->
    
    <jsp:include page="../pageNavigation.jsp"/>
    
    <form class="form-inline my-2 my-lg-0 justify-content-center">
      <input name='search' value="${search}" class="form-control mr-sm-2" type="search" placeholder="검색" aria-label="검색">
      <button class="btn btn-outline-success my-2 my-sm-0">검색</button>
    </form>

  </div> <!-- .container -->
  <jsp:include page="../javascript.jsp"/>
</body>

</html>