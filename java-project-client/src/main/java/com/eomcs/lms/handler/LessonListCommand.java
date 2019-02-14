package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonListCommand implements Command {
  
  Scanner keyboard;

  public LessonListCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute(ObjectInputStream in, ObjectOutputStream out) {
    try {
      out.writeUTF("/lesson/list"); 
      out.flush();
      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

      String status = in.readUTF();

      if (!status.equals("OK")) 
        throw new Exception("서버의 데이터 목록 가져오기 실패!");

      @SuppressWarnings("unchecked")
      List<Lesson> lessons = (List<Lesson>) in.readObject();
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
