package com.eomcs.lms.agent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardAgent {
  
  @SuppressWarnings("unchecked")
  public static List<Board> list(
      ObjectInputStream in, ObjectOutputStream out) throws Exception {
    
    out.writeUTF("/board/list"); 
    out.flush();
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

    String status = in.readUTF();

    if (!status.equals("OK")) 
      throw new Exception("서버의 데이터 목록 가져오기 실패!");

    return (List<Board>) in.readObject();
  }
  
  public static void add(
      Board board, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    
    out.writeUTF("/board/add"); 
    out.flush();
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    out.writeObject(board);
    out.flush();
    
    String status = in.readUTF();
    
    if (!status.equals("OK"))
      throw new Exception("서버의 데이터 저장 실패!");
  }
  
  public static Board get(
      int no, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    
    out.writeUTF("/board/detail");
    out.flush();
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if (!status.equals("OK")) 
      throw new Exception("서버의 데이터 가져오기 실패!");
    
    return (Board) in.readObject();
  }
  
  public static void update(
      Board board, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    
    out.writeUTF("/board/update");
    out.flush();
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    out.writeObject(board);
    out.flush();
    
    String status = in.readUTF();
    if (!status.equals("OK")) 
      throw new Exception("서버의 데이터 데이터 변경 실패!");
  }
  
  public static void delete(
      int no, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    
    out.writeUTF("/board/delete");
    out.flush();
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if (!status.equals("OK")) 
      throw new Exception("서버의 데이터 삭제 실패!");
  }
}









