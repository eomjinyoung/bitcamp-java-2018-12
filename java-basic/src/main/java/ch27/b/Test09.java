// reflection API - 클래스가 상속 받은 수퍼 클래스 알아내기
package ch27.b;

public class Test09 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = String.class;
    
    Class<?>[] interfaces = clazz.getInterfaces();
    for (Class<?> c : interfaces) {
      System.out.println(c.getName());
    }
  }

}





