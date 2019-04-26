/* 주제: 자바는 스태틱 타입 바인딩 방식이다.
*/
class test07 {
  public static void main(String[] args) {
    int a; // 정수를 저장하는 용도
    a = 100; // 정수 변수에 정수 값 저장? OK
    //a = 3.14;  // Error
    //a = "홍길동"; // Error
    a = true; // Error
  }
}
