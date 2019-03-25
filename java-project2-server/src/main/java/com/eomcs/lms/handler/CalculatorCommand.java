package com.eomcs.lms.handler;

import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMapping;

@Component
public class CalculatorCommand {

  @RequestMapping("/calculator")
  public void execute(ServletResponse response) {
    try {
      String[] str = response.requestString("계산식? ").split(" ");
      int a = Integer.parseInt(str[0]);
      String op = str[1];
      int b = Integer.parseInt(str[2]);
      
      switch (op) {
        case "+": response.println("결과: " + (a + b)); break;
        case "-": response.println("결과: " + (a - b)); break;
        case "*": response.println("결과: " + (a * b)); break;
        case "/": response.println("결과: " + (a / b)); break;
        case "%": response.println("결과: " + (a % b)); break;
        default: response.println("해당 연산을 지원하지 않습니다!");
      }
      
    } catch (Exception e) {
      response.println("실행 오류!");
    }
  }

}
