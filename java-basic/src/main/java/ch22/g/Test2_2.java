// Serialize - private 인스턴스 필드의 값을 읽어서 채우기 : getter/setter 있는 경우
package ch22.g;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Test2_2 {
  public static void main(String[] args) {
    
    try (ObjectInputStream in = new ObjectInputStream(
          new FileInputStream("score2.data"))) {
      
      // deserialize 할 때 
      // private 필드의 값도 데이터를 읽어 저장한다.
      // 생성자나 setter를 호출하지 않는다.
      // 인스턴스를 생성한 후 직접 인스턴스의 필드 값을 저장한다.
      Score2 score = (Score2) in.readObject();
      System.out.println(score);
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
