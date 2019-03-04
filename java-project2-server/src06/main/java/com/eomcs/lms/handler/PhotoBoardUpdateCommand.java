package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardUpdateCommand extends AbstractCommand {
  
  PhotoBoardDao photoBoardDao;
  
  public PhotoBoardUpdateCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }
  
  @Override
  public void execute(Response response) throws Exception {
    PhotoBoard board = new PhotoBoard();
    board.setNo(response.requestInt("번호?"));
    
    PhotoBoard origin = photoBoardDao.findByNo(board.getNo());
    if (origin == null) {
      response.println("해당 번호의 사진이 없습니다.");
      return;
    }
    
    board.setTitle(response.requestString(
        String.format("제목(%s)?", origin.getTitle())));
    
    response.println("변경했습니다.");
  }
}
