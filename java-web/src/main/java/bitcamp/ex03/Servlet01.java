// 클라이언트로 출력하기
package bitcamp.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ex03/s1")
public class Servlet01 extends GenericServlet {
  
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    
    // 출력 스트림을 꺼내기 전에 출력할 때 사용할 문자표(charset)를 지정하지 않으면 
    // 기본이 영어 문자로 간주하여 아스키 코드(ASCII)로 변환하여 출력한다.
    // 자바(Unicode2;UTF-16) ===> 출력문자(ASCII) 
    PrintWriter out = res.getWriter();
    out.println("Hello!");
    out.println("안녕하세요!");
    out.println("こんにちは");
    out.println("您好");
    out.println("مع السلامة؛ إلى اللقاء!");
  }
}







