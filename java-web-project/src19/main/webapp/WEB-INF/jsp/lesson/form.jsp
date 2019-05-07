<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>

<html>
<head>
  <title>강의등록</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>

  <jsp:include page="../header.jsp" />

  <div class="container">
  
    <h1>강의등록</h1>
    <form action='add' method='post'>
      
        <div class="form-group row">
          <label for="title" class="col-sm-2 col-form-label">수업명</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" name="title" id="title"/>
          </div>
        </div>
        
        <div class="form-group row">
          <label for="contents" class="col-sm-2 col-form-label">설명</label>
          <div class="col-sm-10">
            <textarea class="form-control" id="contents" name='contents' rows='5'></textarea>
          </div>
        </div>
         
        <div class="form-group row">
          <label for="date" class="col-sm-2 col-form-label">기간</label>
          <div class="col-sm-5">
            <p>시작일</p>
            <input class="form-control" type='date' name='startDate'/>
          </div>
          <div class="col-sm-5">
            <p>종료일</p>
            <input class="form-control" type='date' name='endDate'/>
          </div>
        </div>
         
        <div class="form-group row">
          <label for="totalHours" class="col-sm-2 col-form-label">총수업시간</label>
          <div class="col-sm-10">
            <input type="number" class="form-control" name="totalHours" id="totalHours"/>
          </div>
        </div>

        <div class="form-group row">
          <label for="dayHours" class="col-sm-2 col-form-label">일수업시간</label>
          <div class="col-sm-10">
            <input type="number" class="form-control" name="dayHours" id="dayHours"/>
          </div>
        </div>
                 
        <div class="form-group row">
          <div class="col-sm-10">
            <a class="btn btn-primary" href='./'>강의목록</a>
            <button class="btn btn-primary">등록</button>
          </div>
        </div>         
    
    </form>
    
  </div> <!-- .container -->
  
<jsp:include page="../javascript.jsp"/>
  
</body>
</html>
