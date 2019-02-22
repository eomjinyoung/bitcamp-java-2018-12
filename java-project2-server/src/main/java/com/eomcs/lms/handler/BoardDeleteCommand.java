package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import com.eomcs.lms.dao.BoardDao;

public class BoardDeleteCommand implements Command {
  
  BoardDao boardDao;
  
  public BoardDeleteCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      out.println("번호?");
      out.println("!{}!");
      out.flush();
      
      int no = Integer.parseInt(in.readLine());

      if (boardDao.delete(no) == 0) {
        out.println("해당 번호의 게시물이 없습니다.");
        return;
      }
      out.println("삭제했습니다.");
      
    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
