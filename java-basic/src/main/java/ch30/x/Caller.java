package ch30.x;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Caller {
  @Autowired X x;
  @Autowired Y y;
  @Autowired Z z;
  
  @Override
  public String toString() {
    return "Caller [x=" + x + ", y=" + y + ", z=" + z + "]";
  }

  public void test() {
    System.out.println("Caller.test() 시작");
    x.m1();
    y.m1();
    z.m2();
    System.out.println("Caller.test() 끝");
  }
}
