// 컨텍스트 파라미터 가져오기
package bitcamp.ex06;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/ex06/s5")
public class Filter03 implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    // 필터의 DD 설정으로 지정한 파라미터 값 가져오기
    System.out.printf("ex06.Filter03 : encoding=%s\n", 
        request.getServletContext().getInitParameter("encoding"));
    
    chain.doFilter(request, response);
  }
}





