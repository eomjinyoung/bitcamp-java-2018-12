package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private Object[] list;
  private int size = 0;
  
  public ArrayList() {
    list  = new Object[DEFAULT_CAPACITY];
  }
  
  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY) 
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) {
      return (E[]) Arrays.copyOf(list, size, a.getClass());
    }
    System.arraycopy(list, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }
  
  public void add(E obj) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }
    
    list[size++] = obj;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= size) 
      return null;
    
    return (E) list[index];
  }

  public E set(int index, E obj) {
    if (index < 0 || index >= size)
      return null;
    
    @SuppressWarnings("unchecked")
    E old = (E)list[index];
    list[index] = obj;
    return old;
  }
  
  public E remove(int index) {
    if (index < 0 || index >= size)
      return null;
    
    @SuppressWarnings("unchecked")
    E old = (E)list[index];
    
    int newSize = size - 1;
    System.arraycopy(list, index + 1, list, index, newSize - index);
    list[size = newSize] = null;
    return old;
  }
  
  public int size() {
    return size;
  }
  
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    list.add("ggg");
    
    System.out.println(list.size());
    
    System.out.println(list.remove(3));
    
    System.out.println(list.size());
    
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
  }
}
