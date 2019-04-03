// 세션(session)
package bitcamp.ex11;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ex11/s1")
@SuppressWarnings("serial")
public class Servlet01 extends HttpServlet {
  
  @Override
  protected void service(
      HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    
    // 테스트 방법:
    // => http://localhost:8080/java-web/ex11/s1
    //
    
    // 세션
    // => 클라이언트를 식별하는 기술이다.
    // => HTTP 프로토콜은 Stateless 방식으로 통신을 한다.
    //    즉 연결한 후 요청하고 응답을 받으면 연결을 끊는다.
    //    그래서 서버는 클라이언트가 요청할 때 마다 누구인지 알 수 없다.
    // => 이를 해결하기 위해 클라이언트가 접속하면 웹 서버는 그 클라이언트를 위한 
    //    고유 번호를 발급(쿠키 이용)한다.
    //    이 고유 번호를 '세션 아이디'라 부른다.
    // => 웹 브라우저는 세션 아이디를 보관해 두었다가 그 서버에 요청할 때 마다 
    //    세션 아이디를 보낸다. 
    //    왜? 세셔 아이디는 쿠키이다.
    // => 세션 아이디 쿠키는 유효기간을 설정하지 않았기 때문에 
    //    웹 브라우저를 종료하면 세션 아이디 쿠키는 삭제된다.
    // => 세션 아이디 쿠키의 사용 범위는 웹 애플리케이션이다.
    //    예) /java-web
    //    따라서 같은 웹 애플리케이션의 서블릿을 실행할 때는 무조건 세션 아이디를 보낸다.
    // 
    // 세션 아이디는 언제 발급하는가?
    // => 새 세션을 생성할 때 세션 아이디를 발급한다.
    // 
    // 언제 새 세션을 생성하는가?
    // => 세션이 없는 상태에서 request.getSesssion()을 호출할 때 생성한다.
    // 
/*
HTTP/1.1 200
Set-Cookie: JSESSIONID=9909D09693CE9E0B8D23BE824313C834; Path=/java-web; HttpOnly
Content-Type: text/plain;charset=UTF-8
Content-Length: 44
Date: Wed, 03 Apr 2019 02:38:40 GMT
 */
    
    // 세션 생성하기 : getSession() 호출 
    //
    // 1) 클라이언트가 세션 아이디를 쿠키로 전송
    //    - 서버에서는 해당 아이디의 세션을 찾는다.
    //    - 있으면, 그 세션을 리턴한다.
    //    - 있는데 세션의 유효 기간이 지났다면, 새로 세션을 만들어 리턴한다.
    //    - 없다면, 새로 세션을 만들어 리턴한다. 
    // 2) 클라이언트가 세션 아이디를 보내지 않은 경우 
    //    - 서버는 새 세션을 만들어 리턴한다.
    // 
    // 항상 새 세션을 만들면, 응답할 때 새 세션의 아이디를 쿠키로 보낸다.
    //
    HttpSession session = request.getSession();
    
    // 세션에 데이터 보관하기
    session.setAttribute("v1", "aaa");
    
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("/ex11/s1 - 세션을 생성하였습니다!");
  }
}


