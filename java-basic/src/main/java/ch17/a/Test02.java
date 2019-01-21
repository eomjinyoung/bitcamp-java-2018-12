// 추상 메서드 상속
package ch17.a;

abstract class My1 extends A {
  // A의 추상 메서드인 m2()를 구현하지 않으면 
  // My1도 추상 클래스가 되어야 한다.
  // 추상 메서드가 자기 것이든 상속 받은 것이든 상관없다.
  // 있으면 구현하든가 아니면 추상 클래스로 선언하든가 해야 한다.
}

class My2 extends A {

  @Override
  public void m2(String name) {
    // 이렇게 상속 받은 추상 메서드를 구현하게 되면 
    // 해당 클래스는 일반 클래스가 될 수 있다.
    System.out.println("My2.m2() - " + name);
  }
  
  public void m3() {
    System.out.println("Hello!");
  }
}

// 추상 클래스 <==> 일반 클래스(concrete class)

public class Test02 {

  public static void main(String[] args) {
    
    //A obj = new A(); // 컴파일 오류! 추상 클래스의 인스턴스는 생성할 수 없다.
    //My1 obj2 = new My1(); // 컴파일 오류! 추상 클래스의 인스턴스는 생성할 수 없다.
    
    My2 obj3 = new My2(); // OK!
    obj3.m2("홍길동");
    
    A obj4 = new My2(); 
    
    // obj4는 A 클래스의 레퍼런스이지만,
    // 실제 obj4가 가리키는 인스턴스는 My2이다.
    // 만약 obj4가 호출하는 메서드를 My2가 오버라이딩 했다면,
    // 그 오버라이딩 한 메서드가 호출된다.
    obj4.m2("임꺽정");

    // 주의!
    // 오버라이딩 메서드를 호출해 준다고 해서,
    // A 클래스의 없는 메서드 호출을 허락하는 것은 아니다.
    //obj4.m3(); // 컴파일 오류! obj4는 A 타입이고 A 타입(클래스)에는 m3()가 없다.
    
    ((My2)obj4).m3(); // obj4가 가리키는 객체가 어떤 타입인지 컴파일러에게 알려주면
                      // 그 클래스의 메서드를 호출할 수 있다.
    
  }

}











