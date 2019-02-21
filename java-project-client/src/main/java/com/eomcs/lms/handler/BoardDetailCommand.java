package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao;
  
  public BoardDetailCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    
    try {
      Board board = boardDao.findByNo(no);
      if (board == null) {
        System.out.println("해당 번호의 게시물이 없습니다.");
        return;
      }
      System.out.printf("내용: %s\n", board.getContents());
      System.out.printf("작성일: %s\n", board.getCreatedDate());
      System.out.printf("조회수: %d\n", board.getViewCount());

    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
