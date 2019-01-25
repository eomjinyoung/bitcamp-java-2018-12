// 데이터 관리 객체(ArrayList, LinkedList 등)를 사용하는 규칙을 정의하기
package com.eomcs.util;

public interface List<E> {
  Object[] toArray();
  <T> T[] toArray(T[] a);
  void add(E obj);
  E get(int index);
  E set(int index, E obj);
  E remove(int index);
  int size();
}
