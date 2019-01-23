package algorithm.data_structure.stack;

import static org.junit.Assert.*;
import org.junit.Test;

public class StackTest {

  @Test
  public void testPush() {
    Stack stack = new Stack();
    stack.push(100);
    stack.push(200);
    stack.push(300);
    stack.push(400);
    stack.push(500);
    stack.push(600); // 배열이 50% 확장될 것이다.
    stack.push(700);
    
    assertEquals(7, stack.size());
  }

  @Test
  public void testPop() {
    Stack stack = new Stack();
    stack.push(100);
    stack.push(200);
    stack.push(300);
    
    assertEquals(300, stack.pop());
    assertEquals(2, stack.size());
    
    assertEquals(200, stack.pop());
    assertEquals(1, stack.size());
    
    assertEquals(100, stack.pop());
    assertEquals(0, stack.size());
    
    assertEquals(null, stack.pop());
    assertEquals(0, stack.size());
  }

  @Test
  public void testEmpty() {
    Stack stack = new Stack();
    stack.push(100);
    stack.push(200);
    stack.push(300);
    
    assertEquals(300, stack.pop());
    assertFalse(stack.empty());
    
    assertEquals(200, stack.pop());
    assertFalse(stack.empty());
    
    assertEquals(100, stack.pop());
    assertTrue(stack.empty());
  }

}
