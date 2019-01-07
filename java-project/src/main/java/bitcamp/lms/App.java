package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    
    // 키보드 입력 스트림(System.in)을 스캐너 객체에 연결한다.
    // => 스캐너는 입력 스트림으로 들어온 문자열을 줄 단위로 잘라주는 역할을 수행한다.
    Scanner keyboard = new Scanner(System.in);

    // 콘솔에서 입력 받은 값을 변수에 저장한다.
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    
    System.out.print("수업명? ");
    String title = keyboard.nextLine();
    
    System.out.print("설명? ");
    String contents = keyboard.nextLine();
    
    System.out.print("시작일? ");
    Date startDate = Date.valueOf(keyboard.nextLine());
    
    System.out.print("종료일? ");
    Date endDate = Date.valueOf(keyboard.nextLine());
    
    System.out.print("총수업시간? ");
    int totalHours = Integer.parseInt(keyboard.nextLine());
    
    System.out.print("일수업시간? ");
    int dayHours = Integer.parseInt(keyboard.nextLine());
    
    // 사용후 스캐너 객체의 자원을 해제한다.
    keyboard.close();
    
    System.out.println(); // 빈 줄 출력
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("수업명: %s\n", title);
    System.out.printf("설명: %s\n", contents);
    System.out.printf("기간: %s ~ %s\n", startDate, endDate);
    System.out.printf("총수업시간: %d 시간\n", totalHours);
    System.out.printf("일수업시간: %d 시간\n", dayHours);
  }
}
