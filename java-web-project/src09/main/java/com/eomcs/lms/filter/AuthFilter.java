package com.eomcs.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.domain.Member;

@WebFilter("/app/*")
public class AuthFilter implements Filter {
  
  FilterConfig filterConfig;
  String contextRootPath; // 예) /java-web-project
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    contextRootPath = filterConfig.getServletContext().getContextPath();
  }
  
  @Override
  public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain)
      throws IOException, ServletException {
    
    // 로그인 여부를 검사한다.
    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpResp = (HttpServletResponse) response;
    
    // /app/* URL에 대해서 적용하기 때문에 서블릿 경로를 검사해서는 안된다.
    //String servletPath = httpReq.getServletPath(); // "/app"
    String pathInfo = httpReq.getPathInfo(); // ex) "/board/list"
    
    if (pathInfo.endsWith("add")
        || pathInfo.endsWith("update")
        || pathInfo.endsWith("delete")) {
      // 로그인 되어 있어야 한다.
      Member loginUser = (Member) httpReq.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        // 클라이언트가 요청한 위치를 알 수 없기 때문에
        // 막연히 상대경로로 로그인 폼의 URL을 지정할 수 없다.
        // 절대 경로로 정확하게 지정하라.
        
        httpResp.sendRedirect(contextRootPath + "/app/auth/login");
        return;
      }
    }
    
    // 그런 후에 다음 필터나 또는 최종 목적지인 서블릿을 실행한다.
    chain.doFilter(request, response);
  }
}






