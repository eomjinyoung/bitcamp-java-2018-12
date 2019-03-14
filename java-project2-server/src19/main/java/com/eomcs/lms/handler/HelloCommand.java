package com.eomcs.lms.handler;

import com.eomcs.lms.context.Component;

@Component("/hello")
public class HelloCommand extends AbstractCommand {

  @Override
  public void execute(Response response) {
    response.println("안녕하세요!");
  }

}
