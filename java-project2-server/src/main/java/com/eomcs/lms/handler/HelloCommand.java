package com.eomcs.lms.handler;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMapping;

@Component
public class HelloCommand {
  
  @RequestMapping("/hello")
  public void list(ServletRequest request, ServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>hello</title></head>");
    out.println("<body><h1>Hello, world!</h1>");
    out.println("</body></html>");
  }
}










