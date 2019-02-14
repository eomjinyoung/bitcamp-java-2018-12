// 11단계: AbstractService 상속 받기
package com.eomcs.lms.service;

import com.eomcs.lms.domain.Board;

public class BoardService extends AbstractService<Board> {

  public void execute(String request) throws Exception {

    switch (request) {
      case "/board/add":
        add();
        break;
      case "/board/list":
        list();
        break;
      case "/board/detail":
        detail();
        break;
      case "/board/update":
        update();
        break;
      case "/board/delete":
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
    list.add((Board)in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    
    // ArrayList를 출력하기 위해 serialize 하여 바이트 배열을 만든다.
    // 내부적으로 이렇게 생성된 바이트 배열의 주소를 보관한다.
    // 다음에 또 같은 인스턴스에 대해서 serialize를 수행하면 
    // 성능을 위해 이전에 만든 바이트 배열을 그대로 사용한다.
    // 문제는 ArrayList의 항목이 변경되어도 
    // 이전에 생성한 것을 그대로 사용하기 때문에 
    // 변경된 데이터가 새로 serialize 되지 않는다.
    //out.writeObject(list);
    
    // 그러나 writeUnshared()를 사용하면 
    // 무조건 해당 인스턴스에 대해 새로 serialize를 수행한다.
    // 그리고 그 바이트 배열을 출력한다.
    out.writeUnshared(list);
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    for (Board b : list) {
      if (b.getNo() == no) {
        out.writeUTF("OK");
        out.writeObject(b);
        return;
      }
    }

    out.writeUTF("FAIL");
  }

  private void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    Board board = (Board) in.readObject();

    int index = 0;
    for (Board b : list) {
      if (b.getNo() == board.getNo()) {
        list.set(index, board);
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
    for (Board b : list) {
      if (b.getNo() == no) {
        list.remove(index);
        out.writeUTF("OK");
        return;
      }
      index++;
    }

    out.writeUTF("FAIL");    
  }

}







