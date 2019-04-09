package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/auth/logout")
public class LogoutServlet extends HttpServlet {
  
  @Override
  protected void doGet(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 세션을 무효화시킨다.
    request.getSession().invalidate();
    
    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    request.setAttribute("viewUrl", "redirect:" + getServletContext().getContextPath());
  }
}










