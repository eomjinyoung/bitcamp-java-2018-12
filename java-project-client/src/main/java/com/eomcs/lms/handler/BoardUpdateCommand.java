package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand implements Command {
  
  Scanner keyboard;
  BoardDao boardDao;
  
  public BoardUpdateCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute() {

    try {
      Board board = new Board();

      System.out.print("번호? ");
      board.setNo(Integer.parseInt(keyboard.nextLine()));
      
      System.out.printf("내용? ");
      board.setContents(keyboard.nextLine());
      
      if (boardDao.update(board) == 0) {
        System.out.println("해당 번호의 게시물이 없습니다.");
        return;
      }
      
      System.out.println("변경했습니다.");
      
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
