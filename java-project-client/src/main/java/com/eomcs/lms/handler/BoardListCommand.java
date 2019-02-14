package com.eomcs.lms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {

  Scanner keyboard;

  public BoardListCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  @Override
  public void execute(ObjectInputStream in, ObjectOutputStream out) {
    try {
      out.writeUTF("/board/list"); 
      out.flush();
      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

      String status = in.readUTF();

      if (!status.equals("OK")) 
        throw new Exception("서버의 데이터 목록 가져오기 실패!");

      @SuppressWarnings("unchecked")
      List<Board> boards = (List<Board>) in.readObject();
      for (Board board : boards) {
        System.out.printf("%3d, %-20s, %s, %d\n", 
            board.getNo(), board.getContents(), 
            board.getCreatedDate(), board.getViewCount());
      }
      
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }

}
