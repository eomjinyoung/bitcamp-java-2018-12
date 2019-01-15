package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  
  static final int LENGTH = 1000;
  
  public Scanner keyboard;
  Board[] boards = new Board[LENGTH];
  int boardIdx = 0;

  public void listBoard() {
    for (int j = 0; j < boardIdx; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          this.boards[j].no, this.boards[j].contents, 
          this.boards[j].createdDate, this.boards[j].viewCount);
    }
  }

  public void addBoard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.no = Integer.parseInt(keyboard.nextLine());
    
    System.out.print("내용? ");
    board.contents = keyboard.nextLine();
    
    board.createdDate = new Date(System.currentTimeMillis()); 
    
    board.viewCount = 0;
    
    this.boards[this.boardIdx] = board;
    this.boardIdx++;
    
    System.out.println("저장하였습니다.");
  }

}
