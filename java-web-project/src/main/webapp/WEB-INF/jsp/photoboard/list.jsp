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
      <a href='form' class="btn btn-primary btn-sm">사진게시판등록</a>
    </p>
    
    <table class="table table-hover">
      <thead class="thead-dark">
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">강의제목</th>
          <th scope="col">조회수</th>
          <th scope="col">생성일</th>
        </tr>
       </thead>
       <tbody>
         <c:forEach items="${list}" var="board">
           <tr>
             <th scope="row">${board.no}</th>
             <td><a href='${board.no}' class="alert-link">${board.title}</a></td>
             <td>${board.lessonNo}</td>
             <td>${board.viewCount}</td>
             <td>${board.createdDate}</td>
           </tr>
         </c:forEach>
       </tbody>
    </table>
    
    <form action='search' class="form-inline my-2 my-lg-0 justify-content-center">
      <span class="m-2">수업번호</span><input name='lessonNo' class="form-control mr-sm-2" type="search" placeholder="검색" aria-label="검색">
      <span class="m-2">검색어</span> <input name='keyword' class="form-control mr-sm-2" type="search" placeholder="검색" aria-label="검색">
      <button class="btn btn-outline-success my-2 my-sm-0">검색</button>
    </form>
    
  </div> <!-- .container -->  
  
  <jsp:include page="../javascript.jsp"/>
</body>
</html>