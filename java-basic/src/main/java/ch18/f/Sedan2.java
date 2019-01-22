// 인터페이스 구현
package ch18.f;

// 사용하는 쪽에서 일관된 방식으로 메서드를 호출할 수 있도록 
// 규칙에 따라 클래스를 작성할 때 인터페이스를 구현한다.
public class Sedan2 implements CarSpec {
  
  // 인터페이스에 선언된대로 동작할 수 있도록 추상 메서드를 구현한다.
  @Override
  public void run() {
    System.out.println("달린다!");
  }
}
