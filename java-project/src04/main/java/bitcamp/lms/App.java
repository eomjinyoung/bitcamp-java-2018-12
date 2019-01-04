package bitcamp.lms;

public class App {

  public static void main(String[] args) {
    System.out.println("번호: " + 1); // 문자열 리터럴과 숫자 리터럴 합치기
    System.out.println("수업명: 자바 프로젝트 실습"); // 문자열 리터럴 출력
    System.out.println("설명: " + "자바 프로젝트를 통한 자바 언어 활용법 익히기"); // 문자열 연결
    System.out.print("시작일: "); // 줄바꿈 없이 출력
    System.out.println("2019-01-02");
    System.out.print("종료일: ");
    System.out.print("2019-01-02\n"); // 문자열 속에 줄바꿈 명령 넣기
    System.out.print("총수업시간: ");
    System.out.print(1000);
    System.out.print(" 시간");
    System.out.println(); // 아규먼트 없이 호출하면 줄바꿈 명령만 실행
    System.out.printf("일수업시간: %d 시간\n", 8); // 문자열의 형식을 정의해 두고 값을 넣어 출력하기
  }
}
