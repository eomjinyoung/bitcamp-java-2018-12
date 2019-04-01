// 서블릿 초기화 파라미터 - web.xml에서 설정하기
package bitcamp.ex06;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿이 사용할 값을 DD 설정으로 지정할 수 있다.
// => web.xml 에 지정하였다.
@SuppressWarnings("serial")
public class Servlet04 extends HttpServlet {
  
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    // 서블릿 DD 설정 값을 꺼내려면 ServletConfig 객체가 있어야 한다.
    ServletConfig config = this.getServletConfig();
    
    resp.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    out.printf("driver=%s\n", config.getInitParameter("jdbc.driver"));
    out.printf("url=%s\n", config.getInitParameter("jdbc.url"));
    out.printf("username=%s\n", config.getInitParameter("jdbc.username"));
    out.printf("password=%s\n", config.getInitParameter("jdbc.password"));
  }
}

