package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ConnectionFactory;

public class BoardDaoImpl implements BoardDao {

  public List<Board> findAll() {
    Connection con = ConnectionFactory.create();
    
    try (PreparedStatement stmt = con.prepareStatement(
        "select board_id, conts, cdt, vw_cnt from lms_board"
            + " order by board_id desc")) {

      try (ResultSet rs = stmt.executeQuery()) {

        ArrayList<Board> list = new ArrayList<>();
        while (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_id"));
          board.setContents(rs.getString("conts"));
          board.setCreatedDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));

          list.add(board);
        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(Board board) {
    Connection con = ConnectionFactory.create();
    
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into lms_board(conts) values(?)")) {

      stmt.setString(1, board.getContents());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Board findByNo(int no) {
    Connection con = ConnectionFactory.create();
    
    try {
      // 조회수 증가시키기
      try (PreparedStatement stmt = con.prepareStatement(
          "update lms_board set vw_cnt = vw_cnt + 1 where board_id = ?")) {
        stmt.setInt(1, no);
        stmt.executeUpdate();
      }

      try (PreparedStatement stmt = con.prepareStatement(
          "select board_id, conts, cdt, vw_cnt from lms_board where board_id = ?")) {

        stmt.setInt(1, no);

        try (ResultSet rs = stmt.executeQuery()) {

          if (rs.next()) {
            Board board = new Board();
            board.setNo(rs.getInt("board_id"));
            board.setContents(rs.getString("conts"));
            board.setCreatedDate(rs.getDate("cdt"));
            board.setViewCount(rs.getInt("vw_cnt"));
            return board;
          } else {
            return null;
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Board board) {
    Connection con = ConnectionFactory.create();
    
    try (PreparedStatement stmt = con.prepareStatement(
        "update lms_board set conts = ? where board_id = ?")) {

      stmt.setString(1, board.getContents());
      stmt.setInt(2, board.getNo());

      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    Connection con = ConnectionFactory.create();
    
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from lms_board where board_id = ?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}









