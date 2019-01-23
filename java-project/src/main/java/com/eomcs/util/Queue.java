package com.eomcs.util;

// 기존 기능을 활용하는 가장 쉬운 방법이 상속이다.
// 
public class Queue extends LinkedList {

  public void offer(Object value) {
    this.add(value);
  }
  
  public Object poll() {
    return this.remove(0);
  }
  
  public boolean empty() {
    return this.size == 0;
  }
}
