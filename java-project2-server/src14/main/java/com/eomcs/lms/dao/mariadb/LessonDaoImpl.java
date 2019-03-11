package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {

  // Mybatis 의존 객체 선언
  SqlSessionFactory sqlSessionFactory;
  
  public LessonDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
  
  public List<Lesson> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("LessonMapper.findAll");
    }
  }

  public void insert(Lesson lesson) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("LessonMapper.insert", lesson);
    }
  }

  public Lesson findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("LessonMapper.findByNo", no);
    }
  }

  public int update(Lesson lesson) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("LessonMapper.update", lesson);
    }
  }

  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("LessonMapper.delete", no);
    }
  }
}









