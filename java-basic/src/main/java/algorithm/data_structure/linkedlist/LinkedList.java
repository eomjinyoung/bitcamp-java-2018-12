package algorithm.data_structure.linkedlist;

public class LinkedList {
  Node head;
  Node tail;
  int size;
  
  public LinkedList() {
    head = new Node();
    tail = head;
    size = 0;
  }
  
  // LinkedList는 add() 할 때 마다 노드를 만들어 값을 저장하기 때문에 
  // ArrayList 처럼 한 번에 큰 메모리를 준비할 필요가 없다.
  // => 그러나 값 이외에 다음 노드와 이전 노드의 주소를 담기 위해 추가로 메모리를 사용한다.
  //
  public void add(Object value) {
    tail.value = value;
    
    // 새 노드를 준비한다.
    Node node = new Node();
    
    // 마지막 노드의 다음으로 새 노드를 가리키게 한다.
    tail.next = node;
    
    // 새 노드의 이전으로 마지막 노드를 가리키게 한다.
    node.prev = tail;
    
    // tail이 새로 추가된 노드를 가리키게 한다.
    tail = node;
    
    // 항목 개수를 증가시킨다.
    size++;
  }
  
  public int size() {
    return size;
  }
  
  public Object get(int index) {
    if (index < 0 || index >= size)
      return null;
    
    Node cursor = head;
    
    // 해당 인덱스로 이동한다.
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    
    // cursor가 가리키는 노드의 주소를 리턴?
    // => 노드의 값을 리턴
    return cursor.value;
  }
}







