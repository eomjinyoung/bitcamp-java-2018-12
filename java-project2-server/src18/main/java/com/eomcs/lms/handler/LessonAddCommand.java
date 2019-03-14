package com.eomcs.lms.handler;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddCommand extends AbstractCommand {

  LessonDao lessonDao;
  
  public LessonAddCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
    this.name = "/lesson/add";
  }
  

  @Override
  public void execute(Response response) throws Exception {
    Lesson lesson = new Lesson();
    lesson.setTitle(response.requestString("수업명?"));
    lesson.setContents(response.requestString("설명?"));
    lesson.setStartDate(response.requestDate("시작일?"));
    lesson.setEndDate(response.requestDate("종료일?"));
    lesson.setTotalHours(response.requestInt("총수업시간?"));
    lesson.setDayHours(response.requestInt("일수업시간?"));
    
    lessonDao.insert(lesson);
    response.println("저장하였습니다.");
  }
  
}
