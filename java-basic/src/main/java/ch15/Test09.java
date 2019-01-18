// hash code 응용 III - key 로 String을 사용하기
package ch15;

import java.util.HashMap;

public class Test09 {
  public static void main(String[] args) {
    HashMap map = new HashMap();
    
    String k1 = new String("ok");
    String k2 = new String("no");
    String k3 = new String("haha");
    String k4 = new String("ohora");
    String k5 = new String("hul");
    
    // String을 key로 사용해보자! 
    map.put(k1, new Student("홍길동", 20, false));
    map.put(k2, new Student("임꺽정", 30, true));
    map.put(k3, new Student("유관순", 17, true));
    map.put(k4, new Student("안중근", 24, true));
    map.put(k5, new Student("윤봉길", 22, false));
    
    // key를 사용하여 값을 꺼내보자.
    String k6 = new String("haha");

    System.out.println(k3 == k6); // 비록 인스턴스는 다르지만, 
    System.out.println(k3.hashCode()); // hash code는 같다.
    System.out.println(k6.hashCode()); // hash code는 같다.
    System.out.println(k3.equals(k6)); // equals()의 비교 결과도 같다.
    
    // 비록 k3와 k6는 서로 다른 인스턴스 이지만, 
    // hashCode()의 리턴 값이 같고, equals()의 리턴 값이 true이기 때문에 
    // 두 객체는 같은 key로 간주한다.
    //
    System.out.println(map.get(k6));
    
    // 자바는 문자열에 대해 대소문자를 구분하기 때문에 "haha"와 "Haha"는 다른 객체로 취급한다.
    // 당연히 hashCode()의 리턴 값이 다르고, equals()의 리턴 값은 false 이다.
    String k7 = new String("Haha"); 
    System.out.println(map.get(k7));
  }

}







