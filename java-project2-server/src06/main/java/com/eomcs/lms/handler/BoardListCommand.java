package com.eomcs.lms.handler;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardListCommand extends AbstractCommand {

  BoardDao boardDao;
  
  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(Response response) {
    List<Board> boards = boardDao.findAll();
    
    for (Board board : boards) {
      response.println(
          String.format("%3d, %-20s, %s, %d", 
            board.getNo(), board.getContents(), 
            board.getCreatedDate(), board.getViewCount()));
    }
  }

}
