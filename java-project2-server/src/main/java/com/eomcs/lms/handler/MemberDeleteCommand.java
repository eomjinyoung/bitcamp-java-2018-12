package com.eomcs.lms.handler;
import com.eomcs.lms.dao.MemberDao;

public class MemberDeleteCommand extends AbstractCommand {

  MemberDao memberDao;

  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
    this.name = "/member/delete";
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");

    if (memberDao.delete(no) == 0) {
      response.println("해당 번호의 회원이 없습니다.");
      return;
    }
    response.println("삭제했습니다.");
  }
}
