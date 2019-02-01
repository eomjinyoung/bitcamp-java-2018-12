// 버퍼 사용 - 버퍼 기능을 대신 수행해주는 BufferedOutputStream 사용하기
package ch22.c;

import java.io.FileOutputStream;

public class Test02_3 {
  public static void main(String[] args) {
    
    try (FileOutputStream out = new FileOutputStream("data.bin");
        BufferedOutputStream out2 = new BufferedOutputStream(out)) {
      
      System.out.println("데이터 쓰는 중...");
      
      long start = System.currentTimeMillis();
      
      for (int i = 0; i < 1000000; i++) {
        out2.write(0x55);
      }
      out2.flush(); // 버퍼에 남아 있는 것을 방출한다.
      
      long end = System.currentTimeMillis();
      
      System.out.println(end - start);
      
      out.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }
}








