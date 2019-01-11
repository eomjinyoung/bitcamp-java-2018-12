package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);

    final int LENGTH = 10;
    
    // Lesson 인스턴스의 주소를 담을 레퍼런스를 먼저 100개 생성한다.
    Lesson[] lessons = new Lesson[100];
    
    int size = 0;
    
    while (size < LENGTH) {
      Lesson lesson = new Lesson();
      
      System.out.print("번호? ");
      lesson.no = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("수업명? ");
      lesson.title = keyboard.nextLine();
      
      System.out.print("설명? ");
      lesson.contents = keyboard.nextLine();
      
      System.out.print("시작일? ");
      lesson.startDate = Date.valueOf(keyboard.nextLine());
      
      System.out.print("종료일? ");
      lesson.endDate = Date.valueOf(keyboard.nextLine());
      
      System.out.print("총수업시간? ");
      lesson.totalHours = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("일수업시간? ");
      lesson.dayHours = Integer.parseInt(keyboard.nextLine());
      
      lessons[size++] = lesson;
      
      // 사용자가 입력한 값을 소문자로 변환한다.
      System.out.print("\n계속 입력하시겠습니까?(Y/n) ");
      String answer = keyboard.nextLine().toLowerCase();
      
      // 입력 값이 "Y", "y", "" 이 아니면, 입력을 종료한다. 
      if (!answer.equals("y") && answer.length() > 0) {
        break;
      }

      System.out.println();
    }
    
    keyboard.close();
    
    System.out.println(); // 빈 줄 출력
    
    // 배열에 입력한 개수만큼 출력한다.
    for (int i = 0; i < size; i++) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lessons[i].no, lessons[i].title, 
          lessons[i].startDate, lessons[i].endDate, 
          lessons[i].totalHours);
    }
  }
}






