// 조건 연산자 => 조건 ? 표현식1 : 표현식2
//             cond(ition) ? expr(ession) : expr(ession);
// => 조건이 참이면 표현식1의 리턴 값이 놓이고, 거짓이면 표현식2의 리턴 값이 놓인다.
//
// 문장(statement)?
// => 여러 개의 연산자나 표현식으로 이루어진 한 명령이다.
// => 보통 세미콜론(;)으로 끝낸다.
// 
// 표현식?
// => 문장 중에서 리턴 값이 있는 문장이다.
// 
package ch04;

public class Test17 {
  public static void main(String[] args) {
    int age = 20;
    String result = (age >= 19) ? "성인" : "미성년";
    System.out.println(result);
    System.out.println((age >= 19) ? "성인" : "미성년");
    System.out.println((age >= 65) ? "성인" : false);
  }
}

















