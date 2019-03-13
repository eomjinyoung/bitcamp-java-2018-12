package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberAddCommand extends AbstractCommand {

  SqlSessionFactory sqlSessionFactory;

  public MemberAddCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    // SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // SqlSession으로부터 DAO 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      MemberDao memberDao = sqlSession.getMapper(MemberDao.class);

      Member member = new Member();
      member.setName(response.requestString("이름?"));
      member.setEmail(response.requestString("이메일?"));
      member.setPassword(response.requestString("암호?"));
      member.setPhoto(response.requestString("사진?"));
      member.setTel(response.requestString("전화?"));

      memberDao.insert(member);
      sqlSession.commit();
      
      response.println("저장하였습니다.");
    }
  }
}
