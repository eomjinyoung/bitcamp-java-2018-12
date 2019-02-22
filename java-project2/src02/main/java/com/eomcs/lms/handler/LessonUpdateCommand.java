package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateCommand implements Command {

  Scanner keyboard;
  LessonDao lessonDao;
  
  public LessonUpdateCommand(Scanner keyboard, LessonDao lessonDao) {
    this.keyboard = keyboard;
    this.lessonDao = lessonDao;
  }
  

  @Override
  public void execute() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      Lesson lesson = lessonDao.findByNo(no);
      if (lesson == null) {
        System.out.println("해당 번호의 수업이 없습니다.");
        return;
      }
      
      Lesson temp = lesson.clone();
      
      System.out.printf("수업명(%s)? ", lesson.getTitle());
      String input = keyboard.nextLine();
      if (input.length() > 0) 
        temp.setTitle(input);
      
      System.out.printf("설명(%s)? ", lesson.getContents());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setContents(input);
      
      System.out.printf("시작일(%s)? ", lesson.getStartDate());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setStartDate(Date.valueOf(input));
      
      System.out.printf("종료일(%s)? ", lesson.getEndDate());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setEndDate(Date.valueOf(input));
      
      System.out.printf("총수업시간(%d)? ", lesson.getTotalHours());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setTotalHours(Integer.parseInt(input));
      
      System.out.printf("일수업시간(%d)? ", lesson.getDayHours());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setDayHours(Integer.parseInt(input));
      
      lessonDao.update(temp);
       
      System.out.println("변경했습니다.");
      
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
