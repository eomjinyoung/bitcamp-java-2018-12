package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface Command {
  default void execute() {
    // 이 인터페이스를 새로 구현하는 클래스가 
    // 과거의 규칙을 따르지 않아도 되도록 
    // 기존 규칙도 default로 선언한다.
  }
  
  default void execute(BufferedReader in, PrintWriter out) {
    // 이 규칙은 새로 추가한 규칙이다.
    // 그러나 기존 클래스에 영향을 끼치지 않기 위해 default로 선언한다.
  }
}
