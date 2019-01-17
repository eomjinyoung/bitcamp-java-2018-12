// 다형적 변수의 필요성
package ch14.e;

public class Test01 {

  public static void main(String[] args) {
    PickupTruck c1 = new PickupTruck();
    SUV c2 = new SUV();
    
    test(c1);
    test(c2);
  }
  
  static void test(PickupTruck car) {
    System.out.println("---------------------");
    car.run();
    car.stop();
    car.run();
    car.stop();
    System.out.println("---------------------");
  }
  
  static void test(SUV car) {
    System.out.println("************************");
    car.run();
    car.stop();
    car.run();
    car.stop();
    System.out.println("************************");
  }

}
