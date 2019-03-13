package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailCommand extends AbstractCommand {

  SqlSessionFactory sqlSessionFactory;

  public BoardDetailCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    // SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);    
      
      int no = response.requestInt("번호?");

      Board board = boardDao.findByNo(no);
      if (board == null) {
        response.println("해당 번호의 게시물이 없습니다.");
        return;
      }
      // 게시물 데이터를 가져왔으면 조회수를 증가시킨다.
      boardDao.increaseCount(no);
      sqlSession.commit();
      
      response.println(String.format("내용: %s", board.getContents()));
      response.println(String.format("작성일: %s", board.getCreatedDate()));
      response.println(String.format("조회수: %d", board.getViewCount()));
    }
  }
}
