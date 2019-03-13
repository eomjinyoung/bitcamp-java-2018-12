package com.eomcs.lms.handler;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardAddCommand extends AbstractCommand {
  
  BoardDao boardDao; // 서버의 BoardDaoImpl 객체를 대행하는 프록시 객체이다.
  
  public BoardAddCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(Response response) throws Exception {
    Board board = new Board();
    board.setContents(response.requestString("내용?"));
    boardDao.insert(board);
    
    response.println("저장하였습니다.");
  }
}



