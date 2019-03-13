package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand extends AbstractCommand {

  SqlSessionFactory sqlSessionFactory;

  public BoardUpdateCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {

    // SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

      Board temp = new Board();
      temp.setNo(response.requestInt("번호?"));

      String input = response.requestString("내용?");
      if (input.length() > 0)
        temp.setContents(input);

      if (temp.getContents() != null) {
        if (boardDao.update(temp) == 0) {
          response.println("해당 번호의 게시물이 없습니다.");
          return;
        }
        sqlSession.commit();
        response.println("변경했습니다.");

      } else {
        response.println("변경 취소했습니다.");
      }
    }
  }
}
