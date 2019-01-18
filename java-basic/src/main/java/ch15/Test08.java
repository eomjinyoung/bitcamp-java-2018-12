// hash code 응용 II
package ch15;

import java.util.HashMap;

public class Test08 {
  public static void main(String[] args) {
    // hash 코드는 Map에서 값을 저장하기 위해 key로 사용한다.
    HashMap map = new HashMap();
    
    // Map은 값을 저장할 때 key를 이용한다.
    // => key: 값을 저장할 위치를 계산할 때 사용한다.
    // => map.put(key, value);
    //
    
    // put(Object key, Object value)
    // => key 값으로 int를 넘겨준다? 
    //    내부적으로 auto-boxing을 수행하여 Integer 객체를 만든다.
    //    그리고 그 객체를 넘겨주는 것이다.
    // => put() 메서드는 key로 넘겨받은 객체에 대해 hashCode()를 호출하여 
    //    정수 값을 얻는다.
    // => 해시 코드를 사용하여 값을 저장할 위치를 계산한다.
    // => 그런 후 그 위치에 해당하는 배열(배열로 관리한다면)에 저장한다.
    // 
    
    Integer k1 = new Integer(101);
    Integer k2 = new Integer(102);
    Integer k3 = new Integer(103);
    Integer k4 = new Integer(104);
    Integer k5 = new Integer(105);
    
    
    map.put(k1, new Student("홍길동", 20, false));
    map.put(k2, new Student("임꺽정", 30, true));
    map.put(k3, new Student("유관순", 17, true));
    map.put(k4, new Student("안중근", 24, true));
    map.put(k5, new Student("윤봉길", 22, false));
    
    // key를 사용하여 값을 꺼내보자.
    Integer k6 = new Integer(102);

    System.out.println(k2 == k6); // 비록 인스턴스는 다르지만, 
    System.out.println(k2.hashCode()); // hash code는 같다.
    System.out.println(k6.hashCode()); // hash code는 같다.
    System.out.println(k2.equals(k6)); // equals()의 비교 결과도 같다.
    // 결론!
    // => k2와 k6는 다른 객체지만, 내용물은 같다.
    
    // get(key)
    // => key 파라미터로 받은 객체에 대해 hashCode() 호출하여 정수 값을 얻는다.
    // => 그리고 정수 값을 이용하여 값이 저장된 위치를 찾는다.
    //    원래의 키와 내용물이 같은지 equals()로 한 번 더 비교한다.
    //    만약 같다면 같은 key로 간주하여 해당 값을 꺼내 리턴한다.
    //
    // 따라서 k2로 저장한 값을 k6로 꺼낼 수 있다.
    // 왜? hashCode()의 리턴 값이 같고, equals()의 리턴 값이 true이기 때문에 같은 key로 간주한다.
    // 
    System.out.println(map.get(k6));
    
    // 해당 키로 저장한 값을 찾을 수 없으면 null을 리턴한다.
    Integer k7 = new Integer(200);
    System.out.println(map.get(k7));
  }

}







