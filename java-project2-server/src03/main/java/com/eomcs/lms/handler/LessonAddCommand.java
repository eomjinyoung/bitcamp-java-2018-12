package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddCommand implements Command {

  LessonDao lessonDao;
  
  public LessonAddCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  

  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      Lesson lesson = new Lesson();
      
      out.println("수업명?\n!{}!");
      out.flush();
      lesson.setTitle(in.readLine());
      
      out.println("설명?\n!{}!");
      out.flush();
      lesson.setContents(in.readLine());
      
      out.println("시작일?\n!{}!");
      out.flush();
      lesson.setStartDate(Date.valueOf(in.readLine()));
      
      out.println("종료일?\n!{}!");
      out.flush();
      lesson.setEndDate(Date.valueOf(in.readLine()));
      
      out.println("총수업시간?\n!{}!");
      out.flush();
      lesson.setTotalHours(Integer.parseInt(in.readLine()));
      
      out.println("일수업시간?\n!{}!");
      out.flush();
      lesson.setDayHours(Integer.parseInt(in.readLine()));
      
      lessonDao.insert(lesson);
      out.println("저장하였습니다.");
      
    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
  
}
