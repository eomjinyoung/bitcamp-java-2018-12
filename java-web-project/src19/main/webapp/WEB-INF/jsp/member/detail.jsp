<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원 상세조회</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>

  <jsp:include page="../header.jsp" /> 
   
  <div class="container">
   
    <h1>회원 상세조회</h1>
  
<c:choose>
  <c:when test="${empty member}">
    <meta http-equiv="Refresh" content="2;url=list">
    <p>해당하는 회원이 없습니다.</p>
  </c:when>
  <c:otherwise>
      <form action='update' method='post' enctype='multipart/form-data'>
      
        <div class="row">
          <div class="col-3 bit-photo">
          
            <c:if test="${empty member.photo}">
              <img src='${contextRootPath}/images/default.jpg' class="bit-photo img-fluid rounded-circle mx-auto d-block" alt="Responsive image"><br>
            </c:if>
            
            <c:if test="${not empty member.photo}">
              <a href='${contextRootPath}/upload/member/${member.photo}'  data-toggle="modal" data-target="#bit-photo-detail">
              <img src='${contextRootPath}/upload/member/${member.photo}' class="bit-photo img-fluid rounded-circle mx-auto d-block" alt="Responsive image"><br></a>
            </c:if>
            
            <div class="input-group mb-3">
              <div class="custom-file">
                <input type="file" name='photoFile' class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">사진 선택</label>
              </div>
            </div>
            
          </div> <!-- .bit-photo -->
          
          <div class="col-7">

            <div class="form-group row">
              <label for="no" class="col-sm-3 col-form-label">번호</label>
              <div class="col-sm-9">
                <input type="text" class="form-control-plaintext" name="no" id="no" value="${member.no}" readonly/>
              </div>
            </div>

            <div class="form-group row">
              <label for="name" class="col-sm-3 col-form-label">이름</label>
              <div class="col-sm-9">
                <input type="text" class="form-control" name="name" id="name" value="${member.name}" />
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
                <input type="email" class="form-control" name="email" id="email" value='${member.email}'/>
              </div>
            </div>

            <div class="form-group row">
              <label for="tel" class="col-sm-3 col-form-label">전화번호</label>
              <div class="col-sm-9">
                <input type="text" class="form-control" name="tel" id="tel" value='${member.tel}'/>
              </div>
            </div>

            <div class="form-group row">
              <label for="createdDate" class="col-sm-3 col-form-label">가입일</label>
              <div class="col-sm-9">
                <input type="text" class="form-control-plaintext" id="createdDate" value='${member.registeredDate}' readonly/>
              </div>
            </div>

            <div class="form-group row">
              <div class="col-sm-10">
                <a class="btn btn-primary" href='./'>회원목록</a>
                <a class="btn btn-primary" href='delete/${member.no}'>삭제</a>
                <button class="btn btn-primary">변경</button>
              </div>
            </div>    
            
          </div> <!-- .bit-pro -->
          
        </div> <!-- .row -->
      
    </form>
    
  </c:otherwise>
</c:choose>
  
  </div> <!-- .container -->
  
      <!-- Modal -->
  <div class="modal fade bd-example-modal-xl" id="bit-photo-detail" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
      <div class="modal-content">
        <div class="modal-body">
        
          <c:if test="${empty member.photo}">
            <img src='${contextRootPath}/images/default.jpg' class="img-thumbnail"><br>
          </c:if>
          
          <c:if test="${not empty member.photo}">
            <img src='${contextRootPath}/upload/member/${member.photo}' class="img-thumbnail"/>
          </c:if>
        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
  
  <jsp:include page="../javascript.jsp"/>
</body>
</html>
