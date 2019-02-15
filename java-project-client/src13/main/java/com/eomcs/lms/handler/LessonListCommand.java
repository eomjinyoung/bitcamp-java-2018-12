package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.agent.LessonAgent;
import com.eomcs.lms.domain.Lesson;

public class LessonListCommand implements Command {
  
  Scanner keyboard;
  LessonAgent lessonAgent;
  
  public LessonListCommand(Scanner keyboard, LessonAgent lessonAgent) {
    this.keyboard = keyboard;
    this.lessonAgent = lessonAgent;
  }
  
  
  @Override
  public void execute() {
    try {
      List<Lesson> lessons = lessonAgent.list();
      for (Lesson lesson : lessons) {
        System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
            lesson.getNo(), lesson.getTitle(), 
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
      }
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
