// 5단계: 서버 실행 테스트
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {

  static ObjectOutputStream out;
  static ObjectInputStream in;
  
  public static void main(String[] args) {
    
    try (Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음.");
      ServerTest.in = in;
      ServerTest.out = out;
      
      add(new Member(1, "홍길동"));
      add(new Member(2, "임꺽정"));
      
      list();
      
      out.writeUTF("okok"); out.flush();
      System.out.println(in.readUTF());
      
      quit();
      
      /*
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPassword("1111");
      member.setPassword("hong.gif");
      member.setTel("1111-1111");
      
      // Member 객체를 서버로 serialize하라!
      out.writeObject(member);
      out.flush();
      
      // 또한 서버에서 serialize한 Member 객체를 받아라. 
      System.out.println(in.readObject());
      */
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버와의 연결을 끊었음.");
  }
  
  static void add(Member member) throws Exception {
    out.writeUTF("add"); 
    out.writeObject(member);
    out.flush();
    System.out.println(in.readUTF());
  }
  
  static void list() throws Exception {
    out.writeUTF("list"); out.flush();
    @SuppressWarnings("unchecked")
    List<Member> members = (List<Member>) in.readObject();
    for (Member m : members) {
      System.out.println(m);
    }
  }
  
  static void quit() throws Exception {
    out.writeUTF("quit"); out.flush();
    System.out.println(in.readUTF());
  }

}
