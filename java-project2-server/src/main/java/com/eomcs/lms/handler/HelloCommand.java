package com.eomcs.lms.handler;

public class HelloCommand extends AbstractCommand {

  public HelloCommand() {
    this.name = "/hello";
  }

  @Override
  public void execute(Response response) {
    response.println("안녕하세요!");
  }

}
