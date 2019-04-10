package com.eomcs.lms.controller;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
public class LoginController {

  static final String REFERER_URL = "refererUrl";

  @Autowired MemberService memberService;

  @RequestMapping("/auth/login")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    if (request.getMethod().equals("GET")) {
      HttpSession session = request.getSession();
      session.setAttribute(REFERER_URL, request.getHeader("Referer"));
      return "/auth/form.jsp";

    }

    ServletContext sc = request.getServletContext();
    
    // 이메일 저장을 처리한다. 
    Cookie cookie;
    if (request.getParameter("saveEmail") != null) {
      cookie = new Cookie("email", request.getParameter("email"));
      cookie.setMaxAge(60 * 60 * 24 * 15); // 15일간 쿠키를 보관한다.
      
    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 기존의 쿠키를 제거한다.
    }

    // 이제 이 클래스는 including 서블릿이 아니기 때문에 
    // 프론트 컨트롤러에서 받은 response 객체를 사용하여 
    // 바로 쿠키를 추가할 수 있다.
    response.addCookie(cookie); 

    Member member = memberService.get(
        request.getParameter("email"),
        request.getParameter("password"));

    if (member == null) {
      return "/auth/fail.jsp";
    }

    HttpSession session = request.getSession();
    session.setAttribute("loginUser", member);

    String refererUrl = (String) session.getAttribute(REFERER_URL);
    if (refererUrl == null) {      
      return "redirect:" + sc.getContextPath();
      
    } else {
      return "redirect:" + refererUrl;
    }
  }
}










