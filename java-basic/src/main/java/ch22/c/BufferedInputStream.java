package ch22.c;

import java.io.IOException;
import java.io.InputStream;

// InputStream에 기능을 덧붙이는 플러그인 역할을 수행하는 클래스이다.
// => 이런 클래스를 데코레이터(decorator)라 한다.
// => 데코레이터는 기능을 덧붙이는 대상 클래스와 같은 조상을 가져야 한다.
//    그리고 생성자에게 대상 객체 주소를 받아야 한다.
//    작업을 수행할 때 대상 객체를 사용한다.
//    그리고 자신만의 기능을 덧붙인다.
public class BufferedInputStream extends InputStream {
  
  InputStream in;
  byte[] buf = new byte[1024];
  int size = 0;
  int cursor = 0;
  
  public BufferedInputStream(InputStream in) {
    this.in = in;
  }
  
  public int read() throws IOException {
    if (cursor >= size) {
      size = in.read(buf);
      if (size == -1)
        return -1;
      cursor = 0;
    }
    
    // 바이트의 값을 온전히 4바이트 int 값으로 변환하기 위해
    // 0x000000ff 값을 & 비트 연산한다.
    // => 0xff 상수 값은 0x000000ff 를 의미한다.
    return buf[cursor++] & 0xff;
  }
  
}






