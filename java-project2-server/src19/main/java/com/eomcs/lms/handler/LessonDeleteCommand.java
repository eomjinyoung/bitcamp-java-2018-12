package com.eomcs.lms.handler;
import java.util.HashMap;
import java.util.List;
import com.eomcs.lms.context.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.mybatis.TransactionManager;

@Component("/lesson/delete")
public class LessonDeleteCommand extends AbstractCommand {

  LessonDao lessonDao;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  TransactionManager txManager;
  
  public LessonDeleteCommand(
      LessonDao lessonDao, 
      PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao,
      TransactionManager txManager) {
    this.lessonDao = lessonDao;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }
  

  @Override
  public void execute(Response response) throws Exception {
    txManager.beginTransaction();
    try {
      int no = response.requestInt("번호?");
      
      HashMap<String,Object> params = new HashMap<>();
      params.put("lessonNo", no);
      
      List<PhotoBoard> boards = photoBoardDao.findAll(params);
      for (PhotoBoard board : boards) {
        photoFileDao.deleteByPhotoBoardNo(board.getNo());
        photoBoardDao.delete(board.getNo());
      }
      
      if (lessonDao.delete(no) == 0) {
        response.println("해당 번호의 수업이 없습니다.");
        return;
      }
      response.println("삭제했습니다.");
      txManager.commit();
      
    } catch (Exception e) {
      txManager.rollback();
      response.println("삭제 중 오류 발생.");
    }
  }
}




