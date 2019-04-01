// 인클루딩(including) - 다른 서블릿의 작업을 포함시키기
package bitcamp.ex07;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex07/s11")
@SuppressWarnings("serial")
public class Servlet11 extends HttpServlet {
  
  @Override
  protected void service(
      HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    
    // 테스트 방법:
    // => http://localhost:8080/java-web/ex07/s11?a=100&b=200&op=%2b
    // => http://localhost:8080/java-web/ex07/s11?a=100&b=200&op=-
    // => http://localhost:8080/java-web/ex07/s11?a=100&b=200&op=*
    //
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("계산 결과:");
    out.println("---------------------------------------");
    String op = request.getParameter("op");
    
    RequestDispatcher 요청배달자 = null; 
    
    if (op.equals("+")) {
      요청배달자 = request.getRequestDispatcher("/ex07/s11_plus");
    } else if (op.contentEquals("-")) {
      요청배달자 = request.getRequestDispatcher("/ex07/s11_minus");
    } else {
      요청배달자 = request.getRequestDispatcher("/ex07/s11_error");
    }
    
    // 다른 서블릿을 실행시킨다. 실행이 완료되면 되돌아 온다.
    // => forward()는 다른 서블릿으로 실행을 위임한 후 되돌아 오지 않지만,
    //    include()는 다른 서블릿으로 실행을 위임한 후 되돌아 온다.
    요청배달자.include(request, response);
    
    out.println("---------------------------------------");
  }
}

