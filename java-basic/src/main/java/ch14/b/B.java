// 오버라이딩 규칙
package ch14.b;

public class B extends A {
  
  // 오버라이딩 규칙
  // => 메서드 시그너처(메서드명, 파라미터, 리턴 타입)를 똑 같게 해야 한다.
  // => 파라미터의 이름은 상관없다.
  //@Override void m1(int xx) {} // OK!
  
  // => 리턴 타입이 다르면 안된다.
  //@Override int m1(int a) {} // 컴파일 오류!
  
  // => 파라미터의 타입이 다르면 안된다.
  //@Override void m1(long a) {} // 컴파일 오류!
  
  // => 파라미터의 개수가 다르면 안된다.
  //@Override void m1(int a, int b) {} // 컴파일 오류!
  
  // => 접근 범위를 축소하는 것은 안된다.
  //@Override private void m1(int a) {} // 컴파일 오류!
  
  // => 접근 범위를 확대하는 것은 괜찮다.
  //@Override protected void m1(int a) {} // OK!
  @Override public void m1(int a) {} // OK!
  
  // => 접근 범위를 축소하는 것은 안된다.
  //@Override void m2(String a, int b) {} // 컴파일 오류!
  @Override public void m2(String a, int b) {} // OK!
}








