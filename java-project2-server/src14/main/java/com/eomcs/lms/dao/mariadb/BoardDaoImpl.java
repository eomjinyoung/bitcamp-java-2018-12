package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  //Mybatis 의존 객체 선언
  SqlSessionFactory sqlSessionFactory;

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Board> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BoardMapper.findAll");
    }
  }

  public void insert(Board board) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("BoardMapper.insert", board);
    }
  }

  public Board findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Board board = sqlSession.selectOne("BoardMapper.findByNo", no);
      if (board != null) {
        sqlSession.update("BoardMapper.increaseCount", no);
      }
      return board;
    }
  }

  public int update(Board board) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("BoardMapper.update", board);
    }
  }

  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BoardMapper.delete", no);
    }
  }
}









