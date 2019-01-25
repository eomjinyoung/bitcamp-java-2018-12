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
  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    //return (Queue<E>) super.clone();
    // Object에서 상속 받은 clone()은 
    // 인스턴스 필드의 값만 복제한다.
    // 인스턴스 필드가 가리키는 다른 인스턴스는 복제하지 않는다.
    // 예를 들어 Stack의 list 배열 레퍼런스가 가리키는 배열 인스턴스는 복제하지 않는다.
    // 배열 인스턴스까지 복제(deep clone)하려면 개발자가 직접 코드를 작성해야 한다.
    //
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < this.size(); i++) {
      temp.add(this.get(i));
    }
    return temp;
  }
}








