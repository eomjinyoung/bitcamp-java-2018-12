package com.eomcs.lms.servlet3;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/board3/footer")
public class FooterServlet extends HttpServlet {
  
  @Override
  protected void service(
      HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    
    PrintWriter out = response.getWriter();
    
    out.println("<footer>");
    out.println("<hr>");
    out.println("  비트캠프, <address>서울시 서초구 서초동 1327-15 비트아카데미빌딩</address>");
    out.println("</footer>");
  }


}










