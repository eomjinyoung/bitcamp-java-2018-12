package algorithm.data_structure.array;

public class ArrayList {
  
  static final int DEFAULT_SIZE = 5;
  
  Object[] arr;
  int size;
  
  public ArrayList() {
    this(0);
  }
  
  public ArrayList(int capacity) {
    if (capacity > DEFAULT_SIZE)
      arr = new Object[capacity];
    else 
      arr = new Object[DEFAULT_SIZE];
  }
  
  public Object[] toArray() {
    Object[] list = new Object[this.size];
    for (int i = 0; i < this.size; i++) {
      list[i] = this.arr[i];
    }
    return list;
  }
  
  public void add(Object value) {
    if (this.size == arr.length) {
      int originSize = arr.length;
      int newSize = originSize + (originSize >> 1);
      Object[] temp = new Object[newSize];
      for (int i = 0; i < this.arr.length; i++) {
        temp[i] = this.arr[i];
      }
      arr = temp;
    }
    arr[this.size++] = value;
  }
  
  public void insert(int index, Object value) {
    // 현재 배열이 꽉 찼다면 현재 배열 크기의 50% 만큼 증가시켜라.
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 저장하지 말라.
    // 삽입할 위치의 항목부터 이후의 항목들을 뒤로 밀어라. 
    
  }
  
  public Object get(int index) {
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 null을 리턴하라. 
    return null;
  }
  
  public Object set(int index, Object value) {
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 변경하지 말라. 리턴 값은 null이다.
    return null;
  }
  
  public Object remove(int index) {
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 삭제하지 말라.
    // 삭제한 후 다음 항목을 앞으로 당긴다.
    return null;
  }
  
  public int size() {
    return this.size;
  }
}







