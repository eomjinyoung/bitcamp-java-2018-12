package com.eomcs.lms.service;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardService extends AbstractService<Board> {

  // BoardService가 작업을 수행할 때 사용할 객체(의존 객체; dependency)
  BoardDao boardDao;
  
  public BoardService(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
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
    boardDao.insert((Board)in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    out.writeUnshared(boardDao.findAll());
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    Board obj = boardDao.findByNo(no);
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
    Board board = (Board) in.readObject();

    if (boardDao.update(board) == 0) {
      out.writeUTF("FAIL");
      return;
    }
    
    out.writeUTF("OK");
  }

  private void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    if (boardDao.delete(no) == 0) {
      out.writeUTF("FAIL");    
      return;
    }
    
    out.writeUTF("OK");
  }

}







