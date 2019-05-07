<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>강의 목록</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>
  <jsp:include page="../header.jsp" />
  
  <div class="container">
    <h1>강의 목록</h1>
    <p>
      <a href='form' class="btn btn-primary btn-sm">강의등록</a>
    </p>
    
    <div class="bit-list">
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">시작일</th>
          <th scope="col">종료일</th>
          <th scope="col">총 강의시간</th>
        </tr>
       </thead>
       <tbody>
         <c:forEach items="${list}" var="lesson">
         <tr>
           <th scope="row">${lesson.no}</th>
           <td><a href='${lesson.no}'>${lesson.title}</a></td>
           <td>${lesson.startDate}</td>
           <td>${lesson.endDate}</td>
           <td>${lesson.totalHours}</td>
         </tr>
         </c:forEach>
       </tbody>
    </table>
    <div class="bit-list"><!-- .bit-list -->
    
    <jsp:include page="../pageNavigation.jsp"/>
    
  </div> <!-- .container -->
  
  <jsp:include page="../javascript.jsp"/>
</body>
</html>