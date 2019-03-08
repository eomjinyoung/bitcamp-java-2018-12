package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.DataSource;

public class LessonDaoImpl implements LessonDao {

  // DataSource 의존 객체 선언
  DataSource dataSource;
  
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
    Connection con = dataSource.getConnection();
    
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into lms_lesson(titl,conts,sdt,edt,tot_hr,day_hr)"
        + " values(?,?,?,?,?,?)")) {
      
      stmt.setString(1, lesson.getTitle());
      stmt.setString(2, lesson.getContents());
      stmt.setDate(3,lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHours());
      stmt.setInt(6, lesson.getDayHours());
      
      stmt.executeUpdate();
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Lesson findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("LessonMapper.findByNo", no);
    }
  }

  public int update(Lesson lesson) {
    Connection con = dataSource.getConnection();
    
    try (PreparedStatement stmt = con.prepareStatement(
        "update lms_lesson set"
        + " titl = ?,"
        + " conts = ?,"
        + " sdt = ?,"
        + " edt = ?,"
        + " tot_hr = ?,"
        + " day_hr = ?"
        + " where lesson_id = ?")) {
      
      stmt.setString(1, lesson.getTitle());
      stmt.setString(2, lesson.getContents());
      stmt.setDate(3, lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHours());
      stmt.setInt(6, lesson.getDayHours());
      stmt.setInt(7, lesson.getNo());
      
      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    Connection con = dataSource.getConnection();
    
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from lms_lesson where lesson_id = ?")) {
      
      stmt.setInt(1, no);
      
      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}









