package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    final int LENGTH = 10;
    
    Board[] boards = new Board[LENGTH];
    
    int i = 0;
    while (i < LENGTH) {
      Board board = new Board();
      
      System.out.print("번호? ");
      board.no = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("내용? ");
      board.contents = keyboard.nextLine();
      
      board.createdDate = new Date(System.currentTimeMillis()); 
      
      board.viewCount = 0;
      
      boards[i] = board;
      i++;
      
      System.out.print("\n계속 입력하시겠습니까?(Y/n) ");
      String answer = keyboard.nextLine().toLowerCase();
      
      if (!answer.equals("y") && answer.length() > 0) {
        break;
      }

      System.out.println();
    }
    
    keyboard.close();
    
    System.out.println();
    
    // 배열에 입력한 개수만큼 출력한다.
    for (int j = 0; j < i; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          boards[j].no, boards[j].contents, boards[j].createdDate, boards[j].viewCount);
    }
  }
}
