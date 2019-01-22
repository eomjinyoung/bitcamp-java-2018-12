// 인터페이스 구현 - 새로 추가한 규칙에 따라 클래스를 구현한다. 
package ch18.g;

public class My3 implements B {
  // 원래 B에 선언된 메서드 구현 
  @Override
  public void m1() {
    System.out.println("My3.m1()");
  }
  
  // 나중에 디폴트로 추가한 메서드
  @Override
  public void m2() {
    System.out.println("My3.m2()");
  }
}
