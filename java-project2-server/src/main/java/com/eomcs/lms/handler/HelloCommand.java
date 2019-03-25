package com.eomcs.lms.handler;

import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMapping;

@Component
public class HelloCommand {

  @RequestMapping("/hello")
  public void execute(ServletResponse response) {
    response.println("안녕하세요!");
  }

}
