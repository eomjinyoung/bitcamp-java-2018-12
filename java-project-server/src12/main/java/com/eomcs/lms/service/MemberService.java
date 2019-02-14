package com.eomcs.lms.service;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberService extends AbstractService<Member> {

  MemberDao memberDao;
  
  public MemberService(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void execute(String request) throws Exception {

    switch (request) {
      case "/member/add":
        add();
        break;
      case "/member/list":
        list();
        break;
      case "/member/detail":
        detail();
        break;
      case "/member/update":
        update();
        break;
      case "/member/delete":
        delete();
        break;  
      default:
        out.writeUTF("FAIL");
    }
    out.flush();
  }

  private void add() throws Exception {
    out.writeUTF("OK");
    out.flush();
    memberDao.insert((Member)in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    out.writeUnshared(memberDao.findAll());
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    Member obj = memberDao.findByNo(no);
    if (obj == null) { 
      out.writeUTF("FAIL");
      return;
    }

    out.writeUTF("OK");
    out.writeObject(obj);
  }

  private void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    Member member = (Member) in.readObject();

    if (memberDao.update(member) == 0) {
      out.writeUTF("FAIL");
      return;
    }
    
    out.writeUTF("OK");
  }

  private void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    if (memberDao.delete(no) == 0) {
      out.writeUTF("FAIL");    
      return;
    }
    
    out.writeUTF("OK");
  }

}







