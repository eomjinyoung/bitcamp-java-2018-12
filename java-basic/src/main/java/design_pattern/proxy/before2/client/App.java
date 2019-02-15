// 2단계 - C/S(Client/Server) 계산기 애플리케이션 만들기
// => 개발자는 원격 서버와 통신을 하여 Calculator 클래스를 간접적으로 사용한다.
package design_pattern.proxy.before2.client;

public class App {
  public static void main(String[] args) throws Exception {
    
    // 클라이언트 개발자가 원격에 있는 객체를 사용하기 위해서
    // 원격 서버와 통신하는 코드를 프로토콜(데이터를 주고 받는 규칙)에 맞춰 직접 작성하였다.
    // 
    CalculatorClient calcStub = new CalculatorClient();
    
    System.out.println(calcStub.plus(100, 200));
    System.out.println(calcStub.minus(100, 200));
  }
}
