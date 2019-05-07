package com.eomcs.lms.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@Controller
@RequestMapping("/lesson")
public class LessonController {
  
  @Autowired LessonService lessonService;

  @GetMapping("form")
  public void form() {
  }
  
  @PostMapping("add")
  public String add(Lesson lesson) {
    lessonService.add(lesson);
    return "redirect:.";
  }
  
  @GetMapping("delete/{no}")
  public String delete(@PathVariable int no) {

    if (lessonService.delete(no) == 0) 
      throw new RuntimeException("해당 번호의 수업이 없습니다.");
      
    return "redirect:../";
  }
  
  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) {
    Lesson lesson = lessonService.get(no);
    model.addAttribute("lesson", lesson);
    return "lesson/detail";
  }

  @GetMapping
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize,
      String search,
      Model model) {
    
    if (pageSize < 3 || pageSize > 8) 
      pageSize = 3;
    
    int rowCount = lessonService.size(search);
    int totalPage = rowCount / pageSize;
    if (rowCount % pageSize > 0)
      totalPage++;
    
    if (pageNo > totalPage)
      pageNo = totalPage;
    if (pageNo < 1) 
      pageNo = 1;
    
    List<Lesson> lessons = lessonService.list(pageNo, pageSize, search);
    model.addAttribute("list", lessons);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("search", search);
    
    return "lesson/list";
  }
  
  @PostMapping("update")
  public String update(Lesson lesson) {
    if (lessonService.update(lesson) == 0)
      throw new RuntimeException("해당 번호의 수업이 없습니다.");
    return "redirect:.";
  }
}
