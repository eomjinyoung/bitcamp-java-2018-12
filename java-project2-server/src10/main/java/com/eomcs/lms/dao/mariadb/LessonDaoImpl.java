// DBMS 적용
package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ConnectionFactory;

public class LessonDaoImpl implements LessonDao {

  public List<Lesson> findAll() {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement stmt = con.prepareStatement(
        "select lesson_id, titl, sdt, edt, tot_hr from lms_lesson"
        + " order by lesson_id desc")) {
      
      try (ResultSet rs = stmt.executeQuery()) {
        
        ArrayList<Lesson> list = new ArrayList<>();
        while (rs.next()) {
          Lesson lesson = new Lesson();
          lesson.setNo(rs.getInt("lesson_id"));
          lesson.setTitle(rs.getString("titl"));
          lesson.setStartDate(rs.getDate("sdt"));
          lesson.setEndDate(rs.getDate("edt"));
          lesson.setTotalHours(rs.getInt("tot_hr"));
          
          list.add(lesson);
        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(Lesson lesson) {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement stmt = con.prepareStatement(
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
    try (Connection con = ConnectionFactory.create();
        PreparedStatement stmt = con.prepareStatement(
        "select lesson_id, titl, conts, sdt, edt, tot_hr, day_hr"
        + " from lms_lesson"
        + " where lesson_id = ?")) {
      
      stmt.setInt(1, no);
      
      try (ResultSet rs = stmt.executeQuery()) {
      
        if (rs.next()) {
          Lesson lesson = new Lesson();
          lesson.setNo(rs.getInt("lesson_id"));
          lesson.setTitle(rs.getString("titl"));
          lesson.setContents(rs.getString("conts"));
          lesson.setStartDate(rs.getDate("sdt"));
          lesson.setEndDate(rs.getDate("edt"));
          lesson.setTotalHours(rs.getInt("tot_hr"));
          lesson.setDayHours(rs.getInt("day_hr"));
          return lesson;
          
        } else {
          return null;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Lesson lesson) {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement stmt = con.prepareStatement(
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
    try (Connection con = ConnectionFactory.create();
        PreparedStatement stmt = con.prepareStatement(
        "delete from lms_lesson where lesson_id = ?")) {
      
      stmt.setInt(1, no);
      
      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}









