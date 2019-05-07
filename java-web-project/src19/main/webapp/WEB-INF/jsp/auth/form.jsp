<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container">
<h1>로그인(JSP)</h1>
<form action='login' method='post'>
  <div class="form-group">
    <label for="email">이메일</label>
    <input type="email" class="form-control" id="email" name="email"
           value='${cookie.email.value}'
           aria-describedby="emailHelp" placeholder="이메일을 입력하세요.">
    <small id="emailHelp" 
           class="form-text text-muted">당신의 이메일을 다른 사이트와 결코 공유하지 않습니다.</small>
  </div>
  <div class="form-group">
    <label for="password">암호</label>
    <input type="password" class="form-control" id="password" name="password" 
           placeholder="암호를 입력하세요.">
  </div>
  <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="saveEmail"
           name="saveEmail">
    <label class="form-check-label" for="saveEmail">이메일 저장</label>
  </div>
  <button class="btn btn-primary">로그인</button>
</form>
</div><!-- .container -->

<jsp:include page="../javascript.jsp"/>
</body>
</html>
