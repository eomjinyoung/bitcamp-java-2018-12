<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>사진 게시판</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>

  <jsp:include page="../header.jsp" />
  
  <div class="container">
    <h1>사진 게시판</h1>
    <p>
      <a href='form' class="btn btn-primary btn-sm">새 사진</a>
    </p>
    
    <div class="bit-list">
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">강의</th>
          <th scope="col">조회수</th>
          <th scope="col">생성일</th>
        </tr>
       </thead>
       <tbody>
         <c:forEach items="${list}" var="board">
           <tr>
             <th scope="row">${board.no}</th>
             <td><a href='${board.no}'>${board.title}</a></td>
             <td>${board.lesson.title}</td>
             <td>${board.viewCount}</td>
             <td>${board.createdDate}</td>
           </tr>
         </c:forEach>
       </tbody>
    </table>
    </div><!-- .bit-list -->
    
    <jsp:include page="../pageNavigation.jsp"/>
    
    <form class="form-inline my-2 my-lg-0 justify-content-center">
      <span class="m-2">검색어</span> 
      <input name='search' value="${search}" class="form-control mr-sm-2" type="search" placeholder="검색" aria-label="검색">
      <button class="btn btn-outline-success my-2 my-sm-0">검색</button>
    </form>
    
  </div> <!-- .container -->  
  
  <jsp:include page="../javascript.jsp"/>
</body>
</html>