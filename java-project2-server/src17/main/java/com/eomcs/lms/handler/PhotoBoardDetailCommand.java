package com.eomcs.lms.handler;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardDetailCommand extends AbstractCommand {

  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDetailCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    // SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // SqlSession으로부터 DAO 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      PhotoBoardDao photoBoardDao = sqlSession.getMapper(PhotoBoardDao.class);

      int no = response.requestInt("번호?");

      // lms_photo 테이블의 데이터와 lms_photo_file 테이블의 데이터를 조인하여 결과를 가져온다. 
      // 그 결과를 PhotoBoard 객체에 저장한다.
      // 특히 lms_photo_file 데이터는 PhotoFile 객체에 저장되고, 
      // 그 목록은 PhotoBoard 객체에 포함되어 리턴된다.
      PhotoBoard board = photoBoardDao.findByNoWithFile(no);
      if (board == null) {
        response.println("해당 사진을 찾을 수 없습니다.");
        return;
      }

      photoBoardDao.increaseCount(no); // 조회수 증가
      sqlSession.commit();
      
      response.println(String.format("제목: %s", board.getTitle()));
      response.println(String.format("작성일: %s", board.getCreatedDate()));
      response.println(String.format("조회수: %d", board.getViewCount()));
      response.println(String.format("수업: %s(%s ~ %s)", 
          board.getLesson().getTitle(),
          board.getLesson().getStartDate(),
          board.getLesson().getEndDate()));

      response.println("사진파일:");
      List<PhotoFile> files = board.getFiles();
      for (PhotoFile file : files) {
        response.println(String.format("> %s", file.getFilePath()));
      }
    }
  }
}





