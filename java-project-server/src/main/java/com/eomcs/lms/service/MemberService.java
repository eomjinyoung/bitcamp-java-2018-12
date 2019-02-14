// 11단계: AbstractService 상속 받기
package com.eomcs.lms.service;

import com.eomcs.lms.domain.Member;

public class MemberService extends AbstractService<Member> {

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
    list.add((Member)in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    //out.writeObject(list);
    out.writeUnshared(list);
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    for (Member m : list) {
      if (m.getNo() == no) {
        out.writeUTF("OK");
        out.writeObject(m);
        return;
      }
    }

    out.writeUTF("FAIL");
  }

  private void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    Member member = (Member) in.readObject();

    int index = 0;
    for (Member m : list) {
      if (m.getNo() == member.getNo()) {
        list.set(index, member);
        out.writeUTF("OK");
        return;
      }
      index++;
    }

    out.writeUTF("FAIL");
  }

  private void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    int index = 0;
    for (Member m : list) {
      if (m.getNo() == no) {
        list.remove(index);
        out.writeUTF("OK");
        return;
      }
      index++;
    }

    out.writeUTF("FAIL");    
  }

}







