// 예외처리 - 애플리케이션 예외 처리
package ch21.a;

import java.util.ArrayList;

public class Test03 {

  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("홍길동");
    list.add("임꺽정");
    list.add("유관순");
    
    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    
    // 유효한 인덱스가 아니면 get() 메서드에서 예외를 발생시킨다.
    // => Exception 계열인 IndexOutOfBoundsException가 발생한다.
    // => 즉 애플리케이션 예외가 발생한다.
    // => 애플리케이션 예외를 처리하지 않으면 JVM에게 그대로 보고가 된다.
    //    JVM은 시스템 예외이든 애플리케이션 예외이든 상관없이 예외를 보고 받으면 
    //    예외가 발생할 때까지 호출된 메서드의 정보를 출력한 후 JVM을 즉시 종료한다.
    //
    System.out.println(list.get(3));
    
    // 위 코드에서 애플리케이션 예외가 발생하여 다음 코드를 실행되지 않는다.
    // 해결책? Test04.java 를 보라!
    System.out.println("프로그램 종료!");
    
    
  }
}
