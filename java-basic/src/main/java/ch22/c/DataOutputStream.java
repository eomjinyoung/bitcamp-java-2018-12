package ch22.c;

import java.io.IOException;
import java.io.OutputStream;

// primitive type의 데이터를 출력하는 플러그인 객체
// => OutputStream의 데코레이터이다.
public class DataOutputStream extends OutputStream {

  OutputStream out;
  
  public DataOutputStream(OutputStream out) {
    this.out = out;
  }
  
  
  @Override
  public void write(int b) throws IOException {
    out.write(b);
  }
  
  public void writeShort(short value) throws IOException {
    out.write(value >> 8);
    out.write(value);
  }
  
  public void writeInt(int value) throws IOException {
    out.write(value >> 24);
    out.write(value >> 16);
    out.write(value >> 8);
    out.write(value);
  }
  
  public void writeUTF(String value) throws IOException {
    // 문자열을 바이트 배열로 출력하는 형식
    // => 바이트수(2byte) + 문자열의 바이트 배열
    byte[] bytes = value.getBytes("UTF-8");
    out.write(bytes.length >> 8);
    out.write(bytes.length);
    out.write(bytes);
  }
  
  @Override
  public void flush() throws IOException {
    out.flush();
  }
  
}







