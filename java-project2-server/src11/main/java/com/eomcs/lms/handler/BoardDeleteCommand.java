package com.eomcs.lms.handler;
import com.eomcs.lms.dao.BoardDao;

public class BoardDeleteCommand extends AbstractCommand {
  
  BoardDao boardDao;
  
  public BoardDeleteCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");

    if (boardDao.delete(no) == 0) {
      response.println("해당 번호의 게시물이 없습니다.");
      return;
    }
    response.println("삭제했습니다.");
  }
}
