package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDetailCommand extends AbstractCommand {

  SqlSessionFactory sqlSessionFactory;

  public LessonDetailCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    // SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // SqlSession으로부터 DAO 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      LessonDao lessonDao = sqlSession.getMapper(LessonDao.class);

      int no = response.requestInt("번호?");

      Lesson lesson = lessonDao.findByNo(no);
      if (lesson == null) {
        response.println("해당 번호의 수업이 없습니다.");
        return;
      }

      response.println(String.format("수업명: %s", lesson.getTitle()));
      response.println(String.format("설명: %s", lesson.getContents()));
      response.println(String.format("기간: %s ~ %s", lesson.getStartDate(), lesson.getEndDate()));
      response.println(String.format("총수업시간: %d", lesson.getTotalHours()));
      response.println(String.format("일수업시간: %d", lesson.getDayHours()));
    }
  }
}
