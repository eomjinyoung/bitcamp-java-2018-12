// 쿠키(cookie) - 쿠키 사용 범위 지정하기
package bitcamp.ex10;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex10/s21")
@SuppressWarnings("serial")
public class Servlet21 extends HttpServlet {
  
  @Override
  protected void service(
      HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    
    // 테스트 방법:
    // => http://localhost:8080/java-web/ex10/s21
    //
    
    // 쿠키 사용범위
    // => 쿠키의 사용 범위를 지정하지 않으면 쿠키를 발행한 URL 범위에 한정된다.
    //    즉 같은 URL로 요청할 때만 쿠키를 보내야 한다.
    // => 예) 
    //    쿠키를 발행한 URL : /ex10/s21
    //    쿠키를 보낼 수 있는 URL : /ex10/*
    //    쿠키를 보낼 수 없는 URL : /ex10 이외의 모든 URL
    // => HTTP 응답 프로토콜
/*
HTTP/1.1 200
Set-Cookie: v1=aaa   <---- 사용 범위가 지정되지 않았다.
Set-Cookie: v2=bbb; Path=/java-web/ex10/a  <--- 사용 범위가 /java-web/ex10/a 로 제한되었다.
Set-Cookie: v3=ccc; Path=/java-web
Content-Type: text/plain;charset=UTF-8
Content-Length: 36
Date: Wed, 03 Apr 2019 02:05:46 GMT
 
 */
    // 사용 범위를 지정하지 않은 쿠키
    Cookie c1 = new Cookie("v1", "aaa");
    
    // 사용 범위 지정
    Cookie c2 = new Cookie("v2", "bbb");
    c2.setPath("/java-web/ex10/a");
    
    Cookie c3 = new Cookie("v3", "ccc");
    c3.setPath("/java-web");
    
    // 어~ 왜 쿠키의 경로를 적을 때 웹 애플리케이션 루트(컨텍스트 루트)까지 적나요?
    // => 쿠키 경로는 서블릿 컨테이너가 사용하는 경로가 아니다.
    // => 웹 브라우저가 사용하는 경로다.
    // => 웹 브라우저에서 '/' 은 서버 루트를 의미한다.
    // => 따라서 웹 브라우저가 사용하는 경로를 지정할 때는 조심해야 한다.
    //    '/'가 서버 루트를 의미하기 때문이다.
    // => 그래서 쿠키의 경로를 지정할 때는 웹 애플리케이션 루트(컨텍스트 루트)를 정확하게 지정해야 한다.
    
    
    // 쿠키를 응답 헤더에 포함시키기
    response.addCookie(c1);
    response.addCookie(c2);
    response.addCookie(c3);
    
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("/ex10/s21 - 쿠키 보냈습니다.");
  }
}


