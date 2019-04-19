// 요청 핸들러의 아규먼트 - @Cookie
package bitcamp.app1;

import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/c04_7")
public class Controller04_7 {

  // 클라이언트가 보낸 쿠키 꺼내기
  // => @CookieValue(쿠키명) 애노테이션을 request handler의 아규먼트 앞에 붙인다.
  
  // 테스트:
  //    http://.../c04_7/h1
  @GetMapping("h1") 
  @ResponseBody 
  public void handler1(
      PrintWriter out,
      HttpServletResponse response
      ) {
    // 이 메서드에서 쿠키를 클라이언트로 보낸다.
    try {
      // 쿠키의 값이 ASCII가 아니라면 URL 인코딩 해야만 데이터가 깨지지 않는다.
      // URL 인코딩을 하지 않으면 ? 문자로 변환된다.
      response.addCookie(new Cookie("name1", "홍길동"));
      response.addCookie(new Cookie(
              "name2", 
              URLEncoder.encode("홍길동", "UTF-8")
      ));
      response.addCookie(new Cookie("age", "20"));
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    out.println("send cookie!");
  }
  
  // 테스트:
  //    http://.../c04_7/h2
  @GetMapping("h2") 
  @ResponseBody 
  public void handler2(
      PrintWriter out,
      @CookieValue(value="name1", required=false) String name1,
      @CookieValue(value="name2", defaultValue="") String name2,
      @CookieValue(value="age", defaultValue="0") int age  // String ===> int 자동 변환
      ) {
    
    out.printf("name1 = %s\n", name1);
    out.printf("name2 = %s\n", name2);
    out.printf("age = %d\n", age);
  }
  

}










