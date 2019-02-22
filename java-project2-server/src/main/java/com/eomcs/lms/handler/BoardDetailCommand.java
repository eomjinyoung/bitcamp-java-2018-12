package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailCommand implements Command {
  
  BoardDao boardDao;
  
  public BoardDetailCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      out.println("번호?");
      out.println("!{}!");
      out.flush();

      int no = Integer.parseInt(in.readLine());
      
      Board board = boardDao.findByNo(no);
      if (board == null) {
        out.println("해당 번호의 게시물이 없습니다.");
        return;
      }
      out.printf("내용: %s\n", board.getContents());
      out.printf("작성일: %s\n", board.getCreatedDate());
      out.printf("조회수: %d\n", board.getViewCount());

    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
