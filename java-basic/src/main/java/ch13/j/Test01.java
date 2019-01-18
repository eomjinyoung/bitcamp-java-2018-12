// 추상 클래스: 서브 클래스에 공통 기능을 물려주는 목적으로 존재하는 클래스
package ch13.j;

public class Test01 {

  public static void main(String[] args) {
    
    Convertible c1 = new Convertible();
    DumpTruck c2 = new DumpTruck();
    
    // 그러나 추상 클래스의 인스턴스는 생성할 수 없다.
    //Car c3 = new Car(); // 컴파일 오류!
  }

}
