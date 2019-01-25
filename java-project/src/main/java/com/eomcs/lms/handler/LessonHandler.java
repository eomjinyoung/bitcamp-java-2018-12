package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {

  Scanner keyboard;
  List<Lesson> list;

  public LessonHandler(Scanner keyboard, List<Lesson> list) {
    this.keyboard = keyboard;
    
    // 다음과 같이 이 클래스가 사용할 List 객체를 LinkedList로 결정해 버리면,
    // List 사용 규칙을 따르는 다른 객체로 쉽게 변경할 수 없다.
    // 다른 List 객체로 변경하려면 이 클래스의 생성자 코드를 바꿔야 한다.
    // 어떤 기능을 변경할 때마다 소스 코드를 변경하는 방식은 유지보수에 좋지 않다.
    // 해결책?
    // => 이 클래스에서 어떤 List 객체를 사용할 것인지 결정하지 말고 
    //    이 클래스를 사용하는 쪽에서 결정하도록 유도하라!
    // => 즉 생성자에게 List 객체를 준비하지 말고 파라미터로 주입 받아라.
    //    이렇게 자기가 작업하는데 사용하는 도구를 "의존 객체(dependency)" 라 부른다.
    //    그리고 의존 객체를 자기가 생성하지 않고 외부로부터 주입 받는 것을 
    //    "의존 객체 주입(dependency injection; DI)"라고 부른다.
    // 
    //this.list = new LinkedList<>(); // 이 클래스에서 의존 객체를 결정하지 말자!
    this.list = list; // 파라미터로 주입된 의존 객체를 저장한다.
  }

  public void listLesson() {
    Lesson[] lessons = list.toArray(new Lesson[0]);
    for (Lesson lesson : lessons) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lesson.getNo(), lesson.getTitle(), 
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
    }
  }

  public void addLesson() {
    Lesson lesson = new Lesson();

    System.out.print("번호? ");
    lesson.setNo(Integer.parseInt(keyboard.nextLine()));

    System.out.print("수업명? ");
    lesson.setTitle(keyboard.nextLine());

    System.out.print("설명? ");
    lesson.setContents(keyboard.nextLine());

    System.out.print("시작일? ");
    lesson.setStartDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("종료일? ");
    lesson.setEndDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("총수업시간? ");
    lesson.setTotalHours(Integer.parseInt(keyboard.nextLine()));

    System.out.print("일수업시간? ");
    lesson.setDayHours(Integer.parseInt(keyboard.nextLine()));

    list.add(lesson);

    System.out.println("저장하였습니다.");
  }
  
  public void detailLesson() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    int index = indexOfLesson(no);
    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }

    Lesson lesson = list.get(index);

    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("설명: %s\n", lesson.getContents());
    System.out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
  }
  
  public void updateLesson() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    int index = indexOfLesson(no);
    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }
    
    Lesson lesson = list.get(index);
    
    try {
      // 일단 기존 값을 복제한다.
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
      
      list.set(index, temp);
      
      System.out.println("수업을 변경했습니다.");
      
    } catch (Exception e) {
      System.out.println("변경 중 오류 발생!");
    }
  }
  
  public void deleteLesson() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    int index = indexOfLesson(no);
    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }
    
    list.remove(index);
    
    System.out.println("수업을 삭제했습니다.");
  }
  
  private int indexOfLesson(int no) {
    for (int i = 0; i < list.size(); i++) {
      Lesson l = list.get(i);
      if (l.getNo() == no)
        return i;
    }
    return -1;
  }
}
