// 문자 배열 출력하기
package ch22.d;

import java.io.FileWriter;

public class Test02_1 {

  public static void main(String[] args) {
    
    // FileWriter
    // => 문자 단위로 데이터를 출력하는 일을 한다.
    //
    try (FileWriter out = new FileWriter("data.txt")) {
      
      char[] chars = {'A', 'B', 'C', '홍', '길', '동', '0', '1', '2'};
      
      out.write(chars);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }

}
