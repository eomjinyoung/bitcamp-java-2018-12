package com.eomcs.lms.handler;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDetailCommand extends AbstractCommand {
  
  MemberDao memberDao;
  
  public MemberDetailCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
    this.name = "/member/detail";
  }
  
  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");

    Member member = memberDao.findByNo(no);
    if (member == null) {
      response.println("해당 번호의 회원이 없습니다.");
      return;
    }
    
    response.println(String.format("이름: %s", member.getName()));
    response.println(String.format("이메일: %s", member.getEmail()));
    response.println(String.format("사진: %s", member.getPhoto()));
    response.println(String.format("전화: %s", member.getTel()));
    response.println(String.format("가입일: %s", member.getRegisteredDate()));
  }
}
