package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  
  static final String REFERER_URL = "refererUrl";
  
  @Override
  protected void doGet(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // 도대체 어느 페이지에서 이리로 보냈나?
    // => 요청 헤더 Referer의 값을 세션에 보관한다.
    //    로그인을 처리할 때 해당 페이지로 리다이렉트 할 것이다.
    // => 웹 브라우저의 주소 창에 직접 URL을 지정한 경우에는 
    //    요청 헤더에 Referer가 없다. 
    // 
    HttpSession session = request.getSession();
    session.setAttribute(REFERER_URL, request.getHeader("Referer"));
    
    // 이메일 쿠키 값을 꺼내온다.
    Cookie[] cookies = request.getCookies();
    String email = "";
    if (cookies != null) {
      for (Cookie c : cookies) {
        if (c.getName().equals("email")) {
          email = c.getValue();
          break;
        }
      }
    }
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<htm>");
    out.println("<head><title>로그인</title></head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");
    out.println("<form action='login' method='post'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("  <th>이메일</th>");
    out.printf("  <td><input type='email' name='email' value='%s'></td>\n", email);
    out.println("</tr>");
    out.println("<tr>");
    out.println("  <th>암호</th>");
    out.println("  <td><input type='password' name='password'></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<input type='checkbox' name='saveEmail' value='ookok'> 이메일 저장");
    out.println("<p>");
    out.println("  <button>로그인</button>");
    out.println("</p>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
  
  @Override
  protected void doPost(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // 이메일 저장을 처리한다. 
    Cookie cookie;
    if (request.getParameter("saveEmail") != null) {
      cookie = new Cookie("email", request.getParameter("email"));
      cookie.setMaxAge(60 * 60 * 24 * 15); // 15일간 쿠키를 보관한다.
    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 기존의 쿠키를 제거한다.
    }
    response.addCookie(cookie);
    
    // 도대체 어느 페이지에서 이리로 보냈나?
    System.out.println(request.getHeader("Referer"));
    
    // Spring IoC 컨테이너에서 BoardService 객체를 꺼낸다.
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);
    
    Member member = memberService.get(
        request.getParameter("email"),
        request.getParameter("password"));
    
    if (member == null) {
      response.setHeader("Refresh", "2;url=login");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title>로그인 실패</title></head><body>");
      out.println("<h1>로그인 오류</h1>");
      out.println("<p>이메일 또는 암호가 맞지 않습니다.</p>");
      out.println("</body></html>");
      return;
    }
    
    HttpSession session = request.getSession();
    
    // 세션에 로그인 사용자의 정보를 보관한다.
    session.setAttribute("loginUser", member);
    
    // 로그인 성공하면 다시 메인 화면으로 보낸다.
    String refererUrl = (String) session.getAttribute(REFERER_URL);
    if (refererUrl == null) {
      response.sendRedirect("../");
    } else {
      response.sendRedirect(refererUrl);
    }
  }
}










