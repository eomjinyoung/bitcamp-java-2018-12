package ch18.f;

// 자동차의 기본 기능을 갖추기 위해 AbstractCar 를 상속 받는다.
// => AbstractCar는 자동차가 가져야 할 기본 필드와 메서드를 갖고 있다.
public class Truck extends AbstractCar {
  
  // 추상 클래스로부터 상속 받은 메서드 중에서 
  // 서브 클래스가 구현하도록 맡겨진 메서드를 정의한다.
  @Override
  public void run() {
    System.out.println("덜컹 덜컹 달린다!");
  }
}
