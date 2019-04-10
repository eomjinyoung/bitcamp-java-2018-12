package com.eomcs.lms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 페이지 컨트롤러의 사용 규칙 정의
// 누가 페이지 컨트롤러를 사용하는가?
// => 프론트 컨트롤러
// 
public interface PageController {
  String execute(
      HttpServletRequest request, 
      HttpServletResponse response) throws Exception;
}
