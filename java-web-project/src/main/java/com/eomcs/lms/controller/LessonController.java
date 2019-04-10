package com.eomcs.lms.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.context.RequestParam;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@Controller
public class LessonController {
  
  @Autowired LessonService lessonService;

  @RequestMapping("/lesson/form")
  public String form() {
    return "/lesson/form.jsp";
  }
  
  @RequestMapping("/lesson/add")
  public String add(
      @RequestParam("title") String title,
      @RequestParam("contents") String contents,
      @RequestParam("startDate") Date startDate,
      @RequestParam("endDate") Date endDate,
      @RequestParam("totalHours") int totalHours,
      @RequestParam("dayHours") int dayHours) throws Exception {
    
    Lesson lesson = new Lesson();
    lesson.setTitle(title);
    lesson.setContents(contents);
    lesson.setStartDate(startDate);
    lesson.setEndDate(endDate);
    lesson.setTotalHours(totalHours);
    lesson.setDayHours(dayHours);

    lessonService.add(lesson);

    return "redirect:list";
  }
  
  @RequestMapping("/lesson/delete")
  public String delete(
      @RequestParam("no") int no) throws Exception {

    if (lessonService.delete(no) == 0) 
      throw new Exception("해당 번호의 수업이 없습니다.");
      
    return "redirect:list";
  }
  
  @RequestMapping("/lesson/detail")
  public String detail(
      @RequestParam("no") int no,
      Map<String,Object> map) throws Exception {

    Lesson lesson = lessonService.get(no);
    map.put("lesson", lesson);
    return "/lesson/detail.jsp";
  }

  @RequestMapping("/lesson/list")
  public String list(Map<String,Object> map) throws Exception {
    List<Lesson> lessons = lessonService.list();
    map.put("list", lessons);
    return "/lesson/list.jsp";
  }
  
  @RequestMapping("/lesson/update")
  public String update(
      @RequestParam("no") int no,
      @RequestParam("title") String title,
      @RequestParam("contents") String contents,
      @RequestParam("startDate") Date startDate,
      @RequestParam("endDate") Date endDate,
      @RequestParam("totalHours") int totalHours,
      @RequestParam("dayHours") int dayHours) throws Exception {

    Lesson lesson = new Lesson();
    lesson.setNo(no);
    lesson.setTitle(title);
    lesson.setContents(contents);
    lesson.setStartDate(startDate);
    lesson.setEndDate(endDate);
    lesson.setTotalHours(totalHours);
    lesson.setDayHours(dayHours);


    if (lessonService.update(lesson) == 0)
      throw new Exception("해당 번호의 수업이 없습니다.");
    
    return "redirect:list";
  }
}
