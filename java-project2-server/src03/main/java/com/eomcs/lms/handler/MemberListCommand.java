package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberListCommand implements Command {
  
  MemberDao memberDao;
  
  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      List<Member> members = memberDao.findAll();
      for (Member member : members) {
        out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
            member.getNo(), member.getName(), 
            member.getEmail(), member.getTel(), member.getRegisteredDate());
      }
    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
