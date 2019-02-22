package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonListCommand implements Command {
  
  LessonDao lessonDao;
  
  public LessonListCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  
  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      List<Lesson> lessons = lessonDao.findAll();
      for (Lesson lesson : lessons) {
        out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
            lesson.getNo(), lesson.getTitle(), 
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
      }
    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
