package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {

  BoardDao boardDao;
  
  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      List<Board> boards = boardDao.findAll();
      
      for (Board board : boards) {
        out.printf("%3d, %-20s, %s, %d\n", 
            board.getNo(), board.getContents(), 
            board.getCreatedDate(), board.getViewCount());
      }
      
    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }

}
