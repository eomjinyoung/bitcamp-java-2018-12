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
    out.writeObject(list);
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







