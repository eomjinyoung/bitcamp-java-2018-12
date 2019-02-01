// String 읽기 - CharBuffer 사용하여 읽기
package ch22.d;

import java.io.FileReader;
import java.nio.CharBuffer;

public class Test03_3 {

  public static void main(String[] args) {
    
    // FileReader
    // => 문자 단위로 데이터를 읽는 일을 한다.
    //
    try (FileReader in = new FileReader("data.txt")) {
      
      CharBuffer cbuf = CharBuffer.allocate(1024);
      in.read(cbuf); // 버퍼로 데이터를 저장한다.
      cbuf.flip(); // 버퍼의 데이터를 읽기 위해 커서의 위치를 0으로 초기화시킨다.
      System.out.println(cbuf); // 버퍼의 데이터를 읽어 출력한다.
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("읽기 완료!");
  }

}
