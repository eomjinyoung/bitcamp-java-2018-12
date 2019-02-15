package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.agent.BoardAgent;

public class BoardDeleteCommand implements Command {
  
  Scanner keyboard;
  BoardAgent boardAgent;
  
  public BoardDeleteCommand(Scanner keyboard, BoardAgent boardAgent) {
    this.keyboard = keyboard;
    this.boardAgent = boardAgent;
  }

  @Override
  public void execute() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      boardAgent.delete(no);
      System.out.println("삭제했습니다.");
      
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
