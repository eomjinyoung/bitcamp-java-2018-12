<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>강의 상세조회</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>
  <jsp:include page="../header.jsp" />
  
  <div class="container">
    <h1>강의 상세조회</h1>
    <c:choose>
    
      <c:when test="${empty lesson}">
        <p>해당 수업이 없습니다</p>
      </c:when>
      
      <c:otherwise>
      
      <form action='update' method='post'>
      
        <div class="form-group row">
          <label for="no" class="col-sm-2 col-form-label">번호</label>
          <div class="col-sm-10">
            <input type="text" class="form-control-plaintext" name="no" id="no" value="${lesson.no}" readonly/>
          </div>
        </div>
      
        <div class="form-group row">
          <label for="title" class="col-sm-2 col-form-label">수업명</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" name="title" id="title" value="${lesson.title}"/>
          </div>
        </div>
        
        <div class="form-group row">
          <label for="contents" class="col-sm-2 col-form-label">설명</label>
          <div class="col-sm-10">
            <textarea class="form-control" id="contents" name='contents' rows='5'>${lesson.contents}</textarea>
          </div>
        </div>
         
        <div class="form-group row">
          <label for="date" class="col-sm-2 col-form-label">기간</label>
          <div class="col-sm-5">
            <p>시작일</p>
            <input class="form-control" type='date' name='startDate' value='${lesson.startDate}'/>
          </div>
          <div class="col-sm-5">
            <p>종료일</p>
            <input class="form-control" type='date' name='endDate' value='${lesson.endDate}'/>
          </div>
        </div>
         
        <div class="form-group row">
          <label for="totalHours" class="col-sm-2 col-form-label">총수업시간</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" name="totalHours" id="totalHours" value="${lesson.totalHours}"/>
          </div>
        </div>

        <div class="form-group row">
          <label for="dayHours" class="col-sm-2 col-form-label">일수업시간</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" name="dayHours" id="dayHours" value="${lesson.dayHours}" >
          </div>
        </div>
                 
        <div class="form-group row">
          <div class="col-sm-10">
            <a class="btn btn-primary" href='./'>강의목록</a>
            <a class="btn btn-primary" href='delete/${lesson.no}'>삭제</a>
            <button class="btn btn-primary">변경</button>
          </div>
        </div>         
                 
        </form>
        
      </c:otherwise>
      
    </c:choose>
  </div> <!-- .container -->
  <jsp:include page="../javascript.jsp"/>
</body>
</html>
