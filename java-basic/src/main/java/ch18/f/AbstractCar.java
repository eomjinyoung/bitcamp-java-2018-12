// 추상 클래스를 사용할 때
package ch18.f;

public abstract class AbstractCar {
  
  String model;
  int cc;
  
  // 추상 클래스의 목적은 서브 클래스에게 공통 필드와 공통 메서드를 상속해주는 것이다. 
  public void on() {
    System.out.println("시동을 켠다.");
  }
  
  public void off() {
    System.out.println("시동을 끈다.");
  }
  
  // 상속해주는 메서드 중에서 일부는 서브 클래스가 구현하도록 강제할 수 있다.
  // => 이럴 때 사용하는 문법이 추상 메서드이다.
  public abstract void run();
}







