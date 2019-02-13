// 7단계: 서버 실행 테스트
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberTest {

  ObjectOutputStream out;
  ObjectInputStream in;
  
  public MemberTest(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }
  
  public void test() throws Exception {
    add(new Member(1, "홍길동"));
    add(new Member(2, "임꺽정"));
    
    detail(1);
    
    update(new Member(1, "홍길동x"));
    
    detail(1);
    
    delete(2);
    
    list();
  }
  
  private void add(Member member) throws Exception {
    out.writeUTF("/member/add"); 
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    out.writeObject(member);
    out.flush();
    
    String status = in.readUTF();
    
    if (status.equals("OK"))
      System.out.println("데이터 추가 성공!");
    else
      System.out.println("데이터 추가 실패!");
  }
  
  private void list() throws Exception {
    out.writeUTF("/member/list"); 
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    String status = in.readUTF();
    
    if (!status.equals("OK")) {
      System.out.println("데이터 목록 가져오기 실패!");
      return;
    }
    
    @SuppressWarnings("unchecked")
    List<Member> members = (List<Member>) in.readObject();
    for (Member m : members) {
      System.out.println(m);
    }
  }
  
  private void detail(int no) throws Exception {
    out.writeUTF("/member/detail");
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if (!status.equals("OK")) {
      System.out.println("데이터 가져오기 실패!");
      return;
    }
    
    Member member = (Member) in.readObject();
    System.out.println(member);
  }
  
  private void update(Member member) throws Exception {
    out.writeUTF("/member/update");
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    out.writeObject(member);
    out.flush();
    
    String status = in.readUTF();
    
    if (status.equals("OK"))
      System.out.println("데이터 변경 성공!");
    else
      System.out.println("데이터 변경 실패!");
  }
  
  private void delete(int no) throws Exception {
    out.writeUTF("/member/delete");
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if (status.equals("OK"))
      System.out.println("데이터 삭제 성공!");
    else
      System.out.println("데이터 삭제 실패!");
  }
  


}
