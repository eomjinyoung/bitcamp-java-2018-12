// 바이너리 데이터 입출력 - FileOutputStream 클래스 사용법
// 
package ch22.b;

import java.io.FileOutputStream;

public class Test01_1 {
  public static void main(String[] args) {
    // 바이너리 데이터?
    // => character set(문자표) 규칙에 따라 작성한 파일이 아닌 파일.
    // => 메모장으로 편집이 불가능한 파일.
    // => 보통 전용 프로그램을 이용해서 편집하는 파일.
    // => 예) .hwp, .gif, .jpeg, .mp3, .mp4, .ppt, .xls, .doc,
    //       .class, .swf, .avi 등
    // 
    // 텍스트 데이터?
    // => 특정 character set(문자표) 규칙에 따라 작성한 파일.
    // => 메모장으로 편집 가능.
    // => 예) .txt, .c, .cpp, .java, .xml, .html, .css, .js,
    //       .py, .docx, .xlsx, .pptx 등
    //
    // Java I/O API(java.io 패키지)
    // => 바이너리 입출력 : InputStream, OutputStream
    // => 텍스트 입출력 : Reader, Writer
    //
    
    // 파일로 바이너리 데이터 출력
    // => FileOutputStream 클래스를 사용한다.
    try {
      // 1) 데이터를 출력을 담당할 객체를 생성한다.
      FileOutputStream out = new FileOutputStream("data.bin");
      
      // 2) 데이터를 출력한다.
      // => write(int) : 1바이트를 출력하는 메서드이다.
      //    파라미터의 타입이 int라고 해서 4바이트를 출력하는 것이 아니다.
      //    오직 1바이트만 출력한다.   
      out.write(2);
      out.write(40);
      out.write(100);
      out.write(101);
      out.write(102);
      out.write(127);
      out.write(0x11223344); // 00010001 00100010 00110011 01000100
      out.write(0x55667788); // 맨 끝 1바이트만 출력한다.
      
      out.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }
}








