// 클래스 사용 전 II
package ch07;

public class Test02 {
  public static void main(String[] args) {
    // 학생 정보를 출력하는 명령어를 별도의 블록으로 뺐다.
    // 성적을 출력하는 명령어를 별도의 블록으로 빼는 이유는 유지보수를 쉽게 하기 위함이다.
    // 출력 형식을 바꾸고 싶으면 그 블록으로 가서 변경하면 된다.
    // 그런데 출력 명령을 별로로 빼면 그 명령어 블록을 실행하기 위해서는 성적 값을 전달해야 한다.
    // 즉 파라미터를 선언해야 한다.
    String name = "홍길동";
    int kor = 100;
    int eng = 100;
    int math = 100;
    
    // 별도로 뺀 명령어 블록(메서드)를 사용해(호출)보자!
    printScore(name, kor, eng, math);
    
    
  }
  
  static void printScore(String name, int kor, int eng, int math) {
    int sum = kor + eng + math;
    float aver = sum / 3f;
    System.out.printf("%s: %d, %d, %d, %d, %f\n", name, kor, eng, math, sum, aver);
  }
  
}
















