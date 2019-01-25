package design_pattern.iterator;

public class StackIterator<E> implements Iterator<E> {
  // 이 클래스는 Stack의 값을 꺼내는 일을 전문적으로 한다.
  // => 이런 일을 하는 객체를 "Iterator"라 부른다.
  //
  Stack<E> stack;
  int index = 0;
  
  public StackIterator(Stack<E> stack) {
    this.stack = stack;
  }
  
  @Override
  public boolean hasNext() {
    return index < stack.size();
  }

  @SuppressWarnings("unchecked")
  @Override
  public E next() {
    int lastIndex = stack.size - 1;
    return (E) stack.list[lastIndex - (index++)];
  }
  
}







