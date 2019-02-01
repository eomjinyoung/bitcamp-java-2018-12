// 문자 데이터 읽기
package ch22.d;

import java.io.FileReader;

public class Test01_2 {

  public static void main(String[] args) {
    
    // FileReader
    // => 문자 단위로 데이터를 읽는 일을 한다.
    //
    try (FileReader in = new FileReader("data.txt")) {
      
      // UTF-8 코드 값을 읽어서 UTF-16 코드 값으로 변환한 다음에 리턴한다.
      //
      // read()의 리턴 값은 UTF-16으로 바꾼 문자 코드 값이다.
      //
      // 0x41(UTF-8) ==> 0x0041(UTF-16)
      System.out.println(Integer.toHexString(in.read()));
      
      // 0x42(UTF-8) ==> 0x0042(UTF-16)
      System.out.println(Integer.toHexString(in.read()));
      
      // 0x43(UTF-8) ==> 0x0043(UTF-16)
      System.out.println(Integer.toHexString(in.read()));
      
      // 0xeab080(UTF-8) ==> 0xac00(UTF-16)
      System.out.println(Integer.toHexString(in.read()));
      
      // 0xeab081(UTF-8) ==> 0xac01(UTF-16)
      System.out.println(Integer.toHexString(in.read()));
      
      // 0xeab084(UTF-8) ==> 0xac04(UTF-16)
      System.out.println(Integer.toHexString(in.read()));
      
      // JVM을 실행할 때 다음 옵션을 지정하지 않으면 
      //   -Dfile.encoding=문자표
      // JVM은 OS의 기본 문자표라고 가정하고 파일을 읽는다.
      // 
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("읽기 완료!");
  }

}
