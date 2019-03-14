package com.eomcs.lms.handler;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailCommand extends AbstractCommand {
  
  BoardDao boardDao;
  
  public BoardDetailCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
    this.name = "/board/detail";
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");
    
    Board board = boardDao.findByNo(no);
    if (board == null) {
      response.println("해당 번호의 게시물이 없습니다.");
      return;
    }
    // 게시물 데이터를 가져왔으면 조회수를 증가시킨다.
    boardDao.increaseCount(no);
    
    response.println(String.format("내용: %s", board.getContents()));
    response.println(String.format("작성일: %s", board.getCreatedDate()));
    response.println(String.format("조회수: %d", board.getViewCount()));
  }
}
