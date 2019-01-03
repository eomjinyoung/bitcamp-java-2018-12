// 리터럴(literal) - 자바 언어로 표현한 값
package ch02;

public class Test01 {
  public static void main(String[] args) {
    // 1) 정수 78을 출력하라
    // => 10진수
    System.out.println(78);
    
    // => 8진수
    System.out.println(0116);
    
    // => 16진수
    System.out.println(0x4e);
    
    // => 2진수
    System.out.println(0b01001110);
    
    // 2) 부동소수점을 출력하라.
    // => 12.345
    System.out.println(12.345);
    
    // => 1.2345 를 출력할 때 12.345가 나오게 하라
    System.out.println(1.2345e1);
    
    // => 0.12345 를 출력할 때 12.345가 나오게 하라
    System.out.println(0.12345e2);
    
    // => 123.45  를 출력할 때 12.345가 나오게 하라
    System.out.println(123.45e-1);
    
    // 3) 논리값을 출력하라
    // => 참 을 출력하라
    System.out.println(true);
    
    // => 거짓 을 출력하라
    System.out.println(false);
    
    // 4) 문자를 출력하라.
    // => 0x61 값을 출력했을 때 a가 나오게 하라.
    System.out.println((char)0x61);
    
    // => 97 값을 출력했을 때 a가 나오게 하라.
    System.out.println((char)97);
    
    // => 작은 따옴표를 사용하여 a를 출력하라.
    System.out.println('a');
    
    // 5) 문자열을 출력하라.
    // => Hello 문자열을 출력하라.
    System.out.println("Hello");
    
    // => 안녕하세요 문자열을 출력하라.
    System.out.println("안녕하세요");
   
  }
}

/*
# 리터럴
정수(integer)
  100 (10진수)
  0144 (8진수)
  0x64, 0X64 (16진수)
  0b01100100, 0B01100100 (2진수; binary)  
부동소수점(floating point)
  3.14
  31.4E-1
  314E-2
  0.314E1
  0.0314E2
논리
  true
  false
문자(결국 숫자로 표현한다)
  (char)0x41
  (char)65
  'A'
문자열
  "ABC"
  "가각간"
메모리 주소(레퍼런스; reference)
  자바에서는 임의의 메모리 주소를 표현할 방법이 없다.
  new 명령으로 메모리를 주소를 리턴 받아야 한다.

# 콘솔로 출력하는 명령어
  System.out.println(값);
 */















