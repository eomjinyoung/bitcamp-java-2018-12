// 8단계: 서버 실행 테스트
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardTest {

  ObjectOutputStream out;
  ObjectInputStream in;
  
  public BoardTest(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }
  
  public void test() throws Exception {
    add(new Board(1, "내용내용"));
    add(new Board(2, "내용입니다."));
    
    //detail(1);
    
    //update(new Board(1, "내용내용222"));
    
    //detail(1);
    
    //delete(2);
    
    list();
  }
  
  private void add(Board board) throws Exception {
    out.writeUTF("/board/add"); 
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    out.writeObject(board);
    out.flush();
    
    String status = in.readUTF();
    
    if (status.equals("OK"))
      System.out.println("데이터 추가 성공!");
    else
      System.out.println("데이터 추가 실패!");
  }
  
  private void list() throws Exception {
    out.writeUTF("/board/list"); 
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    String status = in.readUTF();
    
    if (!status.equals("OK")) {
      System.out.println("데이터 목록 가져오기 실패!");
      return;
    }
    
    @SuppressWarnings("unchecked")
    List<Board> boards = (List<Board>) in.readObject();
    for (Board b : boards) {
      System.out.println(b);
    }
  }
  
  private void detail(int no) throws Exception {
    out.writeUTF("/board/detail");
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
    
    Board board = (Board) in.readObject();
    System.out.println(board);
  }
  
  private void update(Board board) throws Exception {
    out.writeUTF("/board/update");
    out.flush();
    if (!in.readUTF().equals("OK"))
      return;
    
    out.writeObject(board);
    out.flush();
    
    String status = in.readUTF();
    
    if (status.equals("OK"))
      System.out.println("데이터 변경 성공!");
    else
      System.out.println("데이터 변경 실패!");
  }
  
  private void delete(int no) throws Exception {
    out.writeUTF("/board/delete");
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
