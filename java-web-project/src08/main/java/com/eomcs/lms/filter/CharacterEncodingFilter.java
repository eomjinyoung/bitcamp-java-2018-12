package com.eomcs.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@WebFilter("/*")  // web.xml에 필터를 선언하였다.
public class CharacterEncodingFilter implements Filter {
  
  FilterConfig filterConfig;
  String encoding;
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    encoding = filterConfig.getInitParameter("encoding");
    if (encoding == null) {
      encoding = "UTF-8";
    }
  }
  
  @Override
  public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain)
      throws IOException, ServletException {
    
    // POST 요청으로 들어오는 경우 데이터의 인코딩을 초기화 파라미터에 지정한 값으로 설정한다.
    request.setCharacterEncoding(this.encoding);
    
    // 그런 후에 다음 필터나 또는 최종 목적지인 서블릿을 실행한다.
    chain.doFilter(request, response);
  }
}






