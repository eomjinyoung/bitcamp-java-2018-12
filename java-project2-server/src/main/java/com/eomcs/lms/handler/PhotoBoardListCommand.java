package com.eomcs.lms.handler;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardListCommand extends AbstractCommand {

  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardListCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) {

    // SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // SqlSession으로부터 DAO 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      PhotoBoardDao photoBoardDao = sqlSession.getMapper(PhotoBoardDao.class);

      List<PhotoBoard> boards = photoBoardDao.findAll(null);

      for (PhotoBoard board : boards) {
        response.println(
            String.format("%3d, %-20s, %s, %d, %d", 
                board.getNo(), 
                board.getTitle(), 
                board.getCreatedDate(), 
                board.getViewCount(),
                board.getLessonNo()));
      }
    }
  }

}
