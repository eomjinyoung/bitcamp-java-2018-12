package com.eomcs.lms.controller;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
public class AuthController {

  static final String REFERER_URL = "refererUrl";

  @Autowired MemberService memberService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("/auth/form")
  public String form(
      @RequestHeader("Referer") String refererUrl,
      HttpSession session) {
    session.setAttribute(REFERER_URL, refererUrl);
    return "/auth/form.jsp";
  }
  
  @RequestMapping("/auth/login")
  public String login(
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      @RequestParam("saveEmail") String saveEmail,
      HttpSession session,
      HttpServletResponse response) throws Exception {

    // 이메일 저장을 처리한다. 
    Cookie cookie;
    if (saveEmail != null) {
      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 15); // 15일간 쿠키를 보관한다.
      
    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 기존의 쿠키를 제거한다.
    }

    // 이제 이 클래스는 including 서블릿이 아니기 때문에 
    // 프론트 컨트롤러에서 받은 response 객체를 사용하여 
    // 바로 쿠키를 추가할 수 있다.
    response.addCookie(cookie); 

    Member member = memberService.get(email, password);

    if (member == null) {
      return "/auth/fail.jsp";
    }

    session.setAttribute("loginUser", member);

    String refererUrl = (String) session.getAttribute(REFERER_URL);
    if (refererUrl == null) {      
      return "redirect:" + servletContext.getContextPath();
      
    } else {
      return "redirect:" + refererUrl;
    }
  }
  
  @RequestMapping("/auth/logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:../../";
  }
}










