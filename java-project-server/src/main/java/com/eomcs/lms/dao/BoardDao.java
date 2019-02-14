// 데이터 처리 관련 코드를 별도의 클래스로 분리
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardDao extends AbstractDao<Board> {

  public BoardDao(String filepath) {
    this.filepath = filepath;
  }
  
  public void insert(Board board) {
    list.add(board);
  }

  public List<Board> findAll() {
    return list;
  }

  public Board findByNo(int no) {
    for (Board b : list) {
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }

  public int update(Board board) {
    int index = 0;
    for (Board b : list) {
      if (b.getNo() == board.getNo()) {
        list.set(index, board);
        return 1;
      }
      index++;
    }
    return 0;
  }

  public int delete(int no) {
    int index = 0;
    for (Board b : list) {
      if (b.getNo() == no) {
        list.remove(index);
        return 1;
      }
      index++;
    }
    return 0;
  }

}







