package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand extends AbstractCommand {

  SqlSessionFactory sqlSessionFactory;

  public MemberUpdateCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    // SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // SqlSession으로부터 DAO 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      MemberDao memberDao = sqlSession.getMapper(MemberDao.class);

      int no = response.requestInt("번호?");

      Member member = memberDao.findByNo(no);
      if (member == null) {
        response.println("해당 번호의 회원이 없습니다.");
        return;
      }

      Member temp = new Member();
      temp.setNo(no);

      String input = response.requestString(String.format(
          "이름(%s)?", member.getName()));
      if (input.length() > 0) 
        temp.setName(input);

      input = response.requestString(String.format(
          "이메일(%s)?", member.getEmail()));
      if (input.length() > 0)
        temp.setEmail(input);

      input = response.requestString("암호(새 암호를 입력하세요)?");
      if (input.length() > 0)
        temp.setPassword(input);

      input = response.requestString(String.format(
          "사진(%s)?", member.getPhoto()));
      if (input.length() > 0)
        temp.setPhoto(input);

      input = response.requestString(String.format(
          "전화(%s)?", member.getTel()));
      if (input.length() > 0)
        temp.setTel(input);

      if (temp.getName() != null
          || temp.getEmail() != null
          || temp.getPassword() != null
          || temp.getPhoto() != null
          || temp.getTel() != null) {

        memberDao.update(temp);
        sqlSession.commit();
        
        response.println("변경했습니다.");

      } else {
        response.println("변경 취소했습니다.");
      }
    }
  }
}
