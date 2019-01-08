package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    
    System.out.print("내용? ");
    String contents = keyboard.nextLine();
    
    Date createdDate = new Date(System.currentTimeMillis()); 
    
    int viewCount = 0;
    
    // 사용후 스캐너 객체의 자원을 해제한다.
    keyboard.close();
    
    System.out.println(); // 빈 줄 출력
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("내용: %s\n", contents);
    System.out.printf("작성일: %s\n", createdDate);
    System.out.printf("조회수: %d\n", viewCount);
  }
}
