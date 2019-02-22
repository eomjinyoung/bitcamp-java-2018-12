package com.eomcs.lms.handler;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand extends AbstractCommand {

  MemberDao memberDao;

  public MemberUpdateCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");

    Member member = memberDao.findByNo(no);
    if (member == null) {
      response.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member temp = member.clone();

    String input = response.requestString(String.format(
        "이름(%s)?", member.getName()));
    if (input.length() > 0) 
      temp.setName(input);

    input = response.requestString(String.format(
        "이메일(%s)?", member.getEmail()));
    if (input.length() > 0)
      temp.setEmail(input);

    input = response.requestString("암호(새 암호를 입력하세요)?");
    temp.setPassword(input);

    input = response.requestString(String.format(
        "사진(%s)?", member.getPhoto()));
    if (input.length() > 0)
      temp.setPhoto(input);

    input = response.requestString(String.format(
        "전화(%s)?", member.getTel()));
    if (input.length() > 0)
      temp.setTel(input);

    memberDao.update(temp);
    response.println("변경했습니다.");
  }
}
