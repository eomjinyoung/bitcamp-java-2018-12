package algorithm.data_structure.stack;

public class Stack {
  
  public static final int DEFAULT_SIZE = 5;
  
  Object[] list;
  int size;
  
  public Stack() {
    list = new Object[DEFAULT_SIZE];
  }
  
  public void push(Object value) {
    // 맨 마지막에 추가한다.
    // 배열의 크기가 작다면 확장해야 한다.
  }
  
  public Object pop() {
    // 맨 마지막 값을 꺼내 리턴한다.
    // 꺼낸 값을 배열에서 제거되어야 한다.
    
    return null;
  }
  
  public boolean empty() {
    return false;
  }
  
  public int size() {
    return this.size;
  }
}
