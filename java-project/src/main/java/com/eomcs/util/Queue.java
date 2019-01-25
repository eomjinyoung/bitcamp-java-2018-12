// 제네릭 적용 + Cloneable 구현
package com.eomcs.util;

// Queue가 보관하는 데이터 타입을 E 라고 가정하자.
// => E라고 가정한 상태에서 코드를 작성한다.
// 
public class Queue<E> extends LinkedList<E> implements Cloneable {

  public void offer(E value) {
    this.add(value);
  }
  
  public E poll() {
    return this.remove(0);
  }
  
  public boolean empty() {
    return this.size == 0;
  }
  
  // 복제 기능 추가
  // => 그냥 Object에서 상속 받은 clone() 메서드를 오버라이딩 하면 된다.
  // => public 으로 공개한다.
  // => 리턴 타입을 클래스 타입으로 변경한다.
  // => 클래스에 대해 복제를 허락하도록 java.lang.Cloneable 인터페이스를 구현한다.
  @SuppressWarnings("unchecked")
  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    return (Queue<E>) super.clone();
  }
}








