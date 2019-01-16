// Wrapper 클래스
package ch11;

import java.util.Date;

public class Test09 {
  public static void main(String[] args) {
    // 자바는 primitive type의 값을 좀 더 정교하게 제어할 수 있도록 
    // 자바 원시 타입의 각각에 대응하는 클래스를 제공한다.
    // 이 클래스를 "Wrapper 클래스"라 부른다.
    //
    // byte ==> Byte 클래스 (java.lang 패키지 소속이다)
    // short ==> Short 클래스
    // int ==> Integer 클래스
    // long ==> Long 클래스
    // float ==> Float 클래스
    // double ==> Double 클래스
    // boolean ==> Boolean 클래스
    // char ==> Character 클래스 
    //
    
    // wrapper 클래스의 생성자가 deprecated 상태이다. 
    // 가능한 생성자를 사용하여 인스턴스를 생성하지 말라!
    // deprecated(비난받는)? 사용하지 않는 것이 좋다고 결정되었고, 가까운 장래에 제거될 것이라는 의미.
    // 
    //Integer i1 = new Integer(100); // 가능한 사용하지 말자!
    Integer i1 = Integer.valueOf(100); // OK!

    //Character c1 = new Character('가');
    Character c1 = Character.valueOf('가');
    
    // Wrapper 클래스는 해당 값을 다루는 다양한 메서드를 제공한다.
    byte b = i1.byteValue(); // int를 byte로 변환하여 리턴하는 메서드
    String s = i1.toString(); // int를 String 인스턴스로 리턴하는 메서드
    String str = Integer.toHexString(200); // int 값을 16진수로 변환한 후 문자열로 리턴. 
    
    // Wrapper 클래스의 가장 큰 목적!
    // => primitive 값을 포함하여 모든 값을 쉽게 주고 받기 위함이다.
    
    // Wrapper 클래스를 사용하지 않으면 다음과 같이 각 타입의 값을 처리할 메서드를 
    // 여러 개 만들어야 한다.
    printInt(100);
    printFloat(3.14f);
    // printInt("Hello"); // 컴파일 오류!
    String ss = "Hello";
    //printFloat(ss); // 파라미터 변수가 값을 요구하는 primitive 타입의 변수이다.
                    // 인스턴스의 주소를 넘길 수 없다.
    
    // 그런데 Wrapper 클래스를 사용하면 primitive도 인스턴스로 다룰 수 있기 때문에
    // primitive 데이터를 다루는 메서드를 여러 개 만들 필요가 없다.
    Integer x = Integer.valueOf(1000);
    Float y = Float.valueOf(3.14f);
    String z = "Hello";
    Date today = new Date();
    
    printObject(x); // primitive 값을 인스턴스에 담아서 넘길 수 있다. 
    printObject(y); // 이것이 Wrapper 클래스가 필요한 이유이다.
    printObject(z);
    printObject(today);
    
  }
  
  static void printInt(int i) {
    System.out.println("값 => " + i);
  }
  
  static void printFloat(float f) {
    System.out.println("값 => " + f);
  }
  
  static void printObject(Object obj) {
    System.out.println("값 => " + obj.toString());
  }
}
















