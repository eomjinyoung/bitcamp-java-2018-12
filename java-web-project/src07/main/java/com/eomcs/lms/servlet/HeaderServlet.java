package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.lms.domain.Member;

@SuppressWarnings("serial")
@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
  
  @Override
  protected void service(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    HttpSession session = request.getSession();
    Member loginUser = (Member) session.getAttribute("loginUser");
    String contextRootPath = getServletContext().getContextPath();
    
    PrintWriter out = response.getWriter();
    out.println("<header>");
    out.println("  <img src='http://bitcamp.co.kr/img/logo.jpg' style='height:50px'>");
    if (loginUser == null) {
      out.printf("<a href='%s/auth/login'>로그인</a>", contextRootPath);
      
    } else {
      out.printf("<img src='%s/upload/member/%s' style='height:20px;'> %s",
          contextRootPath,
          loginUser.getPhoto(), 
          loginUser.getName());
      out.printf("<a href='%s/auth/logout'>로그아웃</a>", 
          contextRootPath);
    }
    out.println("</header>");
  }
}










