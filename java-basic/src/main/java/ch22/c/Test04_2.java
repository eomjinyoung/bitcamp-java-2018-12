// 바이트 데이터를 읽어 primitive data type의 값으로 바꿔주는 데코레이터 사용하기
package ch22.c;

import java.io.FileInputStream;

public class Test04_2 {
  public static void main(String[] args) {
    
    try (FileInputStream in = new FileInputStream("data.bin");
        DataInputStream in2 = new DataInputStream(in)) {
      
      // 바이너리 데이터를 읽을 때는 저장한 순서(파일 포맷)에 맞춰 읽어야 한다.
      int no = in2.readInt();
      String name = in2.readUTF();
      int age = in2.readInt();
      
      System.out.printf("%d, %s, %d\n", no, name, age);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("읽기 완료!");
  }
}








