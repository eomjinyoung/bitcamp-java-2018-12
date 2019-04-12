package bitcamp;

// 이 클래스는 우리가 만든 ApplicationIntializer 구현체이다.
//
public class ApplicationIntializerImpl2 implements ApplicationInitializer {
  @Override
  public void begin() {
    // 이 메서드는 ServletContainerImpl3 클래스가 호출할 것이다.
    System.out.println("오호라.... ApplicationInitializerImpl2.begin() 호출됨!");
  }
}
