// 리프래시 - 클라이언트에게 다른 URL을 요청하라는 명령
package bitcamp.ex08;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex08/s1")
@SuppressWarnings("serial")
public class Servlet01 extends HttpServlet {
  
  @Override
  protected void service(
      HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    
    // 테스트 방법:
    // => http://localhost:8080/java-web/ex08/s1
    //
    // 리프래시
    // => 서버로부터 응답을 받고 내용을 출력한 후 특정 URL을 자동으로 요청하도록 만들 수 있다.
    // => 보통 웹 페이지를 자동으로 이동시키고 싶을 때 사용한다.
    // => 예1: 로그인 후 메인페이지로 자동 이동
    //    예2: 메일을 전송한 후 메일 목록 페이지로 자동 이동
    //    예3: 게시글 등록한 후 게시글 목록으로 자동 이동 
    //    예4: 결제 완료 후 결제 상태 페이지로 자동 이동 
    // 
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("안녕하세요! - /ex08/s1");
    
    // 응답 헤더에 Refresh 정보를 추가한다.
    // 
    // 위에서 벌써 클라이언트에게 응답을 했는데 어떻게 응답 헤더를 출력할 수 있나요?
    // => 잊지 말자! out.println()이 출력한 것은 출력스트림 버퍼에 보관되어 있다.
    //    따라서 아직 클라이언트에게 응답한 상태가 아니다.
    //    그래서 다음과 같이 출력을 한 후에 응답 헤더 값을 추가하거나 변경할 수 있는 것이다.
    //    메서드 호출이 완료될 때 비로소 클라이언트로 응답헤더와 
    //    버퍼에 저장된 message-body가 출력된다.
    // 
    // 만약 out.println()/out.printf()/out.print() 등에서 출력한 내용이 
    // 버퍼를 꽉 채웠다면 어떻게 하나요?
    // => 그러면 자동으로 클라이언트에게 응답한다.
    //    따라서 일단 클라이언트에게 응답을 하면 다음의 코드는 적용되지 않는다.
    //    즉 응답을 완료한 후에 헤더 값을 변경하거나 바꿀 수 없다.
    //    소용이 없다.
    // 
    
    // 다음은 일부러 버퍼를 채우는 코드이다. 
    // 버퍼가 꽉차면 자동으로 출력하는 것을 확인해보자!
    for (int i = 0; i < 200; i++) {
      // 약 50 바이트씩 100번 출력하면 아직 버퍼에 차지 않았기 때문에 
      // 클라이언트로 출력되지 않는다. 
      // 따라서 반복문 아래에 있는 응답 헤더 설정이 유효하다.
      // 그러나 200번 출력하면 8KB 버퍼가 꽉 차기 때문에 
      // 반복만 다음에 헤더를 설정하기 전에 이미 버퍼 내용이 출력된다.
      // 즉 응답이 완료된다.
      // 응답을 완료한 다음에 응답 헤더의 값을 변경하거나 추가해봐야 소용없다. 
      out.println(i + "  ===> 1234567890123456789012345678901234567890");
    }
    
    response.setHeader("Refresh", "3;url=s100");
  }
}

