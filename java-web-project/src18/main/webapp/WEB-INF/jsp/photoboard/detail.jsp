<%@page import="com.eomcs.lms.domain.PhotoFile"%>
<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>사진 조회</title>
  <jsp:include page="../commonCss.jsp"/>
  <style>
    img.bit-photo{
      height: 80px;
    }
  </style>
</head>
<body>

  <jsp:include page="../header.jsp" />
  
  <div class="container">

    <h1>사진 조회</h1>
  
    <c:choose>
      <c:when test="${empty board}">
        <p>해당 사진을 찾을 수 없습니다.</p>
      </c:when>
      
      <c:otherwise>
      
        <form action='update' method='post' enctype='multipart/form-data'>
        
          <div class="form-group row">
            <label for="no" class="col-sm-2 col-form-label">번호</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" name="no" id="no" value='${board.no}' readonly/>
            </div>
          </div>
        
          <div class="form-group row">
            <label for="createdDate" class="col-sm-2 col-form-label">등록일</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" id="createdDate" value='${board.createdDate}' readonly/>
            </div>
          </div>
        
          <div class="form-group row">
            <label for="viewCount" class="col-sm-2 col-form-label">조회수</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" id="viewCount" value='${board.viewCount}' readonly/>
            </div>
          </div>
        
          <div class="form-group row">
            <label for="title" class="col-sm-2 col-form-label">제목</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="title" id="title" value='${board.title}'/>
            </div>
          </div>
          
          <div class="form-group row">
            <label for="title" class="col-sm-2 col-form-label">수업</label>
            <div class="col-sm-10">
              <div class="input-group mb-3">
                <select name='lessonNo' class="custom-select" id="inputGroupSelect01">
                  <c:forEach items="${lessons}" var="lesson">
                    <option value='${lesson.no}' ${(board.lessonNo == lesson.no) ? "selected" : ""}>
                            ${lesson.title}(${lesson.startDate} ~ ${lesson.endDate})</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="form-group row">
            <label for="photo-list" class="col-sm-2 col-form-label">사진</label>
            <div class="col-sm-10" id="photo-list">
              <c:forEach items="${board.files}" var="file">
                <a href='${contextRootPath}/upload/member/${member.photo}'  data-toggle="modal" data-target="#bit-photo-lesson-${file.filePath}">
                  <img class="bit-photo" src='${contextRootPath}/upload/photoboard/${file.filePath}' class="img-thumbnail"/>
                </a>
                <!-- Modal -->
                <div class="modal fade bd-example-modal-xl" id="bit-photo-lesson-${file.filePath}" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
                  <div class="modal-dialog modal-xl" role="document">
                    <div class="modal-content">
                      <div class="modal-body">
                          <img src='${contextRootPath}/upload/photoboard/${file.filePath}' class="img-thumbnail"/>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                      </div>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div> <!-- #photo-list -->
          </div> <!-- .form-group row -->
          
          <div class="form-group row">
            <div class="custom-file">
              <input name='photo' type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
              <label class="custom-file-label" for="inputGroupFile04">최소 한 개의 사진 파일을 등록해야 합니다.</label>
            </div>
          </div>
        
         <div class="form-group row">
            <div class="custom-file">
              <input name='photo' type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
              <label class="custom-file-label" for="inputGroupFile04">최소 한 개의 사진 파일을 등록해야 합니다.</label>
            </div>
          </div>        
        

         <div class="form-group row">
            <div class="custom-file">
              <input name='photo' type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
              <label class="custom-file-label" for="inputGroupFile04">최소 한 개의 사진 파일을 등록해야 합니다.</label>
            </div>
          </div>

         <div class="form-group row">
            <div class="custom-file">
              <input name='photo' type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
              <label class="custom-file-label" for="inputGroupFile04">최소 한 개의 사진 파일을 등록해야 합니다.</label>
            </div>
          </div>

         <div class="form-group row">
            <div class="custom-file">
              <input name='photo' type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
              <label class="custom-file-label" for="inputGroupFile04">최소 한 개의 사진 파일을 등록해야 합니다.</label>
            </div>
          </div>
          
          <div class="form-group row">
            <div class="col-sm-10">
              <a class="btn btn-primary" href='./'>목록</a>
              <a class="btn btn-primary" href='delete/${board.no}'>삭제</a>
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
