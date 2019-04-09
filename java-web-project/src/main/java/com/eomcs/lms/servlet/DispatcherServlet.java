package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet("/app/*")
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void service(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String pageControllerPath = request.getPathInfo();
    
    // 클라이언트가 요청한 페이지 컨트롤러를 실행한다.
    request.getRequestDispatcher(pageControllerPath)
           .include(request, response);
    
    if (request.getAttribute("error.title") != null) {
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
    
    // 페이지 컨트롤러에서 쿠키를 저장했으면 응답 헤더에 추가한다.
    @SuppressWarnings("unchecked")
    List<Cookie> cookies = (List<Cookie>) request.getAttribute("cookies");
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        response.addCookie(cookie);
      }
    }
       
    
    // ServletRequest 보관소에 저장된 view 컴포넌트 URL을 꺼낸다.
    String viewUrl = (String) request.getAttribute("viewUrl");
    
    if (viewUrl.startsWith("redirect:")) {
      response.sendRedirect(viewUrl.substring(9)); // ex) redirect:list
    } else {
      // 페이지 컨트롤러가 알려준 JSP를 실행한다.
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher(viewUrl).include(request, response);
    }
  }
}








