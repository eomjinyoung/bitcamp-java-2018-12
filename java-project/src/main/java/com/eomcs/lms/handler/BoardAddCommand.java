package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

// 명령어를 처리하는 객체는 일관성 있는 사용을 위해 Command 규칙에 따라 작성되어야 한다.
public class BoardAddCommand implements Command {
   
  Scanner keyboard;
  List<Board> list;
  
  public BoardAddCommand(Scanner keyboard, List<Board> list) {
    this.keyboard = keyboard;
    this.list = list;
  }

  public void execute() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("내용? ");
    board.setContents(keyboard.nextLine());
    
    board.setCreatedDate(new Date(System.currentTimeMillis())); 
    
    board.setViewCount(0);
    
    list.add(board);
    
    System.out.println("저장하였습니다.");
  }

}
