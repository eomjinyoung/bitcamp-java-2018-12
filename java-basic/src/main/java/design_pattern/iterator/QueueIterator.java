package design_pattern.iterator;

public class QueueIterator<E> implements Iterator<E> {
  // 이 클래스는 Queue에서 값을 꺼내주는 일을 전문적으로 한다.
  // => 이런 일을 하는 객체를 "Iterator"라 부른다.
  //
  Queue<E> list;
  int index = 0;
  
  public QueueIterator(Queue<E> list) {
    this.list = list;
  }
  
  @Override
  public boolean hasNext() {
    return index < list.size();
  }

  @Override
  public E next() {
    return list.get(index++);
  }
  
}
