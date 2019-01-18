// Object 클래스 - 자바 최상위 클래스
package ch15;

// 클래스를 정의할 때 수퍼 클래스를 지정하지 않으면 
// 컴파일러는 자동으로 Object를 상속 받는다.
class My1 /*extends Object*/ {
  
}

public class Test01 {

  public static void main(String[] args) {
    
    // instanceof 연산자를 사용하여 해당 인스턴스가 Object 타입인지 확인해 보자.
    // instanceof 연산자?
    // => 레퍼런스가 가리키는 인스턴스가 지정한 클래스를 인스턴스 이거나 또는 조상으로 갖는지 검사한다.
    My1 obj = new My1();
    
    System.out.println(obj instanceof My1);
    //System.out.println(obj instanceof String);
    System.out.println(obj instanceof Object);
    
    // Object를 조상으로 갖는다면 당연히 Object의 메서드를 사용할 수 있을 것이다.
    System.out.println(obj.toString());
    System.out.println(obj.hashCode());
    System.out.println(obj.equals("Hello"));
    
    // 결론!
    // => 자바의 모든 클래스는 Object의 자손이다.
  }

}







