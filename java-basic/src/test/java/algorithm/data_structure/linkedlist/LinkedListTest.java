package algorithm.data_structure.linkedlist;

import static org.junit.Assert.*;
import org.junit.Test;

public class LinkedListTest {

  @Test
  public void testAdd() {
    LinkedList list = new LinkedList();
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(6, list.size());
  }
  
  @Test
  public void testGet() {
    LinkedList list = new LinkedList();
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(100, list.get(0));
    assertEquals(300, list.get(2));
    assertEquals(600, list.get(5));
    
    assertNull(list.get(-1));
    assertNull(list.get(6));
  }

}
