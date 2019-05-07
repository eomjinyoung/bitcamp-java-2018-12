<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>

  <jsp:include page="../header.jsp" /> 
  
  <div class="container">
  
    <h1>회원가입</h1>
    <form action='add' method='post' enctype='multipart/form-data'>
    
        <div class="row">
          <div class="col-3">
          
            <img src='${contextRootPath}/images/default.jpg' class="bit-photo img-fluid rounded-circle mx-auto d-block" alt="Responsive image"><br>
            
            <div class="input-group mb-3">
              <div class="custom-file">
                <input type="file" name='photoFile' class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">사진 선택</label>
              </div>
            </div>
            
          </div> 
          
          <div class="col">

            <div class="form-group row">
              <label for="name" class="col-sm-3 col-form-label">이름</label>
              <div class="col-sm-9"> 
                <input type="text" class="form-control" name="name" id="name"/>
              </div>
            </div>

            <div class="form-group row">
              <label for="password" class="col-sm-3 col-form-label">비밀번호</label>
              <div class="col-sm-9">
                <input type="password" class="form-control" name="password" id="password"/>
              </div>
            </div>

            <div class="form-group row">
              <label for="email" class="col-sm-3 col-form-label">ID (이메일)</label>
              <div class="col-sm-9">
                <input type="email" class="form-control" name="email" id="email"/>
              </div>
            </div>

            <div class="form-group row">
              <label for="tel" class="col-sm-3 col-form-label">전화번호</label>
              <div class="col-sm-9">
                <input type="text" class="form-control" name="tel" id="tel"/>
              </div>
            </div>

            <div class="form-group row">
              <div class="col-sm-10">
                <a class="btn btn-primary" href='./'>회원목록</a>
                <button class="btn btn-primary">등록</button>
              </div>
            </div>    
            
          </div> <!-- .bit-pro -->
          
        </div> <!-- .row -->
    
    </form>
    
  </div> <!-- .container -->
  
  <jsp:include page="../javascript.jsp"/>
</body>
</html>
