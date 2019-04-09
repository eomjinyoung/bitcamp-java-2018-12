package com.eomcs.lms.servlet;
import java.io.IOException;
import java.util.ArrayList;
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
    
    HttpSession session = request.getSession();
    session.setAttribute(REFERER_URL, request.getHeader("Referer"));
    
    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    request.setAttribute("viewUrl", "/auth/form.jsp");
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
      // 프론트 컨트롤러를 도입하였기 때문에 경로를 따로 설정해야 한다.
      cookie.setPath(getServletContext().getContextPath());
      System.out.println("====>");
    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 기존의 쿠키를 제거한다.
    }
    
    // 인클루딩 서블릿 쪽에서 쿠키를 추가할 수 없다.
    //response.addCookie(cookie); // 동작 안함!

    // 쿠키를 응답 헤더에 추가하는 것은 프론트 컨트롤러에게 맡긴다.
    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(cookie);
    request.setAttribute("cookies", cookies);
    
    
    // Spring IoC 컨테이너에서 BoardService 객체를 꺼낸다.
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = 
        (ApplicationContext) sc.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);
    
    Member member = memberService.get(
        request.getParameter("email"),
        request.getParameter("password"));
    
    if (member == null) {
      // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
      request.setAttribute("viewUrl", "/auth/fail.jsp");
      return;
    }
    
    HttpSession session = request.getSession();
    
    // 세션에 로그인 사용자의 정보를 보관한다.
    session.setAttribute("loginUser", member);
    
    // 로그인 성공하면 다시 메인 화면으로 보낸다.
    String refererUrl = (String) session.getAttribute(REFERER_URL);
    if (refererUrl == null) {      // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
      request.setAttribute("viewUrl", "redirect:" + getServletContext().getContextPath());
    } else {
      // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
      request.setAttribute("viewUrl", "redirect:" + refererUrl);
    }
  }
}










