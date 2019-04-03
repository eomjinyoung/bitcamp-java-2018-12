// 세션(session) - 세션에서 값 꺼내기
package bitcamp.ex11;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ex11/s2")
@SuppressWarnings("serial")
public class Servlet02 extends HttpServlet {
  
  @Override
  protected void service(
      HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    
    // 테스트 방법:
    // => http://localhost:8080/java-web/ex11/s2
    //
    
    // 세션 아이디 보내기
    // => 서버로부터 세션 아이디 쿠기를 받은 웹 브라우저는 해당 서버에 요청할 때마다 
    //    세션 아이디 쿠키를 보낸다.
    // => HTTP 요청 프로토콜
/*
GET /java-web/ex11/s2 HTTP/1.1
Host: localhost:8080
...
Cookie: JSESSIONID=9909D09693CE9E0B8D23BE824313C834    <--- 서버에 세션 아이디 보낸다.
 */
    
    HttpSession session = request.getSession();
    
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    // 세션에서 값 꺼내기
    // => 웹 브라우저를 종료하면 이전에 /ex11/s1 을 실행했을 때 
    //    서버로부터 받은 세션 아이디 쿠기가 삭제된다.
    // => 그런 후에 웹 브라우저에서 이 서블릿을 요청하면
    //    getSession() 메서드를 새 세션 객체를 생성한 후 리턴한다.
    //    따라서 새 세션에는 v1 이라는 이름으로 저장된 값이 없기 때문에 
    //    null을 출력할 것이다.
    //
    out.printf("v1=%s\n", session.getAttribute("v1"));
  }
}


