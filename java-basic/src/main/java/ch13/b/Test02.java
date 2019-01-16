// 기능 확장하기 - Score 클래스를 상속 받아 기능 추가하기
package ch13.b;

public class Test02 {
  public static void main(String[] args) {
    
    // Score2를 이용하여 수퍼 클래스의 기능을 호출할 수 있다.
    //
    Score2 s = new Score2();
    s.setName("홍길동"); // Score의 메서드
    s.setKor(100); // Score의 메서드
    s.setEng(100); // Score의 메서드
    s.setMath(100); // Score의 메서드
    s.setMusic(100); // Score2의 메서드
    s.setArt(100); // Score2의 메서드
    System.out.printf("%s: %d(%.1f)\n", s.getName(), s.getSum(), s.getAver());
  }
}
