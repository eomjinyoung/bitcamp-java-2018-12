package algorithm.data_structure.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ArrayListTest {
  
  @Test
  public void testAdd() {
    ArrayList list = new ArrayList();
    list.add(100); // => list.add(Integer.valueOf(100)) : auto-boxing
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(6, list.size());
    
  }
}







