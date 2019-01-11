package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);

    final int LENGTH = 10;
    
    Lesson[] lessons = new Lesson[LENGTH];
    int lessonIdx = 0;
    
    Member[] members = new Member[LENGTH];
    int memberIdx = 0;
    
    Board[] boards = new Board[LENGTH];
    int boardIdx = 0;

    while (true) {
      System.out.print("명령> ");
      String command = keyboard.nextLine().toLowerCase();

      if (command.equals("/lesson/add")) {
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

        // i 번째 배열에 수업 정보를 담고 있는 Lesson 객체(의 주소)를 보관한다.
        lessons[lessonIdx] = lesson;
        lessonIdx++;

        System.out.println("저장하였습니다.");
        
      } else if (command.equals("/lesson/list")) {
        for (int j = 0; j < lessonIdx; j++) {
          System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
              lessons[j].no, lessons[j].title, lessons[j].startDate, 
              lessons[j].endDate, lessons[j].totalHours);
        }
      
      } else if (command.equals("/member/add")) {
        Member member = new Member();
        
        System.out.print("번호? ");
        member.no = Integer.parseInt(keyboard.nextLine());
        
        System.out.print("이름? ");
        member.name = keyboard.nextLine();
        
        System.out.print("이메일? ");
        member.email = keyboard.nextLine();
        
        System.out.print("암호? ");
        member.password = keyboard.nextLine();
    
        System.out.print("사진? ");
        member.photo = keyboard.nextLine();
    
        System.out.print("전화? ");
        member.tel = keyboard.nextLine();
    
        member.registeredDate = new Date(System.currentTimeMillis()); 
        
        members[memberIdx] = member;
        memberIdx++;
        
        System.out.println("저장하였습니다.");
        
      } else if (command.equals("/member/list")) {
        for (int j = 0; j < memberIdx; j++) {
          System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
              members[j].no, members[j].name, members[j].email, 
              members[j].tel, members[j].registeredDate);
        }
        
      } else if (command.equals("/board/add")) {
        Board board = new Board();
        
        System.out.print("번호? ");
        board.no = Integer.parseInt(keyboard.nextLine());
        
        System.out.print("내용? ");
        board.contents = keyboard.nextLine();
        
        board.createdDate = new Date(System.currentTimeMillis()); 
        
        board.viewCount = 0;
        
        boards[boardIdx] = board;
        boardIdx++;
        
        System.out.println("저장하였습니다.");
        
      } else if (command.equals("/board/list")) {
        for (int j = 0; j < boardIdx; j++) {
          System.out.printf("%3d, %-20s, %s, %d\n", 
              boards[j].no, boards[j].contents, boards[j].createdDate, boards[j].viewCount);
        }
        
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
        
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      
      System.out.println(); // 결과 출력 후 빈 줄 출력
    }

    keyboard.close();
  }
}
